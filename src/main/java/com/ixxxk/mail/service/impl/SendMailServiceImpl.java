package com.ixxxk.mail.service.impl;

import com.ixxxk.mail.service.SendMailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 邮件发送
 *
 * @author Tany
 */
@Slf4j
@Service
public class SendMailServiceImpl implements SendMailService {

	@Autowired
	private JavaMailSender javaMailSender;

	/**
	 * 发件人的邮箱地址<br>
	 * 即我们配置的邮件服务的邮箱
	 */
	@Value("${spring.mail.username}")
	private String serverMail;

	/**
	 * 发送邮件
	 *
	 * @param toUser  收件人邮件地址
	 * @param subject 标题
	 * @param text    正文
	 * @return
	 */
	@Override
	public boolean sendMail(String toUser, String subject, String text, String ip) {
		try {
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
			javax.mail.internet.MimeMessage message = javaMailSender.createMimeMessage();
			text += ",ip:" + ip + ",时间：" + sdf.format(now);
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(serverMail);
			helper.setTo(toUser);
			helper.setSubject(subject);
			helper.setText(text, true);
			javaMailSender.send(message);
			log.info("发送邮件to:{},主题：{},内容：{}", toUser, subject, text);
		} catch (Exception e) {
			log.error("", e);
			return Boolean.FALSE;
		}
		return Boolean.TRUE;

	}
}

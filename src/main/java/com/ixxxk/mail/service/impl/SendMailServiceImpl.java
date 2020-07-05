package com.ixxxk.mail.service.impl;

import com.ixxxk.mail.mapper.HelloMapper;
import com.ixxxk.mail.pojo.dto.MailDto;
import com.ixxxk.mail.pojo.entity.HelloInfo;
import com.ixxxk.mail.service.SendMailService;
import com.ixxxk.mail.util.Consts;
import com.ixxxk.mail.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


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

	@Resource
	private HelloMapper helloMapper;

	/**
	 * 发件人的邮箱地址<br>
	 * 即我们配置的邮件服务的邮箱
	 */
	@Value("${spring.mail.username}")
	private String serverMail;

	@Override
	public void sendHello() {
		List<HelloInfo> helloInfoList = helloMapper.findByYesterday();
		SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.YYYY_MM_DD_HH_MM_SS_SSS);
		String subject = "访客统计";
		StringBuffer sb = new StringBuffer();
		for (HelloInfo helloInfo : helloInfoList) {
			sb.append("ip：").append(helloInfo.getIp());
			sb.append(Consts.BR);
			sb.append("城市：").append(helloInfo.getCity());
			sb.append(Consts.BR);
			sb.append("时间：").append(sdf.format(helloInfo.getCreateTime()));
			sb.append("---------------------------------------------------------------");
			sb.append(Consts.BR);
		}
		this.sendMail(Consts.MY_EMAIL, subject, sb.toString(), true);
	}

	@Override
	public boolean send(MailDto mailDto) {
		String subject = "主页邮件";
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.YYYY_MM_DD_HH_MM_SS_SSS);
		String nowDateString = sdf.format(now);
		StringBuffer sb = new StringBuffer();
		sb.append("ip：").append(mailDto.getIp());
		sb.append(Consts.BR);
		sb.append("城市：").append(mailDto.getCity());
		sb.append(Consts.BR);
		sb.append("时间：").append(nowDateString);
		sb.append(Consts.BR);
		sb.append("姓名：").append(mailDto.getName());
		sb.append(Consts.BR);
		sb.append("发件人：").append(mailDto.getEmail());
		sb.append(Consts.BR);
		sb.append("内容：");
		sb.append("    ").append(mailDto.getText());
		log.info("send...............");
		boolean b = this.sendMail(Consts.MY_EMAIL, subject, sb.toString(), true);
		this.sendMail(mailDto.getEmail(), "Tany. 的回信", "您的消息已收到，感谢您的来信。", false);
		return b;
	}

	/**
	 * 发送邮件
	 *
	 * @param toUser  收件人邮件地址
	 * @param subject 标题
	 * @param text    正文
	 * @return
	 */
	@Override
	public boolean sendMail(String toUser, String subject, String text, boolean isHtml) {
		try {
			if (!isHtml) {
				text = text.replaceAll(Consts.BR, Consts.ENTER);
			}
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(serverMail);
			helper.setTo(toUser);
			helper.setSubject(subject);
			helper.setText(text, isHtml);
			javaMailSender.send(message);
			log.info("发送邮件to:{},主题：{},内容：{}", toUser, subject, text);
		} catch (Exception e) {
			log.error("", e);
			return Boolean.FALSE;
		}
		return Boolean.TRUE;

	}
}

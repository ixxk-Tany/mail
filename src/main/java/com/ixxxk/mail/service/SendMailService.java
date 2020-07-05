package com.ixxxk.mail.service;

import com.ixxxk.mail.pojo.dto.MailDto;

public interface SendMailService {
	/**
	 * 发送邮件
	 * @param mailDto mailDto
	 * @return boolean
	 */
	boolean send(MailDto mailDto);

	/**
	 * 发送统计邮件
	 */
	void sendHello();

	boolean sendMail(String toUser, String subject, String text, boolean isHtml);
}

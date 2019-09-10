package pan.springbootkit.mail;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

/**
 * 邮件 util
 * 		注意：每秒只能发送一次
 *
 * Created by panzhangbao on 2019-09-10 17:10:15
 * Copyright © 2019 panzhangbao. All rights reserved.
 */
@Slf4j
public class PanMailUtil {

	/**
	 * smtp服务器地址
	 */
	private static final String MAIL_SMTP_HOST = "smtp.126.com";

	/**
	 * 发件人邮箱
	 */
	private static final String FROM_EMAIL = "panzhangbao@126.com";

	/**
	 * 客户端授权码
	 */
	private static final String SENDER_AUTHORIZATION_CODE = "";

	/**
	 * 发送邮件
	 *
	 * @param toEmail
	 * @param content
	 * @return java.lang.Boolean
	 * @date 2019-09-10 18:08
	 * @author panzhangbao
	 */
	public static Boolean send(String toEmail, String content) {
		return send(toEmail, content, null, null);
	}

	/**
	 * 发送邮件，带主题
	 *
	 * @param toEmail
	 * @param content
	 * @param subject
	 * @return java.lang.Boolean
	 * @date 2019-09-10 18:08
	 * @author panzhangbao
	 */
	public static Boolean sendBySubject(String toEmail, String content, String subject) {
		return send(toEmail, content, subject, null);
	}

	/**
	 * 发送邮件，带附件
	 *
	 * @param toEmail
	 * @param content
	 * @param appendixUrl
	 * @return java.lang.Boolean
	 * @date 2019-09-10 18:08
	 * @author panzhangbao
	 */
	public static Boolean sendByAppendix(String toEmail, String content, String appendixUrl) {
		return send(toEmail, content, null, appendixUrl);
	}

	/**
	 * 发送邮件
	 *
	 * @param toEmail 收件人邮箱
	 * @param content	邮件内容
	 * @param subject	主题
	 * @param appendixUrl 附件地址
	 *
	 * @return java.lang.Boolean
	 * @date 2019-09-10 17:11
	 * @author panzhangbao
	 */
	public static Boolean send(String toEmail, String content, String subject, String appendixUrl) {
		/**
		 * 参数合法性校验
		 */
		if (StringUtils.isBlank(content) || StringUtils.isBlank(toEmail)) {
			return false;
		}

		StringBuilder info = new StringBuilder("----------------		开始发送邮件		----------------");
		if (StringUtils.isNotBlank(subject)) {
			info.append("\n邮件主题:").append(subject);
		}
		info.append("\n邮件内容：").append(content)
				.append("\n收件人：").append(toEmail);
		log.info(info.toString());

		Properties props = new Properties();
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host",MAIL_SMTP_HOST);

		Session session = Session.getInstance(props);
		session.setDebug(false);

		Message msg = new MimeMessage(session);
		try {
			/**
			 * 邮件基本信息
			 */
			msg.setFrom(new InternetAddress(FROM_EMAIL));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			msg.setText(content);

			/**
			 * 设置邮件主题
			 */
			if (StringUtils.isNotBlank(subject)) {
				msg.setSubject(subject);
			}

			/**
			 * 邮件附件
			 */
			if (StringUtils.isNotBlank(appendixUrl)) {
				DataHandler dataHandler = new DataHandler(new FileDataSource(appendixUrl));
				MimeBodyPart mimeBodyPart = new MimeBodyPart();
				mimeBodyPart.setDataHandler(dataHandler);
				mimeBodyPart.setFileName(dataHandler.getName());
				MimeMultipart mimeMultipart = new MimeMultipart();
				mimeMultipart.addBodyPart(mimeBodyPart);
				msg.setContent(mimeMultipart);
			}

			msg.saveChanges();

			Transport transport = session.getTransport();
			transport.connect(FROM_EMAIL,SENDER_AUTHORIZATION_CODE);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();

			log.info("----------------		发送邮件成功		----------------");

			return true;
		} catch (MessagingException e) {
			log.error("----------------		发送邮件失败		----------------\n" + e.toString());
		}

		return false;
	}

	/**
	 * main Method
	 */
	public static void main(String[] args) {
		send("18256054622@163.com", "小阚，你好", "证书发送", "/Users/panzhangbao/Documents/aa.crt");
	}
}

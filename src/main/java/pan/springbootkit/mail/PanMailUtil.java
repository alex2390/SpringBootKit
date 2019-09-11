package pan.springbootkit.mail;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import pan.springbootkit.utils.PanStringUtil;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
	 * @param toEmails 多个收件人邮箱，英文逗号隔开
	 * @param content 邮件内容
	 * @return java.lang.Boolean
	 * @date 2019-09-10 18:08
	 * @author panzhangbao
	 */
	public static Boolean send(String toEmails, String content) {
		return send(toEmails, content, null, null);
	}

	/**
	 * 发送邮件，带主题
	 *
	 * @param toEmails 多个收件人邮箱，英文逗号隔开
	 * @param content 邮件内容
	 * @param subject 主题
	 * @return java.lang.Boolean
	 * @date 2019-09-10 18:08
	 * @author panzhangbao
	 */
	public static Boolean sendBySubject(String toEmails, String content, String subject) {
		return send(toEmails, content, subject, null);
	}

	/**
	 * 发送邮件，带附件
	 *
	 * @param toEmails 多个收件人邮箱，英文逗号隔开
	 * @param content 邮件内容
	 * @param appendixFileList 附件文件列表
	 * @return java.lang.Boolean
	 * @date 2019-09-10 18:08
	 * @author panzhangbao
	 */
	public static Boolean sendByAppendix(String toEmails, String content, List<File> appendixFileList) {
		return send(toEmails, content, null, appendixFileList);
	}

	/**
	 * 发送邮件
	 *
	 * @param toEmails 多个收件人邮箱
	 * @param content	邮件内容
	 * @param subject	主题
	 * @param appendixFileList 附件文件列表
	 *
	 * @return java.lang.Boolean
	 * @date 2019-09-10 17:11
	 * @author panzhangbao
	 */
	public static Boolean send(String toEmails, String content, String subject, List<File> appendixFileList) {
		/**
		 * 参数合法性校验
		 */
		if (StringUtils.isBlank(content) || StringUtils.isBlank(toEmails)) {
			return false;
		}

		StringBuilder info = new StringBuilder("----------------		开始发送邮件		----------------");
		if (StringUtils.isNotBlank(subject)) {
			info.append("\n邮件主题:").append(subject);
		}
		info.append("\n邮件内容：").append(content)
				.append("\n收件人：").append(toEmails);
		log.info(info.toString());

		Properties props = new Properties();
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host",MAIL_SMTP_HOST);

		Session session = Session.getInstance(props);
		session.setDebug(false);

		Message msg = new MimeMessage(session);
		try {
			// 发件人
			msg.setFrom(new InternetAddress(FROM_EMAIL));
			List<String> toEmailsList = PanStringUtil.stringToList(toEmails);

			// 多个收件人
			InternetAddress[] addresses = new InternetAddress[toEmailsList.size()];
			for (int i = 0; i < addresses.length; i++) {
				addresses[i] = new InternetAddress(toEmailsList.get(i));
			}
			msg.setRecipients(Message.RecipientType.TO, addresses);

			// 内容
			Multipart multipart = new MimeMultipart();
			BodyPart contentPart = new MimeBodyPart();
			contentPart.setContent(content, "text/html;charset=utf-8");
			multipart.addBodyPart(contentPart);

			// 主题
			if (StringUtils.isNotBlank(subject)) {
				msg.setSubject(subject);
			}

			// 多个附件
			if(!CollectionUtils.isEmpty(appendixFileList)) {
				for(File appendix : appendixFileList) {
					DataSource source = new FileDataSource(appendix);

					BodyPart attachmentPart = new MimeBodyPart();
					attachmentPart.setDataHandler(new DataHandler(source));

					try {
						// 避免中文乱码的处理
						attachmentPart.setFileName(MimeUtility.encodeWord(appendix.getName()));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					multipart.addBodyPart(attachmentPart);
				}
			}
			msg.setContent(multipart);

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
		List<File> fileList = new ArrayList<>();
		File file = new File("/Users/panzhangbao/Documents/aa.crt");
		fileList.add(file);
		file = new File("/Users/panzhangbao/Documents/a.txt");
		fileList.add(file);
		send("panzhangbao@126.com,982080636@qq.com", new Date().toString(), "证书发送", fileList);
	}
}

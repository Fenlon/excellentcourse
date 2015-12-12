package com.dph.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.imageio.ImageIO;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.ServletContext;

import com.dph.domain.User;

public class SendEmail implements Runnable
{
	private User user;
	private ServletContext context;
	private String host = "smtp.163.com";
	private String email = "zgjpkc@163.com";
	private String username = "zgjpkc@163.com";
	private String password = "zgjpkc01";

	public SendEmail()
	{
		super();
	}

	public SendEmail(User user, ServletContext context)
	{
		super();
		this.user = user;
		this.context = context;
	}

	@Override
	public void run()
	{
		try
		{
			send(user);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void send(User user) throws Exception
	{
		Properties props = new Properties();
		props.setProperty("mail.smtp.host", host);
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.transport.protocol", "smtp");
		Session session = Session.getInstance(props);
		Message message = createMessage(session, user);
		Transport ts = session.getTransport();
		ts.connect(username, password);
		ts.sendMessage(message, message.getAllRecipients());
		ts.close();
	}

	private Message createMessage(Session session, User user2) throws Exception
	{
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(email));
		message.setRecipient(Message.RecipientType.TO,
				new InternetAddress(user.getEmail()));
		message.setSubject("中工精品课程修改密码邮件");

		// 创建MimeBodyPart封装正文
		MimeBodyPart text = new MimeBodyPart();

		Date time = new Date();

		String con = "尊敬的"
				+ user.getNickname()
				+ "您好！</br>下面是重置密码的链接，<font color='red'>一个小时之内请点击进入重置密码，否则失效</font>，我会等着你的哦！"
				+ "<div><a href='http://localhost:8080/ExcellentCourse/servlet/ResetPwdServlet?uid="
				+ user.getId() + "&time=" + time.getTime() + "'>重置密码   uid="
				+ user.getId() + "  time=" + time.getTime() + " </a></div>"
				+ "<img src='cid:logo'/>";
		text.setContent(con, "text/html;charset=UTF-8");

		// chuangjian image
		// 创建图片
		MimeMultipart mm = new MimeMultipart();

		BodyPart image1 = createInlineImagePart(context
				.getRealPath("images/email/beauty.jpg"));
		image1.setHeader("Content-ID", "logo");
		mm.addBodyPart(text);
		mm.addBodyPart(image1);
		mm.setSubType("related");

		message.setContent(mm);
		message.saveChanges();
		// message.writeTo(new FileOutputStream("c:\\1.eml"));
		return message;
	}

	BodyPart createInlineImagePart(String path) throws MessagingException,
			IOException
	{
		MimeBodyPart imagePart = new MimeBodyPart();
		ByteArrayOutputStream baos = new ByteArrayOutputStream(10000);
		BufferedImage img = ImageIO.read(new File(path));
		ImageIO.write(img, "jpg", baos);
		baos.flush();

		DataSource ds = new ByteArrayDataSource(baos.toByteArray(),
				"image/jpeg");
		imagePart.setDataHandler(new DataHandler(ds));
		imagePart.setHeader("Content-Type", "image/jpeg; name=beauty.jpg");
		imagePart.setHeader("Content-Disposition", "inline");
		imagePart.setDisposition(MimeBodyPart.INLINE);
		imagePart.setFileName("beauty.jpg");
		return imagePart;
	}
}

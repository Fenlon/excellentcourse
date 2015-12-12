package com.dph.domain;

import java.io.FileOutputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class ComplexMail
{

	/**
	 * ��������ӵ��ʼ�
	 * 
	 * @throws MessagingException
	 * @throws AddressException
	 */
	public static void main(String[] args) throws Exception
	{

		// �����ʼ�
		Session session = Session.getDefaultInstance(new Properties());
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress("zgjpkc@163.com"));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(
				"dphenixiong@gmail.com"));
		message.setSubject("号码");

		// ����bodypart��װ����
		MimeBodyPart text = new MimeBodyPart();
		text.setContent("丫丫啊 发我i好噶饿<img src='cid:1.jpg'>",
				"text/html;charset=UTF-8");

		// ����bodypart��װͼƬ
		MimeBodyPart image = new MimeBodyPart();
		image.setDataHandler(new DataHandler(new FileDataSource(
				"src/beauty.jpg")));
		image.setContentID("1.jpg");

		// ����bodypart��װ����
		MimeBodyPart attach = new MimeBodyPart();
		DataHandler dh = new DataHandler(new FileDataSource("src/夜空中最亮的星.mp3"));
		attach.setDataHandler(dh);
		attach.setFileName(MimeUtility.encodeText(dh.getName())); // content-disposition

		// ������ݹ�ϵ
		MimeMultipart content = new MimeMultipart();
		content.addBodyPart(text);
		content.addBodyPart(image);
		content.setSubType("related");
		MimeBodyPart mbp = new MimeBodyPart();
		mbp.setContent(content);

		MimeMultipart mm = new MimeMultipart();
		mm.addBodyPart(mbp);
		mm.addBodyPart(attach);
		mm.setSubType("mixed");

		message.setContent(mm);
		message.saveChanges();

		message.writeTo(new FileOutputStream("c:\\1.eml"));

	}

}

package com.pack.java.mail;

import javax.mail.*;
import javax.mail.internet.*;

import com.sun.mail.smtp.SMTPTransport;

import java.util.*;
import java.io.*;

public class MailBroadcastTest {
	public static void main(String[] args)
	{
		EmailClient client = new EmailClient();
		client.sendEmail();
	}
}

class EmailClient {
	final String emailInfo = "res\\EmailInfo.properties";
	Properties properties = new Properties();

	public void sendEmail()
	{
		try
		{
			// This is required to load all the properties
			FileInputStream fileInputStream = new FileInputStream(emailInfo);
			properties.load(fileInputStream);
			fileInputStream.close();
		}
		catch (IOException ioe)
		{
			// throw IOException of your choice.
			// can end here
		}
		System.out.println("Email properties read successfully.");

		String smtpHost = properties.getProperty("smtpHost");
		String smtpPort = properties.getProperty("smtpPort");
		String fromAddress = properties.getProperty("fromAddress");
		String toAddress = properties.getProperty("toAddress");
		String emailSubject = properties.getProperty("emailSubject");
		String emailBody = properties.getProperty("emailBody");
		
		String username = properties.getProperty("username");
		String password = properties.getProperty("password");

		Properties props = new Properties();
		props.put("mail.smtp.host", smtpHost);
		props.put("mail.smtp.port", smtpPort);
		// props.put("mail.from", fromAddress);
		Session session = Session.getInstance(props, null);

		/*try
		{
			Message mimeMessage = new MimeMessage(session);
			mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress, false));
			mimeMessage.setSentDate(new Date());
			mimeMessage.setSubject(emailSubject);
			mimeMessage.setText(emailBody);
			System.out.println("Sending e-mail...");
			SMTPTransport t = (SMTPTransport)session.getTransport("smtps");
			t.connect(smtpHost, Integer.parseInt(smtpPort), username, password);
			t.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
			System.out.println("e-mail sent.");
		}
		catch (MessagingException me)
		{
			me.printStackTrace();
			System.out.println("e-mail send failed.");
			me.getMessage();
			
		}*/
	}
}

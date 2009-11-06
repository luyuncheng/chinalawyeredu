package com.changpeng.common.util;

/**
 * MailHandler �������ⲿ���ʼ����򻯿�����Աʹ��EMail�Ŀ���������������Ҫ����������sendMail()��
 * ����ľ�̬����server��isDebug��session��ServletBase���ʼ�������������ɵ����߸����������߱���
 * �����ı���ֵ�У�from,recipient,subject fields��Ȼ�󷽿ɵ���sendMail()��
 * @author: Administrator
 */
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.FetchProfile;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * MailHandler �������ⲿ���ʼ����򻯿�����Աʹ��EMail�Ŀ���������������Ҫ����������sendMail()��
 * ����ľ�̬����server��isDebug��session��ServletBase���ʼ�������������ɵ����߸����������߱���
 * �����ı���ֵ�У�from,recipient,subject fields��Ȼ�󷽿ɵ���sendMail()��
 *
 * @author: Administrator
 */
public class MailHandler {
	/** Mail �е�from�ֶ�*/
	private java.lang.String from;
	/** Mail �е�replyTo�ֶ�*/
	private java.lang.String replyTo;
	/** Mail �е�sentDate�ֶ�*/
	private java.lang.String sentDate;
	/** Mail �е�subject�ֶ�*/
	private java.lang.String subject;
	/** Mail �е�recipient�ֶ�*/
	private String[] recipient;
	/** Mail server��ַ*/
	static java.lang.String server;
	/** �Ƿ��¼��ϸ������Ϣ*/
	static protected boolean isDebug = false;
	/** ����JavaMail�е�session*/
	static private Session session;

	private String username="13602680150@139.com";
	private String password="860210";
	/**
	 * MailHandler ���캯����
	 */
	public MailHandler() {
		super();

		if (session == null) {
			Authenticator auth = new PopupAuthenticator(username, password);
			Properties props = System.getProperties();
			props.put("mail.smtp.host", server);
			props.put("username", props);

			props.put("password", password);

			session = Session.getDefaultInstance(props, auth);
			session.setDebug(true);
		};

	}
	/**
	 * ���Mail Server�ĵ�ַ��
	 * @return java.lang.String
	 */
	public static java.lang.String getServer() {
		return server;
	}
	/**
	 * һ�����ڲ��Ե�С���ӣ������հ���Ӧɾ����
	 * @param args java.lang.String[]
	 */
	public static void main(String[] args) {
		String[] recipient = {new String("13602680150@139.com")};
		MailHandler.setServer("smtp.139.com");
		

		MailHandler handler = new MailHandler();
		
		handler.setFrom("13602680150@139.com");
		handler.setRecipient(recipient);
		handler.setSubject("hello java mail!");
		try {
			handler.sendMail("hello\nevery mail user\n");
		}
		catch (Exception e) {
			System.out.println(e);
			System.out.println("error in send");
		}

		/*
			Message[] msg=null;
			try{
			 	msg = handler.getMessage("sumintp",-1,"imap","sumin","password");
			}catch(Exception e){
				System.out.println(e.getMessage());
			}catch(AuthenticationFailedException e){
				System.out.println("user password error");
			}
			try{
				if (msg == null){
					System.out.println("empty box");
				}
				System.out.println(msg[0].getSubject());
				System.out.println(msg[1].getSentDate().toString());
			}catch (MessagingException e) {
		
			}
		*/

	}
	/**
	 * �����ʼ����������±���ֵ�Ѿ���ֵ��from,recipient,subject ��Ȼ�󷽿ɵ���
	 * @return boolean
	 * @param content java.lang.String �ż�����
	 */
	public boolean sendMail(String content) throws Exception {
		try {
			// create a message
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			InternetAddress[] address = new InternetAddress[recipient.length];
			for (int i = 0; i < recipient.length; i++) {
				address[i] = new InternetAddress(recipient[i]);
				//                 System.out.println(address[i]);
			}
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject(subject, "gb2312");

			// create and fill the first message part
			MimeBodyPart mbp1 = new MimeBodyPart();
			mbp1.setText(content, "gb2312");

			// create the Multipart and its parts to it
			Multipart mp = new MimeMultipart();
			mp.addBodyPart(mbp1);

			// add the Multipart to the message
			msg.setContent(mp);

			// set the Date: header
			msg.setSentDate(new Date());

			// send the message
			Transport.send(msg);

		}
		catch (MessagingException mex) {
			mex.printStackTrace();
			Exception ex = null;
			if ((ex = mex.getNextException()) != null) {
				ex.printStackTrace();
			}
			throw (new Exception("mail send failed:" + mex.getMessage()));
		}

		return true;
	}
	/**
	 * �����ʼ����������±���ֵ�Ѿ���ֵ��from,recipient,subject ��Ȼ�󷽿ɵ��á�
	 * @return boolean
	 * @param content java.lang.String �ż�����
	 * @param fileNames java.lang.String[] �����ļ�ȫ������
	 * @exception com.gmcc.portal.Common.Exception The exception description.
	 */
	public boolean sendMail(String content, String fileName) throws Exception {

		// create some properties and get the default Session

		try {
			// create a message
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			InternetAddress[] address = new InternetAddress[recipient.length];
			for (int i = 0; i < recipient.length; i++) {
				address[i] = new InternetAddress(recipient[i]);
			}
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject(subject, "gb2312");

			// create and fill the first message part
			MimeBodyPart mbp1 = new MimeBodyPart();
			mbp1.setText(content, "gb2312");

			// create the second message part
			MimeBodyPart mbp2 = new MimeBodyPart();

			// attach the file to the message
			FileDataSource fds = new FileDataSource(fileName);
			mbp2.setDataHandler(new DataHandler(fds));
			mbp2.setFileName(fds.getName());

			// create the Multipart and its parts to it
			Multipart mp = new MimeMultipart();
			mp.addBodyPart(mbp1);
			mp.addBodyPart(mbp2);

			// add the Multipart to the message
			msg.setContent(mp);

			// set the Date: header
			msg.setSentDate(new Date());

			// send the message
			Transport.send(msg);

		}
		catch (MessagingException mex) {
			mex.printStackTrace();
			Exception ex = null;
			if ((ex = mex.getNextException()) != null) {
				ex.printStackTrace();
			}
			throw (new Exception("mail send failed:" + mex.getMessage()));
		}

		return true;
	}
	/**
	 * ��fromֵ��
	 * @param newFrom java.lang.String
	 */
	public void setFrom(java.lang.String newFrom) {
		from = newFrom;
	}
	/**
	 * ��recipientֵ��
	 * @param newRecipient java.lang.String
	 */
	public void setRecipient(String[] newRecipient) {
		recipient = newRecipient;
	}
	/**
	 * ��replyToֵ��
	 * @param newReplyTo java.lang.String
	 */
	public void setReplyTo(java.lang.String newReplyTo) {
		replyTo = newReplyTo;
	}
	/**
	 * ��sendDateֵ��
	 * @param newSentDate java.lang.String
	 */
	public void setSentDate(java.lang.String newSentDate) {
		sentDate = newSentDate;
	}
	/**
	 * ��serverֵ��
	 * @param newServer java.lang.String
	 */
	public static void setServer(java.lang.String newServer) {
		server = newServer;
	}
	/**
	 * ��subjectֵ��
	 * @param newSubject java.lang.String
	 */
	public void setSubject(java.lang.String newSubject) {
		subject = newSubject;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (22/12/2000 PM 4:20:43)
	 * @param server java.lang.String
	 * @param protocol java.lang.String
	 * @param user java.lang.String
	 * @param password java.lang.String
	 */
	public Message[] getMessage(String server, int port, String protocol, String user, String password) throws Exception, AuthenticationFailedException {
		String mbox = "INBOX";
		Message[] msgs = null;

		// Get a Store object
		Store store = null;
		if (server == null || protocol == null || user == null || password == null)
			throw new Exception("Mail Error in parameter");

		if (port == -1) {
			if (protocol.equalsIgnoreCase("pop3"))
				port = 110;
			else if (protocol.equalsIgnoreCase("imap"))
				port = 143;
			else
				throw new Exception("Mail Error in protocol support");
		}

		try {
			store = session.getStore(protocol);

			// Connect
			store.connect(server, port, user, password);

			// Open the Folder
			Folder folder = store.getDefaultFolder();
			folder = folder.getFolder(mbox);

			// try to open read/write and if that fails try read-only
			try {
				folder.open(Folder.READ_WRITE);
			}
			catch (MessagingException ex) {
				folder.open(Folder.READ_ONLY);
			}
			int totalMessages = folder.getMessageCount();

			if (totalMessages == 0) {

				folder.close(false);
				store.close();
				return null;
			}

			msgs = folder.getMessages();

			// Use a suitable FetchProfile
			FetchProfile fp = new FetchProfile();
			fp.add(FetchProfile.Item.ENVELOPE);
			fp.add(FetchProfile.Item.FLAGS);
			fp.add("X-Mailer");
			folder.fetch(msgs, fp);

			folder.close(false);
			store.close();

		}
		catch (AuthenticationFailedException e) {
			throw e;
		}
		catch (MessagingException ex) {
			System.out.println("Mail module got exception: " + ex.getMessage());
		}
		catch (Exception e) {
			System.out.println("Mail module got exception: " + e.getMessage());
		}
		return msgs;
	}
}
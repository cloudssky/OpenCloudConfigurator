package org.opencmp.occ.server;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.opencmp.occ.client.cloudservice.mailsend.MailService;
import org.opencmp.occ.client.cloudservice.registration.action.CloudUserData;
import org.opencmp.occ.shared.MailObject;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;


/**
 * Eine serverseitige Klasse, die E-Mail ueber SSL an angegebenen Adressen(Support, NewUser) versendet.
 * A server-side class, the e-mail addresses listed at over SSL (Support, NewUser) * sent.
 * @author bagautdinov
 * @version 1.0
 */
@SuppressWarnings("serial")
public class SendMailSSLImpl extends RemoteServiceServlet implements MailService {

	final static String fromAddress = "noreply@cloudssky.com";// sender
	final static String login = "noreply@cloudssky.com";
	final static String pwd = "secret";
	final static String toAddressSupport = "servicebot@cloudssky.com";// empfaenger Support
//	final static String ccAddressSupport = "second@xyz.com";// empfaenger Support
																
	final static String port = "465";
	final static String subjectSupport = "Neuer Benutzer wurde registriert";// betreff
	static String subjectNewUser = "Erforlgreiche Registrierung";

	private MailObject mailObjectSupport;
	private MailObject mailObjectNewUser;

	/**
	 * Diese Methode versendet eine E-Mail an Support mit angegebenem
	 * Benutzerdaten
	 */
	public String sendeEMailSSLonSupport(CloudUserData cloudUserData) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", port);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", port);

		setEmailDataSupport();
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(mailObjectSupport.getFromAddress(), mailObjectSupport.getPwd());
			}
		});

		try {
			// Eine neue Message fuer Support erzeugen
			Message message = new MimeMessage(session);
			// Hier werden die Absender- und Empfaengeradressen gesetzt
			message.setFrom(new InternetAddress(mailObjectSupport.getFromAddress()));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailObjectSupport.getToAddress()));
			// Eine CC E-Mail-Adresse setzen
//			message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(mailObjectSupport.getCcAddress()));
			// Der Betreff(subject)
			message.setSubject(mailObjectSupport.getSubject());

			// Hier lassen sich HEADER-Informationen hinzufuegen
			// message.setHeader("Test", "Test");
			message.setSentDate(new Date());
			// Body der Message werden gesetzt
			message.setText(mailObjectSupport.getContent() + "\n" + cloudUserData.toStringSupport() + "\nEnde der Nachricht");
//			System.out.println("cloudUserData in SendMailSSLImpl: " + cloudUserData.toString());
			// wird die Mail verschickt
			Transport.send(message);

			// System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		/* Ab hier wird eine E-Mail an neuem registrierten Benutzer  versendet.*/
		setEmailDataNewUser();
		try {
			// Eine neue Message fuer NewUser erzeugen
			Message message = new MimeMessage(session);
			// Hier werden die Absender- und Empfaengeradressen gesetzt
			message.setFrom(new InternetAddress(mailObjectNewUser.getFromAddress()));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(cloudUserData.getEmail()));// empfaenger setzen
			// Der Betreff(subject)
			message.setSubject(mailObjectNewUser.getSubject());
			// Hier lassen sich HEADER-Informationen hinzufuegen
			// message.setHeader("Test", "Test");
			message.setSentDate(new Date());
			// Body der Message werden gesetzt
			message.setText(mailObjectNewUser.getContent() + cloudUserData.getName() + ",\n" + 
						"\nThank you for registering with us.\n" +
						"Your Cloud Server is being configured with the following resources:\n\n" +
						cloudUserData.getCloudSizerData().toStringNewUserEn() +
						"\n\nYou will get free access for 30 days to your Cloud Server or PaaS within 24 hours.\n\n" +
						"Thanks,\n\nBest Regards,\nYour Clouds Sky Team.");
//			System.out.println("cloudUserData in SendMailSSLImpl: " + cloudUserData.toString());
			// wird die Mail verschickt
			Transport.send(message);

			// System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		return "true";
	}
	
//	void createSession(String id){
//		getThreadLocalRequest().getSession().setAttribute("id", id);
//	}
//	
//	boolean validateSession(String id){
//		if(getThreadLocalRequest().getSession().getAttribute("id") != null) {
//			return true;
//		}
//		return false;
//	}

	/**
	 * Es werden vordefinierte Daten fuer E-Mailversendung an Support gesetzt.
	 */
	private void setEmailDataSupport() {
		mailObjectSupport = new MailObject();
		mailObjectSupport.setToAddress(toAddressSupport);
//		mailObjectSupport.setCcAddress(ccAddressSupport);
		mailObjectSupport.setFromAddress(fromAddress);
		mailObjectSupport.setPort(port);
		mailObjectSupport.setPwd(pwd);
		mailObjectSupport.setSubject(subjectSupport);
		// mailObjectSupport.setContent(content);
		mailObjectSupport.setContent("Neuer Benutzer wurde registriert mit folgenden Daten: ");
	}

	/**
	 * Es werden vordefinierte Daten fuer E-Mailversendung an NewUser gesetzt.
	 */
	private void setEmailDataNewUser() {
		mailObjectNewUser = new MailObject();
		mailObjectNewUser.setToAddress(null);
		mailObjectNewUser.setFromAddress(fromAddress);
		mailObjectNewUser.setPort(port);
		mailObjectNewUser.setPwd(pwd);
		mailObjectNewUser.setSubject(subjectNewUser);
		mailObjectNewUser.setContent("Hallo ");
	}

}
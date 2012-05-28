package org.opencms.occ.shared;

import java.io.Serializable;

/**
 * Ein einfaches Objekt fuer E-Mail-Versenden.
 * A simple object for sending e-mail.
 * @author bagautdinov
 * @version 1.0
 */
public class MailObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private String toAddress;//empfaenger
	private String ccAddress;//empfaenger 2
	private String fromAddress;//sender
	private String subject;//betreff
	private String content;//inhalt
	private String port;//default fuer SSL bei gmail 465
	private String pwd;//passwort

	public MailObject() {
	}
	
	public MailObject(String toAddress, String ccAddress, String fromAddress, String subject, String content, String port, String pwd) {
		super();
		this.toAddress = toAddress;
		this.ccAddress = ccAddress;
		this.fromAddress = fromAddress;
		this.subject = subject;
		this.content = content;
		this.port = port;
		this.pwd = pwd;
	}



	/**
	 * @return the toAddress
	 */
	public String getToAddress() {
		return toAddress;
	}
	
	/**
	 * @return the ccAddress
	 */
	public String getCcAddress() {
		return ccAddress;
	}

	/**
	 * @return the fromAddress
	 */
	public String getFromAddress() {
		return fromAddress;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param toAddress
	 *            the toAddress to set
	 */
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}
	
	/**
	 * @param ccAddress
	 *            the ccAddress to set
	 */
	public void setCcAddress(String ccAddress) {
		this.ccAddress = ccAddress;
	}

	/**
	 * @param fromAddress
	 *            the fromAddress to set
	 */
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	/**
	 * @param subject
	 *            the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the port
	 */
	public String getPort() {
		return port;
	}

	/**
	 * @return the pwd
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(String port) {
		this.port = port;
	}

	/**
	 * @param pwd the pwd to set
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MailObject [toAddress=" + toAddress + ", ccAddress=" + ccAddress + ", fromAddress=" + fromAddress + ", subject=" + subject + ", content=" + content + ", port=" + port + ", pwd= wird nicht ausgegeben"  + "]";
	}
	
	
}

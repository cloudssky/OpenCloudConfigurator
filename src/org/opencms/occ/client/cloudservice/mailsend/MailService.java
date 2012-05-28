package org.opencms.occ.client.cloudservice.mailsend;

import org.opencms.occ.client.cloudservice.registration.action.CloudUserData;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Einfacher clientseitiger RPC-Service fuer E-Mail-Versenden.
 * Simple client-side RPC service for sending e-mail.
 * @author Bagautdinov
 * @version 1.0
 */
@RemoteServiceRelativePath("mail")
public interface MailService extends RemoteService {
	String sendeEMailSSLonSupport(CloudUserData cloudUserData);
}
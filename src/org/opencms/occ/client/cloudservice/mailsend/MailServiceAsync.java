package org.opencms.occ.client.cloudservice.mailsend;

import org.opencms.occ.client.cloudservice.registration.action.CloudUserData;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Asynchroner clientseitiger RPC-Service fuer E-Mail-Versenden.
 * Asynchronous client-side RPC service for sending e-mail.
 * @author Bagautdinov
 * @version 1.0
 */

public interface MailServiceAsync {
	void sendeEMailSSLonSupport(CloudUserData cloudUserData, AsyncCallback<String> asyncCallback);
}
package org.opencms.occ.server.guice;

import org.opencms.occ.client.cloudservice.registration.action.CloudUserData;
import org.opencms.occ.server.CloudUserDataActionHandler;

import com.gwtplatform.dispatch.server.guice.HandlerModule;


public class ServerModule extends HandlerModule {

	@Override
	protected void configureHandlers() {

		bindHandler(CloudUserData.class, CloudUserDataActionHandler.class);
//		bindHandler(MailObject.class, SendMailSSLImpl.class); 
	}
}

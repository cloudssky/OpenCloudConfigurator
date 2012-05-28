package org.opencms.occ.server.guice;

import com.google.inject.servlet.ServletModule;
import com.gwtplatform.dispatch.server.guice.DispatchServiceImpl;
import com.gwtplatform.dispatch.server.guice.HttpSessionSecurityCookieFilter;
import com.gwtplatform.dispatch.shared.ActionImpl;
import com.gwtplatform.dispatch.shared.SecurityCookie;

public class DispatchServletModule extends ServletModule {

	@Override
	public void configureServlets() {
		serve("/" + ActionImpl.DEFAULT_SERVICE_NAME).with(DispatchServiceImpl.class);
		
		//verhinderung von XSRF Atacken
		bindConstant().annotatedWith(SecurityCookie.class).to("MYCOOKIE");
		filter("*.html").through(HttpSessionSecurityCookieFilter.class);
		
//		bind(DB_Conn.class).in((Class<? extends Annotation>) CloudUserDataActionHandler.class);
//		serve("/Popup2").with(DB_Conn.class);
	}
}

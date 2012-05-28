package org.opencms.occ.server.guice;

import org.opencms.occ.server.guice.DispatchServletModule;
import org.opencms.occ.server.guice.ServerModule;

import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.Injector;
import com.google.inject.Guice;

public class GuiceServletConfig extends GuiceServletContextListener {

	@Override
	protected Injector getInjector() {
		return Guice.createInjector(new ServerModule(), new DispatchServletModule());
	}
}

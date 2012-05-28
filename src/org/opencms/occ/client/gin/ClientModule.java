package org.opencms.occ.client.gin;

import org.opencms.occ.client.cloudservice.CloudOldPresenter;
import org.opencms.occ.client.cloudservice.CloudOldView;
import org.opencms.occ.client.cloudservice.CloudPresenter;
import org.opencms.occ.client.cloudservice.CloudView;
import org.opencms.occ.client.cloudservice.registration.RegistrationPresenter;
import org.opencms.occ.client.cloudservice.registration.RegistrationView;
import org.opencms.occ.client.cloudservice.registration.popup.Popup2Presenter;
import org.opencms.occ.client.cloudservice.registration.popup.Popup2View;
import org.opencms.occ.client.cloudservice.registration.popup.PopupPresenter;
import org.opencms.occ.client.cloudservice.registration.popup.PopupView;
import org.opencms.occ.client.main.MainPagePresenter;
import org.opencms.occ.client.main.MainPageView;
import org.opencms.occ.client.place.ClientPlaceManager;
import org.opencms.occ.client.place.CloudOldPlace;
import org.opencms.occ.client.place.CloudPlace;
import org.opencms.occ.client.place.DefaultPlace;
import org.opencms.occ.client.place.NameTokens;
import org.opencms.occ.client.place.PopupPlace;
import org.opencms.occ.client.place.RegistrationPlace;

import com.gwtplatform.dispatch.shared.SecurityCookie;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;


public class ClientModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		install(new DefaultModule(ClientPlaceManager.class));

		bindPresenter(MainPagePresenter.class, MainPagePresenter.MyView.class, MainPageView.class, MainPagePresenter.MyProxy.class);
		
		//verhinderung von XSRF Atacken s. DispatchServletModule.java
		bindConstant().annotatedWith(SecurityCookie.class).to("MYCOOKIE");

		bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.main);

		bindPresenter(CloudPresenter.class, CloudPresenter.MyView.class, CloudView.class, CloudPresenter.MyProxy.class);

		bindConstant().annotatedWith(CloudPlace.class).to(NameTokens.cloud);

		bindPresenter(RegistrationPresenter.class, RegistrationPresenter.MyView.class, RegistrationView.class, RegistrationPresenter.MyProxy.class);

		bindConstant().annotatedWith(RegistrationPlace.class).to(NameTokens.registration);

		bindPresenter(PopupPresenter.class, PopupPresenter.MyView.class, PopupView.class, PopupPresenter.MyProxy.class);

		bindConstant().annotatedWith(PopupPlace.class).to(NameTokens.popup);

		bindPresenter(CloudOldPresenter.class, CloudOldPresenter.MyView.class, CloudOldView.class, CloudOldPresenter.MyProxy.class);

		bindConstant().annotatedWith(CloudOldPlace.class).to(NameTokens.cloudold);

		bindPresenterWidget(Popup2Presenter.class, Popup2Presenter.MyView.class, Popup2View.class);
	}
}

package org.opencmp.occ.client.gin;

import org.opencmp.occ.client.cloudservice.CloudOldPresenter;
import org.opencmp.occ.client.cloudservice.CloudOldView;
import org.opencmp.occ.client.cloudservice.CloudPresenter;
import org.opencmp.occ.client.cloudservice.CloudView;
import org.opencmp.occ.client.cloudservice.registration.RegistrationPresenter;
import org.opencmp.occ.client.cloudservice.registration.RegistrationView;
import org.opencmp.occ.client.cloudservice.registration.popup.Popup2Presenter;
import org.opencmp.occ.client.cloudservice.registration.popup.Popup2View;
import org.opencmp.occ.client.cloudservice.registration.popup.PopupPresenter;
import org.opencmp.occ.client.cloudservice.registration.popup.PopupView;
import org.opencmp.occ.client.main.MainPagePresenter;
import org.opencmp.occ.client.main.MainPageView;
import org.opencmp.occ.client.place.ClientPlaceManager;
import org.opencmp.occ.client.place.CloudOldPlace;
import org.opencmp.occ.client.place.CloudPlace;
import org.opencmp.occ.client.place.DefaultPlace;
import org.opencmp.occ.client.place.NameTokens;
import org.opencmp.occ.client.place.PopupPlace;
import org.opencmp.occ.client.place.RegistrationPlace;

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

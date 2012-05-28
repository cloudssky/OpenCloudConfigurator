package org.opencmp.occ.client.gin;

import org.opencmp.occ.client.cloudservice.CloudOldPresenter;
import org.opencmp.occ.client.cloudservice.CloudPresenter;
import org.opencmp.occ.client.cloudservice.registration.RegistrationPresenter;
import org.opencmp.occ.client.cloudservice.registration.popup.PopupPresenter;
import org.opencmp.occ.client.gin.ClientModule;
import org.opencmp.occ.client.main.MainPagePresenter;

import com.google.gwt.inject.client.GinModules;
import com.gwtplatform.dispatch.client.gin.DispatchAsyncModule;

import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.google.gwt.inject.client.AsyncProvider;


@GinModules({ DispatchAsyncModule.class, ClientModule.class })
public interface ClientGinjector extends Ginjector {

	EventBus getEventBus();

	PlaceManager getPlaceManager();

	AsyncProvider<MainPagePresenter> getMainPagePresenter();

	AsyncProvider<CloudPresenter> getCloudPresenter();

	AsyncProvider<RegistrationPresenter> getRegistrationPresenter();

	AsyncProvider<PopupPresenter> getPopupPresenter();

	AsyncProvider<CloudOldPresenter> getCloudOldPresenter();
}

package org.opencms.occ.client.main;

import org.opencms.occ.client.place.NameTokens;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

/**
 * Diese Klasse implementiert notwendiger Ablauf fuer Startseite. 
 * @author Bagautdinov
 * @version 1.0
 */
public class MainPagePresenter extends Presenter<MainPagePresenter.MyView, MainPagePresenter.MyProxy> {

	public interface MyView extends View {
		public Label getTextLabel();

		public Button getWeiterButton();
	}

	@Inject
	DispatchAsync dispatchAsync;
	@Inject
	PlaceManager placeManager;

	@ProxyCodeSplit
	@NameToken(NameTokens.main)
	public interface MyProxy extends ProxyPlace<MainPagePresenter> {
	}

	@Inject
	public MainPagePresenter(final EventBus eventBus, final MyView view, final MyProxy proxy) {
		super(eventBus, view, proxy);
	}

	@Override
	protected void revealInParent() {
		RevealRootContentEvent.fire(this, this);
	}

	@Override
	protected void onBind() {
		super.onBind();
	}

	@Override
	protected void onReset() {
		super.onReset();
		/*uebergang auf cloudseite*/
		getView().getWeiterButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
//				PlaceRequest request = new PlaceRequest(NameTokens.cloud);
				PlaceRequest request = new PlaceRequest(NameTokens.cloudold);
				placeManager.revealPlace(request);
			}

		});
	}
}

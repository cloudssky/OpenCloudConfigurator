package org.opencms.occ.client.cloudservice.registration.popup;

import org.opencms.occ.client.place.NameTokens;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.Button;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.PopupView;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;

/**
 * Diese Klasse implementiert notwendiger Ablauf fuer Popup-Fenster.
 * This class implements the necessary sequence for pop-up window.
 * @author Bagautdinov
 * @version 1.0
 */
public class Popup2Presenter extends PresenterWidget<Popup2Presenter.MyView> {

	public interface MyView extends PopupView {
		public Button getOkButten();
		}

	@Inject PlaceManager placeManager;
	
	@Inject
	public Popup2Presenter(final EventBus eventBus, final MyView view) {
		super(eventBus, view);
	}

	@Override
	protected void onBind() {
		super.onBind();
		getView().getOkButten().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				PlaceRequest request = new PlaceRequest(NameTokens.main);
				placeManager.revealPlace(request);
			}
		});
	}
	
}

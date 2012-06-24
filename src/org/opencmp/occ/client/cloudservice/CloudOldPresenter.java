package org.opencmp.occ.client.cloudservice;

import org.opencmp.occ.client.place.NameTokens;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.Button;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

/**
 * Diese Klasse implementiert notwendiger Ablauf fuer CloudOldView. Live werden entsprechende Werte duerch den Schieber geaendert und angezeigt.
 * This class implements the necessary sequence for CloudOldView. Live corresponding values are changed by the slider and displayed.
 * @author Bagautdinov
 * @version 1.0
 *
 */
public class CloudOldPresenter extends Presenter<CloudOldPresenter.MyView, CloudOldPresenter.MyProxy> {

	public interface MyView extends View {
		public CloudSizerData getCloudSizerData();
		public Button getRegistrationButton();
	}

//	@Inject
//	DispatchAsync dispatchAsync;
	@Inject
	PlaceManager placeManager;

	@ProxyCodeSplit
	@NameToken(NameTokens.cloudold)
	public interface MyProxy extends ProxyPlace<CloudOldPresenter> {
	}

	@Inject
	public CloudOldPresenter(final EventBus eventBus, final MyView view, final MyProxy proxy) {
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
			getView().getRegistrationButton().addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					// PlaceRequest request = new
					// PlaceRequest(NameTokens.registration).with("name",
					// getView().getCpuBox().getValue());
					// PlaceRequest request = new
					// PlaceRequest(NameTokens.registration).with("tempData",
					// getView().getCloudSizerData().formatedToString());

					// Es werden ausgewaehlte CloudSizerData auf die naechste
					// Seite als Parameter uebergeben
					PlaceRequest request = new PlaceRequest(NameTokens.registration).with("cpu", getView().getCloudSizerData().getCpuSize()).with("ram", getView().getCloudSizerData().getRamSize()).with("hdd", getView().getCloudSizerData().getHddSize()).with("price", getView().getCloudSizerData().getPriceSize()).with("location", getView().getCloudSizerData().getCloudLocation()).with("cloudApps", getView().getCloudSizerData().getCloudApps());
					placeManager.revealPlace(request);
				}
			});
	}

}

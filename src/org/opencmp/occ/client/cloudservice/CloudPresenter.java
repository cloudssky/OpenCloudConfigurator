package org.opencmp.occ.client.cloudservice;

import org.opencmp.occ.client.place.NameTokens;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;


public class CloudPresenter extends Presenter<CloudPresenter.MyView, CloudPresenter.MyProxy> {

	public interface MyView extends View {
//		public SliderBar getSliderBar();
//		public void setSliderBar(SliderBar sliderBar);
		public TextBox getCloudValue();
		public void setCloudValue(TextBox cloudValue);
		public TextBox getRamValue();
		public void setRamValue(TextBox ramValue);
		public TextBox getPriceValue();
		public void setPriceValue(TextBox priceValue);
		public Label getErrorLabel();
		public void setErrorLabel(Label errorLabel);
		public TextBox getHddValue();
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.cloud)
	public interface MyProxy extends ProxyPlace<CloudPresenter> {
	}

	@Inject
	public CloudPresenter(final EventBus eventBus, final MyView view, final MyProxy proxy) {
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
		
//		getView().getSliderBar().setMinValue(1);
//		getView().getSliderBar().setMaxValue(20);
//		getView().getSliderBar().setCurrentValue(1);
//		getView().getSliderBar().setStepSize(1);
//		getView().getSliderBar().setNumLabels(10);
//		getView().getSliderBar().setNumTicks(20);
	}
}

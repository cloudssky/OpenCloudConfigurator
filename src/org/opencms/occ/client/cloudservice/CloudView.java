package org.opencms.occ.client.cloudservice;

import org.opencms.occ.client.cloudservice.slider.SliderBar;
import org.opencms.occ.client.cloudservice.slider.SliderBar.LabelFormatter;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;


public class CloudView extends ViewImpl implements CloudPresenter.MyView {

	private final Widget widget;
	/* Default values for sliderbar(min, max, step) and other(ram, price) values */
	static final int MIN_VALUE = 1;
	static final int MAX_VALUE = 20;
	static final int STEP_VALUE = 1;
	static final int RAM_VALUE = 512;
	static final double PRICE_VALUE = 14.99;
	
//	@UiField SliderBar sliderBar;
	@UiField Label textLabel;
	@UiField TextBox cloudValue;
	@UiField TextBox ramValue;
	@UiField TextBox hddValue;
	@UiField TextBox priceValue;
	@UiField Label errorLabel;

	public interface Binder extends UiBinder<Widget, CloudView> {
	}

	/**
	 * The main slider bar at the top of the page.
	 */
	@SuppressWarnings("unused")
	private SliderBar mainSliderBar = new SliderBar(MIN_VALUE, MAX_VALUE, new LabelFormatter() {
		public String formatLabel(SliderBar slider, double value) {
			return (int) (10 * value) / 10.0 + "";

		}
	});

	@Inject
	public CloudView(final Binder binder) {
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}
	
	/**
	 * @return the cloudValue
	 */
	public TextBox getCloudValue() {
		return cloudValue;
	}

	/**
	 * @param cloudValue the cloudValue to set
	 */
	public void setCloudValue(TextBox cloudValue) {
		this.cloudValue = cloudValue;
	}

	/**
	 * @return the ramValue
	 */
	public TextBox getRamValue() {
		return ramValue;
	}

	/**
	 * @param ramValue the ramValue to set
	 */
	public void setRamValue(TextBox ramValue) {
		this.ramValue = ramValue;
	}

	/**
	 * @return the priceValue
	 */
	public TextBox getPriceValue() {
		return priceValue;
	}

	/**
	 * @param priceValue the priceValue to set
	 */
	public void setPriceValue(TextBox priceValue) {
		this.priceValue = priceValue;
	}

	/**
	 * @return the errorLabel
	 */
	public Label getErrorLabel() {
		return errorLabel;
	}

	/**
	 * @param errorLabel the errorLabel to set
	 */
	public void setErrorLabel(Label errorLabel) {
		this.errorLabel = errorLabel;
	}

	/**
	 * @return the hddValue
	 */
	public TextBox getHddValue() {
		return hddValue;
	}

}

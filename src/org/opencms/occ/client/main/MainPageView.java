package org.opencms.occ.client.main;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
/**
 * Diese Klasse stellt eine einfache Startseite dar.
 * @author Bagautdinov
 * @version 1.0
 */
public class MainPageView extends ViewImpl implements MainPagePresenter.MyView {

	private final Widget widget;

	@UiField Label textLabel;
	@UiField Button weiterButton;
	
	public interface Binder extends UiBinder<Widget, MainPageView> {
	}

	@Inject
	public MainPageView(final Binder binder) {
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}
	
	/**
	 * @return the textLabel
	 */
	public Label getTextLabel() {
		return textLabel;
	}

	/**
	 * @return the weiterButton
	 */
	public Button getWeiterButton() {
		return weiterButton;
	}
}

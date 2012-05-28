package org.opencmp.occ.client.cloudservice.registration.popup;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.PopupViewImpl;
/**
 * Diese Klasse stellt ein einfaches Popup-Fenster dar.
 * This class provides a simple pop-up window.
 * @author Bagautdinov
 * @version 1.0
 */
public class Popup2View extends PopupViewImpl implements Popup2Presenter.MyView {

	private final Widget widget;
	
	@UiField Button okButten;
	
	
	public interface Binder extends UiBinder<Widget, Popup2View> {
	}

	@Inject
	public Popup2View(final EventBus eventBus, final Binder binder) {
		super(eventBus);
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}
	/**
	 * @return the okButten
	 */
	public Button getOkButten() {
		return okButten;
	}
}

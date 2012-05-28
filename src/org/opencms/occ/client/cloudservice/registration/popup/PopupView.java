package org.opencms.occ.client.cloudservice.registration.popup;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class PopupView extends ViewImpl implements PopupPresenter.MyView {

	private final Widget widget;

	public interface Binder extends UiBinder<Widget, PopupView> {
	}

	@Inject
	public PopupView(final Binder binder) {
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}
}

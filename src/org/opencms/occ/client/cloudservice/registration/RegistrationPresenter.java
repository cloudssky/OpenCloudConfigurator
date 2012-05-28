package org.opencms.occ.client.cloudservice.registration;

import org.opencms.occ.client.cloudservice.CloudSizerData;
import org.opencms.occ.client.cloudservice.registration.action.CloudUserData;
import org.opencms.occ.client.cloudservice.registration.action.CloudUserDataResult;
import org.opencms.occ.client.cloudservice.registration.popup.Popup2Presenter;
import org.opencms.occ.client.place.NameTokens;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
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
 * Diese Klasse implementiert notwendiger Ablauf fuer RegistrationView. 
 * Enthaelt E-Mail, PWD-Validirungen und einfache Benutzereingaben werden behandelt.
 * This class implements the necessary sequence for Registration View.
 * Contains e-mail, PWD Validirungen simple and user input is handled.
 * @author Bagautdinov
 * @version 1.0
 */
public class RegistrationPresenter extends
		Presenter<RegistrationPresenter.MyView, RegistrationPresenter.MyProxy> {

	// private String tempData = "";
	private String cpu = "";
	private String ram = "";
	private String hdd = "";
	private String price = "";
	private String location = "";
	// private EMailValidator emailValidator;
	// private PasswordValidator pwdValidator;
	private final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
//	private final SessionId sessionId = new SessionId();

	// private Set<String> cloudSizeData ;
	// private CloudSizerData cloudSizerData;

	public interface MyView extends View {
		public Label getCPULabel();

		public Label getRamLabel();

		public Label getHDDLabel();

		public Label getPriceLabel();

		public Label getLocationLabel();

		public Button getKorrektButton();

		public Button getChangeButton();

		void setCloudUserData(CloudUserData cloudUserData);// nicht noetig

		CloudUserData getCloudUserData();

		CloudSizerData getCloudSizerData();
	}

	@Inject
	DispatchAsync dispatchAsync;
	@Inject
	PlaceManager placeManager;
	@Inject
	Popup2Presenter mypopup2;

	@ProxyCodeSplit
	@NameToken(NameTokens.registration)
	public interface MyProxy extends ProxyPlace<RegistrationPresenter> {
	}

	@Inject
	public RegistrationPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy) {
		super(eventBus, view, proxy);
//		sessionId.setSessionId(Cookies.getCookie("session"));
		// onBind();
	}

	@Override
	protected void revealInParent() {
		RevealRootContentEvent.fire(this, this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		// new CloudUserData();
	}

	// private void newCloudUserData() {
	// new CloudUserData();
	//
	// }

	@Override
	protected void onReset() {
		super.onReset();
		// Daten werden aus prepareFromRequest-Method bekommen
		getView().getCPULabel().setText(cpu);// hier wird cpu parameter gesetzt
		getView().getRamLabel().setText(ram);// hier wird ram parameter gesetzt
		getView().getHDDLabel().setText(hdd);// hier wird hdd parameter gesetzt
		getView().getPriceLabel().setText(price);// hier wird price parameter
													// gesetzt
		getView().getLocationLabel().setText(location);// hier wird location
														// parameter gesetzt

		// Es wird ein back'er-Button implementiert
		getView().getChangeButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				PlaceRequest request = new PlaceRequest(NameTokens.cloudold);// gehe
																				// auf
																				// vorherige
																				// Seite
				placeManager.revealPlace(request);

			}
		});
		getView().getKorrektButton().setEnabled(true);
		getView().getKorrektButton().setText("Submit");
		// ein weiter-Button Implementierung
		getView().getKorrektButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				CloudUserData cloudUserData;
				String validationInput = validateUserInput();
				if (validationInput == null) {
					// es soll geprueft werden, dass email und emailbestaetigung
					// uebereinstimmen. und pwd auch
					if (getView().getCloudUserData().getEmail()
							.equals(getView().getCloudUserData().getEmail2())) {
						if (getView().getCloudUserData().getPassword().equals(getView().getCloudUserData().getPassword2())) {
								getView().getKorrektButton().setText("Please Wait ");
								getView().getKorrektButton().setTitle("Please Wait ");
								getView().getKorrektButton().setEnabled(false);
								cloudUserData = getView().getCloudUserData();
							// System.out.println("cloudUserData.getCloudSizerData().getCpuSize() :"
							// +
							// cloudUserData.getCloudSizerData().getCpuSize());
								dispatchAsync.execute(cloudUserData,getCloudUserData);
								getView().setCloudUserData(new CloudUserData());
						} else {
							Window.alert("Field Confirm Password must be identical with Passwort.");
						}
					} else {
						Window.alert("Field Confirm E-Mail must be identical with E-Mail.");
					}
				} else {
					Window.alert(validationInput);
				}
			}

		});
	}

	private AsyncCallback<CloudUserDataResult> getCloudUserData = new AsyncCallback<CloudUserDataResult>() {

		// Wenn alles korrekt, zeige Popup-Fenster mit der Berstätigung
		@Override
		public void onSuccess(CloudUserDataResult result) {
				addToPopupSlot(mypopup2);
		}

		// Wenn Probleme gibt, ausgeben
		@Override
		public void onFailure(Throwable caught) {
			Window.alert("CloudUser can not be created: "
					+ caught.getMessage());
		}
	};

	/**
	 * Diese Methode uebernimmt uebergebene Parameter von vocherige Seite und
	 * setzt diese Parameter in dargestellte Werte ein.
	 */
	@Override
	public void prepareFromRequest(PlaceRequest request) {
		super.prepareFromRequest(request);
		// Ziehen die Daten aus vorherige Seite
		// getParameter() s. PlaceRequest
		// tempData = request.getParameter("tempData", "temp");
		cpu = request.getParameter("cpu", cpu);
		ram = request.getParameter("ram", ram);
		hdd = request.getParameter("hdd", hdd);
		price = request.getParameter("price", price);
		location = request.getParameter("location", location);
	}

	/**
	 * Diese Methode prueft nach korrekte Eingabe von dem Benutzer. z.B. es darf
	 * kein leeres Feld sein oder korrekte Eingabe von E-Mail, PWD(nach JSR
	 * 303). Wenn alles korrekt, dann name ist null, sonnst felerhafte Feld wird
	 * uebergeben.
	 * 
	 * @return name
	 */
	private String validateUserInput() {
		String name = null;
		// System.out.println("in validateUserInput: ");
		name = validateName(name, "Name", getView().getCloudUserData()
				.getName());
		name = validateName(name, "Last name", getView().getCloudUserData()
				.getLastName());
		name = validateEMail(name, "E-Mail", getView().getCloudUserData()
				.getEmail());
		name = validateEMail(name, "Confirm E-Mail", getView()
				.getCloudUserData().getEmail2());
		name = validatePWD(name, "Passwort", getView().getCloudUserData()
				.getPassword());
		name = validatePWD(name, "Confirm Password", getView()
				.getCloudUserData().getPassword2());
		name = validateName(name, "Street", getView().getCloudUserData()
				.getStreet());
		name = validateHouse(name, "House number", getView().getCloudUserData()
				.getHouse());
		name = validateName(name, "Postcode", getView().getCloudUserData().getPost());
		name = validateName(name, "City", getView().getCloudUserData()
				.getCity());
		return name;
	}

	/**
	 * Diese Methode prueft, ob Eingabefeld nicht leer ist und min. 3 Symbole
	 * enthaelt.
	 * 
	 * @param name
	 * @param fieldName
	 *            Uebergebener Feldname
	 * @param value
	 *            Uebergebener Wert
	 * @return name
	 */
	private String validateName(String name, String fieldName, String value) {
		if ((value == null || value.trim().length() == 0)
				|| value.trim().length() < 3) {
			String temp = "Field " + fieldName
					+ " must not be empty and min. 3 sing.";
			name = name == null ? temp : name + "\n" + temp;
		}
		return name;
	}

	/**
	 * Diese Methode prueft, ob E-Mail korrekt eingegeben. Nach JSR 303 Muster
	 * "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
	 * 
	 * @param name
	 * @param fieldName
	 *            Uebergebener Feldname(mail)
	 * @param value
	 *            Uebergebene E-Mailadresse
	 * @return name
	 */
	private String validateEMail(String name, String fieldName, String value) {
		if (!value.matches(EMAIL_PATTERN)) {
			String temp = "Field " + fieldName + " not correct.";
			name = name == null ? temp : name + "\n" + temp;
		}
		return name;
	}

	/**
	 * Diese Methode prueft, ob PWD korrekt eingegeben. Nach JSR 303 Muster
	 * "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})"
	 * 
	 * @param name
	 * @param fieldName
	 *            Uebergebener Feldname(pwd)
	 * @param value
	 *            Uebergebene PWD
	 * @return name
	 */
	private String validatePWD(String name, String fieldName, String value) {
		if (!value.matches(PASSWORD_PATTERN)) {
			String temp = "Field " + fieldName + " not correct.";
			name = name == null ? temp : name + "\n" + temp;
		}
		return name;
	}

	/**
	 * Diese Methode prueft, ob Eingabefeld nicht leer ist.
	 * 
	 * @param name
	 * @param fieldName
	 *            Uebergebener Feldname(Hausnummer)
	 * @param value
	 *            Uebergebener Hausnummer
	 * @return
	 */
	private String validateHouse(String name, String fieldName, String value) {
		if ((value == null || value.trim().length() == 0)) {
			String temp = "Field " + fieldName + " must not be empty.";
			name = name == null ? temp : name + "\n" + temp;
		}
		return name;
	}

}

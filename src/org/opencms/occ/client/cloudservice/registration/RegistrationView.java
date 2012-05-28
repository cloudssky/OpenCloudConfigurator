package org.opencms.occ.client.cloudservice.registration;

import org.opencms.occ.client.cloudservice.CloudSizerData;
import org.opencms.occ.client.cloudservice.registration.action.CloudUserData;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

/**
 * Diese Klasse stellt eine einfache Registrationsseite mit ausgefaehlten OpenCloudConfigurationsDaten dar.
 * This class provides a simple registration page with selected OpenCloud configuration data.
 * @author Bagautdinov
 * @version 1.0
 */
public class RegistrationView extends ViewImpl implements RegistrationPresenter.MyView {

	private final Widget widget;

	@UiField
	Label cpuLabel;
	@UiField
	Label ramLabel;
	@UiField
	Label hddLabel;
	@UiField
	Label priceLabel;
	@UiField
	Label locationLabel;
	@UiField
	Button changeButton;

	@UiField
	TextBox nameValue;
	@UiField
	TextBox lNameValue;
	@UiField
	TextBox firmaValue;
	@UiField
	TextBox emailValue;
	@UiField
	TextBox email2Value;
	@UiField
	TextBox passValue;
	@UiField
	TextBox pass2Value;

	@UiField
	TextBox streetValue;
	@UiField
	TextBox houseValue;
	@UiField
	TextBox postValue;
	@UiField
	TextBox cityValue;

	@UiField
	Button korrektButton;

	private CloudUserData cloudUserData;
	private CloudSizerData cloudSizerData;

	public interface Binder extends UiBinder<Widget, RegistrationView> {
	}

	@Inject
	public RegistrationView(final Binder binder) {
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	/**
	 * @return the cpuLabel
	 */
	public Label getCPULabel() {
		return cpuLabel;
	}

	/**
	 * @return the ramLabel
	 */
	public Label getRamLabel() {
		return ramLabel;
	}

	/**
	 * @return the hddLabel
	 */
	public Label getHDDLabel() {
		return hddLabel;
	}

	/**
	 * @return the priceLabel
	 */
	public Label getPriceLabel() {
		return priceLabel;
	}

	/**
	 * @return the locationLabel
	 */
	public Label getLocationLabel() {
		return locationLabel;
	}

	/**
	 * @return the nameValue
	 */
	public TextBox getNameValue() {
		return nameValue;
	}

	/**
	 * @return the lNameValue
	 */
	public TextBox getlNameValue() {
		return lNameValue;
	}

	/**
	 * @return the firmaValue
	 */
	public TextBox getFirmaValue() {
		return firmaValue;
	}

	/**
	 * @return the emailValue
	 */
	public TextBox getEmailValue() {
		return emailValue;
	}

	/**
	 * @return the email2Value
	 */
	public TextBox getEmail2Value() {
		return email2Value;
	}

	/**
	 * @return the passValue
	 */
	public TextBox getPassValue() {
		return passValue;
	}

	/**
	 * @return the pass2Value
	 */
	public TextBox getPass2Value() {
		return pass2Value;
	}

	/**
	 * @return the streetValue
	 */
	public TextBox getStreetValue() {
		return streetValue;
	}

	/**
	 * @return the houseValue
	 */
	public TextBox getHouseValue() {
		return houseValue;
	}

	/**
	 * @return the postValue
	 */
	public TextBox getPostValue() {
		return postValue;
	}

	/**
	 * @return the cityValue
	 */
	public TextBox getCityValue() {
		return cityValue;
	}

	/**
	 * @return the korrektButton
	 */
	public Button getKorrektButton() {
		return korrektButton;
	}

	/**
	 * @return the changeButton
	 */
	public Button getChangeButton() {
		return changeButton;
	}

	 @Override
	 public void setCloudUserData(CloudUserData cloudUserData) {
	 this.cloudUserData = cloudUserData;
	
	 }

	/**
	 * Wird ein CloudUserData mit Daten(name, nachname, firma e-mail, pwd,
	 * strasse, hausnum., plz, stadt und CloudSizerData(cpu, ram, hdd, price,
	 * location)) gefuehlt.
	 * 
	 * @return cloudUserData
	 */
	@Override
	public CloudUserData getCloudUserData() {
		cloudUserData = new CloudUserData();

		cloudUserData.setCloudSizerData(getCloudSizerData());
		// System.out.println("cloudUserData.getCloudSizerData() " +
		// cloudUserData.getCloudSizerData());
		cloudUserData.setName(nameValue.getValue());
		//TODO: nachsehen wieso wird es mehrmals aufgeruffen
//		System.out.println("nameValue.getValue() nach set: " + nameValue.getValue());
		cloudUserData.setLastName(lNameValue.getValue());
		cloudUserData.setFirmaName(firmaValue.getValue());
		cloudUserData.setEmail(emailValue.getValue());
		cloudUserData.setEmail2(email2Value.getValue());
		cloudUserData.setPassword(passValue.getValue());
		cloudUserData.setPassword2(pass2Value.getValue());
		cloudUserData.setStreet(streetValue.getValue());
		cloudUserData.setHouse(houseValue.getValue());
		cloudUserData.setPost(postValue.getValue());
		cloudUserData.setCity(cityValue.getValue());
		return cloudUserData;
	}

	@Override
	public CloudSizerData getCloudSizerData() {
		cloudSizerData = new CloudSizerData();
		cloudSizerData.setCpuSize(cpuLabel.getText());
		//TODO: nachsehen wieso wird es mehrmals aufgeruffen
//		System.out.println("cpuLabel.getText() nach set: in cloudSizerData" + cpuLabel.getText());
		cloudSizerData.setRamSize(ramLabel.getText());
		cloudSizerData.setHddSize(hddLabel.getText());
		cloudSizerData.setPriceSize(priceLabel.getText());
		cloudSizerData.setCloudLocation(locationLabel.getText());
		return cloudSizerData;
	}

}

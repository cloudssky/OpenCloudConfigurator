package org.opencmp.occ.client.cloudservice.registration.action;

import org.opencmp.occ.client.cloudservice.CloudSizerData;

import com.gwtplatform.dispatch.shared.ActionImpl;


/**
 * Diese Klasse stellt ein Objekt mit CloudUserData(Name, Nachname, Firmaname, E-Mail, PWD, Strasse,
 * Hausnum., PLZ und Stadt) und CloudSizer(CPU, RAM, HDD, Location, Cloud Apps und Preis) zusammen.
 * This class represents an object with CloudUser data (name, last name, company name, e-mail, PWD, street,
 * House num., Postal code and city) and CloudSizer (CPU, RAM, HDD, location and cost) together.
 * 
 * @author Bagautdinov
 * @version 1.0
 */
public class CloudUserData extends ActionImpl<CloudUserDataResult> {

	
	// private String cloudValue;
	private CloudSizerData cloudSizerData;
//	@Size(min=3) 
	private String name;
	private String lastName;
	private String firmaName;
	
	private String email;
	private String email2;
	private String password;
	private String password2;
	
	private String street;
	private String house;
	private String post;
	private String city;

	// @SuppressWarnings("unused")
	public CloudUserData() {
		// For serialization only
	}

	// Schutz gegen XSRF Atacken
	@Override
	public boolean isSecured() {
		return false;
	}

	/**
	 * @return the cloudSizerData
	 */
	public CloudSizerData getCloudSizerData() {
		return cloudSizerData;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the firmaName
	 */
	public String getFirmaName() {
		return firmaName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @return the emailvalidation
	 */
	public String getEmail2() {
		return email2;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the passwordvalidation
	 */
	public String getPassword2() {
		return password2;
	}
	
	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @return the house
	 */
	public String getHouse() {
		return house;
	}

	/**
	 * @return the post
	 */
	public String getPost() {
		return post;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	// /**
	// * @param cloudValue
	// * the cloudValue to set
	// */
	// public void setCloudValue(String cloudValue) {
	// this.cloudValue = cloudValue;
	// }

	/**
	 * @param cloudSizerData
	 *            the cloudSizerData to set
	 */
	public void setCloudSizerData(CloudSizerData cloudSizerData) {
		this.cloudSizerData = cloudSizerData;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @param firmaName
	 *            the firmaName to set
	 */
	public void setFirmaName(String firmaName) {
		this.firmaName = firmaName;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @param emailvalidation
	 *            the emailvalidation to set
	 */
	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param passwordvalidation
	 *            the passwordvalidation to set
	 */
	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	/**
	 * @param street
	 *            the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @param house
	 *            the house to set
	 */
	public void setHouse(String house) {
		this.house = house;
	}

	/**
	 * @param post
	 *            the post to set
	 */
	public void setPost(String post) {
		this.post = post;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CloudUserData [cloudSizerData=" + cloudSizerData + ", name=" + name + ", lastName=" + lastName + ", firmaName=" + firmaName + ", email=" + email + ", emailvalidation=" + email2 + ", password=" + password + ", passwordvalidation=" + password2 + ", street=" + street + ", house=" + house + ", post=" + post + ", city=" + city + "]";
	}
	/**
	 * Formatierte Ausgabe fuer Support..
	 */
	public String toStringSupport() {
		return "ClooudUserData:\n" 
				+ cloudSizerData.toStringSupport()
				+"\n\nBenutzerData:\n"
				+"Name:   " + name + ",\n"
				+"Nachname:   " + lastName + ",\n"
				+"Firmenname:   " + firmaName + ",\n"
				+"Kontakt-EMail:   " + email + ",\n"
				+"Passwort:   " + password + ",\n"
				+"Stra\u00dfe:   " + street + ", Hausnum.:   " + house + ",\n"
				+"PLZ:   " + post +  ", Stadt:   " + city + ".";
	}

	/**
	 * Formatierte Ausgabe fuer neuen registrierten Benutzer.
	 */
	public String toStringNewUser() {
		return "Clouduser Data\n" + "Name: " + name + ", email:" + email + ".";
	}

}

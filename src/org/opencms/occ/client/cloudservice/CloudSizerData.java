package org.opencms.occ.client.cloudservice;

import java.io.Serializable;
import java.lang.String;

/**
 * Einfaches Objekt als CloudSizer mit CPU, RAM, HDD, Location und Preis
 * 
 * @author Bagautdinov
 * 
 */
public class CloudSizerData implements Serializable {

	private static final long serialVersionUID = 6497321946200200135L;
	private String cpuSize;
	private String ramSize;
	private String hddSize;
	private String priceSize;
	private String cloudLocation;

	public CloudSizerData() {
	}

	public void setCpuSize(String cpuSize) {
		this.cpuSize = cpuSize;
	}

	public void setRamSize(String ramSize) {
		this.ramSize = ramSize;
	}

	public void setHddSize(String hddSize) {
		this.hddSize = hddSize;
	}

	public void setPriceSize(String priceSize) {
		this.priceSize = priceSize;
	}

	public void setCloudLocation(String cloudLocation) {
		this.cloudLocation = cloudLocation;
	}

	public String getCpuSize() {
		return cpuSize;
	}

	public String getRamSize() {
		return ramSize;
	}

	public String getHddSize() {
		return hddSize;
	}

	public String getPriceSize() {
		return priceSize;
	}

	public String getCloudLocation() {
		return cloudLocation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CloudSizerData [cpuSize=" + cpuSize + ", ramSize=" + ramSize + ", hddSize=" + hddSize + ", priceSize=" + priceSize + ", cloudLocation=" + cloudLocation + "]";
	}
	
	/**
	 * Formatierte Ausgabe von OpenCloudKonfigurator fuer Support.
	 */
	public String toStringSupport() { 
		return "OpenCloudConfiguratorData:\n"
				+"Number of vCPUs:   "+ cpuSize + ",\n"
				+"Memory(in MB):   " + ramSize + ",\n"
				+"Storage(in GB): " + hddSize + ",\n"
				+"Monthly Price(in \u20ac):   " + priceSize + ",\n"
				+"Server-Region:   " + cloudLocation + "."; 
	}
	
	/**
	 * Formatierte Ausgabe fuer neuen registrierten Benutzer englische Version.
	 */
	public String toStringNewUserEn() {
		return "Number of vCPUs: " + cpuSize + ",\nMemory(in MB): " + ramSize + ",\nStorage (in GB): " + hddSize + ",\nMonthly Price(in Ä): " + priceSize + ",\nServer-Region: " + cloudLocation + ".";
	}

	/**
	 * Formatierte Ausgabe fuer neuen registrierten Benutzer.
	 */
	public String toStringNewUser() {
		return "CloudSizer Data\nAnz. CPU: " + cpuSize + ",\nSpeichergroﬂe(in MB): " + ramSize + ",\nHDD Kap.(in GB): " + hddSize + ",\nPreis in Monat(in Ä): " + priceSize + ",\nServerposition: " + cloudLocation + ".";
	}

}

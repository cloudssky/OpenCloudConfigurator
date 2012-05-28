package org.opencmp.occ.client;

public class TrackHistory {
	
	/**
	 * constructor - nothing to do
	 */
	public TrackHistory() {
	}
	
	/**
	   * historyToken an event
	   * 
	   * @param historyToken
	   */
	  public static void trackHistory(String historyToken) {
	  	
	  	if (historyToken == null) {
	  		historyToken = "historyToken_null";
	  	}
	  	
	  	historyToken = "/main/" + historyToken;
	  	
	  
	  }
	  
	  
}

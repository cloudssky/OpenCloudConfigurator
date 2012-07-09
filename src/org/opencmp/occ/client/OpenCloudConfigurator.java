package org.opencmp.occ.client;

import org.opencmp.occ.client.gin.ClientGinjector;
import com.allen_sauer.gwt.log.client.Log;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.gwtplatform.mvp.client.DelayedBindRegistry;

/**
 * Main class with EntryPoint.
 * @author Bagautdinov
 * @version 1.0
 *
 */
public class OpenCloudConfigurator implements EntryPoint, ValueChangeHandler<String> {
	
	/**
	   * This field gets compiled out when <code>log_level=OFF</code>, or any <code>log_level</code>
	   * higher than <code>DEBUG</code>.
	   */
	@SuppressWarnings("unused")
	private long startTimeMillis;
	  
	private final ClientGinjector ginjector = GWT.create(ClientGinjector.class);
	
	@Override
	public void onModuleLoad() {
		
		/*
	     * Install an UncaughtExceptionHandler which will produce <code>FATAL</code> log messages
	     */
	    Log.setUncaughtExceptionHandler();
		
	    /*
	     * Use a <code>if (Log.isDebugEnabled()) {...}</code> guard to ensure that
	     * <code>System.currentTimeMillis()</code> is compiled out when <code>log_level=OFF</code>, or
	     * any <code>log_level</code> higher than <code>DEBUG</code>.
	     */
	    if (Log.isDebugEnabled()) {
	      startTimeMillis = System.currentTimeMillis();
	    }
	    
		initHistoryObservations();
		// This is required for Gwt-Platform proxy's generator
		DelayedBindRegistry.bind(ginjector);

		ginjector.getPlaceManager().revealCurrentPlace();
	}

	/**
	 * watch the historyTokens after the querystring#historyToken or you could
	 * say querystring#[historyEvent|applicationState]
	 */
	private void initHistoryObservations() {

		History.addValueChangeHandler(this);
//		History.newItem(null, true);
		// first load
		TrackHistory.trackHistory("main");
		History.fireCurrentHistoryState();
	}

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {

		// get the querystring token
		String historyToken = History.getToken();

		// send to static method that will send the __utm.gif to google's server
		// for tracking
		TrackHistory.trackHistory(historyToken);

	}

}

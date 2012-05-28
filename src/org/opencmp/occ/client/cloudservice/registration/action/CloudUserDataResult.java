package org.opencmp.occ.client.cloudservice.registration.action;

import com.gwtplatform.dispatch.shared.Result;
import java.lang.String;
/**
 * Diese Klasse wird fuer Server-Status benutzt.
 * This class is used for server status.
 * @author Bagautdinov
 * @version 1.0
 */
public class CloudUserDataResult implements Result {

	private String status;

	@SuppressWarnings("unused")
	private CloudUserDataResult() {
		// For serialization only
	}

	public CloudUserDataResult(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}

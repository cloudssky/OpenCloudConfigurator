package org.opencmp.occ.server;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.opencmp.occ.client.cloudservice.registration.action.CloudUserData;
import org.opencmp.occ.client.cloudservice.registration.action.CloudUserDataResult;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * Eine serverseitige Klasse, die alle notwendige Schritte(Wie z.B. DB-Verbindung, E-Mail-Versendung) ausfuehrt.
 * A server-side class that performs all necessary steps (such as DB connection, sending e-mail).
 * @author Bagautdinov
 * @version 1.0
 *
 */
public class CloudUserDataActionHandler implements ActionHandler<CloudUserData, CloudUserDataResult> {

	@Inject
	public CloudUserDataActionHandler() {
	}
	
	DB_Conn db;
	SendMailSSLImpl send;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date date = new Date();//TODO: Id-Generator implementieren z.B. aktuelle Zeit und Benurtzername(0205121350bob) !!! aktuell nur  Zeitstempel!!!
	CloudUserData cloudUserData;
	@Override
	public CloudUserDataResult execute(CloudUserData action, ExecutionContext context) throws ActionException {
		db = new DB_Conn();
		db.connectToDB(dateFormat.format(date), action.getCloudSizerData().getCpuSize(), action.getCloudSizerData().getRamSize(), action.getCloudSizerData().getHddSize(),
						action.getCloudSizerData().getPriceSize(), action.getCloudSizerData().getCloudLocation(), action.getCloudSizerData().getCloudApps(),
						action.getName(), action.getLastName(), action.getFirmaName(), action.getEmail(), action.getPassword(), action.getStreet(), 
						action.getHouse(), action.getPost(), action.getCity(), "Insert");
		send = new SendMailSSLImpl();
//		send.sendeEMailSSL();
		send.sendeEMailSSLonSupport(action);
		return new CloudUserDataResult(action.getName());
	}

	@Override
	public void undo(CloudUserData action, CloudUserDataResult result, ExecutionContext context) throws ActionException {
	}

	@Override
	public Class<CloudUserData> getActionType() {
		return CloudUserData.class;
	}
}

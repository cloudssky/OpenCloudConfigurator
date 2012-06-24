package org.opencmp.occ.client.cloudservice;

import org.opencmp.occ.client.cloudservice.slider.SliderBar;
import org.opencmp.occ.client.cloudservice.slider.SliderBar.LabelFormatter;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;


/**
 * Diese Klasse stellt ein CloudSizer-View zusammen. MailSlideBar wird von
 * http://code.google.com/p/gwt-slider-bar/ benutzt.
 * This class puts together a CloudSizer view. Copyright http://code.google.com/p/gwt-slider-bar/
 * 
 * @author Bagautdinov
 * @version 1.0
 */
@SuppressWarnings("deprecation")
public class CloudOldView extends ViewImpl implements CloudOldPresenter.MyView {
	
	private static String html = "<div id=logoText><img src=\"./image/CloudSizerLogo.png\">OpenCloudConfigurator</div>";
	private final HTMLPanel panel = new HTMLPanel(html);

	final TextBox cpuBox;
	final TextBox ramBox;
	final TextBox hddBox;
	final TextBox priceBox;
	final RadioButton locate;
	final RadioButton locate2;
	final RadioButton locate3;
	final RadioButton locate4;
	String countryLocation = "Germany";
	final ListBox cloudAppsListBox;	
	
	
	
	CloudSizerData cloudSizer;

	private final Button registrationButton;

	// private final Label errorLabel;

	/* Default values for sliderbar(min, max, step) and other(ram, price) values */
	static final int MIN_VALUE = 0;
	static final int MAX_VALUE = 8;
	static final int STEP_VALUE = 1;
	static final int RAM_VALUE = 1024;
	static final double PRICE_VALUE = 14.98;

	private SliderBar mainSliderBar = new SliderBar(MIN_VALUE, MAX_VALUE,
			new LabelFormatter() {
				public String formatLabel(SliderBar slider, double value) {
					return (int) (10 * (int) value) / 10 + "";

				}
			});

	@Inject
	public CloudOldView() {
		// TextBox to display or set current value
		cpuBox = new TextBox();
		cpuBox.setReadOnly(true);
		ramBox = new TextBox();
		ramBox.setReadOnly(true);
		hddBox = new TextBox();
		hddBox.setReadOnly(true);
		priceBox = new TextBox();
		priceBox.setReadOnly(true);
		locate = new RadioButton("country", "Europe");
		locate.setChecked(true);// Punkt auf Germany setzen
		locate2 = new RadioButton("country", "US North");
		locate3 = new RadioButton("country", "Middle East");
		locate4 = new RadioButton("country", "Asia Pacific");
		cloudAppsListBox = new ListBox(false);
		cloudAppsListBox.addItem("- not selected -");
		cloudAppsListBox.addItem("Alfresco");
		cloudAppsListBox.addItem("OpenCms");
		cloudAppsListBox.addItem("Zimbra");
		//appListbox.setSelectedIndex(0);
	    // anzahl der sichbaren Elementen
//		appListbox.setVisibleItemCount(5);
		cloudAppsListBox.addChangeHandler(new ChangeHandler() {
			  @Override
		      public void onChange(ChangeEvent event) {
				  cloudAppsListBox.setSelectedIndex(cloudAppsListBox.getSelectedIndex());
		      }

		});
	    
		// errorLabel = new Label();
		registrationButton = new Button("Next");
		registrationButton.setTitle("go");
		// bei der Erstellung wird Button sofort nicht aktiv sein
		registrationButton.setEnabled(false);

		// Setup the slider bars
		mainSliderBar.setStepSize(STEP_VALUE);
		mainSliderBar.setCurrentValue(0);
		mainSliderBar.setNumTicks(MAX_VALUE);
		mainSliderBar.setNumLabels(8);
		// bei Verschieben wird sich Wert(CPU) aendern
		mainSliderBar.addChangeListener(new ChangeListener() {
			public void onChange(Widget sender) {
				cpuBox.setText((int) mainSliderBar.getCurrentValue() + "");
				// wenn es kein CloudSizer aufgewaehlt oder auf Null gesetzt,
				// dann kann Benutzer auf Auswaehlen-Butten nicht klicken.
				if ((int) mainSliderBar.getCurrentValue() > 0)
					registrationButton.setEnabled(true);
				else
					registrationButton.setEnabled(false);
			}
		});
		// bei Verschieben wird sich Wert(RAM) aendern
		mainSliderBar.addChangeListener(new ChangeListener() {
			public void onChange(Widget sender) {
				ramBox.setText((int) mainSliderBar.getCurrentValue()
						* RAM_VALUE + "");
			}
		});
		// bei Verschieben wird sich Wert(Preis) aendern
		mainSliderBar.addChangeListener(new ChangeListener() {
			public void onChange(Widget sender) {
				priceBox.setText(mainSliderBar.getCurrentValue() * PRICE_VALUE
						+ "");
			}
		});
		// html = html + "<div id=\"supertest\"> <h2> bla </h2> ";
		VerticalPanel vpanel = new VerticalPanel();
		// Place everything in a nice looking grid
		Grid grid = new Grid(2, 6);
		grid.setStyleName("gridSizer");
		grid.setBorderWidth(1);
		grid.setCellPadding(3);

		// The type of text to display
		// final HTML defaultTextLabel = new HTML("custom");

		// CPU-Position mit Startwerten
		cpuBox.setText(String.valueOf(MIN_VALUE));
		cpuBox.setVisibleLength(5);
		grid.setHTML(0, 0, "Number of vCPUs");
		grid.setWidget(1, 0, cpuBox);

		// Ram-Position mit Startwerten
		ramBox.setText(String.valueOf("0"));
		ramBox.setVisibleLength(15);
		grid.setHTML(0, 1, "Memory(in MB)");
		grid.setWidget(1, 1, ramBox);

		// HDD-Position mit Startwerten
		hddBox.setText("10");
		hddBox.setVisibleLength(12);
		grid.setHTML(0, 2, "Storage(in GB)");
		grid.setWidget(1, 2, hddBox);

		// Price-Position mit Startwerten
		priceBox.setText(String.valueOf("0.00"));
		priceBox.setVisibleLength(12);
		grid.setHTML(0, 3, "Monthly Price(in \u20ac)*");
		grid.setWidget(1, 3, priceBox);

		// Country-Position mit Startwert default location=Germany
		locate.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				countryLocation = "Germany";

			}
		});
		// Country-Position mit Startwert
		locate2.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				countryLocation = "US North";

			}
		});
		locate3.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				countryLocation = "Middle East";

			}
		});
		locate4.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				countryLocation = "Asia Pacific";

			}
		});
		grid.setHTML(0, 4, "Server-Region");
		VerticalPanel panelLocation = new VerticalPanel();
		panelLocation.add(locate);
		panelLocation.add(locate2);
		panelLocation.add(locate3);
		panelLocation.add(locate4);
		grid.setWidget(1, 4, panelLocation);
		//fuegen von CloudApps-Elementen(Liste)
		grid.setHTML(0, 5, "Cloud Apps");
		grid.setWidget(1, 5, cloudAppsListBox);
		// Add elements to page
		// panel.add(mainSliderBar, "mainSliderBar");
		// mainSliderBar.setStyleName("supertest");
		vpanel.add(mainSliderBar);
		vpanel.setCellHorizontalAlignment(mainSliderBar,
				HasHorizontalAlignment.ALIGN_CENTER);
		// panel.add(grid);
		vpanel.add(grid);
		vpanel.setCellHorizontalAlignment(grid,
				HasHorizontalAlignment.ALIGN_CENTER);
		// panel.add(errorLabel, "errorLabelContainer");
		// html = html + "</div>";
		// We can add style names to widgets
		registrationButton.addStyleName("registrationButton");
		// panel.add(registrationButton, "registrationButtonContainer");
		TextBox info = new TextBox();
		info.setVisibleLength(22);
		info.setText("* Free 30-day Trial");
		vpanel.add(info);
		vpanel.add(registrationButton);
		vpanel.setCellHorizontalAlignment(registrationButton,
				HasHorizontalAlignment.ALIGN_CENTER);
		panel.add(vpanel);
	}

	@Override
	public Widget asWidget() {
		return panel;
	}

	/**
	 * @return the cpuBox
	 */
	public TextBox getCpuBox() {
		return cpuBox;
	}

	/**
	 * @return the ramBox
	 */
	public TextBox getRamBox() {
		return ramBox;
	}

	/**
	 * @return the hddBox
	 */
	public TextBox getHddBox() {
		return hddBox;
	}

	/**
	 * @return the priceBox
	 */
	public TextBox getPriceBox() {
		return priceBox;
	}

	// /**
	// * @return the countryLocation
	// */
	// public String getCountryLocation() {
	// return countryLocation;
	// }

	/**
	 * Wird ein CloudSizer mit Daten(cpu, ram, hdd, price, location, cloudAppList) gefuehlt
	 * 
	 * @return the cloudSizerData
	 */
	public CloudSizerData getCloudSizerData() {
		cloudSizer = new CloudSizerData();
		cloudSizer.setCpuSize(cpuBox.getValue());
		cloudSizer.setRamSize(ramBox.getValue());
		cloudSizer.setHddSize(hddBox.getValue());
		cloudSizer.setPriceSize(priceBox.getValue());
		// cloudSizer.setCloudLocation(getCountryLocation());
		cloudSizer.setCloudLocation(countryLocation);
		//gibt zurueck nur ausgewaelte Element
		cloudSizer.setCloudApps(cloudAppsListBox.getItemText(cloudAppsListBox.getSelectedIndex())); 
		return cloudSizer;
	}

	/**
	 * @return the registrationButton
	 */
	public Button getRegistrationButton() {
		return registrationButton;
	}

}

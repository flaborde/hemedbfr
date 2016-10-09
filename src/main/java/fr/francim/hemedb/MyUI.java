package fr.francim.hemedb;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.annotation.WebServlet;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

import fr.francim.hemedb.model.Disease;
import fr.francim.hemedb.model.Diseases;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        
                
        setContent(layout);
        
        TextField d1 = new TextField("Maladie 1");
		TextField d2 = new TextField("Maladie 2");
		Button calc = new Button("Calculer");
		Label resultLabel = new Label("");
		HorizontalLayout hlMultiplePrimaryCalc = new  HorizontalLayout(d1,d2,calc,resultLabel);
		hlMultiplePrimaryCalc.setMargin(true);
		hlMultiplePrimaryCalc.setSpacing(true);
		layout.addComponent(hlMultiplePrimaryCalc);
		
		calc.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				resultLabel.setValue("");
				String urlPart = "http://seer.cancer.gov/seertools/hemelymph/mpc/?d1="+d1.getValue()+"&d2="+d2.getValue();
				URL url;
				try {
					url = new URL(urlPart);
					 HttpURLConnection request = (HttpURLConnection) url.openConnection();
				    request.connect();
				    
				    JsonParser jp = new JsonParser(); //from gson
				    JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
				    JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object. 
				    
				    resultLabel.setValue(rootobj.get("message").getAsString());
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   
			}
		});
        
//        XMLContainer xmlDataSource = XMLContainer.Factory.newInstanceForDom(getClass().getResourceAsStream("diseases-data-fr.xml"), Disease.class);
//        
//        Table xmlTable = new Table("HEME DB FR",xmlDataSource);
//        layout.addComponent(xmlTable);
//        xmlTable.setSizeFull();
        
        JAXBContext jc;
        
		try {
			jc = JAXBContext.newInstance(Diseases.class);

	        Unmarshaller unmarshaller = jc.createUnmarshaller();
	        Diseases diseases = (Diseases) unmarshaller.unmarshal(getClass().getResourceAsStream("diseases-data-fr.xml"));
		
	        BeanItemContainer<Disease> bic = new BeanItemContainer<Disease>(Disease.class);
	        bic.addAll(diseases.getDiseases());
	        
	        Table diseasesTable = new Table("HemeDB FR", bic);
	        diseasesTable.setVisibleColumns("icdO3", "name");
	        
	        layout.addComponent(diseasesTable);
	        
	        diseasesTable.setSizeFull();
	        
	        diseasesTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {
				
				@Override
				public void itemClick(ItemClickEvent event) {
					Disease disease = bic.getItem(event.getItemId()).getBean();
					
					if (disease != null){
						Window subWindow = new Window(disease.getName());
				        VerticalLayout subContent = new VerticalLayout();
				        subContent.setMargin(true);
				        subWindow.setContent(new DiseaseLayout(disease));
				        subWindow.center();
				        addWindow(subWindow);
					}
				}
			});
	        
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		

    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}

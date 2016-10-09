package fr.francim.hemedb;

import javax.servlet.annotation.WebServlet;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.vaadin.tunis.saif.xmlcontainer.XMLContainer;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

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
	        
	        layout.addComponent(diseasesTable);
	        diseasesTable.setSizeFull();
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

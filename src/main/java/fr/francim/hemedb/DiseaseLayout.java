package fr.francim.hemedb;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import fr.francim.hemedb.model.Disease;
import fr.francim.hemedb.model.Diseases;

public class DiseaseLayout extends VerticalLayout {

	TextField tfName = new TextField("Nom");
	TextField tfIcd01Morphology = new TextField("Morphologie Code CIM-O 1");
	TextField tfIcd02Morphology = new TextField("Morphologie Code CIM-O 2");
	TextField tfIcd03Morphology = new TextField("Morphologie Code CIM-O 3");
	TextField tfIcd01effective = new TextField();
	TextField tfIcd02effective = new TextField();
	TextField tfIcd03effective = new TextField();
	
	TextField tfReportable = new TextField("Reportable");
	TextField tfPrimarySite = new TextField("Loc. primitive");
	
	
	TextArea taDefinition = new TextArea("Définition");
	
	TextArea taAlternateNames = new TextArea("Autres noms");
	TextArea taDiagnosisMethods = new TextArea("Méthodes de diagnostics");
	TextArea taGenetics = new TextArea("Génétique");
	TextArea taImmunophenotypes = new TextArea("Immunophenotypes");
	
	
	Disease disease ;
	
	public DiseaseLayout(Disease disease) {
		super();
		this.disease = disease;
		init();
	}
	
	private void init(){
		
		setSpacing(true);
		setMargin(true);
		setWidth("600px");
		
		BeanFieldGroup<Disease> bfg = new BeanFieldGroup<Disease>(Disease.class);
		bfg.bind(tfName, "name");
		bfg.bind(tfIcd01Morphology, "icdO1");
		bfg.bind(tfIcd02Morphology, "icdO2");
		bfg.bind(tfIcd03Morphology, "icdO3");
		bfg.bind(tfIcd01effective, "icdO1effective");
		bfg.bind(tfIcd02effective, "icdO2effective");
		bfg.bind(tfIcd03effective, "icdO3effective");
		
		bfg.bind(tfReportable, "reportable");
		bfg.bind(taDefinition, "definition");
		bfg.setItemDataSource(disease);
		
		tfName.setWidth("500px");
		addComponent(tfName);
		addComponent(new HorizontalLayout(tfIcd01Morphology, tfIcd01effective));
		addComponent(new HorizontalLayout(tfIcd02Morphology, tfIcd02effective));
		addComponent(new HorizontalLayout(tfIcd03Morphology, tfIcd03effective));
		addComponent(tfReportable);
		
		if (disease.getPrimarySite() != null){
			tfPrimarySite.setValue(disease.getPrimarySite()+", "+disease.getPrimarySiteText());
		}
		else{
			tfPrimarySite.setValue(disease.getMissingPrimarySiteMessage());
		}
		tfPrimarySite.setWidth("500px");
		addComponent(tfPrimarySite);
		
		taDefinition.setWidth("500px");
		addComponent(taDefinition);
		
		taAlternateNames.setWidth("500px"); 
		taDiagnosisMethods.setWidth("500px");
		taGenetics.setWidth("500px");
		taImmunophenotypes.setWidth("500px");
		
		if (disease.getAlternativesNames()!=null)
			for (String altName : disease.getAlternativesNames()){
				taAlternateNames.setValue(taAlternateNames.getValue() + altName);
			}
		
		if (disease.getDiagnosisMethods()!=null)
			for (String diagMethod : disease.getDiagnosisMethods()){
				taDiagnosisMethods.setValue(taDiagnosisMethods.getValue() + diagMethod);
			}

		if (disease.getGenetics()!=null)
			for (String genetic : disease.getGenetics()){
				taGenetics.setValue(taGenetics.getValue() + genetic);
			}
		
		if (disease.getImmunophenotypes()!=null)
			for (String immunophenotype : disease.getImmunophenotypes()){
				taImmunophenotypes.setValue(taImmunophenotypes.getValue() + immunophenotype);
			}
		
		addComponent(taAlternateNames);
		addComponent(taDiagnosisMethods);
		addComponent(taGenetics);
		addComponent(taImmunophenotypes);
	}
}

package fr.francim.hemedb.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Disease {

	@XmlAttribute(name="name")
	String name;
	
	@XmlAttribute(name="code-icdO1")
	String icdO1;
	
	@XmlAttribute(name="code-icdO2")
	String icdO2;
	
	@XmlAttribute(name="code-icdO3")
	String icdO3;
	
	@XmlAttribute(name="code-icdO1-effective")
	String icdO1effective;
	
	@XmlAttribute(name="code-icdO2-effective")
	String icdO2effective;
	
	@XmlAttribute(name="code-icdO3-effective")
	String icdO3effective;
	
	@XmlAttribute(name="reportable")
	String reportable;
	
	@XmlElement(name="primary-site")
	String primarySite;
	
	@XmlElement(name="missing-primary-site-message")
	String missingPrimarySiteMessage;
	
	@XmlElement(name="primary-site-text")
	String primarySiteText;
	
	@XmlElement(name="definition")
	String definition;
	
	
	@XmlElement(name="alternate-name")
	List<String> alternativesNames;
	
	
	@XmlElement(name="diagnosis-method")
	List<String> diagnosisMethods;
	
	@XmlElement(name="genetics")
	List<String> genetics;
	
	@XmlElement(name="immunophenotypes")
	List<String> immunophenotypes;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getIcdO3() {
		return icdO3;
	}
	public void setIcdO3(String icdO3) {
		this.icdO3 = icdO3;
	}
	public String getMissingPrimarySiteMessage() {
		return missingPrimarySiteMessage;
	}
	public void setMissingPrimarySiteMessage(String missingPrimarySiteMessage) {
		this.missingPrimarySiteMessage = missingPrimarySiteMessage;
	}
	public String getPrimarySiteText() {
		return primarySiteText;
	}
	public void setPrimarySiteText(String primarySiteText) {
		this.primarySiteText = primarySiteText;
	}
	public String getDefinition() {
		return definition;
	}
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	public String getIcdO1() {
		return icdO1;
	}
	public void setIcdO1(String icdO1) {
		this.icdO1 = icdO1;
	}
	public String getIcdO2() {
		return icdO2;
	}
	public void setIcdO2(String icdO2) {
		this.icdO2 = icdO2;
	}
	public String getIcdO1effective() {
		return icdO1effective;
	}
	public void setIcdO1effective(String icdO1effective) {
		this.icdO1effective = icdO1effective;
	}
	public String getIcdO2effective() {
		return icdO2effective;
	}
	public void setIcdO2effective(String icdO2effective) {
		this.icdO2effective = icdO2effective;
	}
	public String getIcdO3effective() {
		return icdO3effective;
	}
	public void setIcdO3effective(String icdO3effective) {
		this.icdO3effective = icdO3effective;
	}
	public String getReportable() {
		return reportable;
	}
	public void setReportable(String reportable) {
		this.reportable = reportable;
	}
	public String getPrimarySite() {
		return primarySite;
	}
	public void setPrimarySite(String primarySite) {
		this.primarySite = primarySite;
	}
	public List<String> getAlternativesNames() {
		return alternativesNames;
	}
	public void setAlternativesNames(List<String> alternativesNames) {
		this.alternativesNames = alternativesNames;
	}
	public List<String> getDiagnosisMethods() {
		return diagnosisMethods;
	}
	public void setDiagnosisMethods(List<String> diagnosisMethods) {
		this.diagnosisMethods = diagnosisMethods;
	}
	public List<String> getGenetics() {
		return genetics;
	}
	public void setGenetics(List<String> genetics) {
		this.genetics = genetics;
	}
	public List<String> getImmunophenotypes() {
		return immunophenotypes;
	}
	public void setImmunophenotypes(List<String> immunophenotypes) {
		this.immunophenotypes = immunophenotypes;
	}
	
}

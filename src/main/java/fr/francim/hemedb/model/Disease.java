package fr.francim.hemedb.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Disease {

	@XmlAttribute(name="name")
	String name;
	
	@XmlAttribute(name="code-icdO3")
	String icdO3;
	
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
}

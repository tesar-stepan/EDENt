//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.03.30 at 07:50:19 odp. CEST 
//


package mzcr.cz.ns.dasta.ds4.ds_ip;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import mzcr.cz.ns.dasta.ds4.ds_type.AnyType;


/**
 * <p>Java class for pracovnikType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="pracovnikType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="titul_pred" type="{urn:cz-mzcr:ns:dasta:ds4:ds_type}str35" minOccurs="0"/>
 *         &lt;element name="jmeno" type="{urn:cz-mzcr:ns:dasta:ds4:ds_type}str24" minOccurs="0"/>
 *         &lt;element name="prijmeni" type="{urn:cz-mzcr:ns:dasta:ds4:ds_type}str35"/>
 *         &lt;element name="titul_za" type="{urn:cz-mzcr:ns:dasta:ds4:ds_type}str35" minOccurs="0"/>
 *         &lt;element name="pracovnikAny" type="{urn:cz-mzcr:ns:dasta:ds4:ds_type}AnyType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="kod_lok" type="{urn:cz-mzcr:ns:dasta:ds4:ds_type}str15" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pracovnikType", propOrder = {
    "titulPred",
    "jmeno",
    "prijmeni",
    "titulZa",
    "pracovnikAny"
})
public class PracovnikType {

    @XmlElement(name = "titul_pred")
    protected String titulPred;
    protected String jmeno;
    @XmlElement(required = true)
    protected String prijmeni;
    @XmlElement(name = "titul_za")
    protected String titulZa;
    protected AnyType pracovnikAny;
    @XmlAttribute(name = "kod_lok")
    protected String kodLok;

    /**
     * Gets the value of the titulPred property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitulPred() {
        return titulPred;
    }

    /**
     * Sets the value of the titulPred property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitulPred(String value) {
        this.titulPred = value;
    }

    /**
     * Gets the value of the jmeno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJmeno() {
        return jmeno;
    }

    /**
     * Sets the value of the jmeno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJmeno(String value) {
        this.jmeno = value;
    }

    /**
     * Gets the value of the prijmeni property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrijmeni() {
        return prijmeni;
    }

    /**
     * Sets the value of the prijmeni property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrijmeni(String value) {
        this.prijmeni = value;
    }

    /**
     * Gets the value of the titulZa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitulZa() {
        return titulZa;
    }

    /**
     * Sets the value of the titulZa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitulZa(String value) {
        this.titulZa = value;
    }

    /**
     * Gets the value of the pracovnikAny property.
     * 
     * @return
     *     possible object is
     *     {@link AnyType }
     *     
     */
    public AnyType getPracovnikAny() {
        return pracovnikAny;
    }

    /**
     * Sets the value of the pracovnikAny property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnyType }
     *     
     */
    public void setPracovnikAny(AnyType value) {
        this.pracovnikAny = value;
    }

    /**
     * Gets the value of the kodLok property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKodLok() {
        return kodLok;
    }

    /**
     * Sets the value of the kodLok property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKodLok(String value) {
        this.kodLok = value;
    }

}

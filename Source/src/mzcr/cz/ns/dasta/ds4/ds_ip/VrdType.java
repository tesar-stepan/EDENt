//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.03.30 at 07:50:19 odp. CEST 
//


package mzcr.cz.ns.dasta.ds4.ds_ip;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import mzcr.cz.ns.dasta.ds4.ds_type.AnyType;


/**
 * <p>Java class for vrdType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="vrdType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nazvy" type="{urn:cz-mzcr:ns:dasta:ds4:ds_ip}nazvyType" minOccurs="0"/>
 *         &lt;element name="dat_dv" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="vrdAny" type="{urn:cz-mzcr:ns:dasta:ds4:ds_type}AnyType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "vrdType", propOrder = {
    "nazvy",
    "datDv",
    "vrdAny"
})
public class VrdType {

    protected NazvyType nazvy;
    @XmlElement(name = "dat_dv", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datDv;
    protected AnyType vrdAny;

    /**
     * Gets the value of the nazvy property.
     * 
     * @return
     *     possible object is
     *     {@link NazvyType }
     *     
     */
    public NazvyType getNazvy() {
        return nazvy;
    }

    /**
     * Sets the value of the nazvy property.
     * 
     * @param value
     *     allowed object is
     *     {@link NazvyType }
     *     
     */
    public void setNazvy(NazvyType value) {
        this.nazvy = value;
    }

    /**
     * Gets the value of the datDv property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatDv() {
        return datDv;
    }

    /**
     * Sets the value of the datDv property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatDv(XMLGregorianCalendar value) {
        this.datDv = value;
    }

    /**
     * Gets the value of the vrdAny property.
     * 
     * @return
     *     possible object is
     *     {@link AnyType }
     *     
     */
    public AnyType getVrdAny() {
        return vrdAny;
    }

    /**
     * Sets the value of the vrdAny property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnyType }
     *     
     */
    public void setVrdAny(AnyType value) {
        this.vrdAny = value;
    }

}

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.03.30 at 07:50:19 odp. CEST 
//


package mzcr.cz.ns.dasta.ds4.ds_ip;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import mzcr.cz.ns.dasta.ds4.ds_type.AnyType;


/**
 * <p>Java class for sciType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sciType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sciAny" type="{urn:cz-mzcr:ns:dasta:ds4:ds_type}AnyType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id_sci_is" use="required" type="{urn:cz-mzcr:ns:dasta:ds4:ds_type}str15" />
 *       &lt;attribute name="klic_nclp" use="required" type="{urn:cz-mzcr:ns:dasta:ds4:ds_type}klic_nclpType" />
 *       &lt;attribute name="krok" use="required" type="{urn:cz-mzcr:ns:dasta:ds4:ds_type}number2d" />
 *       &lt;attribute name="prubeh" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="Z"/>
 *             &lt;enumeration value="P"/>
 *             &lt;enumeration value="K"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sciType", propOrder = {
    "sciAny"
})
public class SciType {

    protected AnyType sciAny;
    @XmlAttribute(name = "id_sci_is", required = true)
    protected String idSciIs;
    @XmlAttribute(name = "klic_nclp", required = true)
    protected String klicNclp;
    @XmlAttribute(required = true)
    protected BigInteger krok;
    @XmlAttribute(required = true)
    protected String prubeh;

    /**
     * Gets the value of the sciAny property.
     * 
     * @return
     *     possible object is
     *     {@link AnyType }
     *     
     */
    public AnyType getSciAny() {
        return sciAny;
    }

    /**
     * Sets the value of the sciAny property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnyType }
     *     
     */
    public void setSciAny(AnyType value) {
        this.sciAny = value;
    }

    /**
     * Gets the value of the idSciIs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdSciIs() {
        return idSciIs;
    }

    /**
     * Sets the value of the idSciIs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdSciIs(String value) {
        this.idSciIs = value;
    }

    /**
     * Gets the value of the klicNclp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKlicNclp() {
        return klicNclp;
    }

    /**
     * Sets the value of the klicNclp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKlicNclp(String value) {
        this.klicNclp = value;
    }

    /**
     * Gets the value of the krok property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getKrok() {
        return krok;
    }

    /**
     * Sets the value of the krok property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setKrok(BigInteger value) {
        this.krok = value;
    }

    /**
     * Gets the value of the prubeh property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrubeh() {
        return prubeh;
    }

    /**
     * Sets the value of the prubeh property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrubeh(String value) {
        this.prubeh = value;
    }

}

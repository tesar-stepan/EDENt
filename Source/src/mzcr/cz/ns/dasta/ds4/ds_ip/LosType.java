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
import javax.xml.bind.annotation.XmlType;
import mzcr.cz.ns.dasta.ds4.ds_cistype.LOPPP;
import mzcr.cz.ns.dasta.ds4.ds_type.AnyType;


/**
 * <p>Java class for losType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="losType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="losAny" type="{urn:cz-mzcr:ns:dasta:ds4:ds_type}AnyType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="prijeti" type="{urn:cz-mzcr:ns:dasta:ds4:ds_cistype}LOPPP" default="B" />
 *       &lt;attribute name="kolize" type="{urn:cz-mzcr:ns:dasta:ds4:ds_cistype}LOZRK" default="B" />
 *       &lt;attribute name="urg_vysled" type="{urn:cz-mzcr:ns:dasta:ds4:ds_cistype}LOZDV" default="B" />
 *       &lt;attribute name="rut_vysled" type="{urn:cz-mzcr:ns:dasta:ds4:ds_cistype}LOZDV" default="B" />
 *       &lt;attribute name="ext_vysled" type="{urn:cz-mzcr:ns:dasta:ds4:ds_cistype}LOZDVE" default="B" />
 *       &lt;attribute name="dodsest" type="{urn:cz-mzcr:ns:dasta:ds4:ds_cistype}LOZDSEC" default="B" />
 *       &lt;attribute name="dodform" type="{urn:cz-mzcr:ns:dasta:ds4:ds_cistype}LOZDFVEC" default="B" />
 *       &lt;attribute name="dodnadpis" type="{urn:cz-mzcr:ns:dasta:ds4:ds_cistype}LOZDN" default="B" />
 *       &lt;attribute name="dodskal" type="{urn:cz-mzcr:ns:dasta:ds4:ds_cistype}LOZDS" default="B" />
 *       &lt;attribute name="typskal" type="{urn:cz-mzcr:ns:dasta:ds4:ds_cistype}LOZTS" default="B" />
 *       &lt;attribute name="dodkoment" type="{urn:cz-mzcr:ns:dasta:ds4:ds_cistype}LOZDK" default="N" />
 *       &lt;attribute name="dodgraf" type="{urn:cz-mzcr:ns:dasta:ds4:ds_cistype}LOZDG" default="B" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "losType", propOrder = {
    "losAny"
})
public class LosType {

    protected AnyType losAny;
    @XmlAttribute
    protected LOPPP prijeti;
    @XmlAttribute
    protected String kolize;
    @XmlAttribute(name = "urg_vysled")
    protected String urgVysled;
    @XmlAttribute(name = "rut_vysled")
    protected String rutVysled;
    @XmlAttribute(name = "ext_vysled")
    protected String extVysled;
    @XmlAttribute
    protected String dodsest;
    @XmlAttribute
    protected String dodform;
    @XmlAttribute
    protected String dodnadpis;
    @XmlAttribute
    protected String dodskal;
    @XmlAttribute
    protected String typskal;
    @XmlAttribute
    protected String dodkoment;
    @XmlAttribute
    protected String dodgraf;

    /**
     * Gets the value of the losAny property.
     * 
     * @return
     *     possible object is
     *     {@link AnyType }
     *     
     */
    public AnyType getLosAny() {
        return losAny;
    }

    /**
     * Sets the value of the losAny property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnyType }
     *     
     */
    public void setLosAny(AnyType value) {
        this.losAny = value;
    }

    /**
     * Gets the value of the prijeti property.
     * 
     * @return
     *     possible object is
     *     {@link LOPPP }
     *     
     */
    public LOPPP getPrijeti() {
        if (prijeti == null) {
            return LOPPP.B;
        } else {
            return prijeti;
        }
    }

    /**
     * Sets the value of the prijeti property.
     * 
     * @param value
     *     allowed object is
     *     {@link LOPPP }
     *     
     */
    public void setPrijeti(LOPPP value) {
        this.prijeti = value;
    }

    /**
     * Gets the value of the kolize property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKolize() {
        if (kolize == null) {
            return "B";
        } else {
            return kolize;
        }
    }

    /**
     * Sets the value of the kolize property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKolize(String value) {
        this.kolize = value;
    }

    /**
     * Gets the value of the urgVysled property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrgVysled() {
        if (urgVysled == null) {
            return "B";
        } else {
            return urgVysled;
        }
    }

    /**
     * Sets the value of the urgVysled property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrgVysled(String value) {
        this.urgVysled = value;
    }

    /**
     * Gets the value of the rutVysled property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRutVysled() {
        if (rutVysled == null) {
            return "B";
        } else {
            return rutVysled;
        }
    }

    /**
     * Sets the value of the rutVysled property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRutVysled(String value) {
        this.rutVysled = value;
    }

    /**
     * Gets the value of the extVysled property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtVysled() {
        if (extVysled == null) {
            return "B";
        } else {
            return extVysled;
        }
    }

    /**
     * Sets the value of the extVysled property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtVysled(String value) {
        this.extVysled = value;
    }

    /**
     * Gets the value of the dodsest property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDodsest() {
        if (dodsest == null) {
            return "B";
        } else {
            return dodsest;
        }
    }

    /**
     * Sets the value of the dodsest property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDodsest(String value) {
        this.dodsest = value;
    }

    /**
     * Gets the value of the dodform property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDodform() {
        if (dodform == null) {
            return "B";
        } else {
            return dodform;
        }
    }

    /**
     * Sets the value of the dodform property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDodform(String value) {
        this.dodform = value;
    }

    /**
     * Gets the value of the dodnadpis property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDodnadpis() {
        if (dodnadpis == null) {
            return "B";
        } else {
            return dodnadpis;
        }
    }

    /**
     * Sets the value of the dodnadpis property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDodnadpis(String value) {
        this.dodnadpis = value;
    }

    /**
     * Gets the value of the dodskal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDodskal() {
        if (dodskal == null) {
            return "B";
        } else {
            return dodskal;
        }
    }

    /**
     * Sets the value of the dodskal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDodskal(String value) {
        this.dodskal = value;
    }

    /**
     * Gets the value of the typskal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypskal() {
        if (typskal == null) {
            return "B";
        } else {
            return typskal;
        }
    }

    /**
     * Sets the value of the typskal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypskal(String value) {
        this.typskal = value;
    }

    /**
     * Gets the value of the dodkoment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDodkoment() {
        if (dodkoment == null) {
            return "N";
        } else {
            return dodkoment;
        }
    }

    /**
     * Sets the value of the dodkoment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDodkoment(String value) {
        this.dodkoment = value;
    }

    /**
     * Gets the value of the dodgraf property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDodgraf() {
        if (dodgraf == null) {
            return "B";
        } else {
            return dodgraf;
        }
    }

    /**
     * Sets the value of the dodgraf property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDodgraf(String value) {
        this.dodgraf = value;
    }

}
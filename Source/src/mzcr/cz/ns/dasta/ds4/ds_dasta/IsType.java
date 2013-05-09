//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.03.30 at 07:50:19 odp. CEST 
//


package mzcr.cz.ns.dasta.ds4.ds_dasta;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import mzcr.cz.ns.dasta.ds4.ds_ip.Ip;
import mzcr.cz.ns.dasta.ds4.ds_type.AnyType;
import mzcr.cz.ns.dasta.ds4.ds_type.SrTypType;


/**
 * <p>Java class for isType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="isType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{urn:cz-mzcr:ns:dasta:ds4:ds_dasta}as"/>
 *         &lt;element name="os" type="{urn:cz-mzcr:ns:dasta:ds4:ds_dasta}osType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{urn:cz-mzcr:ns:dasta:ds4:ds_dasta}a" minOccurs="0"/>
 *         &lt;any namespace='##other'/>
 *         &lt;element name="isAny" type="{urn:cz-mzcr:ns:dasta:ds4:ds_type}AnyType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ico" type="{urn:cz-mzcr:ns:dasta:ds4:ds_type}icisType" />
 *       &lt;attribute name="icz" type="{urn:cz-mzcr:ns:dasta:ds4:ds_type}icisType" />
 *       &lt;attribute name="icp" type="{urn:cz-mzcr:ns:dasta:ds4:ds_type}icisType" />
 *       &lt;attribute name="icl" type="{urn:cz-mzcr:ns:dasta:ds4:ds_type}icisType" />
 *       &lt;attribute name="pcz" type="{urn:cz-mzcr:ns:dasta:ds4:ds_type}number3de" />
 *       &lt;attribute name="oddel" type="{urn:cz-mzcr:ns:dasta:ds4:ds_type}str5e" />
 *       &lt;attribute name="sr_typ" type="{urn:cz-mzcr:ns:dasta:ds4:ds_type}sr_typType" />
 *       &lt;attribute name="sr_pois" type="{urn:cz-mzcr:ns:dasta:ds4:ds_type}str4" />
 *       &lt;attribute name="sr_kod" type="{urn:cz-mzcr:ns:dasta:ds4:ds_type}str9" />
 *       &lt;attribute name="oavl" type="{urn:cz-mzcr:ns:dasta:ds4:ds_type}str11" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "isType", propOrder = {
    "as",
    "os",
    "a",
    "any",
    "isAny",
    "ip"
})
public class IsType {

    @XmlElement(required = true)
    protected AsType as;
    protected List<OsType> os;
    protected AType a;
    @XmlAnyElement(lax = true)
    protected Object any;
    protected AnyType isAny;
    @XmlAttribute
    protected String ico;
    @XmlAttribute
    protected String icz;
    @XmlAttribute
    protected String icp;
    @XmlAttribute
    protected String icl;
    @XmlAttribute
    protected String pcz;
    @XmlAttribute
    protected String oddel;
    @XmlElement(name = "ip", required = true)
    protected Ip ip;
    @XmlAttribute(name = "sr_typ")
    protected SrTypType srTyp;
    @XmlAttribute(name = "sr_pois")
    protected String srPois;
    @XmlAttribute(name = "sr_kod")
    protected String srKod;
    @XmlAttribute
    protected String oavl;

    /**
     * Gets the value of the as property.
     * 
     * @return
     *     possible object is
     *     {@link AsType }
     *     
     */
    public AsType getAs() {
        return as;
    }

    /**
     * Sets the value of the as property.
     * 
     * @param value
     *     allowed object is
     *     {@link AsType }
     *     
     */
    public void setAs(AsType value) {
        this.as = value;
    }

    /**
     * Sets the value of the as property.
     * 
     * @param ip
     *     allowed object is
     *     {@link Ip }
     *     
     */
    public void setIp(Ip ip) {
        this.ip = ip;
    }

    /**
     * Sets the value of the as property.
     * 
     * @param ip
     *     allowed object is
     *     {@link Ip }
     *     
     */
    public Ip getIp() {
        return ip;
    }
    
    /**
     * Gets the value of the os property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the os property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OsType }
     * 
     * 
     */
    public List<OsType> getOs() {
        if (os == null) {
            os = new ArrayList<OsType>();
        }
        return this.os;
    }

    /**
     * Gets the value of the a property.
     * 
     * @return
     *     possible object is
     *     {@link AType }
     *     
     */
    public AType getA() {
        return a;
    }

    /**
     * Sets the value of the a property.
     * 
     * @param value
     *     allowed object is
     *     {@link AType }
     *     
     */
    public void setA(AType value) {
        this.a = value;
    }

    /**
     * Gets the value of the any property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getAny() {
        return any;
    }

    /**
     * Sets the value of the any property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setAny(Object value) {
        this.any = value;
    }

    /**
     * Gets the value of the isAny property.
     * 
     * @return
     *     possible object is
     *     {@link AnyType }
     *     
     */
    public AnyType getIsAny() {
        return isAny;
    }

    /**
     * Sets the value of the isAny property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnyType }
     *     
     */
    public void setIsAny(AnyType value) {
        this.isAny = value;
    }

    /**
     * Gets the value of the ico property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIco() {
        return ico;
    }

    /**
     * Sets the value of the ico property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIco(String value) {
        this.ico = value;
    }

    /**
     * Gets the value of the icz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIcz() {
        return icz;
    }

    /**
     * Sets the value of the icz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIcz(String value) {
        this.icz = value;
    }

    /**
     * Gets the value of the icp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIcp() {
        return icp;
    }

    /**
     * Sets the value of the icp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIcp(String value) {
        this.icp = value;
    }

    /**
     * Gets the value of the icl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIcl() {
        return icl;
    }

    /**
     * Sets the value of the icl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIcl(String value) {
        this.icl = value;
    }

    /**
     * Gets the value of the pcz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPcz() {
        return pcz;
    }

    /**
     * Sets the value of the pcz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPcz(String value) {
        this.pcz = value;
    }

    /**
     * Gets the value of the oddel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOddel() {
        return oddel;
    }

    /**
     * Sets the value of the oddel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOddel(String value) {
        this.oddel = value;
    }

    /**
     * Gets the value of the srTyp property.
     * 
     * @return
     *     possible object is
     *     {@link SrTypType }
     *     
     */
    public SrTypType getSrTyp() {
        return srTyp;
    }

    /**
     * Sets the value of the srTyp property.
     * 
     * @param value
     *     allowed object is
     *     {@link SrTypType }
     *     
     */
    public void setSrTyp(SrTypType value) {
        this.srTyp = value;
    }

    /**
     * Gets the value of the srPois property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSrPois() {
        return srPois;
    }

    /**
     * Sets the value of the srPois property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSrPois(String value) {
        this.srPois = value;
    }

    /**
     * Gets the value of the srKod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSrKod() {
        return srKod;
    }

    /**
     * Sets the value of the srKod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSrKod(String value) {
        this.srKod = value;
    }

    /**
     * Gets the value of the oavl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOavl() {
        return oavl;
    }

    /**
     * Sets the value of the oavl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOavl(String value) {
        this.oavl = value;
    }

}

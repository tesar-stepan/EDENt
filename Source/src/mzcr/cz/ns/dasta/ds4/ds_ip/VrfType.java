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
 * <p>Java class for vrfType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="vrfType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nazvy" type="{urn:cz-mzcr:ns:dasta:ds4:ds_ip}nazvyType" minOccurs="0"/>
 *         &lt;element name="hodnota_kod" type="{urn:cz-mzcr:ns:dasta:ds4:ds_type}str8"/>
 *         &lt;element name="hodnota_text" type="{urn:cz-mzcr:ns:dasta:ds4:ds_type}str32" minOccurs="0"/>
 *         &lt;element name="pozn" type="{urn:cz-mzcr:ns:dasta:ds4:ds_type}str255" minOccurs="0"/>
 *         &lt;element name="vrfAny" type="{urn:cz-mzcr:ns:dasta:ds4:ds_type}AnyType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="s_hodn_ko" type="{urn:cz-mzcr:ns:dasta:ds4:ds_type}sHodnotaType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "vrfType", propOrder = {
    "nazvy",
    "hodnotaKod",
    "hodnotaText",
    "pozn",
    "vrfAny"
})
public class VrfType {

    protected NazvyType nazvy;
    @XmlElement(name = "hodnota_kod", required = true)
    protected String hodnotaKod;
    @XmlElement(name = "hodnota_text")
    protected String hodnotaText;
    protected String pozn;
    protected AnyType vrfAny;
    @XmlAttribute(name = "s_hodn_ko")
    protected Integer sHodnKo;

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
     * Gets the value of the hodnotaKod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHodnotaKod() {
        return hodnotaKod;
    }

    /**
     * Sets the value of the hodnotaKod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHodnotaKod(String value) {
        this.hodnotaKod = value;
    }

    /**
     * Gets the value of the hodnotaText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHodnotaText() {
        return hodnotaText;
    }

    /**
     * Sets the value of the hodnotaText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHodnotaText(String value) {
        this.hodnotaText = value;
    }

    /**
     * Gets the value of the pozn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPozn() {
        return pozn;
    }

    /**
     * Sets the value of the pozn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPozn(String value) {
        this.pozn = value;
    }

    /**
     * Gets the value of the vrfAny property.
     * 
     * @return
     *     possible object is
     *     {@link AnyType }
     *     
     */
    public AnyType getVrfAny() {
        return vrfAny;
    }

    /**
     * Sets the value of the vrfAny property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnyType }
     *     
     */
    public void setVrfAny(AnyType value) {
        this.vrfAny = value;
    }

    /**
     * Gets the value of the sHodnKo property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSHodnKo() {
        return sHodnKo;
    }

    /**
     * Sets the value of the sHodnKo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSHodnKo(Integer value) {
        this.sHodnKo = value;
    }

}

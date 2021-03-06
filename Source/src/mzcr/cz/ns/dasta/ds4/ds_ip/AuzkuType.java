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
import mzcr.cz.ns.dasta.ds4.ds_type.DatDuType;


/**
 * <p>Java class for auzkuType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="auzkuType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dat_du" type="{urn:cz-mzcr:ns:dasta:ds4:ds_type}dat_duType"/>
 *         &lt;choice>
 *           &lt;element name="vykon" type="{urn:cz-mzcr:ns:dasta:ds4:ds_ip}vykonType"/>
 *           &lt;element name="zum" type="{urn:cz-mzcr:ns:dasta:ds4:ds_ip}zumType"/>
 *         &lt;/choice>
 *         &lt;element name="p_pracoviste" type="{urn:cz-mzcr:ns:dasta:ds4:ds_ip}pracovisteType"/>
 *         &lt;element name="p_pracovnik" type="{urn:cz-mzcr:ns:dasta:ds4:ds_ip}pracovnikType" minOccurs="0"/>
 *         &lt;element name="pozn" type="{urn:cz-mzcr:ns:dasta:ds4:ds_type}str255" minOccurs="0"/>
 *         &lt;element name="auzkuAny" type="{urn:cz-mzcr:ns:dasta:ds4:ds_type}AnyType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="indikace" use="required" type="{urn:cz-mzcr:ns:dasta:ds4:ds_cistype}indikaceAUZType" />
 *       &lt;attribute name="doklad_typ" type="{urn:cz-mzcr:ns:dasta:ds4:ds_type}number2de" />
 *       &lt;attribute name="doklad_kod" type="{urn:cz-mzcr:ns:dasta:ds4:ds_type}str7" />
 *       &lt;attribute name="typpol_vz" use="required" type="{urn:cz-mzcr:ns:dasta:ds4:ds_type}polozkaType" />
 *       &lt;attribute name="diag" use="required" type="{urn:cz-mzcr:ns:dasta:ds4:ds_type}str5" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "auzkuType", propOrder = {
    "datDu",
    "vykon",
    "zum",
    "pPracoviste",
    "pPracovnik",
    "pozn",
    "auzkuAny"
})
public class AuzkuType {

    @XmlElement(name = "dat_du", required = true)
    protected DatDuType datDu;
    protected VykonType vykon;
    protected ZumType zum;
    @XmlElement(name = "p_pracoviste", required = true)
    protected PracovisteType pPracoviste;
    @XmlElement(name = "p_pracovnik")
    protected PracovnikType pPracovnik;
    protected String pozn;
    protected AnyType auzkuAny;
    @XmlAttribute(required = true)
    protected String indikace;
    @XmlAttribute(name = "doklad_typ")
    protected String dokladTyp;
    @XmlAttribute(name = "doklad_kod")
    protected String dokladKod;
    @XmlAttribute(name = "typpol_vz", required = true)
    protected String typpolVz;
    @XmlAttribute(required = true)
    protected String diag;

    /**
     * Gets the value of the datDu property.
     * 
     * @return
     *     possible object is
     *     {@link DatDuType }
     *     
     */
    public DatDuType getDatDu() {
        return datDu;
    }

    /**
     * Sets the value of the datDu property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatDuType }
     *     
     */
    public void setDatDu(DatDuType value) {
        this.datDu = value;
    }

    /**
     * Gets the value of the vykon property.
     * 
     * @return
     *     possible object is
     *     {@link VykonType }
     *     
     */
    public VykonType getVykon() {
        return vykon;
    }

    /**
     * Sets the value of the vykon property.
     * 
     * @param value
     *     allowed object is
     *     {@link VykonType }
     *     
     */
    public void setVykon(VykonType value) {
        this.vykon = value;
    }

    /**
     * Gets the value of the zum property.
     * 
     * @return
     *     possible object is
     *     {@link ZumType }
     *     
     */
    public ZumType getZum() {
        return zum;
    }

    /**
     * Sets the value of the zum property.
     * 
     * @param value
     *     allowed object is
     *     {@link ZumType }
     *     
     */
    public void setZum(ZumType value) {
        this.zum = value;
    }

    /**
     * Gets the value of the pPracoviste property.
     * 
     * @return
     *     possible object is
     *     {@link PracovisteType }
     *     
     */
    public PracovisteType getPPracoviste() {
        return pPracoviste;
    }

    /**
     * Sets the value of the pPracoviste property.
     * 
     * @param value
     *     allowed object is
     *     {@link PracovisteType }
     *     
     */
    public void setPPracoviste(PracovisteType value) {
        this.pPracoviste = value;
    }

    /**
     * Gets the value of the pPracovnik property.
     * 
     * @return
     *     possible object is
     *     {@link PracovnikType }
     *     
     */
    public PracovnikType getPPracovnik() {
        return pPracovnik;
    }

    /**
     * Sets the value of the pPracovnik property.
     * 
     * @param value
     *     allowed object is
     *     {@link PracovnikType }
     *     
     */
    public void setPPracovnik(PracovnikType value) {
        this.pPracovnik = value;
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
     * Gets the value of the auzkuAny property.
     * 
     * @return
     *     possible object is
     *     {@link AnyType }
     *     
     */
    public AnyType getAuzkuAny() {
        return auzkuAny;
    }

    /**
     * Sets the value of the auzkuAny property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnyType }
     *     
     */
    public void setAuzkuAny(AnyType value) {
        this.auzkuAny = value;
    }

    /**
     * Gets the value of the indikace property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndikace() {
        return indikace;
    }

    /**
     * Sets the value of the indikace property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndikace(String value) {
        this.indikace = value;
    }

    /**
     * Gets the value of the dokladTyp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDokladTyp() {
        return dokladTyp;
    }

    /**
     * Sets the value of the dokladTyp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDokladTyp(String value) {
        this.dokladTyp = value;
    }

    /**
     * Gets the value of the dokladKod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDokladKod() {
        return dokladKod;
    }

    /**
     * Sets the value of the dokladKod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDokladKod(String value) {
        this.dokladKod = value;
    }

    /**
     * Gets the value of the typpolVz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTyppolVz() {
        return typpolVz;
    }

    /**
     * Sets the value of the typpolVz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTyppolVz(String value) {
        this.typpolVz = value;
    }

    /**
     * Gets the value of the diag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiag() {
        return diag;
    }

    /**
     * Sets the value of the diag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiag(String value) {
        this.diag = value;
    }

}

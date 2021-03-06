//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.03.30 at 07:50:19 odp. CEST 
//


package mzcr.cz.ns.dasta.ds4.ds_ip;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import mzcr.cz.ns.dasta.ds4.ds_dasta.AType;
import mzcr.cz.ns.dasta.ds4.ds_type.AnyType;
import mzcr.cz.ns.dasta.ds4.ds_type.DatXxType;
import mzcr.cz.ns.dasta.ds4.ds_type.SexType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="rodcis" type="{urn:cz-mzcr:ns:dasta:ds4:ds_type}rodcisType" minOccurs="0"/>
 *         &lt;element name="jmeno" type="{urn:cz-mzcr:ns:dasta:ds4:ds_type}str24" minOccurs="0"/>
 *         &lt;element name="prijmeni" type="{urn:cz-mzcr:ns:dasta:ds4:ds_type}str35"/>
 *         &lt;element name="titul_pred" type="{urn:cz-mzcr:ns:dasta:ds4:ds_type}str35" minOccurs="0"/>
 *         &lt;element name="titul_za" type="{urn:cz-mzcr:ns:dasta:ds4:ds_type}str35" minOccurs="0"/>
 *         &lt;element name="dat_dn" type="{urn:cz-mzcr:ns:dasta:ds4:ds_type}dat_xxType" minOccurs="0"/>
 *         &lt;element name="dat_de" type="{urn:cz-mzcr:ns:dasta:ds4:ds_type}dat_xxType" minOccurs="0"/>
 *         &lt;element name="sex" type="{urn:cz-mzcr:ns:dasta:ds4:ds_type}sexType" minOccurs="0"/>
 *         &lt;element name="rod_prijm" type="{urn:cz-mzcr:ns:dasta:ds4:ds_type}str35" minOccurs="0"/>
 *         &lt;element name="jine_idu" type="{urn:cz-mzcr:ns:dasta:ds4:ds_type}str70" minOccurs="0"/>
 *         &lt;element name="ipi_o" type="{urn:cz-mzcr:ns:dasta:ds4:ds_ip}ipiType" minOccurs="0"/>
 *         &lt;element name="ipi_v" type="{urn:cz-mzcr:ns:dasta:ds4:ds_ip}ipiType" minOccurs="0"/>
 *         &lt;element ref="{urn:cz-mzcr:ns:dasta:ds4:ds_dasta}a" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="h" type="{urn:cz-mzcr:ns:dasta:ds4:ds_ip}hType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="pv" type="{urn:cz-mzcr:ns:dasta:ds4:ds_ip}pvType" minOccurs="0"/>
 *         &lt;element name="p" type="{urn:cz-mzcr:ns:dasta:ds4:ds_ip}pType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="n" type="{urn:cz-mzcr:ns:dasta:ds4:ds_ip}nType" minOccurs="0"/>
 *         &lt;element name="u" type="{urn:cz-mzcr:ns:dasta:ds4:ds_ip}uType" minOccurs="0"/>
 *         &lt;element name="an" type="{urn:cz-mzcr:ns:dasta:ds4:ds_ip}anType" minOccurs="0"/>
 *         &lt;element name="oc" type="{urn:cz-mzcr:ns:dasta:ds4:ds_ip}ocType" minOccurs="0"/>
 *         &lt;element name="dg" type="{urn:cz-mzcr:ns:dasta:ds4:ds_ip}dgType" minOccurs="0"/>
 *         &lt;element name="le" type="{urn:cz-mzcr:ns:dasta:ds4:ds_ip}leType" minOccurs="0"/>
 *         &lt;element name="lek" type="{urn:cz-mzcr:ns:dasta:ds4:ds_ip}lekType" minOccurs="0"/>
 *         &lt;element name="pn" type="{urn:cz-mzcr:ns:dasta:ds4:ds_ip}pnType" minOccurs="0"/>
 *         &lt;element name="ku" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ku_z" type="{urn:cz-mzcr:ns:dasta:ds4:ds_ip}ku_zType" maxOccurs="unbounded" minOccurs="0"/>
 *                   &lt;element name="ku_o" type="{urn:cz-mzcr:ns:dasta:ds4:ds_ip}ku_oType" maxOccurs="unbounded" minOccurs="0"/>
 *                   &lt;element name="kuAny" type="{urn:cz-mzcr:ns:dasta:ds4:ds_type}AnyType" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ipAny" type="{urn:cz-mzcr:ns:dasta:ds4:ds_type}AnyType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id_pac" use="required" type="{urn:cz-mzcr:ns:dasta:ds4:ds_type}rodcisType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "rodcis",
    "jmeno",
    "prijmeni",
    "titulPred",
    "titulZa",
    "datDn",
    "datDe",
    "sex",
    "rodPrijm",
    "jineIdu",
    "ipiO",
    "ipiV",
    "a",
    "h",
    "pv",
    "p",
    "n",
    "u",
    "an",
    "oc",
    "dg",
    "le",
    "lek",
    "pn",
    "ku",
    "ipAny"
})
@XmlRootElement(name = "ip")
public class Ip {

    protected String rodcis;
    protected String jmeno;
    @XmlElement(required = true)
    protected String prijmeni;
    @XmlElement(name = "titul_pred")
    protected String titulPred;
    @XmlElement(name = "titul_za")
    protected String titulZa;
    @XmlElement(name = "dat_dn")
    protected DatXxType datDn;
    @XmlElement(name = "dat_de")
    protected DatXxType datDe;
    protected SexType sex;
    @XmlElement(name = "rod_prijm")
    protected String rodPrijm;
    @XmlElement(name = "jine_idu")
    protected String jineIdu;
    @XmlElement(name = "ipi_o")
    protected IpiType ipiO;
    @XmlElement(name = "ipi_v")
    protected IpiType ipiV;
    @XmlElement(namespace = "urn:cz-mzcr:ns:dasta:ds4:ds_dasta")
    protected List<AType> a;
    protected List<HType> h;
    protected PvType pv;
    protected List<PType> p;
    protected NType n;
    protected UType u;
    protected AnType an;
    protected OcType oc;
    protected DgType dg;
    protected LeType le;
    protected LekType lek;
    protected PnType pn;
    protected Ip.Ku ku;
    protected AnyType ipAny;
    @XmlAttribute(name = "id_pac", required = true)
    protected String idPac;

    /**
     * Gets the value of the rodcis property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRodcis() {
        return rodcis;
    }

    /**
     * Sets the value of the rodcis property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRodcis(String value) {
        this.rodcis = value;
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
     * Gets the value of the datDn property.
     * 
     * @return
     *     possible object is
     *     {@link DatXxType }
     *     
     */
    public DatXxType getDatDn() {
        return datDn;
    }

    /**
     * Sets the value of the datDn property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatXxType }
     *     
     */
    public void setDatDn(DatXxType value) {
        this.datDn = value;
    }

    /**
     * Gets the value of the datDe property.
     * 
     * @return
     *     possible object is
     *     {@link DatXxType }
     *     
     */
    public DatXxType getDatDe() {
        return datDe;
    }

    /**
     * Sets the value of the datDe property.
     * 
     * @param value
     *     allowed object is
     *     {@link DatXxType }
     *     
     */
    public void setDatDe(DatXxType value) {
        this.datDe = value;
    }

    /**
     * Gets the value of the sex property.
     * 
     * @return
     *     possible object is
     *     {@link SexType }
     *     
     */
    public SexType getSex() {
        return sex;
    }

    /**
     * Sets the value of the sex property.
     * 
     * @param value
     *     allowed object is
     *     {@link SexType }
     *     
     */
    public void setSex(SexType value) {
        this.sex = value;
    }

    /**
     * Gets the value of the rodPrijm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRodPrijm() {
        return rodPrijm;
    }

    /**
     * Sets the value of the rodPrijm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRodPrijm(String value) {
        this.rodPrijm = value;
    }

    /**
     * Gets the value of the jineIdu property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJineIdu() {
        return jineIdu;
    }

    /**
     * Sets the value of the jineIdu property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJineIdu(String value) {
        this.jineIdu = value;
    }

    /**
     * Gets the value of the ipiO property.
     * 
     * @return
     *     possible object is
     *     {@link IpiType }
     *     
     */
    public IpiType getIpiO() {
        return ipiO;
    }

    /**
     * Sets the value of the ipiO property.
     * 
     * @param value
     *     allowed object is
     *     {@link IpiType }
     *     
     */
    public void setIpiO(IpiType value) {
        this.ipiO = value;
    }

    /**
     * Gets the value of the ipiV property.
     * 
     * @return
     *     possible object is
     *     {@link IpiType }
     *     
     */
    public IpiType getIpiV() {
        return ipiV;
    }

    /**
     * Sets the value of the ipiV property.
     * 
     * @param value
     *     allowed object is
     *     {@link IpiType }
     *     
     */
    public void setIpiV(IpiType value) {
        this.ipiV = value;
    }

    /**
     * Gets the value of the a property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the a property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getA().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AType }
     * 
     * 
     */
    public List<AType> getA() {
        if (a == null) {
            a = new ArrayList<AType>();
        }
        return this.a;
    }

    /**
     * Gets the value of the h property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the h property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getH().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link HType }
     * 
     * 
     */
    public List<HType> getH() {
        if (h == null) {
            h = new ArrayList<HType>();
        }
        return this.h;
    }

    /**
     * Gets the value of the pv property.
     * 
     * @return
     *     possible object is
     *     {@link PvType }
     *     
     */
    public PvType getPv() {
        return pv;
    }

    /**
     * Sets the value of the pv property.
     * 
     * @param value
     *     allowed object is
     *     {@link PvType }
     *     
     */
    public void setPv(PvType value) {
        this.pv = value;
    }

    /**
     * Gets the value of the p property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the p property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getP().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PType }
     * 
     * 
     */
    public List<PType> getP() {
        if (p == null) {
            p = new ArrayList<PType>();
        }
        return this.p;
    }

    /**
     * Gets the value of the n property.
     * 
     * @return
     *     possible object is
     *     {@link NType }
     *     
     */
    public NType getN() {
        return n;
    }

    /**
     * Sets the value of the n property.
     * 
     * @param value
     *     allowed object is
     *     {@link NType }
     *     
     */
    public void setN(NType value) {
        this.n = value;
    }

    /**
     * Gets the value of the u property.
     * 
     * @return
     *     possible object is
     *     {@link UType }
     *     
     */
    public UType getU() {
        return u;
    }

    /**
     * Sets the value of the u property.
     * 
     * @param value
     *     allowed object is
     *     {@link UType }
     *     
     */
    public void setU(UType value) {
        this.u = value;
    }

    /**
     * Gets the value of the an property.
     * 
     * @return
     *     possible object is
     *     {@link AnType }
     *     
     */
    public AnType getAn() {
        return an;
    }

    /**
     * Sets the value of the an property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnType }
     *     
     */
    public void setAn(AnType value) {
        this.an = value;
    }

    /**
     * Gets the value of the oc property.
     * 
     * @return
     *     possible object is
     *     {@link OcType }
     *     
     */
    public OcType getOc() {
        return oc;
    }

    /**
     * Sets the value of the oc property.
     * 
     * @param value
     *     allowed object is
     *     {@link OcType }
     *     
     */
    public void setOc(OcType value) {
        this.oc = value;
    }

    /**
     * Gets the value of the dg property.
     * 
     * @return
     *     possible object is
     *     {@link DgType }
     *     
     */
    public DgType getDg() {
        return dg;
    }

    /**
     * Sets the value of the dg property.
     * 
     * @param value
     *     allowed object is
     *     {@link DgType }
     *     
     */
    public void setDg(DgType value) {
        this.dg = value;
    }

    /**
     * Gets the value of the le property.
     * 
     * @return
     *     possible object is
     *     {@link LeType }
     *     
     */
    public LeType getLe() {
        return le;
    }

    /**
     * Sets the value of the le property.
     * 
     * @param value
     *     allowed object is
     *     {@link LeType }
     *     
     */
    public void setLe(LeType value) {
        this.le = value;
    }

    /**
     * Gets the value of the lek property.
     * 
     * @return
     *     possible object is
     *     {@link LekType }
     *     
     */
    public LekType getLek() {
        return lek;
    }

    /**
     * Sets the value of the lek property.
     * 
     * @param value
     *     allowed object is
     *     {@link LekType }
     *     
     */
    public void setLek(LekType value) {
        this.lek = value;
    }

    /**
     * Gets the value of the pn property.
     * 
     * @return
     *     possible object is
     *     {@link PnType }
     *     
     */
    public PnType getPn() {
        return pn;
    }

    /**
     * Sets the value of the pn property.
     * 
     * @param value
     *     allowed object is
     *     {@link PnType }
     *     
     */
    public void setPn(PnType value) {
        this.pn = value;
    }

    /**
     * Gets the value of the ku property.
     * 
     * @return
     *     possible object is
     *     {@link Ip.Ku }
     *     
     */
    public Ip.Ku getKu() {
        return ku;
    }

    /**
     * Sets the value of the ku property.
     * 
     * @param value
     *     allowed object is
     *     {@link Ip.Ku }
     *     
     */
    public void setKu(Ip.Ku value) {
        this.ku = value;
    }

    /**
     * Gets the value of the ipAny property.
     * 
     * @return
     *     possible object is
     *     {@link AnyType }
     *     
     */
    public AnyType getIpAny() {
        return ipAny;
    }

    /**
     * Sets the value of the ipAny property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnyType }
     *     
     */
    public void setIpAny(AnyType value) {
        this.ipAny = value;
    }

    /**
     * Gets the value of the idPac property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdPac() {
        return idPac;
    }

    /**
     * Sets the value of the idPac property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdPac(String value) {
        this.idPac = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="ku_z" type="{urn:cz-mzcr:ns:dasta:ds4:ds_ip}ku_zType" maxOccurs="unbounded" minOccurs="0"/>
     *         &lt;element name="ku_o" type="{urn:cz-mzcr:ns:dasta:ds4:ds_ip}ku_oType" maxOccurs="unbounded" minOccurs="0"/>
     *         &lt;element name="kuAny" type="{urn:cz-mzcr:ns:dasta:ds4:ds_type}AnyType" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "kuZ",
        "kuO",
        "kuAny"
    })
    public static class Ku {

        @XmlElement(name = "ku_z")
        protected List<KuZType> kuZ;
        @XmlElement(name = "ku_o")
        protected List<KuOType> kuO;
        protected AnyType kuAny;

        /**
         * Gets the value of the kuZ property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the kuZ property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getKuZ().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link KuZType }
         * 
         * 
         */
        public List<KuZType> getKuZ() {
            if (kuZ == null) {
                kuZ = new ArrayList<KuZType>();
            }
            return this.kuZ;
        }

        /**
         * Gets the value of the kuO property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the kuO property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getKuO().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link KuOType }
         * 
         * 
         */
        public List<KuOType> getKuO() {
            if (kuO == null) {
                kuO = new ArrayList<KuOType>();
            }
            return this.kuO;
        }

        /**
         * Gets the value of the kuAny property.
         * 
         * @return
         *     possible object is
         *     {@link AnyType }
         *     
         */
        public AnyType getKuAny() {
            return kuAny;
        }

        /**
         * Sets the value of the kuAny property.
         * 
         * @param value
         *     allowed object is
         *     {@link AnyType }
         *     
         */
        public void setKuAny(AnyType value) {
            this.kuAny = value;
        }

    }

}

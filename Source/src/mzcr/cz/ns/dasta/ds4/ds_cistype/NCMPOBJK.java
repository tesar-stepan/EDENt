//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.03.30 at 07:50:19 odp. CEST 
//


package mzcr.cz.ns.dasta.ds4.ds_cistype;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NCMPOBJK.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="NCMPOBJK">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="AUTOBJ"/>
 *     &lt;enumeration value="ATBKVANT"/>
 *     &lt;enumeration value="ATBKVALI"/>
 *     &lt;enumeration value="MBC "/>
 *     &lt;enumeration value="AUTOVAKC"/>
 *     &lt;enumeration value="KMENDOUR"/>
 *     &lt;enumeration value="STERAUTO"/>
 *     &lt;enumeration value="KONTSTER"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "NCMPOBJK", namespace = "urn:cz-mzcr:ns:dasta:ds4:ds_cistype")
@XmlEnum
public enum NCMPOBJK {

    AUTOBJ("AUTOBJ"),
    ATBKVANT("ATBKVANT"),
    ATBKVALI("ATBKVALI"),
    @XmlEnumValue("MBC ")
    MBC("MBC "),
    AUTOVAKC("AUTOVAKC"),
    KMENDOUR("KMENDOUR"),
    STERAUTO("STERAUTO"),
    KONTSTER("KONTSTER");
    private final String value;

    NCMPOBJK(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static NCMPOBJK fromValue(String v) {
        for (NCMPOBJK c: NCMPOBJK.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.view.utils;

import com.sun.xml.internal.bind.marshaller.NamespacePrefixMapper;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import mzcr.cz.ns.dasta.ds4.ds_dasta.*;
import mzcr.cz.ns.dasta.ds4.ds_ip.*;
import mzcr.cz.ns.dasta.ds4.ds_type.DatXxType;
import mzcr.cz.ns.dasta.ds4.ds_type.GarantDatType;


/**
 *
 * @author Michal Huptych
 */
public class Main {

    public static void main(String[] args) throws JAXBException {

        mzcr.cz.ns.dasta.ds4.ds_dasta.ObjectFactory factoryDasta = new mzcr.cz.ns.dasta.ds4.ds_dasta.ObjectFactory();
        mzcr.cz.ns.dasta.ds4.ds_type.ObjectFactory factoryDsType = new mzcr.cz.ns.dasta.ds4.ds_type.ObjectFactory();
        mzcr.cz.ns.dasta.ds4.ds_ip.ObjectFactory factoryDsIp = new mzcr.cz.ns.dasta.ds4.ds_ip.ObjectFactory();
        
        /**
         * Zakladni hlavicka
         */
        Dasta dasta = factoryDasta.createDasta();    
        dasta.setVerzeDs("04.06.08");
        dasta.setIdSoubor("MEDICALC_KK11115_2005-12-12T14:46:25");
        dasta.setBinPriloha("T");
        dasta.setUr("T");
        dasta.setTypOdesm("KK");
        
        /**
         * Informace o programu, ktery generoval zpravu
         */
        ZdrojIsType zdrojIsType = factoryDasta.createZdrojIsType();
        zdrojIsType.setKodFirmy("MEDICALC");
        zdrojIsType.setKodProg("WMEXP");
        zdrojIsType.setVerzeProg("2.2.3.8");        
         
        /**
         * 
         */
        PmType pm = factoryDasta.createPmType();
        AsType as1 = factoryDasta.createAsType();
        as1.setTyp("I");
        as1.setVnitrni("999");
        pm.setAs(as1);       
        
        /**
         * Informace o garantovi dat
         */
        GarantDatType garantDat = factoryDsType.createGarantDatType();
        garantDat.setIdGarant("450124145");
        garantDat.setOdbornost("801");
        garantDat.setValue("MUDr. Jmeno Prijmeni");
        
        /**
         * Informace o zdravotnickem zarizeni
         */
        IsType is = factoryDasta.createIsType();
        is.setIco("12345678");
        is.setIcz("44101000");
        is.setIcp("44101882");
        AsType as2 = factoryDasta.createAsType();
        as2.setVnitrni("801");
        is.setAs(as2);
        
        /**
         * Pacientska cast
         */
        Ip ip = factoryDsIp.createIp();
        ip.setIdPac("7601019998");
        ip.setRodcis("7601019998");
        ip.setJmeno("Jmeno");
        ip.setPrijmeni("Prijmeni");
        DatXxType datDn = new DatXxType();
        datDn.setFormat("D");
        datDn.setValue("1976-01-01");        
        ip.setDatDn(datDn);
        
        /**
         * Zde vytvorte klinickou udalost podle prikladu. Pozor ale, ze blok ku 
         * je tvoren nize jako seznam, takze rozmyslete od ktereho bloku budete
         * zacinat.
         */  
        Ip.Ku ipKu  = factoryDsIp.createIpKu();
        
        
        /**
         * Nyni je potreba navazat jednotlive bloky na hlavni tag dasta.
         */        
        /**
         * Nasledujici tri bloky si zkuste pridat sami
         */
        
        // pridani zdroje
        // pridani prijmoveho mista
        // pridani garanta
        
        
        /**
         * Blok ku muze obsahovat vetsi pocet klinickych udalosti (reprezentovnych kuZType),
         * proto je potreba tento blok definovat v poli (rep. ArrayListu)
         */
        ArrayList<IsType> isTypes = new ArrayList<>();      
//        Ip.Ku ipKu = factoryDsIp.createIpKu(); // trida Ku je soucasti tridy Ip
        List<KuZType> kuZTypes = ipKu.getKuZ(); // Je vyvoren seznam klinickych udalosti, ktery je soucasti Ip.Ku
        // zde je potreba do seznamu kuZTypes pridat vytvorenou klinickou udalost
        // a pridat blok klinicke udalosti ipKu do bloku pacienta        
        is.setIp(ip);  //  krok pridani bloku pacienta do bloku odesilatele             
        isTypes.add(is);  // Pro pripad, ze by zprava obsahovale vice udalosti je halvni blok is zarazen do seznamu
        dasta.setIs(isTypes); // prirazeni cele zpravy do obalu bloku datsa              

        /**
         * Vytvori xml z hlavniho objektu dasta
         */
        JAXBContext context = JAXBContext.newInstance(Dasta.class);
        JAXBElement<Dasta> element = factoryDasta.createExpenseReport(dasta);        
        Marshaller marshaller = context.createMarshaller();        
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);    
        /**
         * Namapuje elementy jednotlivych namespace na nami pozadovane nazvy
         */
        marshaller.setProperty("com.sun.xml.internal.bind.namespacePrefixMapper", new NamespacePrefixMapper() {

            @Override
            public String[] getPreDeclaredNamespaceUris() {
                return null; //new String[]{WellKnownNamespace.XML_SCHEMA_INSTANCE};
            }

            @Override
            public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {                
                if (namespaceUri.equals("urn:cz-mzcr:ns:dasta:ds4:ds_dasta")) {
                    return "ds";
                }
                if (namespaceUri.equals("urn:cz-mzcr:ns:dasta:ds4:ds_ip")) {
                    return "dsip";
                }
                return suggestion;

            }
        });
        
        /**
         * Vypis xml zpavy
         */
        marshaller.marshal(element, System.out);
        
        /**
         * A vy si zkuste zapis do souboru.
         */ 
    }
}

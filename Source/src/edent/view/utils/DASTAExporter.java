/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edent.view.utils;

import com.sun.xml.internal.bind.marshaller.NamespacePrefixMapper;
import edent.model.Diagnosis;
import edent.model.Patient;
import edent.model.Tooth;
import edent.model.User;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import mzcr.cz.ns.dasta.ds4.ds_dasta.AsType;
import mzcr.cz.ns.dasta.ds4.ds_dasta.Dasta;
import mzcr.cz.ns.dasta.ds4.ds_dasta.IsType;
import mzcr.cz.ns.dasta.ds4.ds_dasta.PmType;
import mzcr.cz.ns.dasta.ds4.ds_dasta.ZdrojIsType;
import mzcr.cz.ns.dasta.ds4.ds_ip.Ip;
import mzcr.cz.ns.dasta.ds4.ds_ip.KuZType;
import mzcr.cz.ns.dasta.ds4.ds_ip.PracovnikType;
import mzcr.cz.ns.dasta.ds4.ds_ip.TextType;
import mzcr.cz.ns.dasta.ds4.ds_type.DatXxType;
import mzcr.cz.ns.dasta.ds4.ds_type.GarantDatType;

/**
 *
 * @author Michal Huptych
 * @author Stepan Tesar
 */
public class DASTAExporter {

    private static final String CPN_NAME = "CVUT_FEL";
    private static final String SYS_NAME = "EDENt";
    private static final String SYS_VERSION = "0.1";
    private static final String FILE_ID_PREFIX = "EDENT_REPORT";
    private static final String TOOTH = "zub";
    private static final String GUM = "dasen";
    private static final String TOP = "nahoře";
    private static final String BOT = "dole";
    private static final String LEFT = "vlevo";
    private static final String RIGHT = "vpravo";

    public static String generateXML(List<Diagnosis> diags, User logged) throws JAXBException, IOException {
        if(diags==null||diags.isEmpty()||logged==null){
            return null;
        }
        mzcr.cz.ns.dasta.ds4.ds_dasta.ObjectFactory factoryDasta = new mzcr.cz.ns.dasta.ds4.ds_dasta.ObjectFactory();
        mzcr.cz.ns.dasta.ds4.ds_type.ObjectFactory factoryDsType = new mzcr.cz.ns.dasta.ds4.ds_type.ObjectFactory();
        mzcr.cz.ns.dasta.ds4.ds_ip.ObjectFactory factoryDsIp = new mzcr.cz.ns.dasta.ds4.ds_ip.ObjectFactory();
        
        Collections.sort(diags);
        Collections.reverse(diags);

        /**
         * Zakladni hlavicka
         */
        String p = "";
        if(diags.get(0).getOrigAppointment().getPatient()!=null){
            p = "_"+diags.get(0).getOrigAppointment().getPatient().getSname()+"_"+diags.get(0).getOrigAppointment().getPatient().getFname();
        }
        String fileId = FILE_ID_PREFIX + p + "_" + TimeFormatter.getXMLIdDate() + "T" + TimeFormatter.getXMLIdTime();
        Dasta dasta = factoryDasta.createDasta();
        dasta.setVerzeDs(TimeFormatter.getXMLDate());
        dasta.setIdSoubor(fileId);
        dasta.setBinPriloha("T");
        dasta.setUr("T");
        dasta.setTypOdesm("KK");

        /**
         * Informace o programu, ktery generoval zpravu
         */
        ZdrojIsType zdrojIsType = factoryDasta.createZdrojIsType();
        zdrojIsType.setKodFirmy(CPN_NAME);
        zdrojIsType.setKodProg(SYS_NAME);
        zdrojIsType.setVerzeProg(SYS_VERSION);

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
        if (logged != null) {
            User doctor = logged;
            garantDat.setIdGarant("" + doctor.getId());
            garantDat.setValue(doctor.getTitlePre() + " " + doctor.getFname() + " " + doctor.getSname() + " " + doctor.getTitlePos());
        }

        dasta.setGarantDat(garantDat);

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
        if (diags.get(0) != null && diags.get(0).getOrigAppointment().getPatient() != null) {
            Patient patient = diags.get(0).getOrigAppointment().getPatient();
            ip.setIdPac(patient.getBirthnum());
            ip.setRodcis(patient.getBirthnum());
            ip.setJmeno(patient.getFname());
            ip.setPrijmeni(patient.getSname());
            DatXxType datDn = new DatXxType();
            datDn.setFormat("D");
            datDn.setValue(TimeFormatter.getXMLPatientBdate(patient.getBirthdate()));
            ip.setDatDn(datDn);
        }
        /**
         * Zde vytvorte klinickou udalost podle prikladu. Pozor ale, ze blok ku
         * je tvoren nize jako seznam, takze rozmyslete od ktereho bloku budete
         * zacinat.
         */
        /**
         * Blok ku muze obsahovat vetsi pocet klinickych udalosti
         * (reprezentovnych kuZType), proto je potreba tento blok definovat v
         * poli (rep. ArrayListu)
         */
        ArrayList<IsType> isTypes = new ArrayList<>();
        Ip.Ku ipKu = factoryDsIp.createIpKu(); // trida Ku je soucasti tridy Ip
        List<KuZType> kuZTypes = ipKu.getKuZ(); // Je vyvoren seznam klinickych udalosti, ktery je soucasti Ip.Ku

        for (Diagnosis diag : diags) {
            KuZType k = factoryDsIp.createKuZType();
            k.setTypku("I");
            if (diag != null) {
                TextType tt = factoryDsIp.createTextType();
                tt.setKtext(diag.getText());
                k.setText(tt);

                TextType ttx = factoryDsIp.createTextType();
                String txt = "";

                if (diag.getHistory().getTooth() != null) {
                    Tooth t = diag.getHistory().getTooth();

                    if (t.getState() != null) {
                        txt += TOOTH + ": " + t.getState().getName();
                    }
                    if (t.getGum() != null && t.getGum().getState() != null) {
                        txt += " " + GUM + ": " + t.getGum().getState().getName();
                    }
                    
                    String pos = t.getPosition() + " " + (t.isLeftSide() ? LEFT : RIGHT) + " " + (t.isTopFloor() ? TOP : BOT);
                    k.setTypkuspeclok(pos);
                }

                ttx.setKtext(txt);
                k.setTextZaver(ttx);

                DatXxType xx = factoryDsType.createDatXxType();
                xx.setFormat("DT");
                xx.setValue(TimeFormatter.getAppointmentDate(diag.getDate()));
                k.setDatVydani(xx);
            }

            PracovnikType pt = factoryDsIp.createPracovnikType();

            if (diag != null && diag.getCreator() != null) {
                User writer = diag.getCreator();
                pt.setJmeno(writer.getFname());
                pt.setPrijmeni(writer.getSname());
                pt.setTitulPred(writer.getTitlePre());
                pt.setTitulZa(writer.getTitlePos());
                k.setPPracovnik(pt);
            }

            kuZTypes.add(k);
        }

        // zde je potreba do seznamu kuZTypes pridat vytvorenou klinickou udalost
        // a pridat blok klinicke udalosti ipKu do bloku pacienta        

        ip.setKu(ipKu);
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
        File f = new File("."+File.separator+fileId+".xml");
        f.createNewFile();
        marshaller.marshal(element, f);

        return fileId;
        
        /**
         * A vy si zkuste zapis do souboru.
         */
    }
}

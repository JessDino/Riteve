/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XML;

import MVC.Modelo.Revisiones;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 *
 * @author usuario
 */
public class CrearXML {

    private Revisiones[] revi;

    public CrearXML(Revisiones[] revi) {
        this.revi = revi;
    }

    public boolean crear() {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        String nombre = "";
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation imple = builder.getDOMImplementation();
            Document doc = imple.createDocument(null, "REVISIONES", null);
            doc.setXmlVersion("1.0");

            Element raiz = doc.getDocumentElement();

            for (Revisiones revi : revi) {
                Element revis = doc.createElement("REVISION");

                Element id = doc.createElement("ID_REVISION");
                Text nodeId = doc.createTextNode(String.valueOf(revi.getIdRevision()));
                id.appendChild(nodeId);

                Element fe = doc.createElement("FECHA");
                Text nodeFe = doc.createTextNode(revi.getFecha().toString());
                fe.appendChild(nodeFe);

                Element ho = doc.createElement("HORA");
                Text nodeHo = doc.createTextNode(revi.getHora().toString());
                ho.appendChild(nodeHo);

                Element tec = doc.createElement("TECNICO");
                Text nodeTec = doc.createTextNode(String.valueOf(revi.getTecnico().getCedula()));
                tec.appendChild(nodeTec);

                Element tipo = doc.createElement("TIPO_REVISION");
                Text nodeTipo = doc.createTextNode(revi.getTipoRevision());
                tipo.appendChild(nodeTipo);

                Element ob = doc.createElement("OBSERVACIONES");
                Text nodeOb = doc.createTextNode(revi.getObservacion());
                ob.appendChild(nodeOb);

                Element es = doc.createElement("ESTADO");
                Text nodeEs = doc.createTextNode(revi.getEstado());
                es.appendChild(nodeEs);

                Element ve = doc.createElement("VEHICULO");
                Text nodeVe = doc.createTextNode(String.valueOf(revi.getVehiculo().getNumeroDePlaca()));
                ve.appendChild(nodeVe);

                revis.appendChild(id);
                revis.appendChild(fe);
                revis.appendChild(ho);
                revis.appendChild(tec);
                revis.appendChild(tipo);
                revis.appendChild(ob);
                revis.appendChild(es);
                revis.appendChild(ve);
                raiz.appendChild(revis);
                nombre = String.valueOf(revi.getVehiculo().getNumeroDePlaca());
            }
            Source sou = new DOMSource(doc);
            Result resul = new StreamResult(new java.io.File("C:\\Users\\dinar\\OneDrive\\Documentos\\guardadosXML", nombre + ".xml"));
            Transformer trans = TransformerFactory.newInstance().newTransformer();
            trans.transform(sou, resul);
            return true;

        } catch (ParserConfigurationException | TransformerException ex) {

            Logger.getLogger(CrearXML.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
}

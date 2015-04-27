package pl.edu.agh.miss.OSM;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by slonka on 27.04.15.
 */
public class OSMUtils {

    /**
     * @param xmlDocument
     * @return a list of openseamap nodes extracted from xml
     */
    @SuppressWarnings("nls")
    public static List<OSMNode> getNodes(Document xmlDocument) {
        List<OSMNode> osmNodes = new ArrayList<OSMNode>();

        Node osmRoot = xmlDocument.getFirstChild();
        NodeList osmXMLNodes = osmRoot.getChildNodes();
        for (int i = 1; i < osmXMLNodes.getLength(); i++) {
            Node item = osmXMLNodes.item(i);
            if (item.getNodeName().equals("node")) {
                NamedNodeMap attributes = item.getAttributes();
                Map<String, String> tags = getTags(item);
                Node namedItemID = attributes.getNamedItem("id");
                Node namedItemLat = attributes.getNamedItem("lat");
                Node namedItemLon = attributes.getNamedItem("lon");
                Node namedItemVersion = attributes.getNamedItem("version");

                String id = namedItemID.getNodeValue();
                String latitude = namedItemLat.getNodeValue();
                String longitude = namedItemLon.getNodeValue();
                String version = "0";
                if (namedItemVersion != null) {
                    version = namedItemVersion.getNodeValue();
                }

                osmNodes.add(new OSMNode(id, latitude, longitude, version, tags));
            }

        }
        return osmNodes;
    }
    /**
     * @param xmlDocument
     * @return a list of openseamap nodes extracted from xml
     */
    @SuppressWarnings("nls")
    public static List<OSMWay> getWaysWithNodes(Document xmlDocument) {
        Map<String, OSMNode> osmNodes = new HashMap<String, OSMNode>();
        List<OSMWay> osmWays = new ArrayList<OSMWay>();

        Node osmRoot = xmlDocument.getFirstChild();
        NodeList osmXMLNodes = osmRoot.getChildNodes();
        for (int i = 1; i < osmXMLNodes.getLength(); i++) {
            Node item = osmXMLNodes.item(i);
            if (item.getNodeName().equals("node")) {
                Map<String, String> tags = getTags(item);
                NamedNodeMap attributes = item.getAttributes();

                String id = attributes.getNamedItem("id").getNodeValue();
                String latitude = attributes.getNamedItem("lat").getNodeValue();
                String longitude = attributes.getNamedItem("lon").getNodeValue();
                String version = "0";
                if (attributes.getNamedItem("version") != null) {
                    version = attributes.getNamedItem("version").getNodeValue();
                }

                osmNodes.put(id, new OSMNode(id, latitude, longitude, version, tags));
            } else if (item.getNodeName().equals("way")){
                NodeList nodeList = item.getChildNodes();
                OSMWay way = new OSMWay();
                for(int j = 1; j < nodeList.getLength(); j++) {
                    Node osmNode = nodeList.item(j);
                    if(osmNode.getNodeType() == Node.ELEMENT_NODE) {
                        String nodeId = osmNode.getAttributes().getNamedItem("ref").getNodeValue();
                        OSMNode node = osmNodes.get(nodeId);
                        way.nodes.add(node);
                    }
                }
                osmWays.add(way);
            }
        }
        return osmWays;
    }

    private static Map<String, String> getTags(Node item) {
        NodeList tagXMLNodes = item.getChildNodes();
        Map<String, String> tags = new HashMap<String, String>();
        for (int j = 1; j < tagXMLNodes.getLength(); j++) {
            Node tagItem = tagXMLNodes.item(j);
            NamedNodeMap tagAttributes = tagItem.getAttributes();
            if (tagAttributes != null) {
                tags.put(tagAttributes.getNamedItem("k").getNodeValue(), tagAttributes.getNamedItem("v")
                        .getNodeValue());
            }
        }
        return tags;
    }

    public static Document getXMLFile(String location) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
        return docBuilder.parse(location);
    }


    /**
     * @param filePath
     * @return
     * @throws java.io.IOException
     */
    public static String readFileAsString(String filePath) throws java.io.IOException {
        StringBuffer fileData = new StringBuffer(1000);
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        char[] buf = new char[1024];
        int numRead = 0;
        while ((numRead = reader.read(buf)) != -1) {
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
            buf = new char[1024];
        }
        reader.close();
        return fileData.toString();
    }
}

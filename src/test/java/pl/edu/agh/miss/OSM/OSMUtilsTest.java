package pl.edu.agh.miss.OSM;

import org.junit.Assert;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.List;

import static pl.edu.agh.miss.OSM.OSMUtils.getWaysWithNodes;

/**
 * Created by slonka on 27.04.15.
 */
public class OSMUtilsTest {


    @org.junit.Test
    public void testGetWaysWithNodes() throws Exception {
        DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
        Document document = docBuilder.parse(this.getClass().getResourceAsStream("/test.xml"));
        List<OSMWay> waysWithNodes = getWaysWithNodes(document);
        Assert.assertEquals(waysWithNodes.size(), 1);
    }
}
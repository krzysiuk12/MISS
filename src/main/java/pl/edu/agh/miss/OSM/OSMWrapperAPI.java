package pl.edu.agh.miss.OSM;

/**
 * Created by slonka on 27.04.15.
 *
 * (c) Jens Kübler
 * This software is public domain
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * (c) Jens Kübler
 * This software is public domain
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

import static pl.edu.agh.miss.OSM.OSMUtils.getNodes;
import static pl.edu.agh.miss.OSM.OSMUtils.readFileAsString;

/**
 *
 */
public class OSMWrapperAPI {

    private static final String OVERPASS_API = "http://www.overpass-api.de/api/interpreter";
    private static final String OPENSTREETMAP_API_06 = "http://www.openstreetmap.org/api/0.6/";

    public static OSMNode getNode(String nodeId) throws IOException, ParserConfigurationException, SAXException {
        String string = "http://www.openstreetmap.org/api/0.6/node/" + nodeId;
        URL osm = new URL(string);
        HttpURLConnection connection = (HttpURLConnection) osm.openConnection();

        DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
        Document document = docBuilder.parse(connection.getInputStream());
        List<OSMNode> nodes = getNodes(document);
        if (!nodes.isEmpty()) {
            return nodes.iterator().next();
        }
        return null;
    }

    /**
     *
     * @param lon the longitude
     * @param lat the latitude
     * @param vicinityRange bounding box in this range
     * @return the xml document containing the queries nodes
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    @SuppressWarnings("nls")
    private static Document getXML(double lon, double lat, double vicinityRange) throws IOException, SAXException,
            ParserConfigurationException {

        DecimalFormat format = new DecimalFormat("##0.0000000", DecimalFormatSymbols.getInstance(Locale.ENGLISH)); //$NON-NLS-1$
        String left = format.format(lat - vicinityRange);
        String bottom = format.format(lon - vicinityRange);
        String right = format.format(lat + vicinityRange);
        String top = format.format(lon + vicinityRange);

        String string = OPENSTREETMAP_API_06 + "map?bbox=" + left + "," + bottom + "," + right + ","
                + top;
        URL osm = new URL(string);
        HttpURLConnection connection = (HttpURLConnection) osm.openConnection();

        DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
        return docBuilder.parse(connection.getInputStream());
    }


    public static List<OSMNode> getOSMNodesInVicinity(double lat, double lon, double vicinityRange) throws IOException,
            SAXException, ParserConfigurationException {
        return getNodes(getXML(lon, lat, vicinityRange));
    }

    /**
     *
     * @param query the overpass query
     * @return the nodes in the formulated query
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public static Document getNodesViaOverpass(String query) throws IOException, ParserConfigurationException, SAXException {
        String hostname = OVERPASS_API;
        String queryString = readFileAsString(query);

        URL osm = new URL(hostname);
        HttpURLConnection connection = (HttpURLConnection) osm.openConnection();
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        DataOutputStream printout = new DataOutputStream(connection.getOutputStream());
        printout.writeBytes("data=" + URLEncoder.encode(queryString, "utf-8"));
        printout.flush();
        printout.close();

        DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
        return docBuilder.parse(connection.getInputStream());
    }


    /**
     * main method that simply reads some nodes
     *
     * @param args
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     */
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        List<OSMNode> osmNodesInVicinity = getOSMNodesInVicinity(49, 8.3, 0.005);
        for (OSMNode osmNode : osmNodesInVicinity) {
            System.out.println(osmNode.getId() + ":" + osmNode.getLatitude() + ":" + osmNode.getLongitude());
        }
    }

}

package pl.edu.agh.miss.importer;

import java.io.File;

/**
 * Created by slonka on 27.04.15.
 */
public class OsmImporter {

    public static void main(String args[]) {



        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();

            File fXmlFile = new File(classloader.getResource("small_map.osm").getFile());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

package pl.edu.agh.miss.OSM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by slonka on 27.04.15.
 */
public class OSMWay {
    List nodes = new ArrayList<OSMNode>();

    Map tags = new HashMap<String, String>();

    public Map getTags() {
        return tags;
    }

    public void setTags(Map tags) {
        this.tags = tags;
    }

    public List getNodes() {
        return nodes;
    }

    public void setNodes(List nodes) {
        this.nodes = nodes;
    }

}

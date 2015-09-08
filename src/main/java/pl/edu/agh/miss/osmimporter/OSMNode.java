package pl.edu.agh.miss.osmimporter;

import java.util.Map;

/**
 * Created by slonka on 27.04.15.
 */
public class OSMNode {

    private String id;
    private String latitude;
    private String longitude;
    private final Map<String, String> tags;
    private String version;

    public OSMNode(String id, String latitude, String longitude, String version, Map<String, String> tags) {

        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.tags = tags;
        this.version = version;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}

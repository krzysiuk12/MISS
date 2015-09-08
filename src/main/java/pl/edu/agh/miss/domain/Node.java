package pl.edu.agh.miss.domain;

/**
 * Created by Krzysztof Kicinger on 2015-05-13.
 */
public class Node {

    private long osmId;
    private String name;
    private double latitude;
    private double longitude;

    public Node() {
    }

    public Node(long osmId) {
        this.osmId = osmId;
    }

    public Node(long osmId, double latitude, double longitude) {
        this.osmId = osmId;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Node(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Node(long osmId, String name, double latitude, double longitude) {
        this.osmId = osmId;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public long getOsmId() {
        return osmId;
    }

    public void setOsmId(long osmId) {
        this.osmId = osmId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (Double.compare(node.latitude, latitude) != 0) return false;
        if (Double.compare(node.longitude, longitude) != 0) return false;
        if (osmId != node.osmId) return false;
        if (name != null && node.name != null && !name.equals(node.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (osmId ^ (osmId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        temp = Double.doubleToLongBits(latitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Node{" +
                "osmId=" + osmId +
                ", name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}

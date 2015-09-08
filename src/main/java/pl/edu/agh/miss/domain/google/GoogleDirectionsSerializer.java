package pl.edu.agh.miss.domain.google;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by krzysztofkicinger on 9/8/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleDirectionsSerializer {

    private List<GoogleDirectionsRoute> routes;
    private GoogleDirectionsStatus status;

    public GoogleDirectionsSerializer() {
    }

    public GoogleDirectionsSerializer(List<GoogleDirectionsRoute> routes, GoogleDirectionsStatus status) {
        this.routes = routes;
        this.status = status;
    }

    public List<GoogleDirectionsRoute> getRoutes() {
        return routes;
    }

    public void setRoutes(List<GoogleDirectionsRoute> routes) {
        this.routes = routes;
    }

    public GoogleDirectionsStatus getStatus() {
        return status;
    }

    public void setStatus(GoogleDirectionsStatus status) {
        this.status = status;
    }
}

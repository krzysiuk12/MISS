package pl.edu.agh.miss.background;

import org.springframework.web.client.RestTemplate;
import pl.edu.agh.miss.domain.Node;
import pl.edu.agh.miss.domain.google.DistanceUnit;
import pl.edu.agh.miss.domain.google.GoogleDirectionsSerializer;
import pl.edu.agh.miss.domain.google.TravelMode;

import java.util.List;

/**
 * Created by krzysztofkicinger on 9/8/15.
 */
public class GoogleDirectionsService {

    public GoogleDirectionsSerializer getPathDescription(Node origin, Node destination, TravelMode mode, DistanceUnit unit, List<Node> waypoints) throws Exception {
        try {
            GoogleDirectionsApiPathBuilder pathBuilder = new GoogleDirectionsApiPathBuilder(origin.getLatitude(), origin.getLongitude(), destination.getLatitude(), destination.getLongitude());
            if (mode != null) {
                pathBuilder.addModeParam(mode);
            }
            if (unit != null) {
                pathBuilder.addUnitParam(unit);
            }
            if (waypoints != null && !waypoints.isEmpty()) {
                pathBuilder.addWaypointsParam(waypoints);
            }
            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.getForObject(pathBuilder.build(), GoogleDirectionsSerializer.class);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

}

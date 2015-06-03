package pl.edu.agh.miss.gis;

import pl.edu.agh.miss.simulation.ITrafficSimulation;

/**
 * Created by slonka on 03.06.15.
 */
public class TrafficSimulationGis implements ITrafficSimulation {
    int iteration = 0;

    public TrafficSimulationGis() {
        JavaGisDao.setCost(2225L, 1.0);
    }

    @Override
    public void simulateTraffic() {

    }

    @Override
    public void simulateAccident() {
        if(iteration == 1) {
            JavaGisDao.setCost(2225L, 1000.0);
        }
        iteration++;
    }
}

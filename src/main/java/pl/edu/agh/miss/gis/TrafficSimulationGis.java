package pl.edu.agh.miss.gis;

import pl.edu.agh.miss.domain.way.Way;
import pl.edu.agh.miss.simulation.ITrafficSimulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by slonka on 03.06.15.
 */
public class TrafficSimulationGis implements ITrafficSimulation {

    private Map<Integer, List<Way>> trafficSimulationMap;
    private Map<Integer, List<Way>> accidentsSimulationMap;

    public TrafficSimulationGis(Map<Integer, List<Way>> trafficSimulationMap, Map<Integer, List<Way>> accidentsSimulationMap) {
        this.trafficSimulationMap = trafficSimulationMap;
        this.accidentsSimulationMap = accidentsSimulationMap;
    }



    @Override
    public void simulateTraffic(int currentIteration) {
        if(trafficSimulationMap != null && !trafficSimulationMap.isEmpty() && trafficSimulationMap.get(currentIteration) != null) {
            List<Way> updatedWays = trafficSimulationMap.get(currentIteration);
            updatedWays.forEach((way) -> JavaGisDao.setCost(way.getOsmId(), way.getWeight().getWeight()));
        }
    }

    @Override
    public void simulateAccident(int currentIteration) {
        if(accidentsSimulationMap != null && !accidentsSimulationMap.isEmpty() && accidentsSimulationMap.get(currentIteration) != null) {
            List<Way> ways = accidentsSimulationMap.get(currentIteration);
            ways.forEach((way) -> JavaGisDao.setCost(way.getOsmId(), Integer.MAX_VALUE));
        }
    }

}

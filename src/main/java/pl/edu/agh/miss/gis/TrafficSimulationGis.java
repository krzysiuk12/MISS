package pl.edu.agh.miss.gis;

import pl.edu.agh.miss.simulation.ITrafficSimulation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by slonka on 03.06.15.
 */
public class TrafficSimulationGis implements ITrafficSimulation {
    int iteration = 0;
    List<Long> roadsToSimulateAccident = new ArrayList<>();

    public TrafficSimulationGis() {
        roadsToSimulateAccident.add(2225L);
        roadsToSimulateAccident.add(1699L);
        roadsToSimulateAccident.add(1684L);
        roadsToSimulateAccident.add(1340L);

        for(Long gid : roadsToSimulateAccident) {
            JavaGisDao.setCost(gid, 1.0);
        }
    }

    @Override
    public void simulateTraffic() {

    }

    /*
    on route from 103 to 958 we have:
  853
  854
 1699
 1700
 1701
 1702
 1703
 1684
 1685
 1686
 1687
 1689
 1690
  423
 1692
  224
 1473
  616
 1491
 1492
 1493
 1494

 when costs are 1.0

     */

    @Override
    public void simulateAccident() {
        processIteration();
    }

    public void processIteration() {
        if (iteration == 1) {
            JavaGisDao.setCost(roadsToSimulateAccident.get(0), 10000.0);
        } else if(iteration == 2) {
            JavaGisDao.setCost(roadsToSimulateAccident.get(1), 10000.0);
        }else if(iteration == 3) {
            JavaGisDao.setCost(roadsToSimulateAccident.get(2), 10000.0);
        }else if(iteration == 4) {
            JavaGisDao.setCost(roadsToSimulateAccident.get(3), 10000.0);
        }
        iteration++;
    }
}

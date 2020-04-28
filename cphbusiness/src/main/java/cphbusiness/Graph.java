package cphbusiness;

import java.util.HashMap;
import java.util.Map;

public class Graph implements IGraph {

    Map<String, airport> airports;

    public Graph() {
        this.airports = new HashMap();
    }

    @Override
    public void addRoute(String fromCode, String toCode, 
    String airline, float distance, float time) {

        Airport from = this.airports.get(fromCode);
        Airport to = this.airports.get(toCode);

        if (from == null) {
            from = new Airport(fromCode);
            airports.put(fromCode, from);
        }
        if (to == null) {
            to = new Airport(toCode);
            airports.put(toCode, to);
        }

        Route newRoute = new Route(from, to, airline);
        from.addRoute(newRoute);
    }
}
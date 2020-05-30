package cphbusiness;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph implements IGraph {

    private final Map<String, Airport> airports;
    private final Set<String> airlineCodes;
    private Airport start;
    private int size;

    public Graph() {
        this.airports = new HashMap<>();
        this.airlineCodes = new HashSet<>();
        this.start = null;
        this.size = 0;
    }

    @Override
    public void addRoute(final String fromCode, final String toCode, final String airline, final float distance,
            final float time) {

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

        if (this.start == null) {
            this.start = from;
        }
        this.airlineCodes.add(airline);
        final Route newRoute = new Route(from, to, airline, distance, time);
        from.addRoute(newRoute);
        size++;
    }

    @Override
    public void addAirport(String code, double latitude, double longitude) {
        this.airports.put(code, new Airport(code, latitude, longitude));

    }

    /**
     * @return the start
     */
    public Airport getStart() {
        return start;
    }

    public Airport getAirport(final String string) {
        return this.airports.get(string);
    }

    public Set<String> getAirlineCodes() {
        return airlineCodes;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

}
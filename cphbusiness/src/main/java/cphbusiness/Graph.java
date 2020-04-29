package cphbusiness;

import java.util.HashMap;
import java.util.Map;

public class Graph implements IGraph {

    private Map<String, Airport> airports;
    private Airport start;
    private int size;

    public Graph() {
        this.airports = new HashMap<>();
        this.start = null;
        this.size = 0;
    }

    @Override
    public void addRoute(final String fromCode, final String toCode, 
    final String airline, final float distance, final float time) {

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

        if(this.start == null){
            this.start = from;
        }

        final Route newRoute = new Route(from, to, airline, distance, time);
        from.addRoute(newRoute);
        size++;
    }

    /**
     * @return the start
     */
    public Airport getStart() {
        return start;
    }

	public Airport getAirport(String string) {
		return this.airports.get(string);
    }
    
    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }
}
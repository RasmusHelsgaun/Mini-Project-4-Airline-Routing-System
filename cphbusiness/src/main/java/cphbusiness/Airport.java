package cphbusiness;

import java.util.ArrayList;
import java.util.List;

public class Airport implements Comparable<Airport> {
    private String code;
    private List<Route> routes;
    private Route via;
    private float shortest;
    private boolean visited;
    private double latitude;
    private double longitude;
    private double heuristic; 

    public Airport(String code, double latitude, double longitude) {
        this.code = code;
        this.routes = new ArrayList<>();
        this.via = null;
        this.shortest = Float.POSITIVE_INFINITY;
        this.visited = false;
        this.latitude = latitude;
        this.longitude = longitude;
        this.heuristic = 0;
    }

    public Airport(String code) {
        this(code, 0, 0);
    }

    public void addRoute(Route route) {
        routes.add(route);
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public String getId() {
        return code;
    }

    public Airport() {
    }

    public Airport(String code, List<Route> routes) {
        this.code = code;
        this.routes = routes;
    }

    public String getCode() {
        return this.code;
    }

    @Override
    public String toString() {
        return "{" + " code='" + getCode() + "'" + "}";
    }

    public Route getVia() {
        return this.via;
    }

    public void setVia(Route via) {
        this.via = via;
    }

    public float getShortest() {
        return this.shortest;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setShortest(float shortest) {
        this.shortest = shortest;
    }

    public boolean isVisited() {
        return this.visited;
    }

    public void setIsVisited() {
        this.visited = true;
    }

    public void setHeuristic(double heuristic) {
        this.heuristic = heuristic;
    }

    public double getHeuristic() {
        return heuristic;
    }
    

    public double heuristifiedShortest(){
        return shortest + heuristic;
    }

    @Override
    public int compareTo(Airport o) {
        if (heuristifiedShortest() < o.heuristifiedShortest()) {
            return -1;
        } else if (heuristifiedShortest() > o.heuristifiedShortest()) {
            return 1;
        } else {
            return 0;
        }
    }

}
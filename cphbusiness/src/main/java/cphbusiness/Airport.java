package cphbusiness;

import java.util.ArrayList;
import java.util.List;

public class Airport {
    private String code;
    private List<Route> routes;

    public Airport(String code) {
        this.code = code;
        this.routes = new ArrayList<>();
    }

    public void addRoute(Route route) {
        routes.add(route);
    }

    public List<Route> getEdges() {
        return routes;
    }

    public String getId() {
        return code;
    }
}
package cphbusiness;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public abstract class Search {

    protected Stack<Route> stack;
    protected Queue<Route> queue;

    public Search() {
        this.stack = new Stack<>();
        this.queue = null;
    }

    public Search(int CAPACITY) {
        this.stack = new Stack<>();
        this.queue = new Queue<>();
    }

    public Collection<Route> findSingleAirlineRoute(Airport start, String end) {
        Set<String> airLineSet = new HashSet<>();

        List<Route> routes = start.getRoutes();
        for (Route route : routes) {
            airLineSet.add(route.getAirlineCode());
        }

        for (String airline : airLineSet) {
            findSingleAirlineRoute(start, end, airline);
            if (!this.stack.isEmpty())
                return this.stack.getJavaImplementation();
        }

        return null;
    }

    protected abstract void findSingleAirlineRoute(Airport start, String end, String airlineCode);

}
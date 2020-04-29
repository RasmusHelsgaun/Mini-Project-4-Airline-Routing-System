package cphbusiness;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class DFS {

    private Stack<Route> stack;

    public DFS() {
        this.stack = new Stack<>();
    }

    public Stack<Route> findSingleAirlineRoute(Airport start, String end) {
        Set<String> airLineSet = new HashSet<>();

        List<Route> routes = start.getRoutes();
        for (Route route : routes) {
            airLineSet.add(route.getAirlineCode());
        }

        for (String airline : airLineSet) {
            findSingleAirlineRoute(start, end, airline);
            if (!this.stack.isEmpty())
                return this.stack;
        }

        return null;
    }

    private void findSingleAirlineRoute(Airport start, String end, String airlineCode) {
        while (!start.getCode().equals(end)) {
            boolean goBack = true;

            for (Route route : start.getRoutes()) {
                if (!route.isVisited() && route.getAirlineCode().equals(airlineCode)) {
                    if (!stack.isEmpty() && route.getTo() == stack.peek().getFrom())
                        continue;

                    route.setIsVisited();
                    this.stack.add(route);
                    start = route.getTo();
                    goBack = false;
                    break;
                }
            }

            if (goBack) {
                if (stack.isEmpty())
                    return;

                start = this.stack.pop().getFrom();
            }

        }
    }

}

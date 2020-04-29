package cphbusiness;

import java.util.HashSet;
import java.util.Set;

public class BFS extends Search {

    public BFS(int CAPACITY) {
        super(CAPACITY);
    }

    @Override
    protected void findSingleAirlineRoute(Airport start, String end, String airlineCode) {
        Set<String> visited = new HashSet<>();
        
        while (!start.getCode().equals(end)) {
            visited.add(start.getCode());

            for (Route route : start.getRoutes()) {
                Airport to = route.getTo();
                if (route.getAirlineCode().equals(airlineCode) && !visited.contains(to.getCode())) {
                    if (to.getCode().equals(end)) {
                        stack.add(route);
                        return;
                    } else {
                        this.queue.add(route);
                    }
                }

            }

            if (queue.isEmpty()) {
                this.stack.removeAllElements();
                return;
            }

            Route nextRoute = queue.poll();
            stack.add(nextRoute);
            start = nextRoute.getTo();
        }
    }

}
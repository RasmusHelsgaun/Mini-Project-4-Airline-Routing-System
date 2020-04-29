package cphbusiness;

public class DFS extends Search {
    
    @Override
    protected void findSingleAirlineRoute(Airport start, String end, String airlineCode) {
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

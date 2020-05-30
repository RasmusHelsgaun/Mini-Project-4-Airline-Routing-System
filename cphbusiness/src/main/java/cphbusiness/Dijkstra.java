package cphbusiness;


public class Dijkstra {
    private PriorityQueue<Airport> pq;

    public Dijkstra() {
        this.pq = new PriorityQueue<>();
    }

    public IArrayList<Route> findShortestPath(Airport start, String end, FieldGetter fieldGetter) {
        start.setShortest(0);
        Airport cur = start;
        while (!cur.getCode().equals(end)) {
            for (Route route : cur.getRoutes()) {
                float acc = fieldGetter.get(route, end) + cur.getShortest();
                Airport to = route.getTo();
                float shortest = to.getShortest();
                if(acc < shortest){
                    to.setShortest(acc);
                    to.setVia(route);
                    this.pq.add(to);
                }
            }
            cur = this.pq.poll();
            if(cur == null) return null;
        }

        IArrayList<Route> path = new ArrayList<Route>();
        
        while(!cur.equals(start)){
            Route via = cur.getVia();
            path.add(via);
            cur = via.getFrom();
        }
        
        return path;
    }
}
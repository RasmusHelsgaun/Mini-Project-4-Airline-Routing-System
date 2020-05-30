package cphbusiness;


public class Prims {
    private PriorityQueue<Route> pq;

    public Prims() {
        this.pq = new PriorityQueue<>();
    }

    public IArrayList<Route> getMST(Airport start, String airlineCode) {
        IArrayList<Route> tree = new ArrayList<>();
        Airport cur = start;
        cur.setIsVisited();
        do {
            for (Route route : cur.getRoutes()) {
                if(route.getAirlineCode().equals(airlineCode) && !route.getTo().isVisited()){
                    pq.add(route);
                }
            }
            
            
            boolean change = true;
            while(change){
                Route next = this.pq.poll();
                if(next == null) return tree;

                Airport nextAirport = next.getTo();
                if(!nextAirport.isVisited()){
                    tree.add(next);
                    cur = nextAirport;
                    cur.setIsVisited();
                    change = false;
                }
            }

        } while (!this.pq.isEmpty());

        return tree;

    }

}
package cphbusiness.algorithm;

import java.util.ArrayList;
import java.util.List;

import cphbusiness.adt.PriorityQueue;
import cphbusiness.entity.*;
import cphbusiness.iface.IFieldGetter;

public class Dijkstra {
    private PriorityQueue<Airport> pq;

    public Dijkstra() {
        this.pq = new PriorityQueue<>();
    }

    public List<Route> findShortestPath(Airport start, Airport end, IFieldGetter fieldGetter) {
        start.setShortest(0);
        Airport cur = start;
        cur.setHeuristic(getHeuristic(cur, end));
        int operations = 0;

        while (!cur.getCode().equals(end.getCode())) {
            for (Route route : cur.getRoutes()) {
                operations += 1;
                float acc = fieldGetter.get(route, end.getCode()) + cur.getShortest();
                Airport to = route.getTo();
                to.setHeuristic(getHeuristic(to, end));
                double hShortest = to.heuristifiedShortest();
                if (acc + to.getHeuristic() < hShortest) {
                    to.setShortest(acc);
                    to.setVia(route);
                    this.pq.add(to);
                }
            }
            cur = this.pq.poll();
            if (cur == null)
                return null;
        }

        List<Route> path = new ArrayList<Route>();

        while (!cur.equals(start)) {
            Route via = cur.getVia();
            path.add(via);
            cur = via.getFrom();
        }

        System.out.println("Operations: " + operations);

        return path;
    }

    public double getHeuristic(Airport to, Airport end) {
        return 0;
    }
}
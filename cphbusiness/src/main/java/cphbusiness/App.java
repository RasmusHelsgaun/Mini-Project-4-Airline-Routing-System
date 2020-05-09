package cphbusiness;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {

    private final static String START = "AER";
    private final static String END = "SIN";

    public static Graph createGraph() {
        String csvFile = "./data/routes.csv";
        String line = "";
        Graph graph = new Graph();
        int i = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                if (i++ == 0)
                    continue;
                String[] route = line.split(";");
                String from = route[1];
                String to = route[2];
                String airline = route[0];
                float distance = Float.parseFloat(route[3]);
                float time = Float.parseFloat(route[4]);
                graph.addRoute(from, to, airline, distance, time);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return graph;
    }

    public static void main(String[] args) throws IOException {
        Graph graph = createGraph();

        Search[] searchAlgs = { new DFS(), new BFS(graph.getSize()) };

        for (Search search : searchAlgs) {
            if (graph == null)
                graph = createGraph();

            System.out.println("__________________________________________________");
            Airport start = graph.getAirport(START);
            System.out.println("Using alg: " + search.getClass().getSimpleName());

            Collection<Route> path = search.findSingleAirlineRoute(start, END);

            if (path == null) {
                System.err.println("No Path To Destination With a single airline!");
            } else {
                System.out.println("Found path!");
                Route first = path.iterator().next();
                System.out.println("Airline Code: " + first.getAirlineCode());
                System.out.println("Path Length: " + path.size());

                float time = 0;
                float distance = 0;

                for (Route route : path) {
                    time += route.getTime();
                    distance += route.getDistance();
                }

                System.out.println("Time Taken: " + time + " hr - Distance: " + distance + " km");

                printPath(path);

            }
            graph = null;
        }

        // Path Algs
        graph = createGraph();
        Airport start = graph.getAirport(START);

        class FieldGetterPackage {
            String fieldName;
            FieldGetter fieldGetter;
            String metric;

            public FieldGetterPackage(String fieldName, FieldGetter fieldGetter, String metric) {
                this.fieldName = fieldName;
                this.fieldGetter = fieldGetter;
                this.metric = metric;
            }
        }

        FieldGetterPackage[] fieldGetterPackages = {
                new FieldGetterPackage("Distance", (Route r, String end) -> r.getDistance(), "km"),
                new FieldGetterPackage("Time", (Route r, String end) -> !r.getTo().getCode().equals(end) ? r.getTime() + 1 : r.getTime(), "hrs") };

        for (FieldGetterPackage fieldGetterPackage : fieldGetterPackages) {
            System.out.println("----------------Shortest Path " + fieldGetterPackage.fieldName + "----------------");
            Dijkstra ds = new Dijkstra();

            List<Route> path = ds.findShortestPath(start, END, fieldGetterPackage.fieldGetter);
            if (path == null) {
                System.err.println("No Path To Destination!");
            } else {
                Airport to = path.get(0).getTo();
                System.out.println("Shortest path from " + start.getCode() + " to " + END + " is " + to.getShortest()
                        + " " + fieldGetterPackage.metric);
                Collections.reverse(path);
                printPath(path);
            }
        }

    }

    private static void printPath(Collection<Route> path) {
        System.out.println("-------PATH--------");
        System.out.print(path.iterator().next().getFrom().getCode());
        for (Route route : path) {
            System.out.print(" -> " + route.getTo().getCode());
        }
        System.out.println();
    }

}

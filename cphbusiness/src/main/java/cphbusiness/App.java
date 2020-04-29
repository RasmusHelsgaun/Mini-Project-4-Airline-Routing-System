package cphbusiness;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;

/**
 * Hello world!
 *
 */
public class App {

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
            if(graph == null) graph = createGraph();
            
            System.out.println("__________________________________________________");
            System.out.println("Using alg: " + search.getClass().getSimpleName());
            Airport start = graph.getAirport("AER");

            Collection<Route> path = search.findSingleAirlineRoute(start, "SIN");

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

                System.out.println("-------PATH--------");
                System.out.print(first.getFrom().getCode());
                for (Route route : path) {
                    System.out.print(" -> " + route.getTo().getCode());
                }
                System.out.println();

            }
            graph = null;
        }

    }

}

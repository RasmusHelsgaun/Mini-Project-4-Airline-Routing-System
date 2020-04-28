package cphbusiness;

public class Route {
    private Airport from;
    private Airport to;
    private String airlineCode;
    private float distance;
    private float time;
    private boolean visited = false;

    public Route(Airport from, Airport to, 
    String airlineCode, float distance, float time) {
        this.from = from;
        this.to = to;
        this.airlineCode = airlineCode;
        this.distance = distance;
        this.time = time;
    }

    public Airport getFrom() {
        return from;
    }

    public Airport getTo() {
        return to;
    }

    public Airport getAirlineCode() {
        return airlineCode;
    }

    public Airport getVisited() {
        return visited;
    }

    public static void main(String[] args) {
        System.out.println( "Hello World!" );
    }
}
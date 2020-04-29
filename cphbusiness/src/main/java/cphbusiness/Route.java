package cphbusiness;

public class Route {
    private Airport from;
    private Airport to;
    private String airlineCode;
    private float distance;
    private float time;
    private boolean visited = false;

    public Route(Airport from, Airport to, String airlineCode, float distance, float time) {
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

    public String getAirlineCode() {
        return airlineCode;
    }

    public float getDistance() {
        return distance;
    }

    public float getTime() {
        return time;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setIsVisited(){
        this.visited = true;
    }

    @Override
    public String toString() {
        return "{" + " from='" + getFrom() + "'" + ", to='" + getTo() + "'" + ", airlineCode='" + getAirlineCode() + "'"
                + ", distance='" + getDistance() + "'" + ", time='" + getTime() + "'" + ", visited='" + isVisited()
                + "'" + "}";
    }

}
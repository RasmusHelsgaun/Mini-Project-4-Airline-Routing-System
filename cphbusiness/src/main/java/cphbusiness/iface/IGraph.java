package cphbusiness.iface;

public interface IGraph {
    void addRoute(String fromCode, String toCode, 
    String airline, float distance, float time);
    void addAirport(String code, double latitude, double longitude);

}
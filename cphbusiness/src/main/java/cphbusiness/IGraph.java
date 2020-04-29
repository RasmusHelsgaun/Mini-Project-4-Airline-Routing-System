package cphbusiness;

public interface IGraph {
    void addRoute(String fromCode, String toCode, 
    String airline, float distance, float time);
}
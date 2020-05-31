# Con Amore

**Problem to solve**
Going into this we wanted to add to "Mini project 4 - Airline-Routing-System" and improve the way we search for the shortest path "Distance" to avoid going through too many vertices to get the final result. For our current test scenario going from airport AER to CTU, it goes through 77.732 operations  / routes to find the shortest path possible from A to B, without knowing the general direction it should go to find the final destination. Not knowing the relative number of operations, it seems like a lot of operations to find the shortest path possible, which we would like to look into improving.

**Solution**
To solve the problem at hand,  we chose to make use of the algorithm A*, as in theory it should be a more "clever" version of Dijkstra's algorithm. Using a heuristic on the different vertices, A* should be able to guide the algorithm in the correct direction towards the final destination to begin with, instead of possibly going the opposite direction first. However this behaviour can lead to the algorithm neglecting better paths, because A* by extension of Dijkstra's is a greedy best first search algorithm, meaning it wont look for possible better paths as soon as it reaches the destination. This means that having A* alter the prioritization in the priority queue, can lead to the algorithm being more greedy than Dijkstra.

**Implementation**
Implementing A* in the project, we made an addition of heuristics to our Dijkstra implementation. Adding heuristics, we had to figure out a fast and simple way of doing so. Here we made use of coordinates of each airport, which gave us its latitude and logitude. We could use these to calculate the distance in kilometers from each Airport to the destination, and using it as the heuristic when sorting the priority queue and determining, if a new path was closer than previous. Using heuristics is normally not a part of Dijkstra, so to avoid the heuristic logic affecting Dijkstra, we made sure to assign the heuristical value 0. However making the A* class which extends Dijkstra's, we override the heuristic function to calculate the distance from coordinate to coordinate, as previously mentioned, and in doing so converting Dijkstra's into A*.

The implementation of A* returned the following results compared to Dijkstra's, where we can see the difference between operations is vastly different, bringing it down to 3.643 compared to Dijkstra's 77.732 operations to find the shortest path.
```
FROM: AER
TO: CTU

----------------Shortest Path Dijkstra (Distance)----------------
Operations: 77732
Shortest path from AER to CTU is 5991.9775 km
-------PATH--------
AER -> TAS -> URC -> CTU
----------------Shortest Path A* (Distance)----------------
Operations: 3643
Shortest path from AER to CTU is 5991.9775 km
-------PATH--------
AER -> TAS -> URC -> CTU
```
This example showcases the general performance of the two algorithms, but in some cases A* finds a slightly worse path in distance compared to Dijkstra as in the following example from AER to SIN.
```
FROM: AER
TO: SIN
----------------Shortest Path Dijkstra (Distance)----------------
Operations: 105991
Shortest path from AER to SIN is 7940.738 km
-------PATH--------
AER -> EVN -> IKA -> KUL -> SIN
----------------Shortest Path A* (Distance)----------------
Operations: 881
Shortest path from AER to SIN is 7971.551 km
-------PATH--------
AER -> DYU -> DEL -> SIN
```
Notice how the actual path is shorter, even though the distance in km is slightly longer, and the difference in operations is very high.

**Conclusion**
Finding a path with A* is immensely faster than using just Dijkstra to find the shortest path in distance, as knows the general direction of the final destination in the form of a heuristic. In doing so the algorithm makes sure to always search through the nodes closest to the destination first, to find a path. Using Dijkstra compared to A* is also a balance between computer power, speed and the quality of path to the destination. Using Dijkstra you can ensure you will get the shortest path possible, however finding it can take a lot more time compared to A*. A* on the other hand rely on the heuristic, and if this is misleading, the algorithm does not work.

In a real world scenario, people also usually prefer to avoid additional stops, even though taking several, might be  

Real world scenario (Go the correct direction comports people, rather than going around the world even though it might be shorter)

Balance between computer power / speed / quality of paths (Dijkstra will always find the shortest even though it might take longer)

Importance of the heuristic (can ruin the algorithm if a wrong number is used)
cannot be too complicated to calculate

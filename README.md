# Con Amore

**Problem to solve**
Going into this we wanted to add to "Mini project 4 - Airline-Routing-System" and improve the way we search for the shortest path "Distance" to avoid going through too many vertices to get the final result. For our current test scenario going from airport AER to CTU, it goes through 77.732 operations  / routes to find the shortest path possible from A to B, without knowing the general direction it should go to find the final destination. Not knowing the relative number of operations, it seems like a lot of operations to find the shortest path possible, which we would like to look into improving.

**Solution**
To solve the problem at hand,  we chose to make use of the algorithm A*, as in theory it should be a more "clever" version of Dijkstra's algorithm. Using a heuristic on the different vertices, A* should be able to guide the algorithm in the correct direction towards the final destination to begin with, instead of possibly going the opposite direction first. However this behaviour can lead to the algorithm neglecting better paths, because A* by extension of Dijkstra's is a greedy best first search algorithm, meaning it wont look for possible better paths as soon as it reaches the destination. This means that having A* alter the prioritization in the priority queue, can lead to the algorithm being more greedy than Dijkstra. Additionally it is important, that the heuristic does not overestimate, as it will ruin the algorithm. As an example we cannot have the heuristic estimating the path between the final destination and the one prior to be longer than it actually is. To fix this, you can tweak it to underestimate the distance, making the algorithm optimistic.

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
Finding a path with A* is immensely faster than using just Dijkstra to find the shortest path, as it knows the general direction of the final destination in the form of a heuristic. In doing so the algorithm makes sure to always search through the vertices closest towards the destination first, to find a path. Using Dijkstra compared to A* is also a balance between computer power, speed and the quality of the path to the destination. Using Dijkstra you can ensure you will get the shortest path possible, however finding it can take a lot more time compared to A*. A* on the other hand will find a path to the destination, but is in some cases not the exact same result as Dijkstra. This is due to A* relying on the heuristic to guide it towards the final destination, making it find the shortest path based on the heuristic. Therefore it is extremely important that the heuristic is correct and not overestimating, as this will lead to the algorithm failing. Tweaking the heuristic to match something that makes sense is therefore vital to the success of A*.

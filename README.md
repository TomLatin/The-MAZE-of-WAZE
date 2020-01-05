
In this project we built a directed graph which is also a weighted graph.
Also in this project you will find some algorithms that can be done on a graph that is both directed and weighted.

For more information:
* https://en.wikipedia.org/wiki/Directed_graph

* https://en.wikipedia.org/wiki/Graph_(discrete_mathematics)


### Here are the departments that build our graph:
#### Node class:
This class represents the vertices, when each vertex has the following properties:
* Key:
An integer representing the "ID" of the vertex. The key must be unique, meaning there must be no more vertex in the graph with the same key.
Note that once the vertex is created, the key field cannot be reset.

* Point:
Represents the position on the axes -X, Y, Z.
We are working on a two-dimensional graph and it is therefore best to determine Z = 0 in advance

* Weight:
An actual number representing the cost of the shortest way to reach to the node, This parameter is designed for programmers to help us with the algorithms.

* Tag:
An integer to indicate that something happened or did not happen
usually on this program, with this parameter, we checked whether or not we "visited" with a vertex.
This parameter is designed for programmers to help us with the algorithms.

* Info: 
A string that can indicate more information about the vertex.
Usually in our program we used this field to keep from which vertex the shortest way to get to that vertex.
This parameter is designed for programmers to help us with the algorithms.

#### Edge class:
This class represents the edges, when each edge has the following properties:
* Src:
An integer that represents the source vertex key

* Dest:
An integer representing the key of the destination vertex

* Weight:
Actual number representing the cost of moving from one vertex of the edge to the other vertex of the edge.
The only way to reset the weight is by an connect function in class DGraph, where if the edge we received is already exists we delete it and instead determine the new edge -the edge we received, so it is the only way to update the edge's weight because there is no set in the weight field

* Tag:
An integer to indicate that something happened or did not happen.
usually on this program, with this parameter, we checked whether or not we "visited" with a edge.
This parameter is designed for programmers to help us with the algorithms.

* Info: 
A string that can indicate more information about the edge.
This parameter is designed for programmers to help us with the algorithms.


















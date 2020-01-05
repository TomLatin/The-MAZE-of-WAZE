
![WhatsApp Image 2020-01-05 at 10 42 38](https://user-images.githubusercontent.com/57855070/71777411-292c4600-2fa8-11ea-8c89-4a91053942bd.jpeg)

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

####  DGraph class:
This class is designed to build a graph  that is both directed and weighted, by adding nodes and edges.
 when each graph has the following properties:
 
 * Nodes count: Count the number of vertices in the graph.
 
 * Edges count: Count the number of edges in the graph.
 
 * Node graph: The field contains a data structure called HashMap that stores all the vertices of the graph.
 
 * Edge graph:The field contains a data structure called HashMap and within HashMap another HashMap, to save all the edges in the graph.
 
 ####  Graph_Algo class:
 The Graph Algo class contains all algorithms that can be run on a graph. The algorithms are:
 
 * Init: Allows to insert a graph into the Graph Algo class so that we can run algorithms on it.
 
 * Init from file: Allows to insert a graph from serializable file into the Graph Algo class so that we can run algorithms on it.

* Save to file: Allows to save a graph into a serializable file.

* isConnected: Checks whether the graph is strongly related, that is, if for any two vertices in the graph we mark them in u, v there is a path between u and v and there is a path between v and u.

* Shortest path dist: The algorithm finds the shortest way between the node Src and the node Dest, the shortest way is the way the least amount of weight is passed.

* shortestPath: The algorithm returns the the shortest path between src to dest - as an ordered List of nodes,for example: 

  src -> n1 ->n2 ->...-> dest

* TSP: The algorithm computes a relatively short path which visit each node in the targets List.
 The algorithm return the nodes we pass in this path  - as an ordered List of nodess,for example: 
  n1 -> n2 ->n3 ->...-> nk

* Copy:The algorithm compute a deep copy of this graph.

####  GUI class:

The GUI class allows us to see the graphs visually.
Plus change the graph in real time by adding or taking down vertices and edges.
also we can save and load graph's from and to the  GUI.
In addition, some algorithms can be run on the graph in the GUI class.




















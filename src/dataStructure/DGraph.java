package dataStructure;

import utils.Point3D;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.io.Serializable;


public class DGraph implements graph,Serializable{

	//Fields
	private int nodesCount;
	private int edgesCount;
	private int modeCount;
	public HashMap<Integer,node_data> nodeGraph;
	public HashMap<Integer,HashMap<Integer,edge_data>> edgeGraph;


	/**
	 * Default constructor
	 */
	public DGraph(){
		this.nodeGraph = new HashMap<Integer,node_data>();
		this.edgeGraph = new HashMap<Integer,HashMap<Integer,edge_data>>();
		this.nodesCount =0;
		this.edgesCount =0;
		this.modeCount =0;
	}

	/**
	 * @param key - the node_id
	 * @return Node that is the key holder
	 * We will check if the key value belongs to a particular Node, if yes - we return it, otherwise we will throw an exception
	 * that stops the program and notify the user that no such node exists.
	 */
	@Override
	public node_data getNode(int key) {
		try{
			return this.nodeGraph.get(key);
		}
		catch (Exception e){
			throw new RuntimeException ("The Node " + key + " NOT exist");
		}
	}

	/**
	 * @param src First tip in the edge
	 * @param dest Second tip in the edge
	 * @return A Edge that starts at the src vertex and ends at the vertex dest
	 * We will check if the Edge is exsit, if yes - we return it, otherwise we will throw an exception
	 * that stops the program and notify the user that no such Edge exists.
	 */
	@Override
	public edge_data getEdge(int src, int dest) {
		try{
			return this.edgeGraph.get(src).get(dest);
		}
		catch (Exception e){
			throw new RuntimeException ("The Edge " + src + " - " + dest + " NOT exist");
		}
	}

	/**
	 * @param n The Node we want added to the Dgraph
	 * We will try to add the Node to the graph, if this is not possible because
	 * this node already exists in the graph we will print an error to the user.
	 * we advance nodesCount and modeCount
	 */
	@Override
	public void addNode(node_data n) {
		try {
			this.nodeGraph.put(n.getKey(),n);
			this.nodesCount++;
			this.modeCount++;

		}
		catch (Exception e)
		{
			System.out.println("ERR: The Node already exists in the graph");
		}
	}

	/**
	 * @param src - the source of the edge.
	 * @param dest - the destination of the edge.
	 * @param w - positive weight representing the cost (aka time, price, etc) between src-->dest.
	 * The function connects a new edge to the graph
	 */
	@Override
	public void connect(int src, int dest, double w) {

		//Checks that both vertices exist
		if (this.nodeGraph.get(src)!=null && this.nodeGraph.get(dest)!=null) {
			edge_data newEdge = new Edge(src, dest, w);
			if (this.edgeGraph.get(src) != null) { //If src has neighbors
				if (this.edgeGraph.get(src).get(dest) == null) { //the edge not exists
					this.edgeGraph.get(src).put(dest, newEdge);
				} else {
					System.out.println("ERR: The Edge exists");
				}
			} else { // no hash map meaning src has no neighbors
				HashMap<Integer, edge_data> toAdd = new HashMap<Integer, edge_data>();
				this.edgeGraph.put(src, toAdd);
				this.edgeGraph.get(src).put(dest, newEdge);
			}
			this.edgesCount++;
			this.modeCount++;
		}
		else System.out.println("ERR: One of the Nodes does not exist");
	}

	/**
	 * @return Collection that representing the Nodes in the graph
	 */
	@Override
	public Collection<node_data> getV() {
		return this.nodeGraph.values();
	}



	/**
	 * @param node_id
	 * @return Collection that representing the edges in the graph
	 */
	//try..catch
	@Override
	public Collection<edge_data> getE(int node_id) {
		return this.edgeGraph.get(node_id).values();
	}

	/**
	 * @param key
	 * @return the node we removed
	 * Deleting a node from the graph,
	 * note that When the node is deleted, all the edge containing the node are also deleted.
	 */
	@Override
	public node_data removeNode(int key) {
		node_data toReturn = this.nodeGraph.get(key);
		if (toReturn!=null) {
			this.nodeGraph.remove(key); //remove node from nodeGraph
			if (this.edgeGraph.get(key) != null) this.edgeGraph.remove(key); //remove all the edge that look like node -> x
			Iterator iter = this.edgeGraph.entrySet().iterator();
			while (iter.hasNext()) { //remove all the edge that look like x -> node
				Map.Entry mapElement = (Map.Entry) iter.next();
				int currSrc = ((int) mapElement.getKey());
				if (this.edgeGraph.get(currSrc).get(key) != null) ;
				removeEdge(currSrc, key);
			}
			this.nodesCount--;
			this.modeCount++;
		}
		return toReturn;
	}

	/**
	 * @param src
	 * @param dest
	 * @return the edge we removed
	 */
	@Override
	public edge_data removeEdge(int src, int dest) {
		edge_data toReturn = this.edgeGraph.get(src).get(dest);
		if (toReturn!=null) {
			this.edgeGraph.get(src).remove(dest);
			this.edgesCount--;
			this.modeCount++;
		}
		return toReturn;
	}

	/**
	 * @return Quantity of Nodes
	 */
	@Override
	public int nodeSize() {
		return this.nodesCount;
	}

	/**
	 * @return Quantity of edges
	 */
	@Override
	public int edgeSize() {
		return this.edgesCount;
	}

	/**
	 * @return The amount of graph changes
	 */
	@Override
	public int getMC() {
		return this.modeCount;
	}

}

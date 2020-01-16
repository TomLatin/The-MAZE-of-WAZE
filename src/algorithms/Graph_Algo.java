package algorithms;

import java.io.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import dataStructure.*;
import utils.Point3D;

/**
 * This class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2
 * @author
 *
 */
public class Graph_Algo implements graph_algorithms,Serializable{

	public graph Greph;

	public Graph_Algo(){
		this.Greph = new DGraph();
	}

	public Graph_Algo(graph g){
	 init(g);
	}

	/**
	 * Init this set of algorithms on the parameter - graph.
	 * @param g - the graph we work on
	 */
	@Override
	public void init(graph g) {
		this.Greph = g;
	}

	/**
	 * Init a graph from file
	 * @param file_name - the name of the file we loading
	 */
	@Override
	public void init(String file_name) {
		try {
			FileInputStream file = new FileInputStream(file_name);
			ObjectInputStream objectInputStream = new ObjectInputStream(file);
			graph g = (graph)objectInputStream.readObject();
			init(g);
		}
		catch (Exception e) {
			System.out.println("Exception is caught");
		}
	}

	/**
	 * Saves the graph to a file.
	 * @param file_name - the name we give to the file we saving
	 */
	@Override
	public void save(String file_name) {
		try
		{
			FileOutputStream file = new FileOutputStream(file_name);
			ObjectOutputStream out = new ObjectOutputStream(file);

			out.writeObject(this.Greph);

			out.close();
			file.close();
		}
		catch(IOException ex)
		{
			System.out.println("IOException is caught");
		}

	}

	/**
	 * we returns true if and only if (iff) there is a valid path from EVREY node to each other node.
	 * @Return true iff the graph is connected
	 */
	@Override
	public boolean isConnected() {
		if(Greph.nodeSize()==0||Greph.nodeSize()==1) return true;
		graph copied = copy();
		if (copied.getV().isEmpty()) return true;
		setZeroTag(copied);
		int keyOfFirst = copied.getV().iterator().next().getKey();
		DFSUtil(copied, keyOfFirst);
		for (node_data curr : copied.getV()){
			if (curr.getTag()==0) return false;
		}
		getTranspose(copied);
		setZeroTag(copied);
		DFSUtil(copied, keyOfFirst);
		for (node_data curr : copied.getV()){
			if (curr.getTag()==0) return false;
		}
		return true;
	}

	/**
	 * set the tags of all the node's graph to 0
	 * @param ga
	 */
	void setZeroTag(graph ga){
		for (node_data curr : ga.getV()){
			curr.setTag(0);
		}
	}

	/**
	 * set the tag of the nodes that we can reach starting with key to 1
	 * @param copied - the name of the graph
	 * @param key - the key we start from
	 */
	void DFSUtil(graph copied , int key)
	{
		copied.getNode(key).setTag(1);
		if (copied.getE(key)!=null) {
			for (edge_data curr : copied.getE(key)) {
				if (copied.getNode(curr.getDest()).getTag() == 0) DFSUtil(copied,curr.getDest());
			}
		}
	}

	/**
	 * this method transpose the graph, if there is no opposite edge we change his direction
	 * @param ga - the graph we transpose
	 */
	void getTranspose(graph ga){
		for (node_data currV : ga.getV()){
			if (ga.getE(currV.getKey())!=null) {
				Iterator iterE = ga.getE(currV.getKey()).iterator();
				edge_data currE;
				while (iterE.hasNext()) {
					currE = (edge_data) iterE.next();
					if (currE != null && currE.getTag() == 0) {

						if (ga.getEdge(currE.getDest(), currE.getSrc()) != null) {
							ga.getEdge(currE.getDest(), currE.getSrc()).setTag(1);
							currE.setTag(1);
						} else {
							ga.connect(currE.getDest(), currE.getSrc(), currE.getWeight());
							ga.getEdge(currE.getDest(), currE.getSrc()).setTag(1);
							ga.removeEdge(currE.getSrc(), currE.getDest());
							iterE = ga.getE(currV.getKey()).iterator();
						}
					}
				}
			}
		}
	}

	/**
	 * we calculate the distance between 2 node (the distance measure by shorted path)
	 * @param src - start node
	 * @param dest - end (target) node
	 * @return the length of the shortest path between src to dest in the graph
	 */
	@Override
	public double shortestPathDist(int src, int dest) {
		for (node_data currV : this.Greph.getV()) {
			currV.setTag(0);
			currV.setInfo("");
			currV.setWeight(Integer.MAX_VALUE);
		}
		this.Greph.getNode(src).setWeight(0);
		node_data min = this.Greph.getNode(src);
		node_data prev=this.Greph.getNode(src);
		while (prev!= this.Greph.getNode(dest) && min.getInfo()!="empty"){
			min.setTag(1);
			if (this.Greph.getE(min.getKey())!=null) {
				for (edge_data currE : this.Greph.getE(min.getKey())) {
					if ( (this.Greph.getNode(currE.getDest()).getTag()==0) && (min.getWeight() + currE.getWeight() < this.Greph.getNode(currE.getDest()).getWeight()) ) {
						this.Greph.getNode(currE.getDest()).setWeight(min.getWeight() + currE.getWeight());
						this.Greph.getNode(currE.getDest()).setInfo("" + min.getKey());
						prev = min;
					}
				}

			}
			min = findMinNode(this.Greph.getV());
		}
		if(this.Greph.getNode(dest).getWeight()==Integer.MAX_VALUE){
			System.out.print("There is not a path between the nodes.");
			return Integer.MAX_VALUE;
		}
		return this.Greph.getNode(dest).getWeight();
	}

	/**
	 * find the node with the smallest value on given collection
	 * @param v - the collectino we get to fint the min value
	 * @return the smallest value on the given collection
	 */
	private node_data findMinNode(Collection<node_data> v) {
		Point3D p = new Point3D(0,0);
		node_data toReturn= new Node(0,p);
		toReturn.setWeight(Integer.MAX_VALUE);
		toReturn.setInfo("empty");
		toReturn.setTag(1);
		for (node_data currV : v){
			if (currV.getTag()==0 && currV.getWeight()<toReturn.getWeight()){
				toReturn = currV;
			}
		}
		return toReturn;
	}

	/**
	 * returns the the shortest path between src to dest - as an ordered List of nodes:
	 * src -> n1 ->n2 ->...-> dest
	 * @param src - start node
	 * @param dest - end (target) node
	 * @return the list of nodes in the shortest path between src to dest in the graph
	 */
	@Override
	public LinkedList<node_data> shortestPath(int src, int dest) {
		Double val = shortestPathDist(src,dest);
		if (val == Integer.MAX_VALUE) return null;
		LinkedList<node_data> toReturn = new LinkedList<node_data>();
		node_data currV = this.Greph.getNode(dest);
		toReturn.add(currV);
		while (currV!=this.Greph.getNode(src)){
			node_data toAdd = this.Greph.getNode(Integer.parseInt(currV.getInfo()));
			toReturn.addFirst(toAdd);
			currV=toAdd;
		}
		return toReturn;
	}

	/**
	 * computes a relatively short path which visit each node in the targets List.
	 * @param targets - the list of Nodes we need to pass in this path
	 * @Return the nodes we pass in this path - - as an ordered List of nodes:
	 * n1 -> n2 ->n3 ->...-> nk
	 */
	@Override
	public List<node_data> TSP (List<Integer> targets) {
		List<Integer> chek = new LinkedList<>();
		for (Integer n : targets) { //check duplicate
			if(!chek.contains(n))
			{
				chek.add(n);
			}
		}
		targets=chek;
		List<node_data> path = new LinkedList<node_data>(); //the path we return
		double w=Double.MAX_VALUE; //the weight of the current full shortest path
		for (int i = 0; i < targets.size(); i++) { // for all the targets nodes we start from..
			double currW=0; //weight counter
			List<node_data> currpath = new LinkedList<node_data>(); //the path we build when starting from each node
			List<Integer> currTarget = new LinkedList<Integer>(); //build copy of targets list
			for (Integer k : targets){
				currTarget.add(k);
			}
			node_data curr = this.Greph.getNode(currTarget.get(i)); //choose the start node(i from the loop)
			currpath.add(curr);
			currTarget.remove((Integer) curr.getKey());
			int size = currTarget.size();
			for (int j = 0; j < size; j++) { // do currTarget.size times:
				List <node_data> nextPath = findNextStep(curr,currTarget); //get the path from curr node
				if (nextPath==null) return null; //this graph is not connected
				currW += shortestPathDist(curr.getKey(),nextPath.get(nextPath.size()-1).getKey()); //curr weight
				curr = nextPath.get(nextPath.size()-1); // make the step
				currpath.addAll(nextPath); // add this part of path to the current path
				currTarget.remove((Integer) curr.getKey());
			}
			if (currW < w){ //check if it is the shorted option
				path=currpath;
				w=currW;
			}
		}
		return  path;
	}


	/**
	 * we will find the next step of given node from a targets list of nodes
	 * @param curr the node we find the next step by shorted path
	 * @param targets list of optional nodes to go from the current node
	 * @return th lise of the path between the current node and his next step
	 */
	private List<node_data> findNextStep(node_data curr, List<Integer> targets) {
		node_data next= this.Greph.getNode(targets.get(0));
		for (Integer n : targets){
			if(shortestPathDist(curr.getKey(),n)==Integer.MAX_VALUE) return null;
			else if(shortestPathDist(curr.getKey(),n) < shortestPathDist(curr.getKey(),next.getKey())){
				next = Greph.getNode(n);
			}
		}
		List<node_data>tempPath = this.shortestPath(curr.getKey(),next.getKey());
		tempPath.remove(0);
		return tempPath;
	}

	/**
	 * Compute a deep copy of this graph.
	 * @return the copy of the graph
	 */
	@Override
	public graph copy() {
		graph toCopy = new DGraph();

		for (node_data currV : this.Greph.getV()) {
			node_data n=new Node((Node)currV);
			toCopy.addNode(n);
		}

		for (node_data currV : this.Greph.getV()){
			if (this.Greph.getE(currV.getKey())!=null) {
				for (edge_data currE : this.Greph.getE(currV.getKey())) {
					edge_data e = new Edge((Edge) currE);
					toCopy.connect(e.getSrc(), e.getDest(), e.getWeight());
				}
			}
		}
		return toCopy;
	}

}

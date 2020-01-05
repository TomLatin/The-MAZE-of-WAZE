package algorithms;

import java.io.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import GUI.Graph_GUI;
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

			System.out.println("Object has been serialized");
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
	public List<node_data> shortestPath(int src, int dest) {
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
		for (Integer n : targets) {
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

//	//@Override
//	public List<node_data> TSPs (List<Integer> targets) {
//		List<node_data> path = new LinkedList<node_data>();
//		node_data curr = this.GraphAlgo.getNode(targets.get(0));
//		path.add(curr);
//		targets.remove((Integer) curr.getKey());
//		int size = targets.size();
//		for (int i = 0; i < size; i++) {
//			List <node_data> currPath = findNextStep(curr,targets);
//			if (currPath==null) return null;
//			curr=currPath.get(currPath.size()-1);
//			path.addAll(currPath);
//			targets.remove((Integer) curr.getKey());
//		}
//		return path;
//	}

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

	public static void main(String[] args) {
//		Point3D x = new Point3D(14,4,0);
//		Point3D x2 = new Point3D(-75,14,0);
//		Point3D x3 = new Point3D(80,5,0);
//		Point3D x4 = new Point3D(1,4,0);
//		Point3D x5 = new Point3D(-5,1,0);
//		Point3D x6 = new Point3D(8,3,0);
//		Point3D x7 = new Point3D(4,1,0);
//		Point3D x8 = new Point3D(75,14,0);
//		node_data a1 = new Node(1,x);
//		node_data a2 = new Node(2,x2);
//		node_data a3 = new Node(3,x3);
//		node_data a4 = new Node(4,x4);
//		node_data a5 = new Node(5,x5);
//		node_data a6 = new Node(6,x6);
//		node_data a7 = new Node(7,x7);
//		node_data a8 = new Node(8,x8);
//		DGraph d = new DGraph();
//		d.addNode(a1);
//		d.addNode(a2);
//		d.addNode(a3);
//		d.addNode(a4);
//		d.addNode(a5);
//		d.addNode(a6);
//		d.addNode(a7);
//		d.addNode(a8);
//		d.connect(a1.getKey(),a2.getKey(),5);
//	//	d.connect(a1.getKey(),a5.getKey(),2);
//		d.connect(a1.getKey(),a3.getKey(),6);
//	//	d.connect(a1.getKey(),a6.getKey(),5);
//		d.connect(a3.getKey(),a4.getKey(),7);
//		d.connect(a2.getKey(),a8.getKey(),8);
//	//	d.connect(a2.getKey(),a7.getKey(),3);
//		d.connect(a5.getKey(),a1.getKey(),5);
//		d.connect(a5.getKey(),a6.getKey(),2);
//	//	d.connect(a6.getKey(),a1.getKey(),3);
//		d.connect(a6.getKey(),a5.getKey(),3);
//		d.connect(a6.getKey(),a7.getKey(),3);
//		//d.connect(a7.getKey(),a6.getKey(),3);
//		Graph_Algo p = new Graph_Algo();
//		p.Greph=d;
//		List<Integer> r = new LinkedList<>();
//		r.add(a1.getKey());
//		r.add(a6.getKey());
//		r.add(a5.getKey());
//		List<node_data> ans = p.TSP(r);
//		//System.out.println(d.getNode(7).getWeight());
//		System.out.println(ans);

		Point3D x = new Point3D(-400,300,0);
		Point3D x2 = new Point3D(300,150,0);
		Point3D x3 = new Point3D(380,-300,0);
		Point3D x4 = new Point3D(150,-400,0);
		Point3D x5 = new Point3D(0,-450,0);
		Point3D x6 = new Point3D(200,-300,0);
		Point3D x7 = new Point3D(-400,-150,0);
		Point3D x8 = new Point3D(-400,120,0);
		Node a1 = new Node(1,x);
		Node a2 = new Node(2,x2);
		Node a3 = new Node(3,x3);
		Node a4 = new Node(4,x4);
		Node a5 = new Node(5,x5);
		Node a6 = new Node(6,x6);
		Node a7 = new Node(7,x7);
		Node a8 = new Node(8,x8);
		DGraph d = new DGraph();
		d.addNode(a1);
		d.addNode(a2);
		d.addNode(a3);
		d.addNode(a4);
		d.addNode(a5);
		d.addNode(a6);
		d.addNode(a7);
		d.addNode(a8);
		d.connect(1,2,5);
		d.connect(1,5,2);
		d.connect(1,3,6);
		d.connect(1,6,5);
		d.connect(3,4,7);
		d.connect(2,8,8);
		d.connect(2,7,3);
		d.connect(5,1,5);
		d.connect(5,6,2);
		d.connect(6,1,3);
		d.connect(6,5,3);
		d.connect(6,7,3);
		d.connect(7,6,3);
		Graph_Algo p = new Graph_Algo();
		p.init(d);
		List<Integer> r = new LinkedList<>();
		r.add(1);
		r.add(6);
		r.add(5);
		List<node_data> ans = p.TSP(r);
		Graph_GUI q = new Graph_GUI(d);



		double bbbb = p.shortestPathDist(3,4);
		//    List<node_data> theList = p.TSP(r);
		double eeeee = p.shortestPathDist(1,6);
		//     List<node_data> qqqq =  p.TSP(r);
		System.out.println(p.isConnected());
		System.out.println("r");
	}

}

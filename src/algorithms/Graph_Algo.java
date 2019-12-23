package algorithms;

import java.util.Iterator;
import java.util.List;

import dataStructure.*;
import utils.Point3D;

/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
 *
 */
public class Graph_Algo implements graph_algorithms{

	private graph graphAlgo;

	public Graph_Algo(){
		this.graphAlgo= null;
	}

	@Override
	public void init(graph g) {
		this.graphAlgo = g;

	}

	@Override
	public void init(String file_name) {
		
	}

	@Override
	public void save(String file_name) {
		
	}

	@Override
	public boolean isConnected() {
		if (graphAlgo.getV().isEmpty()) return true;
		setZeroTag(graphAlgo);
		int keyOfFirst = graphAlgo.getV().iterator().next().getKey();
		DFSUtil(keyOfFirst);
		for (node_data curr : graphAlgo.getV()){
			if (curr.getTag()==0) return false;
		}
		getTranspose(graphAlgo);
		setZeroTag(graphAlgo);
		DFSUtil(keyOfFirst);
		for (node_data curr : graphAlgo.getV()){
			if (curr.getTag()==0) return false;
		}
		return true;
	}

	void setZeroTag(graph ga){
		for (node_data curr : ga.getV()){
			curr.setTag(0);
		}
	}

	void DFSUtil(int key)
	{
		graphAlgo.getNode(key).setTag(1);
		graphAlgo.connect(key,key,0);
		for (edge_data curr : graphAlgo.getE(key)){
			if(graphAlgo.getNode(curr.getDest()).getTag()==0) DFSUtil(curr.getDest());
		}
	}

	void getTranspose(graph ga){
		for (node_data currV : ga.getV()){
			ga.connect(currV.getKey(), currV.getKey(),0);
			Iterator iterE = ga.getE(currV.getKey()).iterator();
			edge_data currE;
			while (iterE.hasNext()){
				currE = (edge_data) iterE.next();
				if (ga.getEdge(currE.getSrc(),currE.getDest()).getTag()==0) {
					ga.connect(currE.getDest(), currE.getSrc(), currE.getWeight());
					ga.getEdge(currE.getDest(), currE.getSrc()).setTag(1);
					ga.removeEdge(currE.getSrc(), currE.getDest());
					iterE = ga.getE(currV.getKey()).iterator();
				}
			}
		}
	}

	@Override
	public double shortestPathDist(int src, int dest) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public graph copy() {
		graph toCopy = new DGraph();
		for (node_data currV : this.graphAlgo.getV()){
			toCopy.addNode(currV);
			for (edge_data currE : this.graphAlgo.getE(currV.getKey())){
				toCopy.connect(currE.getSrc(),currE.getDest(),currE.getWeight());
			}
		}
		return toCopy;
	}

	public static void main(String[] args) {
		Point3D x = new Point3D(1,4);
		Point3D y = new Point3D(2,5);
		Point3D q = new Point3D(4,3);
		node_data a = new Node(1, x,2,3,"asf");
		node_data b = new Node(2,y,4,6,"ads");
		node_data c = new Node(3,q,50,50,"sf");
		DGraph d = new DGraph();
		d.addNode(a);
		d.addNode(b);
		d.addNode(c);
		d.connect(a.getKey(),b.getKey(),4);
		d.connect(b.getKey(),c.getKey(),50);
		d.connect(c.getKey(),a.getKey(),50);
		Graph_Algo nt = new Graph_Algo();
		nt.init(d);
		graph copied = nt.copy();
				System.out.println(nt.isConnected());
	}

}

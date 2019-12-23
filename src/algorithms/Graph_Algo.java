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

	private graph ga;

	public Graph_Algo(){
		this.ga= null;
	}

	@Override
	public void init(graph g) {
		this.ga = g;
	}

	@Override
	public void init(String file_name) {


	}

	@Override
	public void save(String file_name) {
		
	}

	@Override
	public boolean isConnected() {
	    if(ga.getV().isEmpty()) return false;
		Iterator iter = ga.getV().iterator();
		while (iter.hasNext()){
			node_data curr = (node_data) iter.next();
			curr.setTag(0);
		}

		Iterator iter2 = ga.getV().iterator();
		node_data curr2 =  (node_data) iter2.next();
		DFSUtil(curr2.getKey());
		while (iter2.hasNext()){
			if (curr2.getTag()==0) return false;
			curr2 =  (node_data) iter2.next();
		}
		getTranspose(ga);

		Iterator iter4 = ga.getV().iterator();
		while (iter4.hasNext()){
			node_data curr = (node_data) iter4.next();
			curr.setTag(0);
		}
		Iterator iter3 = ga.getV().iterator();
		node_data curr3 =  (node_data) iter3.next();
		DFSUtil(curr3.getKey());
		while (iter3.hasNext()){
			if (curr3.getTag()==0) return false;
			curr3 =  (node_data) iter3.next();
		}

        Iterator iter5 = ga.getV().iterator();
        while (iter5.hasNext()){
            node_data curr = (node_data) iter5.next();
            curr.setTag(0);
        }

		getTranspose(ga);
		return true;
	}

	private void DFSUtil(int key)
	{
		ga.getNode(key).setTag(1);
		ga.connect(key,key,0);
		Iterator iter = ga.getE(key).iterator();
		while (iter.hasNext()){
			edge_data curr = (edge_data) iter.next();
			if(ga.getNode(curr.getDest()).getTag()==0){
				DFSUtil(curr.getDest());
			}
		}
	}

	void getTranspose(graph ga){
		Iterator iterV = ga.getV().iterator();
		while (iterV.hasNext()){
			node_data currV = (node_data) iterV.next();
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
		// TODO Auto-generated method stub
		return null;
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
//		d.connect(c.getKey(),a.getKey(),50);
		Graph_Algo nt = new Graph_Algo();
		nt.init(d);
		System.out.println(nt.isConnected());
	}

}

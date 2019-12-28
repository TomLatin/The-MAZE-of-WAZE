package algorithms;

import java.io.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import dataStructure.*;
import utils.Point3D;

/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
 *
 */
public class Graph_Algo implements graph_algorithms,Serializable{

	public graph GA;

	public Graph_Algo(){
		this.GA = new DGraph();
	}

	@Override
	public void init(graph g) {
		this.GA = g;

	}

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

	@Override
	public void save(String file_name) {
		try
		{
			FileOutputStream file = new FileOutputStream(file_name);
			ObjectOutputStream out = new ObjectOutputStream(file);

			out.writeObject(this.GA);

			out.close();
			file.close();

			System.out.println("Object has been serialized");
		}
		catch(IOException ex)
		{
			System.out.println("IOException is caught");
		}

	}

	@Override
	public boolean isConnected() {
		if (GA.getV().isEmpty()) return true;
		setZeroTag(GA);
		int keyOfFirst = GA.getV().iterator().next().getKey();
		DFSUtil(keyOfFirst);
		for (node_data curr : GA.getV()){
			if (curr.getTag()==0) return false;
		}
		getTranspose(GA);
		setZeroTag(GA);
		DFSUtil(keyOfFirst);
		for (node_data curr : GA.getV()){
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
		GA.getNode(key).setTag(1);
		GA.connect(key,key,0);
		for (edge_data curr : GA.getE(key)){
			if(GA.getNode(curr.getDest()).getTag()==0) DFSUtil(curr.getDest());
		}
	}

	void getTranspose(graph ga){
		for (node_data currV : ga.getV()){
			ga.connect(currV.getKey(), currV.getKey(),0);
			Iterator iterE = ga.getE(currV.getKey()).iterator();
			edge_data currE;
			while (iterE.hasNext()){
				currE = (edge_data) iterE.next();
				if(currE!=null && currE.getTag()==0)
				{

					if(ga.getEdge(currE.getDest(),currE.getSrc())!=null)
					{
						ga.getEdge(currE.getDest(),currE.getSrc()).setTag(1);
						currE.setTag(1);
					}
					else
					{
						ga.connect(currE.getDest(), currE.getSrc(), currE.getWeight());
						ga.getEdge(currE.getDest(), currE.getSrc()).setTag(1);
						ga.removeEdge(currE.getSrc(), currE.getDest());
						iterE = ga.getE(currV.getKey()).iterator();
					}
				}
			}
		}
	}

	@Override
	public double shortestPathDist(int src, int dest) {
		for (node_data currV : this.GA.getV()) {
			currV.setTag(0);
			currV.setInfo("");
			currV.setWeight(Integer.MAX_VALUE);
		}
		this.GA.getNode(src).setWeight(0);
		node_data min = findMinNode (this.GA.getV());
		while (min!= this.GA.getNode(dest) && min.getInfo()!="empty"){
			min.setTag(1);
			for (edge_data currE : this.GA.getE(min.getKey())){
				if(min.getWeight()+currE.getWeight()< this.GA.getNode(currE.getDest()).getWeight()) {
					this.GA.getNode(currE.getDest()).setWeight(min.getWeight() + currE.getWeight());
					this.GA.getNode(currE.getDest()).setInfo(""+min.getKey());
				}
			}
			min=findMinNode(this.GA.getV());
		}
		return this.GA.getNode(dest).getWeight();
	}


	private node_data findMinNode(Collection<node_data> v) {
		Point3D p = new Point3D(0,0);
		node_data toReturn= new Node(0,p,Integer.MAX_VALUE,1,"empty");
		for (node_data currV : v){
			if (currV.getTag()==0 && currV.getWeight()<toReturn.getWeight()){
				toReturn = currV;
			}
		}
		return toReturn;
	}


	@Override
	public List<node_data> shortestPath(int src, int dest) {
		Double dst = shortestPathDist(src,dest);
		LinkedList<node_data> toReturn = new LinkedList<node_data>();
		node_data currV = this.GA.getNode(dest);
		toReturn.add(currV);
		while (currV!=this.GA.getNode(src)){
			node_data toAdd = this.GA.getNode(  Integer.parseInt(currV.getInfo()));
			toReturn.addFirst(toAdd);
			currV=toAdd;
		}
		return toReturn;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public graph copy() {
		graph toCopy = new DGraph();

		for (node_data currV : this.GA.getV()) {
			node_data n=new Node((Node)currV);
			toCopy.addNode(n);
		}

		for (node_data currV : this.GA.getV()){
			for (edge_data currE : this.GA.getE(currV.getKey())){
				edge_data e=new Edge((Edge)currE);
				toCopy.connect(e.getSrc(),e.getDest(),e.getWeight());
			}
		}
		return toCopy;
	}

	public static void main(String[] args) {
		Point3D x = new Point3D(1,4);
		Point3D y = new Point3D(2,5);
		Point3D q = new Point3D(4,3);
		node_data a = new Node(1, x,2,0,"asf");
		node_data b = new Node(2,y,4,0,"ads");
		node_data c = new Node(3,q,50,0,"sf");
		DGraph d = new DGraph();
		d.addNode(a);
		d.addNode(b);
		d.addNode(c);
		d.connect(a.getKey(),b.getKey(),4);
		d.connect(b.getKey(),c.getKey(),4);
		d.connect(c.getKey(),a.getKey(),50);
		d.connect(c.getKey(),b.getKey(),50);
		Graph_Algo nt = new Graph_Algo();
		nt.init(d);
	  //  nt.isConnected();
		//System.out.println(nt.isConnected());
		System.out.println(nt.shortestPathDist(1,3));
		System.out.println(nt.shortestPath(1,3));
			//	nt.save("test1");
	}

}

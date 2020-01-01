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



	@Override
	public void init(graph g) {
		this.Greph = g;
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

	void setZeroTag(graph ga){
		for (node_data curr : ga.getV()){
			curr.setTag(0);
		}
	}

	void DFSUtil(graph copied , int key)
	{
		copied.getNode(key).setTag(1);
		if (copied.getE(key)!=null) {
			for (edge_data curr : copied.getE(key)) {
				if (copied.getNode(curr.getDest()).getTag() == 0) DFSUtil(copied,curr.getDest());
			}
		}
	}

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

	@Override
	public double shortestPathDist(int src, int dest) {
		for (node_data currV : this.Greph.getV()) {
			currV.setTag(0);
			currV.setInfo("");
			currV.setWeight(Integer.MAX_VALUE);
		}
		this.Greph.getNode(src).setWeight(0);
		node_data min = this.Greph.getNode(src);
		while (min!= this.Greph.getNode(dest) && min.getInfo()!="empty"){
			min.setTag(1);
			if (this.Greph.getE(min.getKey())!=null) {
				for (edge_data currE : this.Greph.getE(min.getKey())) {
					if (min.getWeight() + currE.getWeight() < this.Greph.getNode(currE.getDest()).getWeight()) {
						this.Greph.getNode(currE.getDest()).setWeight(min.getWeight() + currE.getWeight());
						this.Greph.getNode(currE.getDest()).setInfo("" + min.getKey());
					}
				}
			}
			min = findMinNode(this.Greph.getV());
		}
		return this.Greph.getNode(dest).getWeight();
	}


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


	@Override
	public List<node_data> shortestPath(int src, int dest) {
		Double dst = shortestPathDist(src,dest);
		LinkedList<node_data> toReturn = new LinkedList<node_data>();
		node_data currV = this.Greph.getNode(dest);
		toReturn.add(currV);
		while (currV!=this.Greph.getNode(src)){
			node_data toAdd = this.Greph.getNode(  Integer.parseInt(currV.getInfo()));
			toReturn.addFirst(toAdd);
			currV=toAdd;
		}
		return toReturn;
	}

	@Override
	public List<node_data> TSP (List<Integer> targets) {
		List<node_data> path = new LinkedList<node_data>();
		double w=Double.MAX_VALUE;
		for (int i = 0; i < targets.size(); i++) {
			double currW=0;
			List<node_data> currpath = new LinkedList<node_data>();
			List<Integer> currTarget = new LinkedList<Integer>();
			for (Integer k : targets){
				currTarget.add(k);
			}
			node_data curr = this.Greph.getNode(currTarget.get(i));
			currpath.add(curr);
			currTarget.remove((Integer) curr.getKey());
			int size = currTarget.size();
			for (int j = 0; j < size; j++) {
				List <node_data> nextPath = findNextStep(curr,currTarget);
				if (nextPath==null) return null;
				currW += shortestPathDist(curr.getKey(),nextPath.get(nextPath.size()-1).getKey());
				curr = nextPath.get(nextPath.size()-1);
				currpath.addAll(nextPath);
				currTarget.remove((Integer) curr.getKey());
			}
			if (currW < w){
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
		Point3D x = new Point3D(14,4,0);
		Point3D x2 = new Point3D(-75,14,0);
		Point3D x3 = new Point3D(80,5,0);
		Point3D x4 = new Point3D(1,4,0);
		Point3D x5 = new Point3D(-5,1,0);
		Point3D x6 = new Point3D(8,3,0);
		Point3D x7 = new Point3D(4,1,0);
		Point3D x8 = new Point3D(75,14,0);
		node_data a1 = new Node(1,x);
		node_data a2 = new Node(2,x2);
		node_data a3 = new Node(3,x3);
		node_data a4 = new Node(4,x4);
		node_data a5 = new Node(5,x5);
		node_data a6 = new Node(6,x6);
		node_data a7 = new Node(7,x7);
		node_data a8 = new Node(8,x8);
		DGraph d = new DGraph();
		d.addNode(a1);
		d.addNode(a2);
		d.addNode(a3);
		d.addNode(a4);
		d.addNode(a5);
		d.addNode(a6);
		d.addNode(a7);
		d.addNode(a8);
		d.connect(a1.getKey(),a2.getKey(),5);
	//	d.connect(a1.getKey(),a5.getKey(),2);
		d.connect(a1.getKey(),a3.getKey(),6);
	//	d.connect(a1.getKey(),a6.getKey(),5);
		d.connect(a3.getKey(),a4.getKey(),7);
		d.connect(a2.getKey(),a8.getKey(),8);
	//	d.connect(a2.getKey(),a7.getKey(),3);
		d.connect(a5.getKey(),a1.getKey(),5);
		d.connect(a5.getKey(),a6.getKey(),2);
	//	d.connect(a6.getKey(),a1.getKey(),3);
		d.connect(a6.getKey(),a5.getKey(),3);
		d.connect(a6.getKey(),a7.getKey(),3);
		//d.connect(a7.getKey(),a6.getKey(),3);
		Graph_Algo p = new Graph_Algo();
		p.Greph=d;
		List<Integer> r = new LinkedList<>();
		r.add(a1.getKey());
		r.add(a6.getKey());
		r.add(a5.getKey());
		List<node_data> ans = p.TSP(r);
		//System.out.println(d.getNode(7).getWeight());
		System.out.println(ans);
	}

}

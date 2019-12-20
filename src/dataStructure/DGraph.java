package dataStructure;

import utils.Point3D;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DGraph implements graph{

	private int nodesCount;
	private int edgesCount;
	private int modeCount;
	HashMap<Integer,node_data> nodeGraph;
	HashMap<Integer,HashMap<Integer,edge_data>> edgeGraph;

	public DGraph(){
		this.nodeGraph = new HashMap<Integer,node_data>();
		this.edgeGraph = new HashMap<Integer,HashMap<Integer,edge_data>>();
		this.nodesCount =0;
		this.edgesCount =0;
		this.modeCount =0;
	}

	@Override
	public node_data getNode(int key) {
		try{
			return this.nodeGraph.get(key);
		}
		catch (Exception e){
			throw new RuntimeException ("The Node " + key + " NOT exist");
		}
	}

	@Override
	public edge_data getEdge(int src, int dest) {
		try{
			return this.edgeGraph.get(src).get(dest);
		}
		catch (Exception e){
			throw new RuntimeException ("The Edge " + src + " - " + dest + " NOT exist");
		}
	}

	@Override
	public void addNode(node_data n) {
		if (this.nodeGraph.get(n.getKey())!=null){
			removeNode(n.getKey());
		}
		this.nodeGraph.put(n.getKey(),n);
		this.nodesCount++;
		this.modeCount++;

	}

	@Override
	public void connect(int src, int dest, double w) {
		if (this.nodeGraph.get(src)!=null && this.nodeGraph.get(dest)!=null) {
			edge_data newEdge = new Edge(src, dest, w, "", 0);
			if (this.edgeGraph.get(src) != null) {
				if (this.edgeGraph.get(src).get(dest) == null) {
					this.edgeGraph.get(src).put(dest, newEdge);
				} else {
					removeEdge(src, dest);
					this.edgeGraph.get(src).put(dest, newEdge);
				}
			} else {
				HashMap<Integer, edge_data> toAdd = new HashMap<Integer, edge_data>();
				this.edgeGraph.put(src, toAdd);
				this.edgeGraph.get(src).put(dest, newEdge);
			}
			this.edgesCount++;
			this.modeCount++;
		}
		else System.out.println("Wrong Input! Missing Node...");
	}

	@Override
	public Collection<node_data> getV() {
		return this.nodeGraph.values();
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		return this.edgeGraph.get(node_id).values();
	}

	@Override
	public node_data removeNode(int key) {
		node_data toReturn = this.nodeGraph.get(key);
		if (toReturn!=null) {
			this.nodeGraph.remove(key);
			Iterator iter = this.edgeGraph.entrySet().iterator();
			if (this.edgeGraph.get(key) != null) this.edgeGraph.remove(key);
			while (iter.hasNext()) {
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

	@Override
	public int nodeSize() {
		return this.nodesCount;
	}

	@Override
	public int edgeSize() {
		return this.edgesCount;
	}

	@Override
	public int getMC() {
		return this.modeCount;
	}

	public static void main(String[] args) {
		Point3D x = new Point3D(1,4);
		Point3D y = new Point3D(2,5);
		Point3D q = new Point3D(4,3);
		node_data a = new Node(1, x,2,3,"asf");
		node_data b = new Node(3,y,4,6,"ads");
		node_data c = new Node(5,q,50,50,"sf");
		DGraph d = new DGraph();
		d.addNode(a);
		d.addNode(b);
		d.addNode(c);
		d.connect(a.getKey(),b.getKey(),4);
		d.connect(a.getKey(),c.getKey(),50);
		node_data g = d.getNode(55);
		System.out.println(g.getWeight());
	}
}

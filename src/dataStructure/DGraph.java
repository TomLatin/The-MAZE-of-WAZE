package dataStructure;

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
		HashMap<Integer,node_data> nodeGraph = new HashMap<>();
		HashMap<Integer,HashMap<Integer,edge_data>> edgeGraph = new HashMap<>();
		this.nodesCount =0;
		this.edgesCount =0;
		this.modeCount =0;
	}

	@Override
	public node_data getNode(int key) {
		return this.nodeGraph.get(key);
	}

	@Override
	public edge_data getEdge(int src, int dest) {
		return this.edgeGraph.get(src).get(dest);
	}

	@Override
	public void addNode(node_data n) {
		this.nodeGraph.put(n.getKey(),n);
		this.nodesCount++;
		this.modeCount++;
	}

	@Override
	public void connect(int src, int dest, double w) {
		if (this.nodeGraph.get(src)!=null && this.nodeGraph.get(dest)!=null) {
			edge_data newEdge = new edge(src, dest, w, "", 0);
			if (this.edgeGraph.get(src) != null) {
				if (this.edgeGraph.get(src).get(dest) == null) {
					this.edgeGraph.get(src).put(dest, newEdge);
				} else {
					removeEdge(src, dest);
					this.edgeGraph.get(src).put(dest, newEdge);
				}
			} else {
				HashMap<Integer, edge_data> toAdd = new HashMap<>();
				this.edgeGraph.put(src, toAdd).put(dest, newEdge);
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
}

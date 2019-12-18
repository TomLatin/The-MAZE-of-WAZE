package dataStructure;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DGraph implements graph{

	private static int nodeSize = 0;
	private static int edgeSize = 0;
	private static int MC = 0;
	HashMap<Integer,node_data> nodeGraph = new HashMap<>();
	HashMap<Integer,HashMap<Integer,edge_data>> edgeGraph = new HashMap<>();

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
		nodeSize++;
		MC++;
	}

	@Override
	public void connect(int src, int dest, double w) {
		edge newEdge = new edge(src,dest,w,"",0);
		if (this.edgeGraph.get(src)!=null){
			if (this.edgeGraph.get(src).get(dest)==null) {
				this.edgeGraph.get(src).put(dest, newEdge);
			}
			else{
				removeEdge(src, dest);
				this.edgeGraph.get(src).put(dest,newEdge);
			}
		}
		else {
			HashMap<Integer,edge_data> toAdd=new HashMap<>();
			this.edgeGraph.put(src,toAdd).put(dest,newEdge);
		}
		edgeSize++;
		MC++;
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
			nodeSize--;
			MC++;
		}
		return toReturn;
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		edge_data toReturn = this.edgeGraph.get(src).get(dest);
		if (toReturn!=null) {
			this.edgeGraph.get(src).remove(dest);
			edgeSize--;
			MC++;
		}
		return toReturn;
	}

	@Override
	public int nodeSize() {
		return nodeSize;
	}

	@Override
	public int edgeSize() {
		return edgeSize;
	}

	@Override
	public int getMC() {
		return MC;
	}

}

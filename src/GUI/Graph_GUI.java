package GUI;

import algorithms.Graph_Algo;
import dataStructure.*;
import dataStructure.node_data;
import utils.*;
import java.awt.*;
import java.util.List;


public class Graph_GUI {



    private DGraph dGraph = new DGraph();
    private Graph_Algo graphAlgo = new Graph_Algo();
    public static int keyEmpty = 1;

    public Graph_GUI(){
        StdDraw.GUI=this;
        draw(1000,1000,new Range(-100,100),new Range(-100,100));
    }

    public void addNode(Point3D p,double weight){
        keyEmpty=this.dGraph.findNextKey();
        Node temp = new Node(keyEmpty,p,weight,0,"");
        dGraph.addNode(temp);
    }

    public void addEdge(int src,int dest, double weight){
        dGraph.connect(src,dest,weight);
    }

    public void deleteNode(int key) {
        dGraph.removeNode(key);
    }
    public void deleteEdge(int src,int des) {
        dGraph.removeEdge(src,des);
    }

    public boolean isConected(){
        graphAlgo.init(dGraph);
        return graphAlgo.isConnected();
    }

    public double shortestPathDist(int src, int dest) {
        graphAlgo.init(dGraph);
        return graphAlgo.shortestPathDist(src,dest);
    }

    public List<node_data> shortestPath(int src, int dest) {
        graphAlgo.init(dGraph);
        return graphAlgo.shortestPath(src,dest);
    }

    public void save(String filename){
        graphAlgo.init(dGraph);
        graphAlgo.save(filename);

    }
    public void initGraph(String filename){
        graphAlgo.init(filename);
        dGraph=(DGraph)graphAlgo.copy();
        keyEmpty=dGraph.findNextKey();
    }

    public void draw(int width, int height, Range x, Range y){
        StdDraw.setCanvasSize(width,height,this);
        StdDraw.setXscale(x.get_min(),x.get_max());
        StdDraw.setYscale(y.get_min(),y.get_max());
        sketch();
    }

    public void sketch() {
        StdDraw.clear();
        for (node_data currV : dGraph.getV()) {
            this.addEdge(currV.getKey(),currV.getKey(),0);
            for (edge_data currE : dGraph.getE(currV.getKey())) {
                if (currE.getDest()!=currE.getSrc()) {
                    double weight = currE.getWeight();
                    node_data srcN = currV;
                    node_data dstN = dGraph.getNode(currE.getDest());
                    Point3D srcP = srcN.getLocation();
                    Point3D dstP = dstN.getLocation();
                    StdDraw.setPenColor(Color.BLACK);
                    StdDraw.setPenRadius(0.01);
                    StdDraw.line(srcP.x(), srcP.y(), dstP.x(), dstP.y());
                    StdDraw.setPenColor(Color.BLUE);
                    double tX = srcP.x() + (dstP.x() - srcP.x()) * 0.8, tY = srcP.y() + (dstP.y() - srcP.y()) * 0.8;
                    StdDraw.text(tX, tY + 2, "" + weight);
                    StdDraw.filledRectangle(tX, tY, 1, 1);
                }
            }
        }
        for (node_data curr : dGraph.getV()){
            StdDraw.setPenColor(Color.RED);
            StdDraw.setPenRadius(0.1);
            Point3D p = curr.getLocation();
            StdDraw.filledCircle(p.x(),p.y(),3.5);
            StdDraw.setPenColor(Color.YELLOW);
            StdDraw.text(p.x(),p.y(),""+curr.getKey());
        }
    }

    public static void main(String[] args) {
        Graph_GUI g = new Graph_GUI();
    }
}

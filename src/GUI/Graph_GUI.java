package GUI;

import algorithms.Graph_Algo;
import dataStructure.*;
import dataStructure.node_data;
import utils.*;
import java.awt.*;
import java.util.List;


public class Graph_GUI {

    private DGraph dGraph;
    private Graph_Algo graphAlgo;
    public static int keyEmpty = 1;

    public Graph_GUI(){
        this.dGraph = new DGraph();
        this.graphAlgo = new Graph_Algo();
        StdDraw.GUI = this;
        draw(1000,1000,new Range(-100,100),new Range(-100,100));
    }

    public Graph_GUI(DGraph g){
        this.dGraph=g;
        this.graphAlgo=new Graph_Algo();
        this.graphAlgo.init(g);
        StdDraw.GUI = this;
        draw(1000,1000,new Range(-100,100),new Range(-100,100));
    }

    public node_data getNeerNode (double x, double y){
        for (node_data curr : dGraph.getV()){
            double currX = curr.getLocation().x();
            double currY = curr.getLocation().y();
            if ((x < currX+3.5) && (x > currX-3.5) && (y < currY+3.5) && (y > currY-3.5)) return curr;
        }
        return null;
    }

    public void addNode(Point3D p){
        keyEmpty = this.dGraph.findNextKey();
        Node temp = new Node(keyEmpty,p);
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

    public List<node_data> TSP (List<Integer> chosen){
        graphAlgo.init(dGraph);
        return graphAlgo.TSP(chosen);
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
            if (dGraph.getE(currV.getKey()) != null) {
                for (edge_data currE : dGraph.getE(currV.getKey())) {
                    if (currE.getDest() != currE.getSrc()) {
                        node_data srcN = currV;
                        node_data dstN = dGraph.getNode(currE.getDest());
                        Point3D srcP = srcN.getLocation();
                        Point3D dstP = dstN.getLocation();
                        StdDraw.setPenColor(Color.BLACK);
                        StdDraw.setPenRadius(0.01);
                        StdDraw.line(srcP.x(), srcP.y(), dstP.x(), dstP.y());
                    }
                }
            }
        }
        for (node_data currV : dGraph.getV()) {
            if (dGraph.getE(currV.getKey()) != null) {
                for (edge_data currE : dGraph.getE(currV.getKey())) {
                    if (currE.getDest() != currE.getSrc()) {
                        double weight = currE.getWeight();
                        node_data srcN = currV;
                        node_data dstN = dGraph.getNode(currE.getDest());
                        Point3D srcP = srcN.getLocation();
                        Point3D dstP = dstN.getLocation();
                        StdDraw.setPenColor(Color.MAGENTA);
                        double tX = srcP.x() + (dstP.x() - srcP.x()) * 0.8, tY = srcP.y() + (dstP.y() - srcP.y()) * 0.8;

                        double rx = 0, gy = 0;
                        if (srcP.y() == dstP.y()) gy = 5;
                        else if (srcP.x() == dstP.x()) rx = 6 ;
                        else {
                            double m = (dstP.y() - srcP.y()) / (dstP.x() - srcP.x());
                            if (Math.abs(m) > 1) rx = 6;
                            else gy = 5;
                        }

                        StdDraw.text(tX + rx, tY + gy, "" + (int)weight);
                        StdDraw.filledRectangle(tX, tY, 1.5, 1.5);
                    }
                }
            }
            for (node_data curr : dGraph.getV()) {
                StdDraw.setPenColor(Color.RED);
                StdDraw.setPenRadius(0.1);
                Point3D p = curr.getLocation();
                StdDraw.filledCircle(p.x(), p.y(), 3.5);
                StdDraw.setPenColor(Color.YELLOW);
                StdDraw.text(p.x(), p.y(), "" + curr.getKey());
            }
        }
    }

    public static void main(String[] args) {
        DGraph d = new DGraph();
        for (int i = 0; i <=8; i++) {
            for (int j = 1; j <= 9; j++) {
                int key = (i*9)+(j);
                Point3D  p = new Point3D(((j-1)*20)-80,(i*20)-80);
                node_data curr = new Node(key,p);
                d.addNode(curr);
                if (((key-1)%9)!=0) d.connect(key-1,key,1+((int)(Math.random()*20)));
                if (key>9) d.connect(key-9,key,1+(int)(Math.random()*20));
            }
        }
        d.connect(81,1,9999999);
        Graph_GUI g = new Graph_GUI(d);
    }
}

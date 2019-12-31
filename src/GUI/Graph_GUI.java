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
                        if (srcP.y() == dstP.y()) rx = 6;
                        else if (srcP.x() == dstP.x()) gy = 5;
                        else {
                            double m = (dstP.y() - srcP.y()) / (dstP.x() - srcP.x());
                            if (Math.abs(m) > 1) rx = 6;
                            else gy = 5;
                        }

                        StdDraw.text(tX + rx, tY + gy, "" + weight);
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
        d.connect(a1.getKey(),a5.getKey(),2);
        d.connect(a1.getKey(),a3.getKey(),6);
        d.connect(a1.getKey(),a6.getKey(),5);
        d.connect(a3.getKey(),a4.getKey(),7);
        d.connect(a2.getKey(),a8.getKey(),8);
        d.connect(a2.getKey(),a7.getKey(),3);
        d.connect(a5.getKey(),a1.getKey(),5);
        d.connect(a5.getKey(),a6.getKey(),2);
        d.connect(a6.getKey(),a1.getKey(),3);
        d.connect(a6.getKey(),a5.getKey(),3);
        d.connect(a6.getKey(),a7.getKey(),3);
        Graph_GUI g = new Graph_GUI(d);
    }
}

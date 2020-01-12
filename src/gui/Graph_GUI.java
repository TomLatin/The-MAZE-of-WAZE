package gui;

import algorithms.Graph_Algo;
import dataStructure.*;
import dataStructure.node_data;
import gameClient.MyGameGUI;
import utils.*;
import java.awt.*;
import java.util.List;


public class Graph_GUI extends Thread {

    //Fields
    private DGraph dGraph;
    private Graph_Algo graphAlgo;
    public static int keyEmpty = 1;
    public int expectedModCount = 0;



    /**
     * Default constructor
     */
    public Graph_GUI(){
        this.dGraph = new DGraph();
        this.graphAlgo = new Graph_Algo();
        StdDraw.GUI = this;
        this.start();
    }

    /**
     * A constructor that accepts DGraph to work on
     */
    public Graph_GUI(DGraph g){
        this.dGraph=g;
        this.graphAlgo=new Graph_Algo();
        this.graphAlgo.init(g);
        StdDraw.GUI = this;
        this.start();
    }

    /**
     * we will return the node that the location (X, Y) is at that node,
     * We will loop through the nodes of the graph until we find a node that corresponds to the desired location
     * @param x - The location on the X axis that is sent to find the node
     * @param y - The location on the Y axis that is sent to find the node
     * @return the node that the location (X, Y) is at that node
     */
    public node_data getNeerNode (double x, double y){
        for (node_data curr : dGraph.getV()){
            double currX = curr.getLocation().x();
            double currY = curr.getLocation().y();
            if ((x < currX+3.5) && (x > currX-3.5) && (y < currY+3.5) && (y > currY-3.5)) return curr;
        }
        return null;
    }





    /**
     * this method sketch of the graph on the opened window of the gui.
     */
    public void sketchGraph(Range RangeX, Range RangeY) {
        StdDraw.clear();
        Range xx = RangeX;
        Range yy = RangeY;
        double rightScaleX = ((xx.get_max()-xx.get_min())*0.004);
        double rightScaleY =  ((yy.get_max()-yy.get_min())*0.004);
        for (node_data currV : dGraph.getV()) {
            if (dGraph.getE(currV.getKey()) != null) {
                for (edge_data currE : dGraph.getE(currV.getKey())) {
                    if (currE.getDest() != currE.getSrc()) {
                        node_data srcN = currV;
                        node_data dstN = dGraph.getNode(currE.getDest());
                        Point3D srcP = srcN.getLocation();
                        Point3D dstP = dstN.getLocation();
                        StdDraw.setPenColor(Color.BLACK);
                        StdDraw.setPenRadius(rightScaleX*0.06);
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
                        if (srcP.y() == dstP.y()) gy = rightScaleX*4;
                        else if (srcP.x() == dstP.x()) rx = rightScaleX*5 ;
                        else {
                            double m = (dstP.y() - srcP.y()) / (dstP.x() - srcP.x());
                            if (Math.abs(m) > 1) rx = rightScaleX*4;
                            else gy = rightScaleX*3;
                        }
                        StdDraw.text(tX + rx, tY + gy, "" + (int)weight);
                        StdDraw.filledRectangle(tX, tY, rightScaleX*1.5, rightScaleY*2);
                    }
                }
            }
            for (node_data curr : dGraph.getV()) {
                StdDraw.setPenColor(Color.RED);
                StdDraw.setPenRadius(rightScaleX*0.1);
                Point3D p = curr.getLocation();
                StdDraw.filledCircle(p.x(), p.y(), rightScaleX*3);
                StdDraw.setPenColor(Color.YELLOW);
                StdDraw.text(p.x(), p.y()-rightScaleX*0.5, "" + curr.getKey());
            }
        }
    }

    /**
     * this method make sure thet changes in the graph will show in the gui.
     */
//    public void run(){
//        while (true){
//            if (this.dGraph.getMC()!=expectedModCount){
//                sketch();
//                this.expectedModCount=this.dGraph.getMC();
//            }
//        }
//    }

    public static void main(String[] args) {
        DGraph d = new DGraph();
        node_data t = new Node(88,new Point3D(120,120));
        d.addNode(t);
        Graph_GUI g = new Graph_GUI(d);
        for (int i = 0; i < 9; i++) {
            for (int j = 1; j <= 9; j++) {
                int key = (i*9)+(j);
                Point3D  p = new Point3D(((j-1)*20)-80,(i*20)-80);
                node_data curr = new Node(key,p);
                d.addNode(curr);
                if (((key-1)%9)!=0) d.connect(key-1,key,1+((int)(Math.random()*20)));
                if (key>9) d.connect(key-9,key,1+(int)(Math.random()*20));
            }
        }
        //d.connect(81,1,9999999);
        //Graph_GUI g = new Graph_GUI(d);
    }
}

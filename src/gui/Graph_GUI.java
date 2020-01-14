package gui;

import algorithms.Graph_Algo;
import dataStructure.*;
import dataStructure.node_data;
import gameClient.MyGameGUI;
import utils.*;
import java.awt.*;
import java.util.List;


public class Graph_GUI {

    //Fields
    private DGraph dGraph;
    private Graph_Algo graphAlgo;
    public static int keyEmpty = 1;



    /**
     * Default constructor
     */
    public Graph_GUI(){
        this.dGraph = new DGraph();
        this.graphAlgo = new Graph_Algo();
        StdDraw.GUI = this;
    }

    /**
     * A constructor that accepts DGraph to work on
     */
    public Graph_GUI(DGraph g){
        this.dGraph=g;
        this.graphAlgo=new Graph_Algo();
        this.graphAlgo.init(g);
        StdDraw.GUI = this;
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
                        StdDraw.setPenRadius(rightScaleX*60);
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
                        double printWeight = Math.round(weight*100.0)/100.0;
                        StdDraw.text(tX + rx, tY + gy, "" + printWeight);
                        StdDraw.filledRectangle(tX, tY, rightScaleX, rightScaleX);
                    }
                }
            }
        }
        for (node_data curr : dGraph.getV()) {
            StdDraw.setPenColor(Color.RED);
            StdDraw.setPenRadius(rightScaleX*0.1);
            Point3D p = curr.getLocation();
            StdDraw.filledCircle(p.x(), p.y(), rightScaleX*2.5);
            StdDraw.setPenColor(Color.YELLOW);
            StdDraw.text(p.x(), p.y()-rightScaleX*0.5, "" + curr.getKey());
        }
    }
}

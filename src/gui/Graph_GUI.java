package gui;

import algorithms.Graph_Algo;
import dataStructure.*;
import dataStructure.node_data;
import utils.*;
import java.awt.*;
import java.util.List;


public class Graph_GUI extends Thread {

    //Fields
    private DGraph dGraph;
    private Graph_Algo graphAlgo;
    public static int keyEmpty = 1;
    public int expectedModCount = 0;
    private Range rangeX;
    private Range rangeY;

    /**
     * Default constructor
     */
    public Graph_GUI(){
        this.dGraph = new DGraph();
        this.graphAlgo = new Graph_Algo();
        StdDraw.GUI = this;
        draw();
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
        draw();
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
     * We add a new Node to the graph
     * @param p - the location in the graph to add the new node
     */
    public void addNode(Point3D p){
        keyEmpty = this.dGraph.findNextKey();
        Node temp = new Node(keyEmpty,p);
        dGraph.addNode(temp);
    }

    /**
     * We connect a new Edge to the graph
     * @param src - the source of the edge.
     * @param dest - the destination of the edge.
     * @param weight - positive weight representing the cost (aka time, price, etc) between src-->dest.
     */
    public void addEdge(int src,int dest, double weight){
        dGraph.connect(src,dest,weight);
    }

    /**
     * Deleting a node from the graph,
     * note that When the node is deleted, all the edge containing the node are also deleted.
     * @param key - the key of the Node we need to delete.
     */
    public void deleteNode(int key) {
        Point3D loc = dGraph.getNode(key).getLocation();
        if (loc.x()==rangeX.get_min() || loc.x()==rangeX.get_max() ||loc.y()==rangeY.get_min() || loc.y()==rangeY.get_max()) {
            dGraph.removeNode(key);
            draw();
        }
        else dGraph.removeNode(key);
    }

    /**
     * Deleting a edge from the graph.
     * @param src - the source of the edge.
     * @param des - the destination of the edge.
     */
    public void deleteEdge(int src,int des) {
        dGraph.removeEdge(src,des);
    }

    /**
     * we returns true if and only if (iff) there is a valid path from EVREY node to each other node.
     * we used the method isConnected from Gragh_Algo.
     */
    public boolean isConected(){
        graphAlgo.init(dGraph);
        return graphAlgo.isConnected();
    }

    /**
     * returns the length of the shortest path between src to dest in the graph
     * @param src - start node
     * @param dest - end (target) node
     * we used the method shortestPathDist from Gragh_Algo.
     */
    public double shortestPathDist(int src, int dest) {
        graphAlgo.init(dGraph);
        return graphAlgo.shortestPathDist(src,dest);
    }

    /**
     * returns the the shortest path between src to dest - as an ordered List of nodes:
     * src -> n1 ->n2 ->...-> dest
     * see: https://en.wikipedia.org/wiki/Shortest_path_problem
     * @param src - start node
     * @param dest - end (target) node
     * we used the method shortestPath from Gragh_Algo.
     */
    public List<node_data> shortestPath(int src, int dest) {
        graphAlgo.init(dGraph);
        return graphAlgo.shortestPath(src,dest);
    }

    /**
     * computes a relatively short path which visit each node in the targets List.
     * @param chosen - the list of Nodes we need to pass in this path
     * we used the method TSP from Gragh_Algo.
     */
    public List<node_data> TSP (List<Integer> chosen){
        graphAlgo.init(dGraph);
        return graphAlgo.TSP(chosen);
    }

    /**
     * Saves the graph to a file.
     * @param filename - the name we give to the file we saving
     */
    public void save(String filename){
        graphAlgo.init(dGraph);
        graphAlgo.save(filename);

    }

    /**
     * Init a graph from file
     * @param filename the name of the file we loading
     */
    public void initGraph(String filename){
        graphAlgo.init(filename);
        dGraph=(DGraph)graphAlgo.copy();
        keyEmpty=dGraph.findNextKey();
        draw();
    }

    /**
     * We will find the range in the X axis where the graph is
     * @return toReturn/Default - the range X we give
     */
    public Range findRangeX(){
        if (dGraph.nodeSize()!=0){
            double min = -80;
            double max = 80;
            for (node_data curr : dGraph.getV()){
                if (curr.getLocation().x() > max) max = curr.getLocation().x();
                if (curr.getLocation().x() < min) min = curr.getLocation().x();
            }
            Range toReturn = new Range(min,max);
            rangeX = toReturn;
            return toReturn;
        }
        else {
            Range Default = new Range(-80,80);
            rangeX = Default;
            return Default;
        }
    }

    /**
     * We will find the range in the Y axis where the graph is
     * @return toReturn/Default - the range Y we give
     */
    public Range findRangeY(){
        if (dGraph.nodeSize()!=0){
            double min = -80;
            double max = 80;
            for (node_data curr : dGraph.getV()){
                if (curr.getLocation().x() > max) max = curr.getLocation().x();
                if (curr.getLocation().x() < min) min = curr.getLocation().x();
            }
            Range toReturn = new Range(min,max);
            rangeY = toReturn;
            return toReturn;
        }
        else {
            Range Default = new Range(-80,80);
            rangeY = Default;
            return Default;
        }
    }

    /**
     * this method open the frame that shows the gui of the graph.
     */
    public void draw(){
        Range x = findRangeX();
        Range y = findRangeY();
        StdDraw.setCanvasSize(1000,1000,this);
        StdDraw.setXscale(x.get_min()-20,x.get_max()+20);
        StdDraw.setYscale(y.get_min()-20,y.get_max()+20);
        sketch();
    }

    /**
     * this method sketch of the graph on the opened window of the gui.
     */
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
                        StdDraw.setPenRadius(0.006);
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
                        if (srcP.y() == dstP.y()) gy = 4;
                        else if (srcP.x() == dstP.x()) rx = 5 ;
                        else {
                            double m = (dstP.y() - srcP.y()) / (dstP.x() - srcP.x());
                            if (Math.abs(m) > 1) rx = 4;
                            else gy = 3;
                        }
                        StdDraw.text(tX + rx, tY + gy, "" + (int)weight);
                        StdDraw.filledRectangle(tX, tY, 1.3, 1.3);
                    }
                }
            }
            for (node_data curr : dGraph.getV()) {
                StdDraw.setPenColor(Color.RED);
                StdDraw.setPenRadius(0.1);
                Point3D p = curr.getLocation();
                StdDraw.filledCircle(p.x(), p.y(), 3);
                StdDraw.setPenColor(Color.YELLOW);
                StdDraw.text(p.x(), p.y()-0.5, "" + curr.getKey());
            }
        }
    }

    /**
     * this method make sure thet changes in the graph will show in the gui.
     */
    public void run(){
        while (true){
            if (this.dGraph.getMC()!=expectedModCount){
                sketch();
                this.expectedModCount=this.dGraph.getMC();
            }
        }
    }

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

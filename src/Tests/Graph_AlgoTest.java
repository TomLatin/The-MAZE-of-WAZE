package Tests;

import algorithms.Graph_Algo;
import algorithms.graph_algorithms;
import dataStructure.DGraph;
import dataStructure.Node;
import dataStructure.graph;
import dataStructure.node_data;
import org.junit.Test;
import utils.Point3D;
import dataStructure.Edge;
import org.junit.Before;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class Graph_AlgoTest {

    @Test
    public void init() {
        Point3D p = new Point3D(1,3);
        Point3D p1 = new Point3D(1,3);
        Point3D p2 = new Point3D(1,3);

        DGraph d = new DGraph();
        Node n1 = new Node(1,p);
        Node n2 = new Node(1,p1);
        Node n3 = new Node(1,p2);
        d.addNode(n1);
        d.addNode(n2);
        d.addNode(n3);
        Graph_Algo g = new Graph_Algo();
        g.init(d);
        assertEquals(g.Greph,d);
    }

    @Test
    public void testInit() {
        Point3D p = new Point3D(1,3);
        Point3D p1 = new Point3D(1,3);
        Point3D p2 = new Point3D(1,3);

        DGraph d = new DGraph();
        Node n1 = new Node(1,p);
        Node n2 = new Node(1,p1);
        Node n3 = new Node(1,p2);
        d.addNode(n1);
        d.addNode(n2);
        d.addNode(n3);
        Graph_Algo g = new Graph_Algo();
        g.init(d);
        g.save("aa");
        Graph_Algo a = new Graph_Algo();
        a.init("aa");
        assertEquals(g.Greph.edgeSize(),a.Greph.edgeSize());

    }

    @Test
    public void save() {
        Point3D p = new Point3D(1,3);
        Point3D p1 = new Point3D(1,3);
        Point3D p2 = new Point3D(1,3);

        DGraph d = new DGraph();
        Node n1 = new Node(1,p);
        Node n2 = new Node(1,p1);
        Node n3 = new Node(1,p2);
        d.addNode(n1);
        d.addNode(n2);
        d.addNode(n3);
        Graph_Algo g = new Graph_Algo();
        g.init(d);
        g.save("aa");
        Graph_Algo a = new Graph_Algo();
        a.init("aa");
        assertEquals(g.Greph.edgeSize(),a.Greph.edgeSize());
    }

    @Test
    public void isConnected() {
        Point3D p = new Point3D(1,3);
        Point3D p1 = new Point3D(1,3);
        Point3D p2 = new Point3D(1,3);

        DGraph d = new DGraph();
        Node n1 = new Node(1,p);
        Node n2 = new Node(2,p1);
        Node n3 = new Node(3,p2);
        d.addNode(n1);
        d.addNode(n2);
        d.addNode(n3);
        d.connect(1,2,5);
        d.connect(2,3,4);
        d.connect(3,1,6);
        Graph_Algo g = new Graph_Algo();
        g.init(d);
        assertEquals(true,g.isConnected());
    }

    @Test
    public void shortestPathDist() {
        Point3D p = new Point3D(1,3);
        Point3D p1 = new Point3D(1,3);
        Point3D p2 = new Point3D(1,3);

        DGraph d = new DGraph();
        Node n1 = new Node(1,p);
        Node n2 = new Node(2,p1);
        Node n3 = new Node(3,p2);
        d.addNode(n1);
        d.addNode(n2);
        d.addNode(n3);
        d.connect(1,2,5);
        d.connect(2,3,4);
        d.connect(3,1,6);
        Graph_Algo g = new Graph_Algo();
        g.init(d);
        double x = g.shortestPathDist(1,3);
        boolean flag = 9 == x;
        assertEquals(true,flag);
    }

    @Test
    public void shortestPath() {
        Point3D p = new Point3D(1,3);
        Point3D p1 = new Point3D(1,3);
        Point3D p2 = new Point3D(1,3);
        DGraph d = new DGraph();
        Node n1 = new Node(1,p);
        Node n2 = new Node(2,p1);
        Node n3 = new Node(3,p2);
        d.addNode(n1);
        d.addNode(n2);
        d.addNode(n3);
        d.connect(1,2,5);
        d.connect(2,3,4);
        d.connect(3,1,6);
        Graph_Algo g = new Graph_Algo();
        g.init(d);
        List<node_data> list = g.shortestPath(1,3);
        assertEquals(list.get(0),n1);
        assertEquals(list.get(1),n2);
        assertEquals(list.get(2),n3);
    }
    @Test
    public void TSPTest(){
        Point3D p = new Point3D(1,3);
        Point3D p1 = new Point3D(1,3);
        Point3D p2 = new Point3D(1,3);
        DGraph d = new DGraph();
        Node n1 = new Node(1,p);
        Node n2 = new Node(2,p1);
        Node n3 = new Node(3,p2);
        d.addNode(n1);
        d.addNode(n2);
        d.addNode(n3);
        d.connect(1,2,5);
        d.connect(2,3,4);
        d.connect(3,1,6);
        Graph_Algo g = new Graph_Algo();
        g.init(d);
        List<Integer> l = new LinkedList<>();
        l.add(1);
        l.add(2);
        l.add(3);
        List<node_data> list = g.TSP(l);
        assertEquals(list.get(0),n1);
        assertEquals(list.get(1),n2);
        assertEquals(list.get(2),n3);
    }
}
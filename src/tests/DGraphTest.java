package tests;

import GUI.Graph_GUI;
import dataStructure.DGraph;
import dataStructure.Edge;
import dataStructure.Node;
import dataStructure.node_data;
import org.junit.Before;
import org.junit.Test;
import utils.Point3D;

import static org.junit.Assert.*;

public class DGraphTest {

    static Point3D x = new Point3D(1,8);
    static Point3D y = new Point3D(2,6);
    static Point3D z = new Point3D(6,9);
    static Point3D t = new Point3D(4,13);
    static Point3D r = new Point3D(5,17);

    static Node[] n0=new Node[2];
    static Node[] n1=new Node[2];
    static Node[] n2=new Node[3];
    static Node[] n3=new Node[5];

    static Edge[] e0=new Edge[1];
    static Edge[] e2=new Edge[4];
    static Edge[] e3=new Edge[7];

    @Before
    public void BeforeEach() {
        //Create Dgraph: a0 -> b0
        DGraph g0 = new DGraph();
        Node a0 = new Node(g0.findNextKey(), x);
        Node b0 = new Node(g0.findNextKey(), y);

        g0.addNode(a0);
        g0.addNode(b0);
        g0.connect(a0.getKey(),b0.getKey(),4);

        //Initialize the array of Node's
        n0[0]=a0;
        n0[1]=b0;

        //Initialize the array of Edge's
        e0[0]=new Edge(a0.getKey(),b0.getKey(),4.0);

        //---------------------------------------------------------//
        //Create Dgraph: a1,b1 (standalone)
        DGraph g1 = new DGraph();
        Node a1 = new Node(g1.findNextKey(), x);
        Node b1 = new Node(g1.findNextKey(),y);

        g1.addNode(a1);
        g1.addNode(b1);

        //Initialize the array of Node's
        n1[0]=a1;
        n1[1]=b1;

        //---------------------------------------------------------//
        /*Create Dgraph: a2 -> b2
                         b2 -> c2
                         c2 -> a2
                         a2 -> c2
         */
        DGraph g2 = new DGraph();
        Node a2 = new Node(g2.findNextKey(), x);
        Node b2 = new Node(g2.findNextKey(),y);
        Node c2 = new Node(g2.findNextKey(),z);

        g2.addNode(a2);
        g2.addNode(b2);
        g2.addNode(c2);
        g2.connect(a2.getKey(),b2.getKey(),4);
        g2.connect(b2.getKey(),c2.getKey(),5);
        g2.connect(c2.getKey(),a2.getKey(),8);
        g2.connect(a2.getKey(),c2.getKey(),11);

        //Initialize the array of Node's
        n2[0]=a2;
        n2[1]=b2;
        n2[2]=c2;

        //Initialize the array of Edge's
        e2[0]=new Edge(a2.getKey(),b2.getKey(),4.0);
        e2[1]=new Edge(b2.getKey(),c2.getKey(),5);
        e2[2]=new Edge(c2.getKey(),a2.getKey(),8);
        e2[3]=new Edge(a2.getKey(),c2.getKey(),11);


        //---------------------------------------------------------//
        /*Create Dgraph: a3 -> b3
                         b3 -> c3
                         c3 -> a3
                         a3 -> c3
                         a3 -> d3
                         d3 -> e3
                         e3 -> b3
         */
        DGraph g3 = new DGraph();
        Node a3 = new Node(g3.findNextKey(), x);
        Node b3 = new Node(g3.findNextKey(), y);
        Node c3 = new Node(g3.findNextKey(), z);
        Node d3 = new Node(g3.findNextKey(), t);
        Node e3 = new Node(g3.findNextKey(), r);

        g3.addNode(a3);
        g3.addNode(b3);
        g3.addNode(c3);
        g3.connect(a3.getKey(),b3.getKey(),4);
        g3.connect(b3.getKey(),c3.getKey(),5);
        g3.connect(c3.getKey(),a3.getKey(),8);
        g3.connect(a3.getKey(),c3.getKey(),11);
        g3.connect(a3.getKey(),d3.getKey(),6);
        g3.connect(d3.getKey(),e3.getKey(),7);
        g3.connect(e3.getKey(),b3.getKey(),7);

        //Initialize the array of Node's
        n3[0]=a3;
        n3[1]=b3;
        n3[2]=c3;
        n3[3]=d3;
        n3[4]=e3;

        //Initialize the array of Edge's
//        e3[0] =new Edge(a3.getKey(),b3.getKey(),4);


    }

    @Test
    public void getNode() {

    }

    @Test
    public void getEdge() {
    }

    @Test
    public void addNode() {
    }

    @Test
    public void connect() {
    }

    @Test
    public void getV() {
    }

    @Test
    public void getE() {
    }

    @Test
    public void removeNode() {
    }

    @Test
    public void removeEdge() {
    }

    @Test
    public void nodeSize() {
    }

    @Test
    public void edgeSize() {
    }

    @Test
    public void getMC() {
    }
}
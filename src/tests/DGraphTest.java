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
    static DGraph g0 = new DGraph();
    static DGraph g1 = new DGraph();
    static DGraph g2 = new DGraph();
    static DGraph g3 = new DGraph();

    static Point3D x = new Point3D(1,8);
    static Point3D y = new Point3D(2,6);
    static Point3D z = new Point3D(6,9);
    static Point3D t = new Point3D(4,13);
    static Point3D r = new Point3D(5,17);

    static Node[] arrN0=new Node[2];
    static Node[] arrN1=new Node[2];
    static Node[] arrN2=new Node[3];
    static Node[] arrN3=new Node[5];

    static Edge[] arrE0=new Edge[1];
    static Edge[] arrE1=new Edge[4];
    static Edge[] arrE3=new Edge[7];

    @Before
    public void BeforeEach() {
        //Create Dgraph: a0 -> b0
        Node a0 = new Node(1, x);
        Node b0 = new Node(2, y);

        g0.addNode(a0);
        g0.addNode(b0);
        g0.connect(a0.getKey(),b0.getKey(),4);

        //Initialize the array of Node's
        arrN0[0]=a0;
        arrN0[1]=b0;

        //Initialize the array of Edge's
        arrE0[0]=new Edge(a0.getKey(),b0.getKey(),4.0);

        //---------------------------------------------------------//
        //Create Dgraph: a1,b1 (standalone)
        Node a1 = new Node(1, x);
        Node b1 = new Node(2,y);

        g1.addNode(a1);
        g1.addNode(b1);

        //Initialize the array of Node's
        arrN1[0]=a1;
        arrN1[1]=b1;

        //---------------------------------------------------------//
        /*Create Dgraph: a2 -> b2
                         b2 -> c2
                         c2 -> a2
                         a2 -> c2
         */
        Node a2 = new Node(1, x);
        Node b2 = new Node(2,y);
        Node c2 = new Node(3,z);

        g2.addNode(a2);
        g2.addNode(b2);
        g2.addNode(c2);
        g2.connect(a2.getKey(),b2.getKey(),4);
        g2.connect(b2.getKey(),c2.getKey(),5);
        g2.connect(c2.getKey(),a2.getKey(),8);
        g2.connect(a2.getKey(),c2.getKey(),11);

        //Initialize the array of Node's
        arrN2[0]=a2;
        arrN2[1]=b2;
        arrN2[2]=c2;

        //Initialize the array of Edge's
        arrE1[0]=new Edge(a2.getKey(),b2.getKey(),4.0);
        arrE1[1]=new Edge(b2.getKey(),c2.getKey(),5);
        arrE1[2]=new Edge(c2.getKey(),a2.getKey(),8);
        arrE1[3]=new Edge(a2.getKey(),c2.getKey(),11);


        //---------------------------------------------------------//
        /*Create Dgraph: a3 -> b3
                         b3 -> c3
                         c3 -> a3
                         a3 -> c3
                         a3 -> d3
                         d3 -> e3
                         e3 -> b3
         */
        Node a3 = new Node(1, x);
        Node b3 = new Node(2, y);
        Node c3 = new Node(3, z);
        Node d3 = new Node(4, t);
        Node e3 = new Node(5, r);

        g3.addNode(a3);
        g3.addNode(b3);
        g3.addNode(c3);
        g3.addNode(d3);
        g3.addNode(e3);
        g3.connect(a3.getKey(),b3.getKey(),4);
        g3.connect(b3.getKey(),c3.getKey(),5);
        g3.connect(c3.getKey(),a3.getKey(),8);
        g3.connect(a3.getKey(),c3.getKey(),11);
        g3.connect(a3.getKey(),d3.getKey(),6);
        g3.connect(d3.getKey(),e3.getKey(),7);
        g3.connect(e3.getKey(),b3.getKey(),7);

        //Initialize the array of Node's
        arrN3[0]=a3;
        arrN3[1]=b3;
        arrN3[2]=c3;
        arrN3[3]=d3;
        arrN3[4]=e3;

//        Initialize the array of Edge's
        arrE3[0] =new Edge(a3.getKey(),b3.getKey(),4);
        arrE3[1] =new Edge(b3.getKey(),c3.getKey(),5);
        arrE3[2] =new Edge(c3.getKey(),a3.getKey(),8);
        arrE3[3] =new Edge(a3.getKey(),c3.getKey(),11);
        arrE3[4] =new Edge(a3.getKey(),d3.getKey(),6);
        arrE3[5] =new Edge(d3.getKey(),e3.getKey(),7);
        arrE3[6] =new Edge(e3.getKey(),b3.getKey(),7);



    }
    @Test
    public void DGraph() {

    }

    @Test
    public void getNode() {
        for (int i = 0; i <arrN0.length ; i++) {
            assertEquals(arrN0[i],g0.getNode(i+1));
        }

        for (int i = 0; i <arrN1.length ; i++) {
            assertEquals(arrN1[i],g1.getNode(i+1));
        }

        for (int i = 0; i <arrN2.length ; i++) {
            assertEquals(arrN2[i],g2.getNode(i+1));
        }

        for (int i = 0; i <arrN3.length ; i++) {
            assertEquals(arrN3[i],g3.getNode(i+1));
        }

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
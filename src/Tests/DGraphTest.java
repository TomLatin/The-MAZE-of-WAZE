package tests;

import algorithms.Graph_Algo;
import dataStructure.*;
import org.junit.Before;
import org.junit.Test;
import utils.Point3D;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class DGraphTest {
    //creat DGraph's
    static DGraph g0 = new DGraph();
    static DGraph g1 = new DGraph();
    static DGraph g2 = new DGraph();
    static DGraph g3 = new DGraph();

    //creat points to the node's
    static Point3D x = new Point3D(1,8);
    static Point3D y = new Point3D(2,6);
    static Point3D z = new Point3D(6,9);
    static Point3D t = new Point3D(4,13);
    static Point3D r = new Point3D(5,17);

    //creat arrays for the node
    static Node[] arrN0=new Node[2];
    static Node[] arrN1=new Node[2];
    static Node[] arrN2=new Node[3];
    static Node[] arrN3=new Node[5];

    //creat arrays for the edges
    static Edge[] arrE0=new Edge[1];
    static Edge[] arrE2 =new Edge[4];
    static Edge[] arrE3=new Edge[7];

    static Node notEqual = new Node(100,new Point3D(100,100));

    @Before
    public void BeforeEach() {
        g0 = new DGraph();
        g1 = new DGraph();
        g2 = new DGraph();
        g3 = new DGraph();

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
        arrE2[0]=new Edge(a2.getKey(),b2.getKey(),4.0);
        arrE2[1]=new Edge(b2.getKey(),c2.getKey(),5);
        arrE2[2]=new Edge(c2.getKey(),a2.getKey(),8);
        arrE2[3]=new Edge(a2.getKey(),c2.getKey(),11);


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

    /**
     * Checks whether the Default constructor is working properly
     */
    @Test
    public void DGraph() {
        DGraph d=new DGraph();
        assertEquals(new HashMap<>(),d.nodeGraph);
        assertEquals(new HashMap<Integer,HashMap<Integer, edge_data>>(),d.nodeGraph);
        assertEquals(0, d.nodeSize());
        assertEquals(0, d.edgeSize());

    }

    /**
     * Checks whether the getNode function is working properly, which means that it returns the corresponding node
     * according to the key
     */
    @Test
    public void getNode() {
        //Checks whether the DGraph g0 returns the correct nodes keys
        for (int i = 0; i <arrN0.length ; i++) {
            assertEquals(arrN0[i],g0.getNode(i+1));
            assertNotEquals(notEqual,g0.getNode(i+1));
            assertNotNull(g0.getNode(i+1));
        }

        //Checks whether the DGraph g1 returns the correct nodes keys
        for (int i = 0; i <arrN1.length ; i++) {
            assertEquals(arrN1[i],g1.getNode(i+1));
            assertNotEquals(notEqual,g1.getNode(i+1));
            assertNotNull(g1.getNode(i+1));
        }

        //Checks whether the DGraph g2 returns the correct nodes keys
        for (int i = 0; i <arrN2.length ; i++) {
            assertEquals(arrN2[i],g2.getNode(i+1));
            assertNotEquals(notEqual,g2.getNode(i+1));
            assertNotNull(g2.getNode(i+1));
        }

        //Checks whether the DGraph g3 returns the correct nodes keys
        for (int i = 0; i <arrN3.length ; i++) {
            assertEquals(arrN3[i],g3.getNode(i+1));
            assertNotEquals(notEqual,g3.getNode(i+1));
            assertNotNull(g3.getNode(i+1));
        }

        // Checks whether exception-throwing is working properly,which means we enter a key that does not exist and expect it
        // to throw an error and then we count the amount of exception-throwing.this test does for all Dgraphs
        int counter=0;
        for (int i = 6; i <10 ; i++) {
            try{
                assertEquals(arrN0[i],g0.getNode(i+1));
            }
            catch (Exception e)
            {
                counter++;
            }
            try{
                assertEquals(arrN1[i],g1.getNode(i+1));
            }
            catch (Exception e)
            {
                counter++;
            }
            try{
                assertEquals(arrN2[i],g2.getNode(i+1));
            }
            catch (Exception e)
            {
                counter++;
            }
            try{
                assertEquals(arrN3[i],g3.getNode(i+1));
            }
            catch (Exception e)
            {
                counter++;
            }

        }
        assertEquals(16,counter);



    }

    /**
     * Checks if the getEdge function is working properly, ie returns the appropriate edge based on the source key and
     * the target key
     */
    @Test
    public void getEdge() {
        //Checks whether the DGraph g0 returns the correct edge's
        for (int i = 0; i <arrE0.length ; i++) {
            assertEquals(arrE0[i].toString(),g0.getEdge(arrE0[i].getSrc(),arrE0[i].getDest()).toString());
        }

        //Checks whether the DGraph g2 returns the correct edge's
        for (int i = 0; i < arrE2.length ; i++) {
            assertEquals(arrE2[i].toString(),g2.getEdge(arrE2[i].getSrc(), arrE2[i].getDest()).toString());
        }

        //Checks whether the DGraph g3 returns the correct edge's
        for (int i = 0; i <arrE3.length ; i++) {
            assertEquals(arrE3[i].toString(),g3.getEdge(arrE3[i].getSrc(),arrE3[i].getDest()).toString());
        }
    }

    /**
     * Checks whether the addNode function is working properly,That is, adds a new node and if it exists the node is
     * replaced with the new node
     */
    @Test
    public void addNode() {
        Node toAdd0 = new Node(9, x);
        Node toAdd1 = new Node(10, x);
        Node toAdd2 = new Node(11, x);
        Node toAdd3 = new Node(12, x);
        Node toAdd4 = new Node(13, x);

        //Adds the node toAdd0 to all graphs
        g0.addNode(toAdd0);
        g1.addNode(toAdd0);
        g2.addNode(toAdd0);
        g3.addNode(toAdd0);

        //Adds the node toAdd1 to all graphs
        g0.addNode(toAdd1);
        g1.addNode(toAdd1);
        g2.addNode(toAdd1);
        g3.addNode(toAdd1);

        //Adds the node toAdd2 to all graphs
        g0.addNode(toAdd2);
        g1.addNode(toAdd2);
        g2.addNode(toAdd2);
        g3.addNode(toAdd2);

        //Adds the node toAdd3 to all graphs
        g0.addNode(toAdd3);
        g1.addNode(toAdd3);
        g2.addNode(toAdd3);
        g3.addNode(toAdd3);

        //Adds the node toAdd4 to all graphs
        g0.addNode(toAdd4);
        g1.addNode(toAdd4);
        g2.addNode(toAdd4);
        g3.addNode(toAdd4);

        //Checks that in all graphs all vertices are not null
        for (int i = 9; i <13 ; i++) {
            assertNotNull(g0.getNode(i));
            assertNotNull(g1.getNode(i));
            assertNotNull(g2.getNode(i));
            assertNotNull(g3.getNode(i));
        }

        //Compares the objects
        assertEquals(toAdd0,g0.getNode(9));
        assertEquals(toAdd0,g1.getNode(9));
        assertEquals(toAdd0,g2.getNode(9));
        assertEquals(toAdd0,g3.getNode(9));

        assertEquals(toAdd1,g0.getNode(10));
        assertEquals(toAdd1,g1.getNode(10));
        assertEquals(toAdd1,g2.getNode(10));
        assertEquals(toAdd1,g3.getNode(10));

        assertEquals(toAdd2,g0.getNode(11));
        assertEquals(toAdd2,g1.getNode(11));
        assertEquals(toAdd2,g2.getNode(11));
        assertEquals(toAdd2,g3.getNode(11));

        assertEquals(toAdd3,g0.getNode(12));
        assertEquals(toAdd3,g1.getNode(12));
        assertEquals(toAdd3,g2.getNode(12));
        assertEquals(toAdd3,g3.getNode(12));

        assertEquals(toAdd4,g0.getNode(13));
        assertEquals(toAdd4,g1.getNode(13));
        assertEquals(toAdd4,g2.getNode(13));
        assertEquals(toAdd4,g3.getNode(13));

        //Checks that the switching of node with the same key is performed properly
        Node toAdd5 = new Node(9, y);
        g0.addNode(toAdd5);
        g0.connect(9,1,9);
        assertEquals(toAdd5,g0.getNode(9));
        assertNotNull(g0.getNode(9));
        assertNotNull(g0.getE(9));
    }

    /**
     * hecks whether the connect function is working properly, That is, adds a new edge and if it exists the edge is
     * replaced with the new edge.If the weight is negative the function throws an exception
     */
    @Test
    public void connect() {
        //Checks all graphs that if you put a negative weight in the connect function, an exception is thrown
        int count=0;
        try{
            g0.connect(arrN0[0].getKey(),arrN0[1].getKey(),-4);
        }
        catch (Exception e)
        {
            count++;
        }

        try{
            g1.connect(arrN0[0].getKey(),arrN0[1].getKey(),-4);
        }
        catch (Exception e)
        {
            count++;
        }

        try{
            g2.connect(arrN0[0].getKey(),arrN0[1].getKey(),-4);
        }
        catch (Exception e)
        {
            count++;
        }
        try{
            g3.connect(arrN0[0].getKey(),arrN0[1].getKey(),-4);
        }
        catch (Exception e)
        {
            count++;
        }

        assertEquals(4,count);

        //inserting new edges into graphs
        g0.connect(arrN0[1].getKey(),arrN0[0].getKey(),6);
        g1.connect(arrN1[0].getKey(),arrN1[1].getKey(),6);
        g2.connect(arrN2[0].getKey(),arrN2[1].getKey(),6);
        g3.connect(arrN3[0].getKey(),arrN3[1].getKey(),6);

        //Checks that the new edges are present
        assertNotNull(g0.getEdge(arrN0[1].getKey(),arrN0[0].getKey()));
        assertNotNull(g1.getEdge(arrN1[0].getKey(),arrN1[1].getKey()));
        assertNotNull(g2.getEdge(arrN2[0].getKey(),arrN2[1].getKey()));
        assertNotNull(g3.getEdge(arrN3[0].getKey(),arrN3[1].getKey()));

        //Checks that the new edges have the right weight
        assertEquals(6,g0.getEdge(arrN0[1].getKey(),arrN0[0].getKey()).getWeight(),0);
        assertEquals(6,g1.getEdge(arrN1[0].getKey(),arrN1[1].getKey()).getWeight(),0);
        assertEquals(6,g2.getEdge(arrN2[0].getKey(),arrN2[1].getKey()).getWeight(),0);
        assertEquals(6,g3.getEdge(arrN3[0].getKey(),arrN3[1].getKey()).getWeight(),0);

        //change the Weight in exist edges
        g0.connect(arrN0[1].getKey(),arrN0[0].getKey(),9);
        g1.connect(arrN1[0].getKey(),arrN1[1].getKey(),9);
        g2.connect(arrN2[0].getKey(),arrN2[1].getKey(),9);
        g3.connect(arrN3[0].getKey(),arrN3[1].getKey(),9);

        //Checks that the change is made
        assertNotNull(g0.getEdge(arrN0[1].getKey(),arrN0[0].getKey()));
        assertNotNull(g1.getEdge(arrN1[0].getKey(),arrN1[1].getKey()));
        assertNotNull(g2.getEdge(arrN2[0].getKey(),arrN2[1].getKey()));
        assertNotNull(g3.getEdge(arrN3[0].getKey(),arrN3[1].getKey()));

        assertEquals(9,g0.getEdge(arrN0[1].getKey(),arrN0[0].getKey()).getWeight(),0);
        assertEquals(9,g1.getEdge(arrN1[0].getKey(),arrN1[1].getKey()).getWeight(),0);
        assertEquals(9,g2.getEdge(arrN2[0].getKey(),arrN2[1].getKey()).getWeight(),0);
        assertEquals(9,g3.getEdge(arrN3[0].getKey(),arrN3[1].getKey()).getWeight(),0);

    }

    /**
     * Checks whether the removeNode function is working properly
     */
    @Test
    public void removeNode() {
        //remove node from all graphs
        g0.removeNode(1);
        g1.removeNode(1);
        g2.removeNode(1);
        g3.removeNode(1);

        int counter=0;

         if( g0.getNode(1)==null) counter++;
         if( g1.getNode(1)==null) counter++;
         if( g2.getNode(1)==null) counter++;
         if( g3.getNode(1)==null) counter++;

        assertEquals(4,counter);


        //Checks whether the DGraph g0 returns the correct nodes keys
        for (int i = 1; i <arrN0.length ; i++) {
            assertEquals(arrN0[i],g0.getNode(i+1));
            assertNotEquals(notEqual,g0.getNode(i+1));
            assertNotNull(g0.getNode(i+1));
        }

        //Checks whether the DGraph g1 returns the correct nodes keys
        for (int i = 1; i <arrN1.length ; i++) {
            assertEquals(arrN1[i],g1.getNode(i+1));
            assertNotEquals(notEqual,g1.getNode(i+1));
            assertNotNull(g1.getNode(i+1));
        }

        //Checks whether the DGraph g2 returns the correct nodes keys
        for (int i = 1; i <arrN2.length ; i++) {
            assertEquals(arrN2[i],g2.getNode(i+1));
            assertNotEquals(notEqual,g2.getNode(i+1));
            assertNotNull(g2.getNode(i+1));
        }

        //Checks whether the DGraph g3 returns the correct nodes keys
        for (int i = 1; i <arrN3.length ; i++) {
            assertEquals(arrN3[i],g3.getNode(i+1));
            assertNotEquals(notEqual,g3.getNode(i+1));
            assertNotNull(g3.getNode(i+1));
        }
    }

    /**
     * Checks whether the removeEdge function is working properly
     */
    @Test
    public void removeEdge() {

        //remove edge from all graphs
        g0.removeEdge(1,2);
        g2.removeEdge(2,3);
        g3.removeEdge(2,3);

        int counter=0;

        if( g0.getEdge(1,2)==null) counter++;
        if( g2.getEdge(2,3)==null) counter++;
        if( g3.getEdge(2,3)==null) counter++;

        assertEquals(3,counter);

    }

    /**
     * Checks whether the nodeSize function is working properly
     */
    @Test
    public void nodeSize() {
        assertEquals(arrN0.length,g0.nodeSize());
        assertEquals(arrN1.length,g1.nodeSize());
        assertEquals(arrN2.length,g2.nodeSize());
        assertEquals(arrN3.length,g3.nodeSize());
    }

    /**
     * Checks whether the edgeSize function is working properly
     */
    @Test
    public void edgeSize() {
        assertEquals(arrE0.length,g0.edgeSize());
        assertEquals(0,g1.edgeSize());
        assertEquals(arrE2.length,g2.edgeSize());
        assertEquals(arrE3.length,g3.edgeSize());

    }

    /**
     * Checks whether the getMC function is working correctly, which means counting the amount of graph changes properly
     * the changes that can be adding a node, adding a edge, removing a node, and removing a edge
     */
    @Test
    public void getMC() {
        Node toAdd1=new Node(6, new Point3D(55,9));
        Node toAdd2=new Node(7, new Point3D(6,0));

        int beforeChange0=g0.getMC();
        g0.addNode(toAdd1);
        g0.addNode(toAdd2);
        g0.connect(toAdd1.getKey(),toAdd2.getKey(),100);
        g0.removeEdge(toAdd1.getKey(),toAdd2.getKey());
        g0.removeNode(toAdd1.getKey());
        g0.removeNode(toAdd2.getKey());

        int beforeChange1=g1.getMC();
        g1.addNode(toAdd1);
        g1.addNode(toAdd2);
        g1.connect(toAdd1.getKey(),toAdd2.getKey(),100);
        g1.removeEdge(toAdd1.getKey(),toAdd2.getKey());
        g1.removeNode(toAdd1.getKey());
        g1.removeNode(toAdd2.getKey());

        int beforeChange2=g2.getMC();
        g2.addNode(toAdd1);
        g2.addNode(toAdd2);
        g2.connect(toAdd1.getKey(),toAdd2.getKey(),100);
        g2.removeEdge(toAdd1.getKey(),toAdd2.getKey());
        g2.removeNode(toAdd1.getKey());
        g2.removeNode(toAdd2.getKey());

        int beforeChange3=g3.getMC();
        g3.addNode(toAdd1);
        g3.addNode(toAdd2);
        g3.connect(toAdd1.getKey(),toAdd2.getKey(),100);
        g3.removeEdge(toAdd1.getKey(),toAdd2.getKey());
        g3.removeNode(toAdd1.getKey());
        g3.removeNode(toAdd2.getKey());


        assertEquals(beforeChange0+beforeChange1+beforeChange2+beforeChange3+24,g0.getMC()+g1.getMC()+g2.getMC()+g3.getMC());

    }

}
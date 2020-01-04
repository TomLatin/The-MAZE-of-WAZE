package tests;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.Edge;
import dataStructure.Node;
import dataStructure.node_data;
import org.junit.Before;
import org.junit.Test;
import utils.Point3D;

import java.util.Collection;
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

    @Test
    public void DGraph() {

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

    @Test
    public void addNode() {

    }

    @Test
    public void connect() {
    }

    /**
     * Checks whether the getV function works correctly, which means that it returns the collection of node data
     */
    @Test
    public void getV() {
        //create the collection of the node's
        Collection <node_data> c0= g0.getV();
        Collection <node_data> c1= g1.getV();
        Collection <node_data> c2= g2.getV();
        Collection <node_data> c3= g3.getV();
        //-----------------------------------------//

        int i=1;
            for (node_data currV : c0) {
                assertEquals(currV, g0.getNode(i));
                i++;
            }

        i=1;
        for (node_data currV : c1){
            assertEquals(currV,g1.getNode(i));
            i++;
        }

        i=1;
        for (node_data currV : c2){
            assertEquals(currV,g2.getNode(i));
            i++;
        }

        i=1;
        for (node_data currV :c3){
            assertEquals(currV,g3.getNode(i));
            i++;
        }

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
        assertEquals(arrN0.length,g0.nodeSize());
        assertEquals(arrN1.length,g1.nodeSize());
        assertEquals(arrN2.length,g2.nodeSize());
        assertEquals(arrN3.length,g3.nodeSize());
    }

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

    @Test
    public void Test(){
        Graph_Algo G = new Graph_Algo();
        Point3D p00 = new Point3D(1, 6, 0);
        Point3D p11 = new Point3D(0, 2, 3);
        Point3D p22 = new Point3D(1, 4, 0);
        Point3D p33 = new Point3D(5, 2, 0);
        Point3D p44 = new Point3D(6,5, 0);
        Point3D p55 = new Point3D(4,6, 0);
        Point3D p66 = new Point3D(3,5, 0);
        Point3D p77 = new Point3D(4,10,0);
        Point3D p88 = new Point3D(4.10,0);
        Point3D p99 = new Point3D(1,30);
        Point3D p10 = new Point3D(10,40);
        node_data node1 = new Node(1,p00);
        node_data node2 = new Node(2,p11);
        node_data node3 = new Node(3,p22);
        node_data node4 = new Node(4,p33);
        node_data node5 = new Node(5,p44);
        node_data node6 = new Node(6,p55);
        node_data node7 = new Node(7,p66);
        node_data node8 = new Node(8,p77);
        node_data node9 =new Node(9,p88);
        node_data node10 = new Node(10,p99);
        node_data node11 = new Node(11,p10);

        DGraph Dg = new DGraph();
        Dg.addNode(node1);
        Dg.addNode(node2);
        Dg.addNode(node3);
        Dg.addNode(node4);
        Dg.addNode(node5);
        Dg.addNode(node6);
        Dg.addNode(node7);
        Dg.addNode(node8);
        Dg.addNode(node9);
        Dg.addNode(node10);
        Dg.addNode(node11);


        Dg.connect(node1.getKey(), node2.getKey(), 5);
        Dg.connect(node1.getKey(), node3.getKey(), 3);
        Dg.connect(node1.getKey(), node4.getKey(), 2);
        Dg.connect(node2.getKey(), node5.getKey(), 2);
        Dg.connect(node3.getKey(), node6.getKey(), 4);
        Dg.connect(node3.getKey(),node1.getKey(),2);
        Dg.connect(node4.getKey(), node6.getKey(), 4);
        Dg.connect(node4.getKey(), node7.getKey(), 2);
        Dg.connect(node5.getKey(), node8.getKey(), 6);
        Dg.connect(node5.getKey(), node7.getKey(), 1);
        Dg.connect(node5.getKey(),node2.getKey(),4);
        Dg.connect(node6.getKey(),node11.getKey(),3);
        Dg.connect(node7.getKey(),node8.getKey(),4);
        Dg.connect(node7.getKey(),node6.getKey(),1);
        Dg.connect(node7.getKey(),node11.getKey(),9);
        Dg.connect(node8.getKey(),node7.getKey(),1);
        Dg.connect(node8.getKey(),node9.getKey(),9);
        Dg.connect(node9.getKey(),node8.getKey(),3);
        Dg.connect(node9.getKey(),node10.getKey(),5);
        Dg.connect(node10.getKey(),node9.getKey(),2);
        Dg.connect(node10.getKey(),node11.getKey(),1);
        Dg.connect(node11.getKey(),node10.getKey(),2);

        G.init(Dg);
        System.out.println("Distance betwenn 1-6 is :" + G.shortestPathDist(node1.getKey(),node6.getKey()));
        System.out.println("Distance between 6-7 is : " + G.shortestPathDist(node6.getKey(),node7.getKey()));
        System.out.println("Distance between 4-1 is : " + G.shortestPathDist(node4.getKey(),node1.getKey()));
        System.out.println("Distance between 7-9 is : " + G.shortestPathDist(node7.getKey(),node9.getKey()));
        System.out.println("Distance between 3-2 is : " + G.shortestPathDist(node3.getKey(),node2.getKey()));

        System.out.println("The graph is Connected :" + G.isConnected());
        System.out.println("The shortest path between 5-10 is :" + G.shortestPath(node5.getKey(),node10.getKey()));
        System.out.println("The shortest path between 10-1 is :" + G.shortestPath(node10.getKey(),node1.getKey()));
        System.out.println("The shortest path between 7-2 is :" + G.shortestPath(node7.getKey(),node2.getKey()));
        System.out.println("The shortest path between 1-9 is :" + G.shortestPath(node1.getKey(),node9.getKey()));

        List<Integer> ans = new LinkedList<>();
        ans.add(1);
        ans.add(7);
        ans.add(3);
        ans.add(10);
        List<Integer> ans2 = new LinkedList<>();
        ans2.add(1);
        ans2.add(10);
        ans2.add(4);
        ans2.add(5);
        System.out.println("TSP[1,7,3,10] is: " +  G.TSP(ans));
        System.out.println("TSP[10,1,4,5] is : " + G.TSP(ans2));
    }


}
package tests;

import org.junit.Test;
import dataStructure.Edge;
import org.junit.Before;
import static org.junit.Assert.*;

public class EdgeTest {

    public static final double EPSILON = 0.00001;
    static Edge[] arrEdges = new Edge[10];

    @Before
    public void BeforeEach() {
        arrEdges[0]=new Edge();
        arrEdges[1]=new Edge(1,2,7.6);
        arrEdges[2]=new Edge(8,17,3);
        arrEdges[3]=new Edge(10,10,7);
        arrEdges[4]=new Edge(10,20,9.8);
        arrEdges[5]=new Edge(11,22,30);
        arrEdges[6]=new Edge(4,2,1);
        arrEdges[7]=new Edge(9,1,4);
        arrEdges[8]=new Edge(22,11,5);
        arrEdges[9]=new Edge(2,10,8);

    }

    /**
     * Checks whether the function returns the src correctly
     */
    @Test
    public void getSrc() {
        int [] expected ={0,1,8,10,10,11,4,9,22,2};
        for (int i = 0; i <arrEdges.length ; i++) {
            assertEquals(expected[i],arrEdges[i].getSrc());
            assertNotEquals(100,arrEdges[i].getSrc());
            assertNotNull(arrEdges[i].getSrc());
        }
    }

    /**
     * Checks whether the function returns the dest correctly
     */
    @Test
    public void getDest() {
        int [] expected ={0,2,17,10,20,22,2,1,11,10};
        for (int i = 0; i <arrEdges.length ; i++) {
            assertEquals(expected[i],arrEdges[i].getDest());
            assertNotEquals(100,arrEdges[i].getDest());
            assertNotNull(arrEdges[i].getDest());
        }
    }

    /**
     * Checks whether the function returns the Weight correctly
     */
    @Test
    public void getWeight() {
        double [] expected ={0,7.6,3,7,9.8,30,1,4,5,8};
        for (int i = 0; i <arrEdges.length ; i++) {
            assertEquals(expected[i],arrEdges[i].getWeight(),EPSILON);
            assertNotEquals(100,arrEdges[i].getWeight());
            assertNotNull(arrEdges[i].getWeight());
        }
    }

    /**
     * Checks whether the function returns the Info correctly
     */
    @Test
    public void getInfo() {

        for (int i = 0; i <arrEdges.length ; i++) {
            assertEquals("",arrEdges[i].getInfo());
            assertNotEquals("100",arrEdges[i].getInfo());
            assertNotNull(arrEdges[i].getInfo());
        }
    }

    /**
     * Checks whether the function determines the Info properly
     */
    @Test
    public void setInfo() {

        for (int i = 0; i <arrEdges.length ; i++) {
            arrEdges[i].setInfo("99,"+i);
            assertEquals("99,"+i, arrEdges[i].getInfo());
            assertNotEquals("",arrEdges[i].getInfo());
            assertNotNull(arrEdges[i].getInfo());
        }
    }

    /**
     * Checks whether the function returns the tag correctly
     */
    @Test
    public void getTag() {
        
        for (int i = 0; i <arrEdges.length ; i++) {
            assertEquals(0,arrEdges[i].getTag());
            assertNotEquals(100,arrEdges[i].getTag());
            assertNotNull(arrEdges[i].getTag());
        }
    }

    /**
     * Checks whether the function determines the Tag properly
     */
    @Test
    public void setTag() {
        for (int i = 0; i <arrEdges.length ; i++) {
            arrEdges[i].setTag(100);
            assertEquals(100, arrEdges[i].getTag());
            assertNotEquals(0,arrEdges[i].getTag());
            assertNotNull(arrEdges[i].getTag());
        }
    }

    /**
     * Checks whether the function prints according to the print format
     */
    @Test
    public void toStringTest() {
        String [] expected ={"src: 0,dest: 0,weight: 0.0,info: ,tag: 0","src: 1,dest: 2,weight: 7.6,info: ,tag: 0",
                             "src: 8,dest: 17,weight: 3.0,info: ,tag: 0","src: 10,dest: 10,weight: 7.0,info: ,tag: 0",
                             "src: 10,dest: 20,weight: 9.8,info: ,tag: 0","src: 11,dest: 22,weight: 30.0,info: ,tag: 0",
                             "src: 4,dest: 2,weight: 1.0,info: ,tag: 0","src: 9,dest: 1,weight: 4.0,info: ,tag: 0",
                             "src: 22,dest: 11,weight: 5.0,info: ,tag: 0","src: 2,dest: 10,weight: 8.0,info: ,tag: 0"};
        for (int i = 0; i <arrEdges.length ; i++) {
            assertEquals(expected[i],arrEdges[i].toString());
            assertNotEquals("100",arrEdges[i].toString());
        }
    }
}
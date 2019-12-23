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
        arrEdges[1]=new Edge(1,2,7.6,"",0);
        arrEdges[2]=new Edge(8,17,3,"",0);
        arrEdges[3]=new Edge(10,10,7,"",0);
        arrEdges[4]=new Edge(10,20,9.8,"",0);
        arrEdges[5]=new Edge(11,22,30,"",0);
        arrEdges[6]=new Edge(4,2,1,"",0);
        arrEdges[7]=new Edge(9,1,4,"",0);
        arrEdges[8]=new Edge(22,11,5,"",0);
        arrEdges[9]=new Edge(2,10,8,"",0);

    }

    @Test
    public void getSrc() {
        int [] expected ={0,1,8,10,10,11,4,9,22,2};
        for (int i = 0; i <arrEdges.length ; i++) {
            assertEquals(expected[i],arrEdges[i].getSrc());
            assertNotEquals(100,arrEdges[i].getSrc());
            assertNotNull(arrEdges[i].getSrc());
        }
    }

    @Test
    public void getDest() {
        int [] expected ={0,2,17,10,20,22,2,1,11,10};
        for (int i = 0; i <arrEdges.length ; i++) {
            assertEquals(expected[i],arrEdges[i].getDest());
            assertNotEquals(100,arrEdges[i].getDest());
            assertNotNull(arrEdges[i].getDest());
        }
    }

    @Test
    public void getWeight() {
        double [] expected ={0,7.6,3,7,9.8,30,1,4,5,8};
        for (int i = 0; i <arrEdges.length ; i++) {
            assertEquals(expected[i],arrEdges[i].getWeight(),EPSILON);
            assertNotEquals(100,arrEdges[i].getWeight());
            assertNotNull(arrEdges[i].getWeight());
        }
    }

    @Test
    public void getInfo() {

        for (int i = 0; i <arrEdges.length ; i++) {
            assertEquals("",arrEdges[i].getInfo());
            assertNotEquals("100",arrEdges[i].getInfo());
            assertNotNull(arrEdges[i].getInfo());
        }
    }

    @Test
    public void setInfo() {

        for (int i = 0; i <arrEdges.length ; i++) {
            arrEdges[i].setInfo("99,"+i);
            assertEquals("99,"+i, arrEdges[i].getInfo());
            assertNotEquals("",arrEdges[i].getInfo());
            assertNotNull(arrEdges[i].getInfo());
        }
    }

    @Test
    public void getTag() {
        
        for (int i = 0; i <arrEdges.length ; i++) {
            assertEquals(0,arrEdges[i].getTag());
            assertNotEquals(100,arrEdges[i].getTag());
            assertNotNull(arrEdges[i].getTag());
        }
    }

    @Test
    public void setTag() {
        for (int i = 0; i <arrEdges.length ; i++) {
            arrEdges[i].setTag(100);
            assertEquals(100, arrEdges[i].getTag());
            assertNotEquals(0,arrEdges[i].getTag());
            assertNotNull(arrEdges[i].getTag());
        }
    }

    @Test
    public void toStringTest() {
//        String [] expected ={" src: 0,dst: 0,weight: 0,info: "" tag: 0"};
//        for (int i = 0; i <arrEdges.length ; i++) {
//            assertEquals(expected[i],arrEdges[i].);
//            assertNotEquals("100",arrEdges[i].);
//            assertNotNull(arrEdges[i].getWeight());
//        }

    }
}
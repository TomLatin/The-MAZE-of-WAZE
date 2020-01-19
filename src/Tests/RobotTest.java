package Tests;

import dataStructure.Node;
import dataStructure.node_data;
import elements.Fruit;
import elements.Robot;
import elements.elementsInTheGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Point3D;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class RobotTest {

    static String[] arrJson=new String[10];
    static Robot [] arrRobot =new Robot[10];
    static Robot rHelp=new Robot();
    static Point3D p=new Point3D(0,0,0);
    static Point3D pCheck=new Point3D(35.189568308313156,32.106617263865544,0.0);

    @BeforeEach
    void BeforeEach() {
        //create 10 new default robots
        for (int i = 0; i < arrRobot.length; i++) {
            arrRobot[i]=new Robot();
        }
        //initialize the robots, it is also a check for init function!
        arrJson[0] =("{\"Robot\":{\"id\":0,\"value\":0,\"src\":0,\"dest\":1,\"speed\":1,\"pos\":\"35.189568308313156,32.106617263865544,0.0\"}}");
        arrJson[1]=("{\"Robot\":{\"id\":1,\"value\":0,\"src\":1,\"dest\":2,\"speed\":1,\"pos\":\"35.189568308313156,32.106617263865544,0.0\"}}");
        arrJson[2]=("{\"Robot\":{\"id\":2,\"value\":0,\"src\":2,\"dest\":3,\"speed\":1,\"pos\":\"35.189568308313156,32.106617263865544,0.0\"}}");
        arrJson[3]=("{\"Robot\":{\"id\":3,\"value\":0,\"src\":3,\"dest\":4,\"speed\":1,\"pos\":\"35.189568308313156,32.106617263865544,0.0\"}}");
        arrJson[4]=("{\"Robot\":{\"id\":4,\"value\":0,\"src\":4,\"dest\":5,\"speed\":1,\"pos\":\"35.189568308313156,32.106617263865544,0.0\"}}");
        arrJson[5]=("{\"Robot\":{\"id\":5,\"value\":0,\"src\":5,\"dest\":6,\"speed\":1,\"pos\":\"35.189568308313156,32.106617263865544,0.0\"}}");
        arrJson[6]=("{\"Robot\":{\"id\":6,\"value\":0,\"src\":6,\"dest\":7,\"speed\":1,\"pos\":\"35.189568308313156,32.106617263865544,0.0\"}}");
        arrJson[7]=("{\"Robot\":{\"id\":7,\"value\":0,\"src\":7,\"dest\":8,\"speed\":1,\"pos\":\"35.189568308313156,32.106617263865544,0.0\"}}");
        arrJson[8]=("{\"Robot\":{\"id\":8,\"value\":0,\"src\":8,\"dest\":9,\"speed\":1,\"pos\":\"35.189568308313156,32.106617263865544,0.0\"}}");
        arrJson[9]=("{\"Robot\":{\"id\":9,\"value\":0,\"src\":9,\"dest\":10,\"speed\":1,\"pos\":\"35.189568308313156,32.106617263865544,0.0\"}}");

        for (int i = 0; i < arrRobot.length; i++) {
            arrRobot[i]=(Robot)rHelp.init(arrJson[i]);
        }

    }

    @Test
    void getAndSetPic() {
        for (int i = 0; i <arrRobot.length ; i++) {
            arrRobot[i].setPic("spidermen.png");
            assertEquals("spidermen.png",  arrRobot[i].getPic());
        }
    }

    @Test
    void getKey() {
        for (int i = 0; i <arrRobot.length ; i++) {
            assertEquals(i,arrRobot[i].getKey());
        }
    }

    @Test
    void getLocation() {
        for (int i = 0; i <arrRobot.length ; i++) {
            assertEquals(pCheck,arrRobot[i].getLocation());
        }
    }

    @Test
    void setLocation() {
        for (int i = 0; i <arrRobot.length ; i++) {
            arrRobot[i].setLocation(p);
            assertEquals(p,arrRobot[i].getLocation());

        }
    }

    @Test
    void getValue() {
        for (int i = 0; i <arrRobot.length ; i++) {
            assertEquals(0,arrRobot[i].getValue());
        }
    }

    @Test
    void setValue() {
        for (int i = 0; i <arrRobot.length ; i++) {
            arrRobot[i].setValue(1);
            assertEquals(1,arrRobot[i].getValue());
        }
    }

    @Test
    void getAndGetPath() {
        LinkedList<node_data> path=new LinkedList<node_data>();
        Node n=new Node(1,new Point3D(0, 0,0));
        path.add(n);
        arrRobot[0].setPath(path);
        assertEquals(path,arrRobot[0].getPath());
    }

    @Test
    void getAndSetRobotFruit() {
        Fruit f=new Fruit();
        LinkedList<Fruit> Fruit=new LinkedList<Fruit>();
        Fruit.add(f);
        arrRobot[0].setRobotFruit(Fruit);
        assertEquals(Fruit,arrRobot[0].getRobotFruit());
    }

    @Test
    void getSpeed() {
        for (int i = 0; i <arrRobot.length ; i++) {
            assertEquals(1,arrRobot[i].getSpeed());
        }
    }

    @Test
    void getSrc() {
        for (int i = 0; i <arrRobot.length ; i++) {
            assertEquals(i,arrRobot[i].getSrc());
        }
    }

    @Test
    void setSrc() {
        for (int i = 0; i <arrRobot.length ; i++) {
            arrRobot[i].setSrc(100);
            assertEquals(100,arrRobot[i].getSrc());
        }
    }

    @Test
    void getDest() {
        for (int i = 0; i <arrRobot.length ; i++) {
            assertEquals(i+1,arrRobot[i].getDest());
        }
    }

    @Test
    void setDest() {
        for (int i = 0; i <arrRobot.length ; i++) {
            arrRobot[i].setDest(100);
            assertEquals(100,arrRobot[i].getDest());
        }
    }
}
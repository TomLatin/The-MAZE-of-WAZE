package Tests;

import elements.Fruit;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import utils.Point3D;
import elements.elementsInTheGame;
import static org.junit.jupiter.api.Assertions.*;

class FruitTest {

    static Fruit[] arrFruit=new Fruit[10];
    static String[] arrJson1=new String[10];
    static Fruit fHelp=new Fruit();
    static Point3D pCheck=new Point3D(3.0,2.0,0.0);
    static Point3D p=new Point3D(0,0,0);

    @Before
    public void BeforeEach(){
        //create 10 new default fruit
        for (int i = 0; i < arrFruit.length; i++) {
            arrFruit[i]=new Fruit();
        }

        //initialize the fruits, it is also a check for init function!
        arrJson1[0]="{\"Fruit\":{\"value\":1.0,\"type\":-1,\"pos\":\"3.0,2.0,0.0\"}}";
        arrJson1[1]=("{\"Fruit\":{\"value\":2.0,\"type\":-1,\"pos\":\"3.0,2.0,0.0\"}}");
        arrJson1[2]=("{\"Fruit\":{\"value\":3.0,\"type\":-1,\"pos\":\"3.0,2.0,0.0\"}}");
        arrJson1[3]=("{\"Fruit\":{\"value\":4.0,\"type\":-1,\"pos\":\"3.0,2.0,0.0\"}}");
        arrJson1[4]=("{\"Fruit\":{\"value\":5.0,\"type\":-1,\"pos\":\"3.0,2.0,0.0\"}}");
        arrJson1[5]=("{\"Fruit\":{\"value\":6.0,\"type\":-1,\"pos\":\"3.0,2.0,0.0\"}}");
        arrJson1[6]=("{\"Fruit\":{\"value\":7.0,\"type\":-1,\"pos\":\"3.0,2.0,0.0\"}}");
        arrJson1[7]=("{\"Fruit\":{\"value\":8.0,\"type\":-1,\"pos\":\"3.0,2.0,0.0\"}}");
        arrJson1[8]=("{\"Fruit\":{\"value\":9.0,\"type\":-1,\"pos\":\"3.0,2.0,0.0\"}}");
        arrJson1[9]=("{\"Fruit\":{\"value\":10.0,\"type\":-1,\"pos\":\"3.0,2.0,0.0\"}}");

        for (int i = 0; i < arrJson1.length; i++) {
            System.out.println(arrJson1[i]);
        }
        for (int i = 0; i < arrFruit.length; i++) {
            arrFruit[i]=(Fruit)fHelp.init(arrJson1[i]);
        }
    }

    @Test
    void getAndSetPic() {
        System.out.println(arrJson1[0]);
        for (int i = 0; i <arrFruit.length ; i++) {
            arrFruit[i].setPic("redStone.png");
            assertEquals("redStone.png", arrFruit[i].getPic());
        }
    }

    @Test
    void getLocation() {
        for (int i = 0; i <arrFruit.length ; i++) {
            assertEquals(pCheck,arrFruit[i].getLocation());
        }
    }

    @Test
    void setLocation() {
        for (int i = 0; i <arrFruit.length ; i++) {
            arrFruit[i].setLocation(p);
            assertEquals(p,arrFruit[i].getLocation());

        }
    }

    //return value
    @Test
    void getWeight() {
        for (int i = 0; i <arrFruit.length ; i++) {
            assertEquals(i,arrFruit[i].getWeight());
        }
    }

    //set value
    @Test
    void setWeight() {
        for (int i = 0; i <arrFruit.length ; i++) {
            arrFruit[i].setWeight(100);
            assertEquals(100,arrFruit[i].getWeight());
        }
    }

    //return type
    @Test
    void getTag() {
        for (int i = 0; i <arrFruit.length ; i++) {
            assertEquals(-1,arrFruit[i].getTag());
        }
    }

    //set type
    @Test
    void setTag() {
        for (int i = 0; i <arrFruit.length ; i++) {
            arrFruit[i].setTag(1);
            assertEquals(1,arrFruit[i].getTag());
        }
    }
}
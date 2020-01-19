package Tests;

import elements.Fruit;
import elements.Robot;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import utils.Point3D;

import static org.junit.jupiter.api.Assertions.*;

class FruitTest {

    static Point3D[] arrPoint=new Point3D[10];
    static Fruit[] arrFruit=new Fruit[10];
    static String[] arrJson=new String[10];
    @Before
    public void BeforeEach(){
        arrPoint[0]=new Point3D(0,0,0);
        arrPoint[1]=new Point3D(1,1,0);
        arrPoint[2]=new Point3D(2,2,0);
        arrPoint[3]=new Point3D(3,3,0);
        arrPoint[4]=new Point3D(4,4,0);
        arrPoint[5]=new Point3D(5,5,0);
        arrPoint[6]=new Point3D(6,6,0);
        arrPoint[7]=new Point3D(7,7,0);
        arrPoint[8]=new Point3D(8,8,0);
        arrPoint[9]=new Point3D(9,9,0);

//        [{"Fruit":{"value":5.0,"type":-1,"pos":"35.20273974670703,32.10439601193746,0.0"}}, {"Fruit":{"value":8.0,"type":-1,"pos":"35.189541903742466,32.10714473742062,0.0"}}, {"Fruit":{"value":13.0,"type":1,"pos":"35.198546018801096,32.10442041371198,0.0"}}]
//
//        arrJson[0]=["Fruit":{"value":5.0,"type":-1,"pos":"35.20273974670703,32.10439601193746,0.0"];
//        arrJson[1]=new Point3D(1,1,0);
//        arrJson[2]=new Point3D(2,2,0);
//        arrJson[3]=new Point3D(3,3,0);
//        arrJson[4]=new Point3D(4,4,0);
//        arrJson[5]=new Point3D(5,5,0);
//        arrJson[6]=new Point3D(6,6,0);
//        arrJson[7]=new Point3D(7,7,0);
//        arrJson[8]=new Point3D(8,8,0);
//        arrJson[9]=new Point3D(9,9,0);

        for (int i = 0; i < arrFruit.length; i++) {
            arrFruit[i]=new Fruit();
        }





    }

    @Test
    void init() {
    }

    @Test
    void setPic() {
    }

    @Test
    void getPic() {
    }

    @Test
    void getKey() {
    }

    @Test
    void getLocation() {
    }

    @Test
    void setLocation() {
    }

    @Test
    void getWeight() {
    }

    @Test
    void setWeight() {
    }

    @Test
    void getInfo() {
    }

    @Test
    void setInfo() {
    }

    @Test
    void getTag() {
    }

    @Test
    void setTag() {
    }
}
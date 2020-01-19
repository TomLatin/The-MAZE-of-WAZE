package Tests;

import Server.Game_Server;
import Server.game_service;
import elements.FruitContain;
import elements.Robot;
import elements.RobotsContain;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import utils.Point3D;

import static org.junit.jupiter.api.Assertions.*;

class RobotsContainTest {

    static game_service server;
    static Robot[] checkforBuildRobot = new Robot[8];
    static Point3D[] checkPoint3D = new Point3D[8];

    @Before
    public void BeforeEach() {

        checkPoint3D[0] = new Point3D(1, 2, 0);
        checkPoint3D[1] = new Point3D(3, 4, 0);
        checkPoint3D[2] = new Point3D(5, 6, 0);
        checkPoint3D[3] = new Point3D(7, 8, 0);
        checkPoint3D[4] = new Point3D(9, 10, 0);
        checkPoint3D[5] = new Point3D(11, 12, 0);
        checkPoint3D[6] = new Point3D(13, 14, 0);
        checkPoint3D[7] = new Point3D(15, 16, 0);

        checkforBuildRobot[0] = new Robot(1, 1, 1, checkPoint3D[0], 1, 1, "spidermen.png");
        checkforBuildRobot[1] = new Robot(2, 2, 2, checkPoint3D[1], 2, 2, "spidermen.png");
        checkforBuildRobot[2] = new Robot(3, 3, 3, checkPoint3D[2], 3, 3, "spidermen.png");
        checkforBuildRobot[3] = new Robot(4, 4, 4, checkPoint3D[3], 4, 4, "spidermen.png");
        checkforBuildRobot[4] = new Robot(5, 5, 5, checkPoint3D[4], 5, 5, "spidermen.png");
        checkforBuildRobot[5] = new Robot(6, 6, 6, checkPoint3D[5], 6, 6, "spidermen.png");
        checkforBuildRobot[6] = new Robot(7, 7, 7, checkPoint3D[6], 7, 7, "spidermen.png");
        checkforBuildRobot[7] = new Robot(8, 8, 8, checkPoint3D[7], 8, 8, "spidermen.png");
    }


    @Test
    public void init() {
        this.server = Game_Server.getServer(0);
        RobotsContain r = new RobotsContain(server);
        Robot[] rr = r.init(this.server.getRobots());
        assertEquals(rr,r.RobotArr);
    }

    @Test
    void initToServer() {
        server = Game_Server.getServer(23);
        for (int i = 0; i < 3; i++) { // insert robots to server
            server.addRobot(i);
            RobotsContain r = new RobotsContain(server);
            Robot[] rr = r.init(this.server.getRobots());
            assertEquals(i,rr[i].getSrc());
        }

    }

    @Test
    void getNumOfRobots() {
            this.server = Game_Server.getServer(0);
            RobotsContain r = new RobotsContain(server);
            int amount = r.getNumOfRobots();
            assertEquals(1, amount);
    }
}
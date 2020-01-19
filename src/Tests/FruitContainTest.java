package Tests;

import Server.Game_Server;
import Server.game_service;
import elements.Fruit;
import elements.FruitContain;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FruitContainTest {

    static String[] checkJsonString = new String[6];
    static List<String> checkforBuildFruitList = new LinkedList<>();
    static game_service server;

    @BeforeEach
    public void BeforeEach() {

        checkJsonString[0] = "{\"Fruit\":{\"value\":1.0,\"type\":-1,\"pos\":\"1.0,2.0,0.0\"}}";
        checkJsonString[1] = "{\"Fruit\":{\"value\":2.0,\"type\":-1,\"pos\":\"3.0,4.0,0.0\"}}";
        checkJsonString[2] = "{\"Fruit\":{\"value\":3.0,\"type\":-1,\"pos\":\"5.0,6.0,0.0\"}}";
        checkJsonString[3] = "{\"Fruit\":{\"value\":4.0,\"type\":-1,\"pos\":\"7.0,8.0,0.0\"}}";
        checkJsonString[4] = "{\"Fruit\":{\"value\":5.0,\"type\":-1,\"pos\":\"9.0,10.0,0.0\"}}";
        checkJsonString[5] = "{\"Fruit\":{\"value\":6.0,\"type\":-1,\"pos\":\"11.0,12.0,0.0\"}}";

        checkforBuildFruitList.add(checkJsonString[0]);
        checkforBuildFruitList.add(checkJsonString[1]);
        checkforBuildFruitList.add(checkJsonString[2]);
        checkforBuildFruitList.add(checkJsonString[3]);
        checkforBuildFruitList.add(checkJsonString[4]);
        checkforBuildFruitList.add(checkJsonString[5]);

    }

    @Test
    public void init() {
        server=Game_Server.getServer(23);
        FruitContain f = new FruitContain(server);
        Fruit [] fruitsArr = f.init(checkforBuildFruitList);
        for(int i =0; i < fruitsArr.length; i++){
            Fruit check = fruitsArr[i];
            assertEquals((i*2)+1, check.getLocation().x(), 0.0001);
            assertEquals((i*2)+2, check.getLocation().y(), 0.0001);
            assertEquals(0.0, check.getLocation().z(), 0.0001);
            assertEquals(i+1, check.getWeight(), 0.0001);
            assertEquals(-1, check.getTag());
            assertEquals("redStone.png", check.getPic());
        }
    }

    @Test
    public void getNumOfFruits() {
        this.server = Game_Server.getServer(0);
        FruitContain f = new FruitContain(server);
        int numOfFruits = f.getNumOfFruits();
        assertEquals(1, numOfFruits);
    }
}
package elements;

import Server.game_service;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class RobotsContain {
    private Robot[] RobotArray;
    private int numOfRobots;
    private game_service game;
    private int[] nodeKey;

    public RobotsContain (game_service game, int[]keys){
        this.game = game;
        getNumOfRobots();
        nodeKey = keys;
        RobotArray = new Robot[this.numOfRobots];
        for (int i = 0; i < numOfRobots; i++) {
            this.game.addRobot(nodeKey[i]);
        }
        List<String> toAdd= game.getRobots();
        for (int i = 0; i < numOfRobots ; i++) {
            
        }
    }

    public void addRobot(Robot toAdd){

    }

    public void getNumOfRobots(){
        try {
            String info = game.toString();
            JSONObject line = new JSONObject(info);
            JSONObject GameServer = line.getJSONObject("GameServer");
            this.numOfRobots = GameServer.getInt("robots");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

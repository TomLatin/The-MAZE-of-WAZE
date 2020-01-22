package elements;

import Server.game_service;
import gameClient.MyGameGUI;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class RobotsContain {
    //Fields
    public Robot[] RobotArr; //The array that saves the robots you get from the server
    private int numOfRobots; //The numbers of robot you get from the server
    private game_service game; //save the current game

    /**
     *A constructor that accepts parameters
     */
    public RobotsContain (game_service game){
        this.game = game;
        this.numOfRobots = getNumOfRobots();
        RobotArr = new Robot[this.numOfRobots];
    }

    /**
     * Insert data Robot the server
     * @param keys
     */
    public void initToServer(int[]keys){
        for (int i = 0; i < this.numOfRobots; i++) { // insert robots to server
            this.game.addRobot(keys[i]);
        }
    }

    /**
     * Insert data into object RobotContain from the server
     * @param json
     * @return Robot array
     */
    public Robot[] init(List<String>json){
        int i=0;
        for (String curr : json)
        {
            Robot toAdd = new Robot();
            toAdd = (Robot) toAdd.init(curr);
            this.RobotArr[i]=toAdd;
            if(i==1)
            {
                this.RobotArr[i].setPic("blackWidoew.png");
            }
            else if(i==2)
            {
                this.RobotArr[i].setPic("captainAmerica.png");
            }
            i++;
        }
        return this.RobotArr;
    }

    /**
     * @return getNumOfRobots
     */
    public int getNumOfRobots(){
        int num=0;
        try {
            String info = game.toString();
            JSONObject line = new JSONObject(info);
            JSONObject GameServer = line.getJSONObject("GameServer");
            num = GameServer.getInt("robots");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return num;
    }
}

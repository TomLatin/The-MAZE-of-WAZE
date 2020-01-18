package elements;

import Server.game_service;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class RobotsContain {
    public Robot[] RobotArr;
    private int numOfRobots;
    private game_service game;


    public RobotsContain (game_service game){
        this.game = game;
        this.numOfRobots = getNumOfRobots();
        RobotArr = new Robot[this.numOfRobots];
    }

    public void initToServer(int[]keys){
        for (int i = 0; i < this.numOfRobots; i++) { // insert robots to server
            this.game.addRobot(keys[i]);
        }
    }

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

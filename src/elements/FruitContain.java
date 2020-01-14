package elements;

import Server.game_service;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class FruitContain {
    public Fruit[] fruitsArr;
    private int numOfFruits;
    private game_service game;

    public  FruitContain (game_service game){
        this.game = game;
        this.numOfFruits = getNumOfFruits();
        this.fruitsArr = new Fruit[numOfFruits];
    }

    public Fruit[] init(List<String> json){
        int i=0;
        for (String curr : json)
        {
            Fruit toAdd = new Fruit();
            toAdd = (Fruit) toAdd.init(curr);
            this.fruitsArr[i]=toAdd;
            i++;
        }
        return this.fruitsArr;
    }

    public int getNumOfFruits(){
        int num =0;
        try {
            String info = game.toString();
            JSONObject line = new JSONObject(info);
            JSONObject GameServer = line.getJSONObject("GameServer");
            num = GameServer.getInt("fruits");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return num;
    }

}

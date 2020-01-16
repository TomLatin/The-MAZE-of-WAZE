package elements;

import Server.game_service;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class FruitContain {
    //Fields
    public Fruit[] fruitsArr; //The array that saves the fruits you get from the server
    private int numOfFruits; //The numbers of fruits you get from the server
    private game_service game; //save the current game

    /**
     *A constructor that accepts parameters
     */
    public  FruitContain (game_service game){
        this.game = game;
        this.numOfFruits = getNumOfFruits();
        this.fruitsArr = new Fruit[numOfFruits];
    }

    /**
     * Insert data into object FruitContain from the server
     * Passes each fruit on json and sends the same fruit to the init of the object fruit
     * @param json
     * @return array of Fruits
     */
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

    /**
     * @return the number of fruit in the game
     */
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

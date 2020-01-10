package gameClient;

import Server.Game_Server;
import Server.game_service;
import dataStructure.DGraph;
import elements.Fruit;
import elements.FruitContain;
import elements.RobotsContain;
import gui.Graph_GUI;
import org.json.JSONException;
import org.json.JSONObject;
import utils.StdDraw;

public class MyGameGUI {
    private Graph_GUI graph_gui;
    private DGraph dg;
    private game_service game;
    private int numOfRobots;
    private RobotsContain robotArr;
    private FruitContain gameFruits;
    private Fruit[] fruitArr;

    public MyGameGUI(int gameSenario) {

        //just draw the graph
        this.game = Game_Server.getServer(gameSenario);
        String graphJson = game.getGraph();
        this.dg = new DGraph();
        this.dg.init(graphJson);
        graph_gui = new Graph_GUI(this.dg);

        this.gameFruits = new FruitContain(this.game);
        fruitArr = gameFruits.init(this.game.getFruits());
        for (Fruit curr : fruitArr){
            StdDraw.picture(curr.getLocation().x(),curr.getLocation().y(),curr.getPic(),0.0005,0.0005);
        }


//      System.out.println(game.getRobots());
        System.out.println(game.getFruits());
        System.out.println(game.toString());

    }


    public static void main(String[] args) {
        MyGameGUI games = new MyGameGUI(23);
    }
}

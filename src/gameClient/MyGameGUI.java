package gameClient;

import Server.Game_Server;
import Server.game_service;
import dataStructure.DGraph;
import dataStructure.node_data;
import elements.Fruit;
import elements.FruitContain;
import elements.Robot;
import elements.RobotsContain;
import gui.Graph_GUI;
import org.json.JSONException;
import org.json.JSONObject;
import utils.Range;
import utils.StdDraw;

public class MyGameGUI extends Thread{
    private Graph_GUI graph_gui;
    private DGraph dg;
    private game_service game;
    private RobotsContain gameRobot;
    private Robot[] robotArr;
    private FruitContain gameFruits;
    private Fruit[] fruitArr;
    private Range rangeX;
    private Range rangeY;


    public MyGameGUI(int gameScenario) {
        welcomWindow();//just open the window

        //create window that ask for scenario
        this.game = Game_Server.getServer(gameScenario);

        this.start();
        drawGraph();
        drawFruits();
        drawRobots();

        System.out.println(game.getRobots());
        System.out.println(game.getFruits());
        System.out.println(game.toString());

    }

    public void drawGraph(){
        //just draw the graph
        String graphJson = game.getGraph();
        this.dg = new DGraph();
        this.dg.init(graphJson);
        draw();
        graph_gui = new Graph_GUI(this.dg);
        graph_gui.sketchGraph(findRangeX(),findRangeY());
    }

    public void drawFruits(){
        this.gameFruits = new FruitContain(this.game); //build a FruitContain
        fruitArr = gameFruits.init(this.game.getFruits()); //initialize the arr of fruits

        //Placing the fruits on the board
        for (Fruit curr : fruitArr){
            StdDraw.picture(curr.getLocation().x(),curr.getLocation().y(),curr.getPic(),0.0008,0.0008);
        }
    }

    public void drawRobots(){
        gameRobot = new RobotsContain(game);
        int [] robotKeys = new int[gameRobot.getNumOfRobots()];  //open arr of keys

        //fill the robot array (menual / auto)
        robotKeys[0] = 8;
//        robotKeys[1] = 30;
//        robotKeys[2] = 1;

        //build RobotContain
        gameRobot.initToServer(robotKeys);

        //print
        robotArr = gameRobot.init(this.game.getRobots());

        //Placing the robots on the board
        for (Robot curr : robotArr){
            StdDraw.picture(curr.getLocation().x(),curr.getLocation().y(),curr.getPic(),0.0008,0.0008);
        }
    }

    /**
     * We will find the range in the X axis where the graph is
     * @return toReturn/Default - the range X we give
     */
    public Range findRangeX(){
        if (dg.nodeSize()!=0){
            double min = Integer.MAX_VALUE;
            double max = Integer.MIN_VALUE;
            for (node_data curr : dg.getV()){
                if (curr.getLocation().x() > max) max = curr.getLocation().x();
                if (curr.getLocation().x() < min) min = curr.getLocation().x();
            }
            Range toReturn = new Range(min,max);
            rangeX = toReturn;
            return toReturn;
        }
        else {
            Range Default = new Range(35.1,35.3);
            rangeX = Default;
            return Default;
        }
    }

    /**
     * We will find the range in the Y axis where the graph is
     * @return toReturn/Default - the range Y we give
     */
    public Range findRangeY(){
        if (dg.nodeSize()!=0){
            double min = Integer.MAX_VALUE;
            double max = Integer.MIN_VALUE;
            for (node_data curr : dg.getV()){
                if (curr.getLocation().y() > max) max = curr.getLocation().y();
                if (curr.getLocation().y() < min) min = curr.getLocation().y();
            }
            Range toReturn = new Range(min,max);
            rangeY = toReturn;
            return toReturn;
        }
        else {
            Range Default = new Range(32.1,32.3);
            rangeY = Default;
            return Default;
        }
    }


    public void welcomWindow(){
        StdDraw.setCanvasSize(1024,512);
        StdDraw.setXscale(-100,100);
        StdDraw.setYscale(-50,50);
        StdDraw.text(0,0, "WELLCOM");
    }

    /**
     * this method open the frame that shows the gui of the graph.
     */
    public void draw(){
        Range x = findRangeX();
        Range y = findRangeY();
        StdDraw.setCanvasSize(1024,512);
        StdDraw.setXscale(x.get_min()-0.002,x.get_max()+0.002);
        StdDraw.setYscale(y.get_min()-0.002,y.get_max()+0.002);

    }

    public void run(){
        while (true){
            drawFruits();
            drawRobots();
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MyGameGUI games = new MyGameGUI(8);
    }
}

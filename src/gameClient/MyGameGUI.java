package gameClient;

import Server.Game_Server;
import Server.game_service;
import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.node_data;
import elements.Fruit;
import elements.FruitContain;
import elements.Robot;
import elements.RobotsContain;
import gui.Graph_GUI;
import utils.Range;
import utils.StdDraw;
import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;


public class MyGameGUI extends Thread{
    private int[] robotKeys;
    private Graph_GUI graph_gui;
    private DGraph dg;
    private game_service game;
    private RobotsContain gameRobot;
    private Robot[] robotArr;
    private FruitContain gameFruits;
    private Fruit[] fruitArr;
    private Range rangeX;
    private Range rangeY;
    private LinkedList<node_data> toMark=new LinkedList<node_data>();



    public MyGameGUI() {
        welcomWindow();//just open the window

        //Opens the scenario selection window
        JFrame f2=new JFrame();
        int Scenario =-1;
        while (Scenario < 0 || Scenario > 23) {
            String pKey = JOptionPane.showInputDialog(f2, "Enter Scenario");
            try {
                Scenario = Integer.parseInt(pKey);
            } catch (Exception e1) {
                Scenario = -1;
            }
        }

        //create window that ask for scenario
        this.game = Game_Server.getServer(Scenario);

        //draw the game in the first time
        drawGraph();
        drawFruits();

        //draw first time robots
        this.gameRobot = new RobotsContain(this.game);
        placeRobots();
        this.gameRobot.initToServer(robotKeys); //build RobotContain
        updateRobot();

        this.game.startGame(); // start the game in the server

        StdDraw.enableDoubleBuffering();
        this.start(); //start Thread
    }

    public void placeRobots(){
        this.robotKeys = new int [this.gameRobot.getNumOfRobots()];  //open arr of keys

        //fill the robot array (menual / auto)
        //manual
        double locX, locY;
        int countClick = 0;
        int tar = this.gameRobot.getNumOfRobots();
        while (countClick < tar){
            if (StdDraw.isMousePressed()) {
                StdDraw.isMousePressed = false;
                locX = StdDraw.mouseX();
                locY = StdDraw.mouseY();
                node_data temp = getNeerNode(locX, locY);
                if (temp != null) {
                   this.robotKeys[countClick++] = temp.getKey();
                   markSelectedNode(temp);
                   toMark.add(temp);
                }
            }
        }
    }

    public void markSelectedNode(node_data toMark){
        StdDraw.setPenColor(Color.BLUE);
        StdDraw.setPenRadius(0.006);
        StdDraw.circle(toMark.getLocation().x(), toMark.getLocation().y(), 0.0003);
    }

    public void menualMove(){
        double locX, locY;
        if (StdDraw.isMousePressed()) {
            System.out.println("click robot");
            StdDraw.isMousePressed = false;
            locX = StdDraw.mouseX();
            locY = StdDraw.mouseY();
            Robot temp = (Robot)getNeerRobot(locX, locY);
            if (temp != null) {
                System.out.println("robot selected");
                while (StdDraw.isMousePressed == false) {
                    if (StdDraw.isMousePressed()) {
                        System.out.println("click dest");
                        locX = StdDraw.mouseX();
                        locY = StdDraw.mouseY();
                        node_data dest = getNeerNode(locX, locY);
                        if (dest != null) {
                            System.out.println("dest is node");
                            for (edge_data curr : this.dg.getE(temp.getSrc())) {
                                if (dest.getKey() == curr.getDest()){
                                    System.out.println("node is neer");
                                    game.chooseNextEdge(temp.getKey(), dest.getKey());
                                }
                                else System.out.println("error");
                            }
                        }
                    }
                }
            }
        }
    }


    /**
     * we will return the node that the location (X, Y) is at that node,
     * We will loop through the nodes of the graph until we find a node that corresponds to the desired location
     * @param x - The location on the X axis that is sent to find the node
     * @param y - The location on the Y axis that is sent to find the node
     * @return the node that the location (X, Y) is at that node
     */
    public node_data getNeerNode (double x, double y){
        for (node_data curr : this.dg.getV()){
            double currX = curr.getLocation().x();
            double currY = curr.getLocation().y();
            if ((x < currX+0.0004) && (x > currX-0.0004) && (y < currY+0.0004) && (y > currY-0.0004)) return curr;
        }
        return null;
    }

    public node_data getNeerRobot (double x, double y){
        for (node_data curr : robotArr){
            double currX = curr.getLocation().x();
            double currY = curr.getLocation().y();
            if ((x < currX+0.0004) && (x > currX-0.0004) && (y < currY+0.0004) && (y > currY-0.0004)) return curr;
        }
        return null;
    }

    //go to here after start, and draw the game all the time
    public void run(){
        while (this.game.isRunning()){

            graph_gui.sketchGraph(findRangeX(),findRangeY());
            drawFruits();
            menualMove();
            game.move();
            updateRobot();
            StdDraw.show();
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //Game Over
        this.game.stopGame();
        System.out.println("Game Over");
        JFrame f=new JFrame();
        JOptionPane.showMessageDialog(f,"The Game is OVER!");
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

    public void updateRobot(){

        //print
        this.robotArr = this.gameRobot.init(this.game.getRobots());


        //Placing the robots on the board
        for (Robot curr : this.robotArr){
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
        StdDraw.text(0,0, "WELLCOME");
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

    public static void main(String[] args) {
        MyGameGUI games = new MyGameGUI();
    }
}

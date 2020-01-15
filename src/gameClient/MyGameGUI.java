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
import org.json.JSONException;
import org.json.JSONObject;
import utils.Point3D;
import utils.Range;
import utils.StdDraw;
import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;


public class MyGameGUI extends Thread{
    public int[] robotKeys;
    public DGraph dg;
    private game_service game;
    public RobotsContain gameRobot;
    public FruitContain gameFruits;
    private MyGameAlgo gameAuto;
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
        this.robotKeys = new int [this.gameRobot.getNumOfRobots()];  //open arr of keys
        //menual
     //   placeRobots();
        //auto
        this.gameAuto = new MyGameAlgo(this,this.dg);
        for (int i = 0; i < this.gameRobot.getNumOfRobots(); i++) {
            Robot toAdd = new Robot(i+1);
            this.gameRobot.RobotArr[i]=toAdd;
        }
        this.gameAuto.menagerOfRobots();

        this.gameRobot.initToServer(robotKeys); //build RobotContain
        updateRobot();

        this.game.startGame(); // start the game in the server

        StdDraw.enableDoubleBuffering();
        this.start(); //start Thread
    }

    public void placeRobots(){
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
                                    this.game.chooseNextEdge(temp.getKey(), dest.getKey());
                                }
                                else System.out.println("error");
                            }
                        }
                    }
                }
            }
        }
    }

    public void autoMove(){
        for (Robot r: this.gameRobot.RobotArr) {
            this.game.chooseNextEdge(r.getKey(),r.path.get(0).getKey());
            System.out.println(r.path.get(0).getKey());
//            System.out.println(r.path.get(1).getKey());
//            System.out.println(this.gameAuto.findFruitsEdge(r.robotFruit));
//            System.out.println(r.path);
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
        for (node_data curr : this.gameRobot.RobotArr){
            double currX = curr.getLocation().x();
            double currY = curr.getLocation().y();
            if ((x < currX+0.0004) && (x > currX-0.0004) && (y < currY+0.0004) && (y > currY-0.0004)) return curr;
        }
        return null;
    }

    //go to here after start, and draw the game all the time
    public void run(){
        while (this.game.isRunning()){

            sketchGraph(findRangeX(),findRangeY());
            StdDraw.setPenColor(Color.BLUE);
            StdDraw.text(findRangeX().get_min(),findRangeY().get_max()+0.0015,"TIME TO END: "+ game.timeToEnd()/1000);
            int score=0;
            try {
                String info = game.toString();
                JSONObject line = new JSONObject(info);
                JSONObject GameServer = line.getJSONObject("GameServer");
                score = GameServer.getInt("grade");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            StdDraw.text(findRangeX().get_max(),findRangeY().get_max()+0.0015,"Score: "+ score);
            drawFruits();
            //manual
            //   menualMove();
            //auto
            this.gameAuto.menagerOfRobots();
            autoMove();
            this.game.move();
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
        sketchGraph(findRangeX(),findRangeY());
    }

    /**
     * this method sketch of the graph on the opened window of the gui.
     */
    public void sketchGraph(Range RangeX, Range RangeY) {
        StdDraw.clear();
        Range xx = RangeX;
        Range yy = RangeY;
        double rightScaleX = ((xx.get_max()-xx.get_min())*0.004);
        double rightScaleY =  ((yy.get_max()-yy.get_min())*0.004);
        for (node_data currV : this.dg.getV()) {
            if (this.dg.getE(currV.getKey()) != null) {
                for (edge_data currE : this.dg.getE(currV.getKey())) {
                    if (currE.getDest() != currE.getSrc()) {
                        node_data srcN = currV;
                        node_data dstN = this.dg.getNode(currE.getDest());
                        Point3D srcP = srcN.getLocation();
                        Point3D dstP = dstN.getLocation();
                        StdDraw.setPenColor(Color.BLACK);
                        StdDraw.setPenRadius(rightScaleX*60);
                        StdDraw.line(srcP.x(), srcP.y(), dstP.x(), dstP.y());
                    }
                }
            }
        }
        for (node_data currV : this.dg.getV()) {
            if (this.dg.getE(currV.getKey()) != null) {
                for (edge_data currE : this.dg.getE(currV.getKey())) {
                    if (currE.getDest() != currE.getSrc()) {
                        double weight = currE.getWeight();
                        node_data srcN = currV;
                        node_data dstN = this.dg.getNode(currE.getDest());
                        Point3D srcP = srcN.getLocation();
                        Point3D dstP = dstN.getLocation();
                        StdDraw.setPenColor(Color.MAGENTA);
                        double tX = srcP.x() + (dstP.x() - srcP.x()) * 0.8, tY = srcP.y() + (dstP.y() - srcP.y()) * 0.8;
                        double rx = 0, gy = 0;
                        if (srcP.y() == dstP.y()) gy = rightScaleX*4;
                        else if (srcP.x() == dstP.x()) rx = rightScaleX*5 ;
                        else {
                            double m = (dstP.y() - srcP.y()) / (dstP.x() - srcP.x());
                            if (Math.abs(m) > 1) rx = rightScaleX*4;
                            else gy = rightScaleX*3;
                        }
                        double printWeight = Math.round(weight*100.0)/100.0;
                        StdDraw.text(tX + rx, tY + gy, "" + printWeight);
                        StdDraw.filledRectangle(tX, tY, rightScaleX, rightScaleX);
                    }
                }
            }
        }
        for (node_data curr : this.dg.getV()) {
            StdDraw.setPenColor(Color.RED);
            StdDraw.setPenRadius(rightScaleX*0.1);
            Point3D p = curr.getLocation();
            StdDraw.filledCircle(p.x(), p.y(), rightScaleX*2.5);
            StdDraw.setPenColor(Color.YELLOW);
            StdDraw.text(p.x(), p.y()-rightScaleX*0.5, "" + curr.getKey());
        }
    }

    public void drawFruits(){
        this.gameFruits = new FruitContain(this.game); //build a FruitContain
        this.gameFruits.init(this.game.getFruits()); //initialize the arr of fruits


        //Placing the fruits on the board
        for (Fruit curr : this.gameFruits.fruitsArr){
            StdDraw.picture(curr.getLocation().x(),curr.getLocation().y(),curr.getPic(),0.0008,0.0008);
        }
    }

    public void
    updateRobot(){
        //print
        this.gameRobot.init(this.game.getRobots());

        //Placing the robots on the board
        for (Robot curr : this.gameRobot.RobotArr){
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

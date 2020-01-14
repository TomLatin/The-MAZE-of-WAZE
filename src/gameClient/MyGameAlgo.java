package gameClient;

import algorithms.Graph_Algo;
import dataStructure.*;
import elements.Fruit;
import elements.FruitContain;
import elements.Robot;
import utils.Point3D;

import java.util.LinkedList;
import java.util.List;

public class MyGameAlgo {

    private LinkedList<edge_data> fruitEdge;
    private final Double EPSILON = 0.0000001;
    private MyGameGUI gameGUI;
    private graph graphGame;
    private Graph_Algo ga;

    public MyGameAlgo(MyGameGUI gameGUI, graph graphGame){
        this.gameGUI = gameGUI;
        this.graphGame = graphGame;
        this.ga.init(graphGame);
    }

    public void findFruitsEdge ( ){
        double currEdge, srcToFruit, fruitToDest;
        fruitEdge = new LinkedList<edge_data>();
        for (Fruit currFruit : this.gameGUI.gameFruits.fruitsArr) { // for each Fruit
            for (node_data currV : this.graphGame.getV()) {
                if (this.graphGame.getE(currV.getKey())!=null){
                    for (edge_data currE : this.graphGame.getE(currV.getKey())) { // for each edge

                        currEdge = currV.getLocation().distance2D(this.graphGame.getNode(currE.getDest()).getLocation()); // dist of the edge
                        srcToFruit = currV.getLocation().distance2D(currFruit.getLocation());  // dist from src to fruit
                        fruitToDest = currFruit.getLocation().distance2D(this.graphGame.getNode(currE.getDest()).getLocation()); //dist from fruit to dest

                        if(srcToFruit + fruitToDest - currEdge < EPSILON){ // is on the edge
                            if (currFruit.getTag() == 1 && currE.getSrc() < currE.getDest()) { // for example: 4->5 with type 1
                                this.fruitEdge.add(currE);
                            }
                            else if(currFruit.getTag() == -1 && currE.getSrc() > currE.getDest()) { // for example: 5->4 with type -1
                                this.fruitEdge.add(currE);
                            }
                        }
                    }
                }
            }
        }
    }


    public LinkedList<node_data> TSP (List<edge_data> targets) {
        List<edge_data> chek = new LinkedList<>();
        for (edge_data n : targets) { //check duplicate
            if(!chek.contains(n))
            {
                chek.add(n);
            }
        }
        targets=chek;
        LinkedList<node_data> path = new LinkedList<node_data>(); //the path we return
        double w=Double.MAX_VALUE; //the weight of the current full shortest path
        for (edge_data currEstart : targets) { // for all the targets nodes we start from..
            double currW=0; //weight counter
            LinkedList<node_data> currpath = new LinkedList<node_data>(); //the path we build when starting from each node
            LinkedList<edge_data> currTarget = new LinkedList<edge_data>(); //build copy of targets list
            for (edge_data k : targets){
                currTarget.add(k);
            }
            node_data currSrc = this.graphGame.getNode(currEstart.getSrc()); //choose the start node from src edge
            node_data currDest = this.graphGame.getNode(currEstart.getSrc()); //choose the start node from dest edge
            currpath.add(currSrc);
            currpath.add(currDest);
            currTarget.remove(currEstart); //remove first edge
            int size = currTarget.size();
            for (int j = 0; j < size; j++) { // do currTarget.size times:
                List <node_data> nextPath = findNextStep(currEstart,currTarget); //get the path from curr node
                if (nextPath==null) return null; //this graph is not connected
                currW += this.ga.shortestPathDist(currEstart.getDest(),nextPath.get(nextPath.size()-2).getKey()); //curr weight
                currEstart =this.graphGame.getEdge(nextPath.get(nextPath.size()-2).getKey(),nextPath.get(nextPath.size()-1).getKey()); // make the step
                currpath.addAll(nextPath); // add this part of path to the current path
                currTarget.remove(currEstart);
            }
            if (currW < w){ //check if it is the shorted option
                path=currpath;
                w=currW;
            }
        }
        Node weight = new Node(1000000,new Point3D(0,0));
        weight.setWeight(w);
        path.add(weight);
        return path;
    }

    /**
     * we will find the next step of given node from a targets list of nodes
     * @param curr the node we find the next step by shorted path
     * @param targets list of optional nodes to go from the current node
     * @return th lise of the path between the current node and his next step
     */
    private List<node_data> findNextStep(edge_data curr, List<edge_data> targets) {
        edge_data next= targets.get(0);
        for (edge_data n : targets){
            if(this.ga.shortestPathDist(curr.getDest(),n.getSrc())==Integer.MAX_VALUE) return null;
            else if(this.ga.shortestPathDist(curr.getDest(),n.getSrc()) < this.ga.shortestPathDist(curr.getDest(),next.getSrc())){//add edges weight
                next = n;
            }
        }
        List<node_data>tempPath = this.ga.shortestPath(curr.getDest(),next.getSrc());
        tempPath.remove(0);
        tempPath.add(this.graphGame.getNode(next.getDest()));
        return tempPath;
    }


    private void menagerOfRobots (){
        LinkedList<node_data> TSP = TSP(this.fruitEdge);
        double weight = TSP.getLast().getWeight();
        TSP.removeLast();
        this.gameGUI.gameRobot.RobotArr[0].setPath(TSP);
        this.gameGUI.gameRobot.RobotArr[0].setWeight(weight);
        LinkedList<Fruit> target;
        LinkedList<edge_data> currEdge;
        for (int i = 1; i <  this.gameGUI.gameRobot.RobotArr.length-1; i++) {
            target = arrayToLinkedList(this.gameGUI.gameFruits.fruitsArr);
            for (Fruit currFruit: this.gameGUI.gameFruits.fruitsArr) {
                target.remove(currFruit);
                currEdge =
            }
        }

    }

    public LinkedList<Fruit> arrayToLinkedList (Fruit[] fruitsArr){
        LinkedList<Fruit> toReturn= new LinkedList<Fruit>();
        for (int i = 0; i <fruitsArr.length ; i++) {
            toReturn.add(fruitsArr[i]);
        }
        return toReturn;
    }



}

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
        this.ga = new Graph_Algo();
        this.ga.init(graphGame);
    }

    public LinkedList<edge_data> findFruitsEdge (LinkedList<Fruit> fruitList){
        LinkedList<edge_data> toReturn = new LinkedList<edge_data>();
        double currEdge, srcToFruit, fruitToDest;
        for (Fruit currFruit : fruitList) { // for each Fruit
            for (node_data currV : this.graphGame.getV()) {
                if (this.graphGame.getE(currV.getKey())!=null){
                    for (edge_data currE : this.graphGame.getE(currV.getKey())) { // for each edge

                        currEdge = currV.getLocation().distance2D(this.graphGame.getNode(currE.getDest()).getLocation()); // dist of the edge
                        srcToFruit = currV.getLocation().distance2D(currFruit.getLocation());  // dist from src to fruit
                        fruitToDest = currFruit.getLocation().distance2D(this.graphGame.getNode(currE.getDest()).getLocation()); //dist from fruit to dest

                        if(srcToFruit + fruitToDest - currEdge < EPSILON){ // is on the edge
                            if (currFruit.getTag() == 1 && currE.getSrc() < currE.getDest()) { // for example: 4->5 with type 1
                                toReturn.add(currE);
                            }
                            else if(currFruit.getTag() == -1 && currE.getSrc() > currE.getDest()) { // for example: 5->4 with type -1
                                toReturn.add(currE);
                            }
                        }
                    }
                }
            }
        }
        return  toReturn;
    }

    public LinkedList<edge_data> TSP (LinkedList<Fruit> targets) {
        LinkedList<edge_data> before = findFruitsEdge(targets);
        LinkedList<edge_data> check = new LinkedList<>();
        for (edge_data n : before) { //check duplicate
            if(!check.contains(n))    check.add(n);
        }
        before =check;
        LinkedList<edge_data> toReturn = new LinkedList<edge_data>(); //the order of fruits edge we return
        double w=Double.MAX_VALUE; //the weight of the current full shortest path
        for (edge_data currEstart : before) { // for all the targets edges we start from..
            double currW=0; //weight counter
            LinkedList<edge_data> currTarget = new LinkedList<edge_data>(); //build copy of targets list
            for (edge_data k : before){
                currTarget.add(k);
            }
            node_data currSrc = this.graphGame.getNode(currEstart.getSrc()); //choose the Src from start edge
            node_data currDest = this.graphGame.getNode(currEstart.getDest()); //choose the Dest from start edge
            currTarget.remove(currEstart); //remove first edge
            int size = currTarget.size();
            LinkedList<edge_data> currEdgeOrdered = new LinkedList<edge_data>();
            currEdgeOrdered.add(currEstart);
            for (int j = 0; j < size; j++) { // do currTarget.size times:
                edge_data nextStep = findNextStep(currEstart,currTarget); //get the path from curr node
                currW += this.ga.shortestPathDist(currEstart.getDest(),nextStep.getSrc()); //curr weight
                currEstart =nextStep; // make the step
                currTarget.remove(currEstart);
                currEdgeOrdered.add(currEstart);
            }
            if (currW < w){ //check if it is the shorted option
                toReturn=currEdgeOrdered;
                w=currW;
            }
        }
        Edge weight = new Edge(-1,-1,w);
        toReturn.add(weight);
        return toReturn;
    }

    public LinkedList<edge_data> TSPedge (LinkedList<edge_data> before) {
        LinkedList<edge_data> check = new LinkedList<>();
        for (edge_data n : before) { //check duplicate
            if(!check.contains(n))    check.add(n);
        }
        before =check;
        LinkedList<edge_data> toReturn = new LinkedList<edge_data>(); //the order of fruits edge we return
        double w=Double.MAX_VALUE; //the weight of the current full shortest path
        for (edge_data currEstart : before) { // for all the targets edges we start from..
            double currW=0; //weight counter
            LinkedList<edge_data> currTarget = new LinkedList<edge_data>(); //build copy of targets list
            for (edge_data k : before){
                currTarget.add(k);
            }
            node_data currSrc = this.graphGame.getNode(currEstart.getSrc()); //choose the Src from start edge
            node_data currDest = this.graphGame.getNode(currEstart.getDest()); //choose the Dest from start edge
            currTarget.remove(currEstart); //remove first edge
            int size = currTarget.size();
            LinkedList<edge_data> currEdgeOrdered = new LinkedList<edge_data>();
            currEdgeOrdered.add(currEstart);
            for (int j = 0; j < size; j++) { // do currTarget.size times:
                edge_data nextStep = findNextStep(currEstart,currTarget); //get the path from curr node
                currW += this.ga.shortestPathDist(currEstart.getDest(),nextStep.getSrc()); //curr weight
                currEstart =nextStep; // make the step
                currTarget.remove(currEstart);
                currEdgeOrdered.add(currEstart);
            }
            if (currW < w){ //check if it is the shorted option
                toReturn=currEdgeOrdered;
                w=currW;
            }
        }
        Edge weight = new Edge(-1,-1,w);
        toReturn.add(weight);
        return toReturn;
    }

    /**
     * we will find the next step of given node from a targets list of nodes
     * @param curr the node we find the next step by shorted path
     * @param targets list of optional nodes to go from the current node
     * @return th lise of the path between the current node and his next step
     */


    private edge_data findNextStep(edge_data curr, List<edge_data> targets) {
        edge_data next= targets.get(0);
        for (edge_data n : targets){
            if(this.ga.shortestPathDist(curr.getDest(),n.getSrc()) < this.ga.shortestPathDist(curr.getDest(),next.getSrc())){//add edges weight
                next = n;
            }
        }
        return next;
    }

    public LinkedList<Fruit>[] placeRobotsFirstTime (){
        int numOfRobots = this.gameGUI.getGameRobot().getNumOfRobots();
        LinkedList<Fruit>[] arrListsFruits = new LinkedList[numOfRobots];
        this.fruitEdge = findFruitsEdge(arrayToLinkedList(this.gameGUI.getGameFruits().fruitsArr)); // init the fruitEdge from the array that came from the game
        LinkedList<edge_data> TSP = TSP(arrayToLinkedList(this.gameGUI.getGameFruits().fruitsArr)); // get the best path from TSP
        double weight = TSP.getLast().getWeight();
        TSP.removeLast();
        arrListsFruits[0]=arrayToLinkedList(this.gameGUI.getGameFruits().fruitsArr);
        LinkedList<edge_data> currEdgeTemp = new LinkedList<edge_data>();
        LinkedList<Fruit> fruitRobotTemp = new LinkedList<Fruit>(); //fruit list of robot we insert to
        double weightTemp = 0; //weight of dest
        double weightSrcTemp=0; //weight of src
        double profitWeight = weight;  //profit
        Fruit toAdd = null;
        boolean takeAnotherFruit = true;
        int indexOfRemoveFromRobot=-1;
        for (int i = 0; i <  numOfRobots; i++) { //for each Robot except the first
            for (int k = 0; k < numOfRobots && takeAnotherFruit && i!=0; k++) {
                for (Fruit currFruit : this.gameGUI.getGameFruits().fruitsArr) { // for each Fruit
                    boolean notContain = false;
                    if (arrListsFruits[i]==null){
                        notContain=true;
                    }
                    else if (!arrListsFruits[i].contains(currFruit)) {
                        notContain=true;
                    }
                    if (notContain){
                        if(arrListsFruits[i]!=null) {
                            fruitRobotTemp.addAll(arrListsFruits[i]);
                        }
                        fruitRobotTemp.add(currFruit); //list of fruits, add the curr fruit to curr robot
                        currEdgeTemp = TSP(fruitRobotTemp); //edge of fruits with curr fruit in -    *ordered list!!*
                        weightTemp = currEdgeTemp.getLast().getWeight(); // weight of new Robot with curr Fruit
                        currEdgeTemp.removeLast(); // best edgeList to get fruits
                        for (int j = 0; j < i; j++) {
                            if(arrListsFruits[j].contains(currFruit))// find the robot that contain the currFruit
                            {
                                fruitRobotTemp.addAll(arrListsFruits[j]);
                                fruitRobotTemp.remove(currFruit);
                                currEdgeTemp = TSP(fruitRobotTemp); //edge of fruits with curr fruit in -    *ordered list!!*
                                weightSrcTemp = currEdgeTemp.getLast().getWeight();
                                currEdgeTemp.removeLast();
                                if(weightSrcTemp < profitWeight && weightTemp < profitWeight){
                                    profitWeight = Math.max(weightSrcTemp,weightTemp);
                                    toAdd = currFruit;
                                    indexOfRemoveFromRobot = j;
                                }
                            }
                        }
                        if (indexOfRemoveFromRobot!= -1) { //if founded a profit switch
                            arrListsFruits[indexOfRemoveFromRobot].remove(toAdd);
                            arrListsFruits[i].add(toAdd);
                        }
                        else {
                            takeAnotherFruit = false;
                        }
                    }
                }
            }
            if (arrListsFruits[i]!=null) this.gameGUI.getRobotKeys()[i] = TSP(arrListsFruits[i]).getFirst().getSrc();
        }
        return arrListsFruits;
    }


    public LinkedList<Fruit> arrayToLinkedList (Fruit[] fruitsArr){
        LinkedList<Fruit> toReturn= new LinkedList<Fruit>();
        for (int i = 0; i <fruitsArr.length ; i++) {
            toReturn.add(fruitsArr[i]);
        }
        return toReturn;
    }

    public LinkedList<Fruit> findNewFruits(){
        LinkedList<Fruit> toReturn= new LinkedList<Fruit>();
        boolean theOne=true;
        for (Fruit toCheck : this.gameGUI.getGameFruits().fruitsArr){
            theOne=true;
            for(Robot r : this.gameGUI.getGameRobot().RobotArr){
                if(r.getRobotFruit().contains(toCheck)) {
                    theOne=false;
                }
            }
            if (theOne){
                toReturn.add(toCheck);
            }
        }
        return toReturn;
    }

    public void updatePathFruits(){
        LinkedList<Fruit> news = findNewFruits();
        double currWeight, profit=Double.MAX_VALUE;
        for (Fruit toFind : news){
            Robot toAdd=null;
            for(Robot currR : this.gameGUI.getGameRobot().RobotArr){
                currR.getRobotFruit().add(toFind);
                currWeight = TSP(currR.getRobotFruit()).getLast().getWeight();
                if (currWeight<profit){
                    profit = currWeight;
                    toAdd = currR;
                }
            }
            if(toAdd!=null) toAdd.getRobotFruit().add(toFind);
        }

        for(Robot currR : this.gameGUI.getGameRobot().RobotArr){
            LinkedList<node_data> toAdd = new LinkedList<node_data>();
            LinkedList<edge_data> toTsp = findFruitsEdge(currR.robotFruit);
            edge_data tocheck = this.graphGame.getEdge(currR.getSrc(),currR.getSrc());
            if (!toTsp.contains(tocheck)) toTsp.add(tocheck);
            toTsp = TSPedge(toTsp);
            for (int i = 0; i < toTsp.size()-1; i++) {
               // if (toTsp.get(i).getSrc()==toTsp.get(i).getDest()) toTsp.remove(i);
                LinkedList<node_data> currPath = this.ga.shortestPath(toTsp.get(i).getDest(),toTsp.get(i+1).getSrc());
                toAdd.addAll(currPath);
            }
            if (toAdd.get(0)==toAdd.get(1)){
                toAdd.removeFirst();
                toAdd.removeFirst();
            }
            currR.setPath(toAdd);
        }
    }

}

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
    private boolean initedFirstTime = true;


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


    public LinkedList<node_data> TSP (List<edge_data> targets) {
        List<edge_data> check = new LinkedList<>();
        for (edge_data n : targets) { //check duplicate
            if(!check.contains(n))    check.add(n);
        }
        targets=check;
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
            node_data currDest = this.graphGame.getNode(currEstart.getDest()); //choose the start node from dest edge
//            currpath.add(currSrc);
//            currpath.add(currDest);
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
//        System.out.println("p "+ path);
//        if (path.get(0).getKey()==path.get(1).getKey()) {
//            path.remove(0);
//        }
//        System.out.println("a "+ path);
        return path;
    }

    public LinkedList<edge_data> TSP2 (LinkedList<Fruit> targets) {
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
      //      LinkedList<node_data> currpath = new LinkedList<node_data>(); //the path we build when starting from each node
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
                edge_data nextStep = findNextStep2(currEstart,currTarget); //get the path from curr node


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
    private List<node_data> findNextStep(edge_data curr, List<edge_data> targets) {
        edge_data next= targets.get(0);
        for (edge_data n : targets){
            if(this.ga.shortestPathDist(curr.getDest(),n.getSrc())==Integer.MAX_VALUE) return null;
            else if(this.ga.shortestPathDist(curr.getDest(),n.getSrc()) < this.ga.shortestPathDist(curr.getDest(),next.getSrc())){//add edges weight
                next = n;
            }
        }
        List<node_data>tempPath = this.ga.shortestPath(curr.getDest(),next.getSrc());
        if (tempPath.size()>2) tempPath.remove(0);
        tempPath.add(this.graphGame.getNode(next.getDest()));
        return tempPath;
    }

    private edge_data findNextStep2(edge_data curr, List<edge_data> targets) {
        edge_data next= targets.get(0);
        for (edge_data n : targets){
            if(this.ga.shortestPathDist(curr.getDest(),n.getSrc()) < this.ga.shortestPathDist(curr.getDest(),next.getSrc())){//add edges weight
                next = n;
            }
        }
        return next;
    }

//    public void menagerOfRobots (){
//        this.fruitEdge = findFruitsEdge(arrayToLinkedList(this.gameGUI.gameFruits.fruitsArr)); // init the fruitEdge from the array that came from the game
//        LinkedList<node_data> TSP = TSP(this.fruitEdge); //get the best path from TSP
//        LinkedList<node_data> pathToFirst = this.ga.shortestPath(this.gameGUI.gameRobot.RobotArr[0].getSrc(),TSP.getFirst().getKey());//path to first node TSP
//
//        double weight = TSP.getLast().getWeight() + this.ga.shortestPathDist(this.gameGUI.gameRobot.RobotArr[0].getSrc(),TSP.getFirst().getKey()); //get the weight from the TSP
//        TSP.removeLast();
//        pathToFirst.addAll(TSP);
//        this.gameGUI.gameRobot.RobotArr[0].setPath(pathToFirst); //set path to first robot
//
////        System.out.println(this.gameGUI.gameRobot.RobotArr[0].getPath());
//
//        this.gameGUI.gameRobot.RobotArr[0].setWeight(weight); //set weight to first robot
//        this.gameGUI.gameRobot.RobotArr[0].robotFruit.addAll(arrayToLinkedList(this.gameGUI.gameFruits.fruitsArr));
//        LinkedList<Fruit> originalFruit = null;
//        LinkedList<edge_data> currEdgeTemp = null;
//        LinkedList<Fruit> fruitRobotTemp = new LinkedList<Fruit>();
//        LinkedList<node_data> currNodeTemp = null;
//        double weightTemp = 0;
//        double weightOrignalTemp=0;
//        double profitWeight = weight;
//        Fruit toAdd = null;
//        boolean takeAnotherFruit = true;
//        int indexOfRemoveFromRobot=-1;
//
//        for (int i = 1; i <  this.gameGUI.gameRobot.RobotArr.length-1; i++) { //for each Robot
//            originalFruit = arrayToLinkedList(this.gameGUI.gameFruits.fruitsArr); //init the temp list
//            for (int k = 0; k < this.gameGUI.gameFruits.fruitsArr.length && takeAnotherFruit; k++) {
//                for (Fruit currFruit : this.gameGUI.gameFruits.fruitsArr) { // for each Fruit
//                    if (!this.gameGUI.gameRobot.RobotArr[i].robotFruit.contains(currFruit)) {
//                        if(this.gameGUI.gameRobot.RobotArr[i].robotFruit!=null) {
//                            fruitRobotTemp.addAll(this.gameGUI.gameRobot.RobotArr[i].robotFruit);
//                        }
//                        fruitRobotTemp.add(currFruit); //list of fruits
//                        currEdgeTemp = findFruitsEdge(fruitRobotTemp); //edge of fruits
//                        currNodeTemp = TSP(currEdgeTemp);
//                        weightTemp = currNodeTemp.getLast().getWeight(); // weight of new Robot with curr Fruit
//                        currNodeTemp.removeLast(); // best nodelist to get fruits  nextstep ---> dest of edge of fruit
//                        for (int j = 0; j <  this.gameGUI.gameRobot.RobotArr.length-1; j++) {
//                            if(this.gameGUI.gameRobot.RobotArr[j].robotFruit.contains(currFruit))// find the robot that contain the currFruit
//                            {
//                                fruitRobotTemp.addAll(this.gameGUI.gameRobot.RobotArr[j].robotFruit);
//                                fruitRobotTemp.remove(currFruit);
//                                currEdgeTemp = findFruitsEdge(fruitRobotTemp);
//                                currNodeTemp = TSP(currEdgeTemp);
//                                weightOrignalTemp = currNodeTemp.getLast().getWeight();
//                                currNodeTemp.removeLast();
//                                if(weightOrignalTemp < profitWeight && weightTemp < profitWeight){
//                                    profitWeight = Math.max(weightOrignalTemp,weightTemp);
//                                    toAdd = currFruit;
//                                    indexOfRemoveFromRobot = j;
//                                }
//                            }
//                        }
//                        if (indexOfRemoveFromRobot!= -1) { //if founded a profit switch
//                            this.gameGUI.gameRobot.RobotArr[indexOfRemoveFromRobot].robotFruit.remove(toAdd);
//                            this.gameGUI.gameRobot.RobotArr[i].robotFruit.add(toAdd);
//                        }
//                        else {
//                            takeAnotherFruit = false;
//                        }
//                    }
//                }
//            }
//            LinkedList<node_data> filalPath = TSP(findFruitsEdge(this.gameGUI.gameRobot.RobotArr[i].robotFruit));
//            filalPath.removeLast();
//            this.gameGUI.gameRobot.RobotArr[i].setPath(filalPath);
//            if(filalPath.size()> 0 && filalPath!=null) {
//                int dest = filalPath.get(0).getKey();
//                ;
//                boolean destFound = false;
//                for (int j = 0; !destFound && j< filalPath.size(); j++) {
//                    if (filalPath.get(j).getKey() != this.gameGUI.gameRobot.RobotArr[i].getSrc()) {
//                        dest = filalPath.get(j).getKey();
//                        destFound = true;
//                    }
//                }
//                this.gameGUI.gameRobot.RobotArr[i].setDest(dest);
//            }
//            else this.gameGUI.gameRobot.RobotArr[i].setDest(this.gameGUI.gameRobot.RobotArr[i].getSrc());
//
//        }
//    }

    public void menagerOfRobots2 (){
        this.fruitEdge = findFruitsEdge(arrayToLinkedList(this.gameGUI.gameFruits.fruitsArr)); // init the fruitEdge from the array that came from the game
        LinkedList<edge_data> TSP = TSP2(arrayToLinkedList(this.gameGUI.gameFruits.fruitsArr)); //get the best path from TSP
        LinkedList<edge_data> pathToFirst = new LinkedList<edge_data>();  //path to first node TSP
        pathToFirst.add(this.graphGame.getEdge(this.gameGUI.gameRobot.RobotArr[0].getSrc(),this.gameGUI.gameRobot.RobotArr[0].getSrc()));
        double weight = TSP.getLast().getWeight() + this.ga.shortestPathDist(this.gameGUI.gameRobot.RobotArr[0].getSrc(),TSP.getFirst().getSrc()); //get the weight from the TSP
        TSP.removeLast();
        pathToFirst.addAll(TSP);
        this.gameGUI.gameRobot.RobotArr[0].setWeight(weight); //set weight to first robot
        this.gameGUI.gameRobot.RobotArr[0].robotFruit.addAll(arrayToLinkedList(this.gameGUI.gameFruits.fruitsArr));
        LinkedList<Fruit> originalFruit = new LinkedList<Fruit>();
        LinkedList<edge_data> currEdgeTemp = null;
        LinkedList<Fruit> fruitRobotTemp = new LinkedList<Fruit>(); //fruit list of robot we insert to
        double weightTemp = 0; //weight of dest
        double weightSrcTemp=0; //weight of src
        double profitWeight = weight;  //profit
        Fruit toAdd = null;
        boolean takeAnotherFruit = true;
        int indexOfRemoveFromRobot=-1;
        for (int i = 0; i <  this.gameGUI.gameRobot.RobotArr.length; i++) { //for each Robot except the first
            originalFruit = arrayToLinkedList(this.gameGUI.gameFruits.fruitsArr); //init the temp list of src
            for (int k = 0; k < this.gameGUI.gameFruits.fruitsArr.length && takeAnotherFruit && i!=0; k++) {
                for (Fruit currFruit : this.gameGUI.gameFruits.fruitsArr) { // for each Fruit
                    if (!this.gameGUI.gameRobot.RobotArr[i].robotFruit.contains(currFruit)) {
                        if(this.gameGUI.gameRobot.RobotArr[i].robotFruit!=null) {
                            fruitRobotTemp.addAll(this.gameGUI.gameRobot.RobotArr[i].robotFruit);
                        }
                        fruitRobotTemp.add(currFruit); //list of fruits, add the curr fruit to curr robot
                        currEdgeTemp = TSP2(fruitRobotTemp); //edge of fruits with curr fruit in -    *ordered list!!*
                        weightTemp = currEdgeTemp.getLast().getWeight(); // weight of new Robot with curr Fruit
                        currEdgeTemp.removeLast(); // best edgeList to get fruits
                        for (int j = 0; j < i; j++) {
                            if(this.gameGUI.gameRobot.RobotArr[j].robotFruit.contains(currFruit))// find the robot that contain the currFruit
                            {
                                fruitRobotTemp.addAll(this.gameGUI.gameRobot.RobotArr[j].robotFruit);
                                fruitRobotTemp.remove(currFruit);
                                currEdgeTemp = TSP2(fruitRobotTemp); //edge of fruits with curr fruit in -    *ordered list!!*
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
                            this.gameGUI.gameRobot.RobotArr[indexOfRemoveFromRobot].robotFruit.remove(toAdd);
                            this.gameGUI.gameRobot.RobotArr[i].robotFruit.add(toAdd);
                        }
                        else {
                            takeAnotherFruit = false;
                        }
                    }
                }
            }
//---------------------------for each robot-------------------------
            double ww= Double.MAX_VALUE;
            LinkedList<node_data> toReturn= new LinkedList<node_data>();
            LinkedList<edge_data> currE = findFruitsEdge(this.gameGUI.gameRobot.RobotArr[i].robotFruit);
            for ( edge_data toCheck : currE ){
                if (this.ga.shortestPathDist(this.gameGUI.gameRobot.RobotArr[i].getSrc(),toCheck.getSrc())<ww){
                    ww = this.ga.shortestPathDist(this.gameGUI.gameRobot.RobotArr[i].getSrc(),toCheck.getSrc());
                    toReturn = this.ga.shortestPath(this.gameGUI.gameRobot.RobotArr[i].getSrc(),toCheck.getSrc());
                    toReturn.add(this.graphGame.getNode(toCheck.getDest()));
                }
            }
            toReturn.removeFirst();
            System.out.println(toReturn);
            this.gameGUI.gameRobot.RobotArr[i].setPath(toReturn);

//            Node fective = new Node(10000*i,this.gameGUI.gameRobot.RobotArr[i].getLocation());
//            this.graphGame.addNode(fective);
//            LinkedList<edge_data> currE = findFruitsEdge(this.gameGUI.gameRobot.RobotArr[i].robotFruit);
//            for(edge_data toConnect : currE){
//                this.graphGame.connect(toConnect.getDest(),10000*i,0);
//            }
//            this.gameGUI.gameRobot.RobotArr[i].setPath(this.ga.shortestPath( this.gameGUI.gameRobot.RobotArr[i].getSrc(),10000*i));
        }
    }


    public void initFirstTime (){
        int i = 0;
        for (int j=0; j<this.gameGUI.gameRobot.RobotArr.length ; j++) {
            Robot r = new Robot(j+1);
            this.gameGUI.gameRobot.RobotArr[j] = r;
            while(i<(this.gameGUI.gameFruits.fruitsArr.length/this.gameGUI.gameRobot.RobotArr.length)+j){
                r.robotFruit.add(this.gameGUI.gameFruits.fruitsArr[i]);
                i++;
            }
          //  r.setPath(TSP(findFruitsEdge(r.robotFruit)));
            this.gameGUI.robotKeys[j] = findFruitsEdge(r.robotFruit).getFirst().getSrc();
            System.out.println(this.gameGUI.robotKeys[j]);
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

package elements;

import dataStructure.node_data;
import org.json.JSONException;
import org.json.JSONObject;
import utils.Point3D;

import java.util.LinkedList;

public class Robot implements elementsInTheGame{
    //Fields
    private int id;
    private double value;
    private int src;
    private int dest;
    private  double speed;
    private Point3D pos;
    private String Pic;
    public double weight;

    public LinkedList<node_data> path; //Keeps the way of the node it needs to pass
    public LinkedList<Fruit> robotFruit; //Keep the fruit that aging to the robot

    /**
     *Copy constructor
     * In order to create a robot, first of all, you have to create a definitive robot and then make it insertion of all
     * data by the init function that receives a Json file
     */
    public Robot()
    {
        this.id=0;
        this.value=0;
        this.src=0;
        this.dest=0;
        this.speed=0;
        this.pos=null;
        this.Pic="";
        this.path = null;
        this.weight= 0;
        this.robotFruit = new LinkedList<Fruit>();

    }

    public Robot(int src,int dest,int id,Point3D pos,int speed,double value,String pic){
        this.src = src;
        this.dest = dest;
        this.id = id;
        this.pos = pos;
        this.speed = speed;
        this.value = value;
        this.Pic = pic;
    }

    /**
     * @param json that we receive from server
     * @return elementsInTheGame that in our case will be Robot
     */
    @Override
    public elementsInTheGame init(String json) {
        Robot toReturn = new Robot();
        try {
            JSONObject line = new JSONObject(json);
            JSONObject Robot = line.getJSONObject("Robot");
            toReturn.id= Robot.getInt("id");
            toReturn.value= Robot.getDouble("value");
            toReturn.src= Robot.getInt("src");
            toReturn.dest= Robot.getInt("dest");
            toReturn.speed= Robot.getDouble("speed");
            String posString = Robot.getString("pos").toString();
            toReturn.pos= new Point3D(posString);
            toReturn.Pic = "spidermen.png";
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return toReturn;
    }

    /**
     * Defines an image to represent the robot
     * @param file_name of the picture
     */
    @Override
    public void setPic(String file_name) {
        this.Pic = file_name;
    }

    /**
     * @return a string that represent the name of the robot picture
     */
    @Override
    public String getPic() {
        return this.Pic;
    }

    /**
     * @return id of robot
     */
    @Override
    public int getKey() {
        return this.id;
    }

    /**
     * @return Location of the robot in Point3D
     */
    @Override
    public Point3D getLocation() {
        return this.pos;
    }

    /**
     * @param p -  new location (position) of this robot.
     */
    @Override
    public void setLocation(Point3D p) {
        this.pos = p;
    }

    /**
     * @return The accumulated value of the points the robot has collected
     */
    public double getValue() {
        return this.value;
    }

    /**
     * @param w the new accumulated value of the points the robot has collected
     */
    public void setValue(double w) {
        this.value = w;
    }

    /**
     * We do NOT USE this function it is because we have configured the interface to implement node_data and because we
     * are not allowed to change this interface we have some unnecessary functions
     * @return 0
     */
    @Override
    public double getWeight() {
        return this.weight;
    }

    /**
     *We do NOT USE this function it is because we have configured the interface to implement node_data and because we
     * are not allowed to change this interface we have some unnecessary functions
     * @param w - the new weight
     */
    @Override
    public void setWeight(double w) {
        this.weight = w;
    }

    /**
     * We do NOT USE this function it is because we have configured the interface to implement node_data and because we
     * are not allowed to change this interface we have some unnecessary functions
     * @return null
     */
    @Override
    public String getInfo() {
        return null;
    }

    /**
     * We do NOT USE this function it is because we have configured the interface to implement node_data and because we
     * are not allowed to change this interface we have some unnecessary functions
     */
    @Override
    public void setInfo(String s) {

    }

    /**
     * We do NOT USE this function it is because we have configured the interface to implement node_data and because we
     * are not allowed to change this interface we have some unnecessary functions
     * @return 0
     */
    @Override
    public int getTag() {
        return 0;
    }

    /**
     * We do NOT USE this function it is because we have configured the interface to implement node_data and because we
     * are not allowed to change this interface we have some unnecessary functions
     */
    @Override
    public void setTag(int t) {

    }

    /**
     * @return the way of the node it needs to pass
     */
    public LinkedList<node_data> getPath (){
        return this.path;
    }

    /**
     * set the way of the node it needs to pass
     * @param path
     */
    public void setPath( LinkedList<node_data> path){
        this.path = path;
    }

    /**
     * @return the fruit that aging to the robot
     */
    public LinkedList<Fruit> getRobotFruit (){
        return this.robotFruit;
    }

    /**
     * set the the fruit that aging to the robot
     * @param robotFruit
     */
    public void setRobotFruit( LinkedList<Fruit> robotFruit){
        this.robotFruit = robotFruit;
    }

    /**
     * @return thr speed of thr robot
     */
    public double getSpeed() {
        return this.speed;
    }

    /**
     * @return the src node of the robot
     */
    public int getSrc() {
        return this.src;
    }

    /**
     * set the src node of the robot
     * @param srcKey
     */
    public void setSrc(int srcKey) {
        this.src = srcKey;
    }

    /**
     *
     * @return the dest node of the robot
     */
    public int getDest() {
        return this.dest;
    }

    /**
     * set the dest node of the robot
     * @param destKey
     */
    public void setDest(int destKey) {
        this.dest = destKey;
    }
}

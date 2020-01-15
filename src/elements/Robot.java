package elements;

import dataStructure.node_data;
import org.json.JSONException;
import org.json.JSONObject;
import utils.Point3D;

import java.util.LinkedList;

public class Robot implements elementsInTheGame{

    private int id;
    private double value;
    private int src;
    private int dest;
    private  double speed;
    private Point3D pos;
    private String Pic;
    public double weight;

    public LinkedList<node_data> path;
    public LinkedList<Fruit> robotFruit;

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
        this.robotFruit = null;

    }

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
            toReturn.Pic = "robot.jpg";
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return toReturn;
    }

    @Override
    public void setPic(String file_name) {
        this.Pic = file_name;
    }

    @Override
    public String getPic() {
        return this.Pic;
    }

//    @Override
//    public void drawOnBorad(elementsInTheGame toDraw) {
//
//    }

    @Override
    public int getKey() {
        return this.id;
    }

    @Override
    public Point3D getLocation() {
        return this.pos;
    }

    @Override
    public void setLocation(Point3D p) {
        this.pos = p;
    }

    public double getValue() {
        return this.value;
    }

    public void setValue(double w) {
        this.value = w;
    }
    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public void setWeight(double w) {
        this.weight = w;
    }

    @Override
    public String getInfo() {
        return null;
    }

    @Override
    public void setInfo(String s) {

    }

    @Override
    public int getTag() {
        return 0;
    }

    @Override
    public void setTag(int t) {

    }

    public LinkedList<node_data> getPath (){
        return  this.path;
    }

    public void setPath( LinkedList<node_data> path){
        this.path = path;
    }
    public LinkedList<Fruit> getRobotFruit (){
        return this.robotFruit;
    }

    public void setRobotFruit( LinkedList<Fruit> robotFruit){
        this.robotFruit = robotFruit;
    }

    public double getSpeed() {
        return this.speed;
    }

    public int getSrc() {
        return this.src;
    }

    public void setSrc(int srcKey) {
        this.src = srcKey;
    }

    public int getDest() {
        return this.dest;
    }

    public void setDest(int destKey) {
        this.dest = destKey;
    }
}

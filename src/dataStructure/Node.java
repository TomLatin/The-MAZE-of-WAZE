package dataStructure;

import utils.Point3D;
import java.io.Serializable;
public class Node implements node_data,Serializable {

    //Fields
    private int key;
    private Point3D point;
    private double weight;
    private int tag;
    private String info;
    private static int nextKey = 1;

    /**
     *The default constructor
     */
    public Node(){
        this.key=nextKey++;
        this.point=null;
        this.weight=0;
        this.tag=0;
        this.info="";

    }

    /**
     *A constructor that accepts parameters
     */
    public Node( Point3D point, double weight){
        this.key=nextKey++;
        this.point=point;
        this.weight=weight;
        this.tag=0;
        this.info="";
    }

    /**
     *Copy constructor
     */
    public Node(Node n){
        this.key=n.key;
        this.point=new Point3D(n.point);
        this.weight=n.weight;
        this.tag=n.tag;
        this.info=n.info;
    }

    /**
     * @return Returns the key value of the Node that sent to the function
     */
    @Override
    public int getKey() {
        return this.key;
    }

    /**
     * @return Returns the point of the Node that sent to the function
     */
    @Override
    public Point3D getLocation() {
        return this.point;
    }

    /**
     * Sets a new point at the same point of the Node
     */
    @Override
    public void setLocation(Point3D p) {
        this.point=new Point3D(p);
    }

    /**
     * @return The weight of the node
     */
    @Override
    public double getWeight() {
            return this.weight;
    }

    /**
     * @param w - the new weight
     * set w to be the new weight of the node
     */
    @Override
    public void setWeight(double w) {
        this.weight=w;
    }

    /**
     * @return The info of the node
     */
    @Override
    public String getInfo() {
        return this.info;
    }

    /**
     * @param s the new info
     *set s to be the new info of the node
     */
    @Override
    public void setInfo(String s) {
        this.info=s;
    }

    /**
     * @return The tag of the node
     */
    @Override
    public int getTag() {
        return this.tag;
    }

    /**
     * @param t - the new value of the tag
     * set t to be the new tag of the node
     */
    @Override
    public void setTag(int t) {
        this.tag=t;
    }

    /**
     *A method that prints all the parameters of the node
     */
    public String toString()
    {
        return "key: "+this.key+",point: "+this.point+",weight: "+this.weight+",info: "+this.info+",tag: "+this.tag;
    }

}

package dataStructure;

import utils.Point3D;
import java.io.Serializable;
public class Node implements node_data,Serializable {

    private int key;
    private Point3D point;
    private double weight;
    private int tag;
    private String info;

    public Node(){
        this.key=0;
        this.point=null;
        this.weight=0;
        this.tag=0;
        this.info="";

    }
    public Node(int key, Point3D point, double weight, int tag, String info){
        this.key=key;
        this.point=point;
        this.weight=weight;
        this.tag=tag;
        this.info=info;
    }

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

    @Override
    public Point3D getLocation() {
        return this.point;
    }

    @Override
    public void setLocation(Point3D p) {
        this.point=new Point3D(p);
    }

    @Override
    public double getWeight() {
            return this.weight;
    }

    @Override
    public void setWeight(double w) {
        this.weight=w;
    }

    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public void setInfo(String s) {
        this.info=s;
    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
        this.tag=t;
    }

    public String toString()
    {
        return "key: "+this.key+",point: "+this.point+",weight: "+this.weight+",info: "+this.info+",tag: "+this.tag;
    }
}

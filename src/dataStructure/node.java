package dataStructure;

import utils.Point3D;

public class node implements node_data {

    private int key;
    private Point3D point;
    private double weight;
    private int tag;
    private String info;
    //private node previos;

    public node(){
        this.key=0;
        this.point=null;
        this.weight=0;
        this.tag=0;
        this.info="";

    }
    public node (int key, Point3D point,double weight,int tag,String info){
        this.key=key;
        this.point=point;
        this.weight=weight;
        this.tag=tag;
        this.info=info;
    }

    public node (node n){
        this.key=n.key;
        this.point=new Point3D(n.point);
        this.weight=n.weight;
        this.tag=n.tag;
        this.info=n.info;
    }

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
}

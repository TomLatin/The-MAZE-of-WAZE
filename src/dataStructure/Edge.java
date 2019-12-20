package dataStructure;

public class Edge implements edge_data{
    private int src;
    private int dest;
    private double weight;
    private String info;
    private int tag;

    public Edge(){
        this.src=0;
        this.dest=0;
        this.weight=0.0;
        this.info="";
        this.tag=0;
    }

    public Edge(int s, int d, double w, String i, int t){
        this.src=s;
        this.dest=d;
        this.weight=w;
        this.info=i;
        this.tag=t;
    }

    public Edge(Edge e){
        this.src=e.src;
        this.dest=e.dest;
        this.weight=e.weight;
        this.info=e.info;
        this.tag=e.tag;
    }

    @Override
    public int getSrc() {
        return this.src;
    }

    @Override
    public int getDest() {
        return this.dest;
    }

    @Override
    public double getWeight() {
        return this.weight;
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

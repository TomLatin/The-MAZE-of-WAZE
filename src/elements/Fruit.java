package elements;

import org.json.JSONException;
import org.json.JSONObject;
import utils.Point3D;

public class Fruit implements elementsInTheGame{
    private double value;
    private int type;
    private Point3D pos;
    private String Pic;


    public Fruit(){
        this.value = 0;
        this.type = 0;
        this.pos = null;
        this.Pic = "";
    }

    @Override
    public elementsInTheGame init(String json) {
        Fruit toReturn = new Fruit();
        try {
            JSONObject line = new JSONObject(json);
            JSONObject fruit = line.getJSONObject("Fruit");
            toReturn.value= fruit.getDouble("value");
            toReturn.type= fruit.getInt("type");
            if (toReturn.type==1) toReturn.Pic = "Banana.jpeg";
            else toReturn.Pic = "Strawberry.jpg";
            String posSring = fruit.getString("pos").toString();
            toReturn.pos= new Point3D(posSring);
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


    @Override
    public int getKey() {
        return 0;
    }

    @Override
    public Point3D getLocation() {
        return this.pos;
    }

    @Override
    public void setLocation(Point3D p) {
        this.pos = p;
    }

    @Override
    public double getWeight() {
        return this.value;
    }

    @Override
    public void setWeight(double w) {
        this.value = w;
    }

    @Override
    public String getInfo() {
        return null;
    }

    @Override
    public void setInfo(String s) {

    }

    //type
    @Override
    public int getTag() {
        return this.type;
    }

    @Override
    public void setTag(int t) {

    }
}

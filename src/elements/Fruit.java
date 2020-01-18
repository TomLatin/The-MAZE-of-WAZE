package elements;

import org.json.JSONException;
import org.json.JSONObject;
import utils.Point3D;

public class Fruit implements elementsInTheGame{
    //Fields
    private double value; //The value of the Fruit
    private int type; //The type of the Fruit, can be -1 or 1
    private Point3D pos; //The location of the Fruit in the graph
    private String Pic; //the picture of the Fruit

    /**
     *The default constructor
     */
    public Fruit(){
        this.value = 0;
        this.type = 0;
        this.pos = null;
        this.Pic = "";
    }

    /**
     * Insert data into object Fruit from the server
     * @param json that we receive from server
     * @return elementsInTheGame, that in our case will be Fruit
     */
    @Override
    public elementsInTheGame init(String json) {
        Fruit toReturn = new Fruit();
        try {
            JSONObject line = new JSONObject(json);
            JSONObject fruit = line.getJSONObject("Fruit");
            toReturn.value= fruit.getDouble("value");
            toReturn.type= fruit.getInt("type");
            if (toReturn.type==1) toReturn.Pic = "greenStone.png";
            else toReturn.Pic = "redStone.png";
            String posSring = fruit.getString("pos").toString();
            toReturn.pos= new Point3D(posSring);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return toReturn;
    }

    /**
     * @param file_name The file name of the image set for the object Fruit view
     */
    @Override
    public void setPic(String file_name) {
        this.Pic = file_name;
    }

    /**
     * @return The file name of the image for the object Fruit view
     */
    @Override
    public String getPic() {
        return this.Pic;
    }

    /**
     * We do NOT USE this function it is because we have configured the interface to implement node_data and because we
     * are not allowed to change this interface we have some unnecessary functions
     * @return 0
     */
    @Override
    public int getKey() {
        return 0;
    }

    /**
     * @return Returns the point of the Fruit that sent to the function
     */
    @Override
    public Point3D getLocation() {
        return this.pos;
    }

    /**
     * Sets a new point at the same point of the Fruit
     */
    @Override
    public void setLocation(Point3D p) {
        this.pos = p;
    }

    /**
     * we use the getWeight for return value of Fruit
     * @return The value of the Fruit
     */
    @Override
    public double getWeight() {
        return this.value;
    }

    /**
     * we use the setWeight for set the value of Fruit
     * @param w - the new value
     */
    @Override
    public void setWeight(double w) {
        this.value = w;
    }

    /**
     * We do NOT USE this function it is because we have configured the interface to implement node_data and because we
     * are not allowed to change this interface we have some unnecessary functions
     * @return NULL
     */
    @Override
    public String getInfo() {
        return null;
    }

    /**
     * We do NOT USE this function it is because we have configured the interface to implement node_data and because we
     * are not allowed to change this interface we have some unnecessary functions
     * @param
     */
    @Override
    public void setInfo(String s) {

    }

    /**
     * we use the getTag for return type of Fruit
     * @return The type of the Fruit, can be -1 or 1
     */
    @Override
    public int getTag() {
        return this.type;
    }

    /**
     *We do NOT USE this function it is because we have configured the interface to implement node_data and because we
     *are not allowed to change this interface we have some unnecessary functions
     */
    @Override
    public void setTag(int t) {

    }
}

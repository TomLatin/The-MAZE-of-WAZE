package elements;

import dataStructure.node_data;

public interface elementsInTheGame extends node_data {

    /**
     * @param json that we receive from server
     * @return object type elementsInTheGame
     */
    public elementsInTheGame init(String json);

    /**
     * set picture to object type elementsInTheGame
     * @param file_name
     */
    public void setPic (String file_name);

    /**
     *
     * @return the name of thr picture of the object type elementsInTheGame
     */
    public String getPic();




}

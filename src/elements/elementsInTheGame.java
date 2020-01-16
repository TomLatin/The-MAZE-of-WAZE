package elements;

import dataStructure.node_data;

public interface elementsInTheGame extends node_data {

    /**
     * @param json that we receive from server
     * @return object type elementsInTheGame
     */
    public elementsInTheGame init(String json);

    public void setPic (String file_name);

    public String getPic();




}

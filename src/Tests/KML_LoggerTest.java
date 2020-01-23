package Tests;

import gameClient.KML_Logger;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static gameClient.MyGameGUI.getKML;
import static org.junit.jupiter.api.Assertions.*;

class KML_LoggerTest {

    /**
     * If it fails then false
     */
    @Test
    void print() {
        String kml = getKML(313525792,0);
        System.out.println("***** KML file example: ******");
        System.out.println(kml);
        assertNotNull(kml);
    }
}
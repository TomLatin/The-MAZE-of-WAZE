package gameClient;

import Server.game_service;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Icon;
import de.micromata.opengis.kml.v_2_2_0.IconStyle;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Style;
import de.micromata.opengis.kml.v_2_2_0.TimeSpan;
import de.micromata.opengis.kml.v_2_2_0.TimeStamp;
import elements.Fruit;
import elements.Robot;
import elements.RobotsContain;
import org.junit.experimental.theories.Theories;
import utils.StdDraw;

import javax.swing.*;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;



public class KML_Logger {

   public static MyGameGUI myGameGUI;

    public void objectToKml()throws ParseException, InterruptedException
    {
        int i=0;
        Kml kml =new Kml(); //create KML object
        Document document=kml.createAndSetDocument(); //create Document object
        while (myGameGUI.getGame().isRunning())
        {
            Thread.sleep(100);
            i++;
            Robot[] arrR=myGameGUI.getGameRobot().RobotArr;
            Fruit [] arrF=myGameGUI.getGameFruits().fruitsArr;

            for (int j = 0; j <arrR.length ; j++) {
                Placemark placemark=document.createAndAddPlacemark();
                Icon icon=new Icon();
                icon.setHref("spidermen.png");
                icon.setViewBoundScale(1);
                icon.setViewRefreshTime(1);
                icon.withRefreshInterval(1);

                IconStyle iconStyle=new IconStyle();
                iconStyle.setScale(1);
                iconStyle.setHeading(1);
                iconStyle.setColor("ff007db3");
                iconStyle.setIcon(icon);
                placemark.createAndAddStyle().setIconStyle(iconStyle);
                placemark.withDescription("Type:Super Hero").withOpen(Boolean.TRUE).createAndSetPoint().addToCoordinates(arrR[j].getLocation().x(),arrR[j].getLocation().y());
                String timeFirst=disIntMillisToString(StringToDisInMillis(currentTime())+i*1000);
                String timeSecond=disIntMillisToString(StringToDisInMillis(currentTime())+(i+1)*1000);
                String [] first=timeFirst.split(" ");
                timeFirst=first[0]+"T"+first[1]+"Z";
                String [] seconde=timeSecond.split(" ");
                timeSecond=seconde[0]+"T"+seconde[1]+"Z";
                TimeSpan timeSpan=placemark.createAndSetTimeSpan();
                timeSpan.setBegin(timeFirst);
                timeSpan.setEnd(timeSecond);
            }

            for (int j = 0; j <arrF.length ; j++) {
                Placemark placemark=document.createAndAddPlacemark();
                Icon icon=new Icon();
                icon.setHref("redStone.png");
                icon.setViewBoundScale(1);
                icon.setViewRefreshTime(1);
                icon.withRefreshInterval(1);

                IconStyle iconStyle=new IconStyle();
                iconStyle.setScale(1);
                iconStyle.setHeading(1);
                iconStyle.setColor("ff007db3");
                iconStyle.setIcon(icon);
                placemark.createAndAddStyle().setIconStyle(iconStyle);
                placemark.withDescription("Type:Power Stone").withOpen(Boolean.TRUE).createAndSetPoint().addToCoordinates(arrF[j].getLocation().x(),arrF[j].getLocation().y());
                String timeFirst=disIntMillisToString(StringToDisInMillis(currentTime())+i*1000);
                String timeSecond=disIntMillisToString(StringToDisInMillis(currentTime())+(i+1)*1000);
                String [] first=timeFirst.split(" ");
                timeFirst=first[0]+"T"+first[1]+"Z";
                String [] seconde=timeSecond.split(" ");
                timeSecond=seconde[0]+"T"+seconde[1]+"Z";
                TimeSpan timeSpan=placemark.createAndSetTimeSpan();
                timeSpan.setBegin(timeFirst);
                timeSpan.setEnd(timeSecond);
            }

        }
        try
        {
            JOptionPane.showConfirmDialog(null,"Save to kml?!","save?",JOptionPane.YES_NO_OPTION);
            kml.marshal(new File("KmlGAME.kml"));
        }
        catch (Exception e)
        {
            System.out.println("The KML isn't create");
        }

    }


    private String disIntMillisToString(Long distMillis)
    {
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDate.format(new Date(distMillis));
    }


    private long StringToDisInMillis(String TimeAsString) throws ParseException
    {
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        Date date = simpleDate.parse(TimeAsString.toString());
        long disInMillis = ((Date) date).getTime();
        return disInMillis;
    }

    private String currentTime()
    {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
    }
}

package GUI;

import dataStructure.*;
import utils.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

//public class Graph_GUI {

public class Window extends JFrame implements ActionListener
{
    LinkedList<Point3D> points = new LinkedList<Point3D>();

    public void Window()
    {
        initGUI();
    }

    private void initGUI()
    {
        this.setSize(1000, 1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Menu");
        menuBar.add(menu);
        this.setMenuBar(menuBar);

        MenuItem item1 = new MenuItem("Item 1");
        item1.addActionListener(this);

        MenuItem item2 = new MenuItem("Item 2");
        item2.addActionListener(this);

        menu.add(item1);
        menu.add(item2);

   //     this.addMouseListener(this);
    }

    public void paint(Graphics g)
    {
        super.paint(g);

        Point3D prev = null;

        for (Point3D p : points)
        {
            g.setColor(Color.BLUE);
            g.fillOval((int)p.x(), (int)p.y(), 10, 10);

            if(prev != null)
            {
                g.setColor(Color.RED);
                g.drawLine((int)p.x(), (int)p.y(),
                        (int)prev.x(), (int)prev.y());

                g.drawString("5", (int)((p.x()+prev.x())/2),(int)((p.y()+prev.y())/2));
            }

            prev = p;
        }
    }



    @Override
    public void actionPerformed(ActionEvent e)
    {
        String str = e.getActionCommand();

        if(str.equals("Item 1"))
        {
            Point3D p1 = new Point3D(100,100);
            Point3D p2 = new Point3D(50,300);
            Point3D p3 = new Point3D(400,150);

            points.add(p1);
            points.add(p2);
            points.add(p3);

            repaint();
        }

    }


    public static void main(String[] args)
    {
        Window window = new Window();
        window.setVisible(true);
    }
}




//    public static void printGraph(DGraph d){
//        StdDraw.setCanvasSize(1000,1000);
//        StdDraw.setXscale(-100,100);
//        StdDraw.setYscale(-100,100);
//        StdDraw.setPenColor(Color.BLACK);
//        StdDraw.setPenRadius(0.005);
//        for (node_data currV : d.getV()) {
//            for (edge_data currE : d.getE(currV.getKey())) {
//                double weight = currE.getWeight();
//                node_data srcN = currV;
//                node_data dstN = d.getNode(currE.getDest());
//                Point3D srcP= srcN.getLocation();
//                Point3D dstP= dstN.getLocation();
//                StdDraw.line(srcP.x(),srcP.y(),dstP.x(),dstP.y());
//                StdDraw.setPenColor(Color.BLACK);
//                StdDraw.text((srcP.x()+dstP.x())/2,(srcP.y()+dstP.y())/2,"" + weight);
//                double tX= srcP.x()+(dstP.x()-srcP.x())*0.8 , tY= srcP.y()+(dstP.y()-srcP.y())*0.8;
//                StdDraw.filledRectangle(tX,tY,0.8,0.8);
//            }
//        }
//        StdDraw.setPenColor(Color.RED);
//        StdDraw.setPenRadius(0.1);
//        for (node_data curr : d.getV()){
//            Point3D p = curr.getLocation();
//            StdDraw.filledCircle(p.x(),p.y(),1);
//            StdDraw.text(p.x(),p.y()+2,""+curr.getKey());
//        }
//
//
//
//    }
//
//
//
//    public static void main(String[] args) {
//        DGraph Graph = new DGraph();
//        Point3D p[] = new Point3D[6];
//        Node n[] = new Node[6];
//        p[0]=new Point3D(15,0);
//        p[1]=new Point3D(40,10);
//        p[2]=new Point3D(0,20);
//        p[3]=new Point3D(20,20);
//        p[4]=new Point3D(19,40);
//        p[5]=new Point3D(9,50);
//        n[0]=new Node(1,p[0],0,20,"a");
//        n[1]=new Node(2,p[1],0,10,"b");
//        n[2]=new Node(3,p[2],0,15,"c");
//        n[3]=new Node(4,p[3],0,5,"d");
//        n[4]=new Node(5,p[4],0,0,"e");
//        n[5]=new Node(6,p[5],0,25,"f");
//        for (int i = 0; i <p.length ; i++) {
//            Graph.addNode(n[i]);
//        }
//        Graph.connect(1,4,20);
//        Graph.connect(2,1,10);
//        Graph.connect(6,1,5);
//        Graph.connect(1,6,6);
//        Graph.connect(4,3,44);
//        Graph.connect(5,6,1);
//        Graph.connect(6,3,22);
//        Graph.connect(3,4,20);
//        Graph.connect(2,5,10);
//        printGraph(Graph);
//    }
//}

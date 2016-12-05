import oracle.jvm.hotspot.jfr.JFR;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class visGraph2 extends NodeProc{


    public visGraph2(JPanel panel, JFrame frame){
        panel.setBounds(800, 800, 200, 100);
        panel.setLayout(null);
        NodeProc myNode = new NodeProc(1, 24f, .01f);
        NodeProc myNode2 = new NodeProc(40, 3f, 23f);
        NodeProc myNode3 = new NodeProc(153, 13f, 3f);
        NodeProc myNode4 = new NodeProc(9999, 7f, 10f);

        NodeProc[] nodes = {myNode,myNode2, myNode3,myNode4};

        //setLayout(new java.awt.GridLayout(4, 4));
        for (int i = 0; i < 4; ++i) {
            JButton b = new JButton(String.valueOf(nodes[i].getPid()));


            Float ramF = nodes[i].getRamUsage();
            if (ramF > 7){
                ramF = 7f;
            }
            if (ramF < 1.5){
                ramF = 1.5f;
            }
            int ram = Math.round(ramF);
            ram = ram * 40;
            b.setSize(ram,ram);
            //Button Color
            float blueF = 0;
            float redF = 0;
            Float cpuF = nodes[i].getCpuUsage();
            if (cpuF > 25){
                cpuF = 25f;
            }
            if (cpuF < 1){
                cpuF = 1f;
            }
            if (cpuF == 25){
                redF = cpuF*10;
            }
            else if (cpuF < 25 && cpuF >= 5){
                blueF = 255;
                redF = 8;
                redF = redF*cpuF;
                blueF = blueF/cpuF*5;
            }
            else if (cpuF < 5){
                redF = cpuF*10;
                blueF = 255;
            }
            int blue = Math.round(blueF);
            int red = Math.round(redF);
            Color btnColor = new Color(red,0,blue);

            b.setBackground(btnColor);
            b.setOpaque(true);
            b.setBorderPainted(false);
            b.setLocation(i*125, i*125);

            final JPopupMenu menu = new JPopupMenu("controls");
            JMenuItem item1 = new JMenuItem("Kill");
            JMenuItem item2 = new JMenuItem("Quit");
            JMenuItem item3 = new JMenuItem("Start");

            menu.add(item1);
            menu.add(item2);
            menu.add(item3);

            panel.add(b);
            NodeProc temp = nodes[i];
            b.addActionListener(new ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    menu.show(b, b.getWidth()/2, b.getHeight()/2);
                    System.out.println(temp.getCpuUsage());
                }
            });
        }
        frame.add(panel);
    }


    public static void main(String args[]) {
        JPanel myPanel = new JPanel();
        JFrame myFrame = new JFrame();
        myPanel.setVisible(true);

            myFrame.setTitle("test");
            myFrame.setSize(900, 900);
            myFrame.add(new visGraph2(myPanel, myFrame));
            myFrame.add(myPanel);
            //myFrame.setLocationRelativeTo(null);
            myFrame.setVisible(true);
            myFrame.setDefaultCloseOperation(myFrame.EXIT_ON_CLOSE);
    }



}

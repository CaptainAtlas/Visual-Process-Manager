import oracle.jvm.hotspot.jfr.JFR;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;


public class visGraph2 extends NodeProc{
    private static ArrayList<GraphNode> nodes;

    public void setNodes(ArrayList<GraphNode> newNodes){
        this.nodes = newNodes;
    }

    public visGraph2(){
    }
    public visGraph2(ArrayList<GraphNode> newNodes){
        this.nodes = newNodes;
    }
    public visGraph2(ArrayList<GraphNode> myNodes, JPanel panel, JFrame frame){
        panel.setBounds(800, 800, 200, 100);
        panel.setLayout(null);
        //ArrayList<GraphNode> nodes = this.myNodes;
        int j = 0;
        char layer = '0';
//        NodeProc tester = new NodeProc(38058, 2f, 3f);
//        NodeProc tester2 = new NodeProc(125f, 123f, 213f);
//        ArrayList<GraphNode> newNodes = new ArrayList();
//        newNodes.add(new GraphNode(tester));
//        newNodes.add(new GraphNode(tester2));
//        this.nodes = newNodes;
        //setLayout(new java.awt.GridLayout(4, 4));
        for (int i = 0; i < this.nodes.size(); i++) {
            j++;
            if(i < 1){
                layer = '0';
            }
            else if(i <= 10 && i >0){
                layer = '1';
            }
            else if(i < 32 && i > 10){
                layer = '2';
            }
            else if(i < 75 && i >= 32){
                layer = '3';
            }
            else if(i < 145 && i >= 75){
                layer = '4';
            }
            else{
                layer = '5';
            }

            JButton b = new JButton(String.valueOf(this.nodes.get(i).getProcess().getPid()));
            b.setText(String.valueOf((int)this.nodes.get(i).getProcess().getPid()));
            GraphNode graphNode = this.nodes.get(i);
            graphNode.setColor();
            graphNode.setSize();

            //System.out.println(graphNode.getProcess().getPid());
            Color btnColor = graphNode.getColor();
            b.setSize(graphNode.getsize(), graphNode.getsize());
            b.setMargin(new Insets(0, 0, 0, 0));
            b.setBackground(btnColor);

            b.setBorder(null);
            b.setOpaque(true);
            b.setBorderPainted(false);
           // System.out.println(j/32);

            b.setLocation((i%32)*25, (j/32)*100);
            switch (layer) {
                case '0':
                    b.setLocation(400 - (this.nodes.get(0).getsize()/4), 350 - (this.nodes.get(0).getsize()/4));
                    System.out.println("case 0");
                    break;
                case '1':
                    System.out.println("case 1");
//                    double theta = (360 / 8) * (i-1);
                    double theta = 36 * (i);
                    double xcord = Math.cos(theta);
                    double ycord = Math.sin(theta);
                    System.out.print(xcord + " " + ycord);
                    System.out.println(theta + " " + xcord + " " + ycord);
                    b.setLocation(400 - (int)(1.5 * xcord * this.nodes.get(1).getsize()), 350 - (int)(1.5 * ycord * this.nodes.get(1).getsize()));
                    break;
                case '2':
                    System.out.println("case 2");
                    theta = 11.25 * (i%32);
                    xcord = Math.cos(theta);
                    ycord = Math.sin(theta);
                    b.setLocation(400 - (int)(2.5 * xcord * this.nodes.get(1).getsize()), 350 - (int)(2.5 * ycord * this.nodes.get(1).getsize()));
                    break;
                case '3':
                    System.out.println("case 3");
                    theta = 4.8 * (i%75);
                    xcord = Math.cos(theta);
                    ycord = Math.sin(theta);
                    b.setLocation(400 - (int)(3.25 * xcord * this.nodes.get(1).getsize()), 350 - (int)(3.25 * ycord * this.nodes.get(1).getsize()));
                    break;
                case '4':
                    System.out.println("case 4");
                    theta = 2.45 * (i%145);
                    xcord = Math.cos(theta);
                    ycord = Math.sin(theta);
                    b.setLocation(400 - (int)(4.2 * xcord * this.nodes.get(1).getsize()), 350 - (int)(4.2 * ycord * this.nodes.get(1).getsize()));
                    break;
                case '5':
                    theta = i%360;
                    xcord = Math.cos(theta);
                    ycord = Math.sin(theta);
                    b.setLocation(400 - (int)(4.75 * xcord * this.nodes.get(1).getsize()), 350 - (int)(4.75 * ycord * this.nodes.get(1).getsize()));
                    break;
                default:
                    break;
            }

            final JPopupMenu menu = new JPopupMenu("controls");
            JMenuItem kill = new JMenuItem("Kill");
            JMenuItem quit = new JMenuItem("Quit");
            JMenuItem start = new JMenuItem("Start");

            menu.add(kill);
            menu.add(quit);
            menu.add(start);

            kill.addActionListener(new ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    try {
                        graphNode.signalProcess("kill");
                        b.setVisible(false);
                    } catch (IOException e1) {
                        e1.printStackTrace();

                    }
                }
            });
            quit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        graphNode.signalProcess("quit");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            });

            start.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        graphNode.signalProcess("start");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            });
            panel.add(b);
            NodeProc temp = this.nodes.get(i).getProcess();
            b.addActionListener(new ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    menu.show(b, b.getWidth()/2, b.getHeight()/2);
                    System.out.println(temp.getCpuUsage());
                }
            });

        }
        panel.setBackground(new Color(135, 135, 135));
        frame.setBackground(new Color(135, 135, 135));
        frame.add(panel);
    }

    public static void makeGraph(JPanel myPanel, JFrame myFrame) {
            myPanel.setVisible(true);
            myFrame.setTitle("Visual Process Manager");
            myFrame.setSize(900, 900);
            myFrame.add(new visGraph2(nodes, myPanel, myFrame));
            myFrame.add(myPanel);
            //myFrame.setLocationRelativeTo(null);
            myFrame.setVisible(true);
            myFrame.setDefaultCloseOperation(myFrame.EXIT_ON_CLOSE);

    }

    public static void main(String argsp[]) throws InterruptedException {
    }
}

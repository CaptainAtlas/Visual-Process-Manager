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
        NodeProc tester = new NodeProc(37642f, 2f, 3f);
        NodeProc tester2 = new NodeProc(125f, 123f, 213f);
        ArrayList<GraphNode> newNodes = new ArrayList();
        newNodes.add(new GraphNode(tester));
        newNodes.add(new GraphNode(tester2));
        this.nodes = newNodes;
        //setLayout(new java.awt.GridLayout(4, 4));
        for (int i = 0; i < this.nodes.size(); ++i) {
            j++;
            if(j > this.nodes.size()/10){
                j = 0;
            }
            JButton b = new JButton(String.valueOf(this.nodes.get(i).getProcess().getPid()));
            b.setText(String.valueOf(this.nodes.get(i).getProcess().getPid()));
            GraphNode graphNode = this.nodes.get(i);
            graphNode.setColor();
            graphNode.setSize();

            System.out.println(graphNode.getProcess().getPid());
            Color btnColor = graphNode.getColor();
            b.setSize(graphNode.getsize(), graphNode.getsize());

            b.setBackground(btnColor);
            b.setOpaque(true);
            b.setBorderPainted(false);
            b.setLocation(i*125, j*125);


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
            NodeProc temp = this.nodes.get(i);
            b.addActionListener(new ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    menu.show(b, b.getWidth()/2, b.getHeight()/2);
                    System.out.println(temp.getCpuUsage());
                }
            });

        }
        frame.add(panel);
    }

    public static void makeGraph() {
        JPanel myPanel = new JPanel();
        JFrame myFrame = new JFrame();
        myPanel.setVisible(true);

            myFrame.setTitle("test");
            myFrame.setSize(900, 900);
            myFrame.add(new visGraph2(nodes, myPanel, myFrame));
            myFrame.add(myPanel);
            //myFrame.setLocationRelativeTo(null);
            myFrame.setVisible(true);
            myFrame.setDefaultCloseOperation(myFrame.EXIT_ON_CLOSE);
    }

    public static void main(String argsp[]) throws InterruptedException {
        makeGraph();
    }
}

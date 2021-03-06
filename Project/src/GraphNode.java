/**
 * Created by DannyBrill on 11/29/16.
 */
import org.omg.SendingContext.RunTime;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.StringTokenizer;

public class GraphNode extends NodeProc implements Comparable<GraphNode>{
    private NodeProc proc;
    private Color color;
    private float locality;
    private float size;

    GraphNode(){
        this.color = new Color(0, 0, 0);
        this.locality = 0;
        this.size = 27;
    }
    GraphNode(NodeProc newProc){
        this.proc = newProc;
        setColor();
        setSize();
        this.color = getColor();
        this.size = getsize();
    }
    GraphNode(NodeProc newProc, Color newColor, float newLocality, float newSize){
        this.proc = newProc;
        this.color = newColor;
        this.locality = newLocality;
        this.size = newSize;
    }

    public NodeProc getProcess(){
        return this.proc;
    }

    public Color getColor(){
        return this.color;
    }

    public void setColor(){
        float cpu = proc.getCpuUsage();

        if(cpu >= 25){
            cpu = 25;
        }
        else if(cpu < 1){
            cpu = 1;
        }

        float red = cpu * 8;
        float blue = 255 / (cpu);

        this.color = new Color((int) red, (int) blue, 0);
        return;
    }
    public float getLocality(){
        return this.locality;
    }

    public void setLocality(float newLocality){
        this.locality = newLocality;
        return;
    }

    public int getsize(){
        return (int)this.size;
    }

    public void setSize(){
        float ram = proc.getRamUsage();
        if (ram > 7){
            ram = 4f;
        }
        if (ram < .5){
            ram = 1f;
        }
        ram = ram * 30;
        this.size = (int)ram;
        return;
    }

    public boolean signalProcess(String command) throws IOException {
        Runtime runTime;
        runTime = Runtime.getRuntime();

        if(command == "kill"){
            runTime.exec("kill -s kill " + String.valueOf((int)proc.getPid()));
        }
        else if(command == "quit"){
            runTime.exec("kill -s quit " + String.valueOf((int)proc.getPid()));
        }
        else if(command == "start"){
            runTime.exec("kill -SIGCONT " + String.valueOf((int)proc.getPid()));
        }
        else{
            throw new IllegalArgumentException("Invalid command");
        }
        return true;
    }

    @Override
    public int compareTo(GraphNode otherNode) {
        if((int)this.getProcess().getRamUsage() > (int)otherNode.getProcess().getRamUsage()){
            return -1;
        }
        else if((int)this.getProcess().getRamUsage() < (int)otherNode.getProcess().getRamUsage()){
            return 1;
        }
        return 0;
    }
}


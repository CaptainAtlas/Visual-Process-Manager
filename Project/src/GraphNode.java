/**
 * Created by DannyBrill on 11/29/16.
 */
import org.omg.SendingContext.RunTime;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.StringTokenizer;

public class GraphNode extends NodeProc{
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
        float red = 0;
        float blue = 0;
        if(cpu > 25){
            cpu = 25;
        }
        if(cpu < 1){
            cpu = 1;
        }
        if(cpu == 25){
            red = cpu*10;
        }

        else if(cpu < 25 && cpu >= 5){
            red = 8 * cpu;
            blue = 255/(cpu * 5);
        }
        else{
            red = cpu*10;
            blue = 255;
        }
        this.color = new Color((int)Math.round(red), 0, (int)Math.round(blue));
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
        int temp = (int)this.size;
        return temp;
    }

    public void setSize(){
        float ram = proc.getRamUsage();
        if (ram > 7){
            ram = 7f;
        }
        if (ram < 2){
            ram = 2f;
        }
        ram = ram * 40;
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

}


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

    public void setColor(Color newColor){
        this.color = newColor;
        return;
    }
    public float getLocality(){
        return this.locality;
    }

    public void setLocality(float newLocality){
        this.locality = newLocality;
        return;
    }

    public float getsize(){
        return this.size;
    }

    public void setSize(float newSize){
        this.size = newSize;
        return;
    }

    public boolean signalProcess(String command) throws IOException {
        Runtime runTime;
        runTime = Runtime.getRuntime();

        if(command == "kill"){
            runTime.exec("kill" + proc.getPid());
        }
        else if(command == "stop"){
            runTime.exec("stop" + proc.getPid());
        }
        else if(command == "start"){
            runTime.exec("start" + proc.getPid());
        }
        else{
            throw new IllegalArgumentException("Invalid command");
        }
        return true;
    }

}


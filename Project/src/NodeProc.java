/**
 * Created by DannyBrill on 11/29/16.
 */

import java.io.*;
import java.util.StringTokenizer;

public class NodeProc {
    private float cpuUsage;
    private float pid;

    NodeProc(){
        this.pid = 0;
        this.cpuUsage = 0;
    }
    public float getPid(){
        return this.pid;
    }
}

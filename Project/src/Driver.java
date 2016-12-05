import java.util.TimerTask;

public class Driver extends TimerTask {
    public void run(){
        GetProcessList processes = new GetProcessList();
        processes.updateProcesses();
    }
//    public static void main (String[]args){
//
//
//        NodeProc myNode = new NodeProc();
//        myNode.setPid(1);
//        System.out.println(myNode.getRamUsage());
//
//    }
}
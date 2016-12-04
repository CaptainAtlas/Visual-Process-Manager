public class Driver{
    public static void main (String[]args){
        GetProcessList processes = new GetProcessList();
        processes.printProcesses();
        NodeProc myNode = new NodeProc();
        myNode.setPid(3269);
        System.out.println(myNode.getCpuUsage());
    }
}
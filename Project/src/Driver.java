public class Driver{
    public static void main (String[]args){

        GetProcessList processes = new GetProcessList();
        processes.updateProcesses();
        NodeProc myNode = new NodeProc();
        myNode.setPid(1);
        System.out.println(myNode.getRamUsage());

    }
}
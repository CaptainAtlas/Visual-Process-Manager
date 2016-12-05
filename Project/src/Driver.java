import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.TimerTask;

public class Driver extends TimerTask {
    ArrayList<NodeProc> nodeProcs = new ArrayList();
    ArrayList<GraphNode> graphNodes = new ArrayList();

    public void run(){
        GetProcessList processes = new GetProcessList();
        processes.updateProcesses();
        DBConnection DBconn = new DBConnection();
        try {
            Connection conn = DBconn.openConnection();
            String query = "SELECT pid FROM Pdata";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                nodeProcs.add(new NodeProc(rs.getFloat("pid")));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < nodeProcs.size(); i++){
//            System.out.println(nodeProcs.get(i).getPid());
            graphNodes.add(new GraphNode(nodeProcs.get(i)));
        }
        visGraph2 myGraph = new visGraph2(graphNodes);
        myGraph.setNodes(graphNodes);
        myGraph.makeGraph();
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

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.xml.soap.Node;


//public class NodeProc

class VisualGraph extends NodeProc {

   // NodeProc myNode = new NodeProc();

    public VisualGraph() {

    }



    public static void main(String[] args) {
        ArrayList<Integer> tester = new ArrayList();
        tester.add(27);
        tester.add(3);
        tester.add(1);
        for(int i = 0; i < tester.size(); i++){
            System.out.println(tester.get(i));
        }

    }
}



/*import javax.swing.*;

public class VisualGraph {
    private Arraylist<GraphNode> nodes;
    private Boolean addNode() {
        return true;
    }
    public void setNodeList(Arraylist<GraphNode> myNodes){
        this.nodes = myNodes;
        return;
    }
    private Boolean removeNode() {
        return true;

    }
}*/

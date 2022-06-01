package mainpack;

import java.util.ArrayList;

public class RootBasicNode extends BasicNode {
    ArrayList<BasicNode> linesR =new ArrayList<>();
    public void addNode(BasicNode node){
        linesR.add(node);
    }
}

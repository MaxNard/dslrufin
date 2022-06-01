package mainpack;

import java.util.ArrayList;

public class ForBasicNode extends BasicNode {
    Token opFor;
    BasicNode lV;
    BasicNode rV;
    BasicNode work;
    public ArrayList<BasicNode> linesFor = new ArrayList<>();

    public ForBasicNode(Token opFor, BasicNode lV, BasicNode rV, BasicNode work) {
        this.opFor = opFor;
        this.lV = lV;
        this.rV = rV;
        this.work =work;
    }
    public void addOperations(BasicNode op){
        linesFor.add(op);
    }
}

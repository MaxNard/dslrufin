package mainpack;

import java.util.ArrayList;

public class IfBasicNode extends BasicNode {
    Token opIf;
    BasicNode lV;
    BasicNode rV;
    public ArrayList<BasicNode> ifLines = new ArrayList<>();
    public ArrayList<BasicNode> elseLines = new ArrayList<>();
    public IfBasicNode(Token opIf, BasicNode lV, BasicNode rV) {
        this.opIf = opIf;
        this.lV = lV;
        this.rV = rV;
    }
    public void addThenOperations(BasicNode op){
        ifLines.add(op);
    }
    public void addElseOperations(BasicNode op){
        elseLines.add(op);
    }
}

package mainpack;

import java.util.ArrayList;

public class WhileBasicNode extends BasicNode {
    Token op;
    BasicNode lV;
    BasicNode rV;
    public ArrayList<BasicNode> whLines = new ArrayList<>();

    public WhileBasicNode(Token op, BasicNode lV, BasicNode rV) {
        this.op = op;
        this.lV = lV;
        this.rV = rV;
    }
    public void addOperations(BasicNode op){
        whLines.add(op);
    }
}

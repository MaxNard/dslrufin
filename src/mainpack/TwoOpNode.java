package mainpack;

public class TwoOpNode extends BasicNode {
    Token op;
    BasicNode lV;
    BasicNode rV;

    public TwoOpNode(Token op, BasicNode lV, BasicNode rV) {
        super();
        this.op = op;
        this.lV = lV;
        this.rV = rV;
    }
}

package mainpack;

public class OneOpNode extends BasicNode {
    Token operator;
    BasicNode value;

    public OneOpNode(Token operator, BasicNode value) {
        this.operator = operator;
        this.value = value;
    }
}

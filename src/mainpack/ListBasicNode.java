package mainpack;

public class ListBasicNode extends BasicNode {
    Token type;
    BasicNode var;

    public ListBasicNode(Token type, BasicNode var) {
        this.type = type;
        this.var = var;
    }
}

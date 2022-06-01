package mainpack;

import java.util.ArrayList;

public class Parser {
    ArrayList<Token> tokens;
    int pos=0;

    public Parser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public Token matchThis(String[] need){
        Token curToken;
        if (pos<tokens.size()) {
            curToken = tokens.get(pos);
            for (String tokenTypeName : need)
                if (tokenTypeName.equals(curToken.type.typeName)) {
                    pos++;
                    return curToken;
                }
        }
        return null;
    }

    public void require(String[] reqTypes){
        Token token = matchThis(reqTypes);
        if(token==null){
            throw new Error("На позииции "+pos+" ожидается "+reqTypes[0]);
        }
    }

    public RootBasicNode analyzeTokenList(){
        RootBasicNode root=new RootBasicNode();
        while (pos<tokens.size()){
            BasicNode codeStringNode= analyzeLine();
            require(new String[]{"КОНЕЦ"});
            root.addNode(codeStringNode);
        }
        return root;
    }

    public BasicNode parseVarNum(){
        if (tokens.get(pos).type.typeName.equals("ЧИСЛО")){
            return new NumberBasicNode(tokens.get(pos++));
        }
        if (tokens.get(pos).type.typeName.equals("ПЕРЕМЕННАЯ")){
            return new VarBasicNode(tokens.get(pos++));
        }
         throw new Error("Ожидается переменная или число на позиции: "+pos);
    }

    public BasicNode analyzePar(){
        if (tokens.get(pos).type.typeName.equals("Л_СКОБКА")){
            pos++;
            BasicNode node = analyzeStatement();
            require(new String[]{"П_СКОБКА"});
            return node;
        }
        else
            return parseVarNum();
    }

    public BasicNode analyzeUmDel(){
        BasicNode leftVal = analyzePar();
        Token operator = matchThis(new String[]{"УМНОЖИТЬ","РАЗДЕЛИТЬ"});
        while (operator!=null){
            BasicNode rightVal = analyzePar();
            leftVal = new TwoOpNode(operator,leftVal,rightVal);
            operator = matchThis(new String[]{"УМНОЖИТЬ","РАЗДЕЛИТЬ"});
        }
        return leftVal;
    }

    public BasicNode analyzeStatement(){
        BasicNode leftVal = analyzeUmDel();
        Token operator = matchThis(new String[]{"ПЛЮС","МИНУС"});
        while (operator!=null){
            BasicNode rightVal= analyzeUmDel();
            leftVal=new TwoOpNode(operator,leftVal,rightVal);
            operator= matchThis(new String[]{"ПЛЮС","МИНУС"});
        }
        return leftVal;
    }

    public BasicNode analyzeLine(){
        switch (tokens.get(pos).type.typeName) {
            case "ПЕРЕМЕННАЯ":
                BasicNode varNode = parseVarNum();
                Token operator = matchThis(new String[]{"РАВНО", "ДОБАВИТЬ", "УДАЛИТЬ", "ПОЛУЧИТЬ"});
                if (operator != null) {
                    BasicNode rightVal = analyzeStatement();
                    return new TwoOpNode(operator, varNode, rightVal);
                }
                throw new Error("После переменной ожидается бинарный оператор на позиции:" + pos);
            case "ВЫВОД":
                pos++;
                return new OneOpNode(tokens.get(pos - 1), this.analyzeStatement());
            case "ЕСЛИ":
                pos++;
                return analyzeIf();
            case "ПОКА":
                pos++;
                return analyzeWhile();
            case "ДЛЯ":
                pos++;
                return analyzeFor();
            case "СПИСОК":
                return analyzeList();
        }
       throw new Error("Ошибка на позиции: "+pos+". Ожидалось действие или переменная");
   }

    public BasicNode getLines(){//Для if, else, while, for
        BasicNode codeStringNode= analyzeLine();
        require(new String[]{"КОНЕЦ"});
        return codeStringNode;
    }

    public BasicNode analyzeIf(){
        BasicNode leftVal= analyzeStatement();
        Token operator= matchThis(new String[]{"МЕНЬШЕ","БОЛЬШЕ","РАВЕНСТВО"});
        BasicNode rightVal= analyzeStatement();
        IfBasicNode ifNode=new IfBasicNode(operator,leftVal,rightVal);
        require(new String[]{"ЛФ_СКОБКА"});
        while(!tokens.get(pos).type.typeName.equals("ПФ_СКОБКА")) {
            ifNode.addThenOperations(getLines());
            if (pos==tokens.size())
                throw new Error("Ошибка, ожидалось }");
        }
        pos++;
        require(new String[]{"ИНАЧЕ"});
        require(new String[]{"ЛФ_СКОБКА"});
        while(!tokens.get(pos).type.typeName.equals("ПФ_СКОБКА")) {
            ifNode.addElseOperations(getLines());
            if (pos==tokens.size())
                throw new Error("Ошибка, ожидалось }");
       }
        pos++;
        return ifNode;
   }
    public BasicNode analyzeFor(){
        BasicNode leftVal= analyzeStatement();
        Token operator= matchThis(new String[]{"МЕНЬШЕ","БОЛЬШЕ","РАВЕНСТВО"});
        BasicNode rightVal= analyzeStatement();

        require(new String[]{"КОНЕЦ"});

        BasicNode varNode = parseVarNum();
        Token assign = matchThis(new String[]{"РАВНО"});
        BasicNode rightActVal = analyzeStatement();
        TwoOpNode action = new TwoOpNode(assign, varNode, rightActVal);
        if (assign == null)
            throw new Error("После переменной ожидается = на позиции:"+pos);
        ForBasicNode forNode= new ForBasicNode(operator,leftVal,rightVal,action);
        require(new String[]{"ЛФ_СКОБКА"});
        while(!tokens.get(pos).type.typeName.equals("ПФ_СКОБКА")) {
            forNode.addOperations(getLines());
            if (pos==tokens.size())
                throw new Error("Ошибка, ожидалось }");
        }
        pos++;
        return forNode;
   }
    public BasicNode analyzeWhile(){
        BasicNode leftVal= analyzeStatement();
        Token operator= matchThis(new String[]{"МЕНЬШЕ","БОЛЬШЕ","РАВЕНСТВО"});
        BasicNode rightVal= analyzeStatement();
        WhileBasicNode whileNode=new WhileBasicNode(operator,leftVal,rightVal);
        require(new String[]{"ЛФ_СКОБКА"});
        while(!tokens.get(pos).type.typeName.equals("ПФ_СКОБКА")) {
            whileNode.addOperations(getLines());
            if (pos==tokens.size())
                throw new Error("Ошибка, ожидалось }");
        }
        pos++;
        return whileNode;
    }
    public BasicNode analyzeList(){
        Token type= matchThis(new String[]{"СПИСОК"});
        BasicNode var=parseVarNum();
        return new ListBasicNode(type,var);
    }
}

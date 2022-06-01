package mainpack;

import java.util.HashMap;

public class Interpreter {

    public static HashMap<String, List> someLists =new HashMap<>();
    public static HashMap<String, String> someVars =new HashMap<>();

    public String run(BasicNode node){

        if (node.getClass()== VarBasicNode.class) {
            return someVars.get(((VarBasicNode) node).tokVar.value);
        }
        if (node.getClass()== NumberBasicNode.class) {
            return ((NumberBasicNode) node).number.value;
        }
        if (node.getClass()== OneOpNode.class) {
            if (((OneOpNode) node).operator.type.typeName.equals("ВЫВОД")) {
                if (((OneOpNode) node).value.getClass() == VarBasicNode.class) {
                    VarBasicNode a = (VarBasicNode) ((OneOpNode) node).value;
                        System.out.println(this.run(((OneOpNode) node).value));
                }
                else System.out.println(this.run(((OneOpNode) node).value));
            }
        }
        if (node.getClass()== TwoOpNode.class) {
            if (((TwoOpNode) node).op.type.typeName.equals("РАВНО")) {
                String res = this.run(((TwoOpNode) node).rV);
                VarBasicNode varNode = (VarBasicNode)((TwoOpNode) node).lV;
                someVars.put(varNode.tokVar.value, res);
                return res;
            }
            else if (((TwoOpNode) node).op.type.typeName.equals("ПОЛУЧИТЬ")||((TwoOpNode) node).op.type.typeName.equals("ДОБАВИТЬ")||
                    ((TwoOpNode) node).op.type.typeName.equals("УДАЛИТЬ")){
                VarBasicNode varNode = (VarBasicNode)((TwoOpNode) node).lV;
                if (someLists.containsKey(varNode.tokVar.value)){
                    int rightValue = Integer.parseInt(run(((TwoOpNode) node).rV));
                    switch (((TwoOpNode) node).op.type.typeName) {
                        case "ДОБАВИТЬ":
                            someLists.get(varNode.tokVar.value).add(rightValue);
                            return "";
                        case "ПОЛУЧИТЬ":
                            System.out.println(someLists.get(varNode.tokVar.value).get(rightValue));
                            return "";
                        case "УДАЛИТЬ":
                            someLists.get(varNode.tokVar.value).delete(rightValue);
                            return "";
                    }
                }
            }
            else
            {
                int left = Integer.parseInt(this.run(((TwoOpNode) node).lV));
                int right = Integer.parseInt(this.run(((TwoOpNode) node).rV));
                return switch (((TwoOpNode) node).op.type.typeName) {
                    case "ПЛЮС" -> String.valueOf(left + right);
                    case "МИНУС" -> String.valueOf(left - right);
                    case "УМНОЖИТЬ" -> String.valueOf(left * right);
                    case "РАЗДЕЛИТЬ" -> String.valueOf(left / right);
                    default -> throw new Error("Недопустимая операция");
                };
            }
        }

        if (node.getClass() == IfBasicNode.class){
            int left = Integer.parseInt(this.run(((IfBasicNode) node).lV));
            int right = Integer.parseInt(this.run(((IfBasicNode) node).rV));
            switch (((IfBasicNode) node).opIf.type.typeName) {
                case "МЕНЬШЕ":
                    if (left < right) {
                        for (int i = 0; i < ((IfBasicNode) node).ifLines.size(); i++)
                            this.run(((IfBasicNode) node).ifLines.get(i));
                    }
                    else{
                        for (int i = 0; i < ((IfBasicNode) node).elseLines.size(); i++)
                            this.run(((IfBasicNode) node).elseLines.get(i));
                    }
                    break;
                case "БОЛЬШЕ":
                    if (left > right) {
                        for (int i = 0; i < ((IfBasicNode) node).ifLines.size(); i++)
                            this.run(((IfBasicNode) node).ifLines.get(i));
                    }
                    else{
                        for (int i = 0; i < ((IfBasicNode) node).elseLines.size(); i++)
                            this.run(((IfBasicNode) node).elseLines.get(i));
                    }
                    break;
                case "РАВЕНСТВО":
                    if (left == right) {
                        for (int i = 0; i < ((IfBasicNode) node).ifLines.size(); i++)
                            this.run(((IfBasicNode) node).ifLines.get(i));
                    }
                    else{
                        for (int i = 0; i < ((IfBasicNode) node).elseLines.size(); i++)
                            this.run(((IfBasicNode) node).elseLines.get(i));
                    }
                    break;
            }
        }
        if (node.getClass()== WhileBasicNode.class){
            int left=Integer.parseInt(this.run(((WhileBasicNode) node).lV));
            int right=Integer.parseInt(this.run(((WhileBasicNode) node).rV));
            switch (((WhileBasicNode) node).op.type.typeName) {
                case "МЕНЬШЕ":
                    while (left < right) {
                        for (int i = 0; i < ((WhileBasicNode) node).whLines.size(); i++)
                            this.run(((WhileBasicNode) node).whLines.get(i));
                        left = Integer.parseInt(this.run(((WhileBasicNode) node).lV));
                        right = Integer.parseInt(this.run(((WhileBasicNode) node).rV));
                    }
                    break;
                case "БОЛЬШЕ":
                    while (left > right) {
                        for (int i = 0; i < ((WhileBasicNode) node).whLines.size(); i++)
                            this.run(((WhileBasicNode) node).whLines.get(i));
                        left = Integer.parseInt(this.run(((WhileBasicNode) node).lV));
                        right = Integer.parseInt(this.run(((WhileBasicNode) node).rV));
                    }
                    break;
                case "РАВЕНСТВО":
                    while (left == right) {
                        for (int i = 0; i < ((WhileBasicNode) node).whLines.size(); i++)
                            this.run(((WhileBasicNode) node).whLines.get(i));
                        left = Integer.parseInt(this.run(((WhileBasicNode) node).lV));
                        right = Integer.parseInt(this.run(((WhileBasicNode) node).rV));
                    }
                    break;
            }
        }
        if (node.getClass()== ForBasicNode.class){
            int left=Integer.parseInt(this.run(((ForBasicNode) node).lV));
            int right=Integer.parseInt(this.run(((ForBasicNode) node).rV));
            switch (((ForBasicNode) node).opFor.type.typeName) {
                case "МЕНЬШЕ":
                    while (left < right) {
                        for (int i = 0; i < ((ForBasicNode) node).linesFor.size(); i++)
                            this.run(((ForBasicNode) node).linesFor.get(i));
                        this.run(((ForBasicNode) node).work);
                        left = Integer.parseInt(this.run(((ForBasicNode) node).lV));
                        right = Integer.parseInt(this.run(((ForBasicNode) node).rV));
                    }
                    break;
                case "БОЛЬШЕ":
                    while (left > right) {
                        for (int i = 0; i < ((ForBasicNode) node).linesFor.size(); i++)
                            this.run(((ForBasicNode) node).linesFor.get(i));
                        this.run(((ForBasicNode) node).work);
                        left = Integer.parseInt(this.run(((ForBasicNode) node).lV));
                        right = Integer.parseInt(this.run(((ForBasicNode) node).rV));
                    }
                    break;
                case "РАВЕНСТВО":
                    while (left == right) {
                        for (int i = 0; i < ((ForBasicNode) node).linesFor.size(); i++)
                            this.run(((ForBasicNode) node).linesFor.get(i));
                        this.run(((ForBasicNode) node).work);
                        left = Integer.parseInt(this.run(((ForBasicNode) node).lV));
                        right = Integer.parseInt(this.run(((ForBasicNode) node).rV));
                    }
                    break;
            }
        }
        if (node.getClass()== ListBasicNode.class){
            VarBasicNode varNode=(VarBasicNode)((ListBasicNode)node).var;
            switch(((ListBasicNode) node).type.type.typeName){
                case "СПИСОК":
                    someLists.put(varNode.tokVar.value,new List());
                    return "";
            }
        }
        return "";
    }
}

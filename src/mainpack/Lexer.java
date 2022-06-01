package mainpack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

import static mainpack.TokenType.tokenTypeList;


public class Lexer
{
    String exmp;
    int pos=0;

    ArrayList<Token> tokens = new ArrayList<Token>();
    public Lexer(String exmp)
    {
        this.exmp = exmp;
    }

    public boolean nextToken()
    {
        exmp = exmp.replaceAll("\\s+", "");
        if (pos >= exmp.length())
        {
            return false;
        }
        for (int i = 0; i < tokenTypeList.length; i++)
        {
            TokenType name = tokenTypeList[i];
            Pattern type = tokenTypeList[i].pattern;
            for (int j = exmp.length(); j > pos; j-- )
            {
                Matcher result = type.matcher(this.exmp.substring(this.pos, j));
                if(result.matches())
                {
                    tokens.add(new Token(name, result.group()));
                    this.pos+=result.group().length();
                    return true;
                }
            }
        }
        throw new Error("На позиции "+pos+" неизвестный символ!");
    }

    public ArrayList<Token> lexToken()
    {
        while (this.nextToken()) {}
        System.out.println("Лексер разбил на токены успешно!");
        return tokens;
    }
}

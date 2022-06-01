package mainpack;

import java.util.regex.Pattern;

public class TokenType {
    String typeName;
    Pattern pattern;
    public static TokenType[] tokenTypeList={
            new TokenType("ИНАЧЕ",Pattern.compile("^(ИНАЧЕ)$")),
            new TokenType("РАВЕНСТВО",Pattern.compile("^==$")),
            new TokenType("МЕНЬШЕ",Pattern.compile("\\<$")),
            new TokenType("БОЛЬШЕ",Pattern.compile("^\\>$")),
            new TokenType("КОНЕЦ",Pattern.compile("^\\;$")),
            new TokenType("ЕСЛИ",Pattern.compile("^(ЕСЛИ)$")),
            new TokenType("ПОКА",Pattern.compile("^(ПОКА)$")),
            new TokenType("ДЛЯ",Pattern.compile("^(ДЛЯ)$")),
            new TokenType("Л_СКОБКА",Pattern.compile("^\\($")),
            new TokenType("П_СКОБКА",Pattern.compile("^\\)$")),
            new TokenType("ПЕРЕМЕННАЯ",Pattern.compile("^[а-я][а-я0-9]{0,}$")),
            new TokenType("ЧИСЛО",Pattern.compile("^0|[1-9][0-9]*$")),
            new TokenType("ПЛЮС",Pattern.compile("^[+]$")),
            new TokenType("МИНУС",Pattern.compile("^[-]$")),
            new TokenType("УМНОЖИТЬ",Pattern.compile("^[*]$")),
            new TokenType("РАЗДЕЛИТЬ",Pattern.compile("^[/]$")),
            new TokenType("РАВНО",Pattern.compile("^=$")),
            new TokenType("ЛФ_СКОБКА",Pattern.compile("^\\{$")),
            new TokenType("ПФ_СКОБКА",Pattern.compile("^\\}$")),
            new TokenType("ВЫВОД",Pattern.compile("^(ВЫВОД)$")),
            new TokenType("СПИСОК",Pattern.compile("^(СПИСОК)$")),
            new TokenType("ДОБАВИТЬ",Pattern.compile("^(ДОБАВИТЬ)$")),
            new TokenType("УДАЛИТЬ",Pattern.compile("^(УДАЛИТЬ)$")),
            new TokenType("ПОЛУЧИТЬ",Pattern.compile("^(ПОЛУЧИТЬ)$"))
    };
    public TokenType(String typeName, Pattern pattern) {
        this.typeName = typeName;
        this.pattern = pattern;
    }
}

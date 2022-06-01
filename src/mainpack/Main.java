package mainpack;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


class Main
{
	public static void main(String[] args) {
		String exmp = "";

		try(FileReader reader = new FileReader("Code.txt"))
		{
			int c;
			while((c = reader.read())!=-1)
			{
				exmp = exmp.concat(String.valueOf((char)c));
			}
		}
		catch(IOException ex)
		{
			System.out.println(ex.getMessage());
		}
		Lexer lex = new Lexer(exmp);

		ArrayList<Token> toktok = lex.lexToken();

		/*for (Token p : toktok)
		{
			System.out.println("ТОКЕН: "+p.value);
		}*/

		Parser parser = new Parser(toktok);
		RootBasicNode root = parser.analyzeTokenList();
		System.out.println("Интерпретатор:");
		Interpreter interpreter = new Interpreter();
		for(int i = 0; i<root.linesR.size(); i++) {
			interpreter.run(root.linesR.get(i));
		}
	}
}

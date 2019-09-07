import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {

	public static void main(String[] args) {
		// declarations
		Scanner sc = null;
		File textFile = new File(args[0]);
		Lexer lexer = new Lexer(textFile);
		textFile = new File("tokens.txt");
		String curr_token = null;
		String next_token = null;
		
		try {
			sc = new Scanner(textFile);
			curr_token = sc.next();
			next_token = curr_token;
			decList(curr_token, next_token);			
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found!");
		}
		
		sc.close();

	}
	
	private static void decList(String curr_token, String next_token) {
		declaration(curr_token, next_token);
		decListP(curr_token, next_token);
	}
	
	private static void decListP(String curr_token, String next_token) {
		//if need more dec do this
		declaration(curr_token, next_token);
		decListP(curr_token, next_token);
		// else do nothing
	}

	private static void declaration(String curr_token, String next_token) {
		typeSpecifier(curr_token, next_token);
		//accept ID
		declarationP(curr_token, next_token);
		
	}

	private static void declarationP(String curr_token, String next_token) {
		
<<<<<<< HEAD
		if(next_token == ";" || next_token == "[" ) {
			varDecP(curr_token, next_token);
		}
		
		// OR if (
		funDecP(curr_token, next_token);
		// else Reject
	}

	private static void funDecP(String curr_token, String next_token) {
		// TODO Auto-generated method stub
		
	}

	private static void varDecP(String curr_token, String next_token) {
		// TODO Auto-generated method stub
		
	}

	private static void typeSpecifier(String curr_token, String next_token) {
		// TODO Auto-generated method stub
		
	}
=======
		if(next_token.equals(";") || next_token.equals("[")) {
			varDecP(curr_token, next_token);
		}
		
		// OR if (
		funDecP(curr_token, next_token);
		// else Reject
	}

	private static void funDecP(String curr_token, String next_token) {
		// TODO Auto-generated method stub
		
	}

	private static void varDecP(String curr_token, String next_token) {
		// TODO Auto-generated method stub
		
	}

	private static void typeSpecifier(String curr_token, String next_token) {
		// TODO Auto-generated method stub
		
	}
	
	//Just a test for github and laptop number a million
	
}

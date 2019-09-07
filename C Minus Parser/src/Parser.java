import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {

	//Global Variables
	static Scanner sc = null;
	static String curr_token = null;
 
		
	public static void main(String[] args) {
		// declarations
		
		File textFile = new File(args[0]);
		Lexer lexer = new Lexer(textFile);
		textFile = new File("tokens.txt");
//		String next_token = null;
		
		try {
			sc = new Scanner(textFile);
			curr_token = sc.nextLine();
//			next_token = curr_token;
			decList();			
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found!");
		}
		
		sc.close();

	}
	
	private static void decList() {
		declaration();
		decListP();
	}
	
	private static void decListP() {
		//if need more dec do this
		declaration();
		//decListP();
		// else do nothing
	}

	private static void declaration() {
		typeSpecifier();
		if(curr_token.contains("ID: ")) {
			System.out.println("A: " + curr_token);
			curr_token = sc.nextLine();
		} else {
			rej();
		}
		declarationP();
		
	}

	private static void declarationP() {
		if (curr_token.equals(";") || curr_token.equals("[")) {
			curr_token = sc.nextLine();			
			varDecP();
		} else if (curr_token.equals("(")) {
			curr_token = sc.nextLine();
			funDecP();
		} else {
			rej();
		}
	}

	private static void funDecP() {
		params();
		if(curr_token.equals(")")) {
			curr_token = sc.nextLine();
		}
	}

	private static void params() {
		// TODO Auto-generated method stub
		
	}

	private static void varDecP() {
		// TODO Auto-generated method stub
		
	}

	private static void typeSpecifier() {
		if(curr_token.equals("K: int")){		
			System.out.println("A: int");
			curr_token = sc.nextLine();
		} else if(curr_token.equals("K: void")){		
			System.out.println("A: void");
			curr_token = sc.nextLine();
			System.out.println(curr_token);
		} else if(curr_token.equals("K: float")){		
			System.out.println("A: float");
			curr_token = sc.nextLine();
		} else {
			rej();
		}
	}	
	
	private static void rej() {
		System.out.println("Reject");
		System.exit(0);
	}
}

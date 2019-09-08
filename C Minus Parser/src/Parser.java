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
		checkID();
		declarationP();
		
	}

	private static void checkID() {
		if(curr_token.contains("ID: ")) {
			System.out.println("A: " + curr_token);
			curr_token = sc.nextLine();
		} else {
			rej();
		}		
	}

	private static void declarationP() {
		if (curr_token.equals(";") || curr_token.equals("[")) {
			curr_token = sc.nextLine();			
			varDecP();
		} else if (curr_token.equals("(")) {
			System.out.println("A: (" );
			curr_token = sc.nextLine();
			funDecP();
		} else {
			rej();
		}
	}

	private static void funDecP() {
		params();
		if(curr_token.equals(")")) {
			System.out.println("A: )");
			curr_token = sc.nextLine();
			compoundStmt();
		} else {
			rej();
		}
	}

	private static void compoundStmt() {
		if(curr_token.equals("{")) {
			curr_token = sc.nextLine();
			localDec();
			stmtList();
		}
	}

	private static void stmtList() {
		statement();
		stmtListP();		
	}

	private static void stmtListP() {
		if (!curr_token.equals("}")) {
			statement();
			stmtListP();
		}
	}

	private static void statement() {
		if(curr_token.equals("{")) {
			compoundStmt();
		} else if (curr_token.contains("K: if")) {
			curr_token = sc.nextLine();
			selectionStmt();
		} else if (curr_token.equals("K: while")) {
			curr_token = sc.nextLine();
			iterationStmt();
		} else if (curr_token.equals("K: return")) {
			curr_token = sc.nextLine();
			returnStmt();
		} else {
			expressionStmt();
		}
	}

	private static void expressionStmt() {
		if (curr_token.equals(";")) {
			curr_token = sc.nextLine();			
		} else {
			expression();
		}
	}

	private static void expression() {
		String tempToken = curr_token;
		curr_token = sc.nextLine();
		System.out.println("IN Expression: " + curr_token);
		if(curr_token.equals("=") || curr_token.equals("[")) {
			var(tempToken);
			System.out.println("A: =");
			
			expression();
		} else {
			simpleExpression();
		}
	}

	private static void simpleExpression() {
		addExpression();
		if(curr_token.equals("<=") || curr_token.equals("<") || curr_token.equals(">") || curr_token.equals(">=") || curr_token.equals("==") || curr_token.equals("!=")) {
			relop();
			addExpression();
		}
	}

	private static void relop() {
		// TODO Auto-generated method stub
		
	}

	private static void addExpression() {
		term();
		addExpressionP();
	}

	private static void addExpressionP() {
		addop();
		term();
	//	addExpressionP();
	}

	private static void termP() {
		addop();
		factor();
		//termP();
	}

	private static void addop() {
		// TODO Auto-generated method stub
		
	}

	private static void factor() {
		if (curr_token.equals("(")) {
			System.out.println("A: (");
			expression();
		} else if (curr_token.contains("ID: ")) {
			checkID();
			factorP();
		} else if (curr_token.contains("INT: ")) {
			System.out.println("A: " + curr_token);
		}
	}

	private static void factorP() {
		// TODO Auto-generated method stub
		
	}

	private static void term() {
		factor();
		termP();
	}

	private static void var(String token) {
		//System.out.println("Here++++++");
		System.out.println("A: " + token);
		if(curr_token.equals("[")) {
			System.out.println("A: [");
			expression();
			if(curr_token.equals("]")) {
				curr_token = sc.nextLine();
				System.out.println("A: ]");
			}
		}
	}

	private static void returnStmt() {
		// TODO Auto-generated method stub
		
	}

	private static void iterationStmt() {
		// TODO Auto-generated method stub
		
	}

	private static void selectionStmt() {
		// TODO Auto-generated method stub
		
	}

	private static void localDec() {
				if (!curr_token.equals("}")) {
					varDec();
					localDecP();
				} else if (curr_token.equals("}")) {
					System.out.println("A: }");
				} else {
					rej();
				}
	}

	private static void localDecP() {
		if (curr_token.contains("K: ")) {
			varDec();
			localDecP();
		} 
	}

	private static void varDec() {
		typeSpecifier();
		checkID();
		if(curr_token.equals(";")) {
			curr_token = sc.nextLine();
			System.out.println("A: ;");
		} else if (curr_token.equals("[")) {
			curr_token = sc.nextLine();
			System.out.println("A: [");
			checkNUM();
			if(!curr_token.equals("]")) {
				rej();
			}
			System.out.println("A: ]");
			curr_token = sc.nextLine();
			if(!curr_token.equals(";")) {
				rej();
			}
			System.out.println("A: ;");
			curr_token = sc.nextLine();
		} else {
			rej();
		}
		
	}

	private static void checkNUM() {
		if (curr_token.contains("INT: ")) {
			System.out.println("A: " + curr_token);
		} else {
			rej();
		}
	}

	private static void params() {
		if(curr_token.equals("K: void")) {
			System.out.println("A: void");
			curr_token = sc.nextLine();
		} else {
			paramsList();
		}
	}

	private static void paramsList() {
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

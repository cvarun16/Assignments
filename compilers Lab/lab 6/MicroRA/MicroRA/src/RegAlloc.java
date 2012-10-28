import java.io.FileInputStream;
import java.io.FileNotFoundException;

import syntaxtree.*;
import visitor.*;

class Main {
	public static void main(String[] args) throws FileNotFoundException {
		try {
			Node root = new microIRParser(new FileInputStream(
					"../Tests/microIR/Factorial.microIR")).Goal();

			// Node root = new microIRParser(System.in).Goal();
			System.out.println("Program parsed successfully");
			root.accept(new GJNoArguDepthFirst()); // Your assignment part is
													// invoked here.
		} catch (ParseException e) {
			System.out.println(e.toString());
		}
	}
}

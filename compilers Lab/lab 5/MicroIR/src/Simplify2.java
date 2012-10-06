import java.io.FileInputStream;
import java.io.FileNotFoundException;

import syntaxtree.*;
import visitor.*;

public class Simplify2 {
	public static void main(String[] args) throws FileNotFoundException {
		try {
			Node root = new MiniIRParser(System.in).Goal();
			/*
			 * Node root = new MiniIRParser(new FileInputStream(
			 * "../TestCases/miniIR/TreeVisitor.miniIR")).Goal();
			 */

			root.accept(new GJNoArguDepthFirst()); // Your assignment part is
													// invoked here.
		} catch (ParseException e) {
			System.out.println(e.toString());
		}
	}
}

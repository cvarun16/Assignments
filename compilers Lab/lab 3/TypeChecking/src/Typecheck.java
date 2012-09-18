import java.io.FileInputStream;
import java.io.FileNotFoundException;

import syntaxtree.*;
import visitor.*;

public class Typecheck {
	public static void main(String[] args) throws FileNotFoundException {
		try {
			Node root = new MiniJavaParser(new FileInputStream(
					"BinarySearchMini.java")).Goal();

			System.out.println("Program parsed successfully");
			root.accept(new GJNoArguDepthFirst()); // Your assignment part is
			// invoked here.
		} catch (ParseException e) {
			System.out.println(e.toString());
		}
	}
}

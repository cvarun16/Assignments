import java.io.FileInputStream;
import java.io.InputStream;

import syntaxtree.*;
import visitor.*;

public class Codegen {
	public static void main(String[] args) {
		try {
			// use the following line only in IDE
			/*
			 * Node root = new MiniRAParser(new FileInputStream(
			 * "../../Tests/miniRA/BinaryTree.miniRA")).Goal();
			 */

			// Final submission file should take input from command line
			Node root = new MiniRAParser(System.in).Goal();

			root.accept(new GJNoArguDepthFirst()); // Your assignment part is
													// invoked here.
			System.out.println(".text ");
			System.out.println(".globl _halloc");
			System.out.println("	_halloc:");
			System.out.println("li $v0, 9");
			System.out.println("syscall");
			System.out.println("j $ra");
			System.out.println(".text");
			System.out.println(".globl _print");
			System.out.println("_print:");
			System.out.println("li $v0, 1");
			System.out.println("syscall");
			System.out.println("la $a0, newl");
			System.out.println("li $v0, 4");
			System.out.println("syscall");
			System.out.println("j $ra");
			System.out.println(".data");
			System.out.println(".align   0");
			System.out.println("newl:    .asciiz \"\\n\"");
			System.out.println(".data");
			System.out.println(".align   0");
			System.out
					.println("str_er:  .asciiz \" ERROR: abnormal termination\\n\" ");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
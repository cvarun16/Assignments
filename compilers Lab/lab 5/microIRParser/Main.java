import java.io.FileInputStream;
import java.io.InputStream;

import syntaxtree.*;
import visitor.*;

public class Main {
public static void main(String [] args) {
try {
// use the following line only in IDE
//Node root = new MiniJavaParser(new FileInputStream("/home/sharmistha/jtb_javacc_demo/Factorial.java")).Goal(); 

//Final submission file should take input from command line
Node root = new microIRParser(System.in).Goal();

System.out.println("Program parsed successfully");
root.accept(new GJNoArguDepthFirst()); // Your assignment part is invoked here.

}
catch (Exception e) {
System.out.println(e.toString());
}
}
} 

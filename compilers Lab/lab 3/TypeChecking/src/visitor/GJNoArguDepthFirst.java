//
// Generated by JTB 1.3.2
//

package visitor;

import syntaxtree.*;
import java.util.*;

import MainPackage.Class;
import MainPackage.FunctionClass;
import MainPackage.SymbolTable;
import MainPackage.VariableClass;

/**
 * Provides default methods which visit each node in the tree in depth-first
 * order. Your visitors may extend this class.
 */
public class GJNoArguDepthFirst<R> implements GJNoArguVisitor<R> {
	//
	// Auto class visitors--probably don't need to be overridden.
	//
	public SymbolTable symt;

	public GJNoArguDepthFirst(SymbolTable sym) {

		this.symt = sym;
	}

	public R visit(NodeList n) {
		R _ret = null;
		int _count = 0;
		for (Enumeration<Node> e = n.elements(); e.hasMoreElements();) {
			e.nextElement().accept(this);
			_count++;
		}
		return _ret;
	}

	public R visit(NodeListOptional n) {
		if (n.present()) {
			R _ret = null;
			int _count = 0;
			for (Enumeration<Node> e = n.elements(); e.hasMoreElements();) {
				e.nextElement().accept(this);
				_count++;
			}
			return _ret;
		} else
			return null;
	}

	public R visit(NodeOptional n) {
		if (n.present())
			return n.node.accept(this);
		else
			return null;
	}

	public R visit(NodeSequence n) {
		R _ret = null;
		int _count = 0;
		for (Enumeration<Node> e = n.elements(); e.hasMoreElements();) {
			e.nextElement().accept(this);
			_count++;
		}
		return _ret;
	}

	public R visit(NodeToken n) {
		return null;
	}

	//
	// User-generated visitor methods below
	//

	/**
	 * f0 -> MainClass() f1 -> ( TypeDeclaration() )* f2 -> <EOF>
	 */
	public R visit(Goal n) {
		R _ret = null;
		n.f0.accept(this);
		System.out.println(n.f0.f1.f0.tokenImage);
		n.f1.accept(this);
		n.f2.accept(this);
		return _ret;
	}

	/**
	 * f0 -> "class" f1 -> Identifier() f2 -> "{" f3 -> "public" f4 -> "static"
	 * f5 -> "void" f6 -> "main" f7 -> "(" f8 -> "String" f9 -> "[" f10 -> "]"
	 * f11 -> Identifier() f12 -> ")" f13 -> "{" f14 -> PrintStatement() f15 ->
	 * "}" f16 -> "}"
	 */
	public R visit(MainClass n) {
		R _ret = null;
		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);
		n.f3.accept(this);
		n.f4.accept(this);
		n.f5.accept(this);
		n.f6.accept(this);
		n.f7.accept(this);
		n.f8.accept(this);
		n.f9.accept(this);
		n.f10.accept(this);
		n.f11.accept(this);
		n.f12.accept(this);
		n.f13.accept(this);
		n.f14.accept(this);
		n.f15.accept(this);
		n.f16.accept(this);
		return _ret;
	}

	/**
	 * f0 -> ClassDeclaration() | ClassExtendsDeclaration()
	 */
	public R visit(TypeDeclaration n) {
		R _ret = null;
		if (n.f0.which == 0) {
			String name = ((ClassDeclaration) n.f0.choice).f1.f0.tokenImage;
			SymbolTable.currentClass = name;

			String hashString = symt.hashString("class", name,
					SymbolTable.currentClass, null);

			symt.push(hashString, new Class(name));
		}
		if (n.f0.which == 1) {
			// TODO: Inheritance of the class
		}
		n.f0.accept(this);

		return _ret;
	}

	/**
	 * f0 -> "class" f1 -> Identifier() f2 -> "{" f3 -> ( VarDeclaration() )* f4
	 * -> ( MethodDeclaration() )* f5 -> "}"
	 */
	public R visit(ClassDeclaration n) {
		R _ret = null;
		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);
		n.f3.accept(this);
		n.f4.accept(this);
		n.f5.accept(this);

		return _ret;
	}

	/**
	 * f0 -> "class" f1 -> Identifier() f2 -> "extends" f3 -> Identifier() f4 ->
	 * "{" f5 -> ( VarDeclaration() )* f6 -> ( MethodDeclaration() )* f7 -> "}"
	 */
	public R visit(ClassExtendsDeclaration n) {
		R _ret = null;
		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);
		n.f3.accept(this);
		n.f4.accept(this);
		n.f5.accept(this);
		n.f6.accept(this);
		n.f7.accept(this);
		return _ret;
	}

	/**
	 * f0 -> Type() f1 -> Identifier() f2 -> ";"
	 */
	public R visit(VarDeclaration n) {
		R _ret = null;
		String type = "";
		String name = "";
		// ArrayType
		if (n.f0.f0.which == 0) {
			type = "int[]";
		}
		// BooleanType
		if (n.f0.f0.which == 1) {
			type = "boolean";
		}
		// IntegerType
		if (n.f0.f0.which == 2) {
			type = "int";
		}
		// Identifier
		if (n.f0.f0.which == 3) {
			type = ((Identifier) (n.f0.f0.choice)).f0.tokenImage;
		}
		name = n.f1.f0.tokenImage;

		String hashString = symt.hashString(type, name,
				SymbolTable.currentClass, SymbolTable.currentFunction);
		if (!symt.mainTable.containsKey(hashString))
			symt.push(hashString, new VariableClass(type, name));
		else {
			System.out.println("No");
			System.exit(1);
		}

		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);

		return _ret;
	}

	/**
	 * f0 -> "public" f1 -> Type() f2 -> Identifier() f3 -> "(" f4 -> (
	 * FormalParameterList() )? f5 -> ")" f6 -> "{" f7 -> ( VarDeclaration() )*
	 * f8 -> ( Statement() )* f9 -> "return" f10 -> Expression() f11 -> ";" f12
	 * -> "}"
	 */
	public R visit(MethodDeclaration n) {
		R _ret = null;
		String retType = "";
		String name = "";

		// ArrayType
		if (n.f1.f0.which == 0) {
			retType = "int[]";
		}
		// BooleanType
		if (n.f1.f0.which == 1) {
			retType = "boolean";
		}
		// IntegerType
		if (n.f1.f0.which == 2) {
			retType = "int";
		}
		// Identifier
		if (n.f1.f0.which == 3) {
			retType = ((Identifier) (n.f1.f0.choice)).f0.tokenImage;
		}
		name = n.f2.f0.tokenImage;
		SymbolTable.currentFunction = name;

		String hashString = symt.hashString("function", name,
				SymbolTable.currentClass, SymbolTable.currentFunction);

		symt.push(hashString, new FunctionClass(retType));
		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);
		n.f3.accept(this);
		n.f4.accept(this);
		n.f5.accept(this);
		n.f6.accept(this);
		n.f7.accept(this);
		n.f8.accept(this);
		n.f9.accept(this);
		n.f10.accept(this);
		n.f11.accept(this);
		n.f12.accept(this);
		return _ret;
	}

	/**
	 * f0 -> FormalParameter() f1 -> ( FormalParameterRest() )*
	 */
	public R visit(FormalParameterList n) {
		R _ret = null;
		n.f0.accept(this);
		n.f1.accept(this);

		return _ret;
	}

	/**
	 * f0 -> Type() f1 -> Identifier()
	 */
	public R visit(FormalParameter n) {
		R _ret = null;

		String hashString = symt.hashString("function",
				SymbolTable.currentFunction, SymbolTable.currentClass,
				SymbolTable.currentFunction);
		FunctionClass F = (FunctionClass) symt.mainTable.get(hashString);

		String type = "";
		String name = "";

		// ArrayType
		if (n.f0.f0.which == 0) {
			type = "int[]";
		}
		// BooleanType
		if (n.f0.f0.which == 1) {
			type = "boolean";
		}
		// IntegerType
		if (n.f0.f0.which == 2) {
			type = "int";
		}
		// Identifier
		if (n.f0.f0.which == 3) {
			type = ((Identifier) (n.f0.f0.choice)).f0.tokenImage;
		}
		name = n.f1.f0.tokenImage;
		if (!symt.mainTable.containsKey(symt.hashString(type, name,
				SymbolTable.currentClass, SymbolTable.currentFunction)))
			symt
					.push(symt.hashString(type, name, SymbolTable.currentClass,
							SymbolTable.currentFunction), new VariableClass(
							type, name));
		else {
			System.out.println("No");
			System.exit(1);
		}
		F.formalParamList.add(new VariableClass(type, name));
		symt.push(hashString, F);

		n.f0.accept(this);
		n.f1.accept(this);
		return _ret;
	}

	/**
	 * f0 -> "," f1 -> FormalParameter()
	 */
	public R visit(FormalParameterRest n) {
		R _ret = null;
		n.f0.accept(this);
		n.f1.accept(this);
		return _ret;
	}

	/**
	 * f0 -> ArrayType() | BooleanType() | IntegerType() | Identifier()
	 */
	public R visit(Type n) {
		R _ret = null;
		n.f0.accept(this);
		return _ret;
	}

	/**
	 * f0 -> "int" f1 -> "[" f2 -> "]"
	 */
	public R visit(ArrayType n) {
		R _ret = null;
		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);
		return _ret;
	}

	/**
	 * f0 -> "boolean"
	 */
	public R visit(BooleanType n) {
		R _ret = null;
		n.f0.accept(this);
		return _ret;
	}

	/**
	 * f0 -> "int"
	 */
	public R visit(IntegerType n) {
		R _ret = null;
		n.f0.accept(this);
		return _ret;
	}

	/**
	 * f0 -> Block() | AssignmentStatement() | ArrayAssignmentStatement() |
	 * IfStatement() | WhileStatement() | PrintStatement()
	 */
	public R visit(Statement n) {
		R _ret = null;
		n.f0.accept(this);
		return _ret;
	}

	/**
	 * f0 -> "{" f1 -> ( Statement() )* f2 -> "}"
	 */
	public R visit(Block n) {
		R _ret = null;
		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);
		return _ret;
	}

	/**
	 * f0 -> Identifier() f1 -> "=" f2 -> Expression() f3 -> ";"
	 */
	public R visit(AssignmentStatement n) {
		R _ret = null;
		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);
		n.f3.accept(this);
		return _ret;
	}

	/**
	 * f0 -> Identifier() f1 -> "[" f2 -> Expression() f3 -> "]" f4 -> "=" f5 ->
	 * Expression() f6 -> ";"
	 */
	public R visit(ArrayAssignmentStatement n) {
		R _ret = null;
		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);
		n.f3.accept(this);
		n.f4.accept(this);
		n.f5.accept(this);
		n.f6.accept(this);
		return _ret;
	}

	/**
	 * f0 -> "if" f1 -> "(" f2 -> Expression() f3 -> ")" f4 -> Statement() f5 ->
	 * "else" f6 -> Statement()
	 */
	public R visit(IfStatement n) {
		R _ret = null;
		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);
		n.f3.accept(this);
		n.f4.accept(this);
		n.f5.accept(this);
		n.f6.accept(this);
		return _ret;
	}

	/**
	 * f0 -> "while" f1 -> "(" f2 -> Expression() f3 -> ")" f4 -> Statement()
	 */
	public R visit(WhileStatement n) {
		R _ret = null;
		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);
		n.f3.accept(this);
		n.f4.accept(this);
		return _ret;
	}

	/**
	 * f0 -> "System.out.println" f1 -> "(" f2 -> Expression() f3 -> ")" f4 ->
	 * ";"
	 */
	public R visit(PrintStatement n) {
		R _ret = null;
		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);
		n.f3.accept(this);
		n.f4.accept(this);
		return _ret;
	}

	/**
	 * f0 -> AndExpression() | CompareExpression() | PlusExpression() |
	 * MinusExpression() | TimesExpression() | ArrayLookup() | ArrayLength() |
	 * MessageSend() | PrimaryExpression()
	 */
	public R visit(Expression n) {
		R _ret = null;
		n.f0.accept(this);
		return _ret;
	}

	/**
	 * f0 -> PrimaryExpression() f1 -> "&" f2 -> PrimaryExpression()
	 */
	public R visit(AndExpression n) {
		R _ret = null;
		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);
		return _ret;
	}

/**
    * f0 -> PrimaryExpression()
    * f1 -> "<"
    * f2 -> PrimaryExpression()
    */
	public R visit(CompareExpression n) {
		R _ret = null;
		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);
		return _ret;
	}

	/**
	 * f0 -> PrimaryExpression() f1 -> "+" f2 -> PrimaryExpression()
	 */
	public R visit(PlusExpression n) {
		R _ret = null;
		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);
		return _ret;
	}

	/**
	 * f0 -> PrimaryExpression() f1 -> "-" f2 -> PrimaryExpression()
	 */
	public R visit(MinusExpression n) {
		R _ret = null;
		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);
		return _ret;
	}

	/**
	 * f0 -> PrimaryExpression() f1 -> "*" f2 -> PrimaryExpression()
	 */
	public R visit(TimesExpression n) {
		R _ret = null;
		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);
		return _ret;
	}

	/**
	 * f0 -> PrimaryExpression() f1 -> "[" f2 -> PrimaryExpression() f3 -> "]"
	 */
	public R visit(ArrayLookup n) {
		R _ret = null;
		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);
		n.f3.accept(this);
		return _ret;
	}

	/**
	 * f0 -> PrimaryExpression() f1 -> "." f2 -> "length"
	 */
	public R visit(ArrayLength n) {
		R _ret = null;
		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);
		return _ret;
	}

	/**
	 * f0 -> PrimaryExpression() f1 -> "." f2 -> Identifier() f3 -> "(" f4 -> (
	 * ExpressionList() )? f5 -> ")"
	 */
	public R visit(MessageSend n) {
		R _ret = null;
		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);
		n.f3.accept(this);
		n.f4.accept(this);
		n.f5.accept(this);
		return _ret;
	}

	/**
	 * f0 -> Expression() f1 -> ( ExpressionRest() )*
	 */
	public R visit(ExpressionList n) {
		R _ret = null;
		n.f0.accept(this);
		n.f1.accept(this);
		return _ret;
	}

	/**
	 * f0 -> "," f1 -> Expression()
	 */
	public R visit(ExpressionRest n) {
		R _ret = null;
		n.f0.accept(this);
		n.f1.accept(this);
		return _ret;
	}

	/**
	 * f0 -> IntegerLiteral() | TrueLiteral() | FalseLiteral() | Identifier() |
	 * ThisExpression() | ArrayAllocationExpression() | AllocationExpression() |
	 * NotExpression() | BracketExpression()
	 */
	public R visit(PrimaryExpression n) {
		R _ret = null;
		n.f0.accept(this);
		return _ret;
	}

	/**
	 * f0 -> <INTEGER_LITERAL>
	 */
	public R visit(IntegerLiteral n) {
		R _ret = null;
		n.f0.accept(this);
		return _ret;
	}

	/**
	 * f0 -> "true"
	 */
	public R visit(TrueLiteral n) {
		R _ret = null;
		n.f0.accept(this);
		return _ret;
	}

	/**
	 * f0 -> "false"
	 */
	public R visit(FalseLiteral n) {
		R _ret = null;
		n.f0.accept(this);
		return _ret;
	}

	/**
	 * f0 -> <IDENTIFIER>
	 */
	public R visit(Identifier n) {
		R _ret = null;
		n.f0.accept(this);
		return _ret;
	}

	/**
	 * f0 -> "this"
	 */
	public R visit(ThisExpression n) {
		R _ret = null;
		n.f0.accept(this);
		return _ret;
	}

	/**
	 * f0 -> "new" f1 -> "int" f2 -> "[" f3 -> Expression() f4 -> "]"
	 */
	public R visit(ArrayAllocationExpression n) {
		R _ret = null;
		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);
		n.f3.accept(this);
		n.f4.accept(this);
		return _ret;
	}

	/**
	 * f0 -> "new" f1 -> Identifier() f2 -> "(" f3 -> ")"
	 */
	public R visit(AllocationExpression n) {
		R _ret = null;
		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);
		n.f3.accept(this);
		return _ret;
	}

	/**
	 * f0 -> "!" f1 -> Expression()
	 */
	public R visit(NotExpression n) {
		R _ret = null;
		n.f0.accept(this);
		n.f1.accept(this);
		return _ret;
	}

	/**
	 * f0 -> "(" f1 -> Expression() f2 -> ")"
	 */
	public R visit(BracketExpression n) {
		R _ret = null;
		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);
		return _ret;
	}

}

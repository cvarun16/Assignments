//
// Generated by JTB 1.3.2
//

package visitor;

import syntaxtree.*;

import java.util.*;

/**
 * Provides default methods which visit each node in the tree in depth-first
 * order. Your visitors may extend this class.
 */
public class GJNoArguDepthFirst<R> implements GJNoArguVisitor<R> {
	//
	// Auto class visitors--probably don't need to be overridden.
	//
	static int tempNumber = 1000;
	static boolean function = false;
	static boolean label = true;

	public R visit(NodeList n) {
		R _ret = null;
		label = true;
		int _count = 0;
		for (Enumeration<Node> e = n.elements(); e.hasMoreElements();) {
			e.nextElement().accept(this);
			_count++;
		}
		return _ret;
	}

	public R visit(NodeListOptional n) {
		if (n.present()) {
			label = true;
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
		label = true;
		if (n.present())
			
			return n.node.accept(this);
		else
			return null;
	}

	public R visit(NodeSequence n) {
		label = true;
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
	 * f0 -> "MAIN" f1 -> StmtList() f2 -> "END" f3 -> ( Procedure() )* f4 ->
	 * <EOF>
	 */
	public R visit(Goal n) {
		R _ret = null;
		n.f0.accept(this);
		System.out.print(" MAIN ");
		n.f1.accept(this);
		n.f2.accept(this);
		System.out.println(" END ");
		n.f3.accept(this);
		n.f4.accept(this);
		return _ret;
	}

	/**
	 * f0 -> ( ( Label() )? Stmt() )*
	 */
	public R visit(StmtList n) {
		R _ret = null;
		
		n.f0.accept(this);
		return _ret;
	}

	/**
	 * f0 -> Label() f1 -> "[" f2 -> IntegerLiteral() f3 -> "]" f4 -> StmtExp()
	 */
	public R visit(Procedure n) {
		R _ret = null;
		label = false;
		R lab = n.f0.accept(this);
		System.out.println(lab);
		n.f1.accept(this);
		System.out.print(" [ ");
		System.out.println(n.f2.accept(this));
		n.f3.accept(this);
		System.out.print(" ] ");
		function = true;
		n.f4.accept(this);
		return _ret;
	}

	/**
	 * f0 -> NoOpStmt() | ErrorStmt() | CJumpStmt() | JumpStmt() | HStoreStmt()
	 * | HLoadStmt() | MoveStmt() | PrintStmt()
	 */
	public R visit(Stmt n) {
		R _ret = null;
		n.f0.accept(this);
		return _ret;
	}

	/**
	 * f0 -> "NOOP"
	 */
	public R visit(NoOpStmt n) {
		R _ret = null;
		n.f0.accept(this);
		System.out.println(" NOOP ");
		return _ret;
	}

	/**
	 * f0 -> "ERROR"
	 */
	public R visit(ErrorStmt n) {
		R _ret = null;
		n.f0.accept(this);
		System.out.println(" ERROR ");
		return _ret;
	}

	/**
	 * f0 -> "CJUMP" f1 -> Exp() f2 -> Label()
	 */
	public R visit(CJumpStmt n) {
		R _ret = null;
		n.f0.accept(this);
		R exp = n.f1.accept(this);
		label = false;
		R lab = n.f2.accept(this);
		int num1 = tempNumber;
		tempNumber++;
		_ret = (R) (" MOVE TEMP " + num1 + " " + exp);
		System.out.print(_ret + " CJUMP ");
		System.out.println(" TEMP " + num1);
		System.out.println(lab);
		return _ret;
	}

	/**
	 * f0 -> "JUMP" f1 -> Label()
	 */
	public R visit(JumpStmt n) {
		R _ret = null;
		n.f0.accept(this);
		label = false;
		R lab = n.f1.accept(this);
		System.out.print(" JUMP ");
		System.out.println(lab);
		return _ret;
	}

	/**
	 * f0 -> "HSTORE" f1 -> Exp() f2 -> IntegerLiteral() f3 -> Exp()
	 */
	public R visit(HStoreStmt n) {
		R _ret = null;
		n.f0.accept(this);
		R exp1 = n.f1.accept(this);
		R intl = n.f2.accept(this);
		R exp2 = n.f3.accept(this);
		int num1 = tempNumber;
		tempNumber++;
		int num2 = tempNumber;
		tempNumber++;
		_ret = (R) (" MOVE TEMP " + num1 + " " + exp1);
		_ret = (R) (_ret + " MOVE TEMP " + num2 + " " + exp2);

		System.out.print(_ret + " HSTORE ");
		System.out.println(" TEMP " + num1);
		System.out.println(intl);
		System.out.println(" TEMP " + num2);
		return _ret;
	}

	/**
	 * f0 -> "HLOAD" f1 -> Temp() f2 -> Exp() f3 -> IntegerLiteral()
	 */
	public R visit(HLoadStmt n) {
		R _ret = null;
		n.f0.accept(this);
		R temp = n.f1.accept(this);
		R exp = n.f2.accept(this);
		R intl = n.f3.accept(this);
		int num1 = tempNumber;
		tempNumber++;
		_ret = (R) (" MOVE TEMP " + num1 + " " + exp);

		System.out.print(_ret + " HLOAD ");
		System.out.println(temp);
		System.out.println(" TEMP " + num1);
		System.out.println(intl);
		return _ret;
	}

	/**
	 * f0 -> "MOVE" f1 -> Temp() f2 -> Exp()
	 */
	public R visit(MoveStmt n) {
		R _ret = null;
		n.f0.accept(this);
		R temp = n.f1.accept(this);
		R exp = n.f2.accept(this);

		int num1 = tempNumber;
		tempNumber++;
		_ret = (R) (" MOVE TEMP " + num1 + " " + exp);
		System.out.print(_ret + " MOVE ");
		System.out.println(temp);
		System.out.println(" TEMP " + num1);
		return _ret;
	}

	/**
	 * f0 -> "PRINT" f1 -> Exp()
	 */
	public R visit(PrintStmt n) {
		R _ret = null;
		n.f0.accept(this);
		R exp = n.f1.accept(this);
		int num1 = tempNumber;
		tempNumber++;

		_ret = (R) (" MOVE TEMP " + num1 + " " + exp);
		System.out.println(_ret + " PRINT TEMP " + num1);
		return _ret;
	}

	/**
	 * f0 -> StmtExp() | Call() | HAllocate() | BinOp() | Temp() |
	 * IntegerLiteral() | Label()
	 */
	public R visit(Exp n) {
		R _ret = null;
		label = false;
		_ret = n.f0.accept(this);
		return _ret;
	}

	/**
	 * f0 -> "BEGIN" f1 -> StmtList() f2 -> "RETURN" f3 -> Exp() f4 -> "END"
	 */
	public R visit(StmtExp n) {
		R _ret = null;
		boolean lfunction = function;
		function = false;

		n.f0.accept(this);
		if (lfunction)
			System.out.print(" BEGIN ");
		n.f1.accept(this);
		n.f2.accept(this);

		R exp = n.f3.accept(this);
		if (lfunction) {
			int num1 = tempNumber;
			tempNumber++;

			_ret = (R) (" MOVE TEMP " + num1 + " " + exp);
			System.out.println(_ret);
			System.out.println(" RETURN ");
			System.out.println(" TEMP " + num1);
		}
		n.f4.accept(this);
		if (lfunction)
			System.out.println(" END ");

		if (!lfunction) {
			int num1 = tempNumber;
			tempNumber++;

			_ret = (R) (" MOVE TEMP " + num1 + " " + exp);
			_ret = (R) (_ret + " MOVE TEMP " + tempNumber + " TEMP " + num1);
			System.out.println(_ret);
			_ret = (R) ("TEMP " + tempNumber);
			tempNumber++;
		}
		lfunction = false;
		return _ret;
	}

	/**
	 * f0 -> "CALL" f1 -> Exp() f2 -> "(" f3 -> ( Exp() )* f4 -> ")"
	 */
	public R visit(Call n) {
		R _ret = null;

		n.f0.accept(this);
		R exp1 = n.f1.accept(this);
		n.f2.accept(this);
		n.f3.accept(this);
		n.f4.accept(this);
		Vector<Node> nodes = n.f3.nodes;

		int num1 = tempNumber;
		tempNumber++;
		_ret = (R) (" MOVE TEMP " + num1 + " " + exp1);

		Vector<Integer> temps = new Vector<Integer>();
		for (Node N : nodes) {
			temps.add(tempNumber);
			_ret = (R) (_ret + " MOVE TEMP " + tempNumber + " " + N
					.accept(this));
			tempNumber++;
		}

		System.out.println(_ret);
		_ret = (R) (" MOVE TEMP " + tempNumber + " CALL TEMP " + num1 + " ( ");

		int i = 0;
		for (Node N : nodes) {
			_ret = (R) (_ret + " TEMP " + temps.get(i));
			i++;
		}
		_ret = (R) (_ret + " ) ");
		System.out.println(_ret);
		_ret = (R) ("TEMP " + tempNumber);
		tempNumber++;
		return _ret;
	}

	/**
	 * f0 -> "HALLOCATE" f1 -> Exp()
	 */
	public R visit(HAllocate n) {
		R _ret = null;
		n.f0.accept(this);
		R exp = n.f1.accept(this);

		int num1 = tempNumber;
		tempNumber++;
		_ret = (R) (" MOVE TEMP " + num1 + " " + exp);

		_ret = (R) (_ret + " MOVE TEMP " + tempNumber + " HALLOCATE TEMP " + num1);
		System.out.println(_ret);
		_ret = (R) ("TEMP " + tempNumber);
		tempNumber++;

		return _ret;
	}

	/**
	 * f0 -> Operator() f1 -> Exp() f2 -> Exp()
	 */
	public R visit(BinOp n) {
		R _ret = null;

		R op = n.f0.accept(this);
		String exp1 = (String) n.f1.accept(this);
		String exp2 = (String) n.f2.accept(this);

		int num1 = tempNumber;
		tempNumber++;
		int num2 = tempNumber;
		tempNumber++;
		_ret = (R) (" MOVE TEMP " + num1 + " " + exp1);
		_ret = (R) (_ret + " MOVE TEMP " + num2 + " " + exp2);

		_ret = (R) (_ret + " MOVE TEMP " + tempNumber + " " + op + " TEMP "
				+ num1 + " TEMP " + num2);
		System.out.println(_ret);
		_ret = (R) ("TEMP " + tempNumber);
		tempNumber++;
		return _ret;
	}

	/**
	 * f0 -> "LT" | "PLUS" | "MINUS" | "TIMES"
	 */
	public R visit(Operator n) {
		R _ret = null;
		n.f0.accept(this);
		switch (n.f0.which) {
		case 0:
			_ret = (R) " LT ";
			break;
		case 1:
			_ret = (R) " PLUS ";
			break;
		case 2:
			_ret = (R) " MINUS ";
			break;
		case 3:
			_ret = (R) " TIMES ";
			break;
		}

		return _ret;
	}

	/**
	 * f0 -> "TEMP" f1 -> IntegerLiteral()
	 */
	public R visit(Temp n) {
		R _ret = null;
		n.f0.accept(this);
		R intl = n.f1.accept(this);
		_ret = (R) ("TEMP " + intl);
		return _ret;
	}

	/**
	 * f0 -> <INTEGER_LITERAL>
	 */
	public R visit(IntegerLiteral n) {
		R _ret = null;
		_ret = n.f0.accept(this);
		_ret = (R) n.f0.tokenImage;
		return _ret;
	}

	/**
	 * f0 -> <IDENTIFIER>
	 */
	public R visit(Label n) {
		R _ret = null;
		if (label)
			System.out.println(n.f0.tokenImage);
		label = true;
		n.f0.accept(this);
		_ret = (R) n.f0.tokenImage;

		return _ret;
	}
}
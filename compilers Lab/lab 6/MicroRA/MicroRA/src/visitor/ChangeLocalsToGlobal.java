//
// Generated by JTB 1.3.2
//

package visitor;

import syntaxtree.*;
import java.util.*;

import MainPackage.AliasTable;
import MainPackage.SymbolTable;

/**
 * Provides default methods which visit each node in the tree in depth-first
 * order. Your visitors may extend this class.
 */
public class ChangeLocalsToGlobal<R, A> implements GJVisitor<R, A> {
	//
	// Auto class visitors--probably don't need to be overridden.
	//
	static String currentFunction = "";
	static int labelNumber = 1;
	static int tempNumber = 1;
	static int paraNumber = -1; // parameter temps will be named
	static boolean functionName = false;

	public R visit(NodeList n, A argu) {
		R _ret = null;
		int _count = 0;
		for (Enumeration<Node> e = n.elements(); e.hasMoreElements();) {
			e.nextElement().accept(this, argu);
			_count++;
		}
		return _ret;
	}

	public R visit(NodeListOptional n, A argu) {
		if (n.present()) {
			R _ret = null;
			int _count = 0;
			for (Enumeration<Node> e = n.elements(); e.hasMoreElements();) {
				e.nextElement().accept(this, argu);
				_count++;
			}
			return _ret;
		} else
			return null;
	}

	public R visit(NodeOptional n, A argu) {
		if (n.present())
			return n.node.accept(this, argu);
		else
			return null;
	}

	public R visit(NodeSequence n, A argu) {
		R _ret = null;
		int _count = 0;
		for (Enumeration<Node> e = n.elements(); e.hasMoreElements();) {
			e.nextElement().accept(this, argu);
			_count++;
		}
		return _ret;
	}

	public R visit(NodeToken n, A argu) {
		return null;
	}

	//
	// User-generated visitor methods below
	//

	/**
	 * f0 -> "MAIN" f1 -> StmtList() f2 -> "END" f3 -> ( Procedure() )* f4 ->
	 * <EOF>
	 */
	public R visit(Goal n, A argu) {
		R _ret = null;

		n.f0.accept(this, argu);
		n.f1.accept(this, argu);
		n.f2.accept(this, argu);
		n.f3.accept(this, argu);
		n.f4.accept(this, argu);
		return _ret;
	}

	/**
	 * f0 -> ( ( Label() )? Stmt() )*
	 */
	public R visit(StmtList n, A argu) {
		R _ret = null;
		n.f0.accept(this, argu);
		return _ret;
	}

	/**
	 * f0 -> Label() f1 -> "[" f2 -> IntegerLiteral() f3 -> "]" f4 -> StmtExp()
	 */
	public R visit(Procedure n, A argu) {
		R _ret = null;
		functionName = true;
		n.f0.accept(this, argu);
		n.f1.accept(this, argu);
		n.f2.accept(this, argu);
		n.f3.accept(this, argu);
		n.f4.accept(this, argu);
		currentFunction = "";
		return _ret;
	}

	/**
	 * f0 -> NoOpStmt() | ErrorStmt() | CJumpStmt() | JumpStmt() | HStoreStmt()
	 * | HLoadStmt() | MoveStmt() | PrintStmt()
	 */
	public R visit(Stmt n, A argu) {
		R _ret = null;
		n.f0.accept(this, argu);
		return _ret;
	}

	/**
	 * f0 -> "NOOP"
	 */
	public R visit(NoOpStmt n, A argu) {
		R _ret = null;
		n.f0.accept(this, argu);
		return _ret;
	}

	/**
	 * f0 -> "ERROR"
	 */
	public R visit(ErrorStmt n, A argu) {
		R _ret = null;
		n.f0.accept(this, argu);
		return _ret;
	}

	/**
	 * f0 -> "CJUMP" f1 -> Temp() f2 -> Label()
	 */
	public R visit(CJumpStmt n, A argu) {
		R _ret = null;
		n.f0.accept(this, argu);
		n.f1.accept(this, argu);
		n.f2.accept(this, argu);
		return _ret;
	}

	/**
	 * f0 -> "JUMP" f1 -> Label()
	 */
	public R visit(JumpStmt n, A argu) {
		R _ret = null;
		n.f0.accept(this, argu);
		n.f1.accept(this, argu);
		return _ret;
	}

	/**
	 * f0 -> "HSTORE" f1 -> Temp() f2 -> IntegerLiteral() f3 -> Temp()
	 */
	public R visit(HStoreStmt n, A argu) {
		R _ret = null;
		n.f0.accept(this, argu);
		n.f1.accept(this, argu);
		n.f2.accept(this, argu);
		n.f3.accept(this, argu);
		return _ret;
	}

	/**
	 * f0 -> "HLOAD" f1 -> Temp() f2 -> Temp() f3 -> IntegerLiteral()
	 */
	public R visit(HLoadStmt n, A argu) {
		R _ret = null;
		n.f0.accept(this, argu);
		n.f1.accept(this, argu);
		n.f2.accept(this, argu);
		n.f3.accept(this, argu);
		return _ret;
	}

	/**
	 * f0 -> "MOVE" f1 -> Temp() f2 -> Exp()
	 */
	public R visit(MoveStmt n, A argu) {
		R _ret = null;
		n.f0.accept(this, argu);
		n.f1.accept(this, argu);
		n.f2.accept(this, argu);
		return _ret;
	}

	/**
	 * f0 -> "PRINT" f1 -> SimpleExp()
	 */
	public R visit(PrintStmt n, A argu) {
		R _ret = null;
		n.f0.accept(this, argu);
		n.f1.accept(this, argu);
		return _ret;
	}

	/**
	 * f0 -> Call() | HAllocate() | BinOp() | SimpleExp()
	 */
	public R visit(Exp n, A argu) {
		R _ret = null;
		n.f0.accept(this, argu);
		return _ret;
	}

	/**
	 * f0 -> "BEGIN" f1 -> StmtList() f2 -> "RETURN" f3 -> SimpleExp() f4 ->
	 * "END"
	 */
	public R visit(StmtExp n, A argu) {
		R _ret = null;
		n.f0.accept(this, argu);
		n.f1.accept(this, argu);
		n.f2.accept(this, argu);
		n.f3.accept(this, argu);
		n.f4.accept(this, argu);
		return _ret;
	}

	/**
	 * f0 -> "CALL" f1 -> SimpleExp() f2 -> "(" f3 -> ( Temp() )* f4 -> ")"
	 */
	public R visit(Call n, A argu) {
		R _ret = null;
		n.f0.accept(this, argu);
		n.f1.accept(this, argu);
		n.f2.accept(this, argu);
		n.f3.accept(this, argu);
		n.f4.accept(this, argu);
		return _ret;
	}

	/**
	 * f0 -> "HALLOCATE" f1 -> SimpleExp()
	 */
	public R visit(HAllocate n, A argu) {
		R _ret = null;
		n.f0.accept(this, argu);
		n.f1.accept(this, argu);
		return _ret;
	}

	/**
	 * f0 -> Operator() f1 -> Temp() f2 -> SimpleExp()
	 */
	public R visit(BinOp n, A argu) {
		R _ret = null;
		n.f0.accept(this, argu);
		n.f1.accept(this, argu);
		n.f2.accept(this, argu);
		return _ret;
	}

	/**
	 * f0 -> "LT" | "PLUS" | "MINUS" | "TIMES"
	 */
	public R visit(Operator n, A argu) {
		R _ret = null;
		n.f0.accept(this, argu);
		return _ret;
	}

	/**
	 * f0 -> Temp() | IntegerLiteral() | Label()
	 */
	public R visit(SimpleExp n, A argu) {
		R _ret = null;
		n.f0.accept(this, argu);
		return _ret;
	}

	/**
	 * f0 -> "TEMP" f1 -> IntegerLiteral()
	 */
	public R visit(Temp n, A argu) {
		R _ret = null;
		n.f0.accept(this, argu);
		R iden = n.f1.accept(this, argu);
		String var = "TEMP " + iden + "#" + currentFunction;
		int number = Integer.parseInt((String) iden);
		if (!AliasTable.IRtoRA.containsKey(var) && number >= 20) {
			AliasTable.insert(var, "TEMP " + tempNumber++);
		}
		if (!AliasTable.IRtoRA.containsKey(var) && number < 20) {
			AliasTable.insert(var, "TEMP " + paraNumber--);
		}
		return _ret;
	}

	/**
	 * f0 -> <INTEGER_LITERAL>
	 */
	public R visit(IntegerLiteral n, A argu) {
		R _ret = null;
		n.f0.accept(this, argu);
		return (R) n.f0.tokenImage;
	}

	/**
	 * f0 -> <IDENTIFIER>
	 */
	public R visit(Label n, A argu) {
		R _ret = null;

		String var = n.f0.tokenImage + "#" + currentFunction;
		if (functionName) {
			functionName = false;
			currentFunction = n.f0.tokenImage;
		} else {
			if (!AliasTable.IRtoRA.containsKey(var)
					&& !SymbolTable.functions.contains(n.f0.tokenImage)) {

				AliasTable.insert(var, "Label" + labelNumber++);
			}

		}
		// n.f0.accept(this, argu);

		return _ret;
	}
}

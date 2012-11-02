//
// Generated by JTB 1.3.2
//

package visitor;

import syntaxtree.*;

import java.util.*;

import MainPackage.AliasTable;
import MainPackage.LiveRange;
import MainPackage.PairLiveRange;
import MainPackage.SymbolTable;

/**
 * Provides default methods which visit each node in the tree in depth-first
 * order. Your visitors may extend this class.
 */
public class MiniRAPrint<R> implements GJNoArguVisitor<R> {
	//
	// Auto class visitors--probably don't need to be overridden.
	//

	// Regarding the temps and label renaming
	static String currentFunction = "";
	static int numberOfParams = 0;
	static boolean functionName = false;
	static boolean label = true;

	static int fSP = 0;

	public boolean isTemp(String str) {
		return str.startsWith("TEMP ");

	}

	public void save_callee(int sp) {
		int i;
		int k = 0;
		for (i = sp; i < sp + 8; i++, k++)
			System.out.println("ASTORE SPILLEDARG " + i + " s" + k);

	}

	public void restore_callee(int sp) {
		int i, k = 0;
		for (i = sp - 8; i < sp; i++, k++)
			System.out.println("ALOAD s" + k + " SPILLEDARG " + i);

	}

	public void save_caller(int sp) {
		int i, k = 0;
		for (i = sp; i < sp + 10; i++, k++)
			System.out.println("ASTORE SPILLEDARG " + i + " t" + k);

	}

	public void restore_caller(int sp) {
		int i, k = 0;
		for (i = sp - 10; i < sp; i++, k++)
			System.out.println("ALOAD t" + k + " SPILLEDARG " + i);

	}

	public R visit(NodeList n) {
		// label = true;
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
	 * f0 -> "MAIN" f1 -> StmtList() f2 -> "END" f3 -> ( Procedure() )* f4 ->
	 * <EOF>
	 */
	public R visit(Goal n) {
		R _ret = null;
		n.f0.accept(this);
		System.out.println(" MAIN [0]" + "["
				+ (SymbolTable.LinearRange.size() + 18) + "]" + "[100]");
		n.f1.accept(this);
		n.f2.accept(this);
		System.out.println(" END ");
		fSP = 0;
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
		functionName = true;
		label = false;
		R lab = n.f0.accept(this);

		n.f1.accept(this);
		R intl = n.f2.accept(this);
		numberOfParams = Integer.parseInt((String) intl);
		System.out.println(lab + " [" + intl + "]["
				+ (SymbolTable.LinearRange.size() + 18) + "][100]");
		n.f3.accept(this);
		n.f4.accept(this);
		functionName = true;
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
		System.out.print(" NOOP ");
		return _ret;
	}

	/**
	 * f0 -> "ERROR"
	 */
	public R visit(ErrorStmt n) {
		R _ret = null;
		n.f0.accept(this);
		System.out.print(" ERROR ");
		return _ret;
	}

	/**
	 * f0 -> "CJUMP" f1 -> Temp() f2 -> Label()
	 */
	public R visit(CJumpStmt n) {
		R _ret = null;
		n.f0.accept(this);
		System.out.print(" CJUMP ");
		R temp = n.f1.accept(this);
		label = false;
		R lab = n.f2.accept(this);
		System.out.print(SymbolTable.variableRegister.get(temp));
		System.out.println(" " + lab + " ");

		return _ret;
	}

	/**
	 * f0 -> "JUMP" f1 -> Label()
	 */
	public R visit(JumpStmt n) {
		R _ret = null;
		n.f0.accept(this);
		System.out.print(" JUMP ");
		label = false;
		R lab = n.f1.accept(this);
		System.out.println(lab);
		return _ret;
	}

	/**
	 * f0 -> "HSTORE" f1 -> Temp() f2 -> IntegerLiteral() f3 -> Temp()
	 */
	public R visit(HStoreStmt n) {
		R _ret = null;
		n.f0.accept(this);
		System.out.print(" HSTORE ");
		R temp1 = n.f1.accept(this);
		R intl = n.f2.accept(this);
		R temp2 = n.f3.accept(this);

		System.out.println(SymbolTable.variableRegister.get(temp1) + " " + intl
				+ " " + SymbolTable.variableRegister.get(temp2));

		return _ret;
	}

	/**
	 * f0 -> "HLOAD" f1 -> Temp() f2 -> Temp() f3 -> IntegerLiteral()
	 */
	public R visit(HLoadStmt n) {
		R _ret = null;
		n.f0.accept(this);
		System.out.print(" HLOAD ");
		R temp1 = n.f1.accept(this);
		R temp2 = n.f2.accept(this);
		R intl = n.f3.accept(this);

		System.out.println(SymbolTable.variableRegister.get(temp1) + " "
				+ SymbolTable.variableRegister.get(temp2) + " " + intl);
		return _ret;
	}

	/**
	 * f0 -> "MOVE" f1 -> Temp() f2 -> Exp()
	 */
	public R visit(MoveStmt n) {
		R _ret = null;
		boolean c = true;
		n.f0.accept(this);

		R temp = n.f1.accept(this);
		if (n.f2.f0.which != 0) {
			System.out.print("MOVE " + SymbolTable.variableRegister.get(temp)
					+ " ");
			c = true;

		} else
			c = false;
		n.f2.accept(this);

		if (!c)
			System.out.println(" MOVE "
					+ SymbolTable.variableRegister.get(temp) + " v0");
		System.out.println();
		return _ret;
	}

	/**
	 * f0 -> "PRINT" f1 -> SimpleExp()
	 */
	public R visit(PrintStmt n) {
		R _ret = null;
		n.f0.accept(this);
		System.out.print(" PRINT ");
		R simExp = n.f1.accept(this);
		if (isTemp((String) simExp))
			System.out.println(SymbolTable.variableRegister.get(simExp));
		else
			System.out.println(simExp);

		return _ret;
	}

	/**
	 * f0 -> Call() | HAllocate() | BinOp() | SimpleExp()
	 */
	public R visit(Exp n) {
		R _ret = null;

		_ret = n.f0.accept(this);
		if (n.f0.which == 3) {
			if (isTemp((String) _ret))
				System.out.print(" " + SymbolTable.variableRegister.get(_ret)
						+ " ");
			else
				System.out.print(" " + _ret + " ");
		}
		return _ret;
	}

	/**
	 * f0 -> "BEGIN" f1 -> StmtList() f2 -> "RETURN" f3 -> SimpleExp() f4 ->
	 * "END"
	 */
	public R visit(StmtExp n) {
		R _ret = null;
		/* Save callee saved registers */
		save_callee(fSP);
		fSP += 8;
		int i;
		for (i = 0; i < Math.min(4, numberOfParams); i++) {
			String var = "TEMP " + i + "#" + currentFunction;
			var = AliasTable.getRAFromIR(var);
			// System.out.println(var);
			System.out.println("MOVE " + SymbolTable.variableRegister.get(var)
					+ " a" + i);
		}
		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);
		R simExp = n.f3.accept(this);
		if (isTemp((String) simExp))
			System.out.println(" MOVE v0 "
					+ SymbolTable.variableRegister.get(simExp));
		else
			System.out.println(" MOVE v0 " + simExp);

		n.f4.accept(this);
		restore_callee(fSP);
		fSP = 0;
		System.out.println("END");

		return _ret;
	}

	/**
	 * f0 -> "CALL" f1 -> SimpleExp() f2 -> "(" f3 -> ( Temp() )* f4 -> ")"
	 */
	public R visit(Call n) {
		R _ret = null;
		Vector<Node> params = n.f3.nodes;
		int i = 0;
		for (Node node : params) {
			R exp = node.accept(this);
			if (i <= 3)
				System.out.println("MOVE a" + i + " "
						+ SymbolTable.variableRegister.get(exp));
			else
				System.out.println("PASSARG " + (i - 3) + " "
						+ SymbolTable.variableRegister.get(exp));

			i++;
		}

		n.f0.accept(this);

		R lab = n.f1.accept(this);
		save_caller(fSP);
		fSP += 10;
		if (isTemp((String) lab))
			System.out.println("CALL " + SymbolTable.variableRegister.get(lab));
		else
			System.out.println("CALL " + lab);
		n.f2.accept(this);
		// n.f3.accept(this);
		restore_caller(fSP);
		fSP -= 10;
		n.f4.accept(this);
		return _ret;
	}

	/**
	 * f0 -> "HALLOCATE" f1 -> SimpleExp()
	 */
	public R visit(HAllocate n) {
		R _ret = null;
		n.f0.accept(this);
		System.out.print(" HALLOCATE ");
		R simExp = n.f1.accept(this);
		if (isTemp((String) simExp))
			System.out.print(SymbolTable.variableRegister.get(simExp));
		else
			System.out.print(simExp);

		System.out.println();
		return _ret;
	}

	/**
	 * f0 -> Operator() f1 -> Temp() f2 -> SimpleExp()
	 */
	public R visit(BinOp n) {
		R _ret = null;
		R op = n.f0.accept(this);
		System.out.print(op);
		R temp = n.f1.accept(this);
		System.out.print(" " + SymbolTable.variableRegister.get(temp) + " ");
		R simExp = n.f2.accept(this);
		if (isTemp((String) simExp))
			System.out.print(" " + SymbolTable.variableRegister.get(simExp)
					+ " ");
		else
			System.out.print(" " + simExp + " ");

		System.out.println();
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
	 * f0 -> Temp() | IntegerLiteral() | Label()
	 */
	public R visit(SimpleExp n) {
		R _ret = null;
		if (n.f0.which == 2)
			label = false;
		_ret = n.f0.accept(this);
		return _ret;
	}

	/**
	 * f0 -> "TEMP" f1 -> IntegerLiteral()
	 */
	public R visit(Temp n) {
		R _ret = null;
		n.f0.accept(this);
		R intl = n.f1.accept(this);

		String hash = "TEMP " + intl + "#" + currentFunction;
		String var = AliasTable.IRtoRA.get(hash);

		return (R) var;
	}

	/**
	 * f0 -> <INTEGER_LITERAL>
	 */
	public R visit(IntegerLiteral n) {
		n.f0.accept(this);
		return (R) n.f0.tokenImage;
	}

	/**
	 * f0 -> <IDENTIFIER>
	 */
	public R visit(Label n) {
		R _ret = null;

		String var = n.f0.tokenImage + "#" + currentFunction;
		if (functionName) {
			functionName = false;
			currentFunction = n.f0.tokenImage;
		}
		if (!SymbolTable.functions.contains(n.f0.tokenImage))
			_ret = (R) AliasTable.IRtoRA.get(var);
		else
			_ret = (R) n.f0.tokenImage;

		if (label) {
			System.out.print(" " + _ret + " ");
		}
		label = true;
		// n.f0.accept(this);
		return _ret;
	}

}

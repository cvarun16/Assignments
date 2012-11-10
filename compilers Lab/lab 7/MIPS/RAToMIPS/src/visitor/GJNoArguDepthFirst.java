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
		label = true;
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
		label = true;
		if (n.present())

			return n.node.accept(this);
		else
			return null;
	}

	public R visit(NodeSequence n) {
		R _ret = null;
		label = true;
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
	 * f0 -> "MAIN" f1 -> "[" f2 -> IntegerLiteral() f3 -> "]" f4 -> "[" f5 ->
	 * IntegerLiteral() f6 -> "]" f7 -> "[" f8 -> IntegerLiteral() f9 -> "]" f10
	 * -> StmtList() f11 -> "END" f12 -> ( Procedure() )* f13 -> <EOF>
	 */
	public R visit(Goal n) {
		R _ret = null;
		System.out.println(".text");
		System.out.println(".globl  main");
		System.out.println("main:");
		n.f0.accept(this);
		n.f1.accept(this);
		R first = n.f2.accept(this);
		n.f3.accept(this);
		n.f4.accept(this);
		R second = n.f5.accept(this);
		n.f6.accept(this);
		n.f7.accept(this);
		R third = n.f8.accept(this);
		System.out.println("move $fp $sp");
		int num = Integer.parseInt((String) third);
		if (num <= 4) {
			num = 4;
			System.out.println("subu $sp,$sp," + num);

		} else {
			num = Integer.parseInt((String) third);
			num = (num - 3) * 4;
			System.out.println("subu $sp,$sp," + num);
		}
		System.out.println("sw $ra,-4($fp)");
		n.f9.accept(this);
		n.f10.accept(this);
		n.f11.accept(this);
		System.out.println("lw $ra,-4($fp)");
		System.out.println("addu $sp,$sp," + num);
		System.out.println("j $ra");
		n.f12.accept(this);

		n.f13.accept(this);

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
	 * f0 -> Label() f1 -> "[" f2 -> IntegerLiteral() f3 -> "]" f4 -> "[" f5 ->
	 * IntegerLiteral() f6 -> "]" f7 -> "[" f8 -> IntegerLiteral() f9 -> "]" f10
	 * -> StmtList() f11 -> "END"
	 */
	public R visit(Procedure n) {
		R _ret = null;

		label = false;
		R lab = n.f0.accept(this);
		System.out.println(".text");
		System.out.println(".globl " + lab);
		System.out.println(lab + ":");
		n.f1.accept(this);
		R first = n.f2.accept(this);
		n.f3.accept(this);
		n.f4.accept(this);
		R second = n.f5.accept(this);
		n.f6.accept(this);
		n.f7.accept(this);
		R third = n.f8.accept(this);
		n.f9.accept(this);
		System.out.println("sw $fp, -8($sp)");
		System.out.println("move $fp, $sp");
		int num = Integer.parseInt((String) first)
				+ Integer.parseInt((String) second)
				+ Integer.parseInt((String) third) + 1;
		num = num * 4;
		System.out.println("subu $sp, $sp," + num);
		System.out.println("sw $ra, -4($fp)");
		n.f10.accept(this);
		int ni = num - 8;
		System.out.println("lw $ra, -4($fp)");

		System.out.println("lw $fp, " + ni + "($sp)");
		System.out.println("addu $sp, $sp, " + num);
		System.out.println("j $ra");

		n.f11.accept(this);
		return _ret;
	}

	/**
	 * f0 -> NoOpStmt() | ErrorStmt() | CJumpStmt() | JumpStmt() | HStoreStmt()
	 * | HLoadStmt() | MoveStmt() | PrintStmt() | ALoadStmt() | AStoreStmt() |
	 * PassArgStmt() | CallStmt()
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
		System.out.println("nop");
		return _ret;
	}

	/**
	 * f0 -> "ERROR"
	 */
	public R visit(ErrorStmt n) {
		R _ret = null;
		n.f0.accept(this);
		System.out.println("la $a0, str_er");
		System.out.println("syscall");

		return _ret;
	}

	/**
	 * f0 -> "CJUMP" f1 -> Reg() f2 -> Label()
	 */
	public R visit(CJumpStmt n) {
		R _ret = null;
		n.f0.accept(this);
		R reg = n.f1.accept(this);
		label = false;
		R lab = n.f2.accept(this);
		System.out.println("beqz " + reg + " " + lab);
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
		System.out.println("b " + lab);
		return _ret;
	}

	/**
	 * f0 -> "HSTORE" f1 -> Reg() f2 -> IntegerLiteral() f3 -> Reg()
	 */
	public R visit(HStoreStmt n) {
		R _ret = null;
		n.f0.accept(this);
		R reg1 = n.f1.accept(this);
		R intl = n.f2.accept(this);
		R reg2 = n.f3.accept(this);
		System.out.println("sw " + reg2 + "," + intl + "(" + reg1 + ")");

		return _ret;
	}

	/**
	 * f0 -> "HLOAD" f1 -> Reg() f2 -> Reg() f3 -> IntegerLiteral()
	 */
	public R visit(HLoadStmt n) {
		R _ret = null;
		n.f0.accept(this);
		R reg1 = n.f1.accept(this);
		R reg2 = n.f2.accept(this);
		R intl = n.f3.accept(this);
		System.out.println("lw " + reg1 + " " + intl + "( " + reg2 + ")");

		return _ret;
	}

	/**
	 * f0 -> "MOVE" f1 -> Reg() f2 -> Exp()
	 */
	public R visit(MoveStmt n) {
		R _ret = null;
		n.f0.accept(this);
		R reg1 = n.f1.accept(this);
		R reg2 = n.f2.accept(this);
		if (n.f2.f0.which == 0)
			System.out.println("move " + reg1 + " $v0");
		else if (n.f2.f0.which == 1)
			System.out.println(((String) reg2).split(";")[0] + " " + reg1
					+ ", " + ((String) reg2).split(";")[1]);
		else {
			if (((String) reg2).startsWith("$"))
				System.out.println("move " + reg1 + " " + reg2);
			else {
				try {
					Integer.parseInt((String) reg2);
					System.out.println("li " + reg1 + " " + reg2);
				} catch (Exception e) {
					System.out.println("la " + reg1 + " " + reg2);
				}
			}
		}
		return _ret;
	}

	/**
	 * f0 -> "PRINT" f1 -> SimpleExp()
	 */
	public R visit(PrintStmt n) {
		R _ret = null;
		n.f0.accept(this);
		R simexp = n.f1.accept(this);
		System.out.println("move $a0 " + simexp);
		System.out.println("jal _print");
		return _ret;
	}

	/**
	 * f0 -> "ALOAD" f1 -> Reg() f2 -> SpilledArg()
	 */
	public R visit(ALoadStmt n) {
		R _ret = null;
		n.f0.accept(this);
		R reg = n.f1.accept(this);
		R spld = n.f2.accept(this);
		int num = Integer.parseInt((String) spld);
		num = num * 4;
		if (((String) reg).equals("$v0") || ((String) reg).equals("$v1"))
			System.out.println("lw " + reg + ", " + num + "($fp)");
		else
			System.out.println("lw " + reg + ", " + num + "($sp)");
		return _ret;
	}

	/**
	 * f0 -> "ASTORE" f1 -> SpilledArg() f2 -> Reg()
	 */
	public R visit(AStoreStmt n) {
		R _ret = null;
		n.f0.accept(this);
		R intl = n.f1.accept(this);
		R reg = n.f2.accept(this);
		int num = Integer.parseInt((String) intl);
		num = num * 4;
		System.out.println("sw " + reg + ", " + num + "($sp)");

		return _ret;
	}

	/**
	 * f0 -> "PASSARG" f1 -> IntegerLiteral() f2 -> Reg()
	 */
	public R visit(PassArgStmt n) {
		R _ret = null;
		n.f0.accept(this);
		R intl = n.f1.accept(this);
		R reg = n.f2.accept(this);
		int num = Integer.parseInt((String) intl) - 1;
		num = num * 4;
		System.out.println("sw " + reg + ", " + num + "($sp)");

		return _ret;
	}

	/**
	 * f0 -> "CALL" f1 -> SimpleExp()
	 */
	public R visit(CallStmt n) {
		R _ret = null;
		n.f0.accept(this);
		R simexp = n.f1.accept(this);
		System.out.println("jalr " + simexp);
		return _ret;
	}

	/**
	 * f0 -> HAllocate() | BinOp() | SimpleExp()
	 */
	public R visit(Exp n) {
		R _ret = null;
		_ret = n.f0.accept(this);
		return _ret;
	}

	/**
	 * f0 -> "HALLOCATE" f1 -> SimpleExp()
	 */
	public R visit(HAllocate n) {
		R _ret = null;
		n.f0.accept(this);
		R simexp = n.f1.accept(this);
		if (((String) simexp).startsWith("$"))
			System.out.println("move $a0 " + simexp);
		else
			System.out.println("li $a0 " + simexp);
		System.out.println("jal _halloc");

		return _ret;
	}

	/**
	 * f0 -> Operator() f1 -> Reg() f2 -> SimpleExp()
	 */
	public R visit(BinOp n) {
		R _ret = null;
		R op = n.f0.accept(this);
		R reg1 = n.f1.accept(this);
		R reg2 = n.f2.accept(this);
		_ret = (R) (op + "; " + reg1 + " , " + reg2);
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
			return (R) "slt";
		case 1:
			return (R) "add";
		case 2:
			return (R) "sub";
		case 3:
			return (R) "mul";

		}
		return _ret;
	}

	/**
	 * f0 -> "SPILLEDARG" f1 -> IntegerLiteral()
	 */
	public R visit(SpilledArg n) {
		R _ret = null;
		n.f0.accept(this);
		_ret = n.f1.accept(this);
		return _ret;
	}

	/**
	 * f0 -> Reg() | IntegerLiteral() | Label()
	 */
	public R visit(SimpleExp n) {
		R _ret = null;
		label = false;
		_ret = n.f0.accept(this);
		return _ret;
	}

	/**
	 * f0 -> "a0" | "a1" | "a2" | "a3" | "t0" | "t1" | "t2" | "t3" | "t4" | "t5"
	 * | "t6" | "t7" | "s0" | "s1" | "s2" | "s3" | "s4" | "s5" | "s6" | "s7" |
	 * "t8" | "t9" | "v0" | "v1"
	 */
	public R visit(Reg n) {
		R _ret = null;
		_ret = n.f0.accept(this);
		switch (n.f0.which) {
		case 0:
			return (R) "$a0";
		case 1:
			return (R) "$a1";
		case 2:
			return (R) "$a2";
		case 3:
			return (R) "$a3";
		case 4:
			return (R) "$t0";
		case 5:
			return (R) "$t1";
		case 6:
			return (R) "$t2";
		case 7:
			return (R) "$t3";
		case 8:
			return (R) "$t4";
		case 9:
			return (R) "$t5";
		case 10:
			return (R) "$t6";
		case 11:
			return (R) "$t7";
		case 12:
			return (R) "$s0";
		case 13:
			return (R) "$s1";
		case 14:
			return (R) "$s2";
		case 15:
			return (R) "$s3";
		case 16:
			return (R) "$s4";
		case 17:
			return (R) "$s5";
		case 18:
			return (R) "$s6";
		case 19:
			return (R) "$s7";
		case 20:
			return (R) "$t8";
		case 21:
			return (R) "$t9";
		case 22:
			return (R) "$v0";
		case 23:
			return (R) "$v1";
		}
		return _ret;
	}

	/**
	 * f0 -> <INTEGER_LITERAL>
	 */
	public R visit(IntegerLiteral n) {
		R _ret = null;
		n.f0.accept(this);
		return (R) n.f0.tokenImage;
	}

	/**
	 * f0 -> <IDENTIFIER>
	 */
	public R visit(Label n) {
		R _ret = null;
		if (label) {
			System.out.println(n.f0.tokenImage + ": ");
		}
		label = true;
		n.f0.accept(this);
		return (R) n.f0.tokenImage;
	}

}
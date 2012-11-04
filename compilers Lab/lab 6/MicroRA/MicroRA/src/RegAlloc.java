/*
 * Author : Karthik Abinav
 * Roll Number : CS10B057
 * References: Liveness Analysis , Linear Scan Algorithm (Class Slides,Wikipedia) 
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Set;
import java.util.Vector;

import MainPackage.AliasTable;
import MainPackage.ControlFlowNode;
import MainPackage.Debugger;
import MainPackage.LiveRange;
import MainPackage.Pair;
import MainPackage.PairLiveRange;
import MainPackage.SymbolTable;

import syntaxtree.*;
import visitor.*;

class Main {
	public static void main(String[] args) throws FileNotFoundException {
		try {
			Node root = new microIRParser(new FileInputStream(
					"../../Tests/microIR/SLinearSearch.microIR")).Goal();

			// Node root = new microIRParser(System.in).Goal();
			root.accept(new ExtractFunctions(), null);
			root.accept(new ChangeLocalsToGlobal(), null);
			Debugger.printAliases();
			root.accept(new GJNoArguDepthFirst());

			SymbolTable.connectLabels();
			SymbolTable.getSuccessors();
			SymbolTable.populateNodes();

			// Debugger.printFlowGraph();

			SymbolTable.livenessAnalysis();
			SymbolTable.getLiveRanges();
			SymbolTable.linearScan();
			SymbolTable.populateRegisterMap();

			// root.accept(new MiniRAPrint());

			// Debugger.printVariableRegisterMap();
			Debugger.printLiveRangesSorted();
			Debugger.printRegisters();
			// Debugger.printStack();
			System.out.println(Debugger.clashingAllocation());
		} catch (ParseException e) {
			System.out.println(e.toString());
		}
	}
}

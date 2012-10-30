package MainPackage;

import java.util.Vector;

public class ControlFlowNode {

	public String typeOfInstruction; // Name of instruction
	public Vector<String> defined = new Vector<String>(); // Temps defined in
															// this node
	public Vector<String> used = new Vector<String>(); // Temps used in this
														// node

	public Vector<Integer> inNodes = new Vector<Integer>();
	public Vector<Integer> outNodes = new Vector<Integer>();

	public Vector<String> liveIn = new Vector<String>();
	public Vector<String> liveOut = new Vector<String>();

}

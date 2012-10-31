package MainPackage;

import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

public class SymbolTable {

	public static HashMap<ControlFlowNode, Integer> nodeToVertex = new HashMap<ControlFlowNode, Integer>();
	public static HashMap<Integer, ControlFlowNode> vertexToNode = new HashMap<Integer, ControlFlowNode>();
	public static HashMap<String, LiveRange> liveRanges = new HashMap<String, LiveRange>();

	public static Vector<Pair> nodeList = new Vector<Pair>();

	public void insert(ControlFlowNode N, Integer I) {
		nodeToVertex.put(N, I);
		vertexToNode.put(I, N);
	}

	public int getVertexFromNode(ControlFlowNode N) {
		if (nodeToVertex.containsKey(N))
			return nodeToVertex.get(N);

		return -1; // -1 indicates that the vertex is not present
	}

	public ControlFlowNode getNodeFromVertex(Integer I) {
		if (vertexToNode.containsKey(I))
			return vertexToNode.get(I);

		return null;
	}

	public static void populateNodes() {
		Set<java.util.Map.Entry<ControlFlowNode, Integer>> e = nodeToVertex
				.entrySet();
		for (java.util.Map.Entry<ControlFlowNode, Integer> i : e) {
			nodeList.add(new Pair(i.getKey(), i.getValue()));
		}
	}

	// Connects the edges between jump statements
	public static void connectLabels() {
		Set<java.util.Map.Entry<ControlFlowNode, Integer>> i1 = nodeToVertex
				.entrySet();
		Set<java.util.Map.Entry<ControlFlowNode, Integer>> i2 = nodeToVertex
				.entrySet();

		for (java.util.Map.Entry<ControlFlowNode, Integer> i : i1) {
			if (!i.getKey().toLabel.equals("")) {
				String lab = i.getKey().toLabel;
				for (java.util.Map.Entry<ControlFlowNode, Integer> j : i2) {
					if (j.getKey().currentLabel.equals(lab)) {
						// i.getKey().succ.add(j.getValue());
						j.getKey().pre.add(i.getValue());
					}
				}
			}
		}
	}

	// Gets the successors of all the nodes from the predeccessors of the nodes
	public static void getSuccessors() {
		Set<java.util.Map.Entry<ControlFlowNode, Integer>> i1 = nodeToVertex
				.entrySet();
		Set<java.util.Map.Entry<ControlFlowNode, Integer>> i2 = nodeToVertex
				.entrySet();

		for (java.util.Map.Entry<ControlFlowNode, Integer> i : i1) {
			for (Integer succ : i.getKey().pre) {
				for (java.util.Map.Entry<ControlFlowNode, Integer> j : i2) {
					if (j.getValue().equals(succ))
						j.getKey().succ.add(i.getValue());
				}
			}

		}

	}

	// Performs Set difference A - B
	public static Vector<String> setDifference(Vector<String> A,
			Vector<String> B) {
		Vector<String> result = new Vector<String>();
		for (String s : A) {
			if (!B.contains(s))
				result.add(s);
		}
		return result;

	}

	// Performs set union A U B
	public static Vector<String> setUnion(Vector<String> A, Vector<String> B) {
		Vector<String> result = new Vector<String>();
		for (String s : A) {
			result.add(s);
		}
		for (String s : B) {
			if (!result.contains(s))
				result.add(s);
		}
		return result;
	}

	// Checks for the termination condition of the outer liveness analysis loop
	public static boolean noChange() {
		for (Pair N : nodeList) {
			ControlFlowNode n = N.first;
			if (!n.liveIn.equals(n.inPrime) && !n.liveOut.equals(n.outPrime))
				return false;
		}
		return true;
	}

	public static void livenessAnalysis() {
		for (Pair N : nodeList) {
			if (N.second == 0)
				N.first.liveOut.addAll(N.first.defined);
		}
		do {

			for (Pair N : nodeList) {

				ControlFlowNode n = N.first;

				// in'[n]<-in[n]
				n.inPrime.removeAllElements();
				n.inPrime.addAll(n.liveIn);

				// out'[n]<-out[n]
				n.outPrime.removeAllElements();
				n.outPrime.addAll(n.liveOut);

				// in[n] ← use[n] ∪ (out[n] − def [n]);
				n.liveIn.removeAllElements();
				n.liveIn.addAll(setUnion(n.used,
						setDifference(n.liveOut, n.defined)));

				// out[n] ← U(s∈succ[n]) in[s] ;
				n.liveOut.removeAllElements();
				for (Integer I : n.succ) {
					ControlFlowNode successor = new ControlFlowNode();
					for (Pair iter : nodeList) {
						if (iter.second.equals(I)) {
							successor = iter.first;
							break;
						}
					}
					n.liveOut.addAll(setUnion(n.liveOut, successor.liveIn));

				}

			}
		} while (!noChange());

	}

	public static void getLiveRanges() {
		Set<java.util.Map.Entry<ControlFlowNode, Integer>> nodes = nodeToVertex
				.entrySet();
		Set<java.util.Map.Entry<String, LiveRange>> variables = liveRanges
				.entrySet();
		for (java.util.Map.Entry<String, LiveRange> var : variables) {
			int start = -1, end = -1; // unset initially
			String temp = var.getKey();
			for (java.util.Map.Entry<ControlFlowNode, Integer> N : nodes) {
				ControlFlowNode n = N.getKey();
				if (n.liveOut.contains(temp)) {
					if (start == -1) {
						start = N.getValue();
					} else if (N.getValue() < start)
						start = N.getValue();
				}
				if (n.liveIn.contains(temp)) {
					if (end == -1) {
						end = N.getValue();
					} else if (N.getValue() > end)
						end = N.getValue();
				}
			}
			var.getValue().start = start;
			var.getValue().end = end;
		}

	}

	public static void sort() {
		int i, j;
		for (i = 0; i < nodeList.size(); i++) {
			for (j = i + 1; j < nodeList.size(); j++) {
				// if(nodeList.get(i).first)
			}
		}
	}

	public static void linearScan() {
		sort();
	}

	public static void sync() {
		nodeToVertex = new HashMap<ControlFlowNode, Integer>();
		vertexToNode = new HashMap<Integer, ControlFlowNode>();
		for (Pair p : nodeList) {
			nodeToVertex.put(p.first, p.second);
			vertexToNode.put(p.second, p.first);
		}
	}
}

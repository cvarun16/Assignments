import java.util.HashMap;

public class SymbolTable {

	public static HashMap<ControlFlowNode, Integer> nodeToVertex = new HashMap<ControlFlowNode, Integer>();
	public static HashMap<Integer, ControlFlowNode> vertexToNode = new HashMap<Integer, ControlFlowNode>();

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

}

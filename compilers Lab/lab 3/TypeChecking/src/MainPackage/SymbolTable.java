package MainPackage;

import java.util.HashMap;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Map.Entry;

import visitor.GJNoArguDepthFirst_Parse2;

public class SymbolTable {
	public static String currentClass;
	public static String currentFunction;
	public static int CIDCounter = 1; // Counter for assigning class IDs

	public HashMap<String, Object> mainTable = new HashMap<String, Object>();

	// Variables for detcting multi level inheritance
	public static boolean needsTransitive = false;
	public HashMap<Integer, String> ClassID = new HashMap<Integer, String>();
	public HashMap<String, Integer> RClassID = new HashMap<String, Integer>();
	public int graph[][] = new int[100][100]; // Assuming no more than

	// 100

	// classes present in the file

	public void push(String key, Object value) {
		this.mainTable.put(key, value);
	}

	public void setID(String className, int id) {
		this.ClassID.put(id, className);
		this.RClassID.put(className, id);
	}

	public int getID(String className) {
		return this.RClassID.get(className);
	}

	public void findTransitiveClosure() {
		int i, j, k;
		for (k = 1; k <= CIDCounter; k++) {
			for (i = 1; i <= CIDCounter; i++) {
				for (j = 1; j <= CIDCounter; j++) {
					if (graph[i][k] == 1 && graph[k][j] == 1)
						graph[i][j] = 1;
				}
			}
		}
		for (i = 1; i <= CIDCounter; i++) {
			for (j = 1; j <= CIDCounter; j++) {
				if (graph[i][j] == 1) {

					// Iterate through the functions and variables of j and add
					// it to i
					String classi = this.ClassID.get(i);
					String classj = this.ClassID.get(j);

					Set<Entry<String, Object>> allElements = this.mainTable
							.entrySet();
					boolean present = false;
					for (Entry<String, Object> s : allElements) {
						String key = s.getKey();
						String values[] = key.split("\\s+");

						if (values[2] == classj) {
							String hashString = hashString(values[0],
									values[1], classi, values[3]);
							
							Object oldClassObj = s.getValue();
							if (oldClassObj instanceof Class)
								continue;
							
							//Check if it is violating overriding conditions
							if (mainTable.containsKey(hashString)) {
								Object o = query(hashString);
								if (o instanceof FunctionClass
										&& oldClassObj instanceof FunctionClass) {
									FunctionClass F1 = (FunctionClass) o;
									FunctionClass F2 = (FunctionClass) oldClassObj;

									if (!F1.equals(F2))
										GJNoArguDepthFirst_Parse2.Exit();
								}
								if (o instanceof VariableClass
										&& oldClassObj instanceof VariableClass) {
									VariableClass F1 = (VariableClass) o;
									VariableClass F2 = (VariableClass) oldClassObj;

									if (!F1.equals(F2))
										GJNoArguDepthFirst_Parse2.Exit();
								}
							}

							push(hashString, s.getValue());
						}

					}
					if (!present) {
						GJNoArguDepthFirst_Parse2.Exit();
					}

				}
			}
		}
	}

	public boolean hasId(String className) {
		return this.ClassID.containsKey(className);
	}

	public Object query(String key) {
		if (this.mainTable.containsKey(key)) {
			return this.mainTable.get(key);
		}
		return null;
	}

	public String hashString(String type, String name, String classScope,
			String functionScope) {

		return type + " " + name + " " + classScope + " " + functionScope;
	}
}

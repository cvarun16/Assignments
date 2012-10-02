package MainPackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;

import visitor.GJNoArguDepthFirst_Parse2;

public class SymbolTable {
	public static String currentClass;
	public static String currentFunction;
	public static int CIDCounter = 1; // Counter for assigning class IDs

	public static int maxTempNumber = 0;

	public static HashMap<String, Object> mainTable = new HashMap<String, Object>();

	// Variables for detcting multi level inheritance
	public static boolean needsTransitive = false;
	public static HashMap<Integer, String> ClassID = new HashMap<Integer, String>();
	public static HashMap<String, Integer> RClassID = new HashMap<String, Integer>();
	public static int graph[][] = new int[100][100]; // Assuming no more than

	public static HashMap<String, ArrayList<String>> Alias = new HashMap<String, ArrayList<String>>();

	public void push(String key, Object value) {
		SymbolTable.mainTable.put(key, value);
	}

	public void setID(String className, int id) {
		SymbolTable.ClassID.put(id, className);
		SymbolTable.RClassID.put(className, id);
	}

	public int getID(String className) {
		return SymbolTable.RClassID.get(className);
	}

	public void FloyddWarshall() {
		int i, j, k;
		for (k = 1; k <= CIDCounter; k++) {
			for (i = 1; i <= CIDCounter; i++) {
				for (j = 1; j <= CIDCounter; j++) {
					if (graph[i][k] == 1 && graph[k][j] == 1) {
						graph[i][j] = 1;
					}
				}
			}
		}
	}

	private boolean AliasedComparision(String s1, String s2) {
		if (s1.equals(s2))
			return true;
		else {
			ArrayList<String> listOfAlias = new ArrayList<String>();
			listOfAlias = SymbolTable.Alias.get(s2);

			if (listOfAlias == null)
				return false;
			else
				return listOfAlias.contains(s1);

		}

	}

	private boolean variableEqual(VariableClass v1, VariableClass v2) {
		if (!(v1.name.equals(v2.name)))
			return false;
		if (!(v1.type.equals(v2.type)))
			return false;
		return true;
	}

	private boolean functionEqual(FunctionClass v1, FunctionClass v2) {
		if (!AliasedComparision(v1.retType, v2.retType))
			return false;
		int i;
		if (v1.formalParamList.size() != v2.formalParamList.size())
			return false;
		for (i = 0; i < v1.formalParamList.size(); i++) {
			if (!AliasedComparision(v1.formalParamList.get(i).type,
					v2.formalParamList.get(i).type))
				return false;
		}

		return true;
	}

	public void findTransitiveClosure() {
		FloyddWarshall();
		int i, j;

		for (i = 1; i <= CIDCounter; i++) {
			for (j = 1; j <= CIDCounter; j++) {

				if (graph[i][j] == 1) {
					String classi = SymbolTable.ClassID.get(i);
					String classj = SymbolTable.ClassID.get(j);

					if (!Alias.containsKey(classj)) {
						ArrayList<String> temp = new ArrayList<String>();
						temp.add(classi);
						Alias.put(classj, temp);
					} else {
						Alias.get(classj).add(classi);
					}
				}

			}
		}

		for (i = 1; i <= CIDCounter; i++) {
			for (j = 1; j <= CIDCounter; j++) {
				if (graph[i][j] == 1) {

					// Iterate through the functions and variables of j and add
					// it to i
					String classi = SymbolTable.ClassID.get(i);
					String classj = SymbolTable.ClassID.get(j);

					Set<Entry<String, Object>> allElements = SymbolTable.mainTable
							.entrySet();

					HashMap<String, Object> tempMap = new HashMap<String, Object>();

					boolean present = false;
					for (Entry<String, Object> s : allElements) {
						String key = s.getKey();
						String values[] = key.split("\\s+");
						if (values[2].equals(classj)) {
							present = true;
							String hashString = hashString(values[0],
									values[1], classi, values[3]);

							Object oldClassObj = s.getValue();
							if (values[0].equals("class"))
								continue;

							// Check if it is violating overloading conditions
							if (mainTable.containsKey(hashString)) {
								Object o = query(hashString);
								if (o instanceof FunctionClass
										&& oldClassObj instanceof FunctionClass) {
									FunctionClass F1 = (FunctionClass) o;
									FunctionClass F2 = (FunctionClass) oldClassObj;

									tempMap.put(hashString, s.getValue());
								}
								if (o instanceof VariableClass
										&& oldClassObj instanceof VariableClass) {
									VariableClass F1 = (VariableClass) o;
									VariableClass F2 = (VariableClass) oldClassObj;

									tempMap.put(hashString, s.getValue());
								}

							}
							tempMap.put(hashString, s.getValue());

						}
					}

					Set<Entry<String, Object>> tempElement = tempMap.entrySet();
					for (Entry<String, Object> s : tempElement) {
						push(s.getKey(), s.getValue());
					}

				}
			}
		}
	}

	public boolean hasId(String className) {
		return SymbolTable.RClassID.containsKey(className);
	}

	public Object query(String key) {
		if (SymbolTable.mainTable.containsKey(key)) {
			return SymbolTable.mainTable.get(key);
		}
		return null;
	}

	public String hashString(String type, String name, String classScope,
			String functionScope) {

		return type + " " + name + " " + classScope + " " + functionScope;
	}
}
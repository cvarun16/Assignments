package MainPackage;

import java.util.HashMap;

public class AliasTable {
	public static HashMap<String, String> IRtoRA = new HashMap<String, String>();
	public static HashMap<String, String> RAtoIR = new HashMap<String, String>();

	public static void insert(String N, String I) {
		IRtoRA.put(N, I);
		RAtoIR.put(I, N);
	}

	public static String getRAFromIR(String IR) {
		if (IRtoRA.containsKey(IR))
			return IRtoRA.get(IR);

		return null;
	}

	public static String getIRFromRA(String RA) {
		if (RAtoIR.containsKey(RA))
			return RAtoIR.get(RA);

		return null;
	}

}

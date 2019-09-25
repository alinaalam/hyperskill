
public class PrefixFunction {
	
	public static int[] prefixCalculator(String str) {
		int[] prefixArray = new int[str.length()];
		
		// first index is always 0, so we'll start with 1
		for (int i = 1; i < prefixArray.length; i++) {
			int j = prefixArray[i - 1];
			
			while (j > 0) {
				if (str.charAt(i) == str.charAt(j)) {
					prefixArray[i] = j + 1;
				}
				j = prefixArray[j - 1];
			}
			
			if (prefixArray[i] == 0) {
				if (str.charAt(i) == str.charAt(j)) {
					prefixArray[i] = j + 1;
				}
			}
		}
		
		return prefixArray;
	}
	
	private static void printArray(int[] prefixArray) {
		for (int i : prefixArray) {
			System.out.print(i + " ");
		}
	}
	
	private static void printStr(String str) {
		for (char c : str.toCharArray()) {
			System.out.print(c + " ");
		}
	}
	
	public static void main(String[] args) {
		String str = "ABACABAD";
		int[] prefixArray = prefixCalculator(str);
		printStr(str);
		System.out.println();
		printArray(prefixArray);
		
		System.out.println();
		
		str = "ACCABACCAC";
		prefixArray = prefixCalculator(str);
		printStr(str);
		System.out.println();
		printArray(prefixArray);
		
		System.out.println();
		
		str = "ABACABAD";
		prefixArray = prefixCalculator(str);
		printStr(str);
		System.out.println();
		printArray(prefixArray);
	}
}

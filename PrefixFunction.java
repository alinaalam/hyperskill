
public class PrefixFunction {
	private static int[] prefixArray;
	private static String longestBorder = "";
	
	private static void prefixCalculator(String str) {
		prefixArray = new int[str.length()];
		longestBorder = "";
		
		// first index is always 0, so we'll start with 1
		for (int i = 1; i < prefixArray.length; i++) {
			
			if (longestBorder.isEmpty()) {
				if (str.charAt(0) == str.charAt(i)) {
					prefixArray[i] = 1;
					longestBorder += str.charAt(i);
				}
			}
			
			else {
				if (longestBorder.charAt(longestBorder.length() - 1) == str.charAt(i)) {
					prefixArray[i] = 1;
				}
				// extend the border
				if (str.charAt(longestBorder.length()) == str.charAt(i)) {
					longestBorder += str.charAt(i);
					prefixArray[i] = longestBorder.length();
				}
			}
		}
	}
	
	private static void printArray() {
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
		prefixCalculator(str);
		printStr(str);
		System.out.println();
		printArray();
		
		System.out.println();
		
		str = "ACCABACCAC";
		prefixCalculator(str);
		printStr(str);
		System.out.println();
		printArray();
	}
}

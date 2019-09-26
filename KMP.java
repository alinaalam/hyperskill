import java.util.*;

public class KMP {
	
	private static boolean isMatch(String text, String pattern, int[] prefixArray) {
		int i = 0;
		int j = 0;
		
		while (i < text.length()) {
			boolean isMatch = true;
			for (; j < pattern.length(); j++, i++) {
				if (text.charAt(i) != pattern.charAt(j)) {
					j = prefixArray[j - 1];
					isMatch = false;
					break;
				}
			}
			if (isMatch) {
				return true;
			}
		}
		
		return false;
	}
	
	private static List<Integer> occurences(String text, String pattern, int[] prefixArray) {
		List<Integer> occurencesList = new ArrayList<>();
		
		int j = 0;
		
		for (int i = 0; i < text.length(); i++) {
			while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
				j = prefixArray[j - 1];
			}
			if (text.charAt(i) == pattern.charAt(j)) {
				j++;
			}
			if (j == pattern.length()) {
				occurencesList.add(i - pattern.length() + 1);
				j = prefixArray[j - 1];
			}
		}
		
		return occurencesList;
	}
	
	private static void printList(List<Integer> list) {
		for (int i : list) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		String text = "BACBACBAD";
		String pattern = "BACBAD";
		
		// calculate prefix function for the pattern
		int[] prefixArray = PrefixFunction.prefixCalculator(pattern);
		
		System.out.println(isMatch(text, pattern, prefixArray));
		
		printList(occurences(text, pattern, prefixArray));
		
		text = "ABCABCAABCABD";
		pattern = "ABCABD";
		
		// calculate prefix function for the pattern
		prefixArray = PrefixFunction.prefixCalculator(pattern);
		
		System.out.println(isMatch(text, pattern, prefixArray));
		
		text = "ABACABAD";
		pattern = "ABA";
		
		// calculate prefix function for the pattern
		prefixArray = PrefixFunction.prefixCalculator(pattern);
		
		printList(occurences(text, pattern, prefixArray));
		
		text = "ABABA";
		pattern = "ABA";
		
		// calculate prefix function for the pattern
		prefixArray = PrefixFunction.prefixCalculator(pattern);
		
		printList(occurences(text, pattern, prefixArray));
	}
}

import java.util.*;

public class Matrix {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
	
		String[] patternMatrix = new String[Integer.parseInt(scanner.nextLine().split(" ")[0])];
		
		for (int i = 0; i < patternMatrix.length; i++) {
			patternMatrix[i] = scanner.nextLine();
		}
		
		String[] textMatrix = new String[Integer.parseInt(scanner.nextLine().split(" ")[0])];
		
		for (int i = 0; i < textMatrix.length; i++) {
			textMatrix[i] = scanner.nextLine();
		}
		long t = System.currentTimeMillis();
		System.out.println(findOccurrences(textMatrix, patternMatrix));
		System.out.println("Time: " + (System.currentTimeMillis() - t));
		
	}
	
	private static int findOccurrences(String[] textMatrix, String[] patternMatrix) {
		int occurrences = 0;
		
		for (int i = 0; i < textMatrix.length; i++) {
			
			// check if you have appropriate remaining rows
			if (textMatrix.length - 1 - i < patternMatrix.length - 1) {
				break;
			}
			
			// check if you can find the first row
			List<Integer> indices = occurrences(textMatrix[i], patternMatrix[0]);
			
			if (indices.isEmpty()) {
				continue;
			}
			
			for (int j = i + 1, a = 1; a <= patternMatrix.length - 1; j++, a++) {
				
				if (textMatrix[j].equals(textMatrix[j - 1]) && patternMatrix[a].equals(patternMatrix[a - 1])) {
					continue;
				}
				
				Iterator<Integer> iterator = indices.iterator();
				while (iterator.hasNext()) {
					int index = iterator.next();
					if (!textMatrix[j].substring(index, index + patternMatrix[a].length()).equals(patternMatrix[a])) {
						iterator.remove();
					}
				}
				
				if (indices.isEmpty()) {
					break;
				}
			}
			
			occurrences += indices.size();
		}
		return occurrences;
	}
	
	private static int[] prefixFunction(String str) {
	    /* 1 */
	    int[] prefixFunc = new int[str.length()];
	 
	    /* 2 */
	    for (int i = 1; i < str.length(); i++) {
	        /* 3 */
	        int j = prefixFunc[i - 1];
	 
	        while (j > 0 && str.charAt(i) != str.charAt(j)) {
	            j = prefixFunc[j - 1];
	        }
	 
	        /* 4 */
	        if (str.charAt(i) == str.charAt(j)) {
	            j += 1;
	        }
	 
	        /* 5 */
	        prefixFunc[i] = j;
	    }
	 
	    /* 6 */
	    return prefixFunc;
	}
	
	private static List<Integer> occurrences(String text, String pattern) {
		int[] prefixArray = prefixFunction(pattern);
		List<Integer> occurrencesList = new ArrayList<>();
		
		int j = 0;
		
		for (int i = 0; i < text.length(); i++) {
			while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
				j = prefixArray[j - 1];
			}
			if (text.charAt(i) == pattern.charAt(j)) {
				j++;
			}
			if (j == pattern.length()) {
				occurrencesList.add(i - pattern.length() + 1);
				j = prefixArray[j - 1];
			}
		}
		
		return occurrencesList;
	}
}

import java.util.Scanner;
import java.util.*;

public class MultiplePatternMatcher {
	private static int numOfOccurrences = 0;
	private static List<Integer> indices = new ArrayList<Integer>();
			
	public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String pattern = scanner.nextLine();
        String text = scanner.nextLine();

        getMultiplePatternMatchers(pattern, text);
        
        System.out.println(numOfOccurrences);
        printIndices();
    }

    private static void getMultiplePatternMatchers(String pattern, String text) {
    	
        if (pattern.length() > text.length()) {
            return;
        }
        
        if (pattern.isEmpty() && text.isEmpty()) {
        	numOfOccurrences++;
    		indices.add(0);
    		return;
        }
        
        if (pattern.isEmpty()) {
        	for (int i = 0; i < text.length(); i++) {
        		numOfOccurrences++;
        		indices.add(i);
        	}
        	return;
        }

        for (int i = 0; i <= text.length() - pattern.length();) {
            boolean patternFound = true;

            for (int j = 0; j < pattern.length(); j++) {
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    patternFound = false;
                    break;
                }
            }

            if (patternFound) {
            	numOfOccurrences++;
            	indices.add(i);
            	i += pattern.length();
            }
            
            else {
            	i++;
            }
        }
    }

    private static void printIndices() {
    	for (int i : indices) {
    		System.out.print(i + " ");
    	}
    }
}

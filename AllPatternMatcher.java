import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AllPatternMatcher {
	private static List<Integer> indices = new ArrayList<Integer>();
			
	public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String pattern = scanner.nextLine();
        String text = scanner.nextLine();

        getMultiplePatternMatchers(pattern, text);
        
        System.out.println(indices.size());
        printIndices();
    }

    private static void getMultiplePatternMatchers(String pattern, String text) {
    	
        if (pattern.length() > text.length()) {
            return;
        }
        
        if (pattern.isEmpty() && text.isEmpty()) {
    		indices.add(0);
    		return;
        }
        
        if (pattern.isEmpty()) {
        	for (int i = 0; i < text.length(); i++) {
        		indices.add(i);
        	}
        	return;
        }

        for (int i = 0; i <= text.length() - pattern.length(); i++) {
            boolean patternFound = true;

            for (int j = 0; j < pattern.length(); j++) {
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    patternFound = false;
                    break;
                }
            }

            if (patternFound) {
            	indices.add(i);
            }
        }
    }

    private static void printIndices() {
    	for (int i : indices) {
    		System.out.print(i + " ");
    	}
    }
}

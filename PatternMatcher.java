
import java.util.Scanner;

public class PatternMatcher {
	public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String pattern = scanner.nextLine();
        String text = scanner.nextLine();

        System.out.println(getStartOfPattern(pattern, text));
    }

    private static int getStartOfPattern(String pattern, String text) {
        int index = -1;

        if (pattern.length() > text.length()) {
            return index;
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
                return i;
            }
        }

        return index;
    }
}

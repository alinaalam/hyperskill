import java.util.Scanner;

class Ascending {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int length = scanner.nextInt();
        
        int[] arr = new int[length];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = scanner.nextInt();
        }

        int sequence = 0;
        int innerSequence = 0;
        int i = 1;
        boolean isCompared = false;

        // 1 2 4 1 2 3 5 7 4 3 - 10
        while (i < length) {
            if (arr[i] > arr[i - 1]) {
                innerSequence++;
                isCompared = false;
            }
            else {
                if (innerSequence > 0 ) {
                    innerSequence++;
                }
                if (innerSequence > sequence) {
                    sequence = innerSequence;
                    innerSequence = 0;
                }
                isCompared = true;
            }
            i++;
        }
        
        if (!isCompared) {
        	if (innerSequence > 0 ) {
                innerSequence++;
            }
            if (innerSequence > sequence) {
                sequence = innerSequence;
                innerSequence = 0;
            }
        }

        System.out.println(sequence);
    }
}
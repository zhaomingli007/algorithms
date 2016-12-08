package interview.str;

/**
 * Created by zhao on 12/8/16.
 */
public class StringWordInverse {
    public static void main(String[] args) {
        //Test reverse method.
        StringWordInverse inverseInst = new StringWordInverse();
        char[] input = "this is my first interveiw question".toCharArray();
        inverseInst.reverse(input, 0, input.length - 1);
        System.out.println(input);

        //Test reverse string
        String strReversed = inverseInst.reverseWordsInString("What's your name");
        System.out.println(strReversed);
    }

    /**
     * Reverse a character input given start and end index
     *
     * @param input    The character input to be reversed.
     * @param startIdx the index from which the head of string will be swapped.
     * @param endIdx   the index to which the tail of string will be swapped.
     */
    private void reverse(char[] input, int startIdx, int endIdx) {
        for (int head = startIdx, tail = endIdx; head < tail; head++, tail--) {
            char tmpSpace = input[head];
            input[head] = input[tail];
            input[tail] = tmpSpace;
        }
    }

    /**
     * @param wordsToReverse A string with its words returned in reverse order.
     * @return The string with its words reversed.
     */
    public String reverseWordsInString(String wordsToReverse) {
        //1. Reverse whole string
        char[] wordsInCharArray = wordsToReverse.toCharArray();
        reverse(wordsInCharArray, 0, wordsInCharArray.length - 1);
        //2. Reverse each word
        int charLength = wordsInCharArray.length;
        int head = 0;
        for (int tail = head; tail < charLength; tail++) {
            if (wordsInCharArray[tail] == ' ') {
                //Word terminator
                reverse(wordsInCharArray, head, tail - 1);
                head = tail + 1;
            }
            if (tail == charLength - 1) {
                //Last words has no space in the end
                reverse(wordsInCharArray, head, tail );
            }
        }
        return String.valueOf(wordsInCharArray);
    }
}
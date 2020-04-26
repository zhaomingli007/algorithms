package algorithm.leetcode.rnd1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class FindFirstMissingPositiveTest {

    @Test
    public void firstMissingPositive() {
        FindFirstMissingPositive fmp = new FindFirstMissingPositive();
        int mp = fmp.firstMissingPositive(new int[]{7, 8, 9, 11, 12});
        assertEquals(1,mp);
        int mp2 = fmp.firstMissingPositive(new int[]{1,2,0});
        assertEquals(3,mp2);


        int mp4 = fmp.firstMissingPositive(new int[]{-5});
        assertEquals(1,mp4);

        int mp3 = fmp.firstMissingPositive(new int[]{3,4,-1,1});
        assertEquals(2,mp3);

    }
}
package algorithm.leetcode.rnd1;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SearchInRotatedSortedArrayTest {

    @Test
    public void search() {
        SearchInRotatedSortedArray seartSorted = new SearchInRotatedSortedArray();
        int pos = seartSorted.search(new int[]{4,5,6,7,0,1,2},7,0);
        assertEquals(4,pos);
        int pos2 = seartSorted.search(new int[]{4,5,6,7,0,1,2},7,3);
        assertEquals(-1,pos2);

    }
}
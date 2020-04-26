package algorithm.leetcode.rnd1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;


public class CombinationSumTest {

    @Test
    public void combinationSum() {
        CombinationSum cs = new CombinationSum();
        List<List<Integer>> solutions = cs.combinationSum(new int[] {2,3,6,7}, 7 );
        List<List<Integer>> expect = new ArrayList<>();
        expect.add(Arrays.asList(2,2,3));
        expect.add(Arrays.asList(7));
        assertIterableEquals(expect, solutions);
    }
}
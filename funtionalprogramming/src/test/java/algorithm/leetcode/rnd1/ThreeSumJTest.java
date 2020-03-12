package algorithm.leetcode.rnd1;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ThreeSumJTest {

    @Test
    void threeSum() {
        ThreeSumJ tsum = new ThreeSumJ();
        List<List<Integer>> res = tsum.threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        List<List<Integer>> exp1 = new LinkedList<>();
        exp1.add(Arrays.asList(-1, -1, 2));
        exp1.add(Arrays.asList(-1, 0, 1));
        System.out.println(res);
        System.out.println(exp1);

        assertIterableEquals(exp1,res);
    }
}
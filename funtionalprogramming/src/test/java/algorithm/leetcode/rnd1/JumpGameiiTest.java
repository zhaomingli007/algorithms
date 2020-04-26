package algorithm.leetcode.rnd1;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JumpGameiiTest {

    @Test
    public void jump() {
        JumpGameii j = new JumpGameii();
        int steps = j.jump(new int []{2,3,1,1,4});
        assertEquals(2,steps);
    }
}
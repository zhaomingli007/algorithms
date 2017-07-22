package interview.cal;


/**
 * Created by zhao on 12/14/16.
 */
public class NumberSumExhaustion {

    public static void main(String[] args) {
        for (int iSum = 1; iSum <= 20; iSum++) {
            for (int jSum = 2; jSum <= 20; jSum += 2) {
                if (iSum + jSum == 20) {
                    System.out.println(iSum + " * 1 + " + jSum / 2 + " * 2 = 20");
                }
                for (int kSum = 5; kSum <= 20; kSum += 5) {
                    if (jSum == 2 && (iSum + kSum) == 20) {
                        System.out.println(iSum + " * 1 + " + kSum / 5 + " * 5 = 20");
                    }
                    if (iSum == 1 && jSum + kSum == 20) {
                        System.out.println(jSum / 2 + " * 2 + " + kSum / 5 + " * 5 = 20");
                    }
                    if (iSum + jSum + kSum == 20) {
                        System.out.println(iSum + " * 1 + " + jSum / 2 + " * 2 + " + kSum / 5 + " * 5 = 20");
                    }
                    if (iSum == 1 && jSum == 2 && kSum == 20) {
                        System.out.println(kSum / 5 + " * 5 = 20");
                    }
                }
                if (iSum == 0 && jSum == 20) {
                    System.out.println(jSum / 2 + " * 2 = 20");
                }
            }
            if (iSum == 20) {
                System.out.println(iSum + " * 1 = 20");
            }
        }

    }
}

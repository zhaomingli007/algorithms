package interview.uf;

//Union find - quick find algorithm
public class QuickFind {
    private static int[] uf = null;

    public QuickFind(int N) {
        this.uf = new int[N];
        //Initialize uf
        for (int i = 0; i < uf.length; i++) {
            uf[i] = i;
        }
    }

    //Union command, add connection between a and b. O(N^2), too slow
    public static void union(int a, int b) {
        for (int i = 0; i < uf.length; i++) {
            if (uf[i] == uf[b])
                uf[i] = uf[a];
        }
    }

    //are p and q in the same component?
    public static Boolean connected(int p, int q) {
        return uf[p] == uf[q];
    }
}
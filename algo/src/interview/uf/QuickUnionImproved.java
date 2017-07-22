package interview.uf;

//Union find problem using quick union algorithm, path compression

public class QuickUnionImproved {
    private int[] qn = null;
    private int[] sz = null;

    public QuickUnionImproved(int N) {
        qn = new int[N];
        sz = new int[N];
        //Initialize
        for (int i = 0; i < qn.length; i++) {
            qn[i] = i;
            sz[i] = 1;
        }
    }

    //Put q and p to the same component. log2(N)
    public void union(int q, int p) {
        int r1 = root(q); //root
        int r2 = root(p); //root
        if (sz[q] >= sz[p]) {
            qn[r2] = r1;
            sz[r1] += sz[r2];
        } else {
            qn[r1] = r2;
            sz[r2] += sz[r1];
        }
    }

    public int root(int r) {
        while (r != qn[r]) {
            qn[r] = qn[qn[r]];
            r = qn[r]; //Make every other node to its grandparent.
        }
        return r;
    }

    public Boolean connected(int q, int p) {
        return root(q) == root(p);
    }
}
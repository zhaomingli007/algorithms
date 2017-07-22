package interview.uf;


//Union find problem using quick union algorithm
public class QuickUnion {
    private int[] qn = null;

    public QuickUnion(int N) {
        qn = new int[N];
        //Initialize
        for (int i = 0; i < qn.length; i++) {
            qn[i] = i;
        }
    }

    //Put q and p to the same component.
    public void union(int q, int p) {
        int r1 = root(q); //root
        int r2 = root(p); //root
        qn[r1] = r2;
    }

    public int root(int r) {
        while (r != qn[r]) {
            r = qn[r];
        }
        return r;
    }

    public Boolean connected(int q, int p) {
        return root(q) == root(p);
    }
}
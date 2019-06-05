package HackerRank.q4;

public class LongestCommonSequence {

    int[][] memo;
    int pLen, qLen;
    String[] p, q;

    public LongestCommonSequence(String p, String q) {
        pLen = p.length();
        qLen = q.length();
        memo = new int[pLen][qLen];
        this.p = p.split("");
        this.q = q.split("");
    }


    public int find() {
        for (int i = 0; i < pLen; i++) {
            for (int j = 0; j < qLen; j++) {
                memo[i][j] = -1;
            }
        }
        int op = LCS(p, q, pLen - 1, qLen - 1);

        for (int i = 0; i < pLen; i++) {
            for (int j = 0; j < qLen; j++) {
                System.out.print(String.format("%3d\t",memo[i][j]));
            }
            System.out.println("");
        }

        return op;

    }

    /**
     * Return the length of the largest common subsequence between p and q
     *
     * @param p    : Array version of 1st string input
     * @param q    : Array version of 2nd string input
     * @param pIdx : part of p to compute over
     * @param qIdx : part of q to compute over
     * @return
     */
    private int LCS(String[] p, String[] q, final int pIdx, final int qIdx) {

        // https://www.youtube.com/watch?v=Qf5R-uYQRPk

        int result;

        if (pIdx < 0 || qIdx < 0) return 0;

        // already calculated
        if (memo[pIdx][qIdx] > -1) {
            return memo[pIdx][qIdx];
        }
        // not calculated
        else if (p[pIdx].equals(q[qIdx])) {
            result = 1 + LCS(p, q, pIdx - 1, qIdx - 1);
        } else {
            // p[pIdx] != q[qIdx]
            int t1 = LCS(p, q, pIdx - 1, qIdx);
            int t2 = LCS(p, q, pIdx, qIdx - 1);
            result = t1 > t2 ? t1 : t2;
        }

        memo[pIdx][qIdx] = result;  // memoize
        return result;
    }

    public static void main(String[] args) {
        System.out.println("\nLongest common Subsequence: " + new LongestCommonSequence("haackerrank", "ha1cker").find());
    }
}

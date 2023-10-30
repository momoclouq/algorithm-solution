package CommonAlgo;

import java.util.Arrays;

public class Common {
    public static void main(String[] args) {
        // prefix sum:  fast calculation of sums of elements in given slice
        System.out.println(Arrays.toString(prefix_sums(new int[]{1, 2, 3, 4, 5})));
        System.out.println(Arrays.toString(prefix_sums(new int[]{10, 22, 33, 44, 55})));
        System.out.println(Arrays.toString(prefix_sums(new int[]{1})));

    }

    public static int[] prefix_sums(int[] A) {
        int n = A.length;
        if (n == 0) return new int[1];

        int[] prefixSums = new int[n+1]; // include the first value
        prefixSums[0] = 0;

        for (int i = 1; i < n + 1; i++) {
            prefixSums[i] = prefixSums[i - 1] + A[i - 1];
        }

        return prefixSums;
    }
}

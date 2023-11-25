package Codility.Lessons.MushroomPicker;

import CommonAlgo.Common;

public class MushroomPicker {
    public static void main(String[] args) {
//        Problem: You are given a non-empty, zero-indexed array A of n (1 � n � 100 000) integers
//        a0, a1, . . , an−1 (0 � ai � 1 000). This array represents number of mushrooms growing on the
//        consecutive spots along a road. You are also given integers k and m (0 � k, m < n).
//        A mushroom picker is at spot number k on the road and should perform m moves. In
//        one move she moves to an adjacent spot. She collects all the mushrooms growing on spots
//        she visits. The goal is to calculate the maximum number of mushrooms that the mushroom
//        picker can collect in m moves.
        System.out.println(solution(new int[]{2, 3, 7, 5, 1, 3, 9}, 6, 4));
    }

    private static int countTotalFromPrefixSum(int[] P, int x, int y) {
        if (x > y || y - 1 >= P.length || x < 0 || y < 0) {
            throw new RuntimeException("data is invalid: x = " + x + ", y = " + y);
        }

        return P[y+1] - P[x];
    }

    public static int solution(int[] A, int m, int k) {
        int len = A.length;
        int result = 0;
        int[] pref = Common.prefix_sums(A);

        // loop from k to the end of array
        for (int pos = 0; pos <= Math.min(m , k); pos++) {
            int left_pos = k - pos;
            int right_pos = Math.min(len - 1, Math.max(k, k + m - 2 * pos));
            result = Math.max(result, countTotalFromPrefixSum(pref, left_pos, right_pos));
        }

        // loop from k to the start of the array
        for (int pos = 0; pos < Math.min(m + 1, len - k); pos++) {
            int right_pos = k + pos;
            int left_pos = Math.max(0, Math.min(k, k - (m - 2 * pos)));
            result = Math.max(result, countTotalFromPrefixSum(pref, left_pos, right_pos));
        }

        return result;
    }
}

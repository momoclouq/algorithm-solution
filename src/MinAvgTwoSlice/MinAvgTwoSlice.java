package MinAvgTwoSlice;

import CommonAlgo.Common;

public class MinAvgTwoSlice {
    public static void main(String[] args) {
//        A non-empty array A consisting of N integers is given. A pair of integers (P, Q), such that 0 ≤ P < Q < N, is called a slice of array A (notice that the slice contains at least two elements). The average of a slice (P, Q) is the sum of A[P] + A[P + 1] + ... + A[Q] divided by the length of the slice. To be precise, the average equals (A[P] + A[P + 1] + ... + A[Q]) / (Q − P + 1).
//
//        For example, array A such that:
//
//        A[0] = 4
//        A[1] = 2
//        A[2] = 2
//        A[3] = 5
//        A[4] = 1
//        A[5] = 5
//        A[6] = 8
//        contains the following example slices:
//
//        slice (1, 2), whose average is (2 + 2) / 2 = 2;
//        slice (3, 4), whose average is (5 + 1) / 2 = 3;
//        slice (1, 4), whose average is (2 + 2 + 5 + 1) / 4 = 2.5.
//                The goal is to find the starting position of a slice whose average is minimal.
//
//        Write a function:
//
//        class Solution { public int solution(int[] A); }
//
//        that, given a non-empty array A consisting of N integers, returns the starting position of the slice with the minimal average. If there is more than one slice with a minimal average, you should return the smallest starting position of such a slice.
//
//                For example, given array A such that:
//
//        A[0] = 4
//        A[1] = 2
//        A[2] = 2
//        A[3] = 5
//        A[4] = 1
//        A[5] = 5
//        A[6] = 8
//        the function should return 1, as explained above.
//
//                Write an efficient algorithm for the following assumptions:
//
//        N is an integer within the range [2..100,000];
//        each element of array A is an integer within the range [−10,000..10,000].
        System.out.println(solution2(new int[]{4, 2, 2, 5, 1, 5, 8})); // 1
        System.out.println(solution2(new int[]{4, 2, 3, 5, 1, 1, 7, 1, -10000})); // 7
        System.out.println(solution2(new int[]{1, 2})); // 0
        System.out.println(solution2(new int[]{-1, -2, -3, -4, -5})); // 3
    }

    private static long[] prefix_sums(int[] A) {
        int n = A.length;
        if (n == 0) return new long[1];

        long[] prefixSums = new long[n+1]; // include the first value // convert to long array for edge case
        prefixSums[0] = 0;

        for (int i = 1; i < n + 1; i++) {
            prefixSums[i] = prefixSums[i - 1] + A[i - 1];
        }

        return prefixSums;
    }

    private static long getResultFromPrefix(long[] prefix, int left, int right) {
        // get sum from left -> right inclusive
        return prefix[right + 1] - prefix[left];
    }

    public static int solution(int[] A) {
        if (A.length == 1) throw new RuntimeException("Wrong input");

        long[] prefixSums = prefix_sums(A); // starts with an extra 0 at 0 position
        double minCalculation = Double.MAX_VALUE;
        int startingPos = 0;
        int endingPos = 0;

        int loops = 0;

        for (int leftPos = 0; leftPos < A.length - 1; leftPos++) {
            for (int rightPos = leftPos + 1; rightPos < A.length; rightPos++) {
                loops++;
                 long currentSum = getResultFromPrefix(prefixSums, leftPos, rightPos);
                 double currentResult = (double) currentSum / (rightPos - leftPos + 1);

                 if (currentResult < minCalculation) {
                     minCalculation = currentResult;
                     startingPos = leftPos;
                     endingPos = rightPos;
                 }
            }
        }

        // logging for testing
//        System.out.println("Final result: " + startingPos + "-" + endingPos + "-" + minCalculation+"-" +loops);

        return startingPos;
    }

    // efficient solution
    // https://codesays.com/2014/solution-to-min-avg-two-slice-by-codility/

    public static int solution2(int[] A){
        // explanation
//        1) There must be some slices, with length of two or three, having the minimal average value among all the slices.
//        (2) And all the longer slices with minimal average are built up with these 2-element and/or 3-element small slices.
//
//        Firstly we will prove the statement (1). In all the following discussion, we assume the slices have two or more element. In every array, there must be at lease one slice, to say S, having the Minimal Average (MA). And PLEASE PAY ATTENTION, the following proof is done with the S, which HAS the global minimal average.
//
//                If the length of S is two or three, it follows our conclusion.
//                If the length of S is odd, we could divide it into a 3-element sub-slice and some 2-element sub-slice. For example, for the slice [1, 2, 3, 4, 5], we could get a 3-element sub-slice [1, 2, 3] and a 2-element sub-slice [4, 5]. Or differently we could get [1, 2] and [3, 4, 5]. But the split does not matter in the following prove.
//        If the sub-slices have different averages, some of them will have smaller average than MA. But it conflicts with the condition that, the MA is known as the global minimal average for all slices.  By this contradictory, it’s proved that all the sub-slices MUST have the same average.
//                If all the sub-slices have the same average, the average of them must be MA. So all these sub-slices have overall minimal average and length of two or three.
//        If the length of S is even, we could divide it into some 2-element sub-slice. For the slice [1, 2, 3, 4], the only possible split is [1, 2] and [3, 4]. Then, similar with the previous case, all these 2-element sub-slices have the global minimal average.
//                And in the construction in step b, we have already proven the statement (2).

        double minimalAverage = (double) (A[0] + A[1]) / 2;
        int minAveragePos = 0;

        for (int i = 0; i < A.length - 2; i++) {
            if ( (double) (A[i] + A[i + 1]) / 2 < minimalAverage) {
                minimalAverage = (double) (A[i] + A[i + 1]) / 2;
                minAveragePos = i;
            }

            if ( (double) (A[i] + A[i + 1] + A[i + 2]) / 3 < minimalAverage) {
                minimalAverage = (double) (A[i] + A[i + 1] + A[i + 2]) / 3;
                minAveragePos = i;
            }
        }

        // try the last 2 elements
        if ( (double) (A[A.length - 1] + A[A.length - 2]) / 2 < minimalAverage) {
            minimalAverage = (double) (A[A.length - 1] + A[A.length - 2]) / 2;
            minAveragePos = A.length - 2;
        }

        return minAveragePos;
    }
}

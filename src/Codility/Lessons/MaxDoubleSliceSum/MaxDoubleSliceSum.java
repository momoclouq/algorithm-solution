package Codility.Lessons.MaxDoubleSliceSum;

public class MaxDoubleSliceSum {
    public static void main(String[] args) {
//        A non-empty array A consisting of N integers is given.
//
//        A triplet (X, Y, Z), such that 0 ≤ X < Y < Z < N, is called a double slice.
//
//        The sum of double slice (X, Y, Z) is the total of A[X + 1] + A[X + 2] + ... + A[Y − 1] + A[Y + 1] + A[Y + 2] + ... + A[Z − 1].
//
//        For example, array A such that:
//
//        A[0] = 3
//        A[1] = 2
//        A[2] = 6
//        A[3] = -1
//        A[4] = 4
//        A[5] = 5
//        A[6] = -1
//        A[7] = 2
//        contains the following example double slices:
//
//        double slice (0, 3, 6), sum is 2 + 6 + 4 + 5 = 17,
//        double slice (0, 3, 7), sum is 2 + 6 + 4 + 5 − 1 = 16,
//        double slice (3, 4, 5), sum is 0.
//        The goal is to find the maximal sum of any double slice.
//
//        Write a function:
//
//        class Solution { public int solution(int[] A); }
//
//        that, given a non-empty array A consisting of N integers, returns the maximal sum of any double slice.
//
//        For example, given:
//
//        A[0] = 3
//        A[1] = 2
//        A[2] = 6
//        A[3] = -1
//        A[4] = 4
//        A[5] = 5
//        A[6] = -1
//        A[7] = 2
//        the function should return 17, because no double slice of array A has a sum of greater than 17.
//
//        Write an efficient algorithm for the following assumptions:
//
//        N is an integer within the range [3..100,000];
//        each element of array A is an integer within the range [−10,000..10,000].
        System.out.println(solution(new int[]{3, 2, 6, -1, 4, 5, -1, 2}));
        System.out.println(solution(new int[]{-3, -2, -6, -1, -1, 2}));

    }

    // reference solution: https://codesays.com/2014/solution-to-max-double-slice-sum-by-codility/
    public static int solution(int[] A) {
        if (A.length <= 3) return 0;

        // calculate the max sub array that ends at each index
        int[] maxEndingHere = new int[A.length];
        int tempMaxEnding = 0;
        for (int i = 1; i < A.length - 2; i++) { // do not count the first and last element and the 1 element of the 2nd array
            tempMaxEnding = Math.max(0, tempMaxEnding + A[i]);
            maxEndingHere[i] = tempMaxEnding;
        }

        // calculate the max sub array that start at each index (from the bottom)
        // this can also be renamed as maxEndingHere but with the start is from the bottom
        // do not count the first and last and the 1 element of the 1st array
        int[] maxStartingHere = new int[A.length];
        int tempMaxStarting = 0;
        for (int i = A.length - 2; i >= 2; i--) {
            tempMaxStarting = Math.max(0, tempMaxStarting + A[i]);
            maxStartingHere[i] = tempMaxStarting;
        }

        // connect the 2 array with the middle stop point
        // if the first array stops at i, the second array starts at i+2
        int maxSum2Slice = 0;
        for (int i = 0 ; i < A.length - 2; i++) {
            maxSum2Slice = Math.max(maxSum2Slice, maxEndingHere[i] + maxStartingHere[i+2]);
        }

        return maxSum2Slice;
    }
}

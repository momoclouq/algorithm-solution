package Codility.Lessons.Flags;

public class Flags {
    public static void main(String[] args) {
//        A non-empty array A consisting of N integers is given.
//
//        A peak is an array element which is larger than its neighbours. More precisely, it is an index P such that 0 < P < N − 1 and A[P − 1] < A[P] > A[P + 1].
//
//                For example, the following array A:
//
//        A[0] = 1
//        A[1] = 5
//        A[2] = 3
//        A[3] = 4
//        A[4] = 3
//        A[5] = 4
//        A[6] = 1
//        A[7] = 2
//        A[8] = 3
//        A[9] = 4
//        A[10] = 6
//        A[11] = 2
//        has exactly four peaks: elements 1, 3, 5 and 10.
//
//        You are going on a trip to a range of mountains whose relative heights are represented by array A, as shown in a figure below. You have to choose how many flags you should take with you. The goal is to set the maximum number of flags on the peaks, according to certain rules.
//
//
//
//                Flags can only be set on peaks. What's more, if you take K flags, then the distance between any two flags should be greater than or equal to K. The distance between indices P and Q is the absolute value |P − Q|.
//
//        For example, given the mountain range represented by array A, above, with N = 12, if you take:
//
//        two flags, you can set them on peaks 1 and 5;
//        three flags, you can set them on peaks 1, 5 and 10;
//        four flags, you can set only three flags, on peaks 1, 5 and 10.
//        You can therefore set a maximum of three flags in this case.
//
//        Write a function:
//
//        class Solution { public int solution(int[] A); }
//
//        that, given a non-empty array A of N integers, returns the maximum number of flags that can be set on the peaks of the array.
//
//                For example, the following array A:
//
//        A[0] = 1
//        A[1] = 5
//        A[2] = 3
//        A[3] = 4
//        A[4] = 3
//        A[5] = 4
//        A[6] = 1
//        A[7] = 2
//        A[8] = 3
//        A[9] = 4
//        A[10] = 6
//        A[11] = 2
//        the function should return 3, as explained above.
//
//                Write an efficient algorithm for the following assumptions:
//
//        N is an integer within the range [1..400,000];
//        each element of array A is an integer within the range [0..1,000,000,000].
        System.out.println(solution1(new int[]{1, 5, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2}));
        System.out.println(solution1(new int[]{1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1}));
    }

    // reference: https://codility.com/media/train/solution-flags.pdf
    // fast solution
    public static int solution1(int[] A) {
        if (A.length <= 2) return 0;

        int maxFlags = 0;

        // the number of values
        for (int i = 1; i <= Math.floor(Math.sqrt(A.length)) + 1; i++) {
            if (checkWhetherXflagsCanBeSet(i, A)) maxFlags = Math.max(i, maxFlags);
        }

        return maxFlags;
    }

    private static boolean[] createPeaks(int[] A) {
        boolean[] peaks = new boolean[A.length];

        if (A.length <= 2) return peaks; // there are no peaks for these scenarios

        for (int i = 1; i < A.length - 1; i++) {
            if (A[i] > A[i - 1] && A[i] > A[i + 1]) {
                peaks[i] = true;
            }
        }

        return peaks;
    }

    private static boolean checkWhetherXflagsCanBeSet(int x, int[] A) {
        boolean[] peaks = createPeaks(A);
        int numberOfFlags = x;
        int pos = 0;

        while (numberOfFlags > 0 && pos < A.length) {
            if (peaks[pos]) {
                pos += x;
                numberOfFlags -= 1;
            } else {
                pos += 1;
            }
        }

        return numberOfFlags == 0;
    }

    // golden solution
    public static int solution2(int[] A) {
        int[] nextPeaks = nextPeaks(A);
        int i = 1;
        int result = 0;

        while ((i - 1) * i <= A.length) {
            int pos = 0;
            int num = 0;
            while (pos < A.length && num < i) {
                pos = nextPeaks[pos];
                if (pos < 0) {
                    break;
                }
                num += 1;
                pos += i;
            }
            result = Math.max(result, num);
            i += 1;
        }

        return result;
    }

    private static int[] nextPeaks(int[] A) {
        int[] next = new int[A.length];
        next[A.length - 1] = -1;
        boolean[] peaks = createPeaks(A);

        for (int i = A.length - 2; i >= 0; i--) {
            if (peaks[i]){
                next[i] = i;
            } else {
                next[i] = next[i + 1];
            }
        }

        return next;
    }
}

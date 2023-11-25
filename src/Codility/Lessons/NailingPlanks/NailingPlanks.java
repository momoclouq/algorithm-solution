package Codility.Lessons.NailingPlanks;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

class SortedNail {
    int position;
    int value;

    public SortedNail(int position, int value) {
        this.position = position;
        this.value = value;
    }
}

class SortByValue implements Comparator<SortedNail> {
    public int compare(SortedNail a, SortedNail b) {
        return a.value - b.value;
    }
}

public class NailingPlanks {
    public static void main(String[] args) {
//        You are given two non-empty arrays A and B consisting of N integers. These arrays represent N planks. More precisely, A[K] is the start and B[K] the end of the K−th plank.
//
//        Next, you are given a non-empty array C consisting of M integers. This array represents M nails. More precisely, C[I] is the position where you can hammer in the I−th nail.
//
//        We say that a plank (A[K], B[K]) is nailed if there exists a nail C[I] such that A[K] ≤ C[I] ≤ B[K].
//
//                The goal is to find the minimum number of nails that must be used until all the planks are nailed. In other words, you should find a value J such that all planks will be nailed after using only the first J nails. More precisely, for every plank (A[K], B[K]) such that 0 ≤ K < N, there should exist a nail C[I] such that I < J and A[K] ≤ C[I] ≤ B[K].
//
//                For example, given arrays A, B such that:
//
//        A[0] = 1    B[0] = 4
//        A[1] = 4    B[1] = 5
//        A[2] = 5    B[2] = 9
//        A[3] = 8    B[3] = 10
//        four planks are represented: [1, 4], [4, 5], [5, 9] and [8, 10].
//
//        Given array C such that:
//
//        C[0] = 4
//        C[1] = 6
//        C[2] = 7
//        C[3] = 10
//        C[4] = 2
//        if we use the following nails:
//
//        0, then planks [1, 4] and [4, 5] will both be nailed.
//        0, 1, then planks [1, 4], [4, 5] and [5, 9] will be nailed.
//        0, 1, 2, then planks [1, 4], [4, 5] and [5, 9] will be nailed.
//        0, 1, 2, 3, then all the planks will be nailed.
//        Thus, four is the minimum number of nails that, used sequentially, allow all the planks to be nailed.
//
//        Write a function:
//
//        class Solution { public int solution(int[] A, int[] B, int[] C); }
//
//        that, given two non-empty arrays A and B consisting of N integers and a non-empty array C consisting of M integers, returns the minimum number of nails that, used sequentially, allow all the planks to be nailed.
//
//        If it is not possible to nail all the planks, the function should return −1.
//
//        For example, given arrays A, B, C such that:
//
//        A[0] = 1    B[0] = 4
//        A[1] = 4    B[1] = 5
//        A[2] = 5    B[2] = 9
//        A[3] = 8    B[3] = 10
//
//        C[0] = 4
//        C[1] = 6
//        C[2] = 7
//        C[3] = 10
//        C[4] = 2
//        the function should return 4, as explained above.
//
//                Write an efficient algorithm for the following assumptions:
//
//        N and M are integers within the range [1..30,000];
//        each element of arrays A, B and C is an integer within the range [1..2*M];
//        A[K] ≤ B[K].
        solution(new int[]{1, 4, 5, 8}, new int[]{4, 5, 9, 10}, new int[]{4, 6, 7, 10, 2});
    }

    private static int findFirstNail(int plankBegin, int plankEnd, SortedNail[] sortedNails, int preResult) {
        int result = -1; // result of the nail in the original array
        int resultSorted = -1; // result of the nail in the sorted array
        int nailLower = 0;
        int nailUpper = sortedNails.length - 1;

        while (nailLower <= nailUpper) {
            int nailMid = nailLower + (nailUpper - nailLower) / 2;
            int nailMidValue = sortedNails[nailMid].value;

            if (nailMidValue < plankBegin) {
                nailLower = nailMid + 1;
            } else if (nailMidValue > plankEnd) {
                nailUpper = nailMid - 1;
            } else {
                nailUpper = nailMid - 1; // continue finding the smallest value
                result = sortedNails[nailMid].position;
                resultSorted = nailMid;
            }
        }

        if (result == -1) return -1;
        resultSorted += 1;
        while (resultSorted <= sortedNails.length - 1) {
            if (sortedNails[resultSorted].value > plankEnd) break;
            result = Math.min(result, sortedNails[resultSorted].position);
            resultSorted += 1;

            if (preResult >= result) return preResult;
        }

        return Math.max(preResult, result);
    }

    // reference: https://codesays.com/2014/solution-to-nailing-planks-by-codility/
     public static int solution(int[] A, int[] B, int[] C) {
        SortedNail[] sortedNails = IntStream.range(0, C.length).mapToObj(index -> {
            return new SortedNail(index, C[index]);
        }).sorted(new SortByValue()).toArray(size -> new SortedNail[size]);

        int result = -1;

        for (int i = 0; i < B.length; i++) {
            result = findFirstNail(A[i], B[i], sortedNails, result);
            if (result == -1) return -1; // could not find any nail for this plank
        }

        return result + 1;
    }
}

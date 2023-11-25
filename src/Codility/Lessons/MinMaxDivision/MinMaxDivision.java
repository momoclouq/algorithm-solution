package Codility.Lessons.MinMaxDivision;

public class MinMaxDivision {
    public static void main(String[] args) {
//        You are given integers K, M and a non-empty array A consisting of N integers. Every element of the array is not greater than M.
//
//        You should divide this array into K blocks of consecutive elements. The size of the block is any integer between 0 and N. Every element of the array should belong to some block.
//
//        The sum of the block from X to Y equals A[X] + A[X + 1] + ... + A[Y]. The sum of empty block equals 0.
//
//        The large sum is the maximal sum of any block.
//
//        For example, you are given integers K = 3, M = 5 and array A such that:
//
//        A[0] = 2
//        A[1] = 1
//        A[2] = 5
//        A[3] = 1
//        A[4] = 2
//        A[5] = 2
//        A[6] = 2
//        The array can be divided, for example, into the following blocks:
//
//[2, 1, 5, 1, 2, 2, 2], [], [] with a large sum of 15;
//[2], [1, 5, 1, 2], [2, 2] with a large sum of 9;
//[2, 1, 5], [], [1, 2, 2, 2] with a large sum of 8;
//[2, 1], [5, 1], [2, 2, 2] with a large sum of 6.
//        The goal is to minimize the large sum. In the above example, 6 is the minimal large sum.
//
//                Write a function:
//
//        class Solution { public int solution(int K, int M, int[] A); }
//
//        that, given integers K, M and a non-empty array A consisting of N integers, returns the minimal large sum.
//
//        For example, given K = 3, M = 5 and array A such that:
//
//        A[0] = 2
//        A[1] = 1
//        A[2] = 5
//        A[3] = 1
//        A[4] = 2
//        A[5] = 2
//        A[6] = 2
//        the function should return 6, as explained above.
//
//                Write an efficient algorithm for the following assumptions:
//
//        N and K are integers within the range [1..100,000];
//        M is an integer within the range [0..10,000];
//        each element of array A is an integer within the range [0..M].
        System.out.println(solution(3, 5, new int[]{2, 1, 5, 1, 2, 2, 2}));
    }

    private static int getNumberOfBlocksWithSize(int[] A, int blockMax) {
        // assume the first element is already in the first block
        int blockNum = 1;
        int currentBlockSum = A[0];

        for (int i = 1; i < A.length; i++) {
            if (currentBlockSum + A[i] <= blockMax) {
                currentBlockSum += A[i];
            } else {
                currentBlockSum = A[i];
                blockNum++;
            }
        }

        return blockNum;
    }

    private static long sum(int[] A) {
        long result = 0;

        for (int i: A) {
            result += i;
        }

        return result;
    }

    private static int max(int[] A) {
        int result = 0; // as each element in A is from 0 -> M

        for (int i: A) {
            if (i > result) {
                result = i;
            }
        }

        return result;
    }


    // reference: https://codesays.com/2014/solution-to-min-max-division-by-codility/
    public static int solution(int K, int M, int[] A) {
        long maxBlockSize = sum(A); // initial max block size is the sum of all A elements
        int minBlockSize = max(A); // initial min block size is the maximum value of A
        int result = 0;

        if (K == 1) {
            return (int) maxBlockSize; // can only be 1 block with size = sum of all A
        }

        // with this condition, A.length is sure to be >= 2
        if (K > A.length) {
            return minBlockSize; // there are too many blocks -> the maximum can only be the maximum value with some empty blocks
        }

        // use binary search to find the correct block size
        while (minBlockSize <= maxBlockSize) {
            int midBlockSize = (int) (minBlockSize + (maxBlockSize - minBlockSize) / 2);
            int blockNum = getNumberOfBlocksWithSize(A, midBlockSize);

            if (blockNum <= K) {
//                # With large sum being resultMaxMid or resultMaxMid-,
//            # we need blocksNeeded/blocksNeeded- blocks. While we
//            # have some unused blocks (K - blocksNeeded), We could
//            # try to use them to decrease the large sum.
                maxBlockSize = midBlockSize - 1;
                result = midBlockSize;
            } else {
                minBlockSize = midBlockSize + 1;
            }
        }

        return result;
    }
}

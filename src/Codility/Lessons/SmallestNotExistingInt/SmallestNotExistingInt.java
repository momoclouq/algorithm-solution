package Codility.Lessons.SmallestNotExistingInt;

public class SmallestNotExistingInt {
    public static void main(String[] args) {
//
//                Write a function:
//
//        class Solution { public int solution(int[] A); }
//
//        that, given an array A of N integers, returns the smallest positive integer (greater than 0) that does not occur in A.
//
//        For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.
//
//        Given A = [1, 2, 3], the function should return 4.
//
//        Given A = [−1, −3], the function should return 1.
//
//        Write an efficient algorithm for the following assumptions:
//
//        N is an integer within the range [1..100,000];
//        each element of array A is an integer within the range [−1,000,000..1,000,000].
        System.out.println(solution(new int[]{1, 2, 3}));
        System.out.println(solution(new int[]{-1, -3, 0, 1, 2, 4, 5, 6, 7, 8, 3, 10}));
        System.out.println(solution(new int[]{1}));
        System.out.println(solution(new int[]{0, 1, 2, 3, 100}));
    }


    public static int solution(int[] A) {
        int smallestInt = 1;
        boolean[] occurrence = new boolean[1000000];

        for (int i = 0; i < A.length; i++) {
            if (A[i] <= 0) continue; // do nothing

            int currentPos = A[i] - 1;
            occurrence[currentPos] = true;

            if (A[i] == smallestInt) {
                boolean foundNewPos = false;

                for (int k = currentPos + 1; k < occurrence.length; k++) {
                    if (!occurrence[k]) {
                        smallestInt = k + 1;
                        foundNewPos = true;
                        break;
                    }
                }

                if (!foundNewPos) return 1000001;
            }
        }

        return smallestInt;
    }
}

package Codility.Lessons.Dominator;

public class Dominator {
    public static void main(String[] args) {
//        An array A consisting of N integers is given. The dominator of array A is the value that occurs in more than half of the elements of A.
//
//                For example, consider array A such that
//
//        A[0] = 3    A[1] = 4    A[2] =  3
//        A[3] = 2    A[4] = 3    A[5] = -1
//        A[6] = 3    A[7] = 3
//        The dominator of A is 3 because it occurs in 5 out of 8 elements of A (namely in those with indices 0, 2, 4, 6 and 7) and 5 is more than a half of 8.
//
//        Write a function
//
//        class Solution { public int solution(int[] A); }
//
//        that, given an array A consisting of N integers, returns index of any element of array A in which the dominator of A occurs. The function should return −1 if array A does not have a dominator.
//
//                For example, given array A such that
//
//        A[0] = 3    A[1] = 4    A[2] =  3
//        A[3] = 2    A[4] = 3    A[5] = -1
//        A[6] = 3    A[7] = 3
//        the function may return 0, 2, 4, 6 or 7, as explained above.
//
//        Write an efficient algorithm for the following assumptions:
//
//        N is an integer within the range [0..100,000];
//        each element of array A is an integer within the range [−2,147,483,648..2,147,483,647].
        System.out.println(solution(new int[]{}));
        System.out.println(solution(new int[]{3, 4, 3, 2, 3, -1, 3, 3}));
        System.out.println(solution(new int[]{1}));
        System.out.println(solution(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 9}));
        System.out.println(solution(new int[]{-1, -2, -3, -4, -5, -6, -7, -8, 9, 9, 9, 9}));
        System.out.println(solution(new int[]{2, 1, 1, 1, 3}));
    }

    public static int solution(int[] A) {
        // find the candidate
        int candidate = -1;
        int size = 0;
        int value = 0;
        int candidateIndex = -1;
        for (int i = 0; i < A.length; i++) {
            if (size == 0) {
                size++;
                value = A[i];
                candidateIndex = i;
            } else if (value != A[i]) {
                size--;
            } else {
                size++;
            }
        }

        // found the candidate
        if (size > 0) {
            candidate = value;
        }

        // determine if the candidate is the dominator
        int count = 0;
        int dominator = -1; // index
        for (int i = 0; i < A.length; i++) {
            if (A[i] == candidate) {
                count++;
            }
        }
        if (count > (A.length / 2)) {
            dominator = candidateIndex;
        }

        return dominator;
    }
}

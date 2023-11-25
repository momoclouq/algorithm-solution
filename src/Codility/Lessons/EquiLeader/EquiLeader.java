package Codility.Lessons.EquiLeader;

public class EquiLeader {
    public static void main(String[] args) {
//        A non-empty array A consisting of N integers is given.
//
//        The leader of this array is the value that occurs in more than half of the elements of A.
//
//                An equi leader is an index S such that 0 ≤ S < N − 1 and two sequences A[0], A[1], ..., A[S] and A[S + 1], A[S + 2], ..., A[N − 1] have leaders of the same value.
//
//        For example, given array A such that:
//
//        A[0] = 4
//        A[1] = 3
//        A[2] = 4
//        A[3] = 4
//        A[4] = 4
//        A[5] = 2
//        we can find two equi leaders:
//
//        0, because sequences: (4) and (3, 4, 4, 4, 2) have the same leader, whose value is 4.
//        2, because sequences: (4, 3, 4) and (4, 4, 2) have the same leader, whose value is 4.
//        The goal is to count the number of equi leaders.
//
//        Write a function:
//
//        class Solution { public int solution(int[] A); }
//
//        that, given a non-empty array A consisting of N integers, returns the number of equi leaders.
//
//                For example, given:
//
//        A[0] = 4
//        A[1] = 3
//        A[2] = 4
//        A[3] = 4
//        A[4] = 4
//        A[5] = 2
//        the function should return 2, as explained above.
//
//                Write an efficient algorithm for the following assumptions:
//
//        N is an integer within the range [1..100,000];
//        each element of array A is an integer within the range [−1,000,000,000..1,000,000,000].
//        System.out.println(solution(new int[]{4, 3, 4, 4, 4, 2}));
//        System.out.println(solution(new int[]{}));
//        System.out.println(solution(new int[]{1}));
//        System.out.println(solution(new int[]{2, 1, 1, 1, 3}));
//        System.out.println(solution(new int[]{3, 3, 3, 3, 3}));
        System.out.println(solution(new int[]{4, 4, 2, 5, 3, 4, 4, 4}));
        System.out.println(solution(new int[]{-100, -100}));
    }

    public static int solution(int[] A) {
        // If there exists a position that separate the array into 2 slices with the same leader
        // then the leader of the 2 slices must also be the leader of the entire array

        // find the candidate
        int candidate = -1;
        int size = 0;
        int value = 0;
        for (int i = 0; i < A.length; i++) {
            if (size == 0) {
                size++;
                value = A[i];
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
        int dominator = -1; // value
        boolean dominatorExist = false;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == candidate) {
                count++;
            }
        }
        if (count > (A.length / 2)) {
            dominator = candidate;
            dominatorExist = true;
        }

        if (!dominatorExist) return 0;

        // now we have the dominator and the count
        // find the number of equi leaders
        int currentDominatorCount = 0;
        int equiLeaders = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == dominator) {
                currentDominatorCount++;
            }

            if (currentDominatorCount > (i + 1) / 2 && (count - currentDominatorCount) > (A.length - 1 - i) / 2) {
                equiLeaders++;
            }
        }

        return equiLeaders;
    }
}

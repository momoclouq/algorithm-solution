package Codility.Lessons.AbsDistinct;

public class AbsDistinct {
    public static void main(String[] args) {
//        A non-empty array A consisting of N numbers is given. The array is sorted in non-decreasing order. The absolute distinct count of this array is the number of distinct absolute values among the elements of the array.
//
//        For example, consider array A such that:
//
//        A[0] = -5
//        A[1] = -3
//        A[2] = -1
//        A[3] =  0
//        A[4] =  3
//        A[5] =  6
//        The absolute distinct count of this array is 5, because there are 5 distinct absolute values among the elements of this array, namely 0, 1, 3, 5 and 6.
//
//        Write a function:
//
//        class Solution { public int solution(int[] A); }
//
//        that, given a non-empty array A consisting of N numbers, returns absolute distinct count of array A.
//
//        For example, given array A such that:
//
//        A[0] = -5
//        A[1] = -3
//        A[2] = -1
//        A[3] =  0
//        A[4] =  3
//        A[5] =  6
//        the function should return 5, as explained above.
//
//                Write an efficient algorithm for the following assumptions:
//
//        N is an integer within the range [1..100,000];
//        each element of array A is an integer within the range [−2,147,483,648..2,147,483,647];
//        array A is sorted in non-decreasing order.
        System.out.println(solution(new int[]{-5, -3, -1, 0, 3, 6}));
        System.out.println(solution(new int[]{-2147483648, -1, 0, 1}));
        System.out.println(solution(new int[]{-1, 1, 1, 1, 1}));
        System.out.println(solution(new int[]{1, 1, 1, 1, 1, 2}));

        System.out.println(solution(new int[]{0}));
        System.out.println(solution(new int[]{1, 1, 1, 2, 2, 2, 3, 3, 3}));
    }

    public static int solution(int[] A) {
        int front = 0;
        int back = A.length - 1;
        int total = 0;
        long prevMax = -1; // starts at -1 as Math.abs could never be less than 0

        while (front <= back) {
            long frontVal = Math.abs((long) A[front]); // prevent edge case in codility
            long backVal = Math.abs((long) A[back]); // prevent edge case in codility

            if (frontVal < backVal) {
                back--;
                if (backVal != prevMax) {
                    prevMax = backVal;
                    total++;
                }
            } else if (frontVal > backVal) {
                front++;
                if (frontVal != prevMax) {
                    prevMax = frontVal;
                    total++;
                }
            } else {
                front++;
                back--;
                if (frontVal != prevMax) {
                    prevMax = frontVal;
                    total++;
                }
            }
        }

        return total;
    }
}

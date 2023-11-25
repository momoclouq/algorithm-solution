package Codility.Lessons.NumberOfDiscIntersection;

import java.util.Arrays;

public class NumberOfDiscIntersection {
    public static void main(String[] args) {
//        We draw N discs on a plane. The discs are numbered from 0 to N − 1. An array A of N non-negative integers, specifying the radiuses of the discs, is given. The J-th disc is drawn with its center at (J, 0) and radius A[J].
//
//                We say that the J-th disc and K-th disc intersect if J ≠ K and the J-th and K-th discs have at least one common point (assuming that the discs contain their borders).
//
//                The figure below shows discs drawn for N = 6 and A as follows:
//
//        A[0] = 1
//        A[1] = 5
//        A[2] = 2
//        A[3] = 1
//        A[4] = 4
//        A[5] = 0
//
//        There are eleven (unordered) pairs of discs that intersect, namely:
//
//        discs 1 and 4 intersect, and both intersect with all the other discs;
//        disc 2 also intersects with discs 0 and 3.
//        Write a function:
//
//        class Solution { public int solution(int[] A); }
//
//        that, given an array A describing N discs as explained above, returns the number of (unordered) pairs of intersecting discs. The function should return −1 if the number of intersecting pairs exceeds 10,000,000.
//
//        Given array A shown above, the function should return 11, as explained above.
//
//                Write an efficient algorithm for the following assumptions:
//
//        N is an integer within the range [0..100,000];
//        each element of array A is an integer within the range [0..2,147,483,647].
        System.out.println(solution(new int[]{1, 5, 2, 1, 4, 0}));
    }

    // reference solution: https://codesays.com/2014/solution-to-beta2010-number-of-disc-intersections-by-codility/
    public static int solution(int[] A) {
        // separate the left and right limit of the circle
        int[] leftLimits = new int[A.length];
        int[] rightLimits = new int[A.length];
        for (int circleCenter = 0; circleCenter < A.length; circleCenter++) {
            int radius = A[circleCenter];
            int leftLimit = circleCenter - radius;
            int rightLimit = circleCenter + radius;

            leftLimits[circleCenter] = leftLimit;
            rightLimits[circleCenter] = rightLimit;
        }

        //sort the left limits and right limits
        Arrays.sort(leftLimits);
        Arrays.sort(rightLimits);

        // count the number of intersection
        int totalIntersectionPairs = 0;
        int lowerIndex = 0;
        for (int upperIndex = 0; upperIndex < A.length; upperIndex++) {
            while (lowerIndex < A.length && leftLimits[lowerIndex] <= rightLimits[upperIndex]) {
                lowerIndex++;
            }

//            # We must exclude some discs such that:
//        #    disc_center - disc_radius <= current_center + current_radius
//        #    AND
//        #    disc_center + disc_radius <(=) current_center + current_radius
//        # These discs are not intersected with current disc, and below the
//        #    current one completely.
//        # After removing, the left discs are intersected with current disc,
//        #    and above the current one.
//        # Attention: the current disc is intersecting itself in this result
//        #    set. But it should not be. So we need to minus one to fix it.
            // upperIndex + 1 = number of disc totally below the current disc + itself
            totalIntersectionPairs += lowerIndex - upperIndex - 1;
            if (totalIntersectionPairs > 10000000) return -1;
        }

        return totalIntersectionPairs;
    }
}

package GeometricRangeQuery;

import java.util.Arrays;

public class GeometricRangeQuery {
    public static void main(String[] args) {
//        A DNA sequence can be represented as a string consisting of the letters A, C, G and T, which correspond to the types of successive nucleotides in the sequence. Each nucleotide has an impact factor, which is an integer. Nucleotides of types A, C, G and T have impact factors of 1, 2, 3 and 4, respectively. You are going to answer several queries of the form: What is the minimal impact factor of nucleotides contained in a particular part of the given DNA sequence?
//
//        The DNA sequence is given as a non-empty string S = S[0]S[1]...S[N-1] consisting of N characters. There are M queries, which are given in non-empty arrays P and Q, each consisting of M integers. The K-th query (0 ≤ K < M) requires you to find the minimal impact factor of nucleotides contained in the DNA sequence between positions P[K] and Q[K] (inclusive).
//
//                For example, consider string S = CAGCCTA and arrays P, Q such that:
//
//        P[0] = 2    Q[0] = 4
//        P[1] = 5    Q[1] = 5
//        P[2] = 0    Q[2] = 6
//        The answers to these M = 3 queries are as follows:
//
//        The part of the DNA between positions 2 and 4 contains nucleotides G and C (twice), whose impact factors are 3 and 2 respectively, so the answer is 2.
//        The part between positions 5 and 5 contains a single nucleotide T, whose impact factor is 4, so the answer is 4.
//        The part between positions 0 and 6 (the whole string) contains all nucleotides, in particular nucleotide A whose impact factor is 1, so the answer is 1.
//        Write a function:
//
//        class Solution { public int[] solution(String S, int[] P, int[] Q); }
//
//        that, given a non-empty string S consisting of N characters and two non-empty arrays P and Q consisting of M integers, returns an array consisting of M integers specifying the consecutive answers to all queries.
//
//                Result array should be returned as an array of integers.
//
//                For example, given the string S = CAGCCTA and arrays P, Q such that:
//
//        P[0] = 2    Q[0] = 4
//        P[1] = 5    Q[1] = 5
//        P[2] = 0    Q[2] = 6
//        the function should return the values [2, 4, 1], as explained above.
//
//                Write an efficient algorithm for the following assumptions:
//
//        N is an integer within the range [1..100,000];
//        M is an integer within the range [1..50,000];
//        each element of arrays P and Q is an integer within the range [0..N - 1];
//        P[K] ≤ Q[K], where 0 ≤ K < M;
//        string S consists only of upper-case English letters A, C, G, T.

        System.out.println(Arrays.toString(solution("CAGCCTA", new int[]{2,5,0}, new int[]{4, 5, 6}))); // result: [2, 4, 1]
//        System.out.println(Arrays.toString(solution("CAGCCTA", new int[]{0}, new int[]{0}))); // result: [2]
//        System.out.println(Arrays.toString(solution("G", new int[]{0}, new int[]{0}))); // result: [3]
        System.out.println(Arrays.toString(solution("AC", new int[]{0, 0, 1}, new int[]{0, 1, 1}))); // result: [1, 1, 2]
    }

    private static int impactFactorConvert(char nucleotide) {
        char upperCaseNu = Character.toUpperCase(nucleotide);

        switch (upperCaseNu) {
            case 'A': return 1;
            case 'C': return 2;
            case 'G': return 3;
            case 'T': return 4;
            default: return -1;
        }
    }

    private static int getLowestFactor(int[] values) {
        int factor = 0;

        for (int i = 0; i < values.length; i++) {
            if (values[i] != 0) {
                factor = i + 1;
                break;
            }
        }

        return factor;
    }

    private static int calculateResult(String S, int lowerLimitIndex, int prefixSumsLower, int prefixSumsUpper, char currentNucleotide) {
        if (prefixSumsLower == 0) return prefixSumsUpper;

        if (prefixSumsUpper - prefixSumsLower == 0 && S.charAt(lowerLimitIndex) == currentNucleotide) {
            return prefixSumsUpper - prefixSumsLower + 1; // plus 1 for the exact nucleotide add
        }

        return prefixSumsUpper - prefixSumsLower;
    }

    private static int[] generateResultFromRange(int[][] prefix_sums, String S, int lowerLimit, int upperLimit) {
        int resultForNucleotide1 = calculateResult(S, lowerLimit, prefix_sums[lowerLimit][0], prefix_sums[upperLimit][0], 'A');
        int resultForNucleotide2 = calculateResult(S, lowerLimit, prefix_sums[lowerLimit][1], prefix_sums[upperLimit][1], 'C');
        int resultForNucleotide3 = calculateResult(S, lowerLimit, prefix_sums[lowerLimit][2], prefix_sums[upperLimit][2], 'G');
        int resultForNucleotide4 = calculateResult(S, lowerLimit, prefix_sums[lowerLimit][3], prefix_sums[upperLimit][3], 'T');

        int[] resultFromLowerToUpperLimit = new int[]{
                resultForNucleotide1,
                resultForNucleotide2,
                resultForNucleotide3,
                resultForNucleotide4
        };

        return resultFromLowerToUpperLimit;
    }

    private static void defaultUpdatePrefixSums(int[][] prefix_sums, int index) {
        if (index <= 0) return;

        prefix_sums[index][0] = prefix_sums[index - 1][0];
        prefix_sums[index][1] = prefix_sums[index - 1][1];
        prefix_sums[index][2] = prefix_sums[index - 1][2];
        prefix_sums[index][3] = prefix_sums[index - 1][3];
    }

    public static int[] solution(String S, int[] P, int[] Q) {
        int[][] prefix_sums = new int[S.length()][4];

        if (S.length() == 0) return new int[0];

        prefix_sums[0][impactFactorConvert(S.charAt(0)) - 1] = 1; // first value

        for (int i = 1; i < S.length(); i++) {
            char currentNucleotide = S.charAt(i);
            int currentImpact = impactFactorConvert(currentNucleotide);
            if (currentImpact == -1) throw new RuntimeException("Wrong input");

            defaultUpdatePrefixSums(prefix_sums, i);

            prefix_sums[i][currentImpact - 1] = prefix_sums[i-1][currentImpact - 1] + 1;
        }

        if (P.length != Q.length || P.length == 0 || Q.length == 0) throw new RuntimeException("wrong input P, Q");

        int[] result = new int[P.length]; // result array with length = P, Q length

        for (int i = 0; i < P.length; i++) {
            int lowerLimit = P[i];
            int upperLimit = Q[i];

            if (lowerLimit == upperLimit) {
                result[i] = impactFactorConvert(S.charAt(lowerLimit));
            } else {
                int[] resultFromLowerToUpperLimit = generateResultFromRange(prefix_sums, S, lowerLimit, upperLimit);
                result[i] = getLowestFactor(resultFromLowerToUpperLimit);
            }
        }

        return result;
    }
}

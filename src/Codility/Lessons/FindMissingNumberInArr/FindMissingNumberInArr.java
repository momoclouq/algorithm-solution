package Codility.Lessons.FindMissingNumberInArr;

public class FindMissingNumberInArr {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 2, 3, 4, 5, 6}));
    }

    private static long sumFrom1to(int N) {
        long longN = N;
        return (longN * (longN + 1)) / 2;
    }

    public static int solution(int[] A) {
        int N = A.length;
        long expectedSum = sumFrom1to(N) + (N + 1);

        for(int i = 0; i < A.length; i++) {
            expectedSum -= A[i];
        }

        return (int) expectedSum;
    }
}

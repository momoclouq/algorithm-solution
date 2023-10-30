package MinimumAbsTape;

public class MinimumAbsTape {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{3,1,2, 5, -100, -230, 1000}));
    }

    public static int solution(int[] A) {
        // A.length = [2, ... 100000]
        // A element = [-1000, ... 1000]
        long abs = 0;
        long currentTape1 = A[0];
        long currentTape2 = 0;

        for (int i = 1; i < A.length; i++) {
            currentTape2 += A[i];
        }
        abs = Math.abs(currentTape1 - currentTape2);

        for (int P = 2; P < A.length; P++) {
            currentTape1 += A[P-1];
            currentTape2 -= A[P-1];
            abs = Math.min(abs, Math.abs(currentTape1 - currentTape2));
        }

        return (int) abs;
    }
}

package Codility.Lessons.OddOneOut;

import java.util.HashMap;
import java.util.Map;

public class OddOneOut {
    public static void main(String[] args) {
            System.out.println(solution(new int[]{3, 3, 3, 3, 9, 7, 9}));
    }

    public static int solution(int[] A) {
        // Implement your solution here
        HashMap<Integer, Integer> occurrenceCount = new HashMap<>();
        int oddOneOut = 0;

        for (int i = 0; i < A.length; i++) {
            if (occurrenceCount.containsKey(A[i])) {
                int previousCount = occurrenceCount.get(A[i]);
                occurrenceCount.put(A[i], previousCount + 1);
            } else {
                occurrenceCount.put(A[i], 1);
            }
        }

        System.out.println(occurrenceCount);

        for (Map.Entry<Integer, Integer> set: occurrenceCount.entrySet()) {
            int key = set.getKey();
            int count = set.getValue();

            if (count % 2 == 1) {
                oddOneOut = key;
                break;
            }
        }

        return oddOneOut;
    }
}

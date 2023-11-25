package CommonAlgo;

import java.util.Arrays;

public class Sorting {
    public static void main(String[] args) {
        // test selection sort - O(n^2)
        System.out.println(Arrays.toString(selectionSort(new int[]{1, 2, 5, 2, 3, 713, 134, 2134, 2, 45336, 8653,33})));

        // test counting sort - O(n + K) with k = value range
        System.out.println(Arrays.toString(countingSort(new int[]{1, 2, 5, 2, 3, 713, 134, 2134, 2, 45336, 8653}, 100000)));
    }
    public static int[] selectionSort(int[] A) {
        int len = A.length;

        for (int i = 0; i < len; i++) {
            int minimal = i;
            for (int k = i + 1; k < len; k++) {
                if (A[k] < A[minimal]) {
                    minimal = k;
                }
            }

            int temp = A[i];
            A[i] = A[minimal];
            A[minimal] = temp;
        }

        return A;
    }

    // A must only consists of elements from 0 -> k
    public static int[] countingSort(int[] A, int k) {
        int len = A.length;
        int[] count = new int[k+1];

        for (int i = 0; i < len; i++) {
            count[A[i]] += 1;
        }

        int currentCount = 0;
        for (int i = 0; i < k + 1; i++) {
            for (int j = 0; j < count[i]; j++) {
                A[currentCount] = i;
                currentCount++;
            }
        }

        return A;
    }

    public static void mergeSort(int[] A, int n) {
        if (n < 2) {
            return ;
        }

        int mid = n / 2;
        int[] left = new int[mid];
        int[] right = new int[n - mid];

        for (int i = 0; i < mid; i++) {
            left[i] = A[i];
        }

        for (int i = mid; i < n; i++) {
            right[i - mid] = A[i];
        }

        mergeSort(left, mid);
        mergeSort(right, n - mid);

        merge(A, left, right, mid, n - mid);
    }

    private static void merge(int[] a, int[] l, int[] r, int left, int right) {
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                a[k++] = l[i++];
            }
            else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }
}

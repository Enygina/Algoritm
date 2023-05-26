

public class hw2 {
    public static void main(String[] args) {
        int[] arrTest = new int[] {15, 7, 1, 4, 9, 2};
        int n = arrTest.length;

        HeapSort ob = new HeapSort();
        ob.sort(arrTest);

        System.out.println("Sorted array is");
        printArray(arrTest);
    }

    private static void printArray(int[] arr) {
        int n = arr.length;
        for (int j : arr) {
            System.out.print(j + " ");
            System.out.println();
        }
    }

    public static class HeapSort {
        public void sort(int[] arr) {
            int n = arr.length;

            for (int i = n / 2 - 1; i >= 0; i--)
                heapify(arr, n, i);

            for (int i = n - 1; i >= 0; i--) {
                int temp = arr[0];
                arr[0] = arr[i];
                arr[i] = temp;

                heapify(arr, i, 0);
            }
        }

        void heapify(int[] arr, int n, int i) {
            int largest = i;
            int l = 2 * i + 1;
            int r = 2 * i + 2;

            if (l < n && arr[l] > arr[largest]) {
                largest = l;
            }

            if (r < n && arr[r] > arr[largest]) {
                largest = r;
            }
            if (largest != i) {
                int swap = arr[i];
                arr[i] = arr[largest];
                arr[largest] = swap;

                heapify(arr, n, largest);
            }
        }


    }
}
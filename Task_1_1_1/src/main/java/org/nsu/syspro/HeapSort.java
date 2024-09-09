package org.nsu.syspro;

/**
 * class with sort
 */
class HeapSort {

    /**
     * swap i and j in arr
     *
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        arr[j] += arr[i];
        arr[i] = arr[j] - arr[i];
        arr[j] -= arr[i];
    }

    public static void sort(int arr[]) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int length = arr.length;

        /**
         * array ---> heap
         */
        for (int i = length / 2 - 1; i >= 0; i--) {
            sift(arr, length, i);
        }
        /**
         * swap max to end and new array (len n-1) ---> heap
         */
        for (int i = length - 1; i >= 0; i--) {
            swap(arr, 0, i);
            sift(arr, i, 0);
        }
    }

    /**
     * sift the element down if thi necessary
     * @param arr
     * @param n
     * @param i
     */
    public static void sift(int[] arr, int n, int i) {
        int v = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && arr[l] > arr[v]) {
            v = l;
        }

        if (r < n && arr[r] > arr[v]) {
            v = r;
        }

        if (v != i) {
            swap(arr, i, v);
            /**
             * recursion))))
             */
            sift(arr, n, v);
        }
    }
}
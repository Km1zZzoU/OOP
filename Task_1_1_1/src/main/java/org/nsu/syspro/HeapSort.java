package org.nsu.syspro;

/**
 * main class for function sort.
 */
public class HeapSort {

    /**swap i and j in arr.
     *
     * @param arr - arr
     * @param i - first ind
     * @param j - second ind
     */
    public static void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        arr[j] += arr[i];
        arr[i] = arr[j] - arr[i];
        arr[j] -= arr[i];
    }

    /**
     * main function sort.
     * in a loop turns the array into a heap and moves the element from the head to the end.
     *
     * @param arr - arr
     */
    public static void sort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int length = arr.length;

        // array ---> heap

        for (int i = length / 2 - 1; i >= 0; i--) {
            sift(arr, length, i);
        }
        // swap max to end and new array (len n-1) ---> heap

        for (int i = length - 1; i >= 0; i--) {
            swap(arr, 0, i);
            sift(arr, i, 0);
        }
    }

    /**sift the element down if thi necessary.
     *
     * @param arr - arr
     * @param n - len
     * @param i - index
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
            sift(arr, n, v);
        }
    }
}
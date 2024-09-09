package org.nsu.syspro;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

/** class for Tests
 */
public class Tests {

    /**Correctness testing
     */
    public static void test(int[] arr) {
        int[] copy = arr.clone();
        HeapSort.sort(arr);
        Arrays.sort(copy);
        assertArrayEquals(arr, copy);
    }

    /** generate random array with len=len
     *
     * @param len
     * @return
     */
    int[] randomarray(int len) {
        Random random = new Random();
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = random.nextInt();
        }
        return arr;
    }

    /**st tests
     */

    @Test
    void testHeapSort() {
        int[] arr = new int[]{4, 7, 8, 10, 3, 7, 10};
        test(arr);
    }

    @Test
    void testEmptyArray() {
        int[] arr = new int[]{};
        test(arr);
    }

    @Test
    void testSingleElement() {
        int[] arr = new int[]{42};
        test(arr);
    }

    @Test
    void testSortedArray() {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        test(arr);
    }

    @Test
    void testReverseSortedArray() {
        int[] arr = new int[]{5, 4, 3, 2, 1};
        test(arr);
    }

    @Test
    void testArrayWithDuplicates() {
        int[] arr = new int[]{5, 1, 5, 3, 1, 5, 2};
        test(arr);
    }

    @Test
    void testAllSameElements() {
        int[] arr = new int[]{7, 7, 7, 7, 7};
        test(arr);
    }

    @Test
    void testArrayWithNegativeNumbers() {
        int[] arr = new int[]{-3, -1, -7, 0, 2, 5, -2};
        test(arr);
    }

    @Test
    void testArrayWithMinMaxIntegers() {
        int[] arr = new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 0, -1, 1};
        test(arr);
    }

    /** test height
     *     *           *         *
     *    / \   and   /    and
     *   *   *       *
     */
    @Test
    void testAnySize() {
        int h = 4;
        for (int i = (int) Math.pow(2, (h - 1)); i < (int) Math.pow(2, h); i++) {
            int[] arr = randomarray(i);
            test(arr);
        }
    }
}

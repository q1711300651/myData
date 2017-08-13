package algorithms;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;

import java.util.Arrays;

/**
 * Created by ww on 17/4/23.
 */
public class TestBinarySearch {

    public static void main(String[] args) {
        In in = new In(args[0]);
        int[] ints = in.readAllInts();
        while (StdIn.isEmpty()) {
            int key = StdIn.readInt();
            int result;
            if ((result = rank(key, ints)) < 0) {
                System.out.println(result);
            }
        }

    }

    private static int rank(int key, int[] arr) {
        int start = 0;
        int end = arr.length - 1;
        Arrays.sort(arr);
        while (start <= end) {
            int mid = (start + end) / 2;
            if (key < arr[mid]) {
                end = mid - 1;
            } else if (key > arr[mid]) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }


}

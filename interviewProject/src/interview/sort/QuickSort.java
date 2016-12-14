package interview.sort;

import static java.lang.System.out;

/**
 * Created by zhao on 12/11/16.
 */
public class QuickSort {

    private int[] input;

    public static void main(String[] args) {
        int[] in = new int[]{6, 5, 3, 1, 8, 7, 2, 4};
        QuickSort qs = new QuickSort();
        qs.sort(in);
        for (int i : in) {
            out.print(i + " ");
        }

    }

    public void sort(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return;
        }
        this.input = numbers;
        quickSort(0, numbers.length - 1);
    }

    public void quickSort(int low, int high) {
        int i = low;
        int j = high;
        int pivot = input[low + (high - low) / 2];
        while (i <= j) {
            while (input[i] < pivot) {
                i++;
            }
            while (input[j] > pivot) {
                j--;
            }
            if (i <= j) {
                swap(i, j);
                i++;
                j--;
            }
        }
        if (low < j) {
            quickSort(low, j);
        }
        if (i < high) {
            quickSort(i, high);
        }

    }

    private void swap(int i, int j) {
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }

}

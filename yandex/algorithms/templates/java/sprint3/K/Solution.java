import java.util.Arrays;

public class Solution {

	public static int[] merge(int[] arr, int left, int mid, int right) {

		if (arr.length <= 1) {
			return arr;
		}

		int[] leftArr = Arrays.copyOfRange(arr, left, mid);
		int[] rightArr = Arrays.copyOfRange(arr, mid, right);

		int leftIndex = 0;
		int rightIndex = 0;
		int index = 0;
		int[] sortedArray = new int[arr.length];

		while (leftIndex < leftArr.length && rightIndex < rightArr.length){
			if (leftArr[leftIndex] <= rightArr[rightIndex]) {
				sortedArray[index] = leftArr[leftIndex];
				leftIndex++;
			} else {
				sortedArray[index] = rightArr[rightIndex];
				rightIndex++;
			}
			index++;
		}

		while (leftIndex < leftArr.length) {
			sortedArray[index] = leftArr[leftIndex];
			leftIndex++;
			index++;
		}
		while (rightIndex < rightArr.length) {
			sortedArray[index] = rightArr[rightIndex];
			rightIndex++;
			index++;
		}


		return sortedArray;
	}

	public static int[] mergeSort(int[] array) {
		if (array.length == 1) {
			return array;
		}

		int[] left = mergeSort(Arrays.copyOfRange(array, 0, array.length/2));

		int[] right = mergeSort(Arrays.copyOfRange(array, array.length/2, array.length));

		int[] result = new int[array.length];

		int l = 0, r = 0, k = 0;
		while (l < left.length && r < right.length) {
			if (left[l] <= right[r]) {
				result[k] = left[l];
				l++;
			} else {
				result[k] = right[r];
				r++;
			}
			k++;
		}

		while (l < left.length) {
			result[k] = left[l];
			l++;
			k++;
		}
		while (r < right.length) {
			result[k] = right[r];
			r++;
			k++;
		}

		return result;
	}

	public static void merge_sort(int[] arr, int left, int right) {
		// Your code
		int[] sortedArray = mergeSort(Arrays.copyOfRange(arr, left, right));
		int idx = 0;
		for (int i = left; i < right; i++) {
			arr[i] = sortedArray[idx];
			idx++;
		}
	}

	public static void main(String[] args) {
		int[] a = {1, 4, 9, 2, 10, 11};
		int[] b = merge(a, 0, 3, 6);
		int[] expected = {1, 2, 4, 9, 10, 11};
		assert Arrays.equals(b, expected);
		int[] c = {1, 4, 2, 10, 1, 2};
		merge_sort(c, 0, 6);
		int[] expected2 = {1, 1, 2, 2, 4, 10};
		assert Arrays.equals(c, expected2);
	}
}
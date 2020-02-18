import java.util.Arrays;

public class CountingSort {

	public void countSort(int[] A, int[] B, int k) {
		// Create an auxiliary array, called C of size k.

		int[] C = new int[k + 1];

		for (int i = 0; i < k; i++) {
			C[i] = 0;
		}

		System.out.println("Step 1: Adding # of elements from A to C.");
		System.out.println("-----------------------------------------");
		for (int j = 0; j < A.length; j++) {
			C[A[j]] = C[A[j]] + 1;
			System.out.println("C: " + Arrays.toString(C));
		}

		System.out.println();
		System.out.println("Step 2: Summing up values from C[i] and C[i+1] and adding it back into C. ");
		System.out.println("--------------------------------------------------------------------------");
		for (int i = 1; i <= k; i++) {
			C[i] = C[i] + C[i - 1];
			System.out.println("C: " + Arrays.toString(C));
		}

		System.out.println();
		System.out.println(
				"Step 3: Getting values from from A[j] and placing it into B. Value at C[A[j]] is also decremented with each pass.");
		System.out.println(
				"-----------------------------------------------------------------------------------------------------------------");
		for (int j = A.length - 1; j >= 0; j--) {
			B[C[A[j]] - 1] = A[j];
			System.out.println("B: " + Arrays.toString(B));
			C[A[j]] = C[A[j]] - 1;
			System.out.println("C: " + Arrays.toString(C));
			System.out.println();
		}
	}

	public static void main(String[] args)

	{
		int[] arr = { 5, 5, 0, 6, 2, 0, 1, 3, 2, 6, 1, 4, 2, 4 };

		int[] result = new int[arr.length];

		CountingSort cs = new CountingSort();

		cs.countSort(arr, result, 6);

		System.out.println("FINAL SORTED ARRAY B: ");
		System.out.println("----------------------");
		System.out.println("B: " + Arrays.toString(result));
	}

}
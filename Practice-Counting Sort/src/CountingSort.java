import java.util.Arrays;

public class CountingSort {

	public void countSort(int[] A, int[] B, int k) {
		// Create an auxiliary array, called C of size k.

		int[] C = new int[k + 1];

		//Assign each index to be 0 in C.
		for (int i = 0; i < k; i++) {
			C[i] = 0;
		}

		System.out.println("STEP 1: Adding the number of elements from A to C.");
		System.out.println("--------------------------------------------------");
		//Adds the number of elements from A to C.
		for (int j = 0; j < A.length; j++) {
			C[A[j]] = C[A[j]] + 1;
			System.out.println("C: " + Arrays.toString(C));
		}
		System.out.println();
		System.out.println("STEP 2: Summing up values from C[i] and C[i+1] and adding it back into C. ");
		System.out.println("-------------------------------------------------------------------------");
		//Sums up values from C[i] and C[i+1] and adds it back to C.
		for (int i = 1; i <= k; i++) {
			C[i] = C[i] + C[i - 1];
			System.out.println("C: " + Arrays.toString(C));
		}

		System.out.println();
		System.out.println(
				"STEP 3: Sorting values from from A[j] into B, while decrementing C[A[j]].");
		System.out.println("-------------------------------------------------------------------------");
		
		//Sorts values from A[j] to B. With each pass, C[A[j]] is also decremented. 
		for (int j = 0; j <= A.length - 1; j++) {
			B[C[A[j]] - 1] = A[j];
			System.out.println("B: " + Arrays.toString(B));
			C[A[j]] = C[A[j]] - 1;
			System.out.println("C: " + Arrays.toString(C));
			System.out.println();
		}
	}

	public static void main(String[] args){
		int[] arr = { 5, 5, 0, 6, 2, 0, 1, 3, 2, 6, 1, 4, 2, 4 };

		int[] result = new int[arr.length];

		CountingSort cs = new CountingSort();

		cs.countSort(arr, result, 6);

		System.out.println("FINAL SORTED ARRAY B: ");
		System.out.println("----------------------");
		System.out.println("B: " + Arrays.toString(result));
	}
}

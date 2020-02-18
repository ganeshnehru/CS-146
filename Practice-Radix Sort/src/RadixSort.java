import java.util.Arrays;

public class RadixSort {

	public void radixSort(int[] A, int d)
	{
		for(int i = 0; i <=d; i++)
		{
			//Use counting sort.
			int[] B = new int[A.length];
			int[] C = new int[d];
			
			//Check how many number of an integer is present in the array A.
			for(int a = 0; a < A.length; a++)
			{
				C[A[a]] = C[A[a]] + 1;
			}
			
			for(int b = 0; b <= d; b++)
			{
				C[i] = C[i] + C[i-1];
			}
			
			for (int j = A.length - 1; j >= 0; j--) {
				B[C[A[j]] - 1] = A[j];
				System.out.println("B: " + Arrays.toString(B));
				C[A[j]] = C[A[j]] - 1;
				System.out.println("C: " + Arrays.toString(C));
				System.out.println();
		}
	}
}
}

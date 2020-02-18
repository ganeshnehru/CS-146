import java.util.Arrays;

public class BucketSort {

	public void bucketSort(int[] A)
	{
		int n = A.length;
		
		int[] B = new int[n-1];
		
		for(int i = 0; i <= n-1; i++)
		{
			B[i] = 0;
		}
		
		for(int i = 0; i <= n; i++)
		{
			B[(int) Math.floor(n*A[i])] = A[i];
		}
		
		for(int i = 0; i <= n-1; i++)
		{
			int key = A[i];
			int j = i - 1;

			while (j >= 0 && A[j] > key) {
				A[j + 1] = A[j];
				j = j - 1;
			}

			A[i + 1] = key;
		}
	}
	
	public static void main(String[] args)
	{
		BucketSort bucket = new BucketSort();
		
		int[] arr = {8,5,4,7,3,2,8,3,5,2,1,5};
		
		bucket.bucketSort(arr);
		
		System.out.println(Arrays.toString(arr));
	}
	
}

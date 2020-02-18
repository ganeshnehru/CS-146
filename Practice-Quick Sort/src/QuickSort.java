import java.util.Arrays;

public class QuickSort {

	public void quickSort(int[] A, int p, int r)
	{
		if( p < r) {
		int q = partition(A, p, r);
		
		quickSort(A, p, q-1);
		quickSort(A, q+1, r);
		}
	}
	
	public int partition(int[] A, int p, int r)
	{
		int x = A[r];
		
		int i = p - 1;
		
		for(int j = p ; j <= r-1; j++)
		{
			if(A[j] <= x)
			{
				i = i + 1;
				
				int temp = A[i];
				A[i] = A[j];
				A[j] = temp;
			}
		}
		int temp2 = A[i+1];
		A[i+1] = A[r];
		A[r] = temp2;
		
		return i+1;
	}
	
	public static void main(String[] args)
	{
		QuickSort qs = new QuickSort();
		
		int[] arr = {3, 6, 1, 5, 8, 2, 4, 7, 3};
		//int[] arr2 = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
		
		qs.quickSort(arr, 0, 8);
		
		System.out.println(Arrays.toString(arr));
	}
}

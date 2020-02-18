import java.util.Arrays;

public class MergeSort {

	//Prints sequence.
	public String seqPrint(int[] array, int midInd, int rightInd)
	{
		StringBuilder str = new StringBuilder();
		
		for(int i = midInd; i < rightInd; i++)
		{
			str.append("  " + array[i]);
		}
		return str.toString();
	}
	
	public void merge(int[] unsorted, int leftInd, int midInd, int rightInd)
	{
		//Define sizes of two subarrays.
		int leftSize = midInd - leftInd + 1;
		int rightSize = rightInd - midInd;
		
		//Define two subarrays with the sizes we defined above.
		int[] leftArray = new int[leftSize];
		int[] rightArray = new int[rightSize];
		
		//Copy elements from 'unsorted' into 'leftArray' and 'rightArray.'
		for(int i = 0; i < leftSize; i++)
		{
			leftArray[i] = unsorted[leftInd + i];
		}
		for(int j = 0; j < rightSize; j++)
		{
			rightArray[j] = unsorted[midInd + 1 + j];
		}
		
		//Merge the 'leftArray' and 'rightArray.'
		int s = 0;
		int t = 0;
		int r = leftInd;
		
		while(s < leftSize && t < rightSize)
		{
			if(leftArray[s] <= rightArray[t])
			{
				unsorted[r] = leftArray[s];
				s++;
			}
			else
			{
				unsorted[r] = rightArray[t];
				t++;
			}
			r++;
		}
		
		while(s < leftSize)
		{
			unsorted[r] = leftArray[s];
			s++;
			r++;
		}
		while(t < rightSize)
		{
			unsorted[r] = rightArray[t];
			t++;
			r++;
		}
		
		System.out.printf("Merge: %s%n", seqPrint(unsorted, leftInd, midInd));
		System.out.printf("       %s%n", seqPrint(unsorted, midInd + 1, rightInd));
		System.out.printf("       %s%n", seqPrint(unsorted, leftInd, rightInd));
	}
	
	//Recursively merges 'left' and 'right' array.
	public void mergeSort(int[] A, int p, int r)
	{
		if(p < r)
		{
			int q = (p+r) / 2;
			
			System.out.printf("Divide: %s%n", seqPrint(A, p, q));
			System.out.printf("        %s%n", seqPrint(A, q + 1, r));
			System.out.printf("        %s%n%n", seqPrint(A, p, r));
			
			mergeSort(A, p, q);
			mergeSort(A, q+1, r);
			merge(A, p, q, r);
		}
	}
	
	
	
	public static void main(String[] args)
	{
		int[] array = {18, 25, 6, 9, 15, 12, 5, 20, 11, 30};
		
		MergeSort ms = new MergeSort();
		//System.out.print("Unsorted: ");
		ms.mergeSort(array, 0, array.length - 1);
		
		System.out.println("Sorted: " + Arrays.toString(array));
	}
}

import java.util.Arrays;

public class InsertionSort {

	int array[];

	public InsertionSort(int array[]) {
		this.array = array;
	}

	public void sorter(int[] array) {
		// 'j' ranges from the second index to the end of the array.
		for (int j = 1; j < array.length; ++j) {
			int key = array[j];
			int i = j - 1;

			while (i >= 0 && array[i] > key) {
				array[i + 1] = array[i];
				i = i - 1;
			}

			array[i + 1] = key;

			System.out.printf("Swaping: " + "%s%n ", seqPrint(array));

		}
	}
	
	// Prints sequence.
	public String seqPrint(int[] array) {
		StringBuilder str = new StringBuilder();

		for (int i = 0; i < array.length; i++) {
			str.append("  " + array[i]);
		}

		return str.toString();
	}

	public static void main(String[] args) {
		int[] array1 = { 18, 25, 6, 9, 15, 12, 5, 20, 11, 30 };

		InsertionSort iS = new InsertionSort(array1);

		iS.sorter(array1);
		System.out.print("Sorted: ");
		System.out.println(Arrays.toString(array1));
	}
}


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class MaxHeapProcess {

	//Temp ArrayList to hold process values for reference.
	static ArrayList<Integer> pArray = new ArrayList<>();
	
	// Responsible for maintaining max-heap property.
	public static void maxHeapify(ArrayList<Process> A, int heapSize, int i) 
	{
		//Initializes the largest as the root.
		int largest = i;
		//Left (i)
		int l = 2 * i + 1;
		//Right(i)
		int r = 2 * i + 2;

		//Checks if LEFT(i) is largest or heap is largest.
		if (l < heapSize && A.get(l).getPriority() < A.get(largest).getPriority()) 
		{
			largest = l;
		} 
		
		else 
		{
			largest = i;
		}
		//Checks if RIGHT(i) is largest.
		if (r < heapSize && A.get(r).getPriority() < A.get(largest).getPriority()) 
		{
			largest = r;
		}
		//If i is not largest, then swap A[i] with A[largest].
		if (largest != i) 
		{
			Process swap = A.get(i);
			A.set(i, A.get(largest));
			A.set(largest, swap);

			maxHeapify(A, heapSize, largest);
		}
	}

	// buildMaxHeap method goes through each node and runs maxHeapify method on each node, to maintain the max-heap property.
	public static void buildMaxHeap(ArrayList<Process> A) 
	{
		//Initializes the heapSize.
		int heapSize = A.size() - 1;

		//Starting from the bottom non-leaf node and working its way up, applying the maxHeapify() function on the non-leaf nodes.
		for (int i = heapSize / 2 - 1; i >= 0; i--) 
		{
			maxHeapify(A, heapSize, i);
		}
	}

	// heapSort sorts the array 'A' in place. Starts from the bottom of the array, working its way up to the second index of the array.
	public static void heapSort(ArrayList<Process> A) {
		
		//Initializes the heapSize;
		int heapSize = A.size();
		
		//Ensure that the given 
		buildMaxHeap(A);

		for (int i = heapSize - 1; i >= 1; i--) 
		{
			Process temp = A.get(0);
			A.set(0, A.get(i));
			A.set(i, temp);

			maxHeapify(A, i, 0);
		}
	}

	// Returns the process ID in the array 'A,' with the largest priority.
	public static int heapMaximum(ArrayList<Process> A) 
	{
		System.out.print("The process ID with the highest priority is P"+ A.get(0).getPid() + " which has a priority value of ");
		//Returns the priority from the top most element in A.
		return A.get(0).getPriority();
	}

	// Removes the largest element in the array 'A' and returns it.
	public static int heapExtractMax(ArrayList<Process> A, int heapSize) 
	{
		//Initialize max value.
		int max = 0;
		//If nothing is present in A, then return error.
		if (heapSize < 0) 
		{
			System.out.println();
			System.out.println("***No process in list.***");
			System.out.println();
		}
		//If A is not empty, remove the top element from A, and call maxHeapify() to maintain max-heap property.
		if(!A.isEmpty()) {
		max = A.get(0).getPriority();
		A.remove(0);
		maxHeapify(A, heapSize, 0);
		}
		//Return the max value that was removed from the A.
		return max;
		}

	// heapIncreaseKey increases the value of 'i' in the array 'A,' to a high value, 'k.'
	public static void heapIncreaseKey(ArrayList<Process> A, int processID, int priority) 
	{	
		//If the input process ID is not present in pArray, then return error.
		if(!pArray.contains(processID))
		{
			System.out.println();
			System.out.println("***No such process exists.***");
			System.out.println();
			return;
		}
		
		//If the input process ID is present in A, then change the priority of that process ID. 
		for(Process p : A)
		{
			//Checks if input process ID exists in A.
			if(p.getPid() == processID)
			{
				//If chosen priority value is less than the current priority value, return error.
				if(p.getPriority() > priority)
				{
					System.out.println();
					System.out.println("***New key is smaller than current key. Queue has not been changed.***");
					System.out.println();
					break;
				}
				//Else, replace the current priority with the inputed priority value.
				else 
				{
					p.setPriority(priority);
				}
				
			}
		}
		//Call heapSort() to make sure the ArrayList is still sorted in a max-heap fashion.
		heapSort(A);
	}

	//Inserts element at the end of the list, then applies max-heap property.
	public static void maxHeapInsert(ArrayList<Process> A, int key, int pri)
	{
		//Create new Process object, called 'p.'
		Process p = new Process(key, pri);
		//Add 'p' into A.
		A.add(p);
		//Add key into pArray for reference. 
		pArray.add(key);
		//Call heapSort() to sort and maintain the max-heap property of A.
		heapSort(A);
	}

	//Helper method to display the process and priority in the format, Process#(Priority).
	public static void display(ArrayList<Process> A)
	{
		//Initializes ArrayList size.
		int size = A.size();
		//Prints out all the processes and priority in A, in the format, Process#(Priority).
		for (int i = 0; i < size; ++i) 
		{
			System.out.print("[P"+A.get(i).getPid() + "("+A.get(i).getPriority()+")  ");
		}
		System.out.println();

	}

	//Main method.
	public static void main(String[] args) 
	{
		//Create new ArrayList.
		ArrayList<Process> aList = new ArrayList<>();
		
		//Initialize process value.
		int process = 1;
		
		//Create random variable.
		Random rand = new Random();
		int n = rand.nextInt(99) + 10;
		
		//Create a new ArrayList to hold unique priority values.
		ArrayList<Integer> uniquePri = new ArrayList<>();
		
		//Scanner function to get user's input.
		Scanner scan = new Scanner(System.in);
		
		//Main interface for user input.
		while(true)
		{
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("Enter one of the following numbers to complete a task.");
			System.out.println("1: Add random process to queue.");
			System.out.println("2: Remove a process with the highest priority.");
			System.out.println("3: Increase process's priority.");
			System.out.println("4: Display process with the largest priority.");
			System.out.println("5: Insert specific process into queue.");
			System.out.println("6: Exit application.");
			System.out.print("Input: ");
			
			//Obtains user input.
			int input = scan.nextInt();
			
			//If user inputs 1, then program adds processes into heap tree.
			if(input == 1)
			{
				System.out.println();
				System.out.println("Selected: 1");
				System.out.println("~~~~~~~~~~~~");
				
				//Generates random integers.
				int priority = rand.nextInt(99) + 1;
				
				//Adds the generated random integers into uniquePri if the ArrayList is empty.
				if(uniquePri.isEmpty())
				{
					uniquePri.add(priority);
				}
				//If uniquePri is not empty and uniquePri already contains the random number generated, 
				//then generate another random number and add it to uniquePri.
				if(!uniquePri.isEmpty() && uniquePri.contains(priority))
				{
					priority = rand.nextInt(99) + 1;
					uniquePri.add(priority);
				}
				//If the random number generated is not already present in uniquePri, then add that number into uniquePri.
				if(!uniquePri.contains(priority))
				{
					uniquePri.add(priority);
				}
				
				//Create a new Process object.
				Process p = new Process(process, priority);
				
				//Add process int value into pArray for reference. 
				pArray.add(process);
				
				//Increment process by 1.
				process++;
				//Add the process object into aList.
				aList.add(p);
				//Call heapSort() to sort all the process IDs added in the queue by highest priority value. 
				heapSort(aList);
				//Display all the process IDs and priority in aList.
				display(aList);
				//Empty line to split sections.
				System.out.println();
			}
			//If user enters 2 as an input, then remove the process ID and priority based on the largest priority value in queue.
			else if(input == 2)
			{
				System.out.println();
				System.out.println("Selected: 2");
				System.out.println("~~~~~~~~~~~~");
				
				//Calls the heapExtractMax() method to display the process with the largest priority value 
				heapExtractMax(aList, 0);
				
				//If aList is empty, then state that there are no processes inside the ArrayList.
				if(aList.isEmpty())
				{
					System.out.println();
					System.out.println("***No process in list.***");
					System.out.println();
				}
				//If aList is not empty, then call heapSort() to sort aList after the removal of the process with 
				//the highest priority value. Display all the process remaining in aList, followed by an empty 
				//line to split sections.
				if(!aList.isEmpty()) 
				{
				heapSort(aList);
				display(aList);
				System.out.println();
				}
				}
			//If user enters 3, program changes the priority of a process that is present in aList.
			else if(input == 3)
			{
				System.out.println();
				System.out.println("Selected: 3");
				System.out.println("~~~~~~~~~~~~");
				
				//Gets user input for the process ID they wish to change the priority.
				System.out.print("Enter process ID: ");
				//Random priority generator.
				int changeProcess = scan.nextInt();
				//If the process ID entered does not match the process IDs inside aList, return error.
				if(!pArray.contains(changeProcess))
				{
					System.out.println();
					System.out.println("***No such process exists.***");
					System.out.println();
				}
				//If the entered process ID does match the process ID in aList, then get request for new priority value.
				if(pArray.contains(changeProcess)) 
				{
				System.out.print("Enter new priority: ");
				int changePriority = scan.nextInt();
				//Call heapIncreaseKey() to update the current priority index with the new given priority value.
				heapIncreaseKey(aList, changeProcess, changePriority);
				//Display all the process inside aList.
				display(aList);
				//Empty line to split sections.
				System.out.println();
				}
			}
			//If user enters 4, then the program returns the process ID with the highest priority. 
			else if(input == 4)
			{
				System.out.println();
				System.out.println("Selected: 4");
				System.out.println("~~~~~~~~~~~~");			
				System.out.println(heapMaximum(aList));
				System.out.println();
			}
			
			//If user enters 5, then they can enter any specific process into aList, with a randomized priority value.
			else if(input == 5)
			{
				System.out.println();
				System.out.println("Selected: 5");
				System.out.println("~~~~~~~~~~~~");
				//Random priority generator.
				int priority = rand.nextInt(99) + 1;
				System.out.print("Enter the process you wish to add to the queue: ");
				//Calls maxHeapInsert to add the user's process input into aList.
				maxHeapInsert(aList, scan.nextInt(), priority);
				//Display all the process inside aList.
				display(aList);
				System.out.println();
			}
			//If user enters 6, then the application exists.
			else if(input == 6)
			{
				System.out.println();
				System.out.println("***Application exited.***");
				//Exit function.
				System.exit(0);
				System.out.println();
			}
		}
	}
		
}

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

//Nodes to implement chaining. 
class HTNode 
{
	// Elements in a node: key and value.
	int key;
	int value;

	// Reference from a node to an adjacent node.
	HTNode next;

	// Constructor with parameters key and value.
	public HTNode(int key, int value) 
	{
		this.key = key;
		this.value = value;
	}
}

// Represents the hash table function.
public class HashTable {

	// Create an array of type HTNode, called tables.
	static HTNode[] table;

	// Create a new variable called slots to indicate the current size of the array.
	static int slots;

	// Variable to indicate the total size of the array.
	static int size;

	// Constructor that initializes the array size to be 11, slots to be 11, and
	// total array size to be 0.
	public HashTable() 
	{
		table = new HTNode[11];
		slots = 11;
		size = 0;

		// Responsible for creating empty chains.
		for (int i = 0; i < slots; i++) 
		{
			table[i] = null;

		}
	}

	// Hash function, responsible for finding an index to place nodes into the
	// array.
	public static int hashFuncIndex(int key) 
	{
		int index = Math.abs(key) % slots;
		return index;
	}

	// Used to remove node from the hash table.
	public static int chainedHashDelete(HashTable T, int key) 
	{
		// Finds an index using the hash function method.
		int slotIndex = hashFuncIndex(key);

		// Create a new head node thats pointed to the index found by the hash function,
		// in the table array.
		HTNode head = table[slotIndex];

		// Create a new previous node and have it point to null.
		HTNode previous = null;

		if (head == null) 
		{
			return -1;
		}
		// Responsible for searching for key in node.
		while (head != null) 
		{
			if (head.key == key) 
			{
				break;
			}
			previous = head;
			head = head.next;
		}
		// Decrement the size by 1.
		size--;

		//Responsible for removing node.
		if (previous != null) 
		{
			previous.next = head.next;
		} 
		else 
		{
			table[slotIndex] = head.next;
		}
		return head.value;
	}

	//Search method which takes key input and returns value of node.
	public static int chainedHashSearch(int key) 
	{
		//Variable holds the index of a given key, using the calculation from the hash function method. 
		int slotIndex = hashFuncIndex(key);
		//Create a new node, called head and have it placed in the index of the table array, of slotIndex. 
		HTNode head = table[slotIndex];

		//Searches for a key in the chain of nodes.
		while (head != null) 
		{
			if (head.key == key) 
			{
				return head.value;
			} 
			else 
			{
				head = head.next;
			}
		}
		//Returns -1 if key is not found.
		System.out.println("**PROCESS WITH THAT PRIORITY VALUE DOES NOT EXIST."); 
		
		return 0;
	}

	//Insert method which allows user to input key and value 
	public static void chainedHashInsert(int key, int value) 
	{
		int slotIndex = hashFuncIndex(key);
		HTNode head = table[slotIndex];
		
		//Responsible for checking if key already exists.
		while (head != null) 
		{
			if (head.key == (key)) 
			{
				head.value = value;
				return;
			}
			head = head.next;
		}
		
		//Responsible for inserting key into the chain, while also increasing the size by 1.
		size = size + 1;

		head = table[slotIndex];

		HTNode node = new HTNode(key, value);
		node.next = head;

		table[slotIndex] = node;

	}

	//Finds the priority in the BST using a priority ID input.
	public static int findPriority(BinarySearchTree tree, int pID)
	{
		ArrayList<Node> al = new ArrayList<>();
		tree.InorderTreeWalk(tree.root, al);
		for (Node n1 : al) 
		{
			if (n1.value == pID) 
			{
				return n1.key;
			}
		}
		return -1;
	}

	//Test class.
	public static void main(String[] args) 
	{
		BinarySearchTree tree = null;
		tree = new BinarySearchTree();

		HashTable list = new HashTable();

		Random r = new Random();

		int pID = 1;
		int priority = 0;

		Scanner scan = new Scanner(System.in);
		while (true)

		{
			
			//Menu options.
			System.out.println("\n");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~MENU~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("~~~~~~~~~~~CHOOSE ONE OF THE FOLLOWING OPTIONS~~~~~~~~~~~~~~");

			System.out.println("1: Add process with random priority value.");

			System.out.println("2: Add a process name and priority value of your choice.");

			System.out.println("3: Add a new process into the BST with just a priority code of your choice.");

			System.out.println("4: Delete a node by entering the process name.");

			System.out.println("5: Display all nodes in the BST.");

			System.out.println("6: Display process name from priority value in the Hash Table.");

			System.out.println("7: Add process name and priority value of your choice into the Hash Table.");

			System.out.println("8: Delete a node from the Hash Table from a given priority value.");
			
			System.out.println("9: Display all nodes in the Hash Table as a chained linked list.");
			
			System.out.println("0: Exit the application.");

			System.out.print("Input: ");

			// Obtains user input.
			int input = scan.nextInt();

			System.out.println(input);

			//When user chooses 1 as their input, the program displays Process names (e.g. P1, P2, etc.), with random priority values.
			if (input == 1) 
			{
				priority = r.nextInt(999) + 1;
				Node n = new Node(priority, pID);
				tree.TreeInsert(tree, n);
				pID++;
				ArrayList<Node> al = new ArrayList<>();
				System.out.println("*Binary Search Tree List:");
				tree.InorderTreeWalk(tree.root, al);
				for (Node n1 : al) 
				{
					list.chainedHashInsert(n1.key, n1.value);
				}

			}
			
			//When the user chooses 2 as their input, they could add a specific node with specific Process name and priority value.
			else if(input == 2)
			{
				System.out.println("*Enter process name you want to insert into BST (e.g. P1): ");
				String str = scan.next();
				String s = str.substring(1);
				int val = Integer.parseInt(s);
				
				System.out.println("*Enter priority value you wish to give to P"+val+" (e.g. 123)");
				String pr = scan.next();
				int prt = Integer.parseInt(pr);
				
				Node node = new Node(prt, val);
				tree.TreeInsert(tree, node);
				
				System.out.println("**The process P"+val+" with priority value of "+prt+" has been added to the BST.");
				
				ArrayList<Node> al = new ArrayList<>();
				tree.InorderTreeWalk(tree.root, al);
			}

			//When user chooses 3 as their input, the program allows the user to add a new process into the BST with a priority code of the user's
			//choice.
			else if (input == 3) 
			{
				// Get priority.
				System.out.println("*Enter Priority value to add (e.g. 123): ");
				String pr = scan.next();
				int prt = Integer.parseInt(pr);
				int newPID = pID +1;
				Node n = new Node(prt, newPID);
				tree.TreeInsert(tree, n);
				pID++;
				ArrayList<Node> al = new ArrayList<>();
				System.out.println("**P"+newPID+" with priority value of "+prt+" has been added into the BST.");
				tree.InorderTreeWalk(tree.root, al);
				for (Node n1 : al) 
				{
					list.chainedHashInsert(n1.key, n1.value);
				}
				
				
			}
			
			//When user chooses 4 as their input, the program allows the user to delete a node by entering the Process name (e.g. P1).
			else if (input == 4) 
			{
				//Takes user input after the letter 'P' and uses that value to delete the node associated with that value from the BST.
				System.out.println("*Enter Process name to delete (e.g. P1): ");
				String str = scan.next();
				String s = str.substring(1);
				int val = Integer.parseInt(s);

				int pval = findPriority(tree, val);
				System.out.println("\n");
				System.out.println("**Removed P" + val + " with priority value of " + pval + ".");

				Node n = tree.IterativeTreeSearch(tree.root, pval);

				tree.TreeDelete(tree, n);

				ArrayList<Node> a2 = new ArrayList<>();
				tree.InorderTreeWalk(tree.root, a2);
				
			} 
			
			//When user chooses 5 as their input, the program returns all the nodes in the BST, in sorted order, with BST property.
			else if (input == 5) 
			{
				ArrayList<Node> a3 = new ArrayList<>();
				System.out.println("**Displaying all nodes in BST...");
				tree.InorderTreeWalk(tree.root, a3);
			} 

			//When user chooses 6 as their user input, the program takes in priority value input from user and then displays the process name from
			//the Hash Table.
			else if (input == 6) {
				// Get priority.
				System.out.println("*Enter priority value (e.g. 123): ");
				String pr = scan.next();
				int prt = Integer.parseInt(pr);
				
				int processID = chainedHashSearch(prt);
				
				System.out.println("**Process Name: P" + processID);
			}
			
			//When the user chooses 7 as their input, the program asks for the users input for process name and priority value to 
			//add to the hash table.
			else if(input == 7)
			{
				System.out.println("*Enter a Process name (e.g. P1): ");
				
				String str = scan.next();
				String s = str.substring(1);
				int val = Integer.parseInt(s);
				
				System.out.println("*Enter priority value (e.g. 123): ");
				int value = scan.nextInt();
				
				chainedHashInsert(value, val );
				
				System.out.println("**Added P"+val+" with priority value of "+ value+" into the hash table.");
			}

			//When user chooses 8 as their input, the program takes in the priority value as an input, then deletes the node which contains
			//the process name associated with that priority value, from the Hash Table.
			else if (input == 8) {
				System.out.println("Enter priority value: ");
				String str = scan.next();
				
				int val = Integer.parseInt(str);
				
				int pVal =	chainedHashDelete(list, val);
				
				System.out.println("Removed P"+pVal + " with priority value of "+val+" from the Hash Table.");
				
				
			}
			
			//When user chooses 9 as their input, the program displays all the nodes that are listed in the Hash Table.
			else if (input == 9) {
				System.out.println("Listing all nodes in Hash Table...:");

				for (int i = 0; i < 11; i++) {
					if (table[i] != null) {
						HTNode h = table[i];
						while (h != null) {
							System.out.print("[P" + h.value + " - " + h.key + "]");

							if (h.next != null) {
								System.out.print("-->");
							}

							h = h.next;
						}

						System.out.println("\n");
					}
				}
				
			} 
			
			//Then the user chooses 0 as their input, the program exits the application.
			else if (input == 0) 
			{
				System.out.println("Application exited.");
				System.exit(0);
			}
			
			
			//When the user chooses another other than the menu option allows, the program displays that it was an invalid option, and then asks for 
			//a valid input.
			else 
			{
				System.out.println("Not a valid input. Try again.");
			}

		}
	}
}
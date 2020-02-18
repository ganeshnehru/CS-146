import java.util.Random;
import java.util.Scanner;

//Node class which represents the node functionality of a RB Tree, along with all the elements of a node.
class Node<K extends Comparable<K>> {

	// Since we are working with a Red Black Tree, we define the two colors of what
	// a node could be.
	public static final int RED = 1;
	public static final int BLACK = 0;

	// Define the elements in a node: process ID(pID), priority, and color.
	public K priority;
	int pID;
	public int color;

	// Define new nodes: parent(p), left(l) and right(r).
	Node<K> p;
	Node<K> l;
	Node<K> r;

	// Constructor with parameter, priority.
	Node(K priority) {
		this();
		this.priority = priority;
	}

	// Constructor with parameters pID and priority.
	Node(int pID, K priority) {
		this();
		this.pID = pID;
		this.priority = priority;
	}

	// Constructor that initializes parent, left, right and color.
	Node() {
		p = null;
		l = null;
		r = null;
		color = BLACK;
	}
}

// RB Tree class which holds all the properties of an RB Tree; from insertion,
// deletion, rotation, etc.
public class RBTree<K extends Comparable<K>> {
	// Define a new node, setNil and root. setNil represents the nil value of a
	// node, whereas the root node
	// represents the top most node in the Red Black Tree.
	private Node<K> setNil = new Node<K>();

	// Initially set rootNode to nil.
	private Node<K> rootNode = setNil;

	// Constructor that initializes root node's parent, left and right subtrees to
	// sentinel.
	public RBTree() {
		rootNode.l = setNil;
		rootNode.r = setNil;
		rootNode.p = setNil;
	}

	// Add method which allows users to add elements to the RB Tree.
	public void addNode(int pID, K priority) {
		addNode(new Node<K>(pID, priority));
	}

	// Inserts elements into the RB Tree, while maintaining the RB Tree property.
	private void addNode(Node<K> z) {
		// Define new nodes y and x. Set y to setNil and x to the root.
		Node<K> y = setNil;
		Node<K> x = rootNode;

		// Set y to x if x is not nil.
		while (!checkNil(x)) {
			y = x;

			// If z's priority is less than x's priority, go to left subtree.
			if (z.priority.compareTo(x.priority) < 0) {
				x = x.l;
			}

			// If z's priority is greater than x's, go right.
			else {
				x = x.r;
			}
		}

		// Set y as z's parent.
		z.p = y;

		// Check if y is nil. If so, set z as the root node.
		if (checkNil(y)) {
			rootNode = z;
		}

		// Else set z as y's left child.
		else if (z.priority.compareTo(y.priority) < 0) {
			y.l = z;
		}

		// Else set z as y's right child.
		else {
			y.r = z;
		}

		// Set z's left and right child to nil and color z red.
		z.l = setNil;
		z.r = setNil;
		z.color = Node.RED;

		// Fix up the add, by calling addFix(), to ensure the RB Tree did not violate
		// its properties.
		additionFix(z);
	}

	// Fixes up properties after add function is performed.
	private void additionFix(Node<K> z) {
		// Define a node y, and set it to nil.
		Node<K> y = setNil;

		// While z's parent's color is red, perform the following operations.
		while (z.p.color == Node.RED) {
			// If z's parent matches z's cousin, set y as z's cousin.
			if (z.p == z.p.p.l) {
				y = z.p.p.r;

				// 1st Case: Re-color if y is RED.
				if (y.color == Node.RED) {
					z.p.color = Node.BLACK;
					y.color = Node.BLACK;
					z.p.p.color = Node.RED;
					z = z.p.p;
				}
				// 2nd Case: Left rotate if z matches z's right cousin.
				else if (z == z.p.r) {
					z = z.p;
					lRotate(z);
				}

				// 3rd Case: If y is black and z is a child of the left subtree, re-color and
				// rotate around z's grandpa.
				else {
					z.p.color = Node.BLACK;
					z.p.p.color = Node.RED;
					rRotate(z.p.p);
				}
			}

			// If z's parent is the right child of z's parent, place y there and perform the
			// following operations.
			else {
				y = z.p.p.l;

				// 1st : If y is the color red, then re-color it.
				if (y.color == Node.RED) {
					z.p.color = Node.BLACK;
					y.color = Node.BLACK;
					z.p.p.color = Node.RED;
					z = z.p.p;
				}

				// 2nd Case: If y is the color black and z is a child of the left subtree, then
				// make z as its parent, followed by a right rotate.
				else if (z == z.p.l) {
					z = z.p;
					rRotate(z);
				}
				// 3rd Case: If y is the color black and z is a child of the right subtree, then
				// re-color and rotate around z's grandpa.
				else {
					z.p.color = Node.BLACK;
					z.p.p.color = Node.RED;
					lRotate(z.p.p);
				}
			}
		}

		// Make the root node black.
		rootNode.color = Node.BLACK;
	}

	// Method is responsible for rotating a node leftward, around the node x.
	private void lRotate(Node<K> x) {
		// Define a new node y, and set it as x's right child.
		Node<K> y;
		y = x.r;
		x.r = y.l;

		// If y's left child is not null, then set x as y's left child's parent.
		if (!checkNil(y.l)) {
			y.l.p = x;
		}

		// Link y's parent node to x's parent node.
		y.p = x.p;

		// If y's left child is not null, then set x as y's left child's parent.
		if (checkNil(x.p)) {
			rootNode = y;
		}

		// If x is in the position of x's parent's left child, then place y there.
		else if (x.p.l == x) {
			x.p.l = y;
		}

		// Else, place y on the right child.
		else {
			x.p.r = y;
		}

		// Set x as y's left child and set y as x's parent.
		y.l = x;
		x.p = y;
	}

	// Method is responsible for rotating a node rightward, around the node, x.
	private void rRotate(Node<K> y) {

		// Define a new node x, and set it as y's left child.
		Node<K> x = y.l;

		// Link y.left to x.right to perform rotation.
		y.l = x.r;

		// If x's right child is not nil, then set y as x's right parent.
		if (!checkNil(x.r)) {
			x.r.p = y;
		}

		// Link x.p and y.p to perform rotation.
		x.p = y.p;

		// If y's parent is nil, then make x the root.
		if (checkNil(y.p)) {
			rootNode = x;
		}

		// If y is in y's parent's right position, then place x there.
		else if (y.p.r == y) {
			y.p.r = x;
		}

		// Else place x in the left subtree.
		else {
			y.p.l = x;
		}

		// Set y as x's right child and set x as y's parent.
		x.r = y;
		y.p = x;
	}

	// Transplant method helps swap positions of two nodes.
	public void transplant(Node<K> u, Node<K> v) {

		// If u's parent is nil, set v as the root node.
		if (checkNil(u.p)) {
			rootNode = v;
		}
		// Else if u matches u's left brother, set v as that brother.
		else if (u == u.p.l) {
			u.p.l = v;
		}
		// Else set v as the right brother of u.
		else {
			u.p.r = v;
		}
		// Link both u's and v's parents together.
		v.p = u.p;
	}

	// Responsible for removal of a node from RB Tree.
	public void deleteNode(Node<K> n) {
		// Define a new node z and use the find method to locate the node containing the
		// given priority.
		Node<K> z = find(n.priority);

		// Define two new nodes, x and y and set them to nil.
		Node<K> x = setNil;
		Node<K> y = setNil;

		// If either z's left or right is nil, then set y to z.
		if (checkNil(z.l) || checkNil(z.r)) {
			y = z;
		}

		// Else, find the successor node of z and place it in y.
		else {
			y = findSuccessorNode(z);
		}

		// If y left child is not nil, then set x as y's left child.
		if (!checkNil(y.l)) {
			x = y.l;
		}

		else {
			x = y.r;
		}

		// Set up a link between x's parent and y's parent.
		x.p = y.p;

		// Check if y's parent is nil, if so, set x as the root.
		if (checkNil(y.p)) {
			rootNode = x;
		}

		// Else if y is a child on the left subtree, then set x to be y's left brother.
		else if (!checkNil(y.p.l) && y.p.l == y) {
			y.p.l = x;
		}

		// Else if y is a child on the right subtree, then set x to be y's right
		// brother.
		else if (!checkNil(y.p.r) && y.p.r == y) {
			y.p.r = x;
		}

		// If y does not match z, move y's data into z node.
		if (y != z) {
			z.priority = y.priority;
		}

		// If y's color is black, call delFix() to maintain the RB Tree property.
		if (y.color == Node.BLACK) {
			deletionFix(x);
		}
	}

	// Responsible for maintaining RB Tree properties after deletion of a node.
	private void deletionFix(Node<K> x) {
		// Define a new node w.
		Node<K> w;

		// While x is not the root and x is not black, perform the following operations.
		while (x != rootNode && x.color == Node.BLACK) {

			// If x matches x's parent's left child, perform the following operations.
			if (x == x.p.l) {

				// Set w as x's parent's right child.
				w = x.p.r;

				// 1st Case: If w is red, perform the following operations.
				if (w.color == Node.RED) {
					w.color = Node.BLACK;
					x.p.color = Node.RED;

					lRotate(x.p);

					w = x.p.r;
				}

				// 2nd Case: If w's left and right children are black, perform the following
				// operations.
				if (w.l.color == Node.BLACK && w.r.color == Node.BLACK) {
					w.color = Node.RED;
					x = x.p;
				} else {
					// 3rd Case:If child of w's right subtree is black, perform the following
					// operations.
					if (w.r.color == Node.BLACK) {
						w.l.color = Node.BLACK;
						w.color = Node.RED;

						rRotate(w);

						w = x.p.r;
					}
					// 4th Case: If w is black and w's right child is red, perform the following
					// operations.
					w.color = x.p.color;
					x.p.color = Node.BLACK;
					w.r.color = Node.BLACK;

					lRotate(x.p);

					x = rootNode;
				}
			}
			// Else set w as x's parent's left child, then perform the following operations.
			else {
				w = x.p.l;

				// 1st Case: If w is the color red, perform the following operations.
				if (w.color == Node.RED) {
					w.color = Node.BLACK;
					x.p.color = Node.RED;

					rRotate(x.p);

					w = x.p.l;
				}

				// 2nd Case: If w's left and right children are black, perform the following
				// operations.
				if (w.r.color == Node.BLACK && w.l.color == Node.BLACK) {
					w.color = Node.RED;
					x = x.p;
				} else {
					// 3rd Case: If only the left child of w is black, perform the following
					// operations.
					if (w.l.color == Node.BLACK) {
						w.r.color = Node.BLACK;
						w.color = Node.RED;

						lRotate(w);

						w = x.p.l;
					}

					// 4th Case: If w is black and w's left child is red, perform the following
					// operations.
					w.color = x.p.color;
					x.p.color = Node.BLACK;
					w.l.color = Node.BLACK;

					rRotate(x.p);

					x = rootNode;
				}
			}
		}
		// Make x the color black.
		x.color = Node.BLACK;
	}

	// Finds a particular node with a given priority value.
	public Node<K> find(K priority) {

		// Define a new node x, and place it as the root node.
		Node<K> x = rootNode;

		// While x is not nil, check if x's priority value matches the given priority
		// value.
		while (!checkNil(x)) {
			// Return that node if x's priority value matches the given priority value.
			if (x.priority.equals(priority)) {
				// Returns the node with the given priority value.
				return x;
			}

			// Else, if the provided priority value is less than x's priority value, go down
			// right subtree.
			else if (x.priority.compareTo(priority) < 0) {
				x = x.r;
			}

			// Else, go to left.
			else {
				x = x.l;
			}
		}

		// Returns null if the priority we entered does not match the priority value in
		// the tree.
		return null;
	}

	// Returns the successor node of a given node.
	public Node<K> findSuccessorNode(Node<K> n) {

		// Checks if n's left is nil, then finds the minimum node to n's right subtree.
		if (!checkNil(n.l))
			return findMinNode(n.r);

		// Define a new node y and set it to n's parent.
		Node<K> y = n.p;

		// While y is not nil and n matches y's right child, keep moving up the tree.
		while (!checkNil(y) && n == y.r) {
			n = y;
			y = y.p;
		}

		// Return y which is the successor node.
		return y;
	}

	// Finds the node with the minimum value.
	public Node<K> findMinNode(Node<K> n) {
		// If n's left child is not nil, continue to go left.
		while (!checkNil(n.l)) {
			n = n.l;
		}

		// Returns the minimum node.
		return n;
	}

	// Prints out RB Tree list.
	public void inorderTraversal(Node<K> root) {
		// root is not nil, perform the following operations.
		if (!checkNil(root)) {
			inorderTraversal(root.l);
			System.out.print(
					"(P" + root.pID + "-" + root.priority + " | " + ((root.color == 0) ? "Black" : "Red") + ") ");
			inorderTraversal(root.r);
		}
	}

	// Checks if a node is has a nil value.
	private boolean checkNil(Node n) {
		// Returns a boolean value to see if whether the node n is nil or not.
		return n == setNil;
	}

	// Main method.
	public static void main(String[] args) {
		RBTree rbt = null;
		rbt = new RBTree();

		Random r = new Random();

		int pID = 1;
		int priority = 0;
		Scanner scan = new Scanner(System.in);
		while (true) {

			// Menu options.
			System.out.println();
			System.out.println("***************************MENU********************************");
			System.out.println("Enter one of the following numbers to complete a task.");

			System.out.println("1: Add process with random priority value into the Red Black Tree.");

			System.out.println("2: Add process with a priority value of your choice(e.g. 123).");

			System.out.println("3: Delete a process.");

			System.out.println("4: Find a process with priority value.");

			System.out.println("5: Display all sorted elements in Red Black Tree.");

			System.out.println("6: Exit application.");

			System.out.print("Input: ");

			// Obtains user input.

			int input = scan.nextInt();

			System.out.println(input);

			System.out.println();

			// Allows users to add element to RB Tree with random priority.
			if (input == 1) {
				priority = r.nextInt(999) + 1;
				Node n = new Node(pID, priority);
				pID++;
				rbt.addNode(n);

				System.out.println(
						"**Added (P" + n.pID + "-" + n.priority + " | " + ((n.color == 0) ? "Black" : "Red") + ")");

				rbt.inorderTraversal(rbt.rootNode);
			}

			// Allows users to add element to RB Tree with priority of their choice.
			else if (input == 2) {
				System.out.print("*Enter priority value(e.g. 123): ");
				input = scan.nextInt();

				System.out.println();

				Node n = new Node(pID, input);

				pID++;

				rbt.addNode(n);

				System.out.println(
						"**Added (P" + n.pID + "-" + n.priority + " | " + ((n.color == 0) ? "Black" : "Red") + ")");

				rbt.inorderTraversal(rbt.rootNode);
			}

			// Allows users to delete a node with a given priority.
			else if (input == 3) {
				System.out.print("*Enter priority value(e.g. 123): ");
				input = scan.nextInt();
				System.out.println();
				Node n = new Node(input);
				Node p = rbt.find(input);
				rbt.deleteNode(n);

				System.out.println(
						"**Deleted (P" + p.pID + "-" + n.priority + " | " + ((n.color == 0) ? "Black" : "Red") + ")");

				rbt.inorderTraversal(rbt.rootNode);
			}

			// Allows users to search for a node with a given priority. Outputs process name
			// and node color.
			else if (input == 4) {
				System.out.print("*Enter priority value(e.g. 123): ");
				input = scan.nextInt();
				System.out.println();
				Node n = rbt.find(input);

				System.out.println("**Entered priority value: " + n.priority);
				System.out.println(
						"**Output process name and color: P" + n.pID + " | " + ((n.color == 0) ? "BLACK" : "RED"));
			}

			// Allows users to print out a list of the nodes inside the RB Tree.
			else if (input == 5) {
				System.out.println("**Red Black Tree Result:");
				System.out.println();
				rbt.inorderTraversal(rbt.rootNode);
			}

			else if (input == 6) {
				System.out.println();
				System.out.println("**APPLICATION EXITED.**");
				System.exit(0);
				System.out.println();
			} else {
				System.out.println("**Not a valid entry. Please try again.");
			}
		}
	}
}
import java.util.ArrayList;

//Since BSTs consists of nodes, we use a Node class to represent the nodes inside the BST
//to hold the key and value, as well as sorting according to value.
class Node 
{
	// Create elements of a node: key and value.
	int key;
	int value;

	// Create left and right node.
	Node parent;
	Node left;
	Node right;

	// Constructor with parameters key and value, and setting initial left and right
	// node to null.
	public Node(int key, int value) 
	{
		this.key = key;
		this.value = value;
		left = null;
		right = null;
		parent = null;
	}
}

//Class that holds all the properties of a BST, which provides several methods to display, insert, delete nodes from the BST.
public class BinarySearchTree 
{
	Node root;

	int size = 0;

	//Constructor that sets the root node as null.
	public BinarySearchTree() 
	{
		this.root = null;
	}

	//Responsible for displaying all the nodes, which hold keys and values in the BST.
	public static void InorderTreeWalk(Node x, ArrayList<Node> al) 
	{
		if (x != null) 
		{
			//Displays all of left subtrees.
			InorderTreeWalk(x.left, al);
			System.out.print("[P" + x.value + " - " + x.key + "]  ");

			al.add(new Node(x.key, x.value));
			//Displays all right subtrees.
			InorderTreeWalk(x.right, al);

		}
	}

	//Method used to search for a particular node, with a given key in the BST.
	public Node TreeSearch(Node x, int k) 
	{
		//If x node is null or k matches the key inside x node, return x node.
		if (x == null || k == x.key) 
		{
			return x;
		}
		//If the key inputed is less than the the key in x node, recurse TreeSearch to the left subtree. Else, return recurse
		//TreeSearch to the right subtree.
		if (k < x.key) {
			return TreeSearch(x.left, k);
		} 
		else 
		{
			return TreeSearch(x.right, k);
		}
	}

	//Different approach to TreeSearch method, which runs efficient on most computers.
	public Node IterativeTreeSearch(Node x, int k) 
	{
		//Instead of recursively calling TreeSearch method, this method uses an iterative approach. 
		while (x != null && k != x.key)
		{
			//If k is less than x's key, place x's left subtree into x's place, else place x's right 
			//subtree into x's place.
			if (k < x.key) 
			{
				x = x.left;
			} 
			else 
			{
				x = x.right;
			}
		}
		return x;
	}

	// Returns the node with the minimum value in the BST.
	public static Node TreeMinimum(Node x) 
	{
		//Due to BST's property, this method runs down x's left most node and returns that node,
		//for it is the minimum node in the BST.
		while (x.left != null) 
		{
			x = x.left;
		}
		return x;
	}

	// Returns the node with the maximum value in the BST.
	public static Node TreeMaximum(Node x) 
	{
		//Due to BST's property, this method runs down x's right most node and returns that node,
		//for it is the minimum node in the BST.
		while (x.right != null) 
		{
			x = x.right;
		}
		return x;
	}

	//Returns the successor node of a given node.
	public static Node TreeSuccessor(Node x) 
	{
		//If x's right subtree is null, then recurse call TreeMinimum to x's right subtrees.
		if (x.right != null) 
		{
			return TreeMinimum(x.right);
		}
		
		//Create a new node called y, and have it point to x.parent.
		Node y = x.parent;

		//While y is not null and x matches y's right subtree, then set y into x and y.parent into y.
		while (y != null && x == y.right) 
		{
			x = y;
			y = y.parent;
		}

		//Return y node.
		return y;
	}

	//Returns the predecessor node of a given node.
	public static Node TreePredecessor(Node x) 
	{
		if (x.left != null) 
		{
			return TreeMaximum(x.left);
		}

		//Create a new node called y, and have it point to x.parent.
		Node y = x.parent;

		//While y is not null and x matches y's left subtree, then set y into x and y.parent into y.
		while (y != null && x == y.left) 
		{
			x = y;
			y = y.parent;
		}

		//Return y node.
		return y;
	}

	//Method used to insert new node into the BST.
	public void TreeInsert(BinarySearchTree T, Node z) 
	{
		//Create two new nodes, called x and y. Set y to null and x as BST's root node.
		Node y = null;
		Node x = T.root;

		//While x is not null, point y node to x node.
		while (x != null) 
		{
			y = x;

			//If z's key is less than x's key, to to x's left subtree, else go to x's right subtree.
			if (z.key < x.key) 
			{
				x = x.left;
			} 
			else 
			{
				x = x.right;
			}
		}

		//Point y to z's parent. 
		z.parent = y;

		//If y is null, then set z as BST's root node.
		if (y == null) 
		{
			T.root = z;
		} 
		//Else if z's key is less than y's key, then point z to y's left subtree.
		else if (z.key < y.key) 
		{
			y.left = z;
		} 
		//Else point z to y's right subtree.
		else 
		{
			y.right = z;
		}
		
		//Increment size by 1.
		size++;
	}

	//Method replaces one subtree as a child of its parent with another subtree.
	public static void Transplant(BinarySearchTree T, Node u, Node v) 
	{
		// Case 1: "u" is the root, for it does not have a parent node. Therefore,
		// replace "u" with "v".
		if (u.parent == null) 
		{
			T.root = v;
		}
		// Case 2: "u" has a left subtree. Then, replace u.parent.left with v, else replace u.parent.right with v.
		else if (u == u.parent.left) 
		{
			u.parent.left = v;
		} 
		else 
		{
			u.parent.right = v;
		}
		//If v node is null, then set u's parent node to v's parent node.
		if (v != null) 
		{
			v.parent = u.parent;
		}
	}

	//Method deletes a node from the BST.
	public static void TreeDelete(BinarySearchTree T, Node z) 
	{
		//If z's left subtree is null, then call Transplant on z's right subtree, else call it on z's left subtree.
		if (z.left == null) 
		{
			Transplant(T, z, z.right);
		} 
		else if (z.right == null) 
		{
			Transplant(T, z, z.left);
		} 
		//Else, create a new node called y and place z's minimum node from its right subtree into y.
		else 
		{
			Node y = TreeMinimum(z.right);

			//If y's parent is not z, then call Transplant method to y's right subtree, and point z's right subtree to y's right subtree.
			if (y.parent != z) 
			{
				Transplant(T, y, y.right);
				y.right = z.right;
				y.right.parent = y;
			}
			Transplant(T, z, y);
			y.left = z.left;
			y.left.parent = y;
		}
	}
}

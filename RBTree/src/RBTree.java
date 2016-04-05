/**
 *
 * RBTree
 *
 * An implementation of a Red Black Tree with non-negative, distinct integer
 * keys and values
 *
 */

public class RBTree {

	/**
	 * public class RBNode
	 */

	private RBNode root;
	private int size;

	public RBTree() {
		this.size = 0;
		this.root = null;
	}

	public static class RBNode {
		private boolean isRed;
		private int key;
		private String value;

		private RBNode parent;
		private RBNode left;
		private RBNode right;

		public boolean isRed() {
			return isRed;
		}

		public int getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}

		public RBNode getParent() {
			return parent;
		}

		public RBNode getLeft() {
			return left;
		}

		public RBNode getRight() {
			return right;
		}

		/*
		 * public void setRed(boolean isRed) { this.isRed = isRed; } public void
		 * setKey(int key) { this.key = key; } public void setValue(int value) {
		 * this.value = value; } public void setParent(RBNode parent) {
		 * this.parent = parent; } public void setLeft(RBNode left) { this.left
		 * = left; } public void setRight(RBNode right) { this.right = right; }
		 */

	}

	/**
	 * public RBNode getRoot()
	 *
	 * returns the root of the red black tree
	 *
	 */
	public RBNode getRoot() {
		return this.root;
	}

	/**
	 * public boolean empty()
	 *
	 * returns true if and only if the tree is empty
	 *
	 */
	public boolean empty() {
		return (this.root == null);
	}

	/**
	 * public String search(int k)
	 *
	 * returns the value of an item with key k if it exists in the tree
	 * otherwise, returns null
	 */
	public String search(int k) {
		if (this.empty())
			return null;
		RBNode node = this.root;
		while (!node.equals(null)) {
			if (node.key == k)
				return node.value;
			else if (node.key > k)
				node = node.left;
			else
				node = node.right;
		}
		return null;
	}

	/**
	 * public int insert(int k, String v)
	 *
	 * inserts an item with key k and value v to the red black tree. the tree
	 * must remain valid (keep its invariants). returns the number of color
	 * switches, or 0 if no color switches were necessary. returns -1 if an item
	 * with key k already exists in the tree.
	 */
	public int insert(int k, String v) {
		return 42; // to be replaced by student code
	}

	/**
	 * public int delete(int k)
	 *
	 * deletes an item with key k from the binary tree, if it is there; the tree
	 * must remain valid (keep its invariants). returns the number of color
	 * switches, or 0 if no color switches were needed. returns -1 if an item
	 * with key k was not found in the tree.
	 */
	public int delete(int k) {
		return 42; // to be replaced by student code
	}

	/**
	 * public String min()
	 *
	 * Returns the value of the item with the smallest key in the tree, or null
	 * if the tree is empty
	 */
	public String min() {
		if (empty())
			return null;
		RBNode current = this.root;
		while (current.left != null) {
			current = current.left;
		}
		return current.value;
	}

	/**
	 * public String max()
	 *
	 * Returns the value of the item with the largest key in the tree, or null
	 * if the tree is empty
	 */
	public String max() {
		if (empty())
			return null;
		RBNode current = this.root;
		while (current.right != null) {
			current = current.right;
		}
		return current.value;
	}

	/**
	 * public int[] keysToArray()
	 *
	 * Returns a sorted array which contains all keys in the tree, or an empty
	 * array if the tree is empty.
	 */
	public int[] keysToArray() {
		int[] arr = new int[size]; // new array with appropriate size
		int i = 0;
		if (this.empty()) // check if empty
			return arr;
		RBNode node = findMAX;
		RBNode max = findMIN;
		while (!node.equals(max)) {
			arr[i] = node.key;

		}
		return arr;
	}

	/**
	 * public String[] valuesToArray()
	 *
	 * Returns an array which contains all values in the tree, sorted by their
	 * respective keys, or an empty array if the tree is empty.
	 */
	public String[] valuesToArray() {
		String[] arr = new String[42]; // to be replaced by student code
		return arr; // to be replaced by student code
	}

	/**
	 * public int size()
	 *
	 * Returns the number of nodes in the tree.
	 *
	 * precondition: none postcondition: none
	 */
	public int size() {
		return this.size;
	}

	/**
	 * public int rank(int k)
	 *
	 * Returns the number of nodes in the tree with a key smaller than k.
	 *
	 * precondition: none postcondition: none
	 */
	public int rank(int k) {
		return 42; // to be replaced by student code
	}

	private RBNode succesor(RBNode node) {
		RBNode next = node;
		if (!next.right.equals(null)) { // case 1, node has right subtree
			next = next.right;
			while (!next.left.equals(null))
				next = next.left;
			return next;
		} else { // case 2, node doesn't have right subtree
			RBNode parent = node.parent;
			while (!parent.equals(null) && node.equals(parent.right)) {
				node = parent;
				parent = node.parent;
			}
			return parent;
		}
	}

	private RBNode predeccesor(RBNode node) {
		RBNode next = node;
		if (!next.left.equals(null)) { // case 1, node has left subtree
			next = next.left;
			while (!next.right.equals(null))
				next = next.right;
			return next;
		} else { // case 2, node doesn't have left subtree
			RBNode parent = node.parent;
			while (!parent.equals(null) && node.equals(parent.left)) {
				node = parent;
				parent = node.parent;
			}
			return parent;
		}
	}

	private void leftChild (RBNode x, RBNode y)
	{
		x.left = y;
		y.parent = x;
	}
	
	private void rightChild (RBNode x, RBNode y)
	{
		x.right = y;
		y.parent = x;
	}
	
	private void transplant (RBNode x, RBNode y)
	{
		if (x == x.parent.left)
		{
			leftChild(x.parent, y);
		}
		else
		{
			rightChild(x, y);
		}
	}
	
	private void replace (RBNode x, RBNode y)
	{
		transplant(x, y);
		leftChild(y, x.left);
		rightChild(y, x.right);
	}
	
	private void leftRotate (RBNode x)
	{
		RBNode y = x.right;
		transplant(x, y);
		rightChild(x, y.left);
		leftChild(y, x);
	}
	
	private void rightRotate (RBNode x)
	{
		RBNode y = x.left;
		transplant(x, y);
		leftChild(x, y.right);
		rightChild(y, x);
	}
	
	private RBNode treePosition(RBNode x, int k)
	{
		RBNode y = null;
		while (x != null)
		{
			y = x;
			if (k == x.key)
				return x;
			else if (k < x.key)
				x = x.left;
			else
				x = x.right;
		}
		return y;
	}
	


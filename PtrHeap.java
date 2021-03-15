package algs24;

import stdlib.StdIn;
import stdlib.StdOut;


/**
 *  The <tt>PtrHeap</tt> class is the priorityQ class from Question 2.4.24.
 *  It represents a priority queue of generic keys.
 *  
 *  It supports the usual <em>insert</em> and <em>delete-the-maximum</em>
 *  operations, along with methods for peeking at the maximum key,
 *  testing if the priority queue is empty, and iterating through
 *  the keys.
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/24pq">Section 2.4</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 */

public class PtrHeap<K extends Comparable<? super K>> {
	
	static class Node<K> {
		K value;
		Node<K> parent;
		Node<K> lchild;
		Node<K> rchild;
	}
	
	private int size;
	private Node<K> root;
	
    boolean isRoot(Node<K> n){ return n == root; }
    
    Node<K> find(int n){ return null; } 
	
	void exch(Node<K> n1, Node<K> n2) { 
	// only swap items of nodes 

		K tmp = n1.value;
		n1.value = n2.value;
		n2.value = tmp;

	}
	
	@SuppressWarnings("unchecked")
	/** Create an empty priority queue  */
	public PtrHeap() {
		
		size = 0;
		root = null;
		
	}
    
	/** Is the priority queue empty? */
	public boolean isEmpty() { 
		return (size== 0); }
	

	/** Return the number of items on the priority queue. */
	public int size() { 
		return size; }
	
	
	public void sink(Node<K> n)
	{
		while(n.lchild != null && n.rchild != null)
		{
			if(n.value.compareTo(n.lchild.value) < 0 || n.value.compareTo(n.rchild.value) < 0)
			{
				if(n.lchild.value.compareTo(n.rchild.value) < 0 )
				{
					exch(n.rchild,n);
					n = n.rchild;
				}
				else 
				{
					exch(n.lchild, n);
					n = n.lchild;
				}
			}
			else
			{
				break;
			}
		}
		
	}
	
	public void swim(Node<K> n)
	{
		while(n != root)
		{
			if(n.value.compareTo(n.parent.value) > 0)
			{
				exch(n, n.parent);
				n = n.parent;
			}
			else
			{
				break;
			}
		}
	}

	/**
	 * Return the largest key on the priority queue.
	 * Throw an exception if the priority queue is empty.
	 */
	public K max() {
		
		return root.value;
		
		
	}

	/** Add a new key to the priority queue. */
	public void insert(K x) { 
		Node<K> insert = new Node<K>();
		insert.value = x; insert.lchild = null; insert.rchild = null;
		
		if(size == 0)
		{
			insert.parent = null;
			root = insert;
			size++;
		}
		else 
		{
			size++;
			String bin = Integer.toBinaryString(size);
			Node<K> curr = root;
			for(int i = 1; i < bin.length()-1; i++)
			{
				if(bin.charAt(i) == '0') {curr = curr.lchild;}
				else {curr = curr.rchild;}
			}
			
			if(bin.charAt(bin.length()-1) == '0') {curr.lchild = insert; insert.parent = curr; swim(insert);}
			else {curr.rchild = insert; insert.parent = curr; swim(insert);}
		}
		
		
	}

	/**
	 * Delete and return the largest key on the priority queue.
	 * Throw an exception if the priority queue is empty.
	 */
	public K delMax() {
		
		if(size == 1)
		{
			Node<K> curr = root;
			size--;
			root = null;
			return curr.value;
		}
		else 
		{
			Node<K> n = root;
			String bin = Integer.toBinaryString(size);
			for(int i = 1; i < bin.length(); i++)
			{
				if(bin.charAt(i) == '0') {n = n.lchild;}
				else {n = n.rchild;}
			}
			
			exch(root, n);
			if(bin.charAt(bin.length()-1) == '0') {n.parent.lchild = null;}
			else {n.parent.rchild = null;}
			size--;
			sink(root);
			return n.value;
		}
		
	}

	private void showHeap() { 
	    // a method to print out the heap
		// useful for debugging
		
		
	}
	

	public static void main(String[] args) {
		PtrHeap<String> pq = new PtrHeap<>();
		StdIn.fromString("10 20 30 40 50 - - - 05 25 35 - - - 70 80 05 - - - - ");
		while (!StdIn.isEmpty()) {
			StdOut.print ("pq:  "); pq.showHeap();
			String item = StdIn.readString();
			if (item.equals("-")) StdOut.println("max: " + pq.delMax());
			else pq.insert(item);
		}
		StdOut.println("(" + pq.size() + " left on pq)");

	}

}
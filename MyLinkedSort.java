package algs13;


import stdlib.*;

// PROBLEM 2.2.17
public class MyLinkedSort {
    static class Node {
        public Node() { }
        public double item;
        public Node next;
    }
    
    int N;
    Node first;
    
    public MyLinkedSort () {
        first = null;
        N = 0;
        checkInvariants ();
    }

    private void myassert (String s, boolean b) { if (!b) throw new Error ("Assertion failed: " + s); }
    private void checkInvariants() {
        myassert("Empty <==> first==null", (N == 0) == (first == null));
        Node x = first;
        for (int i = 0; i < N; i++) {
            if (x==null) {
                throw new Error ("List too short!");
            }
            x = x.next;
        }
        myassert("EndOfList == null", x == null);
    }

    public boolean isEmpty () { return first == null; }
    public int size () { return N; }
    public void add (double item) {
        Node newfirst = new Node ();
        newfirst.item = item;
        newfirst.next = first;
        first = newfirst;
        N++;
    }
    private static int getSize(Node l)
    {
    	int size = 0;
    	Node c = l;
    	while(c != null)
    	{
    		size++;
    		c = c.next;
    	}
    	
    	return size;
    }
    private static void print (String s, Node b) {
        StdOut.print (s + ": ");
        for (Node x = b; x != null; x = x.next)
            StdOut.print (x.item + " ");
        StdOut.println ();
    }
    private static void print (String s, Node b, double i) {
        StdOut.print (s + ": ");
        for (Node x = b; x != null; x = x.next)
            StdOut.print (x.item + " ");
        StdOut.println (": " + i);
    }

    static public Node sort(Node l ){ // 
	   // base case: list is of size 1. reurn
	   // otherwise use split to create two lists
	   // recursively sort each of them
	   // use merge to combine them, and return the result
    	
    	if(getSize(l) == 1) {return l;}
    	else 
    	{ 
    		Node[] spl = split(l);
    		Node first = spl[0];
    		Node second = spl[1];
    		
    		first = sort(first);
    		second = sort(second);

    		return merge(first,second);
    		
    	}
  
	}
		 
	static public Node[] split(Node l){
      // parameter is a List
	  // it returns an array of size 2
	  // the 0th element is theleft ist
	  // the first element is the right list
		Node front, secondFront;
		int size = getSize(l);
		int mid = (size-1)/2;
		
		Node c = l;
		for(int i = 0; i < mid; i++)
		{
			c = c.next;
		}
		
		front = l;
		secondFront = c.next;
		c.next = null;
		
		Node[] spl = new Node[2];
		spl[0] = front;
		spl[1] = secondFront;
		
		return spl;
		
	  }
	
	static public Node merge(Node lt, Node rt){
	// merge creates a new List
	// whose elements come from the lt and rt MyLinkedLists
		Node left = lt;
		Node right = rt;
		Node sorted = null;
		
		if(left.item <= right.item)
		{
			sorted = left;
			left = left.next;
		}
		else
		{
			sorted = right;
			right = right.next;
		}
		
		Node c = sorted;
		while(left != null && right != null)
		{
			if(left.item <= right.item) {c.next = left; c = c.next; left = left.next;}
			else {c.next = right; c = c.next; right = right.next;}
		}
		while(left != null) {c.next = left; c = c.next; left = left.next;}
		while(right != null) {c.next = right; c = c.next; right = right.next;}
		
		return sorted;
	}
	
	
    //


    public static void main (String args[]) {
        int[] a1 = new int[20];
		for (int i = 0; i < a1.length; i++)
			a1[i] = i;
		StdRandom.shuffle (a1);
        MyLinkedSort b1 = new MyLinkedSort ();
        for (int i:a1) b1.add(i);
        MyLinkedSort.print("before sort",b1.first);
        Node res1 = MyLinkedSort.sort(b1.first);
        MyLinkedSort.print("after sort",res1);
        
        
        int[] a2 = new int[200];
		for (int i = 0; i < a2.length; i++)
			a2[i] = i;
		StdRandom.shuffle (a2);
        MyLinkedSort b2 = new MyLinkedSort ();
        for (int i:a1) b2.add(i);
        MyLinkedSort.print("before sort",b2.first);
        Node res2 = MyLinkedSort.sort(b2.first);
        MyLinkedSort.print("after sort",res2);

       // write code for a doubling Test
        

         
    }
}
package algs11;

import stdlib.StdOut;
import java.util.Arrays;


public class ArrayIntSet {
	private int[] data;
	private int size;

	public ArrayIntSet(int capacity) {
		data = new int[capacity];
		size = 0;
	}

	public int size() {
		return size;
	}

	public boolean contains(int i) {
		for (int index = 0; index < size; index++) {
			if (data[index] == i)
				return true;
		}
		return false;
	}

      // Ignore this equals method. Used for testing.
	public boolean equals(Object otherObject) {
		ArrayIntSet other = (ArrayIntSet) otherObject;
		return this.equals(other);
	}



	/***************************** NEW METHODS ************************************/

	/**
	 * Adds <code>element</code> to this set if it is not already present and
	 * returns <code>true</code>. If <code>element</code> is already present, the
	 * set is unchanged and <code>false</code> is returned.
	 * 
	 * @param element the element to be added
	 * @return <code>true</code> if the element was added and <code>false</code>
	 *         otherwise.
	 */
	
	//Checks to see if element is already in data
	//if element is in data, returns false
	//if element is not in data, puts it in data and increments size
	public boolean addElement(int element) {
		
		if(contains(element) == true)
		{
			return false;
		}
		else
		{
			data[size] = element;
			size++;
			return true;
		}
	}

	/**
	 * Removes an element from the set.
	 * 
	 * @param element the element to be removed
	 * @return <code>ture</code> if the element was removed and <code>false</code>
	 *         otherwise.
	 */
	
	//Goes through and finds the elemnt to be removed
	//replaces the position that element is in with the next element in next position
	//does this for the rest of the data set and return true
	//if element does not exist in data, returns false
	public boolean removeElement(int element) {
		int removed = 0;
		for(int i = 0; i < data.length; i++)
		{
			if(data[i] == element)
			{
				for(int j = i; j < data.length-1; j++)
				{
					data[j] = data[j+1];
				}
				size--;
				return true;
			}
		}
		
		return false;
	}

	/**
	 * Returns true if <code>this</code> and <code>other</code> have the same
	 * elements and false otherwise.
	 * 
	 * @param other the set to compare against for equality
	 * @return true if <code>this</code> and <code>other</code> have the same
	 *         elements and false otherwise.
	 */
	
	//goes through the array and checks to see if the other ArrayIntSet has that element
	//checks to see if other contains all the elements in data
	//if the elements are in other, sameNumbers increases
	//if sameNumbers is equal to both the sizes, then they are equal
	//otherwise they are not and the return is false 
	public boolean equals(ArrayIntSet other) {
		int sameNumbers = 0;
		for(int i = 0; i < data.length; i++)
		{
			if(other.contains(data[i]))
			{
				sameNumbers++;
			}
		}
		
		if(sameNumbers == size && sameNumbers == other.size)
		{
			return true;
		}
		
		return false;
	}

	/**
	 * Changes the set so that it is equal the union of itself and
	 * <code>other</code>.
	 * 
	 * @param other the set to union with
	 */
	
	//Goes through the array and checks to find values in other not in data
	//if values are found, it will be stored in data 
	//size is then incremented 
	public void union(ArrayIntSet other) {
		// Replace the line below with your answer
		for(int i = 0; i < data.length; i++)
		{
			if(contains(other.data[i]) == false)
			{
				data[size] = other.data[i];
				size++;
			}
		}
	}
	

	/**
	 * Changes the set so that is equal the intersection of itself and
	 * <code>other</code>.
	 * 
	 * @param other the set to intersect with
	 */
	
	//Goes through the other and finds values that are the same in data
	//puts those values in a new array
	//once all values are found data will equal the new array 
	public void intersect(ArrayIntSet other) {
		
		int intersectArray[] = new int[data.length];
		int pos = 0;
		for(int i = 0; i < data.length; i++)
		{
			if(contains(other.data[i]) == true)
			{
				intersectArray[pos] = other.data[i];
				pos++;
			}
		}
		
		data = intersectArray;
		
	}

}

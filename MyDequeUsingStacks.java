package algs15.perc;

import stdlib.*;
import algs15.*;

// Uncomment the import statements above.

// You can test this using InteractivePercolationVisualizer and PercolationVisualizer
// All methods should make at most a constant number of calls to the UF data structure,
// except percolates(), which may make up to N calls to the UF data structure.
public class Percolation {
	int N;
	boolean[] open;
	// TODO: more fields to add here
	UF u;
	public Percolation(int N) {
		this.N = N;
		this.open = new boolean[N*N];
		// TODO: more to do here
		this.u = new WeightedUF(N*N);
	}
	// open site (row i, column j) if it is not already
	public void open(int i, int j) {
		open[i*N+j] = true;
		// TODO: more to do here.
		
		//checks to see if the spots around are open 
		if(i-1 >= 0 && i-1 < N && isOpen(i-1,j) == true)
		{
			u.union(i*N+j, (i-1)*N + j);
		}
		if(i+1 >= 0 && i+1 < N && isOpen(i+1, j) == true)
		{
			u.union(i*N+j, (i+1)*N + j);
		}
		if(j+1 >= 0 && j+1 < N && isOpen(i,j+1) == true)
		{
			u.union(i*N+j, i*N + (j+1));
		}
		if(j-1 >= 0 && j-1 < N && isOpen(i,j-1) == true)
		{
			u.union(i*N+j, i*N + (j-1));
		}
	}
	// is site (row i, column j) open?
	public boolean isOpen(int i, int j) {
		return open[i*N+j];
	}
	// is site (row i, column j) full?
	public boolean isFull(int i, int j) {
		// TODO
		return !isOpen(i,j);
	}
	// does the system percolate?
	public boolean percolates() {
		// TODO
		
		//checks to see if the top is connected to the bottom
		//if it is returns true
		int k = N-1;
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < N; j++)
			{
				if(u.connected(j+k*N, i) == true)
				{
					return true;
				}
			}
		}
		return false;

		
	}
}
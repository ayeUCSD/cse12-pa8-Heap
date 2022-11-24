import java.util.Comparator;
import java.util.Scanner;

import javax.sound.midi.Soundbank;

import java.io.File;
import java.io.FileNotFoundException;

public class ElementFinder {

	public static int Kth_finder(String filename, int K, String operation) {
		// Create a comparator depending upon the type of operation
		Comparator<Integer> comparator = getComparator(operation);
		if (comparator == null) {
			System.out.println("NULL COMPARATOR");
			return -1;
		}

		// make a heap
		Heap<Integer, Integer> heap = new Heap<Integer, Integer>(comparator);

		// put K integers from file into heap

		try {
			File file = new File(filename);
			Scanner s = new Scanner(file);

			while (s.hasNextLine()) {
				// if eg if we look at k = 4, we havea max of 4 in the heap
				// if we scan EVERY line for integers. If we scan an int that is comparator of
				// peek()
				// we remove() and then insert the scanned integer

				String[] line = s.next().split(" ");
				for (String c : line) {
					int val = Integer.parseInt(c);
					System.out.println("looking at " + val);

					if (heap.size() >= 4) {

						if (comparator.compare(val, heap.peek().getKey()) < 0) {
							System.out.println(heap.toString());
							System.out.println("removing " + heap.poll() + "adding " + val);
							heap.poll();
							heap.add(val, val);
							System.out.println(heap.toString());
						}
						continue;
					}

					heap.add(val, val);
				}
			}
			s.close();
		} catch (FileNotFoundException E) {
			System.out.println(E.getMessage());
			return -1;
		}

		System.out.println("Final Heap");
		System.out.println(heap.toString());
		// the peek() value should be the Kth operation in a heap of size K. Min heap
		// for Largest
		// because the largest values are on the bottom, meaning if we have size k, the
		// root is the kth largest.
		return heap.peek().getKey();
	}

	}

	/* Add any helper methods you find useful */

	public static Comparator<Integer> getComparator(String operation){
		if(operation.equals("largest")){
			System.out.println("making Min Heap");
			// compare(1,5),   5 - 1 > 0, thus 1 has greater priority
			return new Comparator<Integer>() {
				@Override
				public int compare(Integer o2, Integer o1) {
					return o1 - o2;
				}
			};
		
		}else if(operation.equals("smallest")){
			//make a max heap
			System.out.println("making Max Heap");
			return new Comparator<Integer>() {

	@Override
	// if we swap these input names, it should reverse our output... right?
	public int compare(Integer o1, Integer o2) {
		return o1 - o2;
	}
};}return null;}}

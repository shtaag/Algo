/**
 * 
 */
package shtaag.algorithm.sort;

import java.util.List;

/**
 * @author takei_s
 * @date 2011/09/25
 */
public class QuickSortRecursive {
	
	public void sort(List<Integer> array, int p, int r) {
		if (p < r) {
			int i = partition(array, p, r);
			System.out.println("-- Parted -- : pivot = " + i);
			for (Integer ii : array) {
				System.out.println(ii);
			}
			sort(array, p, i-1);
			sort(array, i+1, r);
		}
	}
	
	private int partition(List<Integer> array, final int p, final int r) {
		
		Integer rightest = array.get(r);
		int i = p - 1;
		for (int j = p; j < r; j++) {
			if (array.get(j) <= rightest) {
				i++;
				swap(array, i, j);
			}
		}
		i++;
		swap(array, i, r);
		
		return i;
	}
	
	protected void swap(List<Integer> array, int from, int to) {
		int fromIdx = from;
		int toIdx = to;
		Integer fromValue = array.get(fromIdx);
		Integer toValue = array.get(toIdx);
		array.set(fromIdx, toValue);
		array.set(toIdx, fromValue);
		System.out.println("swapped: >" + fromIdx +":" +fromValue + " with>" + toIdx + ":" + toValue);
	}

}

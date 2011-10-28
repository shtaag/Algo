/**
 * 
 */
package shtaag.algorithm.sort;

import java.util.List;
import java.util.Stack;

/**
 * @author takei_s
 * @date 2011/09/25
 */
public class QuickSortRoop extends QuickSortRecursive{
	
	Stack<SortRange> remained = new Stack<SortRange>();

	public void sort(List<Integer> array) {
		remained.push(new SortRange(0, array.size()-1));
		while (remained.size() > 0) {
			SortRange toSort = remained.pop();
			partition(array, toSort.start, toSort.end);
			System.out.println("-- Parted --");
			for (Integer ii : array) {
				System.out.println(ii);
			}	
		}
	}
	
	private static class SortRange {
		public final int start;
		public final int end;
		private SortRange(int start, int end) {
			this.start = start;
			this.end = end;
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
		// judge left side
		if (i > p + 1) {
			remained.push(new SortRange(p, i-1));
		}
		// judge right side
		if (r > i + 1) {
			remained.push(new SortRange(i+1, r));
		}
		
		return i;
	}
	
}

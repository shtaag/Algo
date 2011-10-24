/**
 * 
 */
package shtaag.algorithm.sort;

import java.util.List;

/**
 * @author takei_s
 * @date 2011/08/28
 */
public class HeapSort {

	
	private static class HeapArray<Integer> {
		int size;
		private List<Integer> array;
		public HeapArray(List<Integer> _array) {
			array = _array;
			this.size = array.size();
		}
		public int length() {
			return array.size();
		}
		public Integer get(int index) {
			return array.get(--index);
		}
		public void add(int index, Integer obj) {
			array.add(--index, obj);
		}
		public void swap(int from, int to) {
			int fromIdx = from - 1;
			int toIdx = to - 1;
			Integer fromValue = array.get(fromIdx);
			Integer toValue = array.get(toIdx);
			array.set(fromIdx, toValue);
			array.set(toIdx, fromValue);
			System.out.println("swapped: >" + fromIdx +":" +fromValue + " with>" + toIdx + ":" + toValue);
		}
		public List<Integer> getSorted() {
			return array;
		}
	}
	
	/*
	 * return the index of node of parent/left child/right child
	 */
	private int parent(int i) {
		return (int) Math.floor(i/2);
	}
	private int left(int i) {
		return i*2;
	}
	private int right(int i) {
		return i*2 + 1;
	}
	
	private void maxHeapify(HeapArray<Integer> heap, int index) {
		int largest = index;
		int left = left(index);
		int right = right(index);
		
		if ((left <= heap.size) && (heap.get(left) > heap.get(index))) {
			largest = left;
		} 
		if ((right <= heap.size) && (heap.get(right) > heap.get(largest))) {
			largest = right;
		}
		System.out.println("largest idx: " + largest);
		
		if (largest != index) {
			heap.swap(largest, index);
			maxHeapify(heap, largest);
		}
	}
		
	
	private void buildMaxHeap(HeapArray<Integer> array) {
		array.size = array.length();
		for (int i = (int)Math.floor(array.length()/2); i > 0 ; i--) {
			maxHeapify(array, i);
		}
	}
	
	public List<Integer> sort(List<Integer> array) {
		HeapArray<Integer> arr = new HeapArray<Integer>(array);
		buildMaxHeap(arr);
		System.out.println("** builded max heap");
		for (int i = arr.length(); i > 1; i--) {
			arr.swap(1, i);
			arr.size--;
			System.out.println("*size decreased.");
			maxHeapify(arr, 1);
		}
		
		return arr.getSorted();
	}
}

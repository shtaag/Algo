/**
 * 
 */
package shtaag.algorithm.game;

import java.util.ArrayList;
import java.util.List;


/**
 * @author takei_s
 * @date 2011/11/04
 */
public class EightQueen {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			EightQueen body = new EightQueen();
			body.search(8);
//			body.search(Integer.parseInt(args[0]));
			System.out.println("Normal End.");
		} catch (Throwable th) {
			System.out.println("Error End.");
			System.out.println(th);
		}
	}
	
	public void search(int n) {
		Board board = new Board(n);
		Queen[] queenArr = new Queen[n] ;
		for (int i = 0; i < n; i++) {
			queenArr[i] = new Queen(i,0);
		}
		setQueen(board, queenArr, 0);
		
		listUp();
		
	}
	
	private void listUp() {
		System.out.println("The number of Answers:" + resultList.size());
		System.out.println("=== List of Answers: (row, column) ===");
		for(Queen[] array: resultList) {
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			for(Queen elem: array) {
				sb.append("(" + elem.row + ", " + elem.column + ")");
			}
			sb.append("]");
			System.out.println(sb.toString());
		}
	}
	
	private List<Queen[]> resultList = new ArrayList<Queen[]>();
	private void setQueen(Board board, Queen[] queenArr, int rowVal) {
		if (rowVal == board.size) {
			Queen[] answer = new Queen[board.size];
			for (int i = 0; i < queenArr.length; i++) {
				answer[i] = ((Queen) queenArr[i]).clone();
			}
			resultList.add(answer);
			return;
		}
		for (int columnVal = 0; columnVal < board.size; columnVal++) {
			if (board.column[columnVal] == OCCUPIED ||
				board.diagPos[rowVal + columnVal] == OCCUPIED ||
				board.diagNeg[rowVal - columnVal + board.size - 1] == OCCUPIED) {
				continue;
			}
				
			queenArr[rowVal].column = columnVal; // set queen to [row][column]
			setBoard(board, rowVal, columnVal); // change board status
			setQueen(board, queenArr, rowVal + 1);
			reverseBoard(board, rowVal, columnVal); // reverse board status
			
		}
	}
	
	private static int FREE = 0;
	private static int OCCUPIED = 1;
	private void setBoard(Board board, int rowVal, int columnVal) {
		board.column[columnVal] = OCCUPIED;
		board.diagPos[rowVal + columnVal] = OCCUPIED;
		board.diagNeg[rowVal - columnVal + board.size - 1] = OCCUPIED; 
	}
	
	private void reverseBoard(Board board, int rowVal, int columnVal) {
		board.column[columnVal] = FREE;
		board.diagPos[rowVal + columnVal] = FREE;
		board.diagNeg[rowVal - columnVal + board.size - 1] = FREE; 
	}
	
	private static class Queen {
		private int row;
		private int column;
		private Queen(int row, int column) {
			this.row = row;
			this.column = column;
		}
		@Override
		public Queen clone() {
			return new Queen(this.row, this.column);
		}
	}
	
	private static class Board {
		private int[] column;
		private int[] diagPos;
		private int[] diagNeg;
		private int size;
		private Board(int n) {
			this.size = n;
			column = new int[n];
			diagPos = new int[2*n-1];
			diagNeg = new int[2*n-1];
		}
	}

}

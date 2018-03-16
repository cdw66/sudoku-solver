import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SudokuSolver {
	private int[][] numbers;
	public final int SIZE = 9;
	
	public SudokuSolver(String filePath) {
		numbers = new int[SIZE][SIZE];
		try {
			Scanner scanner = new Scanner(new File(filePath));
			ArrayList<Integer> list = new ArrayList<Integer>();
			while (scanner.hasNext()) {
				list.add(Integer.parseInt(scanner.next()));
			}
			int pos = 0;
			for (int r = 0; r < SIZE; r++) {
				for (int c = 0; c < SIZE; c++) {
					numbers[r][c] = list.get(pos);
					pos++;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public boolean solve() {
		return solve(0,0);
	}
	
	private boolean solve(int row, int col) {
		if (row == SIZE) {
			row = 0;
			if (++col == SIZE) {
				return true;
			}
		}
		if (numbers[row][col] != 0) {
			return solve(row+1,col);
		}
		for (int k = 1; k <= SIZE; k++) {
			if (isPossibleDigit(k,row,col)) {
				numbers[row][col] = k;
				if (solve(row+1,col)) {
					return true;
				}
			}
		}
		numbers[row][col] = 0;
		return false;
	}
	
	private boolean isPossibleDigit(int number, int row, int col) {
		return !isInRow(number,row) && !isInColumn(number,col) && 
				!isInSquare(number,row,col);
	}
	
	private boolean isInSquare(int k, int row, int col) {
		int[][] square = getSquare(row,col);
		return isInSquare(k,square);
	}

	private boolean isInSquare(int k, int[][] square) {
		for(int r = 0; r < square.length; r ++) {
			for(int c = 0; c < square[0].length; c++) {
				if (square[r][c] == k) return true;
			}
		}
		return false;
	}

	private int[][] getSquare(int row, int col) {
		int[][] square = new int[3][3];
		
		int rowStart = (row/3)*3;
		int colStart = (col/3)*3;
		
		for (int r = rowStart; r < rowStart + 3; r++) {
			for (int c = colStart; c < colStart + 3; c++) {
				square[r-rowStart][c-colStart] = numbers[r][c];
			}
		}
		
		return square;
	}

	private boolean isInColumn(int k, int col) {
		for(int x = 0; x < numbers.length; x++) {
			if (numbers[x][col] == k) return true;
		}
		return false;
	}

	private boolean isInRow(int k, int row) {
		for(int x = 0; x < numbers[row].length; x++) {
			if( numbers[row][x] == k) return true;
		}
		return false;
	}
	
	public void showPuzzle() {
		for (int r = 0; r < SIZE; r++) {
			for (int c = 0; c < SIZE; c++) {
				System.out.print(numbers[r][c] + " ");
			}
			System.out.println();
		}
	}
}

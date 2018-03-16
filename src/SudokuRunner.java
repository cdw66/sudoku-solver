
public class SudokuRunner {

	public static void Main(String[] args) {
		
	
		SudokuSolver solver = new SudokuSolver("puzzle.txt");
	
		solver.showPuzzle();
		System.out.println();
		solver.solve();
		solver.showPuzzle();
	}
	
}

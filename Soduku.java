public class Soduku {
	public static int[][] sudokuBoard;

	public boolean checkForSolution() {
		int row;
		int col;
		int[] blankCell = findBlankLocation();
		row = blankCell[0];
		col = blankCell[1];
		if (row == -1) {
			return true;
		}
		for (int i = 1; i <= 9; i++) {
			if (ifLegal(row, col, i)) {
				sudokuBoard[row][col] = i;
				if (checkForSolution()) {
					return true;	//it's a valid spot
				}
				sudokuBoard[row][col] = 0;
			}
		}
		return false; 
	}
	/*
	 * checks if the move is legal
	 * n= number we are checking
	 */
	public boolean ifLegal(int row, int col, int n) {
		if (!inRow(row, n) && !inCol(col, n)
				&& !inBox(row - row % 3, col - col % 3, n)) {
			return true;
		}
		return false;
	}/*
	 * checks the column
	 */
	public boolean inCol(int col, int n) {
		for (int i = 0; i < 9; i++) {
			if (sudokuBoard[i][col] == n) {
				return true;
			}
		}
		return false;
	}
	/*
	 * checks if in the 3x3 box
	 */
	public boolean inBox(int boxStartRow, int boxStartCol, int n) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (sudokuBoard[i + boxStartRow][j + boxStartCol] == n) {
					return true;
				}
			}
		}
		return false;
	}
	/*
	 * checks the row
	 */
	public boolean inRow(int row, int n) {
		for (int i = 0; i < 9; i++) {
			if (sudokuBoard[row][i] == n) {
				return true;
			}
		}
		return false;
	}
	
	
	/*
	 * coordinates of loaction returned as 1-D array row[0] and col[1]
	 */
	public int[] findBlankLocation() {
		int[] cell = new int[2]; 
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (sudokuBoard[i][j] == 0) {
					cell[0] = i;
					cell[1] = j;
					return cell;
				}
			}
		}
		cell[0] = -1;
		cell[1] = -1;
		return cell; 
	}
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String board="";
		for (int row = 0; row < 9; row++) {
			if (row % 3 == 0) {
				board += "\n";
			}
			for (int col = 0; col < 9; col++) {
				if (col % 3 == 0) {
					board += " ";
				}
				board +=""+ sudokuBoard[row][col] + " ";

			}
			board+="\n";
		}
		return board;
	}

	public static void main(String[] args) {
		sudokuBoard = new int[][] { { 5, 3, 0, 0, 7, 0, 0, 0, 0 },
			{ 6, 0, 0, 1, 9, 5, 0, 0, 0 }, { 0, 9, 8, 0, 0, 0, 0, 6, 0 },
			{ 8, 0, 0, 0, 6, 0, 0, 0, 3 }, { 4, 0, 0, 8, 0, 3, 0, 0, 1 },
			{ 7, 0, 0, 0, 2, 0, 0, 0, 6 }, { 0, 6, 0, 0, 0, 0, 2, 8, 0 },
			{ 0, 0, 0, 4, 1, 9, 0, 0, 5 }, { 0, 0, 0, 0, 8, 0, 0, 7, 9 } };
			Soduku sudoku = new Soduku();
			if (sudoku.checkForSolution()) {
				System.out.print(sudoku.toString());
			} else {
				System.out.println("The Grid is invalid");
			}
	}

}
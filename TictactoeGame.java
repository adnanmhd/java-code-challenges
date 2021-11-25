import java.util.Scanner;

public class TictactoeGame {

	public static Scanner in = new Scanner(System.in);

	public static final int EMPTY = 0;
	public static final int CROSS = 1;
	public static final int NOUGHT = 2;

	public static int COLS = 0;
	public static int ROWS = 0;
	public static int[][] board;

	public static int currentPlayer;
	public static int colInsert;
	public static int rowInsert;

	public static Boolean playing = true;
	public static Boolean draw = false;
	public static Boolean inputValid = true;

	public static void main(String[] args) {

		initGame();
		do {
			gamePlay();

		} while (playing);

	}

	public static void initGame() {
		currentPlayer = CROSS;
		System.out.println("This is TictacToe Game. Insert The Long of Game Board: ");
		int longBoard = in.nextInt();
		COLS = longBoard;
		ROWS = longBoard;
		board = new int[ROWS][COLS];

	}

	public static void gamePlay() {

		do {
			if (currentPlayer == NOUGHT) {
				System.out.println("Player 'O', enter your move (row[1-" + ROWS + "] column[1-" + COLS + "]): ");
				rowInsert = in.nextInt() - 1;
				colInsert = in.nextInt() - 1;

				if (rowInsert >= 0 && rowInsert < ROWS && colInsert >= 0 && colInsert < COLS
						&& board[rowInsert][colInsert] == EMPTY) {
					noughtMove(rowInsert, colInsert);
					gameBoard();
					isWon();
				} else {
					System.out.println("This move at (" + (rowInsert + 1) + "," + (colInsert + 1)
							+ ") is not valid. Try again...");
					inputValid = false;
				}
			} else {
				System.out.println("Player 'X', enter your move (row[1-" + ROWS + "] column[1-" + COLS + "]): ");
				rowInsert = in.nextInt() - 1;
				colInsert = in.nextInt() - 1;

				if (rowInsert >= 0 && rowInsert < ROWS && colInsert >= 0 && colInsert < COLS
						&& board[rowInsert][colInsert] == EMPTY) {
					crossMove(rowInsert, colInsert);
					gameBoard();
					isWon();
				} else {
					System.out.println("This move at (" + (rowInsert + 1) + "," + (colInsert + 1)
							+ ") is not valid. Try again...");
					inputValid = false;
				}
			}

		} while (!inputValid);

	}

	public static void gameBoard() {
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				if (board[row][col] == CROSS) {
					System.out.print(" X ");
				} else if (board[row][col] == NOUGHT) {
					System.out.print(" O ");
				} else {
					System.out.print("   ");
				}

				if (col != COLS - 1) {
					System.out.print("|");
				}
			}
			System.out.println();
			if (row != ROWS - 1) {
				for (int i = 0; i < COLS; i++) {
					System.out.print("----");
				}
				System.out.println();
			}
		}
	}

	public static void crossMove(int row, int col) {
		board[row][col] = CROSS;
		currentPlayer = NOUGHT;
	}

	public static void noughtMove(int row, int col) {
		board[row][col] = NOUGHT;
		currentPlayer = CROSS;
	}

	public static void isPlaying() {
		boolean isFull = true;
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				if (board[row][col] == EMPTY) {
					isFull = false;
					playing = true;
					break;
				}
			}
		}

		if (isFull) {
			System.out.println("It's a Draw! Bye!");
			playing = false;
		}
	}

	public static boolean winDiagonal(int currentRow, int currentCol) {
		boolean winDiagonal = false;
		if (currentRow == currentCol) { // check for diagonal
			for (int col = 0; col < COLS; col++) {
				int colAfter = col + 1;

				if (colAfter < COLS) {
					if (board[col][col] == board[colAfter][colAfter]) {
						winDiagonal = true;
					} else {
						winDiagonal = false;
						break;
					}
				}
			}
		} else {
			int colEnd = COLS - 1;
			int cekDiagonal = 0;
			for (int row = 0; row < ROWS; row++) {
				int rowAfter = row + 1;
				int colEndAfter = colEnd - 1;
				if (colEnd >= 0 && colEndAfter >= 0 && rowAfter < ROWS) {
					cekDiagonal = board[row][colEnd] + board[rowAfter][colEndAfter];
				}
				colEnd -= 1;
			}

			if (cekDiagonal > 0) { // check if the diagonal has value
				int colEndCheck = COLS - 1;
				for (int row = 0; row < ROWS; row++) {
					int rowAfter = row + 1;
					int colEndAfter = colEndCheck - 1;
					if (colEndCheck >= 0 && colEndAfter >= 0 && rowAfter < ROWS) {
						if (board[row][colEndCheck] == board[rowAfter][colEndAfter]) { // check for opposite diagonal
							winDiagonal = true;
						} else {
							winDiagonal = false;
							break;
						}
					}
					colEndCheck -= 1;
				}
			}

		}
		return winDiagonal;
	}

	public static boolean winHorizontal(int currentRow) {
		boolean winHorizontal = false;
		for (int col = 0; col < COLS; col++) {
			int colAfter = col + 1;

			if (colAfter < COLS) {
				if (board[currentRow][col] == board[currentRow][colAfter]) {
					winHorizontal = true;
				} else {
					winHorizontal = false;
					break;
				}

			} else {
				break;
			}
		}
		return winHorizontal;
	}

	public static boolean winVertical(int currentCol) {
		boolean winVertical = false;
		for (int row = 0; row < ROWS; row++) {
			int rowAfter = row + 1;
			if (rowAfter < ROWS) {
				if (board[row][currentCol] == board[rowAfter][currentCol]) {
					winVertical = true;
				} else {
					winVertical = false;
					break;
				}
			} else {
				break;
			}

		}
		return winVertical;
	}

	public static void isWon() {

		if (winVertical(colInsert) == true || winHorizontal(rowInsert) == true
				|| winDiagonal(rowInsert, colInsert) == true) {
			playing = false;
			inputValid = true;

			String player = null;
			switch (currentPlayer) {
			case 1:
				player = "O";
				break;
			case 2:
				player = "X";
				break;
			}
			System.out.println(player + " won. Bye!");
		} else {
			isPlaying();
		}

	}

}

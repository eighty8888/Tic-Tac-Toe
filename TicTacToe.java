import java.util.*;
public class TicTacToe {
	public static void main(String[] args){
		Scanner input= new Scanner(System.in);
		System.out.println("Here is the board: ");
		TTTBoard board = new TTTBoard();
		board.printBoard();
		System.out.println("Player 1, do you want to be X or O?");
		Player player1 = new Player();
		Player player2 = new Player();
		boolean player1Goes = false;
		ArrayList<Character> inputHistory = new ArrayList<>();
		char response = input.next().charAt(0);
		while (response != 'X' && response != 'O') {
			System.out.println("Invalid input. X or O?");
			response = input.next().charAt(0);
		}
		if (response == 'X') {
			player1.setName("Player 1");
			player1.setSymbol(response);
			player2.setName("Player 2");
			player2.setSymbol('O');
			System.out.println("Player 1 is X and Player 2 is O. Since player 1 is X, they go first");
			player1Goes = true;
		}else if (response == 'O') {
			player1.setName("Player 1");
			player1.setSymbol(response);
			player2.setName("Player 2");
			player2.setSymbol('X');
			System.out.println("Player 1 is O and Player 2 is X. Since player 2 is X, they go first");
			player1Goes = false;
		}
		while (!board.isFilled() && !board.winnerExists()) {
			if (player1Goes) {
				System.out.println("Player 1: Enter the row and column you want to place your " + player1.getSymbol() + ": ");
				int row = input.nextInt();
				int col = input.nextInt();
				while (!(row < 4 && row > 0)) {
					System.out.println("Your row input was invalid. Enter a row between 1 and 3: ");
					row = input.nextInt();
				}
				while (!(col < 4 && col > 0)) {
					System.out.println("Your column input was invalid. Enter a column between 1 and 3: ");
					col = input.nextInt();
				}
				while(!board.getSpaces()[row - 1][col - 1].equals("   ")) {
					System.out.println("This spot is filled. Enter a different row and column: ");
					row = input.nextInt();
					col = input.nextInt();
					while (!(row < 4 && row > 0)) {
						System.out.println("Your row input was invalid. Enter a row between 1 and 3: ");
						row = input.nextInt();
					}
					while (!(col < 4 && col > 0)) {
						System.out.println("Your column input was invalid. Enter a column between 1 and 3: ");
						col = input.nextInt();
					}
				}
				board.addSymbol(player1.getSymbol(), row - 1, col - 1);
				inputHistory.add(player1.getSymbol());
				board.printBoard();
				player1Goes = false;
			}else {
				System.out.println("Player 2: Enter the row and column you want to place your " + player2.getSymbol() + ": ");
				int row = input.nextInt();
				int col = input.nextInt();
				while (!(row < 4 && row > 0)) {
					System.out.println("Your row input was invalid. Enter a row between 1 and 3: ");
					row = input.nextInt();
				}
				while (!(col < 4 && col > 0)) {
					System.out.println("Your column input was invalid. Enter a column between 1 and 3: ");
					col = input.nextInt();
				}
				while(!board.getSpaces()[row - 1][col - 1].equals("   ")) {
					System.out.println("This spot is filled. Enter a different row and column: ");
					row = input.nextInt();
					col = input.nextInt();
					while (!(row < 4 && row > 0)) {
						System.out.println("Your row input was invalid. Enter a row between 1 and 3: ");
						row = input.nextInt();
					}
					while (!(col < 4 && col > 0)) {
						System.out.println("Your column input was invalid. Enter a column between 1 and 3: ");
						col = input.nextInt();
					}
				}
				board.addSymbol(player2.getSymbol(), row - 1, col - 1);
				inputHistory.add(player2.getSymbol());
				board.printBoard();
				player1Goes = true;
			}
		}
		if (board.winnerExists()) {
			if (inputHistory.get(inputHistory.size() - 1) == player1.getSymbol()) {
				System.out.println("Player 1 wins!");
			}else if (inputHistory.get(inputHistory.size() - 1) == player2.getSymbol()) {
				System.out.println("Player 2 wins!");
			}
		}else {
			System.out.println("Tied");
		}
		input.close();
	}
}
class TTTBoard {
	private String[][] spaces = {{"   ", "   ", "   "}, {"   ", "   ", "   "}, {"   ", "   ", "   "}};
	
	TTTBoard() {
		
	}
	public String[][] getSpaces() {
		return spaces;
	}
	public void addSymbol(char symbol, int row, int col) {
		spaces[row][col] = " " + symbol + " ";
	}
	public void printBoard() {
		for (int i = 0; i < spaces.length; i++) {
			for (int j = 0; j < spaces[i].length; j++) {
				if (j == 0) {
					System.out.print(spaces[i][j] + "|");
				}else if (j == spaces[i].length - 1) {
					System.out.print("|" + spaces[i][j]);
				}else {
					System.out.print(spaces[i][j]);
				}
			}
			System.out.println();
			if (i != spaces.length - 1) {
				System.out.println("---+---+---");
			}
		}
	}
	public boolean isFilled() {
		for (int i = 0; i < spaces.length; i++) {
			for (int j = 0; j < spaces[i].length; j++) {
				if (spaces[i][j].equals("   ")) {
					return false;
				}
			}
		}
		return true;
	}
	public boolean winnerExists() {
		boolean satisfied = true;
		for (int i = 0; i < spaces.length; i++) {
			int countX = 0;
			int countO = 0;
			for (int j = 0; j < spaces[i].length; j++) {
				if (spaces[i][j].equals(" X ")) {
					countX++;
				}else if (spaces[i][j].equals(" O ")) {
					countO++;
				}
			}
			if (countX == 3 || countO == 3) {
				satisfied = true;
				return satisfied;
			}else {
				satisfied = false;
			}
		}
		for (int i = 0; i < spaces.length; i++) {
			int countX = 0;
			int countO = 0;
			for (int j = 0; j < spaces[i].length; j++) {
				if (spaces[j][i].equals(" X ")) {
					countX++;
				}else if (spaces[j][i].equals(" O ")) {
					countO++;
				}
			}
			if (countX == 3 || countO == 3) {
				satisfied = true;
				return satisfied;
			}else {
				satisfied = false;
			}
		}
		int countX = 0;
		int countO = 0;
		for (int i = 0, j = 0; i < spaces.length; i++, j++) {
			if (spaces[i][j].equals(" X ")) {
				countX++;
			}else if (spaces[i][j].equals(" O ")) {
				countO++;
			}
			if (countX == 3 || countO == 3) {
				satisfied = true;
				return satisfied;
			}else {
				satisfied = false;
			}
		}
		countX = 0;
		countO = 0;
		for (int i = 0, j = spaces.length - 1; i < spaces.length; i++, j--) {
			if (spaces[i][j].equals(" X ")) {
				countX++;
			}else if (spaces[i][j].equals(" O ")) {
				countO++;
			}
			if (countX == 3 || countO == 3) {
				satisfied = true;
				return satisfied;
			}else {
				satisfied = false;
			}
		}
		return satisfied;
	}
}
class Player {
	private String name;
	private char symbol;
	Player(){
		
	}
	Player(String name, char symbol){
		this.name = name;
		this.symbol = symbol;
	}
	public String getName() {
		return name;
	}
	public char getSymbol() {
		return symbol;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
}

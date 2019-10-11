import java.util.ArrayList;
import java.util.Scanner;

public class PlayCheckers {
	public static void main (String[] args) {
		// Get information from player needed to start game
		Scanner keyIn = new Scanner(System.in);
		System.out.print("Enter player color (red or black): ");
		String playerColor = keyIn.next();
		System.out.print("Enter Starting color (red or black): ");
		String currentColor = keyIn.next();
		
		Board board = new Board();
		
		while(board.getBlackCount() > 0 || board.getRedCount() > 0) {
			// Print the board for the current turn
			board.print();
			
			// Find all the moves current player can make
			ArrayList currentMoves = board.findMoves(currentColor);
			
			// Print a list of all moves
			for(Object m: currentMoves) {
				System.out.println("Index: " + currentMoves.indexOf(m) +
								   " Start: " + ((Move) m).getStart().getRow() + "," + ((Move) m).getStart().getColumn() +
								   " End: " + ((Move) m).getEnd().getRow() + "," + ((Move) m).getEnd().getColumn() +
								   " Point Change: " + ((Move) m).getValueChange());
			}
			
			if(currentColor.equals(playerColor)) {
				// Let player make a turn
				System.out.print("Enter index of the move you want to make: ");
				int index = keyIn.nextInt();
				board.MakeMove((Move) currentMoves.get(index));
			} else {
				// AI turn to make move
				board.MakeMove(board.BestMove(currentMoves));
			}
			
			// Change current player
			if (currentColor == "black") {
				currentColor = "red";
			} else {
				currentColor = "black";
			}
		}
	}
}

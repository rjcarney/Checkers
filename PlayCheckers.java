import java.util.ArrayList;
import java.util.Scanner;

public class PlayCheckers {
	public static void main (String[] args) {
		// Get information from player needed to start game
		Scanner keyIn = new Scanner(System.in);
		System.out.print("Enter player color (red or black): ");
		String playerColor = keyIn.nextLine();
		System.out.print("Enter Starting color (red or black): ");
		String currentColor = keyIn.nextLine();
		
		Board board = new Board();
		
		while(board.getBlackCount() > 0 && board.getRedCount() > 0) {
			// Print the board for the current turn
			board.print();
			
			
			// Find all the moves current player can make
			ArrayList<Move> currentMoves = new ArrayList<Move>();
			if(currentColor.equals("red")) {
				currentMoves = board.findMoves("red");
			} else {
				currentMoves = board.findMoves("black");
			}
			
			
			// Print a list of all moves
			for(Move m: currentMoves) {
				System.out.println("Index: " + currentMoves.indexOf(m) +
								   " Start: " + m.getStart().getPosition() +
								   " End: " + m.getEnd().getPosition());
								   //+ " Point Change: " + m.getValueChange());
			}
			
			if(currentColor.equals(playerColor)) {
				// Let player make a turn
				System.out.print("Enter index of the move you want to make: ");
				int index = keyIn.nextInt();
				board.MakeMove((Move) currentMoves.get(index));
			} else {
				// AI turn to make move
				Move bestMove = board.BestMove(currentMoves);
				System.out.println("Computer Made Move: " + currentMoves.indexOf(bestMove));
				board.MakeMove(bestMove);
			}
			
			// Change current player
			if (currentColor.equals("black")) {
				currentColor = "red";
			} else {
				currentColor = "black";
			}
		}
		
		if(board.getBlackCount() > 0)
			System.out.println("Winner is black");
		else
			System.out.println("Winner is red");
	}
}

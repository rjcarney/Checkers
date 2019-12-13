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
		
		//int[] rPos = {7, 27, 28, 29, 31, 32};
		//int[] bPos = {1, 2, 4, 20, 22};
		//Board board = new Board(rPos, bPos);
		
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
				if(m.getJumpedSquares().size() > 0) {
					for(Square s: m.getJumpedSquares()) {
						if(m.getJumpedSquares().indexOf(s)%2 == 0)
							System.out.println("Jumps " + s.getPosition());
						else
							System.out.println("Lands in " + s.getPosition());
					}
				}
			}
			
			if(currentColor.equals(playerColor)) {
				// Let player make a turn
				System.out.print("Enter index of the move you want to make: ");
				int index = keyIn.nextInt();
				board.MakeMove((Move) currentMoves.get(index));
			} else {
				// AI turn to make move
				GameTree tree = new GameTree(board, currentColor);
				Move bestMove = tree.MinMax();
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

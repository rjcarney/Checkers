import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int[] rPos = {32};
		int[] bPos = {27, 18};
		Board board = new Board(rPos, bPos);
		
		board.print();
		
		ArrayList<Move> moves = board.findMoves("red");
		for(Move m: moves) {
			System.out.println("Index: " + moves.indexOf(m) +
							   " Start: " + m.getStart().getPosition() +
							   " End: " + m.getEnd().getPosition() + 
							   " Point Change: " + m.getValueChange());
		}
		board.MakeMove(board.BestMove(moves));
		
		board.print();
	}
}

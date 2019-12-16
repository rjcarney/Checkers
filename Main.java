import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int[] rPos = {29, 32};
		int[] bPos = {18, 22, 23, 26};
		int[] rPosKing = {12};
		int[] bPosKing = {};
		Board board = new Board(rPos, bPos, rPosKing, bPosKing);
		
		board.print();
		
		ArrayList<Move> moves = board.findMoves("black");
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

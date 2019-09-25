import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		Board board = new Board();
		board.print();
		ArrayList moves = board.findMoves("black");
		for(Object m: moves) {
			System.out.println("Start: " + ((Move) m).getStart().getRow() + "," + ((Move) m).getStart().getColumn() +
							   " End: " + ((Move) m).getEnd().getRow() + "," + ((Move) m).getEnd().getColumn() +
							   " Point Change: " + ((Move) m).getValueChange());
		}
		Move bestMove = board.BestMove(moves);
		System.out.println("Best Move is Start: " + bestMove.getStart().getRow() + "," +
							bestMove.getStart().getColumn() + " End: " + bestMove.getEnd().getRow() +
							"," + bestMove.getEnd().getColumn() + " Point Change: " + bestMove.getValueChange());
		board.MakeMove(bestMove);
		board.print();
		
		moves = board.findMoves("red");
		for(Object m: moves) {
			System.out.println("Start: " + ((Move) m).getStart().getRow() + "," + ((Move) m).getStart().getColumn() +
							   " End: " + ((Move) m).getEnd().getRow() + "," + ((Move) m).getEnd().getColumn() +
							   " Point Change: " + ((Move) m).getValueChange());
		}
		bestMove = board.BestMove(moves);
		System.out.println("Best Move is Start: " + bestMove.getStart().getRow() + "," +
							bestMove.getStart().getColumn() + " End: " + bestMove.getEnd().getRow() +
							"," + bestMove.getEnd().getColumn() + " Point Change: " + bestMove.getValueChange());
		board.MakeMove(bestMove);
		board.print();
		
		moves = board.findMoves("black");
		for(Object m: moves) {
			System.out.println("Start: " + ((Move) m).getStart().getRow() + "," + ((Move) m).getStart().getColumn() +
							   " End: " + ((Move) m).getEnd().getRow() + "," + ((Move) m).getEnd().getColumn() +
							   " Point Change: " + ((Move) m).getValueChange());
		}
		bestMove = board.BestMove(moves);
		System.out.println("Best Move is Start: " + bestMove.getStart().getRow() + "," +
							bestMove.getStart().getColumn() + " End: " + bestMove.getEnd().getRow() +
							"," + bestMove.getEnd().getColumn() + " Point Change: " + bestMove.getValueChange());
		board.MakeMove(bestMove);
		board.print();
		
		moves = board.findMoves("red");
		for(Object m: moves) {
			System.out.println("Start: " + ((Move) m).getStart().getRow() + "," + ((Move) m).getStart().getColumn() +
							   " End: " + ((Move) m).getEnd().getRow() + "," + ((Move) m).getEnd().getColumn() +
							   " Point Change: " + ((Move) m).getValueChange());
		}
		bestMove = board.BestMove(moves);
		System.out.println("Best Move is Start: " + bestMove.getStart().getRow() + "," +
							bestMove.getStart().getColumn() + " End: " + bestMove.getEnd().getRow() +
							"," + bestMove.getEnd().getColumn() + " Point Change: " + bestMove.getValueChange());
		board.MakeMove(bestMove);
		board.print();
	}
}

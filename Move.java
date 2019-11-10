import java.util.ArrayList;

public class Move {
	public Square start;
	public Square end;
	public Checker checker;
	public ArrayList<Square> jumpedSquares;
	
	public Move(Square s, Square e, Checker c, int v) {
		this.start = s;
		this.end = e;
		this.checker = c;
		this.jumpedSquares = new ArrayList<Square>();
	}
	
	public Move(Move m, Square adj, Square landing) {
		this.start = m.getStart();
		this.end = landing;
		this.checker = m.getChecker();
		this.jumpedSquares = new ArrayList<Square>();
		if(m.getJumpedSquares().size() > 0) {
			for(Square s: m.getJumpedSquares()) {
				this.jumpedSquares.add(s);
			}
		}
		this.jumpedSquares.add(adj);
		this.jumpedSquares.add(landing);
	}

	public Square getStart() { return this.start; }
	public Square getEnd() { return this.end; }
	public Checker getChecker() { return this.checker; }
	public int getValueChange() { return this.end.getValue() - this.start.getValue(); }
	public ArrayList<Square> getJumpedSquares() { return this.jumpedSquares; }
	
	public void addJumpedSquare(Square s) {
		this.jumpedSquares.add(s);
	}

	public void setEnd(Square e) { this.end = e; }
}

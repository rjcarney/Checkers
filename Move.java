import java.util.ArrayList;

public class Move {
	public Square start;
	public Square end;
	public Checker checker;
	public int valueChange;
	public ArrayList<Square> jumpedSquares;
	
	public Move(Square s, Square e, Checker c, int v) {
		this.start = s;
		this.end = e;
		this.checker = c;
		this.valueChange = v;
		this.jumpedSquares = new ArrayList<Square>();
	}
	
	public Move(Move m, Square s) {
		this.start = m.getStart();
		this.end = m.getEnd();
		this.checker = m.getChecker();
		this.valueChange = m.getValueChange();
		this.jumpedSquares = new ArrayList<Square>();
		boolean matches = true;
		for(Square x: m.getJumpedSquares()) {
			if(matches = true) {
				if(x == s) {
					this.jumpedSquares.add(x);
					matches = false;
				} else {
					this.jumpedSquares.add(x);
				}
			}
		}
		
	}

	public Square getStart() { return this.start; }
	public Square getEnd() { return this.end; }
	public Checker getChecker() { return this.checker; }
	public int getValueChange() { return this.valueChange; }
	public ArrayList<Square> getJumpedSquares() { return this.jumpedSquares; }
	
	public void addJumpedSquare(Square s) {
		this.jumpedSquares.add(s);
	}

	public void setEnd(Square e) { this.end = e; }
}


public class Square {
	public String color;
	public Checker occupyingChecker;
	public boolean isOccupied;
	public int row;
	public int column;
	public int value;
	public int position;
	
	public Square(String c, int r, int col, int v, int p) {
		this.color = c;
		this.row = r;
		this.column = col;
		this.value = v;
		this.position = p;
	}
	
	public String getColor() { return this.color;}
	public int getRow() { return this.row;}
	public int getColumn() { return this.column;}
	public boolean isOccupied() { return this.occupyingChecker != null;}
	public Checker getOccupyingChecker() {return this.occupyingChecker;}
	
	public Checker placeChecker(String c, int d) {
		this.occupyingChecker = new Checker(c, d);
		return this.occupyingChecker;
	}
	
	public Checker placeChecker(String c, int d, boolean isKing) {
		this.occupyingChecker = new Checker(c, d);
		if(isKing)
			this.occupyingChecker.setKing();
		return this.occupyingChecker;
	}
	
	public void placeChecker(Checker c) {
		this.occupyingChecker = c;
	}

	public Checker remove() {
		Checker c = getOccupyingChecker();
		this.occupyingChecker = null;
		return c;
	}
	public int getValue() {return this.value;}

	public int getPosition() { return this.position; }

}

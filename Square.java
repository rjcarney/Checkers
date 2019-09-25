
public class Square {
	public String color;
	public Checker occupyingChecker;
	public boolean isOccupied;
	public int row;
	public int column;
	public int value;
	
	public Square(String c, int r, int col, int v) {
		this.color = c;
		this.row = r;
		this.column = col;
		this.value = v;
	}
	
	public String getColor() { return this.color;}
	public int getRow() { return this.row;}
	public int getColumn() { return this.column;}
	public boolean isOccupied() { return this.occupyingChecker != null;}
	public Checker getOccupyingChecker() {return this.occupyingChecker;}
	
	public void placeChecker(String c, int d) {
		this.occupyingChecker = new Checker(c, d);
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

}

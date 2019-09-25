
public class Checker {
	public String color;
	public int moveDirection;
	public boolean isKing;
	
	public Checker(String c, int d) {
		this.color = c;
		this.moveDirection = d;
	}

	public String getColor() {return this.color;}
	public int getMoveDirection() { return this.moveDirection; }

	public boolean isKing() { return this.isKing; }
}
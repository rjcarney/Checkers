
public class Checker {
	public String color;
	public int moveDirection;
	public boolean isKing;
	
	public Checker(String c, int d) {
		this.color = c;
		this.moveDirection = d;
	}

	public Checker(Checker c) {
		this.color = c.getColor();
		this.moveDirection = c.getMoveDirection();
		if(c.isKing())
			this.setKing();
	}

	public String getColor() {return this.color;}
	public int getMoveDirection() { return this.moveDirection; }

	public boolean isKing() { return this.isKing; }

	public void setKing() { this.isKing = true; }
}
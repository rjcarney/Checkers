import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JFrame {
	public GUI() {
		super();
		this.setSize(800, 800);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(8,8));
		this.setVisible(true);
	}
	
	public class SquarePanel extends JPanel {
	
		public Square square;
		
		
		public SquarePanel(Square s) {
			super();
			this.square = s;
			this.setSize(100, 100);
			
			if(s.getColor().equals("black")) {
				this.setBackground(Color.BLACK);
			} else {
				this.setBackground(Color.RED);
			}
			
		}
		
		public void addChecker(Checker c, Graphics g) {
			Color x;
			if(c.getColor().equals("red"))
				x = Color.WHITE;
			else
				x = Color.YELLOW;
			
			g.setColor(x);
			g.fillOval(0, 0, 80, 80);
			this.setVisible(true);
		}
		
		public void removeChecker(Graphics g) {
			g.clearRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		}
	}

	public SquarePanel addSquare(Square square) {
		SquarePanel newSquare = new SquarePanel(square);
		this.add(newSquare);
		return newSquare;
	}
}

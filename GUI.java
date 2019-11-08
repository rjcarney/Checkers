import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JFrame {
	private ArrayList<SquarePanel> panels;
	Square[][] board;
	
	public GUI(Square[][] board) {
		super();
		this.board = board;
		this.setSize(800, 800);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(8,8));
		this.setVisible(true);
		this.panels = new ArrayList<SquarePanel>();
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				SquarePanel pan = this.addSquare(board[i][j]);
				panels.add(pan);
				if(board[i][j].isOccupied()) {
					pan.addChecker(board[i][j].getOccupyingChecker(), pan.getGraphics());
				}
			}
		}
		
		this.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent componentEvent) {
				for(int i = 0; i < 8; i++) {
					for(int j = 0; j < 8; j++) {
						if(board[i][j].isOccupied()) {
							SquarePanel pan = panels.get((i*8)+j);
							pan.addChecker(board[i][j].getOccupyingChecker(), pan.getGraphics());
						}
					}
				}
			}
		});
	}
	
	

	public class SquarePanel extends JPanel {
	
		public Square square;
		
		
		public SquarePanel(Square s) {
			super();
			this.square = s;
			this.setSize(50, 50);
			
			if(s.getColor().equals("black")) {
				this.setBackground(Color.GRAY);
			} else {
				this.setBackground(Color.WHITE);
			}
			
		}
		
		public void addChecker(Checker c, Graphics g) {
			Color x;
			if(c.getColor().equals("red"))
				x = Color.RED;
			else
				x = Color.BLACK;
			
			g.setColor(x);
			g.fillOval(0, 0, 40, 40);
		}
		
		public void removeChecker(Graphics g) {
			g.setColor(Color.GRAY);
			g.fillOval(0, 0, 40, 40);
		}
	}

	public SquarePanel addSquare(Square square) {
		SquarePanel newSquare = new SquarePanel(square);
		this.add(newSquare);
		return newSquare;
	}

	public SquarePanel getPanel(int row, int column) {
		return this.panels.get((row*8)+column);
	}
}

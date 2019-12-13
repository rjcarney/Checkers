import java.util.ArrayList;

public class GameTree {

	public class BoardNode {
		private Board board;
		private BoardNode parent;
		private ArrayList<BoardNode> children;
		private String turnColor;
		
		/*  CONSTRUCTOR FOR THE ROOT NODE
		 *  board = the current board configuration
		 *  parent = null
		 *  children = all the possible boards after a move that can be made by current player
		 *  color = the color of current player 
		 *  searchDepth = amount of move we will look ahead
		 */
		BoardNode(Board board, String color, int searchDepth){
			this.board = board;
			this.turnColor = color;
			this.parent = null;
			this.children = new ArrayList<BoardNode>();
			if(searchDepth > 0) {
				for(Move m: board.findMoves(color)) {
					Board newBoard = new Board(board, m);
					if(color.equals("black"))
						this.children.add(new BoardNode(this, newBoard, "red", searchDepth--));
					else
						this.children.add(new BoardNode(this, newBoard, "black", searchDepth--));
				}
			}
		}
		
		
		/*  CONSTRUCT CHILDREN NODES
		 *  board = board from parent node with a move made
		 *  parent = parent
		 *  children = all the possible boards after a move that can be made by current player
		 *  color = player that will be taking the next move
		 *  searchDepth = searchDepth--
		 */
		public BoardNode(BoardNode parent, Board board, String color, int searchDepth) {
			this.board = board;
			this.parent = parent;
			this.turnColor = color;
			this.children = new ArrayList<BoardNode>();
			if(searchDepth > 0) {
				for(Move m: board.findMoves(color)) {
					Board newBoard = new Board(board, m);
					if(color.equals("black"))
						this.children.add(new BoardNode(this, newBoard, "red", searchDepth--));
					else
						this.children.add(new BoardNode(this, newBoard, "black", searchDepth--));
				}
			}
		}
		
		public int BoardValue(String color) {
			if(color.equals("red"))
				return this.board.getRedCount() - this.board.getBlackCount();
			else
				return this.board.getBlackCount() - this.board.getRedCount();
		}
	}
	
	private BoardNode root;

	public GameTree(Board board, String turnColor) {
		this.root = new BoardNode(board, turnColor, 6);
	}
	
	
}

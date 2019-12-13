import java.util.ArrayList;

public class GameTree {

	public class BoardNode {
		private Board board;
		private BoardNode parent;
		private ArrayList<BoardNode> children;
		private String turnColor;
		private Move moveMade;
		
		/*  CONSTRUCTOR FOR THE ROOT NODE
		 *  board = the current board configuration
		 *  parent = null
		 *  children = all the possible boards after a move that can be made by current player
		 *  color = the color of current player 
		 *  searchDepth = amount of move we will look ahead
		 */
		BoardNode(Board board, String color, int searchDepth, ArrayList<BoardNode> leaves){
			this.board = board;
			this.turnColor = color;
			this.parent = null;
			this.children = new ArrayList<BoardNode>();
			this.moveMade = null;
			if(searchDepth > 0) {
				for(Move m: board.findMoves(color)) {
					Board newBoard = new Board(board, m);
					if(color.equals("black"))
						this.children.add(new BoardNode(this, newBoard, "red", searchDepth--, leaves, m));
					else
						this.children.add(new BoardNode(this, newBoard, "black", searchDepth--, leaves, m));
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
		public BoardNode(BoardNode parent, Board board, String color, int searchDepth, ArrayList<BoardNode> leaves, Move moveMade) {
			this.board = board;
			this.parent = parent;
			this.turnColor = color;
			this.children = new ArrayList<BoardNode>();
			this.moveMade = moveMade;
			if(searchDepth == 0 ) {
				leaves.add(this);
			}
			if(searchDepth > 0) {
				for(Move m: board.findMoves(color)) {
					Board newBoard = new Board(board, m);
					if(color.equals("black")) {
						this.children.add(new BoardNode(this, newBoard, "red", searchDepth-1, leaves, m));
					} else {
						this.children.add(new BoardNode(this, newBoard, "black", searchDepth-1, leaves, m));
					}
				}
			}
		}
		
		public int BoardValue(String color) {
			if(color.equals("red"))
				return this.board.getRedCount() - this.board.getBlackCount();
			else
				return this.board.getBlackCount() - this.board.getRedCount();
		}
		
		public Move getMoveMade() { return this.moveMade; }
		public BoardNode getParent() { return this.parent; }
		public String getTurnColor() { return this.turnColor; }
	}
	
	private ArrayList<BoardNode> leaves;
	private BoardNode root;

	public GameTree(Board board, String turnColor) {
		this.leaves = new ArrayList<BoardNode>();
		this.root = new BoardNode(board, turnColor, 6, this.leaves);
	}
	
	public ArrayList<BoardNode> getLeaves() { return this.leaves; }
	
	public Move MinMax() {
		for(BoardNode l: this.getLeaves()) {
			System.out.println("there is a leaf");
		}
		ArrayList<Integer> leafValues = new ArrayList<Integer>();
		for(BoardNode l: this.getLeaves()) {
			int v1 = l.BoardValue(l.getTurnColor());
			BoardNode n2 = l.getParent();
			if(!n2.equals(this.getRoot())) {
				int v2 = n2.BoardValue(n2.turnColor);
				BoardNode n3 = n2.getParent();
				if(!n3.equals(this.getRoot())) {
					int v3 = n3.BoardValue(n3.turnColor);
					BoardNode n4 = n3.getParent();
					if(!n4.equals(this.getRoot())) {
						int v4 = n4.BoardValue(n4.turnColor);
						BoardNode n5 = n4.getParent();
						if(!n5.equals(this.getRoot())) {
							int v5 = n5.BoardValue(n5.turnColor);
							BoardNode n6 = n5.getParent();
							if(!n6.equals(this.getRoot())) {
								int v6 = n6.BoardValue(n6.turnColor);
								Integer lValue = v6-v5+v4-v3+v2-v1;
								leafValues.add(lValue);
							} else {
								Integer lValue = v5-v4+v3-v2+v1;
								leafValues.add(lValue);
							}
						} else {
							Integer lValue = v4-v3+v2-v1;
							leafValues.add(lValue);
						}
					} else {
						Integer lValue = v3-v2+v1;
						leafValues.add(lValue);
					}
				} else {
					Integer lValue = v2-v1;
					leafValues.add(lValue);
				}
			} else {
				Integer lValue = v1;
				leafValues.add(lValue);
			}	
		}
		Integer max = leafValues.get(0);
		int maxIndex = 0;
		for(int i = 0; i < leafValues.size(); i++) {
			if(leafValues.get(i) > max) {
				max = leafValues.get(i);
				maxIndex = i;
			}
		}
		BoardNode bestLeaf = this.leaves.get(maxIndex);
		BoardNode nextBoard = bestLeaf.getParent().getParent().getParent().getParent().getParent();
		return nextBoard.getMoveMade();
	}

	private BoardNode getRoot() { return this.root; }
	
}

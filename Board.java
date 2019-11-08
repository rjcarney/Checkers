import java.util.ArrayList;

import javax.swing.JPanel;

public class Board {
	Square[][] board;
	int redCount;
	int blackCount;
	GUI g;

	public int getRedCount() { return this.redCount; }
	public int getBlackCount() { return this.blackCount; }
	
	// Construct Game Board
	public Board() {
		this.board = new Square[8][8];
		this.redCount = 0;
		this.blackCount = 0;
		int position = 1;
		//Place 
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				// Red Squares
				// Player Can Not Use, All Are Worth 0 Points
				if(i%2 == 0 && j%2 == 0) {
						board[i][j] = new Square("red", i, j, 0, 0);
				} else if (i%2 == 1 && j%2 == 1) {
					board[i][j] = new Square("red", i, j, 0, 0);
				}
				
				// Black Squares
				// Player Can Use These Spaces
				// Assign Points Based On Location On Board
				else {
					// Back Rows (5 points)
					if(i == 0 || i == 7) {
						board[i][j] = new Square("black", i, j, 5, position);
					}
					// Outside Wall (1 point)
					else if((j == 0 && i>0 && i<7) ||
							(j == 7 && i>0 && i<7)) {
						board[i][j] = new Square("black", i, j, 1, position);
					}
					// Second Squares (2 points)
					else if((i == 1 && j>0 && j<7) ||
							(i == 6 && j>0 && j<7) ||
							(j == 1 && i>0 && i<7) ||
							(j == 6 && i>0 && i<7)) {
						board[i][j] = new Square("black", i, j, 2, position);
					}
					// Third Squares (3 points)
					else if((i == 2 && j>1 && j<6) ||
							(i == 5 && j>1 && j<6) ||
							(j == 2 && i>1 && i<6) ||
							(j == 5 && i>1 && i<6)) {
						board[i][j] = new Square("black", i, j, 3, position);
					}
					// Center (4 points)
					else {
						board[i][j] = new Square("black", i, j, 4, position);
					}
					
					position++;
					
					//Place Checkers On The Correct Squares
					if(i < 3) {
						Checker c = board[i][j].placeChecker("black", 1);
						this.blackCount++;
					}
					else if(i > 4) {
						Checker c = board[i][j].placeChecker("red", -1);
						this.redCount++;
					}
				}
			
			}
		}
		this.g = new GUI(board);
	}

	// Construct Game Board
		public Board(int[] rPos, int[] bPos) {
			this.board = new Square[8][8];
			this.redCount = 0;
			this.blackCount = 0;
			int position = 1;
			//Place 
			for(int i = 0; i < 8; i++) {
				for(int j = 0; j < 8; j++) {
					// Red Squares
					// Player Can Not Use, All Are Worth 0 Points
					if(i%2 == 0 && j%2 == 0)
							board[i][j] = new Square("red", i, j, 0, 0);
					else if (i%2 == 1 && j%2 == 1) 
						board[i][j] = new Square("red", i, j, 0, 0);
					
					// Black Squares
					// Player Can Use These Spaces
					// Assign Points Based On Location On Board
					else {
						// Back Rows (5 points)
						if(i == 0 || i == 7)
							board[i][j] = new Square("black", i, j, 5, position);
						// Outside Wall (1 point)
						else if((j == 0 && i>0 && i<7) ||
								(j == 7 && i>0 && i<7))
							board[i][j] = new Square("black", i, j, 1, position);
						// Second Squares (2 points)
						else if((i == 1 && j>0 && j<7) ||
								(i == 6 && j>0 && j<7) ||
								(j == 1 && i>0 && i<7) ||
								(j == 6 && i>0 && i<7))
							board[i][j] = new Square("black", i, j, 2, position);
						// Third Squares (3 points)
						else if((i == 2 && j>1 && j<6) ||
								(i == 5 && j>1 && j<6) ||
								(j == 2 && i>1 && i<6) ||
								(j == 5 && i>1 && i<6))
							board[i][j] = new Square("black", i, j, 3, position);
						// Center (4 points)
						else
							board[i][j] = new Square("black", i, j, 4, position);
						
						for(int p: rPos) {
							if(p == position) {
								board[i][j].placeChecker("red", -1);
								this.redCount++;
							}
						}
						for(int p: bPos) {
							if(p == position) {
								board[i][j].placeChecker("black", 1);
								this.blackCount++;
							}
						}
						
						position++;
					}
				}
			}
			this.g = new GUI(board);
		}
	
	
	
	
	
	
	
	//Return An Array Of All Possible Moves
	public ArrayList<Move> findMoves(String c) {
		ArrayList<Move> possibleMoves = new ArrayList<Move>();
		//Search board
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				// Find A Checker Matching The Turn Color c
				if(board[i][j].isOccupied() && board[i][j].occupyingChecker.getColor() == c) {
					//Set Start Square And Current Checker For Move
					System.out.println("Checker found at " + board[i][j].getPosition());
					Square start = board[i][j];
					Checker current = board[i][j].getOccupyingChecker();
					// Check If Checker Can Occupy Adjacent Square
					for(Square s: AdjSquares(start)) {
						if(s.isOccupied() == false &&
						   ((s.getRow() - start.getRow())*current.getMoveDirection() > 0 || current.isKing())) {
								// Space Is Empty And Checker Can Move This Direction
								Move move = new Move(start, s, current, s.getValue()-start.getValue());
								possibleMoves.add(move);
						} else if (s.isOccupied() && s.getOccupyingChecker().getColor() != current.getColor() &&
						   ((s.getRow() - start.getRow())*current.getMoveDirection() > 0 || current.isKing())){
								// Next To Opponent Check If You Can Jump
								if(possibleJump(start, s)) {
									Square end = board[s.getRow()+(s.getRow()-start.getRow())][s.getColumn()+(s.getColumn()-start.getColumn())];
									Move move = new Move(start, end, current, end.getValue()-start.getValue());
									ArrayList<Move> jumps = new ArrayList<Move>();
									JumpPath(start, current, move, jumps);
									for(Move m: jumps) {
										possibleMoves.add(m);
									}
									for(Square s2: move.getJumpedSquares()) {
										System.out.println(s2.getPosition());
									}
									
									move.setEnd(move.getJumpedSquares().get(move.getJumpedSquares().size()-1));
									System.out.println("Piece lands in " + move.getEnd().getPosition());
									possibleMoves.add(move);
								}								
						}
					}
				}
			}
			
		}
		return possibleMoves;
	}

	private boolean possibleJump(Square start, Square s) {
		boolean isPossible = false;
		if(s.getRow() + (s.getRow()-start.getRow()) >= 0 &&
		   s.getRow() + (s.getRow()-start.getRow()) <= 7 &&
		   s.getColumn() + (s.getColumn()-start.getColumn()) >= 0 &&
		   s.getColumn() + (s.getColumn()-start.getColumn()) <= 7) {
			if(board[s.getRow()+(s.getRow()-start.getRow())][s.getColumn()+(s.getColumn()-start.getColumn())].isOccupied() == false) {
				isPossible = true;
			}
		}
		return isPossible;
	}

	//Find Best Move
	public Move BestMove(ArrayList<Move> possibleMoves) {
		Move bestMove = possibleMoves.get(0);
		int bmAdjTotal = AdjTotal(bestMove.getStart());
		for(Move m: possibleMoves) {
			int mAdjTotal = AdjTotal(m.getStart());
			if(m.getJumpedSquares().isEmpty() && bestMove.getJumpedSquares().isEmpty()) {
				// No Jump Has Been Found
				if((m.getEnd().getRow()+(m.getEnd().getRow()-m.getStart().getRow())) >= 0 &&
				   (m.getEnd().getRow()+(m.getEnd().getRow()-m.getStart().getRow())) <= 7 &&
				   (m.getEnd().getColumn()+(m.getEnd().getColumn()-m.getStart().getColumn())) >= 0 &&
				   (m.getEnd().getColumn()+(m.getEnd().getColumn()-m.getStart().getColumn())) <= 7) {
					int row = (m.getEnd().getRow()+(m.getEnd().getRow()-m.getStart().getRow()));
					int column = (m.getEnd().getColumn()+(m.getEnd().getColumn()-m.getStart().getColumn()));
					if(board[m.getEnd().getRow()+(m.getEnd().getRow()-m.getStart().getRow())][m.getEnd().getColumn()+(m.getEnd().getColumn()-m.getStart().getColumn())].isOccupied() == false ||
							   board[m.getEnd().getRow()+(m.getEnd().getRow()-m.getStart().getRow())][m.getEnd().getColumn()+(m.getEnd().getColumn()-m.getStart().getColumn())].getOccupyingChecker().getColor() == m.getChecker().getColor()) {
						// Does Not Give Opponent A Jump
						if(m.getValueChange() > bestMove.getValueChange()) {
							bestMove = m;
							bmAdjTotal = mAdjTotal;
						} else if(m.getValueChange() == bestMove.getValueChange() &&
						   m.getEnd().getValue() > bestMove.getEnd().getValue()) {
								bestMove = m;
								bmAdjTotal = mAdjTotal;
						} else if (m.getValueChange() == bestMove.getValueChange() &&
								   m.getEnd().getValue() == bestMove.getEnd().getValue() &&
								   mAdjTotal < bmAdjTotal) {
								bestMove = m;
								bmAdjTotal = mAdjTotal;	
						}
				}else {
					// Gives Opponent A Jump
					// Add Strategy To Only Allow Beneficial Trades
				}
				}  
			} else if (m.getJumpedSquares().isEmpty() == false && bestMove.getJumpedSquares().isEmpty()) {
				// If Can Jump Must Jump
				bestMove = m;
				bmAdjTotal = mAdjTotal;
			} else if (m.getJumpedSquares().isEmpty() == false && bestMove.getJumpedSquares().isEmpty() == false) {
				// Pick The Best Jump
				if(m.getJumpedSquares().size() > bestMove.getJumpedSquares().size()) {
					// Longest Jump
					bestMove = m;
					bmAdjTotal = mAdjTotal;
				} else if(m.getJumpedSquares().size() == bestMove.getJumpedSquares().size() &&
						  m.getEnd().getValue() > bestMove.getEnd().getValue()) {
					// Equal Jumps, Land In Best Position
					bestMove = m;
					bmAdjTotal = mAdjTotal;
				} else if(m.getJumpedSquares().size() == bestMove.getJumpedSquares().size() &&
						  m.getEnd().getValue() == bestMove.getEnd().getValue() &&
						  mAdjTotal < bmAdjTotal) {
					// Equal Jumps And Landing Values, Move Checker With Lower Adjacent Potential
					bestMove = m;
					bmAdjTotal = mAdjTotal;
				}
			}
		}
		return bestMove;
	}
	
	public void JumpPath(Square s, Checker c, Move m, ArrayList<Move> jumps){
		if(c.isKing()) {
			//Get All Adjacent Squares
			ArrayList<Square> adj = AdjSquares(s);
			for(Square a: adj) {
				if(a.isOccupied()) {
					// There is an Adjacent Checker
					if(a.getOccupyingChecker().getColor().equals(c.getColor()) == false) {
						//Adjacent Checker belongs to opponent
						if(a.getRow() + (a.getRow() - s.getRow()) >= 0 &&
						   a.getRow() + (a.getRow() - s.getRow()) <= 7 &&
						   a.getColumn() + (a.getColumn() - s.getColumn()) >= 0 &&
						   a.getColumn() + (a.getColumn() - s.getColumn()) <= 7) {
						   //Space behind checker is on the board
							if(board[a.getRow() + (a.getRow() - s.getRow())][a.getColumn() + (a.getColumn() - s.getColumn())].isOccupied() == false) {
								//Landing Square is available
								Square landing = board[a.getRow() + (a.getRow() - s.getRow())][a.getColumn() + (a.getColumn() - s.getColumn())];
								if(m.getJumpedSquares().size() == 0) {
									m.addJumpedSquare(a);
									m.addJumpedSquare(landing);
									jumps.add(m);
									JumpPath(landing, c, m, jumps);
								} else {
									Move newMove = new Move(m, s);
									newMove.addJumpedSquare(a);
									newMove.addJumpedSquare(landing);
									jumps.add(newMove);
									JumpPath(landing, c, newMove, jumps);
								}
							}
							
						}
					}
				}
			}
		} else {
			ArrayList<Square> adj = AdjSquares(s);
			for(Square a: adj) {
				if(a.isOccupied()) {
					if(a.getOccupyingChecker().getColor().equals(c.getColor()) == false) {
						if(a.getRow() - s.getRow() == c.getMoveDirection()) {
							if(a.getRow() + (a.getRow() - s.getRow()) >= 0 &&
							   a.getRow() + (a.getRow() - s.getRow()) <= 7 &&
							   a.getColumn() + (a.getColumn() - s.getColumn()) >= 0 &&
							   a.getColumn() + (a.getColumn() - s.getColumn()) <= 7) {
								if(board[a.getRow() + (a.getRow() - s.getRow())][a.getColumn() + (a.getColumn() - s.getColumn())].isOccupied() == false) {
									if(m.getJumpedSquares().size() == 0) {
										Square landing = board[a.getRow() + (a.getRow() - s.getRow())][a.getColumn() + (a.getColumn() - s.getColumn())];
										if(m.getJumpedSquares().size() == 0) {
											m.addJumpedSquare(a);
											m.addJumpedSquare(landing);
											jumps.add(m);
											JumpPath(landing, c, m, jumps);
										} else {
											Move newMove = new Move(m, s);
											newMove.addJumpedSquare(a);
											newMove.addJumpedSquare(landing);
											jumps.add(newMove);
											JumpPath(landing, c, newMove, jumps);
										}
									}
								}
							}
						}
					}	
				}
			}
		}
	}
	
	
	public void JumpPath(Square s, Checker c, Move m) {
		if(c.isKing()) {
			// Find all Adjacent Squares
			ArrayList<Square> adj = AdjSquares(s);
			for(Square a: adj) {
				if(a.isOccupied()) {
					if(a.getOccupyingChecker().getColor().equals(c.getColor()) == false) {
						// Is Occupied By An Opponent Checker
						if(a.getRow() + (a.getRow() - s.getRow()) >= 0 &&
						   a.getRow() + (a.getRow() - s.getRow()) <= 7 &&
						   a.getColumn() + (a.getColumn() - s.getColumn()) >= 0 &&
						   a.getColumn() + (a.getColumn() - s.getColumn()) <= 7) {
							if(board[a.getRow() + (a.getRow() - s.getRow())][a.getColumn() + (a.getColumn() - s.getColumn())].isOccupied() == false) {
								// Landing Square Is On The Board And Empty
								if(m.getJumpedSquares().size() == 0 ||
								   s == m.getJumpedSquares().get(m.getJumpedSquares().size()-1)) { //This should change to find longest jump
										Square landing = board[a.getRow() + (a.getRow() - s.getRow())][a.getColumn() + (a.getColumn() - s.getColumn())];
										m.addJumpedSquare(a);
										m.addJumpedSquare(landing);
										JumpPath(landing, c, m);
								}
							}
						}
					}
					
				}
			}
		} else {
			ArrayList<Square> adj = AdjSquares(s);
			for(Square a: adj) {
				if(a.isOccupied()) {
					if(a.getOccupyingChecker().getColor().equals(c.getColor()) == false) {
						if(a.getRow() - s.getRow() == c.getMoveDirection()) {
							if(a.getRow() + (a.getRow() - s.getRow()) >= 0 &&
							   a.getRow() + (a.getRow() - s.getRow()) <= 7 &&
							   a.getColumn() + (a.getColumn() - s.getColumn()) >= 0 &&
							   a.getColumn() + (a.getColumn() - s.getColumn()) <= 7) {
								if(board[a.getRow() + (a.getRow() - s.getRow())][a.getColumn() + (a.getColumn() - s.getColumn())].isOccupied() == false) {
									if(m.getJumpedSquares().size() == 0 ||
									   s == m.getJumpedSquares().get(m.getJumpedSquares().size()-1)) {  //This should change to find longest jump
											Square landing = board[a.getRow() + (a.getRow() - s.getRow())][a.getColumn() + (a.getColumn() - s.getColumn())];
											m.addJumpedSquare(a);
											m.addJumpedSquare(landing);
											JumpPath(landing, c, m);
									}
								}
							}
						}
					}	
				}
			}
		}
	}
	
	public void MakeMove(Move m) {
		m.end.placeChecker(m.start.remove());
		
		for(int i = 0; i < m.getJumpedSquares().size(); i++) {
			if(m.getJumpedSquares().get(i).getOccupyingChecker().getColor().equals("red")) {
				this.redCount--;
				Square jumped = m.getJumpedSquares().get(i);
				GUI.SquarePanel pan = this.g.getPanel(jumped.getRow(), jumped.getColumn());
				pan.removeChecker(pan.getGraphics());
			}
			if(m.getJumpedSquares().get(i).getOccupyingChecker().getColor().equals("black")) {
				this.blackCount--;
				Square jumped = m.getJumpedSquares().get(i);
				GUI.SquarePanel pan = this.g.getPanel(jumped.getRow(), jumped.getColumn());
				pan.removeChecker(pan.getGraphics());
			}
			m.getJumpedSquares().get(i).remove();
			i++;
		}
		
		if(m.getChecker().getMoveDirection() == 1 &&
		   m.getEnd().getRow() == 7) {
			m.getChecker().setKing();
		}
		if(m.getChecker().getMoveDirection() == -1 &&
				   m.getEnd().getRow() == 0) {
					m.getChecker().setKing();
				}
		
		GUI.SquarePanel start = this.g.getPanel(m.getStart().getRow(), m.getStart().getColumn());
		start.removeChecker(start.getGraphics());
		
		GUI.SquarePanel end = this.g.getPanel(m.getEnd().getRow(), m.getEnd().getColumn());
		end.addChecker(m.getEnd().getOccupyingChecker(), end.getGraphics());
	}
	
	public void FixCounts(Checker c) {
		if(c.getColor() == "black")
			this.blackCount--;
		else
			this.redCount--;
	}
	
	// Return The Point Total Of All Adjacent Empty Spaces
	public int AdjTotal(Square s) {
		int adjTotal = 0;
		ArrayList<Square> adjSquares = AdjSquares(s);
		for(Square n: adjSquares)
			if(n.isOccupied() == false &&
			   (n.getRow()-s.getRow()) * s.getOccupyingChecker().getMoveDirection() > 0)
				adjTotal += ((Square) n).getValue();
		return adjTotal;
	}
	
	// Create An Array List Of All Square Adjacent To A Square
	public ArrayList<Square> AdjSquares(Square s) {
		ArrayList<Square> adjSquares = new ArrayList<Square>();
		if(s.getRow()-1 >= 0 && s.getColumn()-1 >= 0)
			adjSquares.add(board[s.getRow()-1][s.getColumn()-1]);
		if(s.getRow()-1 >= 0 && s.getColumn()+1 <= 7)
			adjSquares.add(board[s.getRow()-1][s.getColumn()+1]);
		if(s.getRow()+1 <= 7 && s.getColumn()-1 >= 0)
			adjSquares.add(board[s.getRow()+1][s.getColumn()-1]);
		if(s.getRow()+1 <= 7 && s.getColumn()+1 <= 7)
			adjSquares.add(board[s.getRow()+1][s.getColumn()+1]);
		return adjSquares;
	}
	
	// Print Current Board Configuration
	public void print() {
		for(int i = 0; i < 8; i++) {
			System.out.print("| ");
			for(int j = 0; j < 8; j++) {
				System.out.print(board[i][j].getPosition());
				if(board[i][j].isOccupied()) {
					if(board[i][j].getOccupyingChecker().getColor() == "red")
						if(board[i][j].getOccupyingChecker().isKing())
							System.out.print("(R)");
						else
							System.out.print("(r)");
					else
						if(board[i][j].getOccupyingChecker().isKing())
							System.out.print("(B)");
						else
							System.out.print("(b)");
				} else
					System.out.print(" ");
				System.out.print("\t |");
			}
			System.out.println();
		}
	}
}

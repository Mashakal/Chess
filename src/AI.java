import java.util.ArrayList;

/**
 * Contains a semi-intelligent computer player that will evaluate all available
 * and valid moves and choose the one that takes the highest rank of the 
 * opponents piece.  Considers a pawn converting into a queen as 3rd highest
 * rank, behind capturing the player's king or queen.
 * @author Alex Neuenkirk
 *
 */
public class AI {
	public Chess game;
	public ArrayList<ValidMove> moves = new ArrayList<ValidMove>();
	public ArrayList<ValidMove> highest_moves = new ArrayList<ValidMove>();
	private int NEWQUEENSCORE = 5;
	
	public AI(Chess chessgameobject){
		game = chessgameobject;
	}
	
	/**
	 * Calls get_moves and get_highest_moves and chooses a random move of the highest score value.
	 */
	public void move(){
		// get all available moves
		get_moves();
		// get all the highest scored moves within the available moves
		get_highest_moves();
		
		// choose a random one from the highest scored available
		int randex = (int)(Math.random() * highest_moves.size());
		ValidMove choice = highest_moves.get(randex);
		// if the score is equal to NEWQUEENSCORE constant
		if(choice.score == NEWQUEENSCORE){
			// move the pawn to create a new queen
			game.positions[choice.newR][choice.newC] = new Queen(game,2);
		// otherwise execute the move chosen
		}else{
			game.positions[choice.newR][choice.newC] = game.positions[choice.oldR][choice.oldC];
		}
		// and remove the object from the previous tile location
		game.positions[choice.oldR][choice.oldC] = null;
	}
	
	/**
	 * Searches through the ArrayList of the valid moves and adds all of the moves that share
	 * the highest score value into a new ArrayList, "highest_moves"
	 */
	public void get_highest_moves(){
		highest_moves.clear();
		int high = 0;
		
		for(int i = 0; i < moves.size(); i++){
			if(moves.get(i).score > high){
				high = moves.get(i).score;
			}
		}
		for(int i = 0; i < moves.size(); i++){
			if(moves.get(i).score == high){
				highest_moves.add(moves.get(i));
			}
		}
	}
	
	/**
	 * Evaluates all moves on the board and adds any valid moves and their corresponding scores into an
	 * ArrayList, "moves".  If the move is to an empty tile, the score is set to 0.
	 */
	public void get_moves(){
		moves.clear();
		for(int row = 0; row < Chess.ROWS; row++){
			for(int col = 0; col < Chess.COLUMNS; col++){
				if(game.positions[row][col] != null && game.positions[row][col].player == 2){
					for(int new_row = 0; new_row < Chess.ROWS; new_row++){
						for(int new_col = 0; new_col < Chess.COLUMNS; new_col++){
							if(game.positions[new_row][new_col] == null){
								if(game.positions[row][col].canMove(row, col, new_row, new_col)){
									if(game.positions[row][col] instanceof Pawn && new_row == Chess.ROWS - 1){
										moves.add(new ValidMove(NEWQUEENSCORE, row, col, new_row, new_col));
									}else{
									moves.add(new ValidMove(0, row, col, new_row, new_col));
									}
								}
							}else if(game.positions[new_row][new_col].player == 1 && game.positions[row][col].canMove(row, col, new_row, new_col)){							
								moves.add(new ValidMove(game.positions[new_row][new_col].kill_value, row, col, new_row, new_col));
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * A "tuple-like" class that holds a moves score value, the starting position, and the destination position for 
	 * moves on the board.
	 * @author Alex Neuenkirk
	 *
	 */
	private static class ValidMove{
		
		public int score;
		public int oldR,oldC,newR,newC;
		
		private ValidMove(int value, int click_row, int click_col, int unclick_row, int unclick_col){
			score = value;
			oldR = click_row;
			oldC = click_col;
			newR = unclick_row;
			newC = unclick_col;
		}
	}
}

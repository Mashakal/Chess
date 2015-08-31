import javax.swing.ImageIcon;

/**
 * Class Knight contains the necessary information to 
 * represent and move a knight in chess.
 * @author Alex Neuenkirk
 *
 */
public class Knight extends Piece{
	
	public Knight(Chess chessgamemainobject, int p){
		
		super(chessgamemainobject, p);
		kill_value = 2;
		
		if(player == 1){
			image = new ImageIcon("src/wknight.gif");
		}else{
			image = new ImageIcon("src/bknight.gif");
		}
	}

	@Override
	public boolean canMove(int click_row, int click_col, int unclick_row, int unclick_col){
		int row_move = Math.abs(unclick_row - click_row);
		int col_move = Math.abs(unclick_col - click_col);

		// if the absolute values of the moves do not add to three
		if(row_move + col_move != 3){
			// deny the move, it is invalid
			return false;
		}
		// if we are not moving both vertically and horizontally
		if(row_move == 0 || col_move == 0){
			// deny the move, it is invalid
			return false;
		}
		// if the destination tile is occupied
		if(game.positions[unclick_row][unclick_col] != null){
			// if it is occupied by a piece of the same color as the knight
			if(game.positions[unclick_row][unclick_col].player == player){
				// deny the move, it is invalid
				return false;
			}
		}
		// otherwise, the move is valid
		return true;
	}  // end canMove

} // end class Knight

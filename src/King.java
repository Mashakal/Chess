import javax.swing.ImageIcon;

/**
 * Class King contains the necessary information to 
 * represent and move a King in chess.  Castling, check, and
 * check-mate are not yet implemented as of 04/08/2015.
 * Game does not end when king is captured as of 04/08/2015.
 * @author Alex Neuenkirk
 *
 */
public class King extends Piece{
	
	public King(Chess chessgamemainobject, int p){
		
		super(chessgamemainobject, p);
		kill_value = 7;
		
		if(player == 1){
			image = new ImageIcon("src/wking.gif");
		}else{
			image = new ImageIcon("src/bking.gif");
		}
	}

	@Override
	public boolean canMove(int click_row, int click_col, int unclick_row, int unclick_col){
		int row_move = Math.abs(unclick_row - click_row);
		int col_move = Math.abs(unclick_col - click_col);
		
		// if the move is more than one row and/or more than one column
		if(row_move > 1 || col_move > 1){
			// deny, it is invalid
			return false;
		}
		// Or if there is no move
		if(row_move != 1 && col_move != 1){
			return false;
		}
		// if the destination tile is occupied
		if(game.positions[unclick_row][unclick_col] != null){
			// if the occupant is a piece of the same color as the king
			if(game.positions[unclick_row][unclick_col].player == player){
				// if it is not the king's first move, castling is not allowed
				return false;
			}
		}
		// Otherwise, the move is valid
		return true;
	}

}

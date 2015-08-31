import javax.swing.ImageIcon;

/**
 * Class Bishop contains the necessary information to 
 * represent and move a bishop in chess.
 * @author Alex Neuenkirk
 *
 */
public class Bishop extends Piece {
	
	public Bishop(Chess chessgameobject, int p) {
		super(chessgameobject, p);
		kill_value = 3;
		
		if(player == 1){
			image = new ImageIcon("src/wbishop.gif");
		}else{
			image = new ImageIcon("src/bbishop.gif");
		}
	}

	@Override
	public boolean canMove(int click_row, int click_col, int unclick_row, int unclick_col) {
		int row_move = Math.abs(unclick_row - click_row);
		int col_move = Math.abs(unclick_col - click_col);
		
		// Check if the move is a diagonal in a straight line
		if(row_move != col_move){
			// if not, deny the move
			return false;
		}
		
		// Check if the path between the starting tile and the destination tile is clear
		if(!isClear(click_row, click_col, unclick_row, unclick_col)){
			// if it is blocked by a piece, deny the move
			return false;
		}
		
		// Check for an occupant in the destination tile
		if(game.positions[unclick_row][unclick_col] != null){
			// If there is an occupant, check to see if it is a piece of the same color as the bishop
			if(game.positions[unclick_row][unclick_col].player == player)
				// if so, deny the move
				return false;
		}
		// Otherwise the move is valid
		return true;
	}
	
}

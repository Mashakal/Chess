import javax.swing.ImageIcon;

/**
 * Class Rook contains the necessary information to 
 * represent and move a Rook in chess.
 * @author Alex Neuenkirk
 *
 */
public class Rook extends Piece{
	
	public Rook(Chess chessgamemainobject, int p){
		
		super(chessgamemainobject, p);
		kill_value = 4;
		
		if(player == 1){
			image = new ImageIcon("src/wrook.gif");
		}else{
			image = new ImageIcon("src/brook.gif");
		}
	}

	@Override
	public boolean canMove(int click_row, int click_col, int unclick_row, int unclick_col){
		int row_move = Math.abs(unclick_row - click_row);
		int col_move = Math.abs(unclick_col - click_col);
		
		// Make sure that we are moving only horizontally or vertically, not diagonally or not moving
		if(row_move == 0 && col_move == 0){
			return false;
		}
		if(row_move != 0 && col_move != 0){
			// if not, deny the move
			return false;
		}
		// Check if the path between the starting tile and the destination tile is clear
		if(!isClear(click_row, click_col, unclick_row, unclick_col))
			// if it is blocked by a piece, deny the move
			return false;
		// Check if the destination cell is occupied
		if(game.positions[unclick_row][unclick_col] != null){
			// If so, make sure that the occupied cell does not contain a piece of the same color as the rook
			if(game.positions[unclick_row][unclick_col].player == player)
				// If it does, deny the move TODO: implement castling
				return false;
		}
		//Otherwise, the move is valid
		return true;
	}
}

import javax.swing.ImageIcon;

/**
 * Class Queen contains the necessary information to 
 * represent and move a queen in chess.
 * @author Alex Neuenkirk
 *
 */
public class Queen extends Piece{
	
	public Queen(Chess chessgamemainobject, int p)
	{
		super(chessgamemainobject, p);
		kill_value = 6;
		
		if(player == 1){
			image = new ImageIcon("src/wqueen.gif");
		}else{
			image = new ImageIcon("src/bqueen.gif");
		}
	}

	@Override
	public boolean canMove(int click_row, int click_col, int unclick_row, int unclick_col){
		int row_move = Math.abs(unclick_row - click_row);
		int col_move = Math.abs(unclick_col - click_col);

		if(row_move == 0 && col_move == 0){
			return false;
		}
		// Check if we are moving diagonally
		if(row_move != 0 && col_move != 0){
			// if we are, make sure that we are moving in a straight line
			if(row_move != col_move){
				// if we are not, deny the move
				return false;
			}
		}
		// Check if the path between the starting tile and the destination tile is clear
		if(!isClear(click_row, click_col, unclick_row, unclick_col)){
			// if it is blocked by a piece, deny the move
			return false;
		}
		// Check if the destination tile is occupied
		if(game.positions[unclick_row][unclick_col] != null){
			// if so, make sure the occupant is not the same colored piece as the queen
			if(game.positions[unclick_row][unclick_col].player == player){
				// if so, deny the move
				return false;
			}
		}
		// Otherwise, the move is valid
		return true;
	} //end canMove
}	// end class Queen

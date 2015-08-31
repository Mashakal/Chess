import javax.swing.ImageIcon;

/**
 * Class Pawn contains the necessary information to 
 * represent and move a pawn in chess.
 * @author Alex Neuenkirk
 *
 */
public class Pawn extends Piece{

	private boolean first_move;
	private int row_inc;	// used to determine if moving forward two tiles is blocked for both colors

	public Pawn(Chess chessgamemainobject, int p)
	{
		super(chessgamemainobject, p);
		first_move = true;
		player = p;
		kill_value = 1;
		
		if(player == 1){
			image = new ImageIcon("src/wpawn.gif");
			row_inc = 1;
		}else{
			image = new ImageIcon("src/bpawn.gif");
			row_inc = -1;
		}
	}

	@Override
	public boolean canMove(int click_row, int click_col, int unclick_row, int unclick_col) {
		// Determine the number of row moves, a positive number indicates moving up on the board and a negative down on the board
		int row_move = Math.abs(click_row - unclick_row);  
		int col_move = click_col - unclick_col;

		// If it is player 2's turn
		if(player == 2){
			// do not allow them to move their pawn up
			if(click_row - unclick_row > 0){
				return false;
			}
		}
		// Make sure we are moving when the piece is clicked
		if(row_move == 0 && col_move == 0){
			// If not, we have not moved
			return false;
		}
		// Validate the first move
		if(first_move){
			// make sure the movement is valid in general
			if(row_move != 1 && row_move != 2){
				// if it is not, we cannot make the move
				return false;
			}
			// If we are moving in a straight line
			if(col_move == 0){
				// if we are moving to the next tile, make sure it is not taken already
				if(row_move == 1 && game.positions[unclick_row][unclick_col] != null){
					// if it is taken, we cannot make the move
					return false;
				// if we are moving two tiles ahead, make sure that tile is not taken already
				}else if(row_move == 2 && game.positions[unclick_row + row_inc][unclick_col] != null){
					// if it is taken, we cannot make the move
					return false;
				}
			// If we are moving diagonally
			}else{
				// Check to make sure that the move is not forward two
				if(col_move < -1 || col_move > 1){
					return false;
				}
				// Check to make sure the tile is not empty and that it does not contain one of its comrade pieces
				if(game.positions[unclick_row][unclick_col] == null || game.positions[unclick_row][unclick_col].player == player){
					// if there isn't, the move will not be allowed
					return false;
				}
			}
		}else{	// if it is not first_move
			// make sure that we are always moving one tile forward
			if(row_move != 1){
				// if it is not, deny the move
				return false;
			}
			// make sure that we are only moving forward one tile or diagonally one tile
			if(col_move > 1 || col_move < -1){
				return false;
			}
			// if we are moving forward, check to make sure the new tile is clear
			if(col_move == 0 && game.positions[unclick_row][unclick_col] != null){
				// if it is not, deny the move
				return false;
			}
			// if we are moving diagonally
			if(col_move == -1 || col_move == 1){
				// if the new tile is not occupied
				if(game.positions[unclick_row][unclick_col] == null){
					// deny the move
					return false;
				// if the new tile is occupied by a piece of the same player
				}else if(game.positions[unclick_row][unclick_col].player == player){
					// deny the move
					return false;
				}
			}
		}
		// otherwise, the move is valid
		if(first_move){
			// and it is no longer the first move
			first_move = false;
		}
		return true;
	} // end canMove
} // end class Pawn

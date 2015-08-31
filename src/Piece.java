import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 * The superclass for the different chess pieces.
 * @author Alex Neuenkirk
 *
 */
public abstract class Piece {
	public int player;
	public ImageIcon image;
	public int kill_value;
	public Chess game;
	
	private int X_BUFFER = 3;  // moves all the piece images to the right by this amount
	
	/**
	 * The constructor for the piece class.
	 * @param chessgameobject :the Chess object of which the piece is a part.
	 * @param p :an int, either 1 or 2, representing the player the piece belongs to.  1 is white, 2 is black.
	 */
	public Piece(Chess chessgameobject, int p){
		game = chessgameobject;
		player = p;
	}

	/**
	 * The code that draws the piece's image to the board.
	 * @param g :Graphics g
	 * @param y :The row position for which the piece should be drawn.
	 * @param x :The column position for which the piece should be drawn.
	 */
	public void drawPiece(Graphics g, int y, int x)
	{	
		g.drawImage(image.getImage(), x * Chess.TILE_SIZE + X_BUFFER, y * Chess.TILE_SIZE, null);
	}
	
	/**
	 * An abstract class that is called to determine if a piece's unique moves are valid.
	 * @param click_row :the row value of a piece that was clicked on
	 * @param click_col :the column value of a piece that was clicked on
	 * @param unclick_row :the destination row for the piece that was clicked on
	 * @param unclick_col :the destination column for the piece that was clicked on
	 * @return	returns a boolean value true if the piece's move is valid and false otherwise
	 */
	public abstract boolean canMove(int click_row, int click_col, int unclick_row, int unclick_col);
	
	/**
	 * MUST be called after a piece's unique moves are validated.  Checks whether the path between
	 * a source tile and a destination tile is clear.
	 * @param click_row :the row value of a piece that was clicked on
	 * @param click_col :the column value of a piece that was clicked on
	 * @param unclick_row :the destination row for the piece that was clicked on
	 * @param unclick_col :the destination column for the piece that was clicked on
	 * @return  true if so and false if a piece is found in the way
	 */
	public boolean isClear(int click_row, int click_col, int unclick_row, int unclick_col){
		int row_move = unclick_row - click_row;
		int col_move = unclick_col - click_col;
		
		// If we are moving down
		if(row_move > 0){
			//if we are also moving diagonally left
			if(col_move < 0){
				// check all tiles between the starting tile and the destination tile for pieces
				int c = click_col - 1;
				for(int r = click_row + 1; r < unclick_row; r++){
					if(game.positions[r][c] != null){
						return false;
					}
					c--;
				}
			}
			//Otherwise, if we are also moving diagonally right
			else if(col_move > 0){
				// check all tiles between the starting tile and the destination tile for pieces
				int c = click_col + 1;
				for(int r = click_row + 1; r < unclick_row; r++){
					if(game.positions[r][c] != null){
						return false;
					}
					c++; //NO, this is java!
				}
			}
			//Otherwise, if we are only moving down
			else if(col_move == 0){
				for(int r = click_row + 1; r < unclick_row; r++){
					if(game.positions[r][unclick_col] != null){
						return false;
					}
				}
			}
		}
		// If, instead, we are moving up
		else if(row_move < 0){
			// if we are also moving diagonally left
			if(col_move < 0){
				int c = click_col - 1;
				for(int r = click_row - 1; r > unclick_row; r--){
					if(game.positions[r][c] != null){
						return false;
					}
					c--;
				}
			}
			// Otherwise, if we are moving diagonally right
			else if(col_move > 0){
				int c = click_col + 1;
				for(int r = click_row - 1; r > unclick_row; r--){
					if(game.positions[r][c] != null){
						return false;
					}
					c++;
				}
			}
			// Otherwise, if we are moving up on the board
			else if(col_move == 0){
				for(int r = click_row - 1; r > unclick_row; r--){
					if(game.positions[r][unclick_col] != null){
						return false;
					}
				}
			}
		}
		// Otherwise, if we are moving either left or right
		else if(row_move == 0){
			// if we are moving left
			if(col_move < 0){
				for(int c = click_col - 1; c > unclick_col; c--){
					if(game.positions[unclick_row][c] != null){
						return false;
					}
				}
			}
			// Otherwise, if we are moving right
			else if(col_move > 0){
				for(int c = click_col + 1; c < unclick_col; c++){
					if(game.positions[unclick_row][c] != null){
						return false;
					}
				}
			}
		}
	// Otherwise, the way is clear!
	return true;
	}
}

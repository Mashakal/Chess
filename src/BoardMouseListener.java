import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Outlines the actions taken when the mouse is 
 * pressed and when released.  This is where the pieces
 * are moved and the computer opponent moves.
 * Implements the MouseListener superclass.
 * @author Alex
 *
 */
public class BoardMouseListener implements MouseListener{
	
	public Chess game;
	private int click_col, click_row;
	private int unclick_col, unclick_row;
	private boolean moved;
	public AI comp;
	
	/**
	 * The constructor for the BoardMouseListener class.
	 * @param chessgamemainobject :the chess object for which the mouse listens.
	 */
	public BoardMouseListener(Chess chessgamemainobject){
		game = chessgamemainobject;
		comp = new AI(game);
		moved = false;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	/**
	 * Determines the tile coordinates on which a player has clicked.
	 */
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		click_col = (int) Math.ceil((e.getX() - Chess.WIDTH_BUFFER/2) / Chess.TILE_SIZE);
		click_row = (int) Math.ceil((e.getY() - Chess.HEIGHT_BUFFER) / Chess.TILE_SIZE);
	}

	@Override
	/**
	 * Determines the tile coordinates on which a player has released the mouse-click.
	 * Checks to see if a valid move was made with the mouse click and updates the chess object
	 * positions array if so.  The computer opponent moves once a valid move is made by the player.
	 */
	public void mouseReleased(MouseEvent e) {
		// Get the tile location of the unclick
		unclick_col = (int) Math.ceil((e.getX() - Chess.WIDTH_BUFFER/2) / Chess.TILE_SIZE);
		unclick_row = (int) Math.ceil((e.getY() - Chess.HEIGHT_BUFFER) / Chess.TILE_SIZE);
		// If the player has not yet made a valid move
		if(!moved){
		// Check to see if the player has clicked on one of their pieces
			if(game.positions[click_row][click_col] != null && game.positions[click_row][click_col].player == 1){
				// If so, make sure that the tile they intend to move to is a valid move based on the piece type
				if(game.positions[click_row][click_col].canMove(click_row, click_col, unclick_row, unclick_col)){
					// if the piece is a pawn and it has reached the opposite end of the board
					if(game.positions[click_row][click_col] instanceof Pawn && unclick_row == 0){
						// make the pawn a queen
						game.positions[unclick_row][unclick_col] = new Queen(game, 1);
					// TODO:  Implement king capture trigger here
					}else{						
					// If the move was valid, update the new tile position with the piece object
						game.positions[unclick_row][unclick_col] = game.positions[click_row][click_col];
					}
					// and finally, remove the piece from its old location
					game.positions[click_row][click_col] = null;
					// set moved to true to break out of the loop and allow the computer to move
					moved = true;
				}
			}
		}
		// If it is the computer's turn
		if(moved){
			// Have the computer make a move
			comp.move();
			// Set moved to false, it is now the player's turn
			moved = false;
			// Update the rendering
			game.board.repaint();
		}
	}	// end mouseReleased
}

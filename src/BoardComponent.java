import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

/**
 * Draws the board background and the pieces onto the frame.
 * @author Alex Neuenkirk
 *
 */
public class BoardComponent extends JComponent {
	
	final Color BEIGE = new Color(233, 174, 95);
	final Color TAN = new Color(175, 112, 23);
	private Chess game;
	
	/**
	 * The constructor for the class, extending JComponent.
	 * @param chess :the Chess object for which the BoardComponent draws.
	 */
	public BoardComponent(Chess chess) {
		super();
		game = chess; 
	}

	/**
	 * The paintComponent for the BoardComponent class.
	 */
	public void paintComponent(Graphics g)
	{
		g.setColor(TAN);
		g.fillRect(0, 0, Chess.WIDTH, Chess.HEIGHT);
		
		g.setColor(BEIGE);
		
		// Draw the beige squares in the correct places
		for(int row = 0; row < Chess.ROWS; row++){
			for(int col = 0; col < Chess.COLUMNS; col++){
				if((row + col) % 2 == 0){
					g.fillRect(row * Chess.TILE_SIZE, col * Chess.TILE_SIZE, Chess.TILE_SIZE, Chess.TILE_SIZE);
				}
			}
		}
		// Draw the pieces on the board
		for(int row = 0; row < Chess.ROWS; row++){
			for(int col = 0; col < Chess.COLUMNS; col++){
				if(game.positions[row][col] != null){
					game.positions[row][col].drawPiece(g, row, col);
				}
			}
		}
	}
}

import javax.swing.JFrame;

/**
 * Creates a chess object to be played against an easy chess computer opponent.
 */
public class Chess {

	final static int PLAYERONE = 1;	// White
	final static int PLAYERTWO = 2;	// Black
	public final static int COLUMNS = 8, ROWS = 8;
	public final static int TILE_SIZE = 50;
	public final static int WIDTH_BUFFER = 6, HEIGHT_BUFFER = 28; //TODO:  IMPROVE THIS
	public final static int WIDTH = TILE_SIZE * COLUMNS + WIDTH_BUFFER;
	public final static int HEIGHT = TILE_SIZE * ROWS + HEIGHT_BUFFER;
	
	public BoardComponent board;
	public Piece[][] positions;
	public boolean blackInCheck, whiteInCheck;  // TODO:  IMPLEMENT CHECK
	public boolean blackKingCaptured, whiteKingCaptured;  //TODO:  IMPLEMENT GAME OVER
	
	/**
	 * The constructor for the chess class.
	 */
	public Chess()
	{
		// SET VARIABLES TO THEIR INITIAL VALUES
		blackInCheck = false;
		whiteInCheck = false;
		blackKingCaptured = false;
		whiteKingCaptured = false;
		
		// CREATE AN ARRAY THAT REPRESENTS THE TILES ON A CHESSBOARD
		positions = new Piece[COLUMNS][ROWS];
		
		// INITIALIZE CHESS PIECES IN THE APPROPRIATE POSITIONS
		// PAWNS
		for(int column = 0; column < COLUMNS; column++){
			positions[1][column] = new Pawn(this, PLAYERTWO);
			positions[ROWS - 2][column] = new Pawn(this, PLAYERONE);
		}
		// PLAYER TWO REMAINING PIECES
		positions[0][0] = new Rook(this, PLAYERTWO);
		positions[0][1] = new Knight(this, PLAYERTWO);
		positions[0][2] = new Bishop(this, PLAYERTWO);
		positions[0][3] = new Queen(this, PLAYERTWO);
		positions[0][4] = new King(this, PLAYERTWO);
		positions[0][5] = new Bishop(this, PLAYERTWO);
		positions[0][6] = new Knight(this, PLAYERTWO);
		positions[0][7] = new Rook(this, PLAYERTWO);
		// PLAYER ONE REMAINING PIECES
		positions[ROWS - 1][0] = new Rook(this, PLAYERONE);
		positions[ROWS - 1][1] = new Knight(this, PLAYERONE);
		positions[ROWS - 1][2] = new Bishop(this, PLAYERONE);
		positions[ROWS - 1][3] = new Queen(this, PLAYERONE);
		positions[ROWS - 1][4] = new King(this, PLAYERONE);
		positions[ROWS - 1][5] = new Bishop(this, PLAYERONE);
		positions[ROWS - 1][6] = new Knight(this, PLAYERONE);
		positions[ROWS - 1][7] = new Rook(this, PLAYERONE);
		
		// CREATE JCOMPONENT OBJECT
		board = new BoardComponent(this);
		
		// DISPLAY VARIABLES
		JFrame frame = new JFrame();
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		frame.add(board);
		frame.addMouseListener(new BoardMouseListener(this));
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setTitle("THE BEST CHESS GAME YOU'VE EVER SEEN, 4REAL");
	}
	
	/**
	 * Main class for chess, holds the instantiation for the chess object only.
	 */
	public static void main(String[] args)
	{
		new Chess();
	}
	
}	// end class Chess

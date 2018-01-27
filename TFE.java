
import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


//NOTE: Applet parameters should be set to a width of 575 and height of 680

public class TFE extends Applet implements KeyListener, MouseListener {
	
	//creates an empty 4 by 4 array
	private int[][] board = new int[4][4];
	private int[][] tempBoard = new int[4][4];
	//instance variables
	private int highNum = 0;
	private boolean gameOver = false;
	private int moves = 0;
	private boolean quitConfirmation = false;
	private boolean newBoard = true;
	private boolean boardFull = false;
	private boolean noMoves = false;
	private boolean validMove = true;
	
	//initializes game (acts as a "main" method)
	public void init() {
		setSize(575,680);
		//fills board array with all 0s which are essentially null values
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board.length; col++) {
				board[row][col] = 0;
			}
		}
		addKeyListener(this);
		addMouseListener(this);
	}
	//paints the Applet aka all the visual components of the game
	public void paint(Graphics g) {
		
		//creates a 2d graphics object so that later, the width of the border on the rectangles will be appropriately drawn
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setStroke(new java.awt.BasicStroke(10));
		
		g.clearRect(0, 0, board.length, board[0].length);
		
		//If board is empty, adds two new tiles
		newBoard = true;
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board.length; col++) {
				if (board[row][col] != 0) {
					newBoard = false;}}}
		if (newBoard == true) {
			newTile();
			newTile();}
		
		//If the game is over it gives options to replay or quit and gives score
		if (gameOver == true && quitConfirmation == false) {
			//draws game over slide
			g.setFont (new Font ("Courier New", 1, 40));
			g.drawString("GAME OVER!", 160, 100);
			g.setFont (new Font ("Courier New", 1, 25));
			g.drawString("Highest Number: " + String.valueOf(highNum), 147, 185);
			g.drawString("Number of moves: " + String.valueOf(moves), 145, 250);
			g.setColor(new Color(0xa6a6a6));
			g.setFont (new Font ("Courier New", 1, 25));
			g.drawRect(25, 540, 200, 100);
			g.fillRect(25, 540, 200, 100);
			g.drawRect(275, 540, 200, 100);
			g.fillRect(275, 540, 200, 100);
			g.setColor(new Color(0xFFFFF0));
			g.setFont (new Font ("Courier New", 1, 40));
			g.drawString("QUIT", 79, 602);
			g.drawString("RESTART", 293, 602);}
		
		//if the player asks to quit, this asks if they are sure
		else if (quitConfirmation == true) {
			g.setFont (new Font ("Courier New", 1, 20));
			g.clearRect(0, 0, board.length, board[0].length);
			g.drawString("ARE YOU SURE YOU WANT TO QUIT", 95, 210);
			g.drawString("YOUR SCORES WILL BE LOST", 140, 260);
			g.setColor(new Color(0xa6a6a6));
			g.setFont (new Font ("Courier New", 1, 25));
			g.drawRect(65, 280, 200, 100);
			g.fillRect(65, 280, 200, 100);
			g.drawRect(315, 280, 200, 100);
			g.fillRect(315, 280, 200, 100);
			g.setColor(new Color(0xFFFFF0));
			g.setFont (new Font ("Courier New", 1, 40));
			g.drawString("YES", 129, 342);
			g.drawString("NO", 395, 342);
			}
		
		//Draws board
		else {
			for (int row = 0; row < board.length; row++) {
				for (int col = 0; col < board[0].length; col++) {
					//draws the tiles and colors them appropriately according to their values
					if (board[row][col] == 2) {
						g.setColor(new Color(0xFFFFF0));
						g.drawRect(row * 125, col * 125, 125, 125);
						g.fillRect(row * 125, col * 125, 125, 125);}
					else if (board[row][col] == 4) {
						g.setColor(new Color(0xfffacd));
						g.drawRect(row * 125, col * 125, 125, 125);
						g.fillRect(row * 125, col * 125, 125, 125);}
					else if (board[row][col] == 8) {
						g.setColor(new Color(0xffc8b3));
						g.drawRect(row * 125, col * 125, 125, 125);
						g.fillRect(row * 125, col * 125, 125, 125);}
					else if (board[row][col] == 16) {
						g.setColor(new Color(0xffb699));
						g.drawRect(row * 125, col * 125, 125, 125);
						g.fillRect(row * 125, col * 125, 125, 125);}
					else if (board[row][col] == 32) {
						g.setColor(new Color(0xff9166));
						g.drawRect(row * 125, col * 125, 125, 125);
						g.fillRect(row * 125, col * 125, 125, 125);}
					else if (board[row][col] == 64) {
						g.setColor(new Color(0xe64100));
						g.drawRect(row * 125, col * 125, 125, 125);
						g.fillRect(row * 125, col * 125, 125, 125);}
					else if (board[row][col] == 128) {
						g.setColor(new Color(0xfff4b3));
						g.drawRect(row * 125, col * 125, 125, 125);
						g.fillRect(row * 125, col * 125, 125, 125);}
					else if (board[row][col] == 256) {
						g.setColor(new Color(0xffe866));
						g.drawRect(row * 125, col * 125, 125, 125);
						g.fillRect(row * 125, col * 125, 125, 125);}
					else if (board[row][col] == 512) {
						g.setColor(new Color(0xffe033));
						g.drawRect(row * 125, col * 125, 125, 125);
						g.fillRect(row * 125, col * 125, 125, 125);}
					else if (board[row][col] == 1024) {
						g.setColor(new Color(0xffd700));
						g.drawRect(row * 125, col * 125, 125, 125);
						g.fillRect(row * 125, col * 125, 125, 125);}
					else if (board[row][col] == 2048) {
						g.setColor(new Color(0xe6c300));
						g.drawRect(row * 125, col * 125, 125, 125);
						g.fillRect(row * 125, col * 125, 125, 125);}
					else if (board[row][col]  >= 4096) {
						g.setColor(new Color(0xcc0000));
						g.drawRect(row * 125, col * 125, 125, 125);
						g.fillRect(row * 125, col * 125, 125, 125);}
					//draws the numbers on the tiles that are not 0 and colors/sizes them appropriately to look "right"
					if (board[row][col] != 0) {
						g.setFont (new Font ("Courier New", 1, 27));
						if (board[row][col] < 5) {
							g.setColor(new Color(0x778899));
							g.drawString(String.valueOf(board[row][col]), (row * 125) + (125 / 2) - 5, (col * 125) + (125 / 2) + 5);}
						else if (board[row][col] < 10) {
							g.setColor(new Color(0xFFFFF0));
							g.drawString(String.valueOf(board[row][col]), (row * 125) + (125 / 2) - 5, (col * 125) + (125 / 2) + 5);}
						else if (board[row][col] < 100){
							g.setColor(new Color(0xFFFFF0));
							g.drawString(String.valueOf(board[row][col]), (row * 125) + (125 / 2) - 13, (col * 125) + (125 / 2) + 5);}
						else if (board[row][col] < 2049){
							g.setColor(new Color(0x778899));
							g.drawString(String.valueOf(board[row][col]), (row * 125) + (125 / 2) - 23, (col * 125) + (125 / 2) + 5);}
						else if (board[row][col] < 10000){
							g.setColor(new Color(0xFFFFF0));
							g.drawString(String.valueOf(board[row][col]), (row * 125) + (125 / 2) - 30, (col * 125) + (125 / 2) + 5);}}
					//draws border around each tile
					g2.setColor(new Color(0xa6a6a6));
					g2.drawRect(row * 125, col * 125, 125, 125);}}
			//gives options to quit or restart during the game at bottom of board
			g.setColor(new Color(0xa6a6a6));
			g.setFont (new Font ("Courier New", 1, 25));
			g.drawRect(25, 540, 200, 100);
			g.fillRect(25, 540, 200, 100);
			g.drawRect(275, 540, 200, 100);
			g.fillRect(275, 540, 200, 100);
			g.drawString("NUMBER OF MOVES: " + String.valueOf(moves), 125, 675);
			g.setColor(new Color(0xFFFFF0));
			g.setFont (new Font ("Courier New", 1, 40));
			g.drawString("QUIT", 79, 602);
			g.drawString("RESTART", 293, 602);}
	}
	//method will take in what direction the user clicked and will make the appropriate update to the board
	public void UpdateBoard(String direction) {
		validMove = true;
		
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board.length; col++) {
				tempBoard[row][col] = board[row][col];}}
		if (gameOver == false) {
		if (direction == "right") {
			for (int row = 0; row < board.length; row++) {
				//moves all numbers to the right
				for (int col = board[0].length - 1; col > 0; col--) {
					if (board[col][row] == 0 && board[col-1][row] != 0) {
						board[col][row] = board[col-1][row];
						board[col-1][row] = 0;
						if (col < 3 && board[col+1][row] == 0) {
							board[col+1][row] = board[col][row];
							board[col][row] = 0;
							if (col < 2 && board[col+2][row] == 0) {
								board[col+2][row] = board[col+1][row];
								board[col+1][row] = 0;}}}}
				//combines and duplicates all necessary tiles
				for (int col = board[0].length - 1; col > 0; col--) {
					if (board[col][row] == board[col-1][row]) {
						board[col][row] = board[col][row]*2;
						board[col-1][row] = 0;
						//col = col - 2;
						}}
				//moves all numbers to the right
				for (int col = board[0].length - 1; col > 0; col--) {
					if (board[col][row] == 0 && board[col-1][row] != 0) {
						board[col][row] = board[col-1][row];
						board[col-1][row] = 0;
						if (col < 3 && board[col+1][row] == 0) {
							board[col+1][row] = board[col][row];
							board[col][row] = 0;if (col < 2 && board[col+2][row] == 0) {
								board[col+2][row] = board[col+1][row];
								board[col+1][row] = 0;}}}}}}
		
		
		if (direction == "left") {
			for (int row = 0; row <  board.length; row++) {
				//moves all numbers to the left
				for (int col = 0; col < board[0].length-1; col++) {
					if (board[col][row] == 0 && board[col+1][row] != 0) {
						board[col][row] = board[col+1][row];
						board[col+1][row] = 0;
						if (col > 0 && board[col-1][row] == 0) {
							board[col-1][row] = board[col][row];
							board[col][row] = 0;
							if (col > 1 && board[col-2][row] == 0) {
								board[col-2][row] = board[col-1][row];
								board[col-1][row] = 0;}}}}
				//combines and duplicates all necessary tiles
				for (int col = 0; col < board[0].length-1; col++) {
					if (board[col][row] == board[col+1][row]) {
						board[col][row] = board[col][row]*2;
						board[col+1][row] = 0;
						//col = col + 2;
						}}	
				//moves all numbers to the left
				for (int col = 0; col < board[0].length-1; col++) {
					if (board[col][row] == 0 && board[col+1][row] != 0) {
						board[col][row] = board[col+1][row];
						board[col+1][row] = 0;
						if (col > 0 && board[col-1][row] == 0) {
							board[col-1][row] = board[col][row];
							board[col][row] = 0;
							if (col > 1 && board[col-2][row] == 0) {
							board[col-2][row] = board[col-1][row];
							board[col-1][row] = 0;}}}}}}
		
		
		if (direction == "up") {
			for (int col = 0; col < board[0].length; col++) {
				//moves all numbers up
				 for (int row = 0; row < board.length-1; row++) {
					 if (board[col][row] == 0 && board[col][row+1] != 0) {
							board[col][row] = board[col][row+1];
							board[col][row+1] = 0;
							if (row > 0 && board[col][row-1] == 0) {
								board[col][row-1] = board[col][row];
								board[col][row] = 0;
								if (row > 1 && board[col][row-2] == 0) {
									board[col][row-2] = board[col][row-1];
									board[col][row-1] = 0;}}}}
				//combines and duplicates all necessary tiles
				 for (int row = 0; row < board.length-1; row++) {
						if (board[col][row] == board[col][row+1]) {
							board[col][row+1] = board[col][row]*2;
							board[col][row] = 0;
							//row = row + 2;
							}}
				//moves all numbers up
				 for (int row = 0; row < board.length-1; row++) {
					 if (board[col][row] == 0 && board[col][row+1] != 0) {
						 board[col][row] = board[col][row+1];
						 board[col][row+1] = 0;
						if (row > 0 && board[col][row-1] == 0) {
							board[col][row-1] = board[col][row];
							board[col][row] = 0;
							if (row > 1 && board[col][row-2] == 0) {
								board[col][row-2] = board[col][row-1];
								board[col][row-1] = 0;}}}}}}
		
		
		if (direction == "down") {
			for (int col = 0; col < board[0].length; col++) {
				//moves all numbers down
				 for (int row = board.length - 1; row > 0; row--) {
					 if (board[col][row] == 0 && board[col][row-1] != 0) {
							board[col][row] = board[col][row-1];
							board[col][row-1] = 0;
							if (row < 3 && board[col][row+1] == 0) {
								board[col][row+1] = board[col][row];
								board[col][row] = 0;
								if (row < 2 && board[col][row+2] == 0) {
									board[col][row+2] = board[col][row+1];
									board[col][row+1] = 0;}}}}
				//combines and duplicates all necessary tiles
				 for (int row = board.length - 1; row > 0; row--) {
					 if (board[col][row] == board[col][row-1]) {
						board[col][row-1] = board[col][row]*2;
						board[col][row] = 0;
						//row = row - 2;
						}}
				//moves all numbers down
				 for (int row = board.length - 1; row > 0; row--) {
					 if (board[col][row] == 0 && board[col][row-1] != 0) {
							board[col][row] = board[col][row-1];
							board[col][row-1] = 0;
							if (row < 3 && board[col][row+1] == 0) {
								board[col][row+1] = board[col][row];
								board[col][row] = 0;
								if (row < 2 && board[col][row+2] == 0) {
									board[col][row+2] = board[col][row+1];
									board[col][row+1] = 0;}}}}}}
		
		
		//Updates running variable "highNum", finding the max value on board
		for (int row = 0; row <  board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				if (board[row][col] > highNum)
					highNum = board[row][col];}}
		}
		//Checks to see if the board changed
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board.length; col++) {
				if (tempBoard[row][col] != board[row][col])
					validMove = false;}}
		
		//if board changed, adds 1 to "moves" and adds a new tile
		if (validMove == false) {
			moves++;
			newTile();
		}
		
	}
	//checks if the game is over
	public void gameOver() {
		//checks if board is full
		boardFull = true;
		for (int row = 0; row <  board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				if (board[row][col] == 0) {
					boardFull = false;}}}
						
		//if board if full, checks if there are any possible moves
		noMoves = true;
		if (boardFull == true) {
			for (int row = 0; row <  board.length - 1; row++) {
				for (int col = 0; col < board[0].length - 1; col++) {
					if (board[row][col] == board[row+1][col]) {
						noMoves = false;}
					else if (board[row][col] == board[row][col+1]) {
						noMoves = false;}}}}
						
		if (boardFull == true && noMoves == true) {
			gameOver = true;}
	}
	//adds either a 2 or 4 to the board 
	public void newTile() {
		//checks if board is full
		boardFull = true;
		for (int row = 0; row <  board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				if (board[row][col] == 0) {
					boardFull = false;}}}
						
		//if board if full, checks if there are any possible moves
		noMoves = true;
		if (boardFull == true) {
			for (int row = 0; row <  board.length - 1; row++) {
				for (int col = 0; col < board[0].length - 1; col++) {
					if (board[row][col] == board[row+1][col]) {
						noMoves = false;}
					else if (board[row][col] == board[row][col+1]) {
						noMoves = false;}}}}
						
		if (boardFull == true && noMoves == true) {
			gameOver = true;}
				
		else {
			//randomly chooses an index
			int randomIndexRow = 0 + (int)(Math.random() * board.length);
			int randomIndexCol = 0 + (int)(Math.random() * board[0].length);
			if (board[randomIndexRow][randomIndexCol] == 0) {
				//randomly chooses 2 or 4 (more likely 2)
				int random2Or4 = 0 + (int)(Math.random() * 4);
				if (random2Or4 == 0) 
					board[randomIndexRow][randomIndexCol] = 4;
				else 
					board[randomIndexRow][randomIndexCol] = 2;}
			else
				newTile();}
	}
	//takes in what key is pressed and accordingly makes a move
	//keys being used in this project are 4 arrow keys or the wasd keys (move), q(quit), r(restart), y(yes), and n(no)
	//q,r,y,and s are not necessary as there are buttons however, I have implemented them if the user perfers
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			gameOver();
			UpdateBoard("right");
			repaint();
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			gameOver();
			UpdateBoard("left");
			repaint();
		}
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			gameOver();
			UpdateBoard("up");
			repaint();
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			gameOver();
			UpdateBoard("down");
			repaint();
		}
		if (e.getKeyCode() == KeyEvent.VK_Q) {
			quitConfirmation = true;
			repaint();
		}
		if (e.getKeyCode() == KeyEvent.VK_R) {
			highNum = 0;
			gameOver = false;
			moves = 0;
			quitConfirmation = false;
			newBoard = true;
			boardFull = false;
			noMoves = false;
			validMove = true;
			for (int row = 0; row < board.length; row++) {
        			for (int col = 0; col  < board.length; col++) {
        				board[row][col] = 0;}}
			repaint();
		}
		if (e.getKeyCode() == KeyEvent.VK_Y) {
			System.exit(0);
		}
		if (e.getKeyCode() == KeyEvent.VK_N) {
			quitConfirmation = false;
			repaint();
		}
	}
	
	//takes in the x and y coordinate of location that is clicked and accordingly quits of restarts
	public void mouseClicked(MouseEvent e) {
		//variables x and y store the coordinate of what is clicked
		int x = e.getX();
		int y = e.getY();
		//if quit button is clicked
		if (x >= 25 && x <= 225 && y >= 540 && y <= 640) {
			quitConfirmation = true;
			repaint();
		}
		//if restart button is clicked
		if (x >= 275 && x <= 475 && y >= 540 && y <= 640) {
			highNum = 0;
			gameOver = false;
			moves = 0;
			quitConfirmation = false;
			newBoard = true;
			boardFull = false;
			noMoves = false;
			validMove = true;
			for (int row = 0; row < board.length; row++) {
        			for (int col = 0; col  < board.length; col++) {
        				board[row][col] = 0;}}
			repaint();
		}
		//if user confirms wanting to quit by pressing yes
		if (x >= 65 && x<= 265 && y >= 280 && y <= 380) {
			System.exit(0);
		}
		//if user says no to confirming quitting
		if (x >= 315 && x<= 515 && y >= 280 && y <= 380) {
			quitConfirmation = false;
			repaint();
		}
	}
	
	//unused methods necessary for compiling because the class implements KeyListner 
	public void keyTyped(KeyEvent e) {

	}
	public void keyReleased(KeyEvent e) {

	}
	public void mousePressed(MouseEvent e) {
		
	}
	public void mouseReleased(MouseEvent e) {
		
	}
	public void mouseEntered(MouseEvent e) {
		
	}
	public void mouseExited(MouseEvent e) {
		
	}

}

import java.util.Scanner;
import java.util.Random;
public class Battleship
{
	//COORDS IN 1A format
	public static char[][] playerBoard;
	public static char[][] computerBoard;
	public static void main(String[] args)
	{
		playerBoard = createBoard();
		computerBoard = createBoard();
		printBoards(playerBoard,computerBoard, true);
		Ship[] playerShips = initializePlayerBoard(playerBoard);
		Ship[] computerShips = initializeComputerBoard(computerBoard);
		boolean playerWon = false;
		boolean computerWon = false;
		int turns = 1;
		while(!playerWon && !computerWon)
		{
			playerWon = playerTurn(computerBoard, computerShips);
			//printBoards(playerBoard,computerBoard, true);
			computerWon = computerTurn(playerBoard, computerShips);
			printBoards(playerBoard,computerBoard, true);
			turns++;
		}
		printBoards(playerBoard,computerBoard, false);
		System.out.println("Game over! The "+ (playerWon?"Player":"Computer") + " won!");
		System.out.println("Game took "+turns+" turns.");
		

		
	}

	public static boolean playerTurn(char[][] enemyBoard, Ship[] enemyShips)
	{
		Scanner scan = new Scanner(System.in);
		Boolean valid = false;
		String coord;
		do
		{
			System.out.print("Please select a target: ");
			coord = scan.nextLine().toUpperCase();
			if(coord.matches("[0-9][A-J]")){valid=true;}
			if(coord.equals("Q")||coord.equals("QUIT")){System.exit(0);}
			if(!valid){System.out.println("Invalid placement, please try again.\n");}
		}while(!valid);
		boolean hit = fire(coord, enemyBoard);
		int[] yx = coordsToInt(coord);
		if(hit)
		{
			System.out.println("HIT!");
			enemyBoard[yx[0]][yx[1]] = 'X';
			for(Ship s : enemyShips)
			{
				if(s.contains(coord))
				{
					s.shoot(coord);
					if(s.sunk){System.out.println("You sunk my "+s.name+"!");break;}
				}
			}
			if(checkWin(enemyShips)){return true;}
		}
		else
		{
			//miss
			System.out.println("Miss!");
			enemyBoard[yx[0]][yx[1]] = 'o';
		}
		//System.out.println("Enter to Continue");
		//scan.nextLine();
		return false;
	}

	public static boolean computerTurn(char[][] enemyBoard, Ship[] enemyShips)
	{

		String coord = computerGuess(enemyBoard, enemyShips);
		boolean hit = fire(coord, enemyBoard);
		int[] yx = coordsToInt(coord);
		if(hit)
		{
			System.out.println("THE COMPUTER HIT! ("+coord+")");
			enemyBoard[yx[0]][yx[1]] = 'X';
			for(Ship s : enemyShips)
			{
				if(s.contains(coord))
				{
					s.shoot(coord);
					if(s.sunk){System.out.println("The opponent sunk your "+s.name+"!");break;}
				}
			}
			if(checkWin(enemyShips)){return true;}
		}
		else
		{
			//miss
			System.out.println("The computer missed! ("+coord+")");
			enemyBoard[yx[0]][yx[1]] = 'o';
		}
		return false;
	}

	public static String computerGuess(char[][] enemyBoard, Ship[] enemyShips)
	{
		//implement better AI here
		Random r = new Random();
		int[] guess = new int[2];
		guess[0] = r.nextInt(10);
		guess[1] = r.nextInt(10);
		return intToCoords(guess);
	}

	public static boolean fire(String coord, char[][] targetBoard)
	{
		//return if the shot will hit or miss
		int[] yx = coordsToInt(coord);
		return (targetBoard[yx[0]][yx[1]] == '#');
	}

	public static boolean checkWin(Ship[] ships)
	{
		for(Ship s : ships)
		{
			if(!s.sunk){return false;}
		}
		return true;
	}

	public static void printBoards(char[][] player, char[][] computer, boolean hideEnemy)
	{
		//prints the two boards as the player sees them
		//1-10 vert A-J horiz
		//. = unknown/empty square
		//# = unhit player ship
		//X = hit ship
		//O = missed shot
		System.out.println("  A B C D E F G H I J  |  A B C D E F G H I J");
		for(int i = 0; i<10;i++)
		{
			System.out.print((i)+" ");
			for(int j = 0; j<10; j++)
			{
				System.out.print(player[i][j]+" ");
			}
			System.out.print(" |  ");
			for(int j = 0; j<10; j++)
			{
				if(computer[i][j]=='#' && hideEnemy){System.out.print('.'+" ");}
				else{System.out.print(computer[i][j]+" ");}
			}
			System.out.print((i)+"\n");
		}
	}

	public static Ship[] initializePlayerBoard(char[][] board)
	{
		Ship[] playerShips = new Ship[7];

		playerShips[0] = promptPlacement(board, "Aircraft Carrier",5);
		placeOnBoard(board,playerShips[0]);
		printBoards(board,computerBoard, true);

		playerShips[1] = promptPlacement(board, "Battleship",4);
		placeOnBoard(board,playerShips[1]);
		printBoards(board,computerBoard, true);

		playerShips[2] = promptPlacement(board, "Cruiser",3);
		placeOnBoard(board,playerShips[2]);
		printBoards(board,computerBoard, true);

		playerShips[3] = promptPlacement(board, "Destroyer",2);
		placeOnBoard(board,playerShips[3]);
		printBoards(board,computerBoard, true);
		
		playerShips[4] = promptPlacement(board, "Destroyer",2);
		placeOnBoard(board,playerShips[4]);
		printBoards(board,computerBoard, true);
		
		playerShips[5] = promptPlacement(board, "Submarine",1);
		placeOnBoard(board,playerShips[5]);
		printBoards(board,computerBoard, true);

		playerShips[6] = promptPlacement(board, "Submarine",1);
		placeOnBoard(board,playerShips[6]);
		printBoards(board,computerBoard, true);

		return playerShips;

	}

	public static Ship[] initializeComputerBoard(char[][] board)
	{
		Ship[] computerShips = new Ship[7];

		computerShips[0] = genPlacement(board, "Aircraft Carrier",5);
		placeOnBoard(board, computerShips[0]);

		computerShips[1] = genPlacement(board, "Battleship",4);
		placeOnBoard(board, computerShips[1]);

		computerShips[2] = genPlacement(board, "Cruiser",3);
		placeOnBoard(board, computerShips[2]);

		computerShips[3] = genPlacement(board, "Destroyer",2);
		placeOnBoard(board, computerShips[3]);

		computerShips[4] = genPlacement(board, "Destroyer",2);
		placeOnBoard(board, computerShips[4]);

		computerShips[5] = genPlacement(board, "Submarine",1);
		placeOnBoard(board, computerShips[5]);

		computerShips[6] = genPlacement(board, "Submarine",1);
		placeOnBoard(board, computerShips[6]);

		return computerShips;
	}

	public static Ship genPlacement(char[][] board, String name, int size)
	{
		Random r = new Random();
		boolean valid = false;
		int[] coord = new int[2];
		int direction;
		do
		{
			coord[0] = r.nextInt(10);
			coord[1] = r.nextInt(10);
			direction = r.nextInt(4);
			valid = validPlacement(board, intToCoords(coord), direction, size);
		}while(!valid);
		String[] coords = new String[size];
		for(int i=0; i<size;i++)
		{
			coords[i] = intToCoords(coord);
			if(direction==0){coord[0]--;}
			else if(direction==1){coord[1]++;}
			else if(direction==2){coord[0]++;}
			else if(direction==3){coord[1]--;}
		}
		Ship ship = new Ship(coords, name);
		return ship;
	}

	public static Ship promptPlacement(char[][] board, String name, int size)
	{
		Scanner scan = new Scanner(System.in);
		Boolean valid = false;
		String coord;
		int direction;
		do
		{
			System.out.print("Please place your "+name+" (size: "+size+"): ");
			coord = scan.nextLine().toUpperCase();
			if(size == 1){direction = 0;}
			else
			{
				System.out.print("Please choose a direction - 0-Up 1-Right 2-Down 3-Left:");
				direction = scan.nextInt();
				scan.nextLine(); //consume newline on int input
			}
			if(coord.matches("[0-9][A-J]") && direction >= 0 && direction <4)
			{
				valid = validPlacement(board, coord, direction, size);
			}
			if(!valid){System.out.println("Invalid placement, please try again.\n");}
		}while(!valid);
		String[] coords = new String[size];
		int[] ints = coordsToInt(coord);
		for(int i=0; i<size;i++)
		{
			coords[i] = intToCoords(ints);
			if(direction==0){ints[0]--;}
			else if(direction==1){ints[1]++;}
			else if(direction==2){ints[0]++;}
			else if(direction==3){ints[1]--;}
		}
		Ship ship = new Ship(coords, name);
		return ship;
	}

	public static boolean validPlacement(char[][] board, String startCoord, int direction, int size)
	{
		//direction : 0=up, 1=right, 2=down, 3=left
		int[] coord = coordsToInt(startCoord);
		for(int i=0; i<size;i++)
		{
			if(coord[0]<0 || coord[1]<0){return false;}
			if(coord[0]>9 || coord[1]>9){return false;}
			if(board[coord[0]][coord[1]] != '.'){return false;}

			if(direction==0){coord[0]--;}
			else if(direction==1){coord[1]++;}
			else if(direction==2){coord[0]++;}
			else if(direction==3){coord[1]--;}
			else{return false;}
		}
		return true;
	}

	public static void placeOnBoard(char[][] board, Ship ship)
	{
		//Assume places are already validated
		for(String coord : ship.coords)
		{
			int[] yx = coordsToInt(coord);
			board[yx[0]][yx[1]] = '#';
		}
	}

	public static char[][] createBoard()
	{
		char[][] board = new char[10][10];
		board[0] = new char[]{'.','.','.','.','.','.','.','.','.','.'};
		board[1] = new char[]{'.','.','.','.','.','.','.','.','.','.'};
		board[2] = new char[]{'.','.','.','.','.','.','.','.','.','.'};
		board[3] = new char[]{'.','.','.','.','.','.','.','.','.','.'};
		board[4] = new char[]{'.','.','.','.','.','.','.','.','.','.'};
		board[5] = new char[]{'.','.','.','.','.','.','.','.','.','.'};
		board[6] = new char[]{'.','.','.','.','.','.','.','.','.','.'};
		board[7] = new char[]{'.','.','.','.','.','.','.','.','.','.'};
		board[8] = new char[]{'.','.','.','.','.','.','.','.','.','.'};
		board[9] = new char[]{'.','.','.','.','.','.','.','.','.','.'};
		return board;
	}

	public static int[] coordsToInt(String coord)
	{
		int[] ints = new int[2];
		char x = coord.toUpperCase().charAt(1);
		char y = coord.charAt(0);
		ints[0] = Integer.parseInt(y+"");
		ints[1] = (int)x - 65;
		return ints;
	}

	public static String intToCoords(int[] ints)
	{
		String coord = "";
		String x = ((char)(ints[1]+65)+"").toUpperCase();
		String y = ""+ints[0];
		coord = y+x;
		return coord;
	}
}
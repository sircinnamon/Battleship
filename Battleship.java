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
		//TEST COORDS
		/*
		System.out.println(intToCoords(new int[]{1,1}));
		System.out.println(coordsToInt("A5")[0] + "," + coordsToInt("A5")[1]);
		*/
		//TEST PLACING
		/*
		System.out.println(validPlacement(playerBoard, "0A",0,3));
		System.out.println(validPlacement(playerBoard, "0A",2,3));
		Ship testShip = new Ship(new String[]{"0A","1A","2A"});
		placeOnBoard(playerBoard,testShip);
		printBoards(playerBoard,computerBoard, true);
		System.out.println(validPlacement(playerBoard, "0A",1,3));
		*/
		//TEST HIDE ENEMY
		/*
		Ship testShip2 = new Ship(new String[]{"0A","1A","2A"});
		placeOnBoard(computerBoard,testShip2);
		printBoards(playerBoard,computerBoard, true);
		printBoards(playerBoard,computerBoard, false);
		*/
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
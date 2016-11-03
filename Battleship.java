public class Battleship
{
	public static char[][] playerBoard;
	public static char[][] computerBoard;
	public static void main(String[] args)
	{
		playerBoard = createBoard();
		computerBoard = createBoard();
		printBoards(playerBoard,computerBoard);
	}

	public static void printBoards(char[][] player, char[][] computer)
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
				System.out.print(computer[i][j]+" ");
			}
			System.out.print((i)+"\n");
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
}
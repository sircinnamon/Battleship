import java.util.Random;
import java.util.ArrayList;
public class BattleshipAI
{
	private int difficulty = 1; //1, 2 or 3 = Easy, Medium, Hard
	public BattleshipAI(int difficulty)
	{
		this.difficulty = difficulty;
	}

	public String bestGuess(char[][] enemyBoard, Ship[] enemyShips)
	{
		return randomGuess(enemyBoard,enemyShips);
	}

	public String randomGuess(char[][] enemyBoard, Ship[] enemyShips)
	{
		//implement better AI here
		Random r = new Random();
		int[] guess = new int[2];
		guess[0] = r.nextInt(10);
		guess[1] = r.nextInt(10);
		if(enemyBoard[guess[0]][guess[1]] == 'X' || enemyBoard[guess[0]][guess[1]] == 'o'){return randomGuess(enemyBoard,enemyShips);}
		return Battleship.intToCoords(guess);
	}

	public String scoredGuess(char[][] enemyBoard, Ship[] enemyShips)
	{
		int[][] score = new int[enemyBoard.length][enemyBoard.length];
		ArrayList<String> deadSpaces= new ArrayList<String>();
		return "0A";
	}

	public String shipGuess(char[][] enemyBoard, Ship[] enemyShips)
	{
		//This guesser goes after ships it thinks it has found
		ArrayList<String> shipSpaces= new ArrayList<String>();
		char x = 'A';
		for(int i = 0; i<enemyBoard.length;i++)
		{
			for(int j = 0; j<enemyBoard.length;j++)
			{
				//Add all squares of hit ships
				if(enemyBoard[i][j]=='X'){shipSpaces.add(""+i+(char)(x+j));}
			}
		}
		for(Ship s : enemyShips)
		{
			//remove any sunk ship's squares
			if(s.sunk)
			{
				for(String coord : s.coords)
				{
					for(int i = 0; i<shipSpaces.size();i++)
					{
						if(shipSpaces.get(i).equals(coord)){shipSpaces.remove(i);}
					}
				}
			}
		}

		//System.out.print("Active Ship Spaces: ");
		//printList(shipSpaces);
		//Ship spaces should now contain any unsunk hit squares
		ArrayList<String> targets = new ArrayList<String>();
		ArrayList<String> primeTargets = new ArrayList<String>();
		for(String square : shipSpaces)
		{
			int[] coord = Battleship.coordsToInt(square);
			if(coord[0]!= 9 && (enemyBoard[coord[0]+1][coord[1]] == '.' || enemyBoard[coord[0]+1][coord[1]] == '#')){targets.add(""+(coord[0]+1)+(char)(x+coord[1]));}
			if(coord[1]!= 9 && (enemyBoard[coord[0]][coord[1]+1] == '.' || enemyBoard[coord[0]][coord[1]+1] == '#')){targets.add(""+coord[0]+(char)(x+coord[1]+1));}
			if(coord[0]!= 0 && (enemyBoard[coord[0]-1][coord[1]] == '.' || enemyBoard[coord[0]-1][coord[1]] == '#')){targets.add(""+(coord[0]-1)+(char)(x+coord[1]));}
			if(coord[1]!= 0 && (enemyBoard[coord[0]][coord[1]-1] == '.' || enemyBoard[coord[0]][coord[1]-1] == '#')){targets.add(""+coord[0]+(char)(x+coord[1]-1));}

			int line = 1;
			while(coord[0]+line<= 9 && enemyBoard[coord[0]+line][coord[1]] == 'X'){line++;}
			if(line>1 && coord[0]+line <=9 && enemyBoard[coord[0]+line][coord[1]] != 'o'){primeTargets.add(""+(coord[0]+line)+(char)(x+coord[1]));}
			line = 1;
			while(coord[1]+line<= 9 && enemyBoard[coord[0]][coord[1]+line] == 'X'){line++;}
			if(line>1 && coord[1]+line <=9 && enemyBoard[coord[0]][coord[1]+line] != 'o'){primeTargets.add(""+(coord[0])+(char)(x+coord[1]+line));}
			line = 1;
			while(coord[0]-line>= 0 && enemyBoard[coord[0]-line][coord[1]] == 'X'){line++;}
			if(line>1 && coord[0]-line >=0 && enemyBoard[coord[0]-line][coord[1]] != 'o'){primeTargets.add(""+(coord[0]-line)+(char)(x+coord[1]));}
			line = 1;
			while(coord[1]-line>= 0 && enemyBoard[coord[0]][coord[1]-line] == 'X'){line++;}
			if(line>1 && coord[1]-line >=0 && enemyBoard[coord[0]][coord[1]-line] != 'o'){primeTargets.add(""+(coord[0])+(char)(x+coord[1]-line));}
		}
		/*
		System.out.print("Targets: ");
		printList(targets);
		System.out.print("Prime Targets: ");
		printList(primeTargets);
		*/
		Random r = new Random();
		if(primeTargets.size() != 0){return primeTargets.get(r.nextInt(primeTargets.size()));}
		if(targets.size() != 0){return targets.get(r.nextInt(primeTargets.size());}
		return randomGuess(enemyBoard,enemyShips);
	}	

	public void printList(ArrayList<String> list)
	{
		System.out.print("[");
		for(String s: list)
		{
			System.out.print(s+", ");
		}
		System.out.println("]");
	}
}
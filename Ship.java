public class Ship
{
	public int size;
	public boolean sunk;
	public String[] coords;
	public boolean[] hits;
	public String name;

	public Ship(String[] coords, String name)
	{
		size = coords.length;
		this.coords = coords;
		hits = new boolean[size];
		this.name = name;
		for(int i=0; i<size;i++){hits[i]=false;}
	}

	public String getStart()
	{
		return coords[0];
	}

	public String getEnd()
	{
		return coords[size-1];
	}

	public boolean contains(String coord)
	{
		for(String s : coords)
		{
			if(s.equals(coord)){return true;}
		}
		return false;
	}

	public void shoot(String coord)
	{
		//spot coord has been hit
		for(int i = 0; i<size;i++)
		{
			if(coords[i].equals(coord)){hits[i]=true;}
		}
		checkSunk();
	}

	public boolean checkSunk()
	{
		for(boolean b : hits)
		{
			if(!b){sunk = false; return false;}
		}
		sunk = true;
		return true;
	}
}
public class Ship
{
	public int size;
	public boolean sunk;
	public String[] coords;
	public boolean[] hits;
	public String name;

	public Ship(String[] coords)
	{
		size = coords.length;
		this.coords = coords;
		hits = new boolean[size];
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
}
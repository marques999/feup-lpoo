package lpoo;

abstract class Backpack
{
	String[] items;
	
	private int maxCapacity;
	private int currentCapacity;
		
	public Backpack()
	{
		this(10);
	}
	
	public Backpack(int maxSize)
	{
		this.maxCapacity = maxSize;
		this.currentCapacity = 0;
		this.items = new String[maxCapacity];
	}
	
	public final String[] getItems()
	{
		return this.items;
	}
	
	public void setItems(String[] items)
	{
		this.items = items;
	}
	
	public boolean addItem(String item)
	{
		if (currentCapacity < maxCapacity)
		{
			items[currentCapacity] = item;
			currentCapacity++;
			
			return true;
		}
		
		return false;
	}
	
	public boolean removeItem(String item)
	{
		if (currentCapacity != 0)
		{
			currentCapacity--;
			items[currentCapacity] = null;
			
			return true;
		}
		
		return false;
	}
	
	public boolean findItem(String item)
	{
		if (currentCapacity != 0)
		{
			for (String it : items)
			{
				if (it.equals(item))
				{
					return true;
				}
			}
			
			return false;
		}
		
		return false;
	}
}
package lpoo.logic;

abstract class Backpack
{
	private Item[] b_items;
	private int b_size;
	private int b_index;
		
	protected Backpack()
	{
		this(10);
	}
	
	protected Backpack(int maxSize)
	{
		this.b_size = maxSize;
		this.b_index = 0;
		this.b_items = new Item[b_size];
	}
	
	protected final Item[] getItems()
	{
		return this.b_items;
	}
	
	protected void setItems(Item[] items)
	{
		this.b_items = items;
	}
	
	protected boolean addItem(Item item)
	{
		if (b_index < b_size)
		{
			b_items[b_index] = item;
			b_index++;
			
			return true;
		}
		
		return false;
	}
	
	protected boolean removeItem(String item)
	{
		if (b_index != 0)
		{
			b_index--;
			b_items[b_index] = null;
			
			return true;
		}
		
		return false;
	}
	
	protected boolean findItem(String item)
	{
		if (b_index != 0)
		{
			for (Item it : b_items)
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
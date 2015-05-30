package lpoo.proj2.logic;

public class RulesAttack extends GameRules
{
	private final int NUMBER_POINTS = 10;
	
	public RulesAttack(Player[] players)
	{
		super(players);
	}
	
	@Override
	public boolean checkOver()
	{
		return (players[0].getScore() + players[1].getScore() == NUMBER_POINTS);
	}

	@Override
	public boolean checkLast()
	{
		return (players[0].getScore() + players[1].getScore() == NUMBER_POINTS - 1);
	}

	@Override
	public boolean checkTie()
	{
		return (players[0].getScore() == players[1].getScore() && players[0].getScore() == NUMBER_POINTS / 2);
	}

	@Override
	public int numberPucks() 
	{
		return 10;
	}
}
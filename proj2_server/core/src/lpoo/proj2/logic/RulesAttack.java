package lpoo.proj2.logic;

public class RulesAttack extends GameRules
{
	private GameBoard board;
	private final int NUMBER_GOALS = 10;
	
	public RulesAttack(GameBoard board)
	{
	}
	
	@Override
	public boolean checkOver()
	{
		return true; // number of pucks <= 0
	}

	@Override
	public boolean checkLast()
	{
		return (players[0].getScore() + players[1].getScore() == NUMBER_GOALS - 1);
	}

	@Override
	public boolean checkTie()
	{
		return (players[0].getScore() == players[1].getScore() && players[0].getScore() == NUMBER_GOALS / 2);
	}
}
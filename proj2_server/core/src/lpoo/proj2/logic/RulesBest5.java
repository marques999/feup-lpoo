package lpoo.proj2.logic;

public class RulesBest5 extends GameRules
{
	private final int NUMBER_POINTS = 5;

	public RulesBest5(Player[] players)
	{
		super(players);
	}

	@Override
	public boolean checkOver()
	{
		return players[0].getScore() + players[1].getScore() >= NUMBER_POINTS;
	}

	@Override
	public boolean checkLast()
	{
		return players[0].getScore() + players[1].getScore() == NUMBER_POINTS - 1;
	}

	@Override
	public boolean checkTie()
	{
		return false;
	}
}
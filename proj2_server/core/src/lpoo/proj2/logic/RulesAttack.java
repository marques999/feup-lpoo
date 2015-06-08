package lpoo.proj2.logic;

public final class RulesAttack extends GameRules
{
	private final int NUMBER_POINTS = 7;

	public RulesAttack(Player[] players)
	{
		super(players);
	}

	@Override
	public boolean checkOver()
	{
		return (players[0].getScore() + players[1].getScore() == NUMBER_POINTS && players[0].getScore() != players[1].getScore());
	}

	@Override
	public boolean checkLast()
	{
		return (players[0].getScore() + players[1].getScore() == NUMBER_POINTS - 1);
	}

	@Override
	public boolean checkTie()
	{
		return false;
	}

	@Override
	public int numberPucks()
	{
		return NUMBER_POINTS;
	}
}
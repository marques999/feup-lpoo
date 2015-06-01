package lpoo.proj2.logic;

public class RulesBest5 extends GameRules
{
	private final int NUMBER_ROUNDS = 5;

	public RulesBest5(Player[] players)
	{
		super(players);
	}

	@Override
	public boolean checkOver()
	{
		return players[0].getScore() + players[1].getScore() >= NUMBER_ROUNDS;
	}

	@Override
	public boolean checkLast()
	{
		return players[0].getScore() + players[1].getScore() == NUMBER_ROUNDS - 1;
	}

	@Override
	public boolean checkTie()
	{
		return false;
	}
}
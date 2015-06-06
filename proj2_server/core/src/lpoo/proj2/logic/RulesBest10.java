package lpoo.proj2.logic;

public final class RulesBest10 extends GameRules
{
	private final int NUMBER_ROUNDS = 10;

	public RulesBest10(Player[] players)
	{
		super(players);
	}

	@Override
	public boolean checkOver()
	{
		return players[0].getScore() + players[1].getScore() >= NUMBER_ROUNDS && players[0].getScore() != players[1].getScore();
	}

	@Override
	public boolean checkLast()
	{
		return players[0].getScore() + players[1].getScore() == NUMBER_ROUNDS - 1;
	}

	@Override
	public boolean checkTie()
	{
		return players[0].getScore() == NUMBER_ROUNDS / 2 && players[1].getScore() == NUMBER_ROUNDS / 2;
	}
}
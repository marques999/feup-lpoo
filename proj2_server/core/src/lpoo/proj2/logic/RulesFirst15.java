package lpoo.proj2.logic;

public class RulesFirst15 extends GameRules
{
	private final int NUMBER_POINTS = 15;
	
	public RulesFirst15(GameBoard board)
	{
		super(board);
	}

	@Override
	public boolean checkOver()
	{
		return players[0].getScore() >= NUMBER_POINTS || players[1].getScore() >= NUMBER_POINTS;
	}

	@Override
	public boolean checkLast()
	{
		return players[0].getScore() == NUMBER_POINTS - 1 || players[1].getScore() == NUMBER_POINTS - 1;
	}

	@Override
	public boolean checkTie()
	{
		return false;
	}
}
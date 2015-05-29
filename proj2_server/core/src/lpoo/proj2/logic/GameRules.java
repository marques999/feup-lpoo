package lpoo.proj2.logic;

import lpoo.proj2.audio.AudioManager;
import lpoo.proj2.audio.Special;

public abstract class GameRules
{
	protected Player players[];
	private ScoreThread runner;
	private AudioManager audio;

	public GameRules()
	{
		players = new Player[2];
		audio = AudioManager.getInstance();
	}

	private class ScoreThread implements Runnable
	{
		@Override
		public void run()
		{
			if (players[0].firstBlood())
			{
				audio.playSpecial(Special.QUAKE_FIRSTBLOOD);
			}
			else if (players[1].firstBlood())
			{
				audio.playSpecial(Special.QUAKE_FIRSTBLOOD);
			}
			else
			{
				switch (Math.max(players[0].getStreak(), players[1].getStreak()))
				{
				case 2:
					audio.playSpecial(Special.QUAKE_DOUBLEKILL);
					break;
				case 4:
					audio.playSpecial(Special.QUAKE_KILLINGSPREE);
					break;
				case 6:
					audio.playSpecial(Special.QUAKE_HOLYSHIT);
					break;
				case 8:
					audio.playSpecial(Special.QUAKE_GODLIKE);
					break;
				}
			}
		}
	}

	public void reset()
	{
		players[0].resetScore();
		players[1].resetScore();
	}

	public void p1Score()
	{
		players[0].score();
		players[1].resetStreak();
		new Thread(runner).start();
	}

	public void p2Goal()
	{
		players[1].score();
		players[0].resetStreak();
		new Thread(runner).start();
	}

	public abstract boolean checkOver();
	public abstract boolean checkLast();
	public abstract boolean checkTie();
}
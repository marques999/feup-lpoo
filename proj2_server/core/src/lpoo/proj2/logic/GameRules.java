package lpoo.proj2.logic;

import lpoo.proj2.audio.AudioManager;
import lpoo.proj2.audio.Special;

public abstract class GameRules
{
	protected Player players[];
	private boolean p1FirstBlood;
	private boolean p2FirstBlood;
	private ScoreThread runner;
	private AudioManager audio;

	public GameRules(Player[] players)
	{
		this.players = players;
		this.p1FirstBlood = false;
		this.p2FirstBlood = false;
		this.runner = new ScoreThread();
		this.audio = AudioManager.getInstance();
	}

	private class ScoreThread implements Runnable
	{
		@Override
		public void run()
		{
			if (p1FirstBlood)
			{
				audio.playSpecial(Special.QUAKE_FIRSTBLOOD);
				p1FirstBlood = false;
			}
			else if (p2FirstBlood)
			{
				audio.playSpecial(Special.QUAKE_FIRSTBLOOD);
				p2FirstBlood = false;
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
		if (players[0].getScore() == 0 && players[1].getScore() == 0)
		{
			p1FirstBlood = true;
		}

		players[0].score();
		players[1].resetStreak();

		new Thread(runner).start();
	}

	public void p2Goal()
	{
		if (players[0].getScore() == 0 && players[1].getScore() == 0)
		{
			p2FirstBlood = true;
		}

		players[1].score();
		players[0].resetStreak();

		new Thread(runner).start();
	}

	public int numberPucks() 
	{
		return 1;
	}

	public abstract boolean checkOver();

	public abstract boolean checkLast();

	public abstract boolean checkTie();
}
package lpoo.proj2.logic;

import lpoo.proj2.audio.AudioManager;
import lpoo.proj2.audio.Special;

public abstract class GameRules
{
	protected Player players[];
	private boolean p1FirstBlood;
	private boolean p2FirstBlood;
	private AudioManager audio;

	public GameRules(Player[] paramPlayers)
	{
		audio = AudioManager.getInstance();
		players = paramPlayers;
		p1FirstBlood = false;
		p2FirstBlood = false;
	}

	private void update()
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
			case 3:
				audio.playSpecial(Special.QUAKE_HATTRICK);
				break;
			case 5:
				audio.playSpecial(Special.QUAKE_KILLINGSPREE);
				break;
			case 7:
				audio.playSpecial(Special.QUAKE_RAMPAGE);
				break;
			case 9:
				audio.playSpecial(Special.QUAKE_HOLYSHIT);
				break;
			case 11:
				audio.playSpecial(Special.QUAKE_GODLIKE);
				break;
			}
		}
	}

	public void reset()
	{
		players[0].resetScore();
		players[1].resetScore();
		p1FirstBlood = false;
		p1FirstBlood = false;
	}

	public void p1Score()
	{
		if (players[0].getScore() == 0 && players[1].getScore() == 0)
		{
			p1FirstBlood = true;
		}

		players[0].score();
		players[1].resetStreak();
		update();
	}

	public void p2Score()
	{
		if (players[0].getScore() == 0 && players[1].getScore() == 0)
		{
			p2FirstBlood = true;
		}

		players[1].score();
		players[0].resetStreak();
		update();
	}

	public int numberPucks()
	{
		return 1;
	}

	public boolean p1Wins()
	{
		return players[0].getScore() > players[1].getScore();
	}

	public boolean p2Wins()
	{
		return players[1].getScore() > players[0].getScore();
	}

	public abstract boolean checkOver();
	public abstract boolean checkLast();
	public abstract boolean checkTie();
}
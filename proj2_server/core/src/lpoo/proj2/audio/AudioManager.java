package lpoo.proj2.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class AudioManager
{
	private static AudioManager instance;
	private float musicVolume = 1.0f;
	private float sfxVolume = 1.0f;

	public static AudioManager getInstance()
	{
		if (instance == null)
		{
			instance = new AudioManager();
		}

		return instance;
	}

	private final Music[] playlist = 
	{
		Gdx.audio.newMusic(Gdx.files.internal("audio/bgm_main.ogg")),
		Gdx.audio.newMusic(Gdx.files.internal("audio/daisukiss.ogg")),
		Gdx.audio.newMusic(Gdx.files.internal("audio/gangnam.ogg")),
		Gdx.audio.newMusic(Gdx.files.internal("audio/bgm_splash.ogg")),
		Gdx.audio.newMusic(Gdx.files.internal("audio/bgm_select.ogg")),
		Gdx.audio.newMusic(Gdx.files.internal("audio/bgm_credits.ogg")),
	};

	private Music currentSong = null;
	private Song currentSongName = Song.THEME_NONE;

	private final Sound[] sfx = 
	{
		Gdx.audio.newSound(Gdx.files.internal("audio/menu_click.mp3")),
		Gdx.audio.newSound(Gdx.files.internal("audio/menu_select.mp3")),
		Gdx.audio.newSound(Gdx.files.internal("audio/goal.mp3")),
		Gdx.audio.newSound(Gdx.files.internal("audio/pause.mp3")),
		Gdx.audio.newSound(Gdx.files.internal("audio/puck_hit.mp3")),
		Gdx.audio.newSound(Gdx.files.internal("audio/puck_place.mp3")),
	};

	private final Sound[] special =
	{
		Gdx.audio.newSound(Gdx.files.internal("audio/doublekill.ogg")),
		Gdx.audio.newSound(Gdx.files.internal("audio/firstblood.ogg")),
		Gdx.audio.newSound(Gdx.files.internal("audio/godlike.ogg")),
		Gdx.audio.newSound(Gdx.files.internal("audio/hattrick.ogg")),
		Gdx.audio.newSound(Gdx.files.internal("audio/holyshit.ogg")),
		Gdx.audio.newSound(Gdx.files.internal("audio/humiliation.mp3")),
		Gdx.audio.newSound(Gdx.files.internal("audio/killingspree.ogg")),
		Gdx.audio.newSound(Gdx.files.internal("audio/prepare.ogg")),
		Gdx.audio.newSound(Gdx.files.internal("audio/rampage.ogg")) 
	};

	public final float getSFXVolume()
	{
		return this.sfxVolume;
	}

	public final float getMusicVolume()
	{
		return this.musicVolume;
	}

	public void setSFXVolume(final float sfxVolume)
	{
		this.sfxVolume = sfxVolume;
	}

	public void setMusicVolume(final float musicVolume)
	{
		this.musicVolume = musicVolume;
		
		if (currentSongName != null)
		{
			if (currentSong.isPlaying())
			{
				currentSong.setVolume(musicVolume);
			}
		}
	}

	public void playSong(final Song songName, final boolean looping)
	{
		if (currentSongName == songName)
		{
			if (currentSong.isPlaying())
			{
				currentSong.setVolume(musicVolume);
			}
			else
			{
				currentSong.play();
			}
		}
		else
		{
			stopSong();

			switch (songName)
			{
			case THEME_MAIN_MENU:
				currentSong = playlist[0];
				break;
			case THEME_A:
				currentSong = playlist[1];
				break;
			case THEME_B:
				currentSong = playlist[2];
				break;
			case THEME_SPLASH:
				currentSong = playlist[3];
				break;
			case THEME_SELECT:
				currentSong = playlist[4];
				break;
			case THEME_CREDITS:
				currentSong = playlist[5];
				break;
			case THEME_NONE:
				currentSong.stop();
				currentSong = null;
				break;
			}

			if (currentSong != null)
			{
				currentSongName = songName;
				currentSong.setLooping(looping);
				currentSong.setVolume(musicVolume);
				currentSong.play();
			}
		}
	}

	public final void playSpecial(final Special soundName)
	{
		switch (soundName)
		{
		case QUAKE_DOUBLEKILL:
			special[0].play(sfxVolume);
			break;
		case QUAKE_FIRSTBLOOD:
			special[1].play(sfxVolume);
			break;
		case QUAKE_GODLIKE:
			special[2].play(sfxVolume);
			break;
		case QUAKE_HATTRICK:
			special[3].play(sfxVolume);
			break;
		case QUAKE_HOLYSHIT:
			special[4].play(sfxVolume);
			break;
		case QUAKE_HUMILIATION:
			special[5].play(sfxVolume);
			break;
		case QUAKE_KILLINGSPREE:
			special[6].play(sfxVolume);
			break;
		case QUAKE_PREPARE:
			special[7].play(sfxVolume);
			break;
		case QUAKE_RAMPAGE:
			special[8].play(sfxVolume);
			break;
		}
	}

	public final void playSound(SFX soundName)
	{
		switch (soundName)
		{
		case MENU_CLICK:
			sfx[0].play(sfxVolume);
			break;
		case MENU_SELECT:
			sfx[1].play(sfxVolume);
			break;
		case SFX_GOAL:
			sfx[2].play(sfxVolume);
			break;
		case SFX_PAUSE:
			sfx[3].play(sfxVolume);
			break;
		case SFX_PUCK_HIT:
			sfx[4].play(sfxVolume);
			break;
		case SFX_PUCK_PLACE:
			sfx[5].play(sfxVolume);
			break;
		}
	}

	public void stopSong()
	{
		if (currentSong != null && currentSong.isPlaying())
		{
			currentSong.stop();
		}
	}

	public void dispose()
	{
		for (final Music m : playlist)
		{
			if (m != null)
			{
				m.dispose();
			}
		}

		for (final Sound s : sfx)
		{
			if (s != null)
			{
				s.dispose();
			}
		}

		for (final Sound s : special)
		{
			if (s != null)
			{
				s.dispose();
			}
		}

		if (currentSong != null)
		{
			currentSong.dispose();
		}
	}
}
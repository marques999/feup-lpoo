package lpoo.proj2.audio;

import lpoo.proj2.AirHockey;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class AudioManager
{
	private static AudioManager instance;
	private float musicVolume = 0.7f;
	private float sfxVolume = 0.6f;

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
		Gdx.audio.newMusic(Gdx.files.internal("audio/bgm_over.ogg")),
		Gdx.audio.newMusic(Gdx.files.internal("audio/bgm_a.ogg")),
		Gdx.audio.newMusic(Gdx.files.internal("audio/bgm_b.ogg")),
		Gdx.audio.newMusic(Gdx.files.internal("audio/bgm_lobby.ogg")),
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
		Gdx.audio.newSound(Gdx.files.internal("audio/paddle_hit.mp3")),
		Gdx.audio.newSound(Gdx.files.internal("audio/puck_hit.mp3")),
		Gdx.audio.newSound(Gdx.files.internal("audio/puck_place.mp3")),
	};

	private final Sound[] special =
	{
		Gdx.audio.newSound(Gdx.files.internal("audio/doublekill.ogg")),
		Gdx.audio.newSound(Gdx.files.internal("audio/firstblood.ogg")),
		Gdx.audio.newSound(Gdx.files.internal("audio/flawless.ogg")),
		Gdx.audio.newSound(Gdx.files.internal("audio/godlike.ogg")),
		Gdx.audio.newSound(Gdx.files.internal("audio/hattrick.ogg")),
		Gdx.audio.newSound(Gdx.files.internal("audio/humiliation.ogg")),
		Gdx.audio.newSound(Gdx.files.internal("audio/monsterkill.ogg")),
		Gdx.audio.newSound(Gdx.files.internal("audio/prepare.ogg")),
		Gdx.audio.newSound(Gdx.files.internal("audio/rampage.ogg")),
		Gdx.audio.newSound(Gdx.files.internal("audio/unstoppable.ogg"))
	};

	public final float getSFXVolume()
	{
		return this.sfxVolume;
	}

	public final float getMusicVolume()
	{
		return this.musicVolume;
	}

	public void setSFXVolume(final float paramSfxVolume)
	{
		sfxVolume = paramSfxVolume;
	}

	public void setMusicVolume(final float paramMusicVolume)
	{
		musicVolume = paramMusicVolume;
		
		if (currentSong != null)
		{
			if (currentSong.isPlaying())
			{
				currentSong.setVolume(musicVolume);
			}
		}
	}

	public void playSong(final Song songName, final boolean looping)
	{
		if (currentSongName == songName && currentSong != null)
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
			if (currentSong != null && currentSong.isPlaying())
			{
				currentSong.stop();
			}

			switch (songName)
			{
			case THEME_MAIN_MENU:
				currentSong = playlist[0];
				break;
			case THEME_GAME_OVER:
				currentSong = playlist[1];
				break;
			case THEME_A:
				currentSong = playlist[2];
				break;
			case THEME_B:
				currentSong = playlist[3];
				break;
			case THEME_LOBBY:
				currentSong = playlist[4];
				break;
			case THEME_SPLASH:
				currentSong = playlist[5];
				break;
			case THEME_SELECT:
				currentSong = playlist[6];
				break;
			case THEME_CREDITS:
				currentSong = playlist[7];
				break;
			case THEME_NONE:
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
		if (!AirHockey.isQuakeEnabled())
		{
			return;
		}

		switch (soundName)
		{
		case QUAKE_DOUBLEKILL:
			special[0].play(sfxVolume);
			break;
		case QUAKE_FIRSTBLOOD:
			special[1].play(sfxVolume);
			break;
		case QUAKE_FLAWLESS:
			special[2].play(sfxVolume);
			break;
		case QUAKE_GODLIKE:
			special[3].play(sfxVolume);
			break;
		case QUAKE_HATTRICK:
			special[4].play(sfxVolume);
			break;
		case QUAKE_HUMILIATION:
			special[5].play(sfxVolume);
			break;
		case QUAKE_MONSTERKILL:
			special[6].play(sfxVolume);
			break;
		case QUAKE_PREPARE:
			special[7].play(sfxVolume);
			break;
		case QUAKE_RAMPAGE:
			special[8].play(sfxVolume);
			break;
		case QUAKE_UNSTOPPABLE:
			special[9].play(sfxVolume);
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
		case SFX_PADDLE_HIT:
			sfx[4].play(sfxVolume);
			break;
		case SFX_PUCK_HIT:
			sfx[5].play(sfxVolume);
			break;
		case SFX_PUCK_PLACE:
			sfx[6].play(sfxVolume);
			break;
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
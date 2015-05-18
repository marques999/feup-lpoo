package lpoo.proj2.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class AudioManager
{
	private static float _volume = 1.0f;

	private static Music[] _playlist = 
	{
		Gdx.audio.newMusic(Gdx.files.internal("audio/velveeta.ogg")),
		Gdx.audio.newMusic(Gdx.files.internal("audio/daisukiss.ogg")),
		Gdx.audio.newMusic(Gdx.files.internal("audio/gangnam.ogg")),
		Gdx.audio.newMusic(Gdx.files.internal("audio/gameover.ogg")) 
	};

	private static Music _song = null;

	private static Sound[] _sounds = 
	{
		Gdx.audio.newSound(Gdx.files.internal("audio/dadada.ogg")),
		Gdx.audio.newSound(Gdx.files.internal("audio/dadada.ogg")),
		Gdx.audio.newSound(Gdx.files.internal("audio/dadada.ogg")),
		Gdx.audio.newSound(Gdx.files.internal("audio/dadada.ogg")), 
	};

	public static void playSong(Song songName, boolean looping)
	{
		if (_song != null && _song.isPlaying())
		{
			_song.stop();
		}

		switch (songName)
		{
		case THEME_MAIN_MENU:
			_song = _playlist[0];
			break;
		case THEME_A:
			_song = _playlist[1];
			break;
		case THEME_B:
			_song = _playlist[2];
			break;
		case THEME_GAME_OVER:
			_song = _playlist[3];
			break;
		case THEME_NONE:
			_song.stop();
			_song = null;
			break;
		}

		_song.setLooping(looping);
		_song.play();
	}

	public static void playSound(SFX soundName)
	{
		switch (soundName)
		{
		case SFX_MENU_CLICK:
			_sounds[0].play(_volume);
			break;
		case SFX_MENU_HOVER:
			_sounds[1].play(_volume);
			break;
		case SFX_PUCK_HIT:
			_sounds[2].play(_volume);
			break;
		case SFX_GOAL:
			_sounds[3].play(_volume);
			break;
		}
	}

	public static void stopSong()
	{
		if (_song != null && _song.isPlaying())
		{
			_song.stop();
		}
	}

	public static void dispose()
	{
		for (Music m : _playlist)
		{
			if (m != null)
			{
				m.dispose();
			}
		}

		for (Sound s : _sounds)
		{
			if (s != null)
			{
				s.dispose();
			}
		}
		
		if (_song != null)
		{
			_song.dispose();
		}
	}
}
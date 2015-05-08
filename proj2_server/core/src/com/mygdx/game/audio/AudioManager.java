package com.mygdx.game.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class AudioManager
{
	private static float _volume = 1.0f;

	private static Music[] _playlist =
	{
		Gdx.audio.newMusic(Gdx.files.internal("core/assets/velveeta.ogg")),
		Gdx.audio.newMusic(Gdx.files.internal("core/assets/daisukiss.ogg")),
		Gdx.audio.newMusic(Gdx.files.internal("core/assets/gangnam.ogg")),
		Gdx.audio.newMusic(Gdx.files.internal("core/assets/gameover.ogg"))
	};

	private static Music _song = _playlist[0];
	private static Song _songTitle = Song.THEME_NONE;

	private static Sound[] _sounds =
	{
		Gdx.audio.newSound(Gdx.files.internal("core/assets/dadada.ogg")),
		Gdx.audio.newSound(Gdx.files.internal("core/assets/dadada.ogg")),
		Gdx.audio.newSound(Gdx.files.internal("core/assets/dadada.ogg")),
		Gdx.audio.newSound(Gdx.files.internal("core/assets/dadada.ogg")),
	};

	public static void playSong(Song songName, boolean looping)
	{
		if (_song.isPlaying())
		{
			_song.stop();
		}

		switch (songName)
		{
			case THEME_MAIN_MENU:
				_songTitle = Song.THEME_MAIN_MENU;
				_song = _playlist[0];
				break;
			case THEME_A:
				_songTitle = Song.THEME_A;
				_song = _playlist[1];
				break;
			case THEME_B:
				_songTitle = Song.THEME_B;
				_song = _playlist[2];
				break;
			case THEME_GAME_OVER:
				_songTitle = Song.THEME_GAME_OVER;
				_song = _playlist[3];
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
		if(_song != null)
		{
			_song.stop();
			_songTitle = Song.THEME_NONE;
		}
	}

	public static void dispose()
	{
		for (Music m : _playlist)
		{
			m.dispose();
		}

		for (Sound s :_sounds)
		{
			s.dispose();
		}
	}
}
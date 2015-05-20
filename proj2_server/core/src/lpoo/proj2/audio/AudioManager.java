package lpoo.proj2.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class AudioManager
{
	private float _musicvolume = 1.0f;
	private float _sfxvolume = 1.0f;
	private static AudioManager _instance;
	
	public static AudioManager getInstance()
	{
		if (_instance == null)
		{
			_instance = new AudioManager();
		}
		
		return _instance;
	}
	
	private Music[] _playlist = 
	{
		Gdx.audio.newMusic(Gdx.files.internal("audio/velveeta.ogg")),
		Gdx.audio.newMusic(Gdx.files.internal("audio/daisukiss.ogg")),
		Gdx.audio.newMusic(Gdx.files.internal("audio/gangnam.ogg")),
		Gdx.audio.newMusic(Gdx.files.internal("audio/gameover.ogg")) 
	};

	private Music _song = null;

	private Sound[] _sounds = 
	{
		Gdx.audio.newSound(Gdx.files.internal("audio/menu_click.mp3")),
		Gdx.audio.newSound(Gdx.files.internal("audio/menu_select.mp3")),
		Gdx.audio.newSound(Gdx.files.internal("audio/dadada.ogg")),
		Gdx.audio.newSound(Gdx.files.internal("audio/dadada.ogg")), 
	};
	
	private Sound[] _special =
	{
		Gdx.audio.newSound(Gdx.files.internal("audio/doublekill.ogg")),
		Gdx.audio.newSound(Gdx.files.internal("audio/firstblood.ogg")),
		Gdx.audio.newSound(Gdx.files.internal("audio/godlike.ogg")),
		Gdx.audio.newSound(Gdx.files.internal("audio/hattrick.ogg")),
		Gdx.audio.newSound(Gdx.files.internal("audio/holyshit.ogg")),
		Gdx.audio.newSound(Gdx.files.internal("audio/killingspree.ogg")),
		Gdx.audio.newSound(Gdx.files.internal("audio/prepare.ogg")),
		Gdx.audio.newSound(Gdx.files.internal("audio/rampage.ogg"))
	};

	public float getSFXVolume()
	{
		return _sfxvolume;
	}
	
	public float getMusicVolume()
	{
		return _musicvolume;
	}
	
	public void setSFXVolume(float sfxVolume)
	{
		_sfxvolume = sfxVolume;
	}
	
	public void setMusicVolume(float musicVolume)
	{
		_musicvolume = musicVolume;
	}
	
	public void playSong(Song songName, boolean looping)
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
		_song.setVolume(_musicvolume);
		_song.play();
	}
	
	public void playSpecial(Special soundName)
	{
		switch (soundName)
		{
		case QUAKE_DOUBLEKILL:
			_special[0].play(_sfxvolume);
			break;
		case QUAKE_FIRSTBLOOD:
			_special[1].play(_sfxvolume);
			break;
		case QUAKE_GODLIKE:
			_special[2].play(_sfxvolume);
			break;
		case QUAKE_HATTRICK:
			_special[3].play(_sfxvolume);
			break;
		case QUAKE_HOLYSHIT:
			_special[4].play(_sfxvolume);
			break;
		case QUAKE_KILLINGSPREE:
			_special[5].play(_sfxvolume);
			break;
		case QUAKE_PREPARE:
			_special[6].play(_sfxvolume);
			break;
		case QUAKE_RAMPAGE:
			_special[7].play(_sfxvolume);
			break;
		}
	}

	public void playSound(SFX soundName)
	{
		switch (soundName)
		{
		case MENU_CLICK:
			_sounds[0].play(_sfxvolume);
			break;
		case MENU_SELECT:
			_sounds[1].play(_sfxvolume);
			break;
		case SFX_PUCK_HIT:
			_sounds[2].play(_sfxvolume);
			break;
		case SFX_GOAL:
			_sounds[3].play(_sfxvolume);
			break;
		}
	}

	public void stopSong()
	{
		if (_song != null && _song.isPlaying())
		{
			_song.stop();
		}
	}

	public void dispose()
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
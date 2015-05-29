package lpoo.proj2;

import java.util.ArrayList;
import java.util.Stack;

import lpoo.proj2.audio.AudioManager;
import lpoo.proj2.audio.Song;
import lpoo.proj2.gui.GUICredits;
import lpoo.proj2.gui.GUISelect;
import lpoo.proj2.gui.GUIGame;
import lpoo.proj2.gui.GUIMainMenu;
import lpoo.proj2.gui.GUIOptions;
import lpoo.proj2.gui.GUIScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class AirHockey extends Game
{
	private GUIScreen _current;
	private ArrayList<GUIScreen> _menus;
	private Stack<Integer> _stack;
	private AudioManager _am;
	private boolean _multiplayer = false;

	public AirHockey()
	{
	}
	
	public boolean isMultiplayer()
	{
		return _multiplayer;
	}
	
	public void startMultiplayer()
	{
		_multiplayer = true;
		switchTo(3);
	}
	
	public void startSingleplayer()
	{
		_multiplayer = false;
		switchTo(3);
	}

	private Preferences preferences;
    private static final String PREF_SINGLEPLAYER_THEME = "game.bgmusic";
    private static final String PREF_SINGLEPLAYER_DIFFICULTY = "game.difficulty";
    private static final String PREF_AUDIO_QUAKE_SOUNDS = "audio.sfx.quake";
    private static final String PREF_AUDIO_SFX_VOLUME = "audio.sfx.volume";
    private static final String PREF_AUDIO_MUSIC_VOLUME = "audio.music.volume";

	public enum Difficulty 
	{
		EASY, MEDIUM, HARD, INSANE
	}
	
	public void getPreferences()
	{
		if (preferences == null)
		{
			preferences = Gdx.app.getPreferences("Air Hockey.gdx");
		}
	}

	public int getDifficulty()
	{
		return preferences.getInteger(PREF_SINGLEPLAYER_DIFFICULTY, 0);
	}
	
	public void setDifficulty(int difficulty)
	{
		if (difficulty >= 0 && difficulty <= 3)
		{
			preferences.putInteger(PREF_SINGLEPLAYER_DIFFICULTY, difficulty);
		}
	}
	
	public boolean isQuakeEnabled()
	{
		return preferences.getBoolean(PREF_AUDIO_QUAKE_SOUNDS, true);
	}
	
	public void enableQuake()
	{
		preferences.putBoolean(PREF_AUDIO_QUAKE_SOUNDS, true);
	}
	
	public void disableQuake()
	{
		preferences.putBoolean(PREF_AUDIO_QUAKE_SOUNDS, false);
	}
	
	public Song getTheme()
	{
		return preferences.getInteger(PREF_SINGLEPLAYER_THEME, 0) == 0 ? Song.THEME_A : Song.THEME_B;
	}
	
	public void setTheme(int theme)
	{
		preferences.putInteger(PREF_SINGLEPLAYER_THEME, theme);
	}
	
	public float getSFXVolume()
	{
		return preferences.getFloat(PREF_AUDIO_SFX_VOLUME, 0.6f);
	}
	
	public float getMusicVolume()
	{
		return preferences.getFloat(PREF_AUDIO_MUSIC_VOLUME, 1.0f);
	}
	
	public void setSFXVolume(float sfxVolume)
	{
		preferences.putFloat(PREF_AUDIO_SFX_VOLUME, sfxVolume);
	}
	
	public void setMusicVolume(float musicVolume)
	{
		preferences.putFloat(PREF_AUDIO_MUSIC_VOLUME, musicVolume);
	}
	
	@Override
	public void create()
	{
		getPreferences();
		_menus = new ArrayList<GUIScreen>();
		_stack = new Stack<Integer>();
		_menus.add(new GUIGame(this));
		_menus.add(new GUIMainMenu(this));
		_menus.add(new GUIOptions(this));
		_menus.add(new GUISelect(this));
		_menus.add(new GUICredits(this));
		_am = AudioManager.getInstance();
		switchTo(1);
	}

	public void switchTo(final int i)
	{
		if (_current != null)
		{
			_current.hide();
		}

		_current = _menus.get(i);
		_stack.push(i);
		Gdx.input.setInputProcessor(_current);
		_current.show();
		setScreen(_current);
		_am.playSong(_current.getSong(), true);
		preferences.flush();
	}

	@Override
	public void render()
	{
		super.render();
	}
}

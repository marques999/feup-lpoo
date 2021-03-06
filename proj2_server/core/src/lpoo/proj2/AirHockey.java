package lpoo.proj2;

import java.util.ArrayList;

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
	private AudioManager _am;
	private boolean _multiplayer;
	private int _mode;

	public AirHockey()
	{
		_multiplayer = false;
		_mode = 1;
	}

	public final boolean isMultiplayer()
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

	public final int getMode()
	{
		return _mode;
	}

	public void setMode(int paramMode)
	{
		_mode = paramMode;
	}

	private static Preferences preferences;
	private final static String PREF_SINGLEPLAYER_THEME = "game.bgmusic";
	private final static String PREF_SINGLEPLAYER_DIFFICULTY = "game.difficulty";
	private final static String PREF_SINGLEPLAYER_PUCK = "game.puck.color";
	private final static String PREF_AUDIO_QUAKE_SOUNDS = "audio.sfx.quake";
	private final static String PREF_AUDIO_SFX_VOLUME = "audio.sfx.volume";
	private final static String PREF_AUDIO_MUSIC_VOLUME = "audio.music.volume";

	public static void getPreferences()
	{
		if (preferences == null)
		{
			preferences = Gdx.app.getPreferences("Air Hockey.gdx");
		}
	}

	public static int getDifficulty()
	{
		return preferences.getInteger(PREF_SINGLEPLAYER_DIFFICULTY, 0);
	}

	public static void setDifficulty(int difficulty)
	{
		preferences.putInteger(PREF_SINGLEPLAYER_DIFFICULTY, difficulty);
	}

	public static int getColor()
	{
		return preferences.getInteger(PREF_SINGLEPLAYER_PUCK, 0);
	}

	public static void setColor(int color)
	{
		preferences.putInteger(PREF_SINGLEPLAYER_PUCK, color);
	}

	public static Song getTheme()
	{
		return preferences.getInteger(PREF_SINGLEPLAYER_THEME, 0) == 0 ? Song.THEME_A : Song.THEME_B;
	}

	public static void setTheme(int theme)
	{
		preferences.putInteger(PREF_SINGLEPLAYER_THEME, theme);
	}

	public static boolean isQuakeEnabled()
	{
		return preferences.getBoolean(PREF_AUDIO_QUAKE_SOUNDS, true);
	}

	public static void enableQuake()
	{
		preferences.putBoolean(PREF_AUDIO_QUAKE_SOUNDS, true);
	}

	public static void disableQuake()
	{
		preferences.putBoolean(PREF_AUDIO_QUAKE_SOUNDS, false);
	}

	public static float getSFXVolume()
	{
		return preferences.getFloat(PREF_AUDIO_SFX_VOLUME, 0.6f);
	}

	public static float getMusicVolume()
	{
		return preferences.getFloat(PREF_AUDIO_MUSIC_VOLUME, 0.7f);
	}

	public static void setSFXVolume(float sfxVolume)
	{
		preferences.putFloat(PREF_AUDIO_SFX_VOLUME, sfxVolume);
	}

	public static void setMusicVolume(float musicVolume)
	{
		preferences.putFloat(PREF_AUDIO_MUSIC_VOLUME, musicVolume);
	}

	@Override
	public void create()
	{
		getPreferences();

		_am = AudioManager.getInstance();
		_am.setMusicVolume(getMusicVolume());
		_am.setSFXVolume(getSFXVolume());
		_menus = new ArrayList<GUIScreen>();
		_menus.add(new GUIGame(this));
		_menus.add(new GUIMainMenu(this));
		_menus.add(new GUIOptions(this));
		_menus.add(new GUISelect(this));
		_menus.add(new GUICredits(this));

		switchTo(1);
	}

	public void switchTo(final int i)
	{
		if (_current != null)
		{
			_current.hide();
		}

		_current = _menus.get(i);
		Gdx.input.setInputProcessor(_current);
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
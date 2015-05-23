package lpoo.proj2;

import java.util.ArrayList;
import java.util.Stack;

import lpoo.proj2.audio.AudioManager;
import lpoo.proj2.gui.GUICredits;
import lpoo.proj2.gui.GUIDifficulty;
import lpoo.proj2.gui.GUIGame;
import lpoo.proj2.gui.GUIMainMenu;
import lpoo.proj2.gui.GUIOptions;
import lpoo.proj2.gui.GUIScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class AirHockey extends Game
{
	private GUIScreen _current;
	private ArrayList<GUIScreen> _menus;
	private Stack<Integer> _stack;
	private AudioManager _am;

	public AirHockey()
	{
	}

	@Override
	public void create()
	{
		_menus = new ArrayList<GUIScreen>();
		_stack = new Stack<Integer>();
		_menus.add(new GUIGame(this));
		_menus.add(new GUIMainMenu(this));
		_menus.add(new GUIOptions(this));
		_menus.add(new GUIDifficulty(this));
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
		setScreen(_current);
		_am.playSong(_current.getSong(), true);
		_current.show();
	}

	@Override
	public void render()
	{
		super.render();
	}
}

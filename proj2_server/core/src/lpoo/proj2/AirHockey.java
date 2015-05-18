package lpoo.proj2;

import java.util.ArrayList;
import java.util.Stack;

import lpoo.proj2.audio.AudioManager;
import lpoo.proj2.audio.Song;
import lpoo.proj2.gui.GUIGame;
import lpoo.proj2.gui.GUIMainMenu;
import lpoo.proj2.gui.GUIOptions;
import lpoo.proj2.gui.GUIScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class AirHockey extends Game
{
	GUIScreen _current;
	
	private ArrayList<GUIScreen> _menus;
	private Stack<GUIScreen> _stack;
	
	public AirHockey()
	{
		
	}

	@Override
	public void create()
	{
		_menus = new ArrayList<GUIScreen>();
		_stack = new Stack<GUIScreen>();
		_menus.add(new GUIGame(this));
		_menus.add(new GUIMainMenu(this));
		_menus.add(new GUIOptions(this));
		switchTo(0);
	}
	
	protected void switchTo(int i)
	{
		if (_current != null)
		{
			_current.hide();
		}
		
		_current = _menus.get(i);
		_stack.push(_current);
		Gdx.input.setInputProcessor(_current);
		setScreen(_current);
		AudioManager.playSong(Song.THEME_MAIN_MENU, true);
		_current.show();
	}

	@Override
	public void render()
	{
		super.render();
		_current.render(0);;
	}
}

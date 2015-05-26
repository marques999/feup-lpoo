package lpoo.proj2.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import lpoo.proj2.AirHockey;

public class DesktopLauncher
{
	public static void main(String[] arg)
	{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 480;
		config.height = 800;
		config.vSyncEnabled = true;
		config.fullscreen = false;
		new LwjglApplication(new AirHockey(), config);
	}
}
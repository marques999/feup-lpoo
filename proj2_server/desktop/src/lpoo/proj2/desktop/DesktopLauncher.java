package lpoo.proj2.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import lpoo.proj2.AirHockey;

public class DesktopLauncher
{
	public static void main(String[] arg)
	{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.addIcon("lpoo/proj2/res/icon-128x128.png", FileType.Internal);
		config.addIcon("lpoo/proj2/res/icon-32x32.png", FileType.Internal);
		config.addIcon("lpoo/proj2/res/icon-16x16.png", FileType.Internal);
		config.title = "Air Hockey";
		config.width = 480;
		config.height = 800;
		config.fullscreen = false;
		config.resizable = false;
		config.vSyncEnabled = true;
		new LwjglApplication(new AirHockey(), config);
	}
}
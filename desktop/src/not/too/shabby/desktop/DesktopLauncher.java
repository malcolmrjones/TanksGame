/*
 * DesktopLauncher.java
 * Required class for desktop game applications in LibGDX.
 * research LibGDX for more information.
 */
package not.too.shabby.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import not.too.shabby.TanksGame;

public class DesktopLauncher 
{
	public static void main (String[] arg) 
	{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.title = "Tanks ~ !tooShabby() 2017";
		config.addIcon("tankicon.png", FileType.Internal);
		config.height = TanksGame.HEIGHT;
		config.width = TanksGame.WIDTH;
		config.resizable = false;
		
		new LwjglApplication(new TanksGame(), config);
	}
}

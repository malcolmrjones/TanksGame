/*
 * GameWorld.java
 * The GameWorld class represents the game's world.
 * This class' responsibility is to house all the GameObjects
 * in the world. This class allows easy access to all game objects
 * in the world from other classes, screens, etc. 
 */

package not.too.shabby;

import java.util.ArrayList;
import com.badlogic.gdx.math.Vector2;



public class GameWorld 
{
	
	/* PLAYER 1 and PLAYER 2 */
	public static Tank tank1;
	public static Tank tank2;
	
	/* ALL BULLETS CURRENTLY IN THE WORLD */
	public static ArrayList<Bullet> bullets;

	
	/* INITIALIZES ALL GAME OBJECTS ~~~ INIT() METHOD MUST BE CALLED */
	public static void init()
	{
		tank1 = new Tank(new Vector2(145, 303), 1);
		tank1.rotation = 180;
		
		tank2 = new Tank(new Vector2(645, 303), 2);

		bullets = new ArrayList<Bullet>();
	}

}

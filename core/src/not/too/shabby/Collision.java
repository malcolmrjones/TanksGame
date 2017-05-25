/*
 * Collision.java
 * The Collision class handles all collisions in the game.
 * The class mostly handles collision between tanks and bullets.
 * When a bullet hits a tanks then the tank takes damage. 
 */

package not.too.shabby;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;



public class Collision 
{
	
	public static void handleCollision()
	{
		
		/***** HANDLES COLLISION DETECTION BETWEEN BULLETS AND TANKS *******/
		for(int i = 0; i < GameWorld.bullets.size(); i++)
		{
			Bullet bullet = GameWorld.bullets.get(i);
			
			
			if(bullet.collider.collidesWith(GameWorld.tank1.collider) && bullet.parentID != 1)
			{
				//System.out.println("BULLET HIT");
				GameWorld.tank1.takeDamage(bullet.damage);
				GameWorld.bullets.remove(i);
			}
			
			if(bullet.collider.collidesWith(GameWorld.tank2.collider) && bullet.parentID != 2)
			{
				//System.out.println("BULLET HIT");
				GameWorld.tank2.takeDamage(bullet.damage);
				GameWorld.bullets.remove(i);
			}
		}
		/***** ******* ******* ******* ******* ******* ******* ******* ******/
	}
	
}

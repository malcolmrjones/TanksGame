/*
 * Input.java
 * The Input class does all the input handling for the game
 * The Input class handles the movement of the tanks as well as the
 * shooting of the tanks.
 */

package not.too.shabby;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.sun.org.apache.bcel.internal.generic.LASTORE;

import not.too.shabby.screens.MainGameScreen;




public class Input 
{
	
	private static long lastShootTimeTank1 = 0;
	private static long lastShootTimeTank2 = 0;
	
	public static void handleInput()
	{

		
		/******************************  PLAYER 1 CONTROLS ******************************/
		
		if(!Gdx.input.isKeyPressed(Keys.W) ||
				!Gdx.input.isKeyPressed(Keys.A) ||
				!Gdx.input.isKeyPressed(Keys.D))
		{
			GameWorld.tank1.ANIMATION_STATE = 1;
		}

			
		if(Gdx.input.isKeyPressed(Keys.W))
		{
			GameWorld.tank1.forward();
			GameWorld.tank1.ANIMATION_STATE = 2;
		}
			
	
		if(Gdx.input.isKeyPressed(Keys.A))
		{
			GameWorld.tank1.rotateLeft();
			GameWorld.tank1.ANIMATION_STATE = 3;
		}
			
		
		if(Gdx.input.isKeyPressed(Keys.D))
		{
			GameWorld.tank1.rotateRight();
			GameWorld.tank1.ANIMATION_STATE = 4;
		}
			
		
		
		/**** SHOOTING ****/
		if(Gdx.input.isKeyJustPressed(Keys.S) || 
				Gdx.input.isKeyJustPressed(Keys.C) ||
				Gdx.input.isKeyJustPressed(Keys.Z))
		{
			
			
			if(TimeUtils.nanoTime() - lastShootTimeTank1 > (1000000000 / 2))
			{
				Vector2 playerCenter = new Vector2(GameWorld.tank1.position.x + GameWorld.tank1.width / 2,
						GameWorld.tank1.position.y + GameWorld.tank1.height / 2);

				Vector2 spawnPos = new Vector2( playerCenter.x - 10,
									playerCenter.y - 10);
				
				//Spawn Bullet
				Bullet bullet = new Bullet(spawnPos, GameWorld.tank1.rotation,1);
				GameWorld.bullets.add(bullet);
				
				lastShootTimeTank1 = TimeUtils.nanoTime();
			}
			

		}
		/****************/
		
		/************************************* END PLAYER 1 CONTROLS *************************************/
		
		
		
		
		
		
		
		
		/******************************  PLAYER 2 CONTROLS ******************************/
		
		if(!Gdx.input.isKeyPressed(Keys.I) ||
				!Gdx.input.isKeyPressed(Keys.J) ||
				!Gdx.input.isKeyPressed(Keys.L))
		{
			GameWorld.tank2.ANIMATION_STATE = 1;
		}

			
		if(Gdx.input.isKeyPressed(Keys.I))
		{
			GameWorld.tank2.forward();
			GameWorld.tank2.ANIMATION_STATE = 2;
		}
			
	
		if(Gdx.input.isKeyPressed(Keys.J))
		{
			GameWorld.tank2.rotateLeft();
			GameWorld.tank2.ANIMATION_STATE = 3;
		}
			
		
		if(Gdx.input.isKeyPressed(Keys.L))
		{
			GameWorld.tank2.rotateRight();
			GameWorld.tank2.ANIMATION_STATE = 4;
		}
		
		
		
		/**** SHOOTING ****/
		if(Gdx.input.isKeyJustPressed(Keys.K) || 
				Gdx.input.isKeyJustPressed(Keys.N) ||
				Gdx.input.isKeyJustPressed(Keys.PERIOD))
		{
			if(TimeUtils.nanoTime() - lastShootTimeTank2 > (1000000000 / 2))
			{
			
				Vector2 playerCenter = new Vector2(GameWorld.tank2.position.x + GameWorld.tank2.width / 2,
						GameWorld.tank2.position.y + GameWorld.tank2.height / 2);
	
				Vector2 spawnPos = new Vector2( (playerCenter.x - 10),
												(playerCenter.y - 10));
				
				//Spawn Bullet
				Bullet bullet = new Bullet(spawnPos, GameWorld.tank2.rotation,2);
				GameWorld.bullets.add(bullet);
				
				lastShootTimeTank2 = TimeUtils.nanoTime();
			}
		}
		/****************/
		
		/************************************* END PLAYER 2 CONTROLS *************************************/
		
		
		
		
		
		
		/******************************  RESET BUTTON ******************************/
		if(Gdx.input.isKeyJustPressed(Keys.R))
		{
			GameWorld.tank1.position.set(new Vector2(145, 303));
			GameWorld.tank2.position.set(new Vector2(645, 303));
		}
		/**************************************************************************/

	}

}

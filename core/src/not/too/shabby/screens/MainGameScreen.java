/*
 * MainGameScreen.java
 * This screen is showed when the actual game is being played
 * This is where the tanks, bullets, map, etc are drawn.
 * This class calls the Collision.java and Input.java classes.
 */
package not.too.shabby.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import not.too.shabby.GameWorld;
import not.too.shabby.Input;
import not.too.shabby.TanksGame;
import not.too.shabby.Bullet;
import not.too.shabby.Collision;

public class MainGameScreen implements Screen
{
	Sound tankHit;
	Sound tankHit2;
	
	Music inGameMusic;
	
	TanksGame game;
	
	public static MainGameScreen gameScreen;
	
	SpriteBatch batch;
	ShapeRenderer rendr;
	
	private int tank1SnapShot = 100;
	private int tank2SnapShot = 100;
	
	private final float HEALTHBARSCALE = 2f; 
	
	Texture mapImg = new Texture("world.png");
	Texture helpImg = new Texture("helpmenu.png");
	Texture pressForHelp = new Texture("presshforhelp.png");
	
	
	public MainGameScreen(TanksGame tank)
	{
		game = tank;
		batch = new SpriteBatch();		
		rendr = new ShapeRenderer();
		GameWorld.init();
		
		
		inGameMusic = Gdx.audio.newMusic(Gdx.files.internal("InGameMusic.mp3"));
		tankHit = Gdx.audio.newSound(Gdx.files.internal("explosionsound.mp3"));
		tankHit2 = Gdx.audio.newSound(Gdx.files.internal("explosionsound.mp3"));
	}
	
	
	/* 
	 * RENDER METHOD IS CALLED EVERY FRAME
	 * IT HANDLES RENDERING OF TANKS, BULLETS, WORLD, ETC. 
	 * AS WELL AS CALLS TO INPUT HANDLING AND COLLISION HANDLING
	 */
	@Override
	public void render(float delta) 
	{
		
		/* IN GAME MUSIC */
		inGameMusic.setVolume(1.0f);
		inGameMusic.setLooping(true);
		inGameMusic.play();
		playHitSound();
		/*****************/
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	
		if (Gdx.input.isKeyPressed(Keys.H))
		{
			/***** HELP SCREEN RENDERING HERE *****/
			Gdx.gl.glClearColor(0, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			
			batch.begin();
			batch.draw(helpImg, 0, 0);
			batch.end();
			/***** ***** ***** ***** ***** ***** ***/
			
			return; //Important! leave this return statement alone
		}
		
		
		/* DETERMINES WHEN TO GO TO GAME OVER SCREEN  */
		if (GameWorld.tank1.getHealth() == 0 || GameWorld.tank2.getHealth() == 0)
		{
			inGameMusic.stop();
			inGameMusic.dispose();
			game.setScreen(new GameOverScreen(game));
		}
		/***** ***** ***** ***** ***** ***** ***** ****/

		
		/*** CALLS TO INPUT AND COLLISION HANDLING ***/
		Input.handleInput();
		Collision.handleCollision();
		/***** ***** ***** ***** ***** ***** ***** ****/
		
		/* UPDATE TANK'S COLLIDERS */
		GameWorld.tank1.update();
		GameWorld.tank2.update();
		/***** ***** ***** ***** ***/
		
		/* RENDERING OF TANKS, MAP, HEALTHBAR **/
		batch.begin();
		
		batch.draw(mapImg, 0, 0);
		batch.draw(pressForHelp, Gdx.graphics.getWidth() / 2 - pressForHelp.getWidth() / 2, Gdx.graphics.getHeight() - 30);
		
		GameWorld.tank1.draw(batch);
		GameWorld.tank2.draw(batch);
		
		drawBullets();
		
		batch.end();
		
		displayHealth();

	}
	
	
	
	
	
	/** HANDLES THE DRAWING OF BULLETS WHEN THEY ARE SHOT **/
	public void drawBullets()
	{
		for(Bullet bullet : GameWorld.bullets)
		{
			bullet.discharge();
			bullet.update();
			bullet.draw(batch);
		}
		
		for(int i = 0; i < GameWorld.bullets.size(); i++)
		{
			Bullet bullet = GameWorld.bullets.get(i);
			if(bullet.position.x < 0 || bullet.position.x > Gdx.graphics.getWidth() ||
					bullet.position.y < 0 || bullet.position.y > Gdx.graphics.getHeight())
			{
				GameWorld.bullets.remove(i);
			}
		}
	}
	
	/** RENDERS HEALTHBARS FOR THE TANKS **/
	public void displayHealth()
	{
		rendr.begin(ShapeType.Filled);
		
		
		if(GameWorld.tank1.getHealth() > 50)
			rendr.setColor(Color.GREEN);
		else if(GameWorld.tank1.getHealth() > 20)
			rendr.setColor(Color.YELLOW);
		else
			rendr.setColor(Color.RED);
		
		rendr.rect(10, Gdx.graphics.getHeight() - 30f, 
				GameWorld.tank1.getHealth() * HEALTHBARSCALE, 15);
		
		
		
		
		
		
		if(GameWorld.tank2.getHealth() > 50)
			rendr.setColor(Color.GREEN);
		else if(GameWorld.tank2.getHealth() > 20)
			rendr.setColor(Color.YELLOW);
		else
			rendr.setColor(Color.RED);
		
		rendr.rect(Gdx.graphics.getWidth() - (100 * HEALTHBARSCALE + 10), 
				Gdx.graphics.getHeight() - 30f, 
				GameWorld.tank2.getHealth() * HEALTHBARSCALE, 15);
		
		rendr.end();
	}
	
	/** HANDLES THE SOUND WHEN A BULLET HITS A TANK **/
	public void playHitSound()
	{
		if (GameWorld.tank1.getHealth() != tank1SnapShot)
		{
			float pitch = (float)(Math.random() * 1.2) + 0.9f;
			tankHit.play(0.1f, pitch, 1f);	
			tank1SnapShot = GameWorld.tank1.getHealth();
		}
		
		if (GameWorld.tank2.getHealth() != tank2SnapShot)
		{
			float pitch = (float)(Math.random() * 1.2) + 0.9f;
			tankHit2.play(0.1f, pitch, 1f);		
			tank2SnapShot = GameWorld.tank2.getHealth();
		}
	}
	
	
	@Override
	public void dispose() 
	{
		tankHit.stop();
		inGameMusic.dispose();
		
		tankHit2.stop();
		tankHit2.dispose();
		
		tankHit.stop();
		tankHit.dispose();
	}
	
	
	
	
	
	
	
	@Override
	public void resize(int width, int height) 
	{}

	@Override
	public void pause() 
	{}

	@Override
	public void resume() 
	{}

	@Override
	public void hide() 
	{}
	
	@Override
	public void show() 
	{}

	

}

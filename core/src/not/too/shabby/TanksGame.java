/*
 * TanksGame.java
 * This class represents a LIBGDX Game.
 * This class is used by the desktop launcher.
 * See LIBGDX Game class for more details.
 */


package not.too.shabby;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import not.too.shabby.screens.TitleScreen;

public class TanksGame extends Game
{
	/* RESOLUTION OF GAME */
	public static final int WIDTH = 800; 
	public static final int HEIGHT = 600;
	
	/* this is public because it is used in other the screens */
	public SpriteBatch batch; 
	
	public static TanksGame game;
	
	@Override
	public void create () 
	{
		batch = new SpriteBatch();
		this.setScreen(new TitleScreen(this));
		
		game = this;
		
	}

	@Override
	public void render () 
	{
		super.render();
	}
	
	@Override
	public void dispose () 
	{
		batch.dispose();
		
	}
}

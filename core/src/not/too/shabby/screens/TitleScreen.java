/*
 * TitleScreen.java
 * The title screen represents the starting screen of the game application.
 * The screen displays the team name as well as the game developer's names 
 */

package not.too.shabby.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import not.too.shabby.TanksGame;

//this is where you render the title screen and implement any audio for the screen.
public class TitleScreen implements Screen
{
	
	TanksGame game;
	
	SpriteBatch batch = new SpriteBatch();
	Texture titleimage = new Texture("titlescreen.png");
	
	public TitleScreen(TanksGame tank)
	{
		this.game = tank;
	}

	@Override
	public void show() 
	{}

	@Override
	public void render(float delta) 
	{
		
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		/*
		 * this is where you will put stuff to render. currently there is nothing to render on the 
		 * title screen, so it just switches to the main menu screen when clicked.
		*/
		batch.begin();         
		batch.draw(titleimage, 0, 0);
		batch.end();
		
		if (Gdx.input.isKeyJustPressed(Keys.ENTER))
		{
			game.setScreen(new MainMenuScreen(game));
		}
		
		
		
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
	public void dispose() 
	{}

}

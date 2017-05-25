/*
 * GameOverScreen.java
 * This screen is shown when a tank is destroyed.
 * It displays text indicating who won the match.
 */
package not.too.shabby.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import not.too.shabby.GameWorld;
import not.too.shabby.TanksGame;




public class GameOverScreen implements Screen
{
	
	TanksGame game;
	TanksGame newGame;
	
	SpriteBatch batch = new SpriteBatch();
	Texture player1winsImg = new Texture("player1wins.png");
	Texture player2winsImg = new Texture("player2wins.png");
	Texture pressEnter = new Texture("pressentertocontinue.png");
	
	public GameOverScreen(TanksGame game)
	{
		this.game = game;
	}

	@Override
	public void show() 
	{}

	@Override
	public void render(float delta) 
	{
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
		batch.draw(pressEnter, Gdx.graphics.getWidth() / 2 - pressEnter.getWidth() / 2, 100);
		
		
		if (GameWorld.tank1.getHealth() == 0)
		{
			batch.draw(player2winsImg, 0, 0);
		}
		else if (GameWorld.tank2.getHealth() == 0)
		{
			batch.draw(player1winsImg, 0, 0);
		}
		batch.end();
		
		if (Gdx.input.isKeyPressed(Keys.ENTER))
		{
			game.setScreen(new MainGameScreen(game));
		}
		
		if (Gdx.input.isKeyPressed(Keys.ESCAPE))
		{
			Gdx.app.exit();
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
	{
		game.dispose();
	}

}

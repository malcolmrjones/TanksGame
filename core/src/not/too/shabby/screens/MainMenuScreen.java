/*
 * MainMenuScreen.java
 * This screen displays the main menu
 * The main menu consist of the title and two buttons. 
 */

package not.too.shabby.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

import not.too.shabby.TanksGame;

public class MainMenuScreen implements Screen
{
	
	private static final int START_BUTTON_WIDTH = 401;
	private static final int START_BUTTON_HEIGHT = 118;
	private static final int EXIT_BUTTON_WIDTH = 401;
	private static final int EXIT_BUTTON_HEIGHT = 113;
	
	private static final int START_BUTTON_Y = 205;
	private static final int START_BUTTON_X = 200;  // or something
	
	private static final int EXIT_BUTTON_Y = 56;
	private static final int EXIT_BUTTON_X = 200;
	
	Texture mainMenuImage;
	Texture startButtonActive;
	Texture startButtonInactive;
	Texture exitButtonActive;
	Texture exitButtonInactive;
	
	Music mainMenuMusic;
	
	TanksGame game;
	
	public MainMenuScreen(TanksGame tank)
	{
		game = tank;
		mainMenuImage = new Texture("mainmenu.png");
		startButtonActive = new Texture("startdark.png");
		startButtonInactive = new Texture("startlight.png");
		exitButtonActive = new Texture("exitdark.png");
		exitButtonInactive = new Texture("exitlight.png");
		mainMenuMusic = Gdx.audio.newMusic(Gdx.files.internal("PotentialFinal.mp3"));
	
	}
	
	
	
	@Override
	public void show() 
	{}

	@Override
	public void render(float delta) 
	{
		
		mainMenuMusic.setVolume(1.0f);
		mainMenuMusic.setLooping(true);
		mainMenuMusic.play();
		game.batch.begin();
		
		game.batch.draw(mainMenuImage, 0, 0);
		
		 /* 
		  * draws "startButtonActive" if the mouse is hovering over it's position on the screen, 
		  * and draws "startButtonInactive" otherwise
		  */
		 if (Gdx.input.getX() < START_BUTTON_X+START_BUTTON_WIDTH && Gdx.input.getX() > START_BUTTON_X && 
				 TanksGame.HEIGHT - Gdx.input.getY() < START_BUTTON_Y + START_BUTTON_HEIGHT && 
				 TanksGame.HEIGHT - Gdx.input.getY() > START_BUTTON_Y)
		 { 
			 game.batch.draw(startButtonActive, 
					 		START_BUTTON_X, START_BUTTON_Y, 
					 		START_BUTTON_WIDTH, START_BUTTON_HEIGHT);
			 
			 if (Gdx.input.isTouched())
			 {
				// if the "start" button is pressed, the game changes screens to the MainGameScreen  
				 mainMenuMusic.stop();
				 game.setScreen(new MainGameScreen(game));  
			 }
			 
		 }
	     else
		 {
		 	game.batch.draw(startButtonInactive, 
		 					START_BUTTON_X, START_BUTTON_Y, 
		 					START_BUTTON_WIDTH, START_BUTTON_HEIGHT);
		 }
		 
		 
		 
		 
		 
		 
		 
	     
	  /* 
	   * draws "exitButtonActive" if the mouse is hovering over it's position on the screen, 
	   * and draws "exitButtonInactive" otherwise
	   */
	     if (Gdx.input.getX() < EXIT_BUTTON_X+EXIT_BUTTON_WIDTH && Gdx.input.getX() > EXIT_BUTTON_X && 
	    		 TanksGame.HEIGHT - Gdx.input.getY() < EXIT_BUTTON_Y + EXIT_BUTTON_HEIGHT && 
	    		 TanksGame.HEIGHT - Gdx.input.getY() > EXIT_BUTTON_Y)
	     {
	        game.batch.draw(exitButtonActive, 
	        				EXIT_BUTTON_X, EXIT_BUTTON_Y, 
	        				EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
	        
	     	if (Gdx.input.isTouched())
	     	{
	     		Gdx.app.exit();   // if the "exit" button is pressed, the game exits, in case you were not sure you PLEB READING MY CODE
	     	}
	     }
	     else
		 {
		 	game.batch.draw(exitButtonInactive, 
		 					EXIT_BUTTON_X, EXIT_BUTTON_Y, 
		 					EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);
		 }
	     
	     game.batch.end();
		
	}
	
	public void dispose() 
	{
		startButtonActive.dispose();
		startButtonInactive.dispose();
		exitButtonActive.dispose();
		exitButtonInactive.dispose();
		mainMenuMusic.dispose();
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

	
}

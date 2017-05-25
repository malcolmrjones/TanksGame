/*
 * Tank.java
 * The Tank class represents a tank game object in the world
 * The tanks class handles the movement of the tank and 
 * the drawing of the tank. A Tank is a GameObject
 */

package not.too.shabby;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;



public class Tank extends GameObject
{
	
	private int health = 100;
	private final float speed = 230;
	private float rotSpeed = 180;

	public Collider collider;
	

	
	
	/* GRAPHIC VARS FOR TANK */
	private Texture image;
	
	public int ANIMATION_STATE = 1;
	private float stateTime = 0f;
	
	//Makes Animation
	private Animation<TextureRegion> fowardAnimation;
	private Animation<TextureRegion> stillAnimation;
	private Animation<TextureRegion> leftAnimation;
	private Animation<TextureRegion> rightAnimation;
	
	//Makes Texture Regions
	private TextureRegion[] tankFoward = new TextureRegion[4];
	private TextureRegion[] tankLeft = new TextureRegion[4];
	private TextureRegion[] tankRight = new TextureRegion[4];
	private TextureRegion[] tankStill = new TextureRegion[4];
	
	//Animation Renderings
	private TextureRegion rendTankFoward;
	private TextureRegion rendTankLeft;
	private TextureRegion rendTankRight;
	private TextureRegion rendTankStill;
	
	
	public Tank(Vector2 setPosition, int ID)
	{
		super(setPosition, 22f, 22f);
		
		/* Determines which tank image to load depending on which player */
		if(ID == 1)
			image = new Texture("tankgreen.png");
		else if(ID == 2)
			image = new Texture("tanktan.png");
		
		
		collider = new Collider(this);
		
		scale = 3f;
		
		
		/* SET UP FOR TANK ANIMATIONS AND IMAGES */
		TextureRegion[][] tempTank = TextureRegion.split(image, image.getWidth()/4,
				image.getHeight()/4);
	
		
		for(int row = 0; row < 4; row++)
		{
			
			for(int col = 0; col < 4; col++)
			{
				TextureRegion region = tempTank[row][col];
				
				if(row == 0)
					tankStill[col] = region;
				else if(row == 1)
					tankFoward[col] = region;
				else if(row == 2)
					tankLeft[col] = region;
				else
					tankRight[col] = region;
	
			}
			
		}
		
		fowardAnimation = new Animation<TextureRegion>(.1f, tankFoward);
		leftAnimation = new Animation<TextureRegion>(.1f, tankLeft);
		rightAnimation = new Animation<TextureRegion>(.1f, tankRight);
		stillAnimation = new Animation<TextureRegion>(1f, tankStill);

	
	}
	
	/* FOR UPDATING THE COLLIDER POSTION, ROTATION, ETC. WITH THE TANK */
	public void update()
	{
		collider.update();
	}
	
	/* METHOD THAT HANDLES ALL OF THE DRAWING FOR THE TANK OBJECT */
	public void draw(SpriteBatch batch)
	{
		
		//Update statetime for animation
		stateTime += Gdx.graphics.getDeltaTime();
		
		rendTankFoward = fowardAnimation.getKeyFrame(stateTime, true);
		rendTankLeft = leftAnimation.getKeyFrame(stateTime, true);
		rendTankRight = rightAnimation.getKeyFrame(stateTime, true);
		rendTankStill = stillAnimation.getKeyFrame(stateTime, true);
		
		
		switch(ANIMATION_STATE)
		{
			case 1:
				batch.draw(rendTankStill, position.x, position.y, origin.x, origin.y, width, height, scale, scale, rotation);
				break;
			case 2:
				batch.draw(rendTankFoward, position.x, position.y, origin.x, origin.y, width, height, scale, scale, rotation);
				break;
			case 3:
				batch.draw(rendTankLeft, position.x, position.y, origin.x, origin.y, width, height, scale, scale, rotation);
				break;
			case 4:
				batch.draw(rendTankRight, position.x, position.y, origin.x, origin.y, width, height, scale, scale, rotation);
				break;
		}
		

		batch.end();
		//collider.draw(); //USE FOR DEBUGGING COLLIDERS
		batch.begin();
	}

	
	
	
	
	
	
	/* MOVES TANK FORWARD */
	public void forward() 
	{
		float radRotation = (float)Math.toRadians(rotation);
		position.add((speed * Gdx.graphics.getDeltaTime()) * (float)Math.cos(radRotation),
						(speed * Gdx.graphics.getDeltaTime()) * (float)Math.sin(radRotation));

	}
	
	/* MOVES TANK BACKWARDS */
	public void backward() 
	{
		float radRotation = (float)Math.toRadians(rotation);
		position.sub((speed * Gdx.graphics.getDeltaTime()) * (float)Math.cos(radRotation),
				(speed * Gdx.graphics.getDeltaTime()) * (float)Math.sin(radRotation));
	}

	/* ROTATES TANK COUNTER-CLOCKWISE */
	public void rotateLeft() 
	{
		rotation += rotSpeed * Gdx.graphics.getDeltaTime();
	}
	
	/* ROTATES TANK CLOCKWISE */
	public void rotateRight() 
	{
		rotation -= rotSpeed * Gdx.graphics.getDeltaTime();
	}
	
	public void bounceBack()
	{
		float radRotation = (float)Math.toRadians(rotation);
		position.sub(2f*(speed * Gdx.graphics.getDeltaTime()) * (float)Math.cos(radRotation),
				(speed * Gdx.graphics.getDeltaTime()) * (float)Math.sin(radRotation));
	}

	
	
	
	
	
	/* USED WHEN TANK TAKES DAMAGE. CHANGES HEALTH */
	public void takeDamage(int amount)
	{
		health -= amount;
	}
	
	/* RETURN HEALTH OF TANK */
	public int getHealth()
	{
		return health;
	}

}

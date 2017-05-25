/* 
 * Bullet.java
 * The Bullet class represents a single Bullet Object in the Game World
 * Bullet class handles rendering of the bullet as well as the discharge
 * of the bullet.
 */

package not.too.shabby;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Bullet extends GameObject 
{

	Texture image;
	float speed;
	int damage;
	
	Collider collider;
	
	int parentID;
	
	

	
	
	
	public Bullet(Vector2 setPosition, float setRotation, int setParentID)
	{
		super(setPosition, 5, 2);
		
		image = new Texture("Bullet.png");
		
		speed = 1000f;
		
		rotation = setRotation;
		
		collider = new Collider(this);
		
		damage = 10;
		
		parentID = setParentID;
		
		scale = 8f;
	}
	
	
	
	
	
	
	/* FOR UPDATING THE COLLIDER POSTION, ROTATION, ETC. WITH THE BULLET */
	public void update()
	{
		collider.update();	
	}
	
	/* HANDLES THE DRAWING OF THE BULLET OBJECT */
	@Override
	public void draw(SpriteBatch batch)
	{
		
		batch.draw(image, position.x, position.y, 
				origin.x, origin.y, 
				width, height, 
				scale, scale, 
				rotation, 
				0, 0, 
				(int)width, (int)height,
				false, false);
		
		
		batch.end();
		//collider.draw(); // USED FOR DEBUGGING
		batch.begin();
		

	}
	
	
	
	
	
	
	/* DISCHARGES THE BULLET SENDING IT FORWARD */
	public void discharge()
	{
		float radRotation = (float)Math.toRadians(rotation);
		position.add(speed * Gdx.graphics.getDeltaTime() * (float)Math.cos(radRotation), 
						speed * Gdx.graphics.getDeltaTime() * (float)Math.sin(radRotation));
	}

}

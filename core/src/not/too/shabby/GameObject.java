package not.too.shabby;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class GameObject 
{
	
	public Vector2 position;
	Vector2 origin;
	float rotation;
	
	float width;
	float height;
	
	public float scale = 1f;

	
	public GameObject(Vector2 setPosition, float setWidth, float setHeight)
	{
		position = setPosition;
		origin = new Vector2(setWidth / 2, setHeight / 2);
		
		width = setWidth;
		height = setHeight;
		
		rotation = 0;
	}
	
	public abstract void draw(SpriteBatch batch);

}

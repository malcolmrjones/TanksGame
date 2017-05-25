/*
 * Collider.java
 * The Collider classes represents bounding boxes.
 * Colliders are used for detecting collision.
 * A collider requires a parent from with the collider
 * is bounded to. THe Update method must be called to update
 * the collider's position and rotation.
 */

package not.too.shabby;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;




public class Collider 
{
	
	GameObject parent;
	Polygon bounds;
	
	public Collider(GameObject setParent)
	{
		parent = setParent;
		
		float[] boundsPoints = {
				parent.position.x , parent.position.y,
				parent.position.x + parent.width, parent.position.y,
				parent.position.x + parent.width, parent.position.y + parent.height,
				parent.position.x, parent.position.y + parent.height	
		};
		
		bounds = new Polygon(boundsPoints);
		bounds.setOrigin(parent.origin.x, parent.origin.y);
	}
	
	/* UPDATES THE LOCATION AND ROTATION OF BOUNDING BOX ~~~~ REQUIRED METHOD */
	public void update()
	{

		
		bounds.setRotation(0);
		float[] boundsPoints = {
				parent.position.x, parent.position.y,
				parent.position.x + parent.width, parent.position.y,
				parent.position.x + parent.width, parent.position.y + parent.height,
				parent.position.x, parent.position.y + parent.height	
		};
		
		bounds.setVertices(boundsPoints);
		//bounds.setPosition(parent.position.x, parent.position.y);
		bounds.setOrigin(parent.position.x + parent.origin.x, parent.position.y + parent.origin.y);
		bounds.setRotation(parent.rotation);
		bounds.setScale(parent.scale, parent.scale);
	}
	
	/* DRAWS BOUNDING BOX IN RED OUTLINE. USED FOR DEBUGGING */
	public void draw()
	{
		ShapeRenderer rendr = new  ShapeRenderer();
		rendr.begin(ShapeType.Line);
		rendr.setColor(Color.RED);
		rendr.polygon(bounds.getTransformedVertices());
		rendr.end();
	}
	
	
	/* 
	 * CHECKS FOR COLLISION WITH ANOTHER COLLIDER.
	 * RETURNS TRUE IF THERE IS A COLLISION WITH THE other COLLIDER.
	 * FALSE OTHERWISE
	 */
	public boolean collidesWith(Collider other)
	{
		return Intersector.overlapConvexPolygons(this.bounds, other.bounds);
	}
	
	public String toString()
	{
		return "COLLIDER (" + parent.position.x + ", " + parent.position.y + ")\n";
	}

}

package bewareofthetruth.model.util;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;


public class TiledObjectUtil {

	public static void parseTiledObjectLayer(World world, MapObjects objects) {
		for(MapObject object : objects) {
			
			Shape shape = null;
			
			if(object instanceof PolylineMapObject) {
				shape = createPolyline((PolylineMapObject) object);
			}
			
			Body body;
			BodyDef bdef = new BodyDef();
			bdef.type = BodyDef.BodyType.StaticBody;
			body = world.createBody(bdef);
			body.createFixture(shape, 1.0f);
			shape.dispose();
		}
	}
	
	/*public static void parseTiledObjectLayer(World world, MapLayer mapLayer) {
		Shape shape = null;
		
		
			shape = createPolyline((PolylineMapObject) mapLayer.getObjects());
		
		
		Body body;
		BodyDef bdef = new BodyDef();
		bdef.type = BodyDef.BodyType.StaticBody;
		body = world.createBody(bdef);
		body.createFixture(shape, 1.0f);
		shape.dispose();
	}*/
	
	
	private static ChainShape createPolyline(PolylineMapObject polyline) {
		float[] vertices = polyline.getPolyline().getTransformedVertices();
		Vector2[] worldVertices = new Vector2[vertices.length /2];
		
		for(int i = 0; i < worldVertices.length; i++) {
			worldVertices[i] = new Vector2(vertices[i  * 2] / Constants.PPM, vertices [i * 2 + 1] / Constants.PPM);
		}
		ChainShape cs = new ChainShape();
		cs.createChain(worldVertices);
		return cs;
	}
}

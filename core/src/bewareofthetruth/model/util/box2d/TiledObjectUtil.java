package bewareofthetruth.model.util.box2d;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.CircleMapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import static bewareofthetruth.model.util.Constants.BIT_ENNEMY;
import static bewareofthetruth.model.util.Constants.BIT_PLAYER;
import static bewareofthetruth.model.util.Constants.BIT_WALL;
import bewareofthetruth.model.util.Constants;



public class TiledObjectUtil {

	private static float ppt = 128f; //pixels per tile
	
	public static void parseTiledObjectLayer(World world, MapObjects objects) {
		for(MapObject object : objects) {
			
			Shape shape = null;
			System.out.println("parse Tiled");
			if(object instanceof PolylineMapObject) {
				shape = createPolyline((PolylineMapObject) object);
			}else if(object instanceof CircleMapObject) {
				System.out.println("tentative circle shape");
				shape = createCircle((CircleMapObject) object);
			}else if (object instanceof RectangleMapObject) {
                shape = createRectangle((RectangleMapObject)object);
                System.out.println("Creation rectangle !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            } else if (object instanceof PolygonMapObject) {
                shape = createPolygon((PolygonMapObject)object);
            }
			
			Body body;
			BodyDef bdef = new BodyDef();
			bdef.type = BodyDef.BodyType.StaticBody;
			body = world.createBody(bdef);
			System.out.println(body.toString());
			System.out.println(shape.toString());
			FixtureDef fixtureDef = new FixtureDef();
			fixtureDef.shape = shape;
			fixtureDef.density = 1.0f;			
			fixtureDef.filter.categoryBits = BIT_WALL;
			fixtureDef.filter.maskBits = (short) (BIT_ENNEMY | BIT_PLAYER);
			fixtureDef.filter.groupIndex = (short) 0;
			body.createFixture(fixtureDef);
			shape.dispose();
		}
	}
	
	public static void parseLightObjectLayer(World world, MapObjects objects) {
		for(MapObject object : objects) {
			
			Shape shape = null;
			System.out.println("parse Tiled");
			if(object instanceof PolylineMapObject) {
				shape = createPolyline((PolylineMapObject) object);
			}else if(object instanceof CircleMapObject) {
				System.out.println("tentative circle shape");
				shape = createCircle((CircleMapObject) object);
			}else if (object instanceof RectangleMapObject) {
                shape = createRectangle((RectangleMapObject)object);
                System.out.println("Creation rectangle !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            } else if (object instanceof PolygonMapObject) {
                shape = createPolygon((PolygonMapObject)object);
            }
			
			Body body;
			BodyDef bdef = new BodyDef();
			bdef.type = BodyDef.BodyType.StaticBody;
			body = world.createBody(bdef);
			System.out.println(body.toString());
			System.out.println(shape.toString());
			FixtureDef fixtureDef = new FixtureDef();
			fixtureDef.shape = shape;
			fixtureDef.density = 1.0f;			
			fixtureDef.filter.categoryBits = BIT_WALL;
			fixtureDef.filter.maskBits = (short) (BIT_ENNEMY | BIT_PLAYER);
			fixtureDef.filter.groupIndex = (short) 0;
			body.createFixture(fixtureDef);
			shape.dispose();
		}
	}
		
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
	
	private static CircleShape createCircle(CircleMapObject circleObject) {
		Circle circle = circleObject.getCircle();	
		CircleShape cs = new CircleShape();
		System.out.println("new circle shape");
		cs.setRadius(circle.radius / Constants.PPM);
		cs.setPosition(new Vector2(circle.x /Constants.PPM, circle.y /Constants.PPM));
		return cs;
		
	}
	
    private static PolygonShape createPolygon(PolygonMapObject polygonObject) {
        PolygonShape polygon = new PolygonShape();
        float[] vertices = polygonObject.getPolygon().getTransformedVertices();

        float[] worldVertices = new float[vertices.length];

        for (int i = 0; i < vertices.length; ++i) {
            System.out.println(vertices[i]);
            worldVertices[i] = vertices[i] / ppt;
        }

        polygon.set(worldVertices);
        return polygon;
    }
    
    private static PolygonShape createRectangle(RectangleMapObject rectangleObject) {
        Rectangle rectangle = rectangleObject.getRectangle();
        PolygonShape polygon = new PolygonShape();
        Vector2 size = new Vector2((rectangle.x + rectangle.width * 0.5f) / Constants.PPM,
                                   (rectangle.y + rectangle.height * 0.5f ) / Constants.PPM);
        polygon.setAsBox(rectangle.width * 0.5f / Constants.PPM,
                         rectangle.height * 0.5f / Constants.PPM,
                         size,
                         0.0f);
        return polygon;
    }
}

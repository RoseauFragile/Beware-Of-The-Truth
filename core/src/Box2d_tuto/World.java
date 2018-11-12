package Box2d_tuto;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class World  {
	private static final int SCENE_WIDTH = 13; // 13 metres    wide 
	private static final int SCENE_HEIGHT = 7; // 7 metres    high
	private static final float WORLD_WIDTH = 20f; // 13 metres    wide 
	private static final float WORLD_HEIGHT = 10f; // 7 metres    high
	
	World world; 
	Box2DDebugRenderer debugRenderer; 
	BodyDef defaultDynamicBodyDef;

	// Box 
	FixtureDef boxFixtureDef; 
	PolygonShape square;
	
	// Ball 
	CircleShape circle; 
	FixtureDef circleFixtureDef;
	private Viewport viewport; 
	
	
	private void create() {
		viewport = new FitViewport(SCENE_WIDTH, SCENE_HEIGHT);
		// Center camera to get (0,0) as the origin of the Box2D world 
		viewport.update(SCENE_WIDTH, SCENE_HEIGHT, true);
		
		world = new World(new Vector2(0,-9.8f), true); 
	}
}

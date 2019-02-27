package Box2d_tuto;

import java.awt.Point;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import bewareofthetruth.main.Main;
import bewareofthetruth.screens.GameScreen;

public class World_Box2D extends GameScreen implements InputProcessor {
	private static final int SCENE_WIDTH = 13; // 13 metres    wide 
	private static final int SCENE_HEIGHT = 7; // 7 metres    high
	private static final float WORLD_WIDTH = 20f; // 13 metres    wide 
	private static final float WORLD_HEIGHT = 10f; // 7 metres    high
	
	World world; 
	Box2DDebugRenderer debugRenderer; 
	BodyDef defaultDynamicBodyDef;
	Matrix4 debugMatrix;
    SpriteBatch batch;



	// Box 
	FixtureDef boxFixtureDef; 
	PolygonShape square;
	protected boolean boxMode = true;
	
	// Ball 
	CircleShape circle; 
	FixtureDef circleFixtureDef;
	private Viewport viewport; 
	
	private Main _game;
	
	public World_Box2D(Main game) {
		this._game = game;
	}
	
	@Override
	public void show() {
		viewport = new FitViewport(SCENE_WIDTH, SCENE_HEIGHT);
		// Center camera to get (0,0) as the origin of the Box2D world 
		viewport.update(SCENE_WIDTH, SCENE_HEIGHT, true);
		
		world = new World(new Vector2(0,-9.8f), true); 
		System.out.println("WORLD INIALIZED");
		
		debugRenderer = new Box2DDebugRenderer();
		
		defaultDynamicBodyDef = new BodyDef(); 
		defaultDynamicBodyDef.type = BodyType.DynamicBody; 
        batch = new SpriteBatch();
		// Shape for boxes 
		square = new PolygonShape(); 
		// 1 meter-sided square (0.5f is half-width/height) 
		square.setAsBox(0.5f, 0.5f);
		// Fixture definition for boxes 
		boxFixtureDef = new FixtureDef(); 
		boxFixtureDef.shape = square; 
		boxFixtureDef.density = 0.8f; 
		boxFixtureDef.friction = 0.8f; 
		boxFixtureDef.restitution = 0.15f; 
		// Shape for circles 
		circle = new CircleShape(); 
		// 0.5 metres for radius 
		circle.setRadius(0.5f);
		// Fixture definition for our shapes 
		circleFixtureDef = new FixtureDef(); 
		circleFixtureDef.shape = circle; 
		circleFixtureDef.density = 0.5f; 
		circleFixtureDef.friction = 0.4f; 
		circleFixtureDef.restitution = 0.6f; 
	}
	
	@Override
	public void render(float delta) {
		Gdx.input.setInputProcessor(this);
		world.step(1/60f, 6, 2);
		debugMatrix = batch.getProjectionMatrix().cpy().scale(5,
                5, 0);
		debugRenderer.render(world, debugMatrix);		
		super.render(delta);
	}
	
	@Override
	public void dispose() { 
		debugRenderer.dispose();
		square.dispose();  
		circle.dispose();  
		world.dispose(); 
	}
	
	private void createSquare(float x, float y) {  
		defaultDynamicBodyDef.position.set(x,y);
		Body body = world.createBody(defaultDynamicBodyDef);
		body.createFixture(boxFixtureDef); 
	  }
	
	private void createCircle(float x, float y) {  
		defaultDynamicBodyDef.position.set(x,y);
		Body body = world.createBody(defaultDynamicBodyDef);
		body.createFixture(circleFixtureDef); 
	  }


	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		 if (button == Input.Buttons.LEFT) {    
			 	Vector3 point = new Vector3(5, 5, 0);
				viewport.getCamera().unproject(point.set(screenX,screenY, 0));    
			    if(boxMode) {   
			    	createSquare(point.x, point.y);
			    	boxMode = !boxMode;    

			    } else { // Ball mode      
			    	createCircle(point.x, point.y);    
			    	boxMode = !boxMode;    
			    }
			    return true;  
			    
		 } return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}

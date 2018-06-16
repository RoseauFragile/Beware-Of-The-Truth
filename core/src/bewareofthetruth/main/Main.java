package bewareofthetruth.main;

import java.sql.SQLException;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import bewareofthetruth.contract.model.data.IModelFacade;
import bewareofthetruth.model.ModelFacade;

public class Main implements ApplicationListener {

	private SpriteBatch batch;
	private IModelFacade modelFacade;
    private float elapsedTime = 0;
    private Sprite zombieSprite;
    private OrthographicCamera cam;
	
	@Override
	public void create() {

			try {
				this.modelFacade = new ModelFacade();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			this.cam = this.modelFacade.getBewareOfTruthModel().getCam().getCamera();
			this.zombieSprite = new Sprite(this.modelFacade.getBewareOfTruthModel().getPlayer().getTexture());
			this.zombieSprite.setPosition(0, 0);
			this.zombieSprite.setSize(50, 50);
			cam.position.set(this.modelFacade.getBewareOfTruthModel().getCam().getCamera().viewportWidth / 2f, this.modelFacade.getBewareOfTruthModel().getCam().getCamera().viewportHeight / 2f, 0);
			cam.update();
			this.batch = new SpriteBatch();
			
			/*// First we create a body definition
			BodyDef bodyDef = new BodyDef();
			// We set our body to dynamic, for something like ground which doesn't move we would set it to StaticBody
			bodyDef.type = BodyType.DynamicBody;
			// Set our body's starting position in the world
			bodyDef.position.set(100, 300);

			// Create our body in the world using our body definition
			Body body = this.modelFacade.getBewareOfTruthModel().getWorld().createBody(bodyDef);

			// Create a circle shape and set its radius to 6
			CircleShape circle = new CircleShape();
			circle.setRadius(6f);

			// Create a fixture definition to apply our shape to
			FixtureDef fixtureDef = new FixtureDef();
			fixtureDef.shape = circle;
			fixtureDef.density = 0.5f; 
			fixtureDef.friction = 0.4f;
			fixtureDef.restitution = 0.6f; // Make it bounce a little bit
			Fixture fixture = body.createFixture(fixtureDef);
			circle.dispose();*/
			
			/*// Create our body definition
			BodyDef groundBodyDef = new BodyDef();  
			// Set its world position
			groundBodyDef.position.set(new Vector2(0, 10));  

			// Create a body from the defintion and add it to the world
			Body groundBody = this.modelFacade.getBewareOfTruthModel().getWorld().createBody(groundBodyDef);  

			// Create a polygon shape
			PolygonShape groundBox = new PolygonShape();  
			// Set the polygon shape as a box which is twice the size of our view port and 20 high
			// (setAsBox takes half-width and half-height as arguments)
			groundBox.setAsBox(this.modelFacade.getBewareOfTruthModel().getCam().getCamera().viewportWidth, 10.0f);
			// Create a fixture from our polygon shape and add it to our ground body  
			groundBody.createFixture(groundBox, 0.0f); 
			// Clean up after ourselves
			groundBox.dispose();*/
	}

	@Override
	public void resize(int width, int height) {
		cam.viewportWidth = 30f;                 // Viewport of 30 units!
		cam.viewportHeight = 30f * height/width; // Lets keep things in proportion.
		cam.update();
	}

	@Override
	public void render() {
		
		handleInput();
		cam.update();
		this.batch.setProjectionMatrix(cam.combined);
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		this.batch.begin();
		this.zombieSprite.draw(this.batch);
		this.batch.end();
        /*this.batch.begin();
        this.modelFacade.getBewareOfTruthModel().getDebugRenderer().render(this.modelFacade.getBewareOfTruthModel().getWorld(), this.modelFacade.getBewareOfTruthModel().getCam().getCamera().combined);
        this.batch.draw(this.modelFacade.getBewareOfTruthModel().getPlayer().getTexture(), 10 , 10);
        this.modelFacade.getBewareOfTruthModel().getCam().getCamera().update();
        this.batch.setProjectionMatrix(this.modelFacade.getBewareOfTruthModel().getCam().getCamera().combined);
        //this.batch.draw(this.modelFacade.getBewareOfTruthModel().getPlayer().getTexture(), 200 , 200);
        //this.elapsedTime += Gdx.graphics.getDeltaTime();
        
        //this.batch.draw(this.modelFacade.getBewareOfTruthModel().getPlayer().getAnimation().getKeyFrame(elapsedTime, true), 0, 0);
        this.batch.end();*/
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		/*batch.dispose();
		this.modelFacade.getBewareOfTruthModel().getPlayer().getTextureAtlas().dispose();	*/
	}
	
	private void handleInput() {
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			cam.zoom += 0.02;
			//If the A Key is pressed, add 0.02 to the Camera's Zoom
		}
		if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
			cam.zoom -= 0.02;
			//If the Q Key is pressed, subtract 0.02 from the Camera's Zoom
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			cam.translate(-3, 0, 0);
			//If the LEFT Key is pressed, translate the camera -3 units in the X-Axis
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			cam.translate(3, 0, 0);
			//If the RIGHT Key is pressed, translate the camera 3 units in the X-Axis
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			cam.translate(0, -3, 0);
			//If the DOWN Key is pressed, translate the camera -3 units in the Y-Axis
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			cam.translate(0, 3, 0);
			//If the UP Key is pressed, translate the camera 3 units in the Y-Axis
		}
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			cam.rotate(-this.modelFacade.getBewareOfTruthModel().getCam().getRotationSpeed(), 0, 0, 1);
			//If the W Key is pressed, rotate the camera by -rotationSpeed around the Z-Axis
		}
		if (Gdx.input.isKeyPressed(Input.Keys.E)) {
			cam.rotate(this.modelFacade.getBewareOfTruthModel().getCam().getRotationSpeed(), 0, 0, 1);
			//If the E Key is pressed, rotate the camera by rotationSpeed around the Z-Axis
		}

		cam.zoom = MathUtils.clamp(cam.zoom, 0.1f, 100/cam.viewportWidth);

		float effectiveViewportWidth = cam.viewportWidth * cam.zoom;
		float effectiveViewportHeight = cam.viewportHeight * cam.zoom;

		cam.position.x = MathUtils.clamp(cam.position.x, effectiveViewportWidth / 2f, 100 - effectiveViewportWidth / 2f);
		cam.position.y = MathUtils.clamp(cam.position.y, effectiveViewportHeight / 2f, 100 - effectiveViewportHeight / 2f);
	}
}

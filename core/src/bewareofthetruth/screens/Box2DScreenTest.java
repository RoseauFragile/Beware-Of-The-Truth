package bewareofthetruth.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import bewareofthetruth.audio.AudioObserver;
import bewareofthetruth.main.Main;
import bewareofthetruth.main.Main.ScreenType;
import bewareofthetruth.utility.Utility;

public class Box2DScreenTest extends GameScreen implements InputProcessor {

	private Stage _stage;
	private Main _game;
    public static Texture backgroundTexture;
    public static Sprite backgroundSprite;
    private World _world;
    private Box2DDebugRenderer _box2DDebugRenderer;	
    BodyDef _defaultDynamicBodyDef;
	// Box
	FixtureDef _boxFixtureDef;
	PolygonShape _square;
	// Ball
	CircleShape _circle;
	FixtureDef _circleFixtureDef;
	private static final float SCENE_WIDTH = 12.8f;
	// 13 metres    wide
	private static final float SCENE_HEIGHT = 7.2f;
	// 7 metres    high
	
	public Box2DScreenTest(Main game) {
		this._game = game;
		
		Gdx.input.setInputProcessor(this);
		
		_world = new World(new Vector2(0,-9.8f), true);
		_box2DDebugRenderer = new Box2DDebugRenderer();
		_defaultDynamicBodyDef = new BodyDef();
		_defaultDynamicBodyDef.type = BodyType.DynamicBody;
		
		// Shape for boxes
		_square = new PolygonShape();
		// 1 meter-sided square (0.5f is half-width/height)
		_square.setAsBox(45.0f, 45.5f);
		// Fixture definition for boxes
		_boxFixtureDef = new FixtureDef();
		_boxFixtureDef.shape = _square;
		_boxFixtureDef.density = 0.8f;
		_boxFixtureDef.friction = 0.8f;
		_boxFixtureDef.restitution = 0.15f; 
		
		// Shape for circles
		_circle = new CircleShape();
		// 0.5 metres for radius
		_circle.setRadius(0.5f);
		
		// Fixture definition for our shapes
		_circleFixtureDef = new FixtureDef();
		_circleFixtureDef.shape = _circle;
		_circleFixtureDef.density = 0.5f;
		_circleFixtureDef.friction = 0.4f;
		_circleFixtureDef.restitution = 0.6f; 
		
		_defaultDynamicBodyDef.position.set(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
		Body body = _world.createBody(_defaultDynamicBodyDef);
		body.createFixture(_boxFixtureDef); 
		
		
		//creation
		_stage = new Stage();
		Table table = new Table();
		table.setFillParent(true);
		TextButton returnButton = new TextButton("Exit",Utility.STATUSUI_SKIN);
		//Layout
		table.add(returnButton).spaceBottom(10).row();
		_stage.addActor(table);



notify(AudioObserver.AudioCommand.MUSIC_LOAD, AudioObserver.AudioTypeEvent.MUSIC_TITLE);
	}
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		_stage.act(delta);
		_stage.draw();
		
		_world.step(1/60f, 6, 2);
		_box2DDebugRenderer.render(_world, _stage.getViewport().getCamera().combined); 
	}

	@Override
	public void resize(int width, int height) {
		_stage.getViewport().setScreenSize(width, height);
	}

	@Override
	public void show() {
		notify(AudioObserver.AudioCommand.MUSIC_PLAY_LOOP, AudioObserver.AudioTypeEvent.MUSIC_TITLE);
		Gdx.input.setInputProcessor(_stage);
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		_stage.dispose();
		//_batch.dispose();
		_square.dispose();
		_circle.dispose();
		_world.dispose();
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
		return false;
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

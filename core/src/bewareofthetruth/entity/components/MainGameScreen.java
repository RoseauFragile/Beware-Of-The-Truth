package bewareofthetruth.entity.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMapImageLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Json;

import bewareofthetruth.main.Main;

public class MainGameScreen implements Screen {
	
	private static final String TAG = MainGameScreen.class.getSimpleName();
	private PlayerController controller;
	private TextureRegion currentPlayerFrame;
	private Sprite currentPlayerSprite;
	private OrthogonalTiledMapRenderer mapRenderer = null;
	private OrthographicCamera camera = null;
	private static Entity layer;
	
	public static enum GameState {
		SAVING,
		LOADING,
		RUNNING,
		PAUSED,
		GAME_OVER
	}
	private static GameState _gameState;

	protected OrthogonalTiledMapRenderer _mapRenderer = null;
	protected MapManager _mapMgr;
	protected OrthographicCamera _camera = null;
	protected OrthographicCamera _hudCamera = null;

	private Json _json;
	private InputMultiplexer _multiplexer;
	private Main _game;
	private Entity _player;
	//private PlayerHUD _playerHUD;
	
	public static class VIEWPORT {
		public static float viewportWidth;
		public static float viewportHeight;
		public static float virtualWidth;
		public static float virtualHeight;
		public static float physicalWidth;
		public static float physicalHeight;
		public static float aspectRatio;
	}
	
	public MainGameScreen(Main main) {

		_game = main;
		_mapMgr = new MapManager();
		_json = new Json();

		//setGameState(GameState.RUNNING);

		//_camera setup
		setupViewport(10, 10);

		//get the current size
		_camera = new OrthographicCamera();
		_camera.setToOrtho(false, VIEWPORT.viewportWidth, VIEWPORT.viewportHeight);

		_player = EntityFactory.getInstance().getEntity(EntityFactory.EntityType.PLAYER);
		_mapMgr.setPlayer(_player);
		_mapMgr.setCamera(_camera);
	}
	
	@Override
	public void show() {
		/*
		//camera setup
		setupViewport(10 ,10);
		
		//get the current size
		camera = new OrthographicCamera();
		camera.setToOrtho(false, VIEWPORT.viewportWidth, VIEWPORT.viewportHeight);
		
		mapRenderer = new OrthogonalTiledMapRenderer(_mapMgr.getCurrentMap(), MapManager.UNIT_SCALE);
		mapRenderer.setView(camera);
		
		Gdx.app.debug(TAG, "Unit Scale value is: " + mapRenderer.getUnitScale());
		
		_player = new Entity();
		_player.init(_mapMgr.getPlayerStartUnitScaled().x, _mapMgr.getPlayerStartUnitScaled().y);
		
		currentPlayerSprite = _player.getFrameSprite();
		
		controller = new PlayerController(_player);
		Gdx.input.setInputProcessor(controller);*/
		
		if( _mapRenderer == null ){
			_mapRenderer = new OrthogonalTiledMapRenderer(_mapMgr.getCurrentTiledMap(), Map.UNIT_SCALE);
		}
	}

	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		_mapRenderer.setView(_camera);

		_mapRenderer.getBatch().enableBlending();
		_mapRenderer.getBatch().setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

		if( _mapMgr.hasMapChanged() ){
			_mapRenderer.setMap(_mapMgr.getCurrentTiledMap());
			_player.sendMessage(Component.MESSAGE.INIT_START_POSITION, _json.toJson(_mapMgr.getPlayerStartUnitScaled()));

			_camera.position.set(_mapMgr.getPlayerStartUnitScaled().x, _mapMgr.getPlayerStartUnitScaled().y, 0f);
			_camera.update();


			_mapMgr.setMapChanged(false);

		}

		
		TiledMapImageLayer lightMap = (TiledMapImageLayer)_mapMgr.getCurrentLightMapLayer();
		TiledMapImageLayer previousLightMap = (TiledMapImageLayer)_mapMgr.getPreviousLightMapLayer();

		if( lightMap != null) {
			_mapRenderer.getBatch().begin();
			TiledMapTileLayer backgroundMapLayer = (TiledMapTileLayer)_mapMgr.getCurrentTiledMap().getLayers().get(Map.BACKGROUND_LAYER);
			if( backgroundMapLayer != null ){
				_mapRenderer.renderTileLayer(backgroundMapLayer);
			}

			TiledMapTileLayer groundMapLayer = (TiledMapTileLayer)_mapMgr.getCurrentTiledMap().getLayers().get(Map.GROUND_LAYER);
			if( groundMapLayer != null ){
				_mapRenderer.renderTileLayer(groundMapLayer);
			}

			TiledMapTileLayer decorationMapLayer = (TiledMapTileLayer)_mapMgr.getCurrentTiledMap().getLayers().get(Map.DECORATION_LAYER);
			if( decorationMapLayer != null ){
				_mapRenderer.renderTileLayer(decorationMapLayer);
			}

			_mapRenderer.getBatch().end();

			_mapMgr.updateCurrentMapEntities(_mapMgr, _mapRenderer.getBatch(), delta);
			_player.update(_mapMgr, _mapRenderer.getBatch(), delta);

			_mapRenderer.getBatch().begin();
			_mapRenderer.getBatch().setBlendFunction(GL20.GL_DST_COLOR, GL20.GL_ONE_MINUS_SRC_ALPHA);

			_mapRenderer.renderImageLayer(lightMap);
			_mapRenderer.getBatch().setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
			_mapRenderer.getBatch().end();

			if( previousLightMap != null ){
				_mapRenderer.getBatch().begin();
				_mapRenderer.getBatch().setBlendFunction(GL20.GL_DST_COLOR, GL20.GL_ONE_MINUS_SRC_COLOR);
				_mapRenderer.renderImageLayer(previousLightMap);
				_mapRenderer.getBatch().setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
				_mapRenderer.getBatch().end();
			}
		}else{
			_mapRenderer.render();
			_mapMgr.updateCurrentMapEntities(_mapMgr, _mapRenderer.getBatch(), delta);
			_player.update(_mapMgr, _mapRenderer.getBatch(), delta);
		}
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
		if( _player != null ){
			_player.dispose();
		}

		if( _mapRenderer != null ){
			_mapRenderer.dispose();
		}
		MapFactory.clearCache();
	}

	
	private void setupViewport(int width, int height){
		//Make the viewport a percentage of the total display area
		VIEWPORT.virtualWidth = width;
		VIEWPORT.virtualHeight = height;

		//Current viewport dimensions
		VIEWPORT.viewportWidth = VIEWPORT.virtualWidth;
		VIEWPORT.viewportHeight = VIEWPORT.virtualHeight;

		//pixel dimensions of display
		VIEWPORT.physicalWidth = Gdx.graphics.getWidth();
		VIEWPORT.physicalHeight = Gdx.graphics.getHeight();

		//aspect ratio for current viewport
		VIEWPORT.aspectRatio = (VIEWPORT.virtualWidth / VIEWPORT.virtualHeight);

		//update viewport if there could be skewing
		if( VIEWPORT.physicalWidth / VIEWPORT.physicalHeight >= VIEWPORT.aspectRatio){
			//Letterbox left and right
			VIEWPORT.viewportWidth = VIEWPORT.viewportHeight * (VIEWPORT.physicalWidth/VIEWPORT.physicalHeight);
			VIEWPORT.viewportHeight = VIEWPORT.virtualHeight;
		}else{
			//letterbox above and below
			VIEWPORT.viewportWidth = VIEWPORT.virtualWidth;
			VIEWPORT.viewportHeight = VIEWPORT.viewportWidth * (VIEWPORT.physicalHeight/VIEWPORT.physicalWidth);
		}

		Gdx.app.debug(TAG, "WorldRenderer: virtual: (" + VIEWPORT.virtualWidth + "," + VIEWPORT.virtualHeight + ")" );
		Gdx.app.debug(TAG, "WorldRenderer: viewport: (" + VIEWPORT.viewportWidth + "," + VIEWPORT.viewportHeight + ")" );
		Gdx.app.debug(TAG, "WorldRenderer: physical: (" + VIEWPORT.physicalWidth + "," + VIEWPORT.physicalHeight + ")" );
	}
	
}

package bewareofthetruth.entity.components.player;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import bewareofthetruth.entity.Entity;
import bewareofthetruth.entity.EntityConfig;
import bewareofthetruth.entity.EntityConfig.AnimationConfig;
import bewareofthetruth.entity.components.ComponentObserver;
import bewareofthetruth.entity.components.GraphicsComponent;
import bewareofthetruth.map.Map;
import bewareofthetruth.map.MapManager;
import bewareofthetruth.sfx.ShakeCamera;


public class PlayerGraphicsComponent extends GraphicsComponent {

	private static final String TAG = PlayerGraphicsComponent.class.getSimpleName();
	protected Vector2 _previousPosition;
	protected float cameraX = _currentPosition.x;
	protected float cameraY = _currentPosition.y;
	private final ShakeCamera _shakeCam;


	public PlayerGraphicsComponent(){
		_previousPosition = new Vector2(0,0);
		_shakeCam = new ShakeCamera(_currentPosition.x,_currentPosition.y, 10.0f);

	}

	@Override
	public void receiveMessage(String message) {

		final String[] string = message.split(MESSAGE_TOKEN);

		if( string.length == 0 ) {
			return;
		}

		//Specifically for messages with 1 object payload
		if( string.length == 2 ) {
			if (string[0].equalsIgnoreCase(MESSAGE.CURRENT_POSITION.toString())) {
				_currentPosition = _json.fromJson(Vector2.class, string[1]);
			} else if (string[0].equalsIgnoreCase(MESSAGE.INIT_START_POSITION.toString())) {
				_currentPosition = _json.fromJson(Vector2.class, string[1]);
			} else if (string[0].equalsIgnoreCase(MESSAGE.CURRENT_STATE.toString())) {
				_currentState = _json.fromJson(Entity.State.class, string[1]);
			} else if (string[0].equalsIgnoreCase(MESSAGE.CURRENT_DIRECTION.toString())) {
				_currentDirection = _json.fromJson(Entity.Direction.class, string[1]);
			} else if (string[0].equalsIgnoreCase(MESSAGE.LOAD_ANIMATIONS.toString())) {
				final EntityConfig entityConfig = _json.fromJson(EntityConfig.class, string[1]);
				final Array<AnimationConfig> animationConfigs = entityConfig.getAnimationConfig();

				for( final AnimationConfig animationConfig : animationConfigs ){
					final Array<String> textureNames = animationConfig.getTexturePaths();
					final Array<GridPoint2> points = animationConfig.getGridPoints();
					final Entity.AnimationType animationType = animationConfig.getAnimationType();
					final float frameDuration = animationConfig.getFrameDuration();
					Animation<TextureRegion> animation = null;

					if( textureNames.size == 1) {
						animation = loadAnimation(textureNames.get(0), points, frameDuration);
					}else if( textureNames.size == 2){
						animation = loadAnimation(textureNames.get(0), textureNames.get(1), points, frameDuration);
					}

					_animations.put(animationType, animation);
				}
			}
		}
	}

	@Override
	public void update(Entity entity, MapManager mapMgr, Batch batch, float delta){
		updateAnimations(delta);

		//Player has moved
		if( _previousPosition.x != _currentPosition.x ||
				_previousPosition.y != _currentPosition.y){
			notify("", ComponentObserver.ComponentEvent.PLAYER_HAS_MOVED);
			_previousPosition = _currentPosition.cpy();
		}

		final Camera camera = mapMgr.getCamera();

		final TiledMap tiledMap = mapMgr.getCurrentTiledMap();
		final MapProperties prop = tiledMap.getProperties();

		final float mapWidth = prop.get("width", Integer.class) * 2;
		final float mapHeight = prop.get("height", Integer.class) * 2;
		final float camViewportHalfX = camera.viewportWidth / 2;
		final float camViewportHalfY = camera.viewportHeight / 2;
		final float positionX = MathUtils.clamp(_currentPosition.x, camViewportHalfX, mapWidth - camViewportHalfX);
		final float positionY = MathUtils.clamp(_currentPosition.y, camViewportHalfY, mapHeight - camViewportHalfY);

		cameraX += (positionX + delta - cameraX) * delta;
		cameraY += (positionY + delta - cameraY) * delta;
		if(_currentPosition.x < 5) {
			_shakeCam.startShaking();
		}
		if( _shakeCam.isCameraShaking() ){
			final Vector2 shakeCoords = _shakeCam.getNewShakePosition();
			cameraX += shakeCoords.x/100;
			cameraY += shakeCoords.y/100;
			_shakeCam.reset();
			System.out.println("X: " + cameraX + " Y: " + cameraY );
		}

		camera.position.set(cameraX, cameraY, 0f);
		camera.update();
		_shakeCam.reset();



		batch.begin();
		batch.draw(_currentFrame, getEntity().get_physicsComponent().getBody().getPosition().x - Entity.FRAME_WIDTH * Map.UNIT_SCALE / 2, getEntity().get_physicsComponent().getBody().getPosition().y - Entity.FRAME_WIDTH * Map.UNIT_SCALE / 2, 1, 1);
		batch.end();

		//Used to graphically debug boundingboxes
		/*
        Rectangle rect = entity.getCurrentBoundingBox();
        _shapeRenderer.setProjectionMatrix(camera.combined);
        _shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        _shapeRenderer.setColor(Color.RED);
        _shapeRenderer.rect(rect.getX() * Map.UNIT_SCALE , rect.getY() * Map.UNIT_SCALE, rect.getWidth() * Map.UNIT_SCALE, rect.getHeight()*Map.UNIT_SCALE);
        _shapeRenderer.end();
		 */
	}

	@Override
	public void dispose(){
	}

}
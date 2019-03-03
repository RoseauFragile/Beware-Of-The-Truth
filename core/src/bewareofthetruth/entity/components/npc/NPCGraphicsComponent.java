package bewareofthetruth.entity.components.npc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import bewareofthetruth.entity.Entity;
import bewareofthetruth.entity.EntityConfig;
import bewareofthetruth.entity.components.ComponentObserver;
import bewareofthetruth.entity.components.GraphicsComponent;
import bewareofthetruth.map.Map;
import bewareofthetruth.map.MapManager;

public class NPCGraphicsComponent extends GraphicsComponent {
	private static final String TAG = NPCGraphicsComponent.class.getSimpleName();

	private boolean _isSelected = false;
	private boolean _wasSelected = false;

	private boolean _sentShowConversationMessage = false;
	private boolean _sentHideCoversationMessage = false;

	public NPCGraphicsComponent(){
	}

	@Override
	public void receiveMessage(String message) {
		//Gdx.app.debug(TAG, "Got message " + message);
		final String[] string = message.split(MESSAGE_TOKEN);

		if( string.length == 0 ) {
			return;
		}

		if( string.length == 1 ) {
			if (string[0].equalsIgnoreCase(MESSAGE.ENTITY_SELECTED.toString())) {
				if( _wasSelected ){
					_isSelected = false;
				}else{
					_isSelected = true;
				}
			}else if (string[0].equalsIgnoreCase(MESSAGE.ENTITY_DESELECTED.toString())) {
				_wasSelected = _isSelected;
				_isSelected = false;
			}
		}

		if( string.length == 2 ) {
			if (string[0].equalsIgnoreCase(MESSAGE.CURRENT_POSITION.toString())) {
				_currentPosition = _json.fromJson(Vector2.class, string[1]);
			} else if (string[0].equalsIgnoreCase(MESSAGE.INIT_START_POSITION.toString())) {
				_currentPosition = _json.fromJson(Vector2.class, string[1]);
			} else if (string[0].equalsIgnoreCase(MESSAGE.CURRENT_STATE.toString())) {
				_currentState = _json.fromJson(Entity.State.class, string[1]);
			} else if (string[0].equalsIgnoreCase(MESSAGE.CURRENT_DIRECTION.toString())) {
				_currentDirection = _json.fromJson(Entity.Direction.class, string[1]);
			}else if (string[0].equalsIgnoreCase(MESSAGE.LOAD_ANIMATIONS.toString())) {
				final EntityConfig entityConfig = _json.fromJson(EntityConfig.class, string[1]);
				final Array<EntityConfig.AnimationConfig> animationConfigs = entityConfig.getAnimationConfig();

				for( final EntityConfig.AnimationConfig animationConfig : animationConfigs ){
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

		if( _isSelected ){
			drawSelected(entity, mapMgr);
			mapMgr.setCurrentSelectedMapEntity(entity);
			if( _sentShowConversationMessage == false){
				notify(_json.toJson(entity.getEntityConfig()), ComponentObserver.ComponentEvent.SHOW_CONVERSATION);
				_sentShowConversationMessage = true;
				_sentHideCoversationMessage = false;
			}
		}else{
			if( _sentHideCoversationMessage == false ){
				notify(_json.toJson(entity.getEntityConfig()), ComponentObserver.ComponentEvent.HIDE_CONVERSATION);
				_sentHideCoversationMessage = true;
				_sentShowConversationMessage = false;
			}
		}

		batch.begin();
		batch.draw(_currentFrame, getEntity().get_physicsComponent().getBody().getPosition().x - Entity.FRAME_WIDTH * Map.UNIT_SCALE / 2, getEntity().get_physicsComponent().getBody().getPosition().y - Entity.FRAME_WIDTH * Map.UNIT_SCALE / 2, 1, 1);
		batch.end();

		//Used to graphically debug boundingboxes
		/*
        Rectangle rect = entity.getCurrentBoundingBox();
        Camera camera = mapMgr.getCamera();
        _shapeRenderer.setProjectionMatrix(camera.combined);
        _shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        _shapeRenderer.setColor(Color.BLACK);
        _shapeRenderer.rect(rect.getX() * Map.UNIT_SCALE, rect.getY() * Map.UNIT_SCALE, rect.getWidth() * Map.UNIT_SCALE, rect.getHeight() * Map.UNIT_SCALE);
        _shapeRenderer.end();
		 */
	}

	private void drawSelected(Entity entity, MapManager mapMgr){
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		final Camera camera = mapMgr.getCamera();
		final Rectangle rect = entity.getCurrentBoundingBox();
		_shapeRenderer.setProjectionMatrix(camera.combined);
		_shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		_shapeRenderer.setColor(0.0f, 1.0f, 1.0f, 0.5f);

		final float width =  rect.getWidth() * Map.UNIT_SCALE*2f;
		final float height = rect.getHeight() * Map.UNIT_SCALE/2f;
		final float x = rect.x * Map.UNIT_SCALE - width/4;
		final float y = rect.y * Map.UNIT_SCALE - height/2;

		_shapeRenderer.ellipse(x,y,width,height);
		_shapeRenderer.end();
		Gdx.gl.glDisable(GL20.GL_BLEND);
	}


	@Override
	public void dispose(){
	}
}

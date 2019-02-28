package bewareofthetruth.entity.components;

import java.util.Hashtable;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;

import bewareofthetruth.entity.Entity;
import bewareofthetruth.map.MapManager;
import bewareofthetruth.utility.Utility;

public abstract class GraphicsComponent extends ComponentSubject implements Component {
	protected TextureRegion _currentFrame = null;
	protected float _frameTime = 0f;
	protected Entity.State _currentState;
	protected Entity.Direction _currentDirection;
	protected Json _json;
	public Vector2 _currentPosition;
	protected Hashtable<Entity.AnimationType, Animation<TextureRegion>> _animations;
	protected ShapeRenderer _shapeRenderer;
	private static String TAG = GraphicsComponent.class.getSimpleName();
	private Entity entity;

	protected GraphicsComponent(){
		_currentPosition = new Vector2(0,0);
		_currentState = Entity.State.WALKING;
		_currentDirection = Entity.Direction.DOWN;
		_json = new Json();
		_animations = new Hashtable<>();
		_shapeRenderer = new ShapeRenderer();
	}

	public abstract void update(Entity entity, MapManager mapManager, Batch batch, float delta);

	protected void updateAnimations(float delta){
		_frameTime = (_frameTime + delta)%5; //Want to avoid overflow
		//Look into the appropriate variable when changing position
		//TODO Lier l'image au box2d body

		switch (_currentDirection) {
		case DOWN:
			if (_currentState == Entity.State.WALKING) {
				final Animation<TextureRegion> animation = _animations.get(Entity.AnimationType.WALK_DOWN);
				if( animation == null ) {
					return;
				}
				_currentFrame = animation.getKeyFrame(_frameTime);
			} else if (_currentState == Entity.State.ROLL) {
				final Animation<TextureRegion> animation = _animations.get(Entity.AnimationType.ROLL_RIGHT);
				if( animation == null ) {
					return;
				}
				_currentFrame = animation.getKeyFrame(_frameTime);
			} else if(_currentState == Entity.State.IDLE) {
				final Animation<TextureRegion> animation = _animations.get(Entity.AnimationType.WALK_DOWN);
				if( animation == null ) {
					return;
				}
				_currentFrame = animation.getKeyFrames()[0];
			} else if(_currentState == Entity.State.IMMOBILE) {
				final Animation<TextureRegion> animation = _animations.get(Entity.AnimationType.IMMOBILE);
				if( animation == null ) {
					return;
				}
				_currentFrame = animation.getKeyFrame(_frameTime);
			}
			break;
		case LEFT:
			if (_currentState == Entity.State.WALKING) {
				final Animation<TextureRegion> animation = _animations.get(Entity.AnimationType.WALK_LEFT);
				if( animation == null ) {
					return;
				}
				_currentFrame = animation.getKeyFrame(_frameTime);
			} else if (_currentState == Entity.State.ROLL) {
				final Animation<TextureRegion> animation = _animations.get(Entity.AnimationType.ROLL_LEFT);
				if( animation == null ) {
					return;
				}
				_currentFrame = animation.getKeyFrame(_frameTime);
			} else if(_currentState == Entity.State.IDLE) {
				final Animation<TextureRegion> animation = _animations.get(Entity.AnimationType.WALK_LEFT);
				if( animation == null ) {
					return;
				}
				_currentFrame = animation.getKeyFrames()[0];
			} else if(_currentState == Entity.State.IMMOBILE) {
				final Animation<TextureRegion> animation = _animations.get(Entity.AnimationType.IMMOBILE);
				if( animation == null ) {
					return;
				}
				_currentFrame = animation.getKeyFrame(_frameTime);
			}
			break;
		case UP:
			if (_currentState == Entity.State.WALKING ) {
				final Animation<TextureRegion> animation = _animations.get(Entity.AnimationType.WALK_UP);
				if( animation == null ) {
					return;
				}
				_currentFrame = animation.getKeyFrame(_frameTime);
			} else if (_currentState == Entity.State.ROLL) {
				final Animation<TextureRegion> animation = _animations.get(Entity.AnimationType.ROLL_LEFT);
				if( animation == null ) {
					return;
				}
				_currentFrame = animation.getKeyFrame(_frameTime);
			} else if(_currentState == Entity.State.IDLE) {
				final Animation<TextureRegion> animation = _animations.get(Entity.AnimationType.WALK_UP);
				if( animation == null ) {
					return;
				}
				_currentFrame = animation.getKeyFrames()[0];
			} else if(_currentState == Entity.State.IMMOBILE) {
				final Animation<TextureRegion> animation = _animations.get(Entity.AnimationType.IMMOBILE);
				if( animation == null ) {
					return;
				}
				_currentFrame = animation.getKeyFrame(_frameTime);
			}
			break;
		case RIGHT:
			if (_currentState == Entity.State.WALKING) {
				final Animation<TextureRegion> animation = _animations.get(Entity.AnimationType.WALK_RIGHT);
				if( animation == null ) {
					return;
				}
				_currentFrame = animation.getKeyFrame(_frameTime);
			} else if (_currentState == Entity.State.ROLL) {
				final Animation<TextureRegion> animation = _animations.get(Entity.AnimationType.ROLL_RIGHT);
				if( animation == null ) {
					return;
				}
				_currentFrame = animation.getKeyFrame(_frameTime);
			} else if(_currentState == Entity.State.IDLE) {
				final Animation<TextureRegion> animation = _animations.get(Entity.AnimationType.WALK_RIGHT);
				if( animation == null ) {
					return;
				}
				_currentFrame = animation.getKeyFrames()[0];
			} else if(_currentState == Entity.State.IMMOBILE) {
				final Animation<TextureRegion> animation = _animations.get(Entity.AnimationType.IMMOBILE);
				if( animation == null ) {
					return;
				}
				_currentFrame = animation.getKeyFrame(_frameTime);
			}
			break;
		default:
			break;
		}
	}

	//Specific to two frame animations where each frame is stored in a separate texture
	protected Animation loadAnimation(String firstTexture, String secondTexture, Array<GridPoint2> points, float frameDuration){
		Utility.loadTextureAsset(firstTexture);
		final Texture texture1 = Utility.getTextureAsset(firstTexture);

		Utility.loadTextureAsset(secondTexture);
		final Texture texture2 = Utility.getTextureAsset(secondTexture);

		final TextureRegion[][] texture1Frames = TextureRegion.split(texture1, Entity.FRAME_WIDTH, Entity.FRAME_HEIGHT);
		final TextureRegion[][] texture2Frames = TextureRegion.split(texture2, Entity.FRAME_WIDTH, Entity.FRAME_HEIGHT);

		final GridPoint2 point = points.first();

		final Animation animation = new Animation(frameDuration, texture1Frames[point.x][point.y],texture2Frames[point.x][point.y]);
		animation.setPlayMode(Animation.PlayMode.LOOP);

		return animation;
	}

	protected Animation loadAnimation(String textureName, Array<GridPoint2> points, float frameDuration){
		Utility.loadTextureAsset(textureName);
		final Texture texture = Utility.getTextureAsset(textureName);

		final TextureRegion[][] textureFrames = TextureRegion.split(texture, Entity.FRAME_WIDTH, Entity.FRAME_HEIGHT);

		final TextureRegion[] animationKeyFrames = new TextureRegion[points.size];

		for(int i=0; i < points.size; i++){
			animationKeyFrames[i] = textureFrames[points.get(i).x][points.get(i).y];
		}

		final Animation animation = new Animation(frameDuration, (Object[])animationKeyFrames);
		animation.setPlayMode(Animation.PlayMode.LOOP);

		return animation;
	}

	public Animation<TextureRegion> getAnimation(Entity.AnimationType type){
		return _animations.get(type);
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}
}

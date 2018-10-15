package bewareofthetruth.entity.components;

import java.util.Hashtable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.utils.Array;

import bewareofthetruth.MapManager;
import bewareofthetruth.Utility;
import bewareofthetruth.entity.components.Entity.Direction;
import bewareofthetruth.entity.components.Entity.State;

public abstract class GraphicsComponent implements Component {
	
	private static final String TAG = GraphicsComponent.class.getSimpleName();
	private static final String defaultSpritePath = "sprite/zombie_test.png";
	
	@SuppressWarnings("rawtypes")
	private Animation _walkLeftAnimation;
	@SuppressWarnings("rawtypes")
	private Animation _walkRightAnimation;
	@SuppressWarnings("rawtypes")
	private Animation _walkUpAnimation;
	@SuppressWarnings("rawtypes")
	private Animation _walkDownAnimation;
	
	 private Array<TextureRegion> walkLeftFrames;
	 private Array<TextureRegion> walkRightFrames;
	 private Array<TextureRegion> walkUpFrames;
	 private Array<TextureRegion> walkDownFrames;
	protected Hashtable<Entity.AnimationType, Animation>
	_animations;
	protected ShapeRenderer _shapeRenderer;
	 protected float _frameTime = 0f;
	 protected Sprite _frameSprite = null;
	 protected TextureRegion _currentFrame = null;
		private Direction _currentDirection = Direction.LEFT;
		@SuppressWarnings("unused")
		private Direction _previousDirection = Direction.UP;
		private State _currentState = null;
		 public final int FRAME_WIDTH = 64;
		 public final int FRAME_HEIGHT = 64;
	 
	protected GraphicsComponent() {
		 Utility.loadTextureAsset(defaultSpritePath);
		 loadDefaultSprite();
		 loadAllAnimations();
	}
	
	public abstract void update(Entity entity, MapManager mapManager, Batch batch, float delta);
	
	private void loadDefaultSprite() {
		 Texture texture = Utility.getTextureAsset(defaultSpritePath);
		 TextureRegion[][] textureFrames = TextureRegion.split(texture, FRAME_WIDTH, FRAME_WIDTH);
		 _frameSprite = new Sprite(textureFrames[0][0].getTexture(),0,0,FRAME_WIDTH,FRAME_HEIGHT);
		 _currentFrame = textureFrames[0][0];
	 }
	 
	 //TODO à faire ici pour les animations
	 @SuppressWarnings({ "unchecked", "rawtypes" })
	private void loadAllAnimations() {
		 //walk animation
		 Texture texture =Utility.getTextureAsset(defaultSpritePath);
		 TextureRegion[][] textureFrames = TextureRegion.split(texture, FRAME_WIDTH, FRAME_HEIGHT);
		/*
		 walkDownFrames = new Array<TextureRegion>(4);
		 walkLeftFrames = new Array<TextureRegion>(4);
		 walkRightFrames = new Array<TextureRegion>(4);
		 walkUpFrames = new Array<TextureRegion>(4);
		 
		 
		 for (int i = 0; i < 4; i++) {
			 for (int j = 0; j < 4; j++) {
				 TextureRegion region = textureFrames[i][j];
				 if( region == null ){
					 Gdx.app.debug(TAG, "Got null animation frame " + i + "," + j);
					 }
				 switch(i){
					case 0:
						walkDownFrames.insert(j, region);
						break;
					case 1:
						walkLeftFrames.insert(j, region);
						break;          
					case 2:
						walkRightFrames.insert(j, region);
						break;
					case 3:
						walkUpFrames.insert(j, region);
						break;
				 }
			 }
		 }*/
		 
		 walkDownFrames = new Array<TextureRegion>(4);
		 walkLeftFrames = new Array<TextureRegion>(4);
		 walkRightFrames = new Array<TextureRegion>(4);
		 walkUpFrames = new Array<TextureRegion>(4);
		 
		 
		 //for (int i = 0; i < 1; i++) {
			 for (int j = 0; j < 4; j++) {
				 Gdx.app.debug(TAG, "j =" + j);
				 TextureRegion region = textureFrames[0][j];
				 if( region == null ){
					 Gdx.app.debug(TAG, "Got null animation frame " + 0 + "," + j);
					 } else if (j == 0){
						walkDownFrames.insert(j, region);
						walkUpFrames.insert(j, region);
						walkLeftFrames.insert(j, region);
						walkRightFrames.insert(j, region);
				} else if (j == 1){
					walkDownFrames.insert(j, region);
					walkUpFrames.insert(j, region);
					walkLeftFrames.insert(j, region);
					walkRightFrames.insert(j, region);
				} else if (j == 2){
					walkDownFrames.insert(j, region);
					walkUpFrames.insert(j, region);
					walkLeftFrames.insert(j, region);
					walkRightFrames.insert(j, region);
				} else if (j == 3){
					walkDownFrames.insert(j, region);
					walkUpFrames.insert(j, region);
					walkLeftFrames.insert(j, region);
					walkRightFrames.insert(j, region);
				 }
			 }
			 Gdx.app.debug(TAG, "j = DONE" );
		// }
		 
		 _walkDownAnimation = new Animation (0.25f, walkDownFrames, Animation.PlayMode.LOOP);
		 _walkLeftAnimation = new Animation (0.25f, walkLeftFrames, Animation.PlayMode.LOOP);
		 _walkRightAnimation = new Animation (0.25f, walkRightFrames, Animation.PlayMode.LOOP);
		 _walkUpAnimation = new Animation (0.25f, walkUpFrames, Animation.PlayMode.LOOP);
	 }
	 
	 protected void updateAnimations(float delta) {
		 //want to avoid overrflow
		 _frameTime = (_frameTime + delta)%5;
		 
		 switch(_currentDirection) {
		 case DOWN:
			 if(_currentState == Entity.State.WALKING) {
				 Animation animation = _animations.get(Entity.AnimationType.WALK_DOWN);
			 if (animation == null) return;
			 _currentFrame = (TextureRegion) animation.getKeyFrame(_frameTime);
			 }else if (_currentState == Entity.State.IDLE) {
				 Animation animation = _animations.get(Entity.AnimationType.WALK_DOWN);
				 if(animation == null) return;
				 _currentFrame = (TextureRegion) animation.getKeyFrames()[0];
			 }else if(_currentState == Entity.State.IMMOBILE) {
				 Animation animation = _animations.get(Entity.AnimationType.IMMOBILE);
					if(animation == null) return;
					_currentFrame = (TextureRegion) animation.getKeyFrame(_frameTime);
			 }
			 break;
		 case LEFT:
			 if(_currentState == Entity.State.WALKING) {
				 Animation animation = _animations.get(Entity.AnimationType.WALK_LEFT);
			 if (animation == null) return;
			 _currentFrame = (TextureRegion) animation.getKeyFrame(_frameTime);
			 }else if (_currentState == Entity.State.IDLE) {
				 Animation animation = _animations.get(Entity.AnimationType.WALK_LEFT);
				 if(animation == null) return;
				 _currentFrame = (TextureRegion) animation.getKeyFrames()[0];
			 }else if(_currentState == Entity.State.IMMOBILE) {
				 Animation animation = _animations.get(Entity.AnimationType.IMMOBILE);
					if(animation == null) return;
					_currentFrame = (TextureRegion) animation.getKeyFrame(_frameTime);
			 }
			 break;
		 case UP:
			 if(_currentState == Entity.State.WALKING) {
				 Animation animation = _animations.get(Entity.AnimationType.WALK_UP);
			 if (animation == null) return;
			 _currentFrame = (TextureRegion) animation.getKeyFrame(_frameTime);
			 }else if (_currentState == Entity.State.IDLE) {
				 Animation animation = _animations.get(Entity.AnimationType.WALK_UP);
				 if(animation == null) return;
				 _currentFrame = (TextureRegion) animation.getKeyFrames()[0];
			 }else if(_currentState  == Entity.State.IMMOBILE) {
				 Animation animation = _animations.get(Entity.AnimationType.IMMOBILE);
					if(animation == null) return;
					_currentFrame = (TextureRegion) animation.getKeyFrame(_frameTime);
			 }
			 break;
		 case RIGHT:
			 if(_currentState == Entity.State.WALKING) {
				 Animation animation = _animations.get(Entity.AnimationType.WALK_RIGHT);
			 if (animation == null) return;
			 _currentFrame = (TextureRegion) animation.getKeyFrame(_frameTime);
			 }else if (_currentState == Entity.State.IDLE) {
				 Animation animation = _animations.get(Entity.AnimationType.WALK_RIGHT);
				 if(animation == null) return;
				 _currentFrame = (TextureRegion) animation.getKeyFrames()[0];
			 }else if(_currentState == Entity.State.IMMOBILE) {
				 Animation animation = _animations.get(Entity.AnimationType.IMMOBILE);
					if(animation == null) return;
					_currentFrame = (TextureRegion) animation.getKeyFrame(_frameTime);
			 }
			 break;
			 default:
				 break;
		 }
	 }
	 
	 protected Animation loadAnimation(String firstTexture, String secondTexture, Array<GridPoint2> points, float frameDuration) {
		 Utility.loadTextureAsset(firstTexture);
		 Texture texture1 = Utility.getTextureAsset(firstTexture);
		 Utility.loadTextureAsset(secondTexture);
		 Texture texture2 = Utility.getTextureAsset(secondTexture);
		 
		 TextureRegion[][] texture1Frames = TextureRegion.split(texture1, Entity.FRAME_WIDTH, Entity.FRAME_HEIGHT);
		 TextureRegion[][] texture2Frames = TextureRegion.split(texture2, Entity.FRAME_WIDTH, Entity.FRAME_HEIGHT);
		 Array<TextureRegion> animationKeyFrames = new Array<TextureRegion>(2);
		 GridPoint2 point = points.first();
		 
		 animationKeyFrames.add(texture1Frames[point.x][point.y]);
		 animationKeyFrames.add(texture2Frames[point.x][point.y]);
		 
		 return new Animation(frameDuration, animationKeyFrames, Animation.PlayMode.LOOP);
	 }
	 
	 protected Animation loadAnimation(String textureName, Array<GridPoint2> points, float frameDuration) {
		 Utility.loadTextureAsset(textureName);
		 Texture texture = Utility.getTextureAsset(textureName);
		 
		 TextureRegion[][] textureFrames = TextureRegion.split(texture, Entity.FRAME_WIDTH, Entity.FRAME_HEIGHT);
		 
		 Array<TextureRegion> animationKeyFrames = new Array<TextureRegion>(points.size);
		 
		 for(GridPoint2 point : points){
			 animationKeyFrames.add(textureFrames[point.x][point.y]);
		 }
		 return new Animation(frameDuration, animationKeyFrames, Animation.PlayMode.LOOP);
	 }
	 
	 
}

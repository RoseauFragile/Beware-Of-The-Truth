package bewareofthetruth.entity.components;


import java.util.UUID;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import bewareofthetruth.MapManager;
import bewareofthetruth.Utility;
import bewareofthetruth.entity.Entity;


public abstract class PhysicsComponent implements Component{


	private static final String TAG = PhysicsComponent.class.getSimpleName();

	//TODO a faire ici pour les sprites
	private static final String defaultSpritePath = "sprite/zombie_test.png";
	
	private Vector2 velocity;
	@SuppressWarnings("unused")
	private String entityID;
	
	private Direction currentDirection = Direction.LEFT;
	@SuppressWarnings("unused")
	private Direction previousDirection = Direction.UP;
	
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
	 
	 protected Vector2 nextEntityPosition;
	 protected Vector2 currentEntityPosition;
	 protected State state = State.IDLE;
	 protected float frameTime = 0f;
	 protected Sprite frameSprite = null;
	 protected TextureRegion _currentFrame = null;
	 
	 //TODO a faire ici pour les dimensions
	 public final int FRAME_WIDTH = 64;
	 public final int FRAME_HEIGHT = 64;
	 public static Rectangle _boundingBox;
	 protected BoundingBoxLocation _boundingBoxLocation;
	 
	 public static enum BoundingBoxLocation{
		 BOTTOM_LEFT,
		 BOTTOM_CENTER,
		 CENTER,
		 
	 }

	 public enum State {
		 IDLE, WALKING
	 }
	 

	 
	 public enum Direction {
		 UP, RIGHT, LEFT, DOWN;
		 }	 
	 
	 public PhysicsComponent() {
		 initPhysicsComponent();
	 }
	 
	 @SuppressWarnings("static-access")
	public void initPhysicsComponent() {
		 this.entityID = UUID.randomUUID().toString();
		 this.nextEntityPosition = new Vector2();
		 this.currentEntityPosition = new Vector2();
		 this._boundingBox = new Rectangle();
		 this.velocity = new Vector2(2f,2f);
		 
		 Utility.loadTextureAsset(defaultSpritePath);
		 loadDefaultSprite();
		 loadAllAnimations();
	 }
	 
	 public void update(Entity entity, MapManager mapManager, Batch batch, float delta) {
		 frameTime = (frameTime + delta)%5; //avoid overflow
		 setBoundingBoxSize(0f, 0.5f); //hitbox au pied
	 }	
	 
	 public void init(float startX, float startY) {
		 this.currentEntityPosition.x = startX;
		 this.currentEntityPosition.y = startY;
		 
		 this.nextEntityPosition.x = startX;
		 this.nextEntityPosition.y = startY;
	 }
	 
	 @SuppressWarnings("unused")
	public void setBoundingBoxSize(float percentageWidthReduced, float percentageHeightReduced) {
		 float width;
		 float height;
		 
		 float widthReductionAmount = 1.0f - percentageWidthReduced;
		//.8f for 20% (1 - .20)
		 float heightReductionAmount = 1.0f - percentageHeightReduced;
		//.8f for 20% (1 - .20)
		
		 if(widthReductionAmount > 0 && widthReductionAmount < 1) {
			 width = FRAME_WIDTH * widthReductionAmount;
		 } else {
			 width = FRAME_WIDTH;
		 }
		 
		 if(heightReductionAmount > 0 && heightReductionAmount < 1) {
			 height = FRAME_HEIGHT * heightReductionAmount;
		 } else {
			 height = FRAME_HEIGHT;
		 }
		 
		 if( width == 0 || height == 0) {
			 Gdx.app.debug(TAG, "Width and height are 0!! " + width +":"+ height);
		 }
		 
		//Need to account for the unitscale, since the map coordinates will be in pixels
		 float minX;
		 float minY;
		 if(MapManager.UNIT_SCALE > 0) {
			 minX = nextEntityPosition.x / MapManager.UNIT_SCALE;
			 minY = nextEntityPosition.y / MapManager.UNIT_SCALE;
		 } else {
			 minX = nextEntityPosition.x;
			 minY = nextEntityPosition.y;
		 }
		 _boundingBox.set(minX, minY, width, height);
	 }
	 
	 private void loadDefaultSprite() {
		 Texture texture = Utility.getTextureAsset(defaultSpritePath);
		 TextureRegion[][] textureFrames = TextureRegion.split(texture, FRAME_WIDTH, FRAME_WIDTH);
		 frameSprite = new Sprite(textureFrames[0][0].getTexture(),0,0,FRAME_WIDTH,FRAME_HEIGHT);
		 _currentFrame = textureFrames[0][0];
	 }
	 
	 //TODO � faire ici pour les animations
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
	 
	 public void dispose() {
		 Utility.unloadAsset(defaultSpritePath);
	 }
	 
	 public void setState(State state) {
		 this.state = state;
	 }
	 
	 public Sprite getFrameSprite() {
		 return frameSprite;
	 }
	 
	 public TextureRegion getFrame() {
		 return _currentFrame;
	 }
	 
	 public Vector2 getCurrentPosition() {
		 return currentEntityPosition;
	 }
	 
	 public void setCurrentPosition(float currentPositionX, float currentPositionY) {
		 frameSprite.setX(currentPositionX);
		 frameSprite.setY(currentPositionY);
		 this.currentEntityPosition.x = currentPositionX;
		 this.currentEntityPosition.y = currentPositionY;
	 }
	 
	 public void setDirection(Direction direction, float deltaTime) {
		 this.previousDirection = this.currentDirection;
		 this.currentDirection = direction;
		 //TODO a check si il y a un bug, les cast
		 switch(currentDirection) {
		 case DOWN :
			 _currentFrame = (TextureRegion) _walkDownAnimation.getKeyFrame(frameTime);
			 break;
		 case LEFT :
			 _currentFrame = (TextureRegion) _walkLeftAnimation.getKeyFrame(frameTime);
			 break;
		 case UP :
			 _currentFrame = (TextureRegion) _walkUpAnimation.getKeyFrame(frameTime);
			 break;
		 case RIGHT :
			 _currentFrame = (TextureRegion) _walkRightAnimation.getKeyFrame(frameTime);
			 break;
		 default:
			 break;	 
		 }
	 }
	 
	 public void setNextPositionToCurrent(Entity entity) {
		 setCurrentPosition(entity.nextPlayerPosition.x,entity.nextPlayerPosition.y);
	 }
	 
	 public void calculateNextPosition(Direction currentDirection, float deltaTime) {
		 float testX = currentEntityPosition.x;
		 float testY = currentEntityPosition.y;
		 velocity.scl(deltaTime);
		 
		 switch(currentDirection) {
		 case LEFT:
			 //Gdx.app.debug(TAG, "touche gauche " + currentDirection);
			 testX -= velocity.x;
			 break;
		 case RIGHT:
			 //Gdx.app.debug(TAG, "touche droite " + currentDirection);
			 testX += velocity.x;
			 break;
		 case UP:
			// Gdx.app.debug(TAG, "touche haut " + currentDirection);

			 testY += velocity.y;
			 break;
		 case DOWN:
			 //Gdx.app.debug(TAG, "touche bas " + currentDirection);
			 testY -= velocity.y;
			 break;
		 default:
			 break;
		 }
		 
		 nextEntityPosition.x = testX;
		 nextEntityPosition.y = testY;
		 
		 velocity.scl(1 / deltaTime);
	 }
	 
	 protected boolean isCollisionWithMapEntities(Entity entity, MapManager mapManager) {
		 Array<Entity> entities = mapManager.getCurrentMapEntites();
		 boolean isCollisionWithMapEntities = false;
		 
		 for(Entity mapEntity : entities) {
			 //check for testing again self
			 if(mapEntity.equals(entity)) {
				 continue;
			 }
			 
			 Rectangle targetRect = mapEntity.getCurrentBoundingBox();
			 if(this._boundingBox.overlaps(targetRect)) {
				 //Collision
				 entity.sendMessage(MESSAGE.COLLISION_WITH_ENTITY);
				 isCollisionWithMapEntities = true;
				 break;
			 }
		 }
		 return isCollisionWithMapEntities;
	 }
	 
	 protected boolean isCollision(Entity entitySource, Entity entityTarget) {
		 boolean isCollisionWithMapEntities = false;
		 
		 if( entitySource.equals(entityTarget)) {
			 return false;
		 }
		 
		 if(entitySource.getCurrentBoundingBox().overlaps(entityTarget.getCurrentBoundingBox())) {
			 //Collsion
			 entitySource.sendMessage(MESSAGE.COLLISION_WITH_ENTITY);
			 isCollisionWithMapEntities = true;
		 }
		 return isCollisionWithMapEntities;
	 }
	 
		protected boolean isCollisionWithMapLayer (Entity entity, MapManager mapManager) {
			MapLayer mapCollisionLayer = mapManager.getCollisionLayer();
			
			if(mapCollisionLayer == null) {
				return false;
			}
			
			Rectangle rectangle = null;
			
			for(MapObject object : mapCollisionLayer.getObjects()) {
				if(object instanceof RectangleMapObject) {
					rectangle = ((RectangleMapObject)object).getRectangle();
					if(entity.boundingBox.overlaps(rectangle)) {
						return true;
					}
				}
			}
			return false;
		}
		
		protected void initBoundingBox(float percentageWidthReduced, float percentageHeightReduced) {
			
			float width;
		 	float height;
		 	
		 	float origWidth = Entity.FRAME_WIDTH;
		 	float origHeight = Entity.FRAME_HEIGHT;
		 	
		 	float widthReductionAmount = 1.0f - percentageWidthReduced;
		 	float heightReductionAmount = 1.0f - percentageHeightReduced;
		 	
		 	if( widthReductionAmount > 0 && widthReductionAmount < 1) {
		 		width = Entity.FRAME_WIDTH * widthReductionAmount;
		 	}else {
		 		width = Entity.FRAME_WIDTH;
		 	}
		 	
		 	if(heightReductionAmount >  0 && heightReductionAmount < 1) {
		 		height = Entity.FRAME_HEIGHT * heightReductionAmount;
		 	}else {
		 		height = Entity.FRAME_HEIGHT;
		 	}
		 	
		 	if (width == 0 || height ==0) {
		 		Gdx.app.debug(TAG, "Width and Height are 0!!" + width + ":" + height);
		 	}
		 	
		 	//Need to account for the unitscale, since the map coordinates will be in pixels
		 	float minX;
		 	float minY;
		 	
		 	if(Map.UNIT_SCALE > 0) {
		 		minX = nextEntityPosition.x / Map.UNIT_SCALE;
		 		minY = nextEntityPosition.Y / Map.UNIT_SCALE;
		 	} else {
		 		minX = nextEntityPosition.x;
		 		minY = nextEntityPosition.y;
		 	}
		 	
		 	_boundingBox.setWidth(width);
		 	_boundingBox.setHeight(height);
		 	
		 	switch(_boundingBoxLocation) {
		 	case BOTTOM_LEFT:
		 		_boundingBox.set(minX, minY, width, height);
		 		break;
		 	case BOTTOM_CENTER:
		 		_boundingBox.setCenter(minX + origWidth/2, minY + origHeight/4);
		 		break;
		 	case CENTER:
		 		_boundingBox.setCenter(minX + origWidth/2, minY + origHeight/2);
		 		break;
		 	
		 	}
		}
		
		protected void updateBoundingBoxPosition(Vector2 position) {
			float minX;
		 float minY;
		 
		 if(Map.UNIT_SCALE > 0) {
			 minX = position.x / Map.UNIT_SCALE:
				 minY = position.y / Map.UNIT_SCALE;
		 }else {
			 minX = position.x;
			 minY = position.y;
		 }
		 
		 switch(_boundingBoxLocation) {
		 case BOTTOM_LEFT:
			 _boundingBox.set(minX, minY, _boundingBox.getWidth(), _boundingBox.getHeight());
			 break;
		 case BOTTOM_CENTER:
			 _boundingBox.setCenter(minX + Entity.FRAME_WIDTH/2, minY + Entity.FRAME_HEIGHT / 4);
			 break;
		 case CENTER:
			 _boundingBox.setCenter(minX + Entity.FRAME_WIDTH/2, minY+Entity.FRAME_HEIGHT/2);
			 break;
		 }
		}
}


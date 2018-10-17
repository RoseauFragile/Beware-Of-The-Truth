package bewareofthetruth.entity.components;


import java.util.UUID;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;

import bewareofthetruth.MapManager;
import bewareofthetruth.Utility;


public abstract class PhysicsComponent implements Component{


	private static final String TAG = PhysicsComponent.class.getSimpleName();

	//TODO a faire ici pour les sprites
	private static final String defaultSpritePath = "sprite/zombie_test.png";
	
	 private Direction _previousDirection = Direction.UP;

	 
	 protected Vector2 _nextEntityPosition;
	 protected Vector2 _currentEntityPosition;
	 protected Entity.Direction  _currentDirection;
	 protected Json _json;
	 protected Vector2 _velocity;
	 
	 protected Array<Entity> _tempEntities;
	 
	 public Rectangle _boundingBox;
	 protected BoundingBoxLocation _boundingBoxLocation;
	 protected Ray _selectionRay;
	 protected final float _selectRayMaximumDistance = 32.0f;
	 
	 //OK
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
		 /*this.entityID = UUID.randomUUID().toString();
		 this._nextEntityPosition = new Vector2();
		 this._currentEntityPosition = new Vector2();
		 this._boundingBox = new Rectangle();
		 this.velocity = new Vector2(2f,2f);*/
		 
	        this._nextEntityPosition = new Vector2(0,0);
	        this._currentEntityPosition = new Vector2(0,0);
	        this._velocity = new Vector2(2f,2f);
	        this._boundingBox = new Rectangle();
	        this._json = new Json();
	        this._tempEntities = new Array<Entity>();
	        _boundingBoxLocation = BoundingBoxLocation.BOTTOM_LEFT;
	        _selectionRay = new Ray(new Vector3(), new Vector3());
	 }
	 
	 //OK
	 public abstract void update(Entity entity, MapManager mapManager, float delta);	
	 
	 public void init(float startX, float startY) {
		 this._currentEntityPosition.x = startX;
		 this._currentEntityPosition.y = startY;
		 this._nextEntityPosition.x = startX;
		 this._nextEntityPosition.y = startY;
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
			 width = Entity.FRAME_WIDTH * widthReductionAmount;
		 } else {
			 width = Entity.FRAME_WIDTH;
		 }
		 
		 if(heightReductionAmount > 0 && heightReductionAmount < 1) {
			 height = Entity.FRAME_HEIGHT * heightReductionAmount;
		 } else {
			 height = Entity.FRAME_HEIGHT;
		 }
		 
		 if( width == 0 || height == 0) {
			 Gdx.app.debug(TAG, "Width and height are 0!! " + width +":"+ height);
		 }
		 
		//Need to account for the unitscale, since the map coordinates will be in pixels
		 float minX;
		 float minY;
		 if(MapManager.UNIT_SCALE > 0) {
			 minX = _nextEntityPosition.x / MapManager.UNIT_SCALE;
			 minY = _nextEntityPosition.y / MapManager.UNIT_SCALE;
		 } else {
			 minX = _nextEntityPosition.x;
			 minY = _nextEntityPosition.y;
		 }
		 _boundingBox.set(minX, minY, width, height);
	 }
	
	 
	 public void dispose() {
		 Utility.unloadAsset(defaultSpritePath);
	 }
	 
	 public Vector2 getCurrentPosition() {
		 return _currentEntityPosition;
	 }
	
	  
	 //OK
	 //TODO si bug position regarder ici
	 public void setNextPositionToCurrent(Entity entity) {
		 
	        this._currentEntityPosition.x = _nextEntityPosition.x;
	        this._currentEntityPosition.y = _nextEntityPosition.y;
	        Gdx.app.debug(TAG, "SETTING Current Position " + entity.getEntityConfig().getEntityID() + ": (" + _currentEntityPosition.x + "," + _currentEntityPosition.y + ")");
	        entity.sendMessage(MESSAGE.CURRENT_POSITION, _json.toJson(_currentEntityPosition));
	 }
	 
	 //OK
	 //TODO si bug vitesse regarder ici
	 public void calculateNextPosition(Direction currentDirection, float deltaTime) {
		 float testX = _currentEntityPosition.x;
		 float testY = _currentEntityPosition.y;
		 _velocity.scl(deltaTime);
		 
		 switch(currentDirection) {
		 case LEFT:
			 //Gdx.app.debug(TAG, "touche gauche " + currentDirection);
			 testX -= _velocity.x;
			 break;
		 case RIGHT:
			 //Gdx.app.debug(TAG, "touche droite " + currentDirection);
			 testX += _velocity.x;
			 break;
		 case UP:
			// Gdx.app.debug(TAG, "touche haut " + currentDirection);

			 testY += _velocity.y;
			 break;
		 case DOWN:
			 //Gdx.app.debug(TAG, "touche bas " + currentDirection);
			 testY -= _velocity.y;
			 break;
		 default:
			 break;
		 }
		 
		 _nextEntityPosition.x = testX;
		 _nextEntityPosition.y = testY;
		 
		 _velocity.scl(1 / deltaTime);
	 }
	 
	 //OK
	 //TODO Régler ça
	 protected boolean isCollisionWithMapEntities(Entity entity, MapManager mapManager) {
		 Array<Entity> entities = mapManager.getCurrentMapEntities();
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
	 
	 //OK
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
	 
	 //OK
	protected boolean isCollisionWithMapLayer (Entity entity, MapManager mapManager) {
			MapLayer mapCollisionLayer = mapManager.getCollisionLayer();
			
			if(mapCollisionLayer == null) {
				return false;
			}
			
			Rectangle rectangle = null;
			
			for(MapObject object : mapCollisionLayer.getObjects()) {
				if(object instanceof RectangleMapObject) {
					rectangle = ((RectangleMapObject)object).getRectangle();
					//TODO si bug COllision regarder ici
					if(_boundingBox.overlaps(rectangle)) {
						return true;
					}
				}
			}
			return false;
		}
		
	//OK
	//TODO a régler
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
		 	
		 	if(MapManager.UNIT_SCALE > 0) {
		 		minX = _nextEntityPosition.x / MapManager.UNIT_SCALE;
		 		minY = _nextEntityPosition.y / MapManager.UNIT_SCALE;
		 	} else {
		 		minX = _nextEntityPosition.x;
		 		minY = _nextEntityPosition.y;
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
		
		//OK 
		//TODO a régler
		protected void updateBoundingBoxPosition(Vector2 position) {
			float minX;
		 float minY;
		 
		 if(MapManager.UNIT_SCALE > 0) {
			 minX = position.x / MapManager.UNIT_SCALE;
				 minY = position.y / MapManager.UNIT_SCALE;
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


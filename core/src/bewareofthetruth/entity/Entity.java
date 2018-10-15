package bewareofthetruth.entity;

import java.util.ArrayList;
import java.util.UUID;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.JsonValue;
import bewareofthetruth.MapManager;
import bewareofthetruth.Utility;
import bewareofthetruth.entity.components.Component; 

public class Entity {

	private static final String TAG = Entity.class.getSimpleName();
	private Json json;
	private EntityConfig entityConfig;
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
	 
	 public Vector2 nextPlayerPosition;
	 public Vector2 currentPlayerPosition;
	 protected State state = State.IDLE;
	 protected float frameTime = 0f;
	 protected Sprite frameSprite = null;
	 protected TextureRegion _currentFrame = null;
	 
	 //TODO a faire ici pour les dimensions
	 public final static int FRAME_WIDTH = 64;
	 public final static int FRAME_HEIGHT = 64;
	 public static Rectangle boundingBox;
	 
	 private static final int MAX_COMPONENTS = 5;
	 private Array<Component> components;
	 
	 private InputComponent inputComponent;
	 private GraphicsComponent graphicsComponent;
	 private PhysicsComponent physicsComponent;
	 
	 public enum State {
		 IDLE, WALKING, IMMOBILE;
		 
		 public static State getRandomNext() {
			 return State.values()[MathUtils.random(State.values().length - 2)];
		 }
	 }
	 
	 public static enum AnimationType {
		 WALK_LEFT,
		 WALK_RIGHT,
		 WALK_UP,
		 WALK_DOWN,
		 IDLE,
		 IMMOBILE
	 }
	 
	 public enum Direction {
		 UP, RIGHT, LEFT, DOWN;
		 
		 public static Direction getRandomNext() {
			 return Direction.values()[MathUtils.random(Direction.values().length - 1)];
		 }
	 
			 public Direction getOpposite() {
				 if ( this == LEFT) {
					 return RIGHT;
				 }else if( this == RIGHT) {
			 return LEFT;
				 }else if( this == UP) {
					 return DOWN;
				 } else {
					 return UP;
				 }
			 }
		 }	 
	 
	 public Entity(InputComponent inputComponent, PhysicsComponent physicsComponent, GraphicsComponent graphicsComponent) {
		 this.entityConfig = new EntityConfig();
		 this.json = new Json();
		 
		 this.components = new Array<Component>(MAX_COMPONENTS);
		 
		 this.inputComponent = inputComponent;
		 this.physicsComponent = physicsComponent;
		 this.graphicsComponent = graphicsComponent;
		 
		 this.components.add(this.inputComponent);
		 this.components.add(this.physicsComponent);
		 this.components.add(this.graphicsComponent);
		 //initEntity();
	 }
	 
	 public EntityConfig getEntityCOnfig() {
		 return entityConfig;
	 }
	 
	 public void sendMessage(Component.MESSAGE messageType, String...args ) {
		 String fullMessage = messageType.toString();
		 
		 for(String string : args) {
			 fullMessage += Component.MESSAGE_TOKEN + string;
		 }
		 
		 for(Component component : components) {
			 component.receiveMessage(fullMessage);
		 }
	 }
	 
	 @SuppressWarnings("static-access")
	public void initEntity() {
		 this.entityID = UUID.randomUUID().toString();
		 this.nextPlayerPosition = new Vector2();
		 this.currentPlayerPosition = new Vector2();
		 this.boundingBox = new Rectangle();
		 this.velocity = new Vector2(2f,2f);
		 
		 Utility.loadTextureAsset(defaultSpritePath);
		 loadDefaultSprite();
		 loadAllAnimations();
	 }
	 
	 public void update(MapManager mapManager, Batch batch, float delta) {
		 inputComponent.update(this, delta);
		 physicsComponent.update(this, mapManager, delta);
		 graphicsComponent.update(this, mapManager, batch, delta);
		 /*frameTime = (frameTime + delta)%5; //avoid overflow
		 setBoundingBoxSize(0f, 0.5f); //hitbox au pied*/
	 }	
	 
	 public void init(float startX, float startY) {
		 this.currentPlayerPosition.x = startX;
		 this.currentPlayerPosition.y = startY;
		 
		 this.nextPlayerPosition.x = startX;
		 this.nextPlayerPosition.y = startY;
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
			 minX = nextPlayerPosition.x / MapManager.UNIT_SCALE;
			 minY = nextPlayerPosition.y / MapManager.UNIT_SCALE;
		 } else {
			 minX = nextPlayerPosition.x;
			 minY = nextPlayerPosition.y;
		 }
		 boundingBox.set(minX, minY, width, height);
	 }
	 
	 private void loadDefaultSprite() {
		 Texture texture = Utility.getTextureAsset(defaultSpritePath);
		 TextureRegion[][] textureFrames = TextureRegion.split(texture, FRAME_WIDTH, FRAME_WIDTH);
		 frameSprite = new Sprite(textureFrames[0][0].getTexture(),0,0,FRAME_WIDTH,FRAME_HEIGHT);
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
	 
	 public void dispose() {
		 //Utility.unloadAsset(defaultSpritePath);
		 for(Component component : components) {
			 component.dispose();
		 }
	 }
	 
	 public void setEntityConfig(EntityConfig entityConfig) {
		 this.entityConfig = entityConfig;
	 }
	 
	 public static EntityConfig getEntityConfig(String configFilePath) {
		 Json json = new Json();
		 return json.fromJson(EntityConfig.class, Gdx.files.internal(configFilePath));
	 }
	 
	 public static Array<EntityConfig> getEntityConfigs(String configFilePath){
		 Json json = new Json();
		 Array<EntityConfig> configs = new Array<EntityConfig>();
		 ArrayList<JsonValue> list = json.fromJson(ArrayList.class, Gdx.files.internal(configFilePath));
		 for(JsonValue jsonVal : list) {
			 configs.add(json.readValue(EntityConfig.class, jsonVal));
		 }
		 return configs;
	 }

	 
	 public Rectangle getCurrentBoundingBox() {
		 return physicsComponent.boundingBox;
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
		 return currentPlayerPosition;
	 }
	 
	 public void setCurrentPosition(float currentPositionX, float currentPositionY) {
		 frameSprite.setX(currentPositionX);
		 frameSprite.setY(currentPositionY);
		 this.currentPlayerPosition.x = currentPositionX;
		 this.currentPlayerPosition.y = currentPositionY;
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
	 
	 public void setNextPositionToCurrent() {
		 setCurrentPosition(nextPlayerPosition.x,nextPlayerPosition.y);
	 }
	 
	 public void calculateNextPosition(Direction currentDirection, float deltaTime) {
		 float testX = currentPlayerPosition.x;
		 float testY = currentPlayerPosition.y;
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
		 
		 nextPlayerPosition.x = testX;
		 nextPlayerPosition.y = testY;
		 
		 velocity.scl(1 / deltaTime);
	 }
}

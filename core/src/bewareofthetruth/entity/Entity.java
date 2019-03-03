package bewareofthetruth.entity;

import java.util.ArrayList;
import java.util.Hashtable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import bewareofthetruth.entity.components.Component;
import bewareofthetruth.entity.components.ComponentObserver;
import bewareofthetruth.entity.components.GraphicsComponent;
import bewareofthetruth.entity.components.InputComponent;
import bewareofthetruth.entity.components.PhysicsComponent;
import bewareofthetruth.map.MapManager;
import bewareofthetruth.profile.ProfileManager;

public class Entity {
	private static final String TAG = Entity.class.getSimpleName();

	public static enum Direction {
		UP,
		RIGHT,
		DOWN,
		LEFT,
		UP_RIGHT,
		UP_LEFT,
		DOWN_RIGHT,
		DOWN_LEFT;


		static public Direction getRandomNext() {
			return Direction.values()[MathUtils.random(Direction.values().length - 1)];
		}

		public Direction getOpposite() {
			if( this == LEFT){
				return RIGHT;
			}else if( this == RIGHT){
				return LEFT;
			}else if( this == UP){
				return DOWN;
			}else{
				return UP;
			}
		}
	}

	public static enum State {
		IDLE,
		WALKING,
		ROLL,
		IMMOBILE;//This should always be last

		static public State getRandomNext() {
			//Ignore IMMOBILE which should be last state
			return State.values()[MathUtils.random(State.values().length - 2)];
		}
	}

	public static enum AnimationType {
		WALK_LEFT,
		WALK_RIGHT,
		WALK_UP,
		WALK_DOWN,
		ROLL_LEFT,
		ROLL_RIGHT,
		IDLE,
		IMMOBILE
	}

	public static final int FRAME_WIDTH = 64;
	public static final int FRAME_HEIGHT = 64;
	private static final int MAX_COMPONENTS = 5;

	private Json _json;
	private EntityConfig _entityConfig;
	private Array<Component> _components;
	private InputComponent _inputComponent;
	private GraphicsComponent _graphicsComponent;
	private PhysicsComponent _physicsComponent;

	public Entity(Entity entity){
		set(entity);
	}

	private Entity set(Entity entity) {
		_inputComponent = entity._inputComponent;
		_graphicsComponent = entity._graphicsComponent;
		_physicsComponent = entity._physicsComponent;

		if( _components == null ){
			_components = new Array<Component>(MAX_COMPONENTS);
		}
		_components.clear();
		_components.add(_inputComponent);
		_components.add(_physicsComponent);
		_components.add(_graphicsComponent);

		_json = entity._json;

		_entityConfig = new EntityConfig(entity._entityConfig);
		return this;
	}

	public Entity(InputComponent inputComponent, PhysicsComponent physicsComponent, GraphicsComponent graphicsComponent){
		_entityConfig = new EntityConfig();
		_json = new Json();

		_components = new Array<Component>(MAX_COMPONENTS);

		_inputComponent = inputComponent;
		_physicsComponent = physicsComponent;
		_graphicsComponent = graphicsComponent;

		_components.add(_inputComponent);
		_components.add(_physicsComponent);
		_components.add(_graphicsComponent);
	}

	public EntityConfig getEntityConfig() {
		return _entityConfig;
	}

	public void sendMessage(Component.MESSAGE messageType, String ... args){
		String fullMessage = messageType.toString();

		for (final String string : args) {
			fullMessage += Component.MESSAGE_TOKEN + string;
		}

		for(final Component component: _components){
			component.receiveMessage(fullMessage);
		}
	}

	public void registerObserver(ComponentObserver observer){
		_inputComponent.addObserver(observer);
		_physicsComponent.addObserver(observer);
		_graphicsComponent.addObserver(observer);
	}

	public void unregisterObservers(){
		_inputComponent.removeAllObservers();
		_physicsComponent.removeAllObservers();
		_graphicsComponent.removeAllObservers();
	}

	public void update(MapManager mapMgr, Batch batch, float delta){
		_inputComponent.update(this, delta);
		_physicsComponent.update(this, mapMgr, delta);
		_graphicsComponent.update(this, mapMgr, batch, delta);
	}

	public void updateInput(float delta){
		_inputComponent.update(this, delta);
	}

	public void dispose(){
		for(final Component component: _components){
			component.dispose();
		}
	}

	public Rectangle getCurrentBoundingBox(){
		return _physicsComponent._boundingBox;
	}

	public Vector2 getCurrentPosition(){
		return _graphicsComponent._currentPosition;
	}

	public InputProcessor getInputProcessor(){
		return _inputComponent;
	}

	public void setEntityConfig(EntityConfig entityConfig){
		_entityConfig = entityConfig;
	}

	public Animation<TextureRegion> getAnimation(Entity.AnimationType type){
		return _graphicsComponent.getAnimation(type);
	}

	static public EntityConfig getEntityConfig(String configFilePath){
		final Json json = new Json();
		return json.fromJson(EntityConfig.class, Gdx.files.internal(configFilePath));
	}

	static public Array<EntityConfig> getEntityConfigs(String configFilePath){
		final Json json = new Json();
		final Array<EntityConfig> configs = new Array<EntityConfig>();

		final ArrayList<JsonValue> list = json.fromJson(ArrayList.class, Gdx.files.internal(configFilePath));

		for (final JsonValue jsonVal : list) {
			configs.add(json.readValue(EntityConfig.class, jsonVal));
		}

		return configs;
	}

	public static EntityConfig loadEntityConfigByPath(String entityConfigPath){
		final EntityConfig entityConfig = Entity.getEntityConfig(entityConfigPath);
		final EntityConfig serializedConfig = ProfileManager.getInstance().getProperty(entityConfig.getEntityID(), EntityConfig.class);

		if( serializedConfig == null ){
			return entityConfig;
		}else{
			return serializedConfig;
		}
	}

	public static EntityConfig loadEntityConfig(EntityConfig entityConfig){
		final EntityConfig serializedConfig = ProfileManager.getInstance().getProperty(entityConfig.getEntityID(), EntityConfig.class);

		if( serializedConfig == null ){
			return entityConfig;
		}else{
			return serializedConfig;
		}
	}

	public static Entity initEntity(EntityConfig entityConfig, Vector2 position){
		final Json json = new Json();
		final Entity entity = EntityFactory.getEntity(EntityFactory.EntityType.NPC);
		entity.setEntityConfig(entityConfig);

		entity.sendMessage(Component.MESSAGE.LOAD_ANIMATIONS, json.toJson(entity.getEntityConfig()));
		entity.sendMessage(Component.MESSAGE.INIT_START_POSITION, json.toJson(position));
		entity.sendMessage(Component.MESSAGE.INIT_STATE, json.toJson(entity.getEntityConfig().getState()));
		entity.sendMessage(Component.MESSAGE.INIT_DIRECTION, json.toJson(entity.getEntityConfig().getDirection()));

		return entity;
	}

	public static Hashtable<String, Entity> initEntities(Array<EntityConfig> configs){
		final Json json = new Json();
		final Hashtable<String, Entity > entities = new Hashtable<String, Entity>();
		for( final EntityConfig config: configs ){
			final Entity entity = EntityFactory.getEntity(EntityFactory.EntityType.NPC);

			entity.setEntityConfig(config);
			entity.sendMessage(Component.MESSAGE.LOAD_ANIMATIONS, json.toJson(entity.getEntityConfig()));
			entity.sendMessage(Component.MESSAGE.INIT_START_POSITION, json.toJson(new Vector2(0,0)));
			entity.sendMessage(Component.MESSAGE.INIT_STATE, json.toJson(entity.getEntityConfig().getState()));
			entity.sendMessage(Component.MESSAGE.INIT_DIRECTION, json.toJson(entity.getEntityConfig().getDirection()));

			entities.put(entity.getEntityConfig().getEntityID(), entity);
		}

		return entities;
	}

	public static Entity initEntity(EntityConfig entityConfig){
		final Json json = new Json();
		final Entity entity = EntityFactory.getEntity(EntityFactory.EntityType.NPC);
		entity.setEntityConfig(entityConfig);

		entity.sendMessage(Component.MESSAGE.LOAD_ANIMATIONS, json.toJson(entity.getEntityConfig()));
		entity.sendMessage(Component.MESSAGE.INIT_START_POSITION, json.toJson(new Vector2(0,0)));
		entity.sendMessage(Component.MESSAGE.INIT_STATE, json.toJson(entity.getEntityConfig().getState()));
		entity.sendMessage(Component.MESSAGE.INIT_DIRECTION, json.toJson(entity.getEntityConfig().getDirection()));

		return entity;
	}

	public InputComponent get_inputComponent() {
		return _inputComponent;
	}

	public void set_inputComponent(InputComponent _inputComponent) {
		this._inputComponent = _inputComponent;
	}

	public GraphicsComponent get_graphicsComponent() {
		return _graphicsComponent;
	}

	public void set_graphicsComponent(GraphicsComponent _graphicsComponent) {
		this._graphicsComponent = _graphicsComponent;
	}

	public PhysicsComponent get_physicsComponent() {
		return _physicsComponent;
	}

	public void set_physicsComponent(PhysicsComponent _physicsComponent) {
		this._physicsComponent = _physicsComponent;
	}

}
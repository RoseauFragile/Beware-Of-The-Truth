package bewareofthetruth.entity.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import bewareofthetruth.MapManager;
import java.util.ArrayList;

public class Entity{
	private static final String TAG = Entity.class.getSimpleName();
	private Json _json;
	private EntityConfig _entityConfig;
	
	public static enum Direction{
		UP,
		RIGHT,
		DOWN,
		LEFT;
		
		static public Direction getrandomNext() {
			return Direction.values()[MathUtils.random(Direction.values().length - 1 )];
		}
		
		public Direction getOpposite() {
			if ( this == LEFT) {
				return RIGHT;
			}else if( this== RIGHT) {
				return LEFT;
			}else if(this == UP) {
				return DOWN;
			}else {
				return UP;
			}
		}
	}
	
	public static enum State {
		IDLE,
		WALKING,
		IMMOBILE;
		
		static public State getRandomNext() {
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
	
	public static final int FRAME_WIDTH = 64;
	public static final int FRAME_HEIGHT = 64;
	
	private static final int MAX_COMPONENTS = 5;
	private Array<Component> _components;
	
	private InputComponent _inputComponent;
	private GraphicsComponent _graphicsComponent;
	private PhysicsComponent _physicsComponent;
	
	public Entity(InputComponent inputComponent, PhysicsComponent physicsComponent, GraphicsComponent graphicsComponent) {
		
		_entityConfig = new EntityConfig();
		_json = new Json();
		
		_components = new Array<Component>(MAX_COMPONENTS);
		
		_inputComponent = inputComponent;
		_graphicsComponent = graphicsComponent;
		_physicsComponent = physicsComponent;
		
		_components.add(_inputComponent);
		_components.add(physicsComponent);
		_components.add(graphicsComponent);
	}
	
	public EntityConfig getEntityConfig() {
		return _entityConfig;
	}
	
	public void sendMessage(Component.MESSAGE messageType, String...args) {
		String fullMessage = messageType.toString();
		
		for(String string :args) {
			fullMessage += Component.MESSAGE_TOKEN + string;
		}
		
		for (Component component : _components) {
			component.receiveMessage(fullMessage);
		}
	}
	
	public void update(MapManager mapManager, Batch batch, float delta) {
		_inputComponent.update(this, delta);
		_physicsComponent.update(this, mapManager, delta);
		_graphicsComponent.update(this, mapManager, batch, delta);
	}
	public void dispose() {
		for(Component component: _components) {
			component.dispose();
		}
	}
	
	//TODO faire attention c'est le physicsComponent qui contient la bounding box
	public Rectangle getCurrentBoundingBox() {
		return _physicsComponent._boundingBox;
	}
	
	public void setEntityConfig(EntityConfig entityConfig) {
		this._entityConfig = entityConfig;
	}
	
	static public EntityConfig getEntityConfig(String configFilePath) {
		Json json = new Json();
		return json.fromJson(EntityConfig.class, Gdx.files.internal(configFilePath));
	}
	
	static public Array<EntityConfig> getEntityConfigs(String configFilePath){
		Json json = new Json();
		Array<EntityConfig> configs = new Array<EntityConfig>();
		
		ArrayList<JsonValue> list = json.fromJson(ArrayList.class, Gdx.files.internal(configFilePath));
	
		for(JsonValue jsonVal : list) {
			configs.add(json.readValue(EntityConfig.class, jsonVal));
		}
		return configs;
	}
	
}

package bewareofthetruth.entity.components;

import com.badlogic.gdx.utils.Json;
import java.util.HashMap;
import java.util.Map;

public abstract class InputComponent implements Component {
	
	protected Entity.Direction _currentDirection = null;
	protected Entity.State _currentState = null;
	protected Json _json;
	
	protected enum Keys{
		LEFT,RIGHT,UP,DOWN,QUIT
	}
	
	protected enum Mouse{
		SELECT, DOACTION
	}
	
	protected static Map<Keys, Boolean> keys = new HashMap<Keys, Boolean>();
	protected static Map<Mouse, Boolean> mouseButtons = new HashMap<Mouse, Boolean>();
	
	static {
		keys.put(Keys.LEFT, false);
		keys.put(Keys.RIGHT, false);
		keys.put(Keys.UP, false);
		keys.put(Keys.DOWN, false);
		keys.put(Keys.QUIT, false);
	};
	
	static {
		mouseButtons.put(Mouse.SELECT, false);
		mouseButtons.put(Mouse.DOACTION, false);
	};
	
	InputComponent(){
		_json = new Json();
	}
	
	public abstract void update(Entity entity, float delta);

}

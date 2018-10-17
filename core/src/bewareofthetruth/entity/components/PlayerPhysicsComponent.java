package bewareofthetruth.entity.components;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;

import bewareofthetruth.MapManager;

public class PlayerPhysicsComponent extends PhysicsComponent{

	private Entity.State _state;
	private Vector3 _mouseSelectCoordinates;
	private boolean _isMouseSelectEnabled = false;
	private Ray _selectionRay;
	private float _selectRayMaximumDistance = 32.0f;
	
	public PlayerPhysicsComponent() {
		_mouseSelectCoordinates = new Vector3(0,0,0);
		_selectionRay = new Ray(new Vector3(), new Vector3());
	}
	
	public void dispose() {
		
	}
	
	@Override
	public void receiveMessage(String message) {
		String[] string = message.split(Component.MESSAGE_TOKEN);
		
		if( string.length == 0) return;
		
		if (string[0].equalsIgnoreCase(MESSAGE.INIT_START_POSITION.toString())) {
			_currentEntityPosition = _json.fromJson(Vector2.class, string [1]);
			_nextEntityPosition.set(_currentEntityPosition.x, _currentEntityPosition.y);
		}else if(string[0].equalsIgnoreCase(MESSAGE.CURRENT_STATE.toString())) {
			_state = _json.fromJson(Entity.State.class, string[1]);
		}else if(string[0].equalsIgnoreCase(MESSAGE.CURRENT_DIRECTION.toString())) {
			_currentDirection = _json.fromJson(Entity.Direction.class,string[1]);
		}else if(string[0].equalsIgnoreCase(MESSAGE.INIT_SELECT_ENTITY.toString())) {
			_mouseSelectCoordinates = _json.fromJson(Vector3.class, string[1]);
			_isMouseSelectEnabled = true;
		}
	}

	@Override
	public void update(Entity entity, MapManager mapManager, float delta) {
	}

}

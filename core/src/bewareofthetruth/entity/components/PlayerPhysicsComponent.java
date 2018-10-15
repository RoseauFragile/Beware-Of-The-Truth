package bewareofthetruth.entity.components;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;

import bewareofthetruth.MapManager;

public class PlayerPhysicsComponent extends PhysicsComponent{

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
		}
	}

	@Override
	public void update(Entity entity, MapManager mapManager, float delta) {
	}

}

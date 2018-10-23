package bewareofthetruth.entity.components;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.Json;

import bewareofthetruth.entity.Entity;
import bewareofthetruth.entity.Entity.Direction;
import bewareofthetruth.entity.Entity.State;

import java.util.HashMap;
import java.util.Map;

public abstract class InputComponent extends ComponentSubject implements Component, InputProcessor {

    protected Entity.Direction _currentDirection = null;
    protected Entity.State _currentState = null;
    protected Json _json;

    public enum Keys {
        LEFT, RIGHT, UP, DOWN, QUIT, PAUSE
    }

    public enum Mouse {
        SELECT, DOACTION
    }

    protected static Map<Keys, Boolean> keys = new HashMap<Keys, Boolean>();
    protected static Map<Mouse, Boolean> mouseButtons = new HashMap<Mouse, Boolean>();

    //initialize the hashmap for inputs
    static {
        keys.put(Keys.LEFT, false);
        keys.put(Keys.RIGHT, false);
        keys.put(Keys.UP, false);
        keys.put(Keys.DOWN, false);
        keys.put(Keys.QUIT, false);
        keys.put(Keys.PAUSE, false);
    };

    static {
        mouseButtons.put(Mouse.SELECT, false);
        mouseButtons.put(Mouse.DOACTION, false);
    };

    protected InputComponent(){
        _json = new Json();
    }

    public abstract void update(Entity entity, float delta);

}

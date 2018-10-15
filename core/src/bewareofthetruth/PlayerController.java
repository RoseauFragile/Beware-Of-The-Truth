package bewareofthetruth;

import java.util.HashMap;
import java.util.Map;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;

import bewareofthetruth.entity.components.Entity; 

public class PlayerController implements InputProcessor {

	private final static String TAG = PlayerController.class.getSimpleName();
	
	enum Keys {
		LEFT, RIGHT, UP, DOWN, QUIT
	}
	
	enum Mouse {
		SELECT, DOACTION
	}
	
	private static Map<Keys, Boolean> keys = new HashMap<PlayerController.Keys, Boolean>();
	private static Map<Mouse, Boolean> mouseButtons = new HashMap<PlayerController.Mouse , Boolean>();
	private Vector3 lastMouseCoordinates;
	
	static {
		keys.put(Keys.LEFT, false);
		keys.put(Keys.RIGHT, false);
		keys.put(Keys.UP, false);
		keys.put(Keys.DOWN, false);
		keys.put(Keys.QUIT, false);
	}
	
	static {
		mouseButtons.put(Mouse.SELECT, false);
		mouseButtons.put(Mouse.DOACTION, false);
	}
	
	private Entity player;
	
	public PlayerController(Entity player) {
		this.lastMouseCoordinates = new Vector3();
		this.player = player;
	}

	  @Override
	  public boolean keyDown(int keycode) {
		  if( keycode == Input.Keys.LEFT || keycode == Input.Keys.A){
			  this.leftPressed();
			  }
		  if( keycode == Input.Keys.RIGHT || keycode == Input.Keys.D){
			  this.rightPressed();
			  }
		  if( keycode == Input.Keys.UP || keycode == Input.Keys.W){
			  this.upPressed();
			  }
		  if( keycode == Input.Keys.DOWN || keycode == Input.Keys.S){
			  this.downPressed();
			  }
		  if( keycode == Input.Keys.Q){
			  this.quitPressed();
			  }
		  return true;
	  }
	    

	  @Override
	  public boolean keyUp(int keycode) {
		  if( keycode == Input.Keys.LEFT || keycode == Input.Keys.A){
			  this.leftReleased();
			  }
		  if( keycode == Input.Keys.RIGHT || keycode == Input.Keys.D){
			  this.rightReleased();
			  }
		  if( keycode == Input.Keys.UP || keycode == Input.Keys.W ){
			  this.upReleased();
			  }
		  if( keycode == Input.Keys.DOWN || keycode == Input.Keys.S){
			  this.downReleased();
			  }
		  if( keycode == Input.Keys.Q){
			  this.quitReleased();
			  }
		  return true;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(button == Input.Buttons.LEFT || button == Input.Buttons.RIGHT) {
			this.setClickedMouseCoordinates(screenX, screenY);
		}
		
		//left is selection, right is context menu
		if( button == Input.Buttons.LEFT){
			this.selectMouseButtonPressed(screenX, screenY);
			}
		if( button == Input.Buttons.RIGHT){
			this.doActionMouseButtonPressed(screenX, screenY);
			}
		return true;
	}

	@Override
	 public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		//left is selection, right is context men
		if( button == Input.Buttons.LEFT){
			this.selectMouseButtonReleased(screenX, screenY);
			}
		if( button == Input.Buttons.RIGHT){
			this.doActionMouseButtonReleased(screenX, screenY);
			}
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	public void dispose() {
	}
	
	//Key presses
	public void leftPressed(){
		keys.put(Keys.LEFT, true);
	}
	
	public void rightPressed(){
		keys.put(Keys.RIGHT, true);
	}
	  
	public void upPressed(){
		keys.put(Keys.UP, true);
	}
	
	public void downPressed(){
		keys.put(Keys.DOWN, true);
	}
	
	public void quitPressed(){
		keys.put(Keys.QUIT, true);
	}
	
	public void setClickedMouseCoordinates(int x, int y){
		lastMouseCoordinates.set(x, y, 0);
	}
	
	public void selectMouseButtonPressed(int x, int y){
		mouseButtons.put(Mouse.SELECT, true);
	}
	
	public void doActionMouseButtonPressed(int x, int y){
		mouseButtons.put(Mouse.DOACTION, true);
	}
	
	//Releases
	public void leftReleased(){
		keys.put(Keys.LEFT, false);
	}
	
	public void rightReleased(){
		keys.put(Keys.RIGHT, false);
	}
	
	public void upReleased(){
		keys.put(Keys.UP, false);
	}
	
	public void downReleased(){
		keys.put(Keys.DOWN, false);
	}
	
	public void quitReleased(){
		keys.put(Keys.QUIT, false);
	}
	
	public void selectMouseButtonReleased(int x, int y){
		mouseButtons.put(Mouse.SELECT, false);
	}
	
	public void doActionMouseButtonReleased(int x, int y){
		mouseButtons.put(Mouse.DOACTION, false);
	}
	
	public void update(float delta){
		  processInput(delta);
	}
	  
	public static void hide(){
		keys.put(Keys.LEFT, false);
		keys.put(Keys.RIGHT, false);
		keys.put(Keys.UP, false);
		keys.put(Keys.DOWN, false);
		keys.put(Keys.QUIT, false);
	}
	
	private void processInput(float delta) {
		
		//Keyboard input
		if(keys.get(Keys.LEFT)) {
			player.calculateNextPosition(Entity.Direction.LEFT, delta);
			player.setState(Entity.State.WALKING);
			player.setDirection(Entity.Direction.LEFT, delta);
		} else if (keys.get(Keys.RIGHT)) {
			player.calculateNextPosition(Entity.Direction.RIGHT, delta);
			player.setState(Entity.State.WALKING);
			player.setDirection(Entity.Direction.RIGHT, delta);
		} else if (keys.get(Keys.UP)) {
			player.calculateNextPosition(Entity.Direction.UP, delta);
			player.setState(Entity.State.WALKING);
			player.setDirection(Entity.Direction.UP, delta);
		} else if (keys.get(Keys.DOWN)) {
			player.calculateNextPosition(Entity.Direction.DOWN, delta);
			player.setState(Entity.State.WALKING);
			player.setDirection(Entity.Direction.DOWN, delta);
		} else if(keys.get(Keys.QUIT)) {
			Gdx.app.exit();
		} else {
			player.setState(Entity.State.IDLE);
		}
		
		//Mouse Input
		if(mouseButtons.get(Mouse.SELECT)) {
			mouseButtons.put(Mouse.SELECT, false);
		}
	}
}

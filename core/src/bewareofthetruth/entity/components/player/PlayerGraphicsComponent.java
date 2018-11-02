package bewareofthetruth.entity.components.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import bewareofthetruth.entity.Entity;
import bewareofthetruth.entity.EntityConfig;
import bewareofthetruth.entity.Entity.AnimationType;
import bewareofthetruth.entity.Entity.Direction;
import bewareofthetruth.entity.Entity.State;
import bewareofthetruth.entity.EntityConfig.AnimationConfig;
import bewareofthetruth.entity.components.GraphicsComponent;
import bewareofthetruth.entity.components.Component.MESSAGE;
import bewareofthetruth.map.MapManager;
import bewareofthetruth.entity.components.ComponentObserver;


public class PlayerGraphicsComponent extends GraphicsComponent {

    private static final String TAG = PlayerGraphicsComponent.class.getSimpleName();
    protected Vector2 _previousPosition;
    protected float cameraX = _currentPosition.x;
    protected float cameraY = _currentPosition.y;
    
    public PlayerGraphicsComponent(){
        _previousPosition = new Vector2(0,0);
    }

    @Override
    public void receiveMessage(String message) {

        String[] string = message.split(MESSAGE_TOKEN);

        if( string.length == 0 ) return;

        //Specifically for messages with 1 object payload
        if( string.length == 2 ) {
            if (string[0].equalsIgnoreCase(MESSAGE.CURRENT_POSITION.toString())) {
                _currentPosition = _json.fromJson(Vector2.class, string[1]);
            } else if (string[0].equalsIgnoreCase(MESSAGE.INIT_START_POSITION.toString())) {
                _currentPosition = _json.fromJson(Vector2.class, string[1]);
            } else if (string[0].equalsIgnoreCase(MESSAGE.CURRENT_STATE.toString())) {
                _currentState = _json.fromJson(Entity.State.class, string[1]);
            } else if (string[0].equalsIgnoreCase(MESSAGE.CURRENT_DIRECTION.toString())) {
                _currentDirection = _json.fromJson(Entity.Direction.class, string[1]);
            } else if (string[0].equalsIgnoreCase(MESSAGE.LOAD_ANIMATIONS.toString())) {
                EntityConfig entityConfig = _json.fromJson(EntityConfig.class, string[1]);
                Array<AnimationConfig> animationConfigs = entityConfig.getAnimationConfig();

                for( AnimationConfig animationConfig : animationConfigs ){
                    Array<String> textureNames = animationConfig.getTexturePaths();
                    Array<GridPoint2> points = animationConfig.getGridPoints();
                    Entity.AnimationType animationType = animationConfig.getAnimationType();
                    float frameDuration = animationConfig.getFrameDuration();
                    Animation<TextureRegion> animation = null;

                    if( textureNames.size == 1) {
                        animation = loadAnimation(textureNames.get(0), points, frameDuration);
                    }else if( textureNames.size == 2){
                        animation = loadAnimation(textureNames.get(0), textureNames.get(1), points, frameDuration);
                    }

                    _animations.put(animationType, animation);
                }
            }
        }
    }

    @Override
    public void update(Entity entity, MapManager mapMgr, Batch batch, float delta){
        updateAnimations(delta);

        //Player has moved
        if( _previousPosition.x != _currentPosition.x ||
                _previousPosition.y != _currentPosition.y){
            notify("", ComponentObserver.ComponentEvent.PLAYER_HAS_MOVED);
            _previousPosition = _currentPosition.cpy();
        }

        Camera camera = mapMgr.getCamera();
        
        TiledMap tiledMap = mapMgr.getCurrentTiledMap();
        MapProperties prop = tiledMap.getProperties();

        float mapWidth = prop.get("width", Integer.class) * 2;
        float mapHeight = prop.get("height", Integer.class) * 2;
        System.out.println("PlayerX : " + _currentPosition.x + " PlayerY : " + _currentPosition.y);
        System.out.println("mapWidth : " + mapWidth + " mapHeigh : " + mapHeight);

        if(_currentPosition.x >= 4 && _currentPosition.x <= mapWidth - 4) {
        	cameraX += (_currentPosition.x + delta - cameraX) * delta;
        }
        if(_currentPosition.y > 4 && _currentPosition.y < mapHeight - 4) {
        	cameraY += (_currentPosition.y + delta - cameraY) * delta;
        }
        
        camera.position.set(cameraX, cameraY, 0f);
        camera.update();


        batch.begin();
        batch.draw(_currentFrame, _currentPosition.x, _currentPosition.y, 1, 1);
        batch.end();

        //Used to graphically debug boundingboxes
        /*
        Rectangle rect = entity.getCurrentBoundingBox();
        _shapeRenderer.setProjectionMatrix(camera.combined);
        _shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        _shapeRenderer.setColor(Color.RED);
        _shapeRenderer.rect(rect.getX() * Map.UNIT_SCALE , rect.getY() * Map.UNIT_SCALE, rect.getWidth() * Map.UNIT_SCALE, rect.getHeight()*Map.UNIT_SCALE);
        _shapeRenderer.end();
        */
    }

    @Override
    public void dispose(){
    }

}
package bewareofthetruth;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import bewareofthetruth.entity.components.Entity;
import bewareofthetruth.entity.components.Map;
import bewareofthetruth.entity.components.MapFactory;
import bewareofthetruth.entity.components.Component;
import java.util.Hashtable;

public class MapManager {

	private static final String TAG = MapManager.class.getSimpleName();
	
	private Hashtable<String,String> mapTable;
	private Hashtable<String,Vector2> playerStartLocationTable;
	
	private final static String TOP_WORLD = "TOP_WORLD";
	private final static String TOWN = "TOWN";
	private final static String CASTLE_OF_DOOM = "CASTLE_OF_DOOM";
	
	private final static String MAP_COLLISION_LAYER = "MAP_COLLISION_LAYER";
	private final static String MAP_SPAWNS_LAYER = "MAP_SPAWNS_LAYER";
	private final static String MAP_PORTAL_LAYER = "MAP_PORTAL_LAYER";
	
	private final static String PLAYER_START = "PLAYER_START";
	
	private Vector2 playerStartPositionRect;
	private Vector2 closestPlayerStartPosition;
	private Vector2 convertedUnits;
	private Vector2 playerStart;
	private Map _currentMap = null;
	private String currentMapName = null;
	
	private MapLayer collisionLayer = null;
	private MapLayer portalLayer = null;
	private MapLayer spawnsLayer = null;

    private Entity _player;
    private Camera _camera;
    private Entity _currentSelectedEntity = null;
    private boolean _mapChanged = false;
    
    private MapLayer _currentLightMap = null;
    private MapLayer _previousLightMap = null;
	
	public final static float UNIT_SCALE = 1/64f;
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public MapManager() {
		playerStart = new Vector2(0,0);
		mapTable = new Hashtable();
		
		mapTable.put(TOP_WORLD, "tiledMap/zone1.2.tmx");
		mapTable.put(TOWN, "tiledMap/zone1.1.tmx");
		mapTable.put(CASTLE_OF_DOOM, "tiledMap/zone1.3.tmx");
		
		playerStartLocationTable = new Hashtable();
		playerStartLocationTable.put(TOP_WORLD, playerStart.cpy());
		playerStartLocationTable.put(TOWN, playerStart.cpy());
		playerStartLocationTable.put(CASTLE_OF_DOOM, playerStart.cpy());
		
		playerStartPositionRect = new Vector2(0,0);
		closestPlayerStartPosition = new Vector2(0,0);
		convertedUnits = new Vector2(0,0);
	}

	public void loadMap(MapFactory.MapType mapType) {
		Map map = MapFactory.getMap(mapType);
		if(map == null) {
			Gdx.app.debug(TAG, "Map is invalid");
			return;
		}

        _currentMap = map;
        _mapChanged = true;
        clearCurrentSelectedMapEntity();
        Gdx.app.debug(TAG, "Player Start: (" + _currentMap.getPlayerStart().x + "," + _currentMap.getPlayerStart().y + ")");
	}
	
	public MapLayer getCollisionLayer() {
		return collisionLayer;
	}

	public MapLayer getPortalLayer() {
		return portalLayer;
	}

	public Vector2 getPlayerStartUnitScaled() {
		Vector2 playerStart = this.playerStart.cpy();
		playerStart.set(playerStart.x * UNIT_SCALE, playerStart.y * UNIT_SCALE);
		return playerStart;
	}
	

	//TODO a modifier pour inclure la première position d'une prochaine map, explications ci dessous
	//en effet pour déduire le bon start quand on revient sur un map, le jeu garde en mémoire l'ancienne position, c'est à dire
	//que si nous n'avons jamais été une fois dans un map, le calcul est un gros aléatoire, donc il faut rajouter un objet first start sur tiled,
	//Le prendre en compte et rajouter le calcul
	private void setClosestStartPosition(final Vector2 position){
		//Get last known position on this map
		playerStartPositionRect.set(0,0);
		closestPlayerStartPosition.set(0,0);
		float shortestDistance = 0f;
		
		//Go through all player start positions and choose closest to
		//last known position
		for( MapObject object: spawnsLayer.getObjects()){
			if( object.getName().equalsIgnoreCase(PLAYER_START) ){
				((RectangleMapObject)object).getRectangle().
				getPosition(playerStartPositionRect);
				float distance = position.dst2(playerStartPositionRect);
			
		
		         if( distance < shortestDistance ||
		        		 shortestDistance == 0 ){
		        	 closestPlayerStartPosition.set(
		        			 playerStartPositionRect);
		         shortestDistance = distance;
		         }
			}
		}
		playerStartLocationTable.put(
				currentMapName, closestPlayerStartPosition.cpy()); 
	}
	
	/*@SuppressWarnings("unused")
	public void setClosestStartPositionFromScaledUnits(Vector2 position) {
		if(UNIT_SCALE <=0) {
			return;
		} else {
			convertedUnits.set(position.x/UNIT_SCALE, position.y/UNIT_SCALE);
			setClosestStartPosition(convertedUnits);
		}
	}*/
	
    public void setPlayer(Entity entity){
        this._player = entity;
    }

    public Entity getPlayer(){
        return this._player;
    }

    public void setCamera(Camera camera){
        this._camera = camera;
    }

    public Camera getCamera(){
        return _camera;
    }
    
    public Entity getCurrentSelectedMapEntity(){
        return _currentSelectedEntity;
    }

    public void setCurrentSelectedMapEntity(Entity currentSelectedEntity) {
        this._currentSelectedEntity = currentSelectedEntity;
    }

    public void clearCurrentSelectedMapEntity(){
        if( _currentSelectedEntity == null ) return;
        _currentSelectedEntity.sendMessage(Component.MESSAGE.ENTITY_DESELECTED);
        _currentSelectedEntity = null;
    }
    
    public void setClosestStartPositionFromScaledUnits(Vector2 position) {
        _currentMap.setClosestStartPositionFromScaledUnits(position);
    }
    
    public MapFactory.MapType getCurrentMapType(){
        return _currentMap.getCurrentMapType();
    }
    
    public TiledMap getCurrentTiledMap(){
        if( _currentMap == null ) {
            loadMap(MapFactory.MapType.TOWN);
        }
        return _currentMap.getCurrentTiledMap();
    }
    
    public final Array<Entity> getCurrentMapEntities(){
        return _currentMap.getMapEntities();
    }
    
    public void updateCurrentMapEntities(MapManager mapMgr, Batch batch, float delta){
        _currentMap.updateMapEntities(mapMgr, batch, delta);
    }
    
    public void setMapChanged(boolean hasMapChanged){
        this._mapChanged = hasMapChanged;
    }
    
    public boolean hasMapChanged(){
        return _mapChanged;
    }
    
    public MapLayer getPreviousLightMapLayer(){
        return _previousLightMap;
    }

    public MapLayer getCurrentLightMapLayer(){
        return _currentLightMap;
    }
    
}

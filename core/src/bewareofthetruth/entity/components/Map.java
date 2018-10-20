package bewareofthetruth.entity.components;

import java.util.Hashtable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.maps.MapObject;
import bewareofthetruth.MapManager;
import bewareofthetruth.Utility;

import com.badlogic.gdx.maps.objects.RectangleMapObject;

public abstract class Map {

    private static final String TAG = Map.class.getSimpleName();
    public final static float UNIT_SCALE  = 1/64f;
    protected final static String COLLISION_LAYER = "MAP_COLLISION_LAYER";
    protected final static String SPAWNS_LAYER = "MAP_SPAWNS_LAYER";
    protected final static String PORTAL_LAYER = "MAP_PORTAL_LAYER";
    //Starting locations
    protected final static String PLAYER_START = "PLAYER_START";
    protected final static String NPC_START = "NPC_START";
    
    protected Json _json;

    protected Vector2 _playerStartPositionRect;
    protected Vector2 _closestPlayerStartPosition;
    protected Vector2 _convertedUnits;
    protected TiledMap _currentMap = null;
    protected Vector2 _playerStart;
    protected Array<Vector2> _npcStartPositions;
    protected Hashtable<String, Vector2> _specialNPCStartPositions;

    protected MapLayer _collisionLayer = null;
    protected MapLayer _portalLayer = null;
    protected MapLayer _spawnsLayer = null;
    
    protected MapFactory.MapType _currentMapType;
    protected Array<Entity> _mapEntities;
    
    Map( MapFactory.MapType mapType, String fullMapPath){
        _json = new Json();
        _mapEntities = new Array<Entity>(10);
        _currentMapType = mapType;
        _playerStart = new Vector2(0,0);
        _playerStartPositionRect = new Vector2(0,0);
        _closestPlayerStartPosition = new Vector2(0,0);
        _convertedUnits = new Vector2(0,0);

        if( fullMapPath == null || fullMapPath.isEmpty() ) {
            Gdx.app.debug(TAG, "Map is invalid");
            return;
        }

        Utility.loadMapAsset(fullMapPath);
        if( Utility.isAssetLoaded(fullMapPath) ) {
            _currentMap = Utility.getMapAsset(fullMapPath);
        }else{
            Gdx.app.debug(TAG, "Map not loaded");
            return;
        }

        _collisionLayer = _currentMap.getLayers().get(COLLISION_LAYER);
        if( _collisionLayer == null ){
            Gdx.app.debug(TAG, "No collision layer!");
        }

        _portalLayer = _currentMap.getLayers().get(PORTAL_LAYER);
        if( _portalLayer == null ){
            Gdx.app.debug(TAG, "No portal layer!");
        }

        _spawnsLayer = _currentMap.getLayers().get(SPAWNS_LAYER);
        if( _spawnsLayer == null ){
            Gdx.app.debug(TAG, "No spawn layer!");
        }else{
            setClosestStartPosition(_playerStart);
        }

        _npcStartPositions = getNPCStartPositions();
        _specialNPCStartPositions = getSpecialNPCStartPositions();


    }
    public Array<Entity> getMapEntities(){
        return _mapEntities;
    }

    public MapFactory.MapType getCurrentMapType(){
        return _currentMapType;
    }

    public Vector2 getPlayerStart() {
        return _playerStart;
    }

    public void setPlayerStart(Vector2 playerStart) {
        this._playerStart = playerStart;
    }

    protected void updateMapEntities(MapManager mapMgr, Batch batch, float delta){
        for( int i=0; i < _mapEntities.size; i++){
            _mapEntities.get(i).update(mapMgr, batch, delta);
        }
        /*for( int i=0; i < _mapQuestEntities.size; i++){
            _mapQuestEntities.get(i).update(mapMgr, batch, delta);
        }*/
    }


    protected void dispose(){
        for( int i=0; i < _mapEntities.size; i++){
            _mapEntities.get(i).dispose();
        }
       /* for( int i=0; i < _mapQuestEntities.size; i++){
            _mapQuestEntities.get(i).dispose();
        }
        for( int i=0; i < _mapParticleEffects.size; i++){
            _mapParticleEffects.get(i).dispose();
        }*/
    }

    public MapLayer getCollisionLayer(){
        return _collisionLayer;
    }

    public MapLayer getPortalLayer(){
        return _portalLayer;
    }

    public TiledMap getCurrentTiledMap() {
        return _currentMap;
    }

    public Vector2 getPlayerStartUnitScaled(){
        Vector2 playerStart = _playerStart.cpy();
        playerStart.set(_playerStart.x * UNIT_SCALE, _playerStart.y * UNIT_SCALE);
        return playerStart;
    }

    private Array<Vector2> getNPCStartPositions(){
        Array<Vector2> npcStartPositions = new Array<Vector2>();

        for( MapObject object: _spawnsLayer.getObjects()){
            String objectName = object.getName();

            if( objectName == null || objectName.isEmpty() ){
                continue;
            }

            if( objectName.equalsIgnoreCase(NPC_START) ){
                //Get center of rectangle
                float x = ((RectangleMapObject)object).getRectangle().getX();
                float y = ((RectangleMapObject)object).getRectangle().getY();

                //scale by the unit to convert from map coordinates
                x *= UNIT_SCALE;
                y *= UNIT_SCALE;

                npcStartPositions.add(new Vector2(x,y));
            }
        }
        return npcStartPositions;
    }

    private Hashtable<String, Vector2> getSpecialNPCStartPositions(){
        Hashtable<String, Vector2> specialNPCStartPositions = new Hashtable<String, Vector2>();

        for( MapObject object: _spawnsLayer.getObjects()){
            String objectName = object.getName();

            if( objectName == null || objectName.isEmpty() ){
                continue;
            }

            //This is meant for all the special spawn locations, a catch all, so ignore known ones
            if(     objectName.equalsIgnoreCase(NPC_START) ||
                    objectName.equalsIgnoreCase(PLAYER_START) ){
                continue;
            }

            //Get center of rectangle
            float x = ((RectangleMapObject)object).getRectangle().getX();
            float y = ((RectangleMapObject)object).getRectangle().getY();

            //scale by the unit to convert from map coordinates
            x *= UNIT_SCALE;
            y *= UNIT_SCALE;

            specialNPCStartPositions.put(objectName, new Vector2(x,y));
        }
        return specialNPCStartPositions;
    }

    private void setClosestStartPosition(final Vector2 position){
         Gdx.app.debug(TAG, "setClosestStartPosition INPUT: (" + position.x + "," + position.y + ") " + _currentMapType.toString());

        //Get last known position on this map
        _playerStartPositionRect.set(0,0);
        _closestPlayerStartPosition.set(0,0);
        float shortestDistance = 0f;

        //Go through all player start positions and choose closest to last known position
        for( MapObject object: _spawnsLayer.getObjects()){
            String objectName = object.getName();

            if( objectName == null || objectName.isEmpty() ){
                continue;
            }

            if( objectName.equalsIgnoreCase(PLAYER_START) ){
                ((RectangleMapObject)object).getRectangle().getPosition(_playerStartPositionRect);
                float distance = position.dst2(_playerStartPositionRect);

                Gdx.app.debug(TAG, "DISTANCE: " + distance + " for " + _currentMapType.toString());

                if( distance < shortestDistance || shortestDistance == 0 ){
                    _closestPlayerStartPosition.set(_playerStartPositionRect);
                    shortestDistance = distance;
                    Gdx.app.debug(TAG, "closest START is: (" + _closestPlayerStartPosition.x + "," + _closestPlayerStartPosition.y + ") " +  _currentMapType.toString());
                }
            }
        }
        _playerStart =  _closestPlayerStartPosition.cpy();
    }

    @SuppressWarnings("unused")
	public void setClosestStartPositionFromScaledUnits(Vector2 position){
        if( UNIT_SCALE <= 0 )
            return;

        _convertedUnits.set(position.x/UNIT_SCALE, position.y/UNIT_SCALE);
        setClosestStartPosition(_convertedUnits);
    }

}

package bewareofthetruth.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import bewareofthetruth.entity.Entity;
import bewareofthetruth.entity.components.Component;
import bewareofthetruth.entity.components.ComponentObserver;
import bewareofthetruth.profile.ProfileManager;
import bewareofthetruth.profile.ProfileObserver;
import bewareofthetruth.screens.MainGameScreen;


public class MapManager implements ProfileObserver {
    private static final String TAG = MapManager.class.getSimpleName();

    private Camera _camera;
    private boolean _mapChanged = false;
    private Map _currentMap;
    private Entity _player;
    private Entity _currentSelectedEntity = null;
    private MapLayer _currentLightMap = null;
    private MapLayer _previousLightMap = null;
    private MapFactory _mapFactory;
    private MainGameScreen _mainGameScreen;
   // private ClockActor.TimeOfDay _timeOfDay = null;

    public MapManager(MainGameScreen _mainGameScreen){
    	this.set_mainGameScreen(_mainGameScreen);
    	this.set_mapFactory(new MapFactory());
    	this.get_mapFactory().setMapSql(this.get_mainGameScreen().get_bewareOfTruthDao());
    }

    @Override
    public void onNotify(ProfileManager profileManager, ProfileEvent event) {
        switch(event){
            case PROFILE_LOADED:
                int currentMap = profileManager.getProperty("currentMapId", int.class);
                int mapId;
                if( profileManager.getProperty("currentMapId", int.class) == null  ){
                    mapId = 1;
                }else{
                    mapId = profileManager.getProperty("currentMapId", int.class);
                }
                loadMap(mapId);
                for (int i = 0; i< this.get_mapFactory().getMapSql().size;i++) {
                  
                    String nameOfMapLoop = this.get_mapFactory().getMapSql().get(i).get_mapName()+"Position";
                    Vector2 position = profileManager.getProperty(nameOfMapLoop, Vector2.class);
                    if( position != null ){
                        MapFactory.getMapById(this.get_mapFactory().getMapSql().get(i).get_id(),this.get_mainGameScreen().get_bewareOfTruthDao()).setPlayerStart(position);
                    }
                }
                
                break;
            case SAVING_PROFILE:
                if( _currentMap != null ){
                    profileManager.setProperty("currentMapId", _currentMap._currentMapId);
                }
                for (int i = 0; i< this.get_mapFactory().getMapSql().size;i++) {
                	String propertyString = this.get_mapFactory().getMapSql().get(i).get_mapName()+"Position";
                    profileManager.setProperty(propertyString, MapFactory.getMapById(this.get_mapFactory().getMapSql().get(i).get_id(),this.get_mainGameScreen().get_bewareOfTruthDao()).getPlayerStart() );
                }

                break;
            case CLEAR_CURRENT_PROFILE:
                _currentMap = null;
                profileManager.setProperty("currentMapType", 1);
                MapFactory.clearCache();
                for (int i = 0; i< this.get_mapFactory().getMapSql().size;i++) {
                	String propertyString = this.get_mapFactory().getMapSql().get(i).get_mapName()+"Position";
                    profileManager.setProperty(propertyString, MapFactory.getMapById(this.get_mapFactory().getMapSql().get(i).get_id(),this.get_mainGameScreen().get_bewareOfTruthDao()).getPlayerStart() );
                }

                break;
            default:
                break;
        }
    }
    
    public void loadMap(int id){
        Map map = MapFactory.getMapById(id,this.get_mainGameScreen().get_bewareOfTruthDao());

        if( map == null ){
            Gdx.app.debug(TAG, "Map does not exist! with ID :" + id);
            return;
        }

        if( _currentMap != null ){
            _currentMap.unloadMusic();
            if( _previousLightMap != null ){
                _previousLightMap.setOpacity(0);
                _previousLightMap = null;
            }
            if( _currentLightMap != null ){
                _currentLightMap.setOpacity(1);
                _currentLightMap = null;
            }
        }

        map.loadMusic();

        _currentMap = map;
        _mapChanged = true;
        clearCurrentSelectedMapEntity();
    }

    public void unregisterCurrentMapEntityObservers(){
        if( _currentMap != null ){
            Array<Entity> entities = _currentMap.getMapEntities();
            for(Entity entity: entities){
                entity.unregisterObservers();
            }

            Array<Entity> questEntities = _currentMap.getMapQuestEntities();
            for(Entity questEntity: questEntities){
                questEntity.unregisterObservers();
            }
        }
    }

    public void registerCurrentMapEntityObservers(ComponentObserver observer){
        if( _currentMap != null ){
            Array<Entity> entities = _currentMap.getMapEntities();
            for(Entity entity: entities){
                entity.registerObserver(observer);
            }

            Array<Entity> questEntities = _currentMap.getMapQuestEntities();
            for(Entity questEntity: questEntities){
                questEntity.registerObserver(observer);
            }
        }
    }


    public void disableCurrentmapMusic(){
        _currentMap.unloadMusic();
    }

    public void enableCurrentmapMusic(){
        _currentMap.loadMusic();
    }

    public void setClosestStartPositionFromScaledUnits(Vector2 position) {
        _currentMap.setClosestStartPositionFromScaledUnits(position);
    }

    public MapLayer getCollisionLayer(){
        return _currentMap.getCollisionLayer();
    }

    public MapLayer getPortalLayer(){
        return _currentMap.getPortalLayer();
    }

    public Array<Vector2> getQuestItemSpawnPositions(String objectName, String objectTaskID) {
        return _currentMap.getQuestItemSpawnPositions(objectName, objectTaskID);
    }

    public MapLayer getQuestDiscoverLayer(){
        return _currentMap.getQuestDiscoverLayer();
    }

    public MapLayer getEnemySpawnLayer(){
        return _currentMap.getEnemySpawnLayer();
    }

    public int getCurrentMapId(){
        return _currentMap.getCurrentMapId();
    }

    public Vector2 getPlayerStartUnitScaled() {
        return _currentMap.getPlayerStartUnitScaled();
    }

    public TiledMap getCurrentTiledMap(){
        if( _currentMap == null ) {
            loadMap(1);
        }
        return _currentMap.getCurrentTiledMap();
    }

    public MapLayer getPreviousLightMapLayer(){
        return _previousLightMap;
    }

    public MapLayer getCurrentLightMapLayer(){
        return _currentLightMap;
    }

    public void updateCurrentMapEntities(MapManager mapMgr, Batch batch, float delta){
        _currentMap.updateMapEntities(mapMgr, batch, delta);
    }

    public void updateCurrentMapEffects(MapManager mapMgr, Batch batch, float delta){
        _currentMap.updateMapEffects(mapMgr, batch, delta);
    }

    public final Array<Entity> getCurrentMapEntities(){
        return _currentMap.getMapEntities();
    }

    public final Array<Entity> getCurrentMapQuestEntities(){
        return _currentMap.getMapQuestEntities();
    }

    public void addMapQuestEntities(Array<Entity> entities){
        _currentMap.getMapQuestEntities().addAll(entities);
    }

    @SuppressWarnings("unchecked")
	public void removeMapQuestEntity(Entity entity){
        entity.unregisterObservers();

        Array<Vector2> positions = ProfileManager.getInstance().getProperty(entity.getEntityConfig().getEntityID(), Array.class);
        if( positions == null ) return;

        for( Vector2 position : positions){
            if( position.x == entity.getCurrentPosition().x &&
                    position.y == entity.getCurrentPosition().y ){
                positions.removeValue(position, true);
                break;
            }
        }
        _currentMap.getMapQuestEntities().removeValue(entity, true);
        ProfileManager.getInstance().setProperty(entity.getEntityConfig().getEntityID(), positions);
    }

    public void clearAllMapQuestEntities(){
        _currentMap.getMapQuestEntities().clear();
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

    public boolean hasMapChanged(){
        return _mapChanged;
    }

    public void setMapChanged(boolean hasMapChanged){
        this._mapChanged = hasMapChanged;
    }

	public MapFactory get_mapFactory() {
		return _mapFactory;
	}

	public void set_mapFactory(MapFactory _mapFactory) {
		this._mapFactory = _mapFactory;
	}

	public MainGameScreen get_mainGameScreen() {
		return _mainGameScreen;
	}

	public void set_mainGameScreen(MainGameScreen _mainGameScreen) {
		this._mainGameScreen = _mainGameScreen;
	}

	public int getMapIdByName(String mapName) {
		int nextMap = this.get_mapFactory().getMapIdByName(mapName);
		return nextMap;
	}
}

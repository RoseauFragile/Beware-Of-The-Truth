package bewareofthetruth.map.zone1;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import bewareofthetruth.audio.AudioObserver;
import bewareofthetruth.entity.Entity;
import bewareofthetruth.entity.EntityConfig;
import bewareofthetruth.entity.components.Component;
import bewareofthetruth.map.Map;
import bewareofthetruth.map.MapFactory;
import bewareofthetruth.profile.ProfileManager;

//TODO Soit on garde cette architecture et on créer une map pour chaque Level ce qui est en soit efficaces pour un rpg mais LOURD soit on fais une factory plus élaboré avec la bdd
public class ZoneOneDotOne extends Map{


    private static String _mapPath = "maps/zone1.1.tmx";
    private Json _json;

    public ZoneOneDotOne(){
        super(MapFactory.MapType.ZONE_1_1, _mapPath);
        _json = new Json();


    }

    @Override
    public void unloadMusic() {
      notify(AudioObserver.AudioCommand.MUSIC_STOP, AudioObserver.AudioTypeEvent.MUSIC_TOWN);
    }

    @Override
    public void loadMusic() {
        notify(AudioObserver.AudioCommand.MUSIC_LOAD, AudioObserver.AudioTypeEvent.MUSIC_TOWN);
        notify(AudioObserver.AudioCommand.MUSIC_PLAY_LOOP, AudioObserver.AudioTypeEvent.MUSIC_TOWN);
    }

    private void initSpecialEntityPosition(Entity entity){
        Vector2 position = new Vector2(0,0);

        if( _specialNPCStartPositions.containsKey(entity.getEntityConfig().getEntityID()) ) {
            position = _specialNPCStartPositions.get(entity.getEntityConfig().getEntityID());
        }
        entity.sendMessage(Component.MESSAGE.INIT_START_POSITION, _json.toJson(position));

        EntityConfig entityConfig = ProfileManager.getInstance().getProperty(entity.getEntityConfig().getEntityID(), EntityConfig.class);
        if( entityConfig != null ){
        	
            entity.setEntityConfig(entityConfig);
        }
    }
}

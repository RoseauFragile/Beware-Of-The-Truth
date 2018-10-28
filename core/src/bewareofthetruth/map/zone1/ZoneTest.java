package bewareofthetruth.map.zone1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;

import bewareofthetruth.audio.AudioObserver;
import bewareofthetruth.entity.Entity;
import bewareofthetruth.entity.EntityConfig;
import bewareofthetruth.entity.EntityFactory;
import bewareofthetruth.entity.components.Component;
import bewareofthetruth.entity.components.player.PlayerPhysicsComponent;
import bewareofthetruth.map.Map;
import bewareofthetruth.map.MapFactory;
import bewareofthetruth.profile.ProfileManager;

public class ZoneTest extends Map {
	
	  @SuppressWarnings("unused")
		private static final String TAG = PlayerPhysicsComponent.class.getSimpleName();

	    private static String _mapPath = "maps/zone_test.tmx";
	    private Json _json;

	    public ZoneTest(){
	        super(MapFactory.MapType.ZONE_TEST, _mapPath);

	        _json = new Json();

	        for( Vector2 position: _npcStartPositions){
	            Entity entity = EntityFactory.getInstance().getEntityByName(EntityFactory.EntityName.TOWN_GUARD_WALKING);
	            entity.sendMessage(Component.MESSAGE.INIT_START_POSITION, _json.toJson(position));
	            _mapEntities.add(entity);
	        }

	        
	        //Special cases
	        Entity blackSmith = EntityFactory.getInstance().getEntityByName(EntityFactory.EntityName.TOWN_BLACKSMITH);
	        initSpecialEntityPosition(blackSmith);
	        _mapEntities.add(blackSmith);

	        Entity mage = EntityFactory.getInstance().getEntityByName(EntityFactory.EntityName.TOWN_MAGE);
	        initSpecialEntityPosition(mage);
	        _mapEntities.add(mage);

	        Entity innKeeper = EntityFactory.getInstance().getEntityByName(EntityFactory.EntityName.TOWN_INNKEEPER);
	        initSpecialEntityPosition(innKeeper);
	        _mapEntities.add(innKeeper);

	        Entity townfolk1 = EntityFactory.getInstance().getEntityByName(EntityFactory.EntityName.TOWN_FOLK1);
	        initSpecialEntityPosition(townfolk1);
	        _mapEntities.add(townfolk1);

	        Entity townfolk2 = EntityFactory.getInstance().getEntityByName(EntityFactory.EntityName.TOWN_FOLK2);
	        initSpecialEntityPosition(townfolk2);
	        _mapEntities.add(townfolk2);

	        Entity townfolk3 = EntityFactory.getInstance().getEntityByName(EntityFactory.EntityName.TOWN_FOLK3);
	        initSpecialEntityPosition(townfolk3);
	        _mapEntities.add(townfolk3);

	        Entity townfolk4 = EntityFactory.getInstance().getEntityByName(EntityFactory.EntityName.TOWN_FOLK4);
	        initSpecialEntityPosition(townfolk4);
	        _mapEntities.add(townfolk4);

	        Entity townfolk5 = EntityFactory.getInstance().getEntityByName(EntityFactory.EntityName.TOWN_FOLK5);
	        initSpecialEntityPosition(townfolk5);
	        _mapEntities.add(townfolk5);/*


	       /* Array<Vector2> candleEffectPositions = getParticleEffectSpawnPositions(ParticleEffectFactory.ParticleEffectType.CANDLE_FIRE);
	        for( Vector2 position: candleEffectPositions ){
	            _mapParticleEffects.add(ParticleEffectFactory.getParticleEffect(ParticleEffectFactory.ParticleEffectType.CANDLE_FIRE, position));
	        }

	        Array<Vector2> lanternEffectPositions = getParticleEffectSpawnPositions(ParticleEffectFactory.ParticleEffectType.LANTERN_FIRE);
	        for( Vector2 position: lanternEffectPositions ){
	            _mapParticleEffects.add(ParticleEffectFactory.getParticleEffect(ParticleEffectFactory.ParticleEffectType.LANTERN_FIRE, position));
	        }*/
	        for(int i =0; i <_mapEntities.size; i++) {
	        	Gdx.app.debug(TAG,i + "entite : " +_mapEntities.get(i).toString() + " x,y = " + _mapEntities.get(i).getCurrentPosition().x + ":" + _mapEntities.get(i).getCurrentPosition().y);
	        }
	    }

	    @Override
	    public void unloadMusic() {
	      notify(AudioObserver.AudioCommand.MUSIC_STOP, AudioObserver.AudioTypeEvent.MUSIC_HOTLINE);
	    }

	    @Override
	    public void loadMusic() {
	        notify(AudioObserver.AudioCommand.MUSIC_LOAD, AudioObserver.AudioTypeEvent.MUSIC_HOTLINE);
	        notify(AudioObserver.AudioCommand.MUSIC_PLAY_LOOP, AudioObserver.AudioTypeEvent.MUSIC_HOTLINE);
	    }

	    private void initSpecialEntityPosition(Entity entity){
	    	Gdx.app.debug(TAG, entity.getEntityConfig().getEntityID());
	        Vector2 position = new Vector2(0,0);

	        if( _specialNPCStartPositions.containsKey(entity.getEntityConfig().getEntityID()) ) {
	        	//Gdx.app.debug(TAG, "test");
	            position = _specialNPCStartPositions.get(entity.getEntityConfig().getEntityID());
	        }
	        entity.sendMessage(Component.MESSAGE.INIT_START_POSITION, _json.toJson(position));

	        //Overwrite default if special config is found
	        EntityConfig entityConfig = ProfileManager.getInstance().getProperty(entity.getEntityConfig().getEntityID(), EntityConfig.class);
	        if( entityConfig != null ){
	        	
	            entity.setEntityConfig(entityConfig);
	        }
	    }
	}


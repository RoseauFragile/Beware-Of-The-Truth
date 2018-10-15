package bewareofthetruth.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;

public class EntityFactory {

	public static Json json = new Json();
	private final static String TAG = EntityFactory.class.getSimpleName();
	public static enum EntityType{
		PLAYER,
		DEMO_PLAYER,
		NPC
	}
	
	public static String PLAYER_CONFIG = "scripts/player.json";
	
	static public Entity getEntity(EntityType entityType) {
		Entity entity = null;
		switch(entityType) {
		case PLAYER:
			entity = new Entity(
					new PlayerInputComponent(),
					new PlayerPhysicsComponent(),
					new PlayerGraphicsComponent());
			entity.setEntityConfig(
					Entity.getEntityConfig(
							EntityFactory.PLAYER_CONFIG));
			entity.sendMessage(
					Component.MESSAGE.LOAD_ANIMATION,
					json.toJson(entity.getEntityConfig()));
			return entity;
		case DEMO_PLAYER:
			entity = new Entity(
					new NPCInputComponent(),
					new PlayerPhysicsComponent(),
					new PLayerGraphicsComponent());
			return entity;
		case NPC:
			entity = new Entity(
					new NPCInputComponent(),
					new NPCPhysicsComponent(),
					new NPCGraphicsComponent());
			return entity;
		default:
			return null;
			
		}
	}
}

package bewareofthetruth.model.gameMechanics.entity.mobile;

import com.badlogic.gdx.physics.box2d.World;
import bewareofthetruth.contract.model.gameMecanism.IEntity;
import bewareofthetruth.model.dao.MobileSql;

public abstract class CharacterFactory {

 public static IEntity createEntity(MobileSql mobileSql, World world) {
	 switch(mobileSql.getMobtype()) {
	 case ZOMBIE:
		 return new Zombie(world, mobileSql.getX(), mobileSql.getY(), false);
	default :
		 return null;
	 }
 }
}

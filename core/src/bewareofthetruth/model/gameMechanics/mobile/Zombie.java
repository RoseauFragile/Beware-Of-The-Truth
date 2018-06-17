package bewareofthetruth.model.gameMechanics.mobile;

import com.badlogic.gdx.physics.box2d.World;

public class Zombie extends Mob{

	
	private static String sourceTexture = "zombieMarche.png";
	
	public Zombie(World world, float x, float y, boolean isStatic) {
		super(sourceTexture, world, x, y, isStatic);	
	}

}

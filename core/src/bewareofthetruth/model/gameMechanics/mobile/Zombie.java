package bewareofthetruth.model.gameMechanics.mobile;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.World;

import bewareofthetruth.contract.model.utils.Direction;

public class Zombie extends Mob{

	
	private static String sourceTexture = "zombieMarche.png";
	
	public Zombie(World world, float x, float y, boolean isStatic) {
		super(sourceTexture, world, x, y, isStatic);	
		this.setAtlas(new TextureAtlas("sprite/zombie.txt"));
		this.setAnimationCurrent(this.getAnimationWalkDown());
		this.setLastDirection(Direction.DOWN);
	}

}

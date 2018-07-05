package bewareofthetruth.model.gameMechanics.entity.mobile;

import com.badlogic.gdx.physics.box2d.World;

import bewareofthetruth.model.gameMechanics.entity.Entity;

public class Mob extends Entity{

	public Mob(String sourceTexture, World world, float x, float y, boolean isStatic) {
		super(sourceTexture, world, x, y, isStatic);
		
	}

}

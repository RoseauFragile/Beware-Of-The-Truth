package bewareofthetruth.model.gameMechanics.entity.mobile;

import com.badlogic.gdx.physics.box2d.World;

import bewareofthetruth.model.gameMechanics.entity.Entity;

public class Mob extends Entity {

	// TODO RYO il faudrait que tu codes ici un strategy pour le comportement des
	// mobs, si tu as le temps. Et surtout un attribut pdv, enfin de quoi le buter.
	public Mob(final World world, final float x, final float y, final boolean isStatic) {
		super(world, x, y, isStatic);

	}

}

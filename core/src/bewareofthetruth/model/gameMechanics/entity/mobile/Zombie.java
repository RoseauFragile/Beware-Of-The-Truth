package bewareofthetruth.model.gameMechanics.entity.mobile;

import static bewareofthetruth.model.util.Constants.BIT_DOOR;
import static bewareofthetruth.model.util.Constants.BIT_ENNEMY;
import static bewareofthetruth.model.util.Constants.BIT_LIGHT;
import static bewareofthetruth.model.util.Constants.BIT_PLAYER;
import static bewareofthetruth.model.util.Constants.BIT_WALL;

import com.badlogic.gdx.physics.box2d.World;

import bewareofthetruth.contract.model.utils.Direction;
import bewareofthetruth.model.util.box2d.BodyBuilder;

public class Zombie extends Mob {

	private static boolean fixedRotation = true;
	private static int WIDTH = 20;
	private static int HEIGHT = 58;

	public Zombie(final World world, final float x, final float y, final boolean isStatic) {
		super(world, x, y, isStatic);
		this.setBody(BodyBuilder.createEntityBody(this.getWorld(), x, y, WIDTH, HEIGHT, isStatic, fixedRotation,
				BIT_ENNEMY, (short) (BIT_PLAYER | BIT_WALL | BIT_LIGHT | BIT_ENNEMY | BIT_DOOR), (short) 0, this));
		this.initAnimation("sprite/zombie.txt", Direction.DOWN, 0.25f, 0.25f, 0.15f);
	}

}

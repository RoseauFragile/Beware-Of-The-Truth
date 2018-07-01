package bewareofthetruth.model.gameMechanics.mobile;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.World;
import static bewareofthetruth.model.util.Constants.BIT_ENNEMY;
import static bewareofthetruth.model.util.Constants.BIT_PLAYER;
import static bewareofthetruth.model.util.Constants.BIT_WALL;
import static bewareofthetruth.model.util.Constants.BIT_LIGHT;
import bewareofthetruth.contract.model.utils.Direction;
import bewareofthetruth.model.util.box2d.BodyBuilder;

public class Zombie extends Mob{

	private static boolean fixedRotation = true;
	private static String sourceTexture = "zombieMarche.png";
	private static int WIDTH = 25;
	private static int HEIGHT = 64;
	
	public Zombie(World world, float x, float y, boolean isStatic) {
		super(sourceTexture, world, x, y, isStatic);	
		this.setBody(BodyBuilder.createEntityBody(this.getWorld(), x, y, WIDTH, HEIGHT,isStatic, fixedRotation, BIT_ENNEMY, (short) (BIT_PLAYER | BIT_WALL | BIT_LIGHT), (short) 0));
		this.setAtlas(new TextureAtlas("sprite/zombie.txt"));
		this.setAnimationCurrent(this.getAnimationWalkDown());
		this.setLastDirection(Direction.DOWN);
	}

}

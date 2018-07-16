package bewareofthetruth.model.gameMechanics.tiles;

import static bewareofthetruth.model.util.Constants.BIT_DOOR;
import static bewareofthetruth.model.util.Constants.BIT_ENNEMY;
import static bewareofthetruth.model.util.Constants.BIT_PLAYER;

import com.badlogic.gdx.physics.box2d.World;

import bewareofthetruth.contract.model.gameMecanism.ITeleporter;
import bewareofthetruth.model.gameMechanics.entity.Entity;
import bewareofthetruth.model.util.box2d.BodyBuilder;

public class Teleporter extends Entity implements ITeleporter {

	private static boolean IS_STATIC = true;
	private int idLevel;
	private int idNextLevel;
	private int idTeleporter;
	private static int WIDTH = 64;
	private static int HEIGHT = 64;
	private boolean CAN_ROTATE = false;
	private int xSpawn;
	private int ySpawn;

	public Teleporter(final World world, final float x, final float y) {
		super(world, x, y, IS_STATIC);

	}

	public Teleporter(final int idTeleporter, final int idLevel, final int idNextLevel, final int x, final int y,
			final World world, final int xSpawn, final int ySpawn) {
		super(world, x, y, IS_STATIC);
		this.setIdTeleporter(idTeleporter);
		this.setIdLevel(idLevel);
		this.setIdNextLevel(idNextLevel);
		this.setxSpawn(xSpawn);
		this.setySpawn(ySpawn);
		this.setBody(BodyBuilder.createTeleporter(world, x, y, WIDTH, HEIGHT, IS_STATIC, this.CAN_ROTATE, BIT_DOOR,
				(short) (BIT_ENNEMY | BIT_PLAYER), (short) 0, this));
	}

	@Override
	public int getIdLevel() {
		return this.idLevel;
	}

	@Override
	public void setIdLevel(final int idLevel) {
		this.idLevel = idLevel;
	}

	@Override
	public int getIdNextLevel() {
		return this.idNextLevel;
	}

	@Override
	public void setIdNextLevel(final int idNextLevel) {
		this.idNextLevel = idNextLevel;
	}

	@Override
	public int getIdTeleporter() {
		return this.idTeleporter;
	}

	@Override
	public void setIdTeleporter(final int idTeleporter) {
		this.idTeleporter = idTeleporter;
	}

	@Override
	public int getxSpawn() {
		return this.xSpawn;
	}

	@Override
	public void setxSpawn(final int xSpawn) {
		this.xSpawn = xSpawn;
	}

	@Override
	public int getySpawn() {
		return this.ySpawn;
	}

	@Override
	public void setySpawn(final int ySpawn) {
		this.ySpawn = ySpawn;
	}

}

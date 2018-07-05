package bewareofthetruth.model.gameMechanics.tiles;

import com.badlogic.gdx.physics.box2d.World;
import static bewareofthetruth.model.util.Constants.BIT_ENNEMY;
import static bewareofthetruth.model.util.Constants.BIT_DOOR;
import static bewareofthetruth.model.util.Constants.BIT_PLAYER;
import bewareofthetruth.contract.model.gameMecanism.ITeleporter;
import bewareofthetruth.model.gameMechanics.entity.Entity;
import bewareofthetruth.model.util.box2d.BodyBuilder;

public class Teleporter extends Entity implements ITeleporter{

	private static boolean IS_STATIC = true;
	private int idLevel;
	private int idNextLevel;
	private int idTeleporter;
	private static int WIDTH = 64;
	private static int HEIGHT = 64;
	private boolean CAN_ROTATE = false;
	
	public Teleporter(String sourceTexture, World world, float x, float y) {
		super(sourceTexture, world, x, y, IS_STATIC);
		
	}

	public Teleporter(int idTeleporter, int idLevel, int idNextLevel, int x, int y, World world) {
		super(world, x, y, IS_STATIC);
		this.setIdTeleporter(idTeleporter);
		this.setIdLevel(idLevel);
		this.setIdNextLevel(idNextLevel);
		this.setBody(BodyBuilder.createTeleporter(world, idNextLevel, y, WIDTH, HEIGHT, IS_STATIC, CAN_ROTATE, BIT_DOOR, (short) (BIT_ENNEMY | BIT_PLAYER), (short) 0, this));
	}

	public int getIdLevel() {
		return idLevel;
	}

	public void setIdLevel(int idLevel) {
		this.idLevel = idLevel;
	}

	public int getIdNextLevel() {
		return idNextLevel;
	}

	public void setIdNextLevel(int idNextLevel) {
		this.idNextLevel = idNextLevel;
	}

	public int getIdTeleporter() {
		return idTeleporter;
	}

	public void setIdTeleporter(int idTeleporter) {
		this.idTeleporter = idTeleporter;
	}

}

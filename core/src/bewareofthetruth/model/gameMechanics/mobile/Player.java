package bewareofthetruth.model.gameMechanics.mobile;

import static bewareofthetruth.model.util.Constants.BIT_ENNEMY;
import static bewareofthetruth.model.util.Constants.BIT_LIGHT;
import static bewareofthetruth.model.util.Constants.BIT_PLAYER;
import static bewareofthetruth.model.util.Constants.BIT_WALL;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.World;
import bewareofthetruth.contract.model.data.IBewareOfTruthModel;
import bewareofthetruth.contract.model.gameMecanism.IPlayer;
import bewareofthetruth.contract.model.utils.Direction;
import bewareofthetruth.contract.model.utils.IEnergy;
import bewareofthetruth.contract.model.utils.IHealth;
import bewareofthetruth.model.gameMechanics.entity.Entity;
import bewareofthetruth.model.util.box2d.BodyBuilder;

public class Player extends Entity implements IPlayer {

	private IHealth health;
	private IEnergy energy;
	private int idChapter;
	private IBewareOfTruthModel bewareOfTruthModel;
	private int idLevel;
	private int id;
	private int idInventory;
	private String playerName;
	private static String sourceTexture = "herosMarche.png";
	private static boolean fixedRotation = true;
	private static int WIDTH = 20;
	private static int HEIGHT = 58;


	public Player(int idPlayer, String nom, int idLevel, int idInventory, int idChapter, World world, float x, float y, boolean isStatic) {
		super(sourceTexture, world, x, y, isStatic);
		this.setId(idPlayer);
		this.setPlayerName(nom);
		this.setIdLevel(idLevel);
		this.setIdInventory(idInventory);
		this.setIdChapter(idChapter);
		this.setBody(BodyBuilder.createEntityBody(this.getWorld(), x, y, WIDTH, HEIGHT, isStatic, fixedRotation, BIT_PLAYER, (short) (BIT_ENNEMY | BIT_WALL | BIT_LIGHT), (short) 0));
		this.setAtlas(new TextureAtlas("sprite/hero_walk.txt"));
		this.setAnimationCurrent(this.getAnimationWalkDown());
		this.setWalkDelta(0.15f);
		this.setIdleDelta(0.15f);
		this.setLastDirection(Direction.DOWN);
		
	}

	@Override
	public IEnergy getEnergy() {
		return this.energy;
	}

	@Override
	public void setEnergy(IEnergy energy) {
		this.energy = energy;
	}

	@Override
	public int getChapter() {
		return this.idChapter;
	}

	@Override
	public void setChapter(int idChapter) {
		this.idChapter = idChapter;
	}

	@Override
	public IHealth getHealth() {
		return health;
	}

	@Override
	public void setHealth(IHealth health) {
		this.health = health;
	}

	@Override
	public IBewareOfTruthModel getBewareOfTruthModel() {
		return this.bewareOfTruthModel;
	}

	@Override
	public void setBewareOfTruthModel(IBewareOfTruthModel bewareOfTruthModel) {
		this.bewareOfTruthModel = bewareOfTruthModel;
	}

	public int getIdChapter() {
		return this.idChapter;
	}

	public void setIdChapter(int idChapter) {
		this.idChapter = idChapter;
	}

	public int getIdLevel() {
		return this.idLevel;
	}

	public void setIdLevel(int idLevel) {
		this.idLevel = idLevel;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdInventory() {
		return this.idInventory;
	}

	public void setIdInventory(int idInventory) {
		this.idInventory = idInventory;
	}

	public String getPlayerName() {
		return this.playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
}
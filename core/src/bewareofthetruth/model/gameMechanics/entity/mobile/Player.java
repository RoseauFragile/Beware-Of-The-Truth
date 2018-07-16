package bewareofthetruth.model.gameMechanics.entity.mobile;

import static bewareofthetruth.model.util.Constants.BIT_DOOR;
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
	private static boolean fixedRotation = true;
	private static int WIDTH = 20;
	private static int HEIGHT = 58;

	// TODO Ryo, ici aussi il faut coder de quoi tuer le player, genre l'animation
	// de mort, les pdv a 0 et une animation pour taper.
	public Player(final int idPlayer, final String nom, final int idLevel, final int idInventory, final int idChapter,
			final World world, final float x, final float y, final boolean isStatic) {
		super(world, x, y, isStatic);
		this.setId(idPlayer);
		this.setPlayerName(nom);
		this.setIdLevel(idLevel);
		this.setIdInventory(idInventory);
		this.setIdChapter(idChapter);
		this.setBody(BodyBuilder.createPlayerBody(this.getWorld(), x, y, WIDTH, HEIGHT, isStatic, fixedRotation,
				BIT_PLAYER, (short) (BIT_ENNEMY | BIT_WALL | BIT_LIGHT | BIT_DOOR), (short) 0, this));
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
	public void setEnergy(final IEnergy energy) {
		this.energy = energy;
	}

	@Override
	public int getChapter() {
		return this.idChapter;
	}

	@Override
	public void setChapter(final int idChapter) {
		this.idChapter = idChapter;
	}

	@Override
	public IHealth getHealth() {
		return this.health;
	}

	@Override
	public void setHealth(final IHealth health) {
		this.health = health;
	}

	@Override
	public IBewareOfTruthModel getBewareOfTruthModel() {
		return this.bewareOfTruthModel;
	}

	@Override
	public void setBewareOfTruthModel(final IBewareOfTruthModel bewareOfTruthModel) {
		this.bewareOfTruthModel = bewareOfTruthModel;
	}

	public int getIdChapter() {
		return this.idChapter;
	}

	public void setIdChapter(final int idChapter) {
		this.idChapter = idChapter;
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
	public int getId() {
		return this.id;
	}

	@Override
	public void setId(final int id) {
		this.id = id;
	}

	@Override
	public int getIdInventory() {
		return this.idInventory;
	}

	@Override
	public void setIdInventory(final int idInventory) {
		this.idInventory = idInventory;
	}

	@Override
	public String getPlayerName() {
		return this.playerName;
	}

	@Override
	public void setPlayerName(final String playerName) {
		this.playerName = playerName;
	}

	public void hit() {
		System.out.println("Hit");
	}
}
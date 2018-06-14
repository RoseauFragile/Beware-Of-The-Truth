package bewareofthetruth.model.gameMechanics.mobile;

import com.badlogic.gdx.graphics.g2d.Sprite;

import bewareofthetruth.contract.model.data.IBewareOfTruthModel;
import bewareofthetruth.contract.model.gameMecanism.IPlayer;
import bewareofthetruth.contract.model.utils.IEnergy;
import bewareofthetruth.contract.model.utils.IHealth;
import bewareofthetruth.model.gameMechanics.entity.Entity;

public class Player extends Entity implements IPlayer {

	private IHealth health;
	private IEnergy energy;
	private int idChapter;
	private IBewareOfTruthModel bewareOfTruthModel;
	private int idLevel;
	private int id;
	private int idInventory;
	private String playerName;
	private static Sprite SPRITE = new Sprite();

	public Player() {
		super(SPRITE);
	}

	public Player(int idPlayer, String nom, int idLevel, int idInventory, int idChapter) {
		super(SPRITE);
		this.setId(idPlayer);
		this.setPlayerName(playerName);
		this.setIdLevel(idLevel);
		this.setIdInventory(idInventory);
		this.setIdChapter(idChapter);
		System.out.println("nouveau player créer");
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
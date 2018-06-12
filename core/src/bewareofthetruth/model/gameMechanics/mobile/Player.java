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
	private static Sprite SPRITE = new Sprite();

	public Player() {
		super(SPRITE);
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

}
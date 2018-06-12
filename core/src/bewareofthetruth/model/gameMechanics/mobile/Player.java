package bewareofthetruth.model.gameMechanics.mobile;

import bewareofthetruth.contract.model.data.IBewareOfTruthModel;
import bewareofthetruth.contract.model.gameMecanism.IPlayer;
import bewareofthetruth.contract.model.utils.IEnergy;
import bewareofthetruth.contract.model.utils.IHealth;

public class Player implements IPlayer {

	private IHealth health;
	private IEnergy energy;
	private int idChapter;
	private IBewareOfTruthModel bewareOfTruthModel;

	public Player() {

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
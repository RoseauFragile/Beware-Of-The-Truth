package bewareofthetruth.contract.model.gameMecanism;

import bewareofthetruth.contract.model.data.IBewareOfTruthModel;
import bewareofthetruth.contract.model.utils.IEnergy;
import bewareofthetruth.contract.model.utils.IHealth;

public interface IPlayer {

	public IEnergy getEnergy();

	public void setEnergy(IEnergy energy);

	public int getChapter();

	public void setChapter(int idChapter);

	public IHealth getHealth();

	public void setHealth(IHealth health);

	public IBewareOfTruthModel getBewareOfTruthModel();

	public void setBewareOfTruthModel(IBewareOfTruthModel bewareOfTruthModel);
}

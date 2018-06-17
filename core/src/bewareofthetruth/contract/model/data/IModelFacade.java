package bewareofthetruth.contract.model.data;

import bewareofthetruth.contract.model.gameMecanism.IPlayer;
import bewareofthetruth.main.Constants;

public interface IModelFacade {
	IBewareOfTruthModel getBewareOfTruthModel();

	public IPlayer getPlayer();
	
	public Constants getConstants();

	public void setConstants(Constants constants);
}

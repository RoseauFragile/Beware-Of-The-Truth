package bewareofthetruth.contract.model.data;

import bewareofthetruth.contract.model.gameMecanism.IPlayer;

public interface IModelFacade {
	IBewareOfTruthModel getBewareOfTruthModel();

	public IPlayer getPlayer();
}

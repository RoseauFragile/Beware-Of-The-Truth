package bewareofthetruth.model;

import java.sql.SQLException;

import bewareofthetruth.contract.model.data.IBewareOfTruthModel;
import bewareofthetruth.contract.model.data.IModelFacade;
import bewareofthetruth.contract.model.gameMecanism.IPlayer;

public class ModelFacade implements IModelFacade {

	private IBewareOfTruthModel bewareOfTruthModel;

	public ModelFacade() throws SQLException {
		super();
		System.out.println("ModelFacade créer");
		this.setBewareOfTruthModel(new BewareOfTruthModel());
		this.getBewareOfTruthModel().setModelFacade(this);
	}

	@Override
	public IBewareOfTruthModel getBewareOfTruthModel() {
		return this.bewareOfTruthModel;
	}

	public void setBewareOfTruthModel(IBewareOfTruthModel bewareOfTruthModel) {
		this.bewareOfTruthModel = bewareOfTruthModel;
	}

	@Override
	public IPlayer getPlayer() {
		return this.getBewareOfTruthModel().getPlayer();
	}
}

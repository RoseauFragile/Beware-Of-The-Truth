package bewareofthetruth.model;

import java.sql.SQLException;

import bewareofthetruth.contract.model.data.IBewareOfTruthModel;
import bewareofthetruth.contract.model.data.IModelFacade;
import bewareofthetruth.contract.model.gameMecanism.IPlayer;
import bewareofthetruth.model.util.Constants;

public class ModelFacade implements IModelFacade {

	private IBewareOfTruthModel bewareOfTruthModel;
	private Constants constants;

	public ModelFacade() throws SQLException {
		super();
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
		return this.getBewareOfTruthModel().getLevel().getPlayer();
	}

	public Constants getConstants() {
		return this.constants;
	}

	public void setConstants(Constants constants) {
		this.constants = constants;
	}
}

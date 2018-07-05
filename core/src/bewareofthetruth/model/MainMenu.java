package bewareofthetruth.model;

import bewareofthetruth.contract.model.data.IBewareOfTruthModel;
import bewareofthetruth.contract.model.data.IMainMenu;

public class MainMenu implements IMainMenu {

	private IBewareOfTruthModel bewareOfTruthModel;

	public MainMenu() {
	}

	@Override
	public void loadCinema() {
		// TODO Auto-generated method stub

	}

	@Override
	public IBewareOfTruthModel getBewareOfTruthModel() {
		return this.bewareOfTruthModel;
	}

	@Override
	public void setBewareOfTruthModel(IBewareOfTruthModel bewareOfTruthModel) {
		this.bewareOfTruthModel = bewareOfTruthModel;
	}

	public void setBewareOfTruthPlayer() {

	}
}

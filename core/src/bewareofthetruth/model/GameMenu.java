package bewareofthetruth.model;

import bewareofthetruth.contract.model.data.IBewareOfTruthModel;
import bewareofthetruth.contract.model.data.IGameMenu;

public class GameMenu implements IGameMenu {

	private IBewareOfTruthModel bewareOfTruthModel;

	public GameMenu() {
		System.out.println("GameMenu créer");
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

package bewareofthetruth.model;

import bewareofthetruth.contract.model.data.IBewareOfTruthModel;
import bewareofthetruth.contract.model.data.IHud;

public class Hud implements IHud {

	private IBewareOfTruthModel bewareOfTruthModel;

	public Hud() {
		System.out.println("Hud créer");
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
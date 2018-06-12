package bewareofthetruth.model;

import bewareofthetruth.contract.model.data.IBewareOfTruthModel;
import bewareofthetruth.contract.model.data.IOptions;

public class Options implements IOptions {

	private IBewareOfTruthModel bewareOfTruthModel;

	public Options() {
		System.out.println("Options créer");
	}

	@Override
	public void setKeys() {

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
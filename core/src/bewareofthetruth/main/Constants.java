package bewareofthetruth.main;

import com.badlogic.gdx.graphics.Camera;
import bewareofthetruth.model.ModelFacade;

public class Constants {

	private ModelFacade modelFacade;
	public final Camera CAMERA = this.modelFacade.getBewareOfTruthModel().getCam().getCamera();
	
	public Constants(ModelFacade modelFacade) {
		this.setModelFacade(modelFacade);
	}

	public ModelFacade getModelFacade() {
		return this.modelFacade;
	}

	public void setModelFacade(ModelFacade modelFacade) {
		this.modelFacade = modelFacade;
	}
}

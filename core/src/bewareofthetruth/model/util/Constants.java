package bewareofthetruth.model.util;

import com.badlogic.gdx.graphics.Camera;
import bewareofthetruth.model.ModelFacade;

public final class Constants {

	private ModelFacade modelFacade;
	public final Camera CAMERA = this.modelFacade.getBewareOfTruthModel().getCam().getCamera();
	public static final float PPM = 32;
	
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

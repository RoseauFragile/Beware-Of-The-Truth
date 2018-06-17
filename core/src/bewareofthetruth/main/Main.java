package bewareofthetruth.main;

import java.sql.SQLException;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import bewareofthetruth.contract.model.data.IModelFacade;
import bewareofthetruth.model.ModelFacade;

public class Main implements ApplicationListener {

	private IModelFacade modelFacade;
	
	@Override
	public void create() {

			try {
				this.modelFacade = new ModelFacade();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	@Override
	public void resize(int width, int height) {
		this.modelFacade.getBewareOfTruthModel().getCam().resize(width /2, height /2);
	}

	@Override
	public void render() {
		update(Gdx.graphics.getDeltaTime());
		Gdx.gl.glClearColor(0, 0, 1, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		this.modelFacade.getBewareOfTruthModel().getDebugRenderer().render(this.modelFacade.getBewareOfTruthModel().getWorld(), this.modelFacade.getBewareOfTruthModel().getCam().getCamera().combined);
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		this.modelFacade.getBewareOfTruthModel().getWorld().dispose();
		this.modelFacade.getBewareOfTruthModel().getDebugRenderer().dispose();
	}
	
	public void update(float delta) {
		this.modelFacade.getBewareOfTruthModel().getWorld().step(1/60f, 6, 2);
		this.modelFacade.getBewareOfTruthModel().getCam().cameraUpdate(delta);
	}
}

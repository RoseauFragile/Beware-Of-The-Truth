package bewareofthetruth.main;

import java.sql.SQLException;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import bewareofthetruth.contract.model.data.IModelFacade;
import bewareofthetruth.model.ModelFacade;

public class Main implements ApplicationListener {

	private SpriteBatch batch;
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
	}
}

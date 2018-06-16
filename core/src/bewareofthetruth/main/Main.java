package bewareofthetruth.main;

import java.sql.SQLException;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import bewareofthetruth.contract.model.data.IModelFacade;
import bewareofthetruth.model.ModelFacade;

public class Main implements ApplicationListener {

	private SpriteBatch batch;
	private IModelFacade modelFacade;
    private float elapsedTime = 0;
	
	@Override
	public void create() {

			try {
				this.modelFacade = new ModelFacade();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			this.batch = new SpriteBatch();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void render() {
		/*Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.batch.begin();
        this.batch.draw(this.modelFacade.getBewareOfTruthModel().getPlayer().getTexture(), 200 , 100);
        this.batch.draw(this.modelFacade.getBewareOfTruthModel().getPlayer().getTexture(), 200 , 200);
		for (int i = 0; i < this.modelFacade.getBewareOfTruthModel().getPlayer().getRegions().length; i++) {
			this.modelFacade.getBewareOfTruthModel().getPlayer().getRegions().;		// #8
		}
        //this.elapsedTime += Gdx.graphics.getDeltaTime();
        //this.batch.draw(this.modelFacade.getBewareOfTruthModel().getPlayer().getAnimation().getKeyFrame(elapsedTime, true), 0, 0);
        this.batch.end();*/
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		/*batch.dispose();
		this.modelFacade.getBewareOfTruthModel().getPlayer().getTextureAtlas().dispose();	*/
	}
}

package bewareofthetruth.main;

import java.sql.SQLException;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import bewareofthetruth.contract.model.data.IModelFacade;
import bewareofthetruth.model.ModelFacade;
import static bewareofthetruth.model.util.Constants.PPM;

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
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//BATCH START
		this.modelFacade.getBewareOfTruthModel().getBatch().begin();
	
		
		//DRAW PLAYER
		this.modelFacade.getBewareOfTruthModel().getBatch().draw(this.modelFacade.getBewareOfTruthModel().getPlayer().getRegions()[0][0],
				this.modelFacade.getBewareOfTruthModel().getPlayer().getBody().getPosition().x * PPM - 
				(this.modelFacade.getBewareOfTruthModel().getPlayer().getRegions()[0][0].getRegionWidth() / 2),
				this.modelFacade.getBewareOfTruthModel().getPlayer().getBody().getPosition().y * PPM - 
				(this.modelFacade.getBewareOfTruthModel().getPlayer().getRegions()[0][0].getRegionHeight() / 2));
		
		
		//DRAW ZOMBIE
		this.modelFacade.getBewareOfTruthModel().getBatch().draw(this.modelFacade.getBewareOfTruthModel().getZombie().getRegions()[0][1],
				this.modelFacade.getBewareOfTruthModel().getZombie().getBody().getPosition().x * PPM - 
				(this.modelFacade.getBewareOfTruthModel().getZombie().getRegions()[0][1].getRegionWidth() / 2),
				this.modelFacade.getBewareOfTruthModel().getZombie().getBody().getPosition().y * PPM - 
				(this.modelFacade.getBewareOfTruthModel().getZombie().getRegions()[0][1].getRegionHeight() / 2));
		
		//DRAW ZOMBIE2
		this.modelFacade.getBewareOfTruthModel().getBatch().draw(this.modelFacade.getBewareOfTruthModel().getZombie2().getRegions()[0][2],
				this.modelFacade.getBewareOfTruthModel().getZombie2().getBody().getPosition().x * PPM - 
				(this.modelFacade.getBewareOfTruthModel().getZombie2().getRegions()[0][2].getRegionWidth() / 2),
				this.modelFacade.getBewareOfTruthModel().getZombie2().getBody().getPosition().y * PPM - 
				(this.modelFacade.getBewareOfTruthModel().getZombie2().getRegions()[0][2].getRegionHeight() / 2));
		
		//BATCH END
		this.modelFacade.getBewareOfTruthModel().getBatch().end();

		this.modelFacade.getBewareOfTruthModel().getTmr().render();
		this.modelFacade.getBewareOfTruthModel().getDebugRenderer().render(this.modelFacade.getBewareOfTruthModel().getWorld(), this.modelFacade.getBewareOfTruthModel().getCam().getCamera().combined.scl(PPM));
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}
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
		this.modelFacade.getBewareOfTruthModel().getBatch().dispose();
		this.modelFacade.getBewareOfTruthModel().getTmr().dispose();
		this.modelFacade.getBewareOfTruthModel().getLevel().getMap().getTiledMap().dispose();
	}
	
	public void update(float delta) {
		this.modelFacade.getBewareOfTruthModel().getWorld().step(1/60f, 6, 2);
		inputUpdate(delta);
		this.modelFacade.getBewareOfTruthModel().getTmr().setView(this.modelFacade.getBewareOfTruthModel().getCam().getCamera());
		this.modelFacade.getBewareOfTruthModel().getCam().cameraUpdate(delta);
		this.modelFacade.getBewareOfTruthModel().getTmr().setView(this.modelFacade.getBewareOfTruthModel().getCam().getCamera());
		this.modelFacade.getBewareOfTruthModel().getBatch().setProjectionMatrix(this.modelFacade.getBewareOfTruthModel().getCam().getCamera().combined);
	}
	
	public void inputUpdate(float delta) {
		
		int horizontalForce = 0;
		int verticalForce = 0;
		
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			horizontalForce -= 1;
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			horizontalForce += 1;
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.UP)){
			verticalForce += 1;
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			verticalForce -= 1;
		}
		
		this.modelFacade.getBewareOfTruthModel().getPlayer().getBody().setLinearVelocity(horizontalForce * 5, verticalForce * 5);
	}
}

package bewareofthetruth.main;

import java.sql.SQLException;
import com.badlogic.gdx.ApplicationListener;
import bewareofthetruth.contract.model.data.IModelFacade;
import bewareofthetruth.model.ModelFacade;

public class Main implements ApplicationListener {

	@Override
	public void create() {

			try {
				IModelFacade modelFacade = new ModelFacade();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}

package bewareofthetruth.dao;

import com.badlogic.gdx.Gdx;

public class BewareOfTheTruthDAO extends AbstractDAO{

	private MapDAO _mapDAO;
	private static final String TAG = BewareOfTheTruthDAO.class.getSimpleName();
	
	public BewareOfTheTruthDAO() {
		super();
		Gdx.app.debug(TAG, "DEBUG");
		this._mapDAO = new MapDAO();
	}

	public MapDAO getMapDAO() {
		return _mapDAO;
	}
}

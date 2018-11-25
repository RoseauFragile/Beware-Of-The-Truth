package bewareofthetruth.dao;

public class BewareOfTheTruthDAO extends AbstractDAO{

	private MapDAO _mapDAO;
	
	public BewareOfTheTruthDAO() {
		super();
		this._mapDAO = new MapDAO();
	}

	public MapDAO getMapDAO() {
		return _mapDAO;
	}
}

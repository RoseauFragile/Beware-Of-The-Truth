package bewareofthetruth.dao;

public class MapSql {

	private int _id;
	private String _mapPath;
	
	public MapSql(final int id, final String mapPath) {
		this._id = id;
		this._mapPath = mapPath;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String get_mapPath() {
		return _mapPath;
	}

	public void set_mapPath(String _mapPath) {
		this._mapPath = _mapPath;
	}

}

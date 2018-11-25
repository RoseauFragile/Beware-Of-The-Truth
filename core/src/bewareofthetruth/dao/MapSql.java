package bewareofthetruth.dao;

public class MapSql {

	private int _id;
	private String _mapPath;
	private String _mapName;
	
	public MapSql(final int id,final String mapName, final String mapPath) {
		this._id = id;
		this._mapName = mapName;
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

	public String get_mapName() {
		return _mapName;
	}

	public void set_mapName(String _mapName) {
		this._mapName = _mapName;
	}

}

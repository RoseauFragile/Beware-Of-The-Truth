package bewareofthetruth.dao;

public class MapSql {

	private int _id;
	private String _mapPath;
	private String _mapName;
	private String _mapMusicPath;
	
	public MapSql(final int id,final String mapName, final String mapPath, final String mapMusicPath) {
		this._id = id;
		this._mapName = mapName;
		this._mapPath = mapPath;
		this._mapMusicPath = mapMusicPath;
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

	public String get_mapMusicPath() {
		return _mapMusicPath;
	}

	public void set_mapMusicPath(String _mapMusicPath) {
		this._mapMusicPath = _mapMusicPath;
	}

}

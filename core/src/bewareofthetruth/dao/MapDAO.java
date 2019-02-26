package bewareofthetruth.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.badlogic.gdx.utils.Array;
import bewareofthetruth.entity.EntityFactory;

public class MapDAO extends AbstractDAO{
	
	private int _idChapter;
	private String _MapPath;
	private ArrayList<EntityFactory.EntityName> _entities;
	private static int _idMapColumn = 1;
	private static int _mapNameColumn = 2;
	private static int _mapPathColumn = 3;
	private static final String TAG = MapDAO.class.getSimpleName();
	
	public MapDAO() {
		System.out.println(TAG);
	}

	public MapSql getMapById() throws SQLException {

		return null;
	}
	
	public int get_idChapter() {
		return _idChapter;
	}

	public void set_idChapter(int idChapter) {
		this._idChapter = idChapter;
	}

	public String get_MapPath() {
		return _MapPath;
	}

	public void set_MapPath(String MapPath) {
		this._MapPath = MapPath;
	}

	public ArrayList<EntityFactory.EntityName> get_entities() {
		return _entities;
	}

	public void set_entities(ArrayList<EntityFactory.EntityName> entities) {
		this._entities = entities;
	}

	@SuppressWarnings({ "rawtypes", "unchecked", "static-access" })
	public Array<MapSql> getMapSql() throws SQLException {
		Array<MapSql> mapSql = new Array();
        String sql = "SELECT map.id, map.name, map.path FROM map";
        try (
             ResultSet rs    = this.executeQuery(sql)){
            while (rs.next()) {
				mapSql.add(new MapSql(rs.getInt(_idMapColumn),rs.getString(_mapNameColumn), rs.getString(_mapPathColumn)));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		return mapSql;
	}
}

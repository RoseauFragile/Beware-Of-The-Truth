package bewareofthetruth.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bewareofthetruth.entity.EntityFactory;

public class MapDAO extends AbstractDAO{
	
	private int _idChapter;
	private String _MapPath;
	private ArrayList<EntityFactory.EntityName> _entities;
	private static int _idMapColumn = 1;
	private static int _mapNameColumn = 2;
	private static int _mapPathColumn = 3;
	
	public MapDAO() {
	}

	public MapSql getMapById() throws SQLException {
		MapSql mapSql = null;
		
		//TODO a modifier avec la future bdd
		String getMapById = ("SELECT Level.ID_Level, Level.Name_Level, Level.Height, Level.Width, Level.Source_Map\r\n"
				+ "FROM Level, Chapter, comporte\r\n"
				+ "WHERE Level.ID_Level = comporte.ID_Level AND Chapter.ID_Chapter = comporte.ID_Chapter AND comporte.ID_Chapter = "
								);
		ResultSet rs = executeQuery(getMapById);
		if(rs.next() == true) {
			mapSql = new MapSql(rs.getInt(_idMapColumn),rs.getString(_mapNameColumn), rs.getString(_mapPathColumn));
		}
		return mapSql;
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

}

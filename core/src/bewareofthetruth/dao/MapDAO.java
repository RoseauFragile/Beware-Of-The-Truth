package bewareofthetruth.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.badlogic.gdx.utils.Array;
import bewareofthetruth.entity.EntityFactory;

public class MapDAO extends AbstractDAO{
	
	private static int _idMapColumn = 1;
	private static int _mapNameColumn = 2;
	private static int _mapPathColumn = 3;
	private static int _mapMusicColumn = 4;
	private static final String TAG = MapDAO.class.getSimpleName();
	
	public MapDAO() {
		System.out.println(TAG);
	}

	public MapSql getMapById() throws SQLException {

		return null;
	}

	public ArrayList<EntityFactory.EntityName> get_entitiesByMapId(int id) {
		ArrayList<EntityFactory.EntityName> entities = new ArrayList();
		String sql = "SELECT entite.type, contain.id_entite FROM contain, entite WHERE contain.id_map = "+id+ " AND contain.id_entite = entite.id";;
        try (
             ResultSet rs    = this.executeQuery(sql)){
            while (rs.next()) {
				entities.add(EntityFactory.EntityName.valueOf(rs.getString(1)));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		return entities;
	}

	@SuppressWarnings({ "rawtypes", "unchecked", "static-access" })
	public Array<MapSql> getMapSql() throws SQLException {
		Array<MapSql> mapSql = new Array();
        String sql = "SELECT map.id, map.name, map.path, map.musicpath FROM map";
        try (
             ResultSet rs    = this.executeQuery(sql)){
            while (rs.next()) {
				mapSql.add(new MapSql(rs.getInt(_idMapColumn),rs.getString(_mapNameColumn), rs.getString(_mapPathColumn), rs.getString(_mapMusicColumn)));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		return mapSql;
	}
}

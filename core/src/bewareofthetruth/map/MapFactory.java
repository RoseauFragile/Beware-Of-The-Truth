package bewareofthetruth.map;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import bewareofthetruth.dao.BewareOfTheTruthDAO;
import bewareofthetruth.dao.MapSql;
import bewareofthetruth.entity.EntityFactory;

public class MapFactory {
    
    private static Array<Map> mapTable = new Array<Map>();
    private static Array<MapSql> mapSql;
    private static final String TAG = MapFactory.class.getSimpleName();
    
    public static void clearCache(){
        for( Map map: mapTable){
            map.dispose();
        }
        mapTable.clear();
    }

	public Array<MapSql> getMapSql() {
		return mapSql;
	}

	public void setMapSql(BewareOfTheTruthDAO bewareOfTheTruthDAO) {
		try {
			this.mapSql = bewareOfTheTruthDAO.getMapDAO().getMapSql();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Iterator<MapSql> it = this.mapSql.iterator(); 
		while (it.hasNext()) {
		       MapSql s = it.next();
		       Gdx.app.debug(TAG, " : map Sql : "+it + " : " + " : "+s.get_id() + " : " + s.get_mapName() + " : "+s.get_mapPath());
		}
	}

	public static Map getMapById(int id, BewareOfTheTruthDAO bewareOfTheTruthDAO) {
		Map map =null;
		for(int i = 0; i < mapTable.size;i++) {
			if(mapTable.get(i).getId() == id) {
				map = mapTable.get(i);
			};
		}
		if(map == null) {
			for(int i = 0; i < mapSql.size;i++) {
				if(mapSql.get(i).get_id() == id) {
			        ArrayList<EntityFactory.EntityName> arrayListOfEntities = bewareOfTheTruthDAO.getMapDAO().get_entitiesByMapId(mapSql.get(i).get_id()) ;
					map = new Map(mapSql.get(i).get_id(),mapSql.get(i).get_mapPath(),mapSql.get(i).get_mapMusicPath(), arrayListOfEntities);
					mapTable.add(map);
				};
			}
		}
		return map;
	}

	public int getMapIdByName(String mapName) {
		int id = 0;
		for(MapSql map : mapSql) {
			Gdx.app.debug(TAG, " DEBUG TP V2 : MAP IN LOOP ID = " + map.get_id() +" MAP IN LOOP NAME = " + map.get_mapName() + " MAP REQUEST : " + mapName);
			if(map.get_mapName().equals(mapName)) {
				id = map.get_id();
			};
		}
		return id;
	}
}

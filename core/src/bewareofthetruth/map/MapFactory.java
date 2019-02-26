package bewareofthetruth.map;

import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Iterator;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import bewareofthetruth.map.zone1.ZoneOneDotOne;
import bewareofthetruth.map.zone1.ZoneOneDotThree;
import bewareofthetruth.map.zone1.ZoneOneDotTwo;
import bewareofthetruth.map.zone1.ZoneTest;
import bewareofthetruth.dao.BewareOfTheTruthDAO;
import bewareofthetruth.dao.MapSql;

public class MapFactory {
	//AllMaps for the game
	//TODO Soit on garde cette architecture et on créer une map pour chaque Level ce qui est en soit efficaces pour un rpg mais LOURD soit on fais une factory plus élaboré avec la bdd
	//De plus il faudra avoir un algo plus réfléchi sur les teleporters, en effet cela ne calcule pas le point de spawn player le plus près du tp correpondant à la zone d'ou l'on vient
    private static Hashtable<MapType,Map> _mapTable = new Hashtable<MapType, Map>();
    private Array<MapSql> mapSql;
    private static final String TAG = MapFactory.class.getSimpleName();
    
    public static enum MapType{
        ZONE_1_1,
        ZONE_1_2,
        ZONE_1_3,
        ZONE_TEST
    }
	
    //TODO l'idée pour passer en BDD sera de passer d'un référencement en mapType à un référencement en IdMap avec la Bdd, 
    //en gros au lancement du jeu on récupère la première map en fonction de l'ID de la map contenue dans la sauvegarde du jeu,
    // OU ALORS si cela s'avère trop compliqué on peut tout simplement créer un attribut mapType dans la BDD et faireun énorme switch case ici
	static public Map getMap(MapType mapType) {
		Map map =null;
		switch(mapType) {
		case ZONE_1_1:
			map = _mapTable.get(MapType.ZONE_1_1);
			if(map == null) {
				map = new ZoneOneDotOne();
				_mapTable.put(MapType.ZONE_1_1, map);
			}
			break;
		case ZONE_1_2:
			map = _mapTable.get(MapType.ZONE_1_2);
			if(map == null) {
				map = new ZoneOneDotTwo();
				_mapTable.put(MapType.ZONE_1_2, map);
			}
			break;
		case ZONE_1_3:
			map = _mapTable.get(MapType.ZONE_1_3);
			if(map == null) {
				map = new ZoneOneDotThree();
				_mapTable.put(MapType.ZONE_1_3, map);
			}
			break;
		case ZONE_TEST:
			map = _mapTable.get(MapType.ZONE_TEST);
			if(map == null) {
				map = new ZoneTest();
				_mapTable.put(MapType.ZONE_TEST, map);
			}
			break;
		default:
				break;
		}
		return map;
	}
	
    public static void clearCache(){
        for( Map map: _mapTable.values()){
            map.dispose();
        }
        _mapTable.clear();
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
}

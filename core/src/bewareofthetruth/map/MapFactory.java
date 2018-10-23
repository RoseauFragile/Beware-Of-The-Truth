package bewareofthetruth.map;

import java.util.Hashtable;

import bewareofthetruth.map.zone1.ZoneOneDotOne;
import bewareofthetruth.map.zone1.ZoneOneDotThree;
import bewareofthetruth.map.zone1.ZoneOneDotTwo;

public class MapFactory {
	//AllMaps for the game
	//TODO Soit on garde cette architecture et on créer une map pour chaque Level ce qui est en soit efficaces pour un rpg mais LOURD soit on fais une factory plus élaboré avec la bdd
    private static Hashtable<MapType,Map> _mapTable = new Hashtable<MapType, Map>();

    public static enum MapType{
        TOP_WORLD,
        TOWN,
        CASTLE_OF_DOOM
    }
	
	static public Map getMap(MapType mapType) {
		Map map =null;
		switch(mapType) {
		case TOP_WORLD:
			map = _mapTable.get(MapType.TOP_WORLD);
			if(map == null) {
				map = new ZoneOneDotTwo();
				_mapTable.put(MapType.TOP_WORLD, map);
			}
			break;
		case TOWN:
			map = _mapTable.get(MapType.TOWN);
			if(map == null) {
				map = new ZoneOneDotOne();
				_mapTable.put(MapType.TOWN, map);
			}
			break;
		case CASTLE_OF_DOOM:
			map = _mapTable.get(MapType.CASTLE_OF_DOOM);
			if(map == null) {
				map = new ZoneOneDotThree();
				_mapTable.put(MapType.CASTLE_OF_DOOM, map);
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
}

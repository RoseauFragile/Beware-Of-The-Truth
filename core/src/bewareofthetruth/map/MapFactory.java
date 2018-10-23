package bewareofthetruth.map;

import java.util.Hashtable;

import bewareofthetruth.map.zone1.ZoneOneDotOne;
import bewareofthetruth.map.zone1.ZoneOneDotThree;
import bewareofthetruth.map.zone1.ZoneOneDotTwo;

public class MapFactory {
	//AllMaps for the game
	//TODO Soit on garde cette architecture et on cr�er une map pour chaque Level ce qui est en soit efficaces pour un rpg mais LOURD soit on fais une factory plus �labor� avec la bdd
	//De plus il faudra avoir un algo plus r�fl�chi sur les teleporters, en effet cela ne calcule pas le point de spawn player le plus pr�s du tp correpondant � la zone d'ou l'on vient
    private static Hashtable<MapType,Map> _mapTable = new Hashtable<MapType, Map>();

    public static enum MapType{
        ZONE_1_1,
        ZONE_1_2,
        ZONE_1_3
    }
	
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
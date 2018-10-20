package bewareofthetruth.entity.components;

import bewareofthetruth.entity.components.MapFactory.MapType;

public class CastleDoomMap extends Map {
	//TODO a modif
	private static String _mapPath = "maps/town.tmx";
	CastleDoomMap() {
		super(MapFactory.MapType.CASTLE_OF_DOOM, _mapPath);
		
	}

}

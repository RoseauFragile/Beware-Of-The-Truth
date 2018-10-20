package bewareofthetruth.entity.components;



public class CastleDoomMap extends Map {
	//TODO a modif
	private static String _mapPath = "maps/town.tmx";
	CastleDoomMap() {
		super(MapFactory.MapType.CASTLE_OF_DOOM, _mapPath);
		
	}

}

package bewareofthetruth.entity.components;



public class TownMap extends Map {
	//TODO a modif
	private static String _mapPath = "maps/town.tmx";
	TownMap() {
		super(MapFactory.MapType.TOWN, _mapPath);
		
	}

}

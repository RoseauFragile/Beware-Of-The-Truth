package bewareofthetruth.entity.components;



public class TopWorldMap extends Map {

	//TODO a modif
	private static String _mapPath = "maps/town.tmx";
	TopWorldMap() {
		super(MapFactory.MapType.TOP_WORLD, _mapPath);
		
	}

}

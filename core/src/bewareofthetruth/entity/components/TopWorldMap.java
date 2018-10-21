package bewareofthetruth.entity.components;



public class TopWorldMap extends Map {

	
	private static String _mapPath = "tiledMap/zone1.2.tmx";
	TopWorldMap() {
		super(MapFactory.MapType.TOP_WORLD, _mapPath);
		
	}
	@Override
	public void unloadMusic() {
	}
	@Override
	public void loadMusic() {
	}

}

package bewareofthetruth.entity.components;



public class CastleDoomMap extends Map {
	
	private static String _mapPath = "tiledMap/zone1.3.tmx";
	CastleDoomMap() {
		super(MapFactory.MapType.CASTLE_OF_DOOM, _mapPath);
		
	}
	@Override
	public void unloadMusic() {
	}
	@Override
	public void loadMusic() {
	}

}

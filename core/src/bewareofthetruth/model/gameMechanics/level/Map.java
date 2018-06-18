package bewareofthetruth.model.gameMechanics.level;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import bewareofthetruth.contract.model.data.ILevel;
import bewareofthetruth.contract.model.data.IMap;
import bewareofthetruth.contract.model.data.IMiniMap;

public class Map implements IMap {
	
	private String tiledMapSource;
	private TiledMap map;
	private IMiniMap miniMap;
	private String mapName;
	private ILevel level;
	
	public Map(String sourceMap) {
		System.out.println("NOUVELLE MAP");
		this.setTiledMapSource(sourceMap);
		this.setTiledMap(this.getTiledMapSource());
	}
	
	public String getTiledMapSource() {
		return tiledMapSource;
	}
	
	public void setTiledMapSource(String tiledMapSource) {
		this.tiledMapSource = tiledMapSource;
	}

	@Override
	public String getMapName() {
		return this.mapName;
	}

	@Override
	public IMiniMap getMiniMap() {
		return this.miniMap;
	}

	@Override
	public IMiniMap getPlayerPosition() {
		return null;
	}

	@Override
	public void setLevel(ILevel level) {
		this.level = level;
	}

	@Override
	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	@Override
	public void setMiniMap(IMiniMap miniMap) {
		this.miniMap = miniMap;
	}

	public TiledMap getTiledMap() {
		return this.map;
	}

	public void setTiledMap(String map) {
		this.map = new TmxMapLoader().load("tiledMap/"+map);
		System.out.println("nouvelle tiled map : " + this.getTiledMap().getProperties());
	}
	
	public ILevel getLevel() {
		return this.level;
	}
}

package bewareofthetruth.model.gameMechanics.level;

import bewareofthetruth.contract.model.data.ILevel;
import bewareofthetruth.contract.model.data.IMap;
import bewareofthetruth.contract.model.data.IMiniMap;

public class Map implements IMap {
	
	private String tiledMapSource;
	
	public Map() {
		
	}
	
	public String getTiledMapSource() {
		return tiledMapSource;
	}
	
	public void setTiledMapSource(String tiledMapSource) {
		this.tiledMapSource = tiledMapSource;
	}

	@Override
	public String getMapName() {
		return null;
	}

	@Override
	public IMiniMap getMiniMap() {
		return null;
	}

	@Override
	public IMiniMap getPlayerPosition() {
		return null;
	}

	@Override
	public void setLevel(ILevel level) {
	}

	@Override
	public void setMapName(String mapName) {
	}

	@Override
	public void setMiniMap(IMiniMap miniMap) {
	}
	
}

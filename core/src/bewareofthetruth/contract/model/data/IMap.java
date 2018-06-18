package bewareofthetruth.contract.model.data;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

/* **** POUR BEN **** INTERFACE POUR LA MAP, QUI CONTIENT LA TILED MAP */

public interface IMap {

	public String getMapName();

	public IMiniMap getMiniMap();

	public IMiniMap getPlayerPosition();

	public void setLevel(final ILevel level);

	public void setMapName(String mapName);

	public void setMiniMap(IMiniMap miniMap);
	
	public TiledMap getTiledMap();

	public void setTiledMap(String map);
}

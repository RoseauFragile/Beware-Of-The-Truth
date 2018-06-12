package bewareofthetruth.contract.model.data;

/* **** POUR BEN **** INTERFACE POUR LA MAP, QUI CONTIENT LA TILED MAP */

public interface IMap {

	public String getMapName();

	public IMiniMap getMiniMap();

	public IMiniMap getPlayerPosition();

	public void setLevel(final ILevel level);

	public void setMapName(String mapName);

	public void setMiniMap(IMiniMap miniMap);
}

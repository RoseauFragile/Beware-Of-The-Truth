package bewareofthetruth;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.assets.AssetManager;

public final class Utility {

	public static final AssetManager ASSET_MANAGER = new AssetManager();
	private static final String TAG = Utility.class.getSimpleName();
	private static InternalFileHandleResolver FILE_PATH_RESOLVER = new InternalFileHandleResolver();
	
	public static void unloadAsset(String assetFilenamePath) {
		if (ASSET_MANAGER.isLoaded(assetFilenamePath)) {
			ASSET_MANAGER.unload(assetFilenamePath);
		} else {
			Gdx.app.debug(TAG, "Ressource non charge, rien a decharger: " +assetFilenamePath);
		}
	}
	
	public static float loadCompleted() {
		return ASSET_MANAGER.getProgress();
	}
	
	public static int numberAssetsQueued() {
		return ASSET_MANAGER.getQueuedAssets();
	}
	
	public static boolean updateAssetLoading() {
		return ASSET_MANAGER.update();
	}
	
	public static boolean isAssetLoaded(String fileName) {
		return ASSET_MANAGER.isLoaded(fileName);
	}
	
	public static void loadMapAsset (String mapFilenamePath) {
		if ( mapFilenamePath == null || mapFilenamePath.isEmpty()) {
			return;
		}
		//load asset
		if (FILE_PATH_RESOLVER.resolve(mapFilenamePath).exists()) {
			ASSET_MANAGER.setLoader(TiledMap.class, new TmxMapLoader(FILE_PATH_RESOLVER));
			ASSET_MANAGER.load(mapFilenamePath, TiledMap.class);
			
			//until we add loading screen
			//just block until we load the map
			ASSET_MANAGER.finishLoadingAsset(mapFilenamePath);
			Gdx.app.debug(TAG, "Map charge :" + mapFilenamePath);
		} else {
			Gdx.app.debug(TAG, "Map introuvable :" + mapFilenamePath);
		}		
	}
	
	public static TiledMap getMapAsset(String mapFilenamePath) {
		TiledMap map = null;
		
		if (ASSET_MANAGER.isLoaded(mapFilenamePath)) {
			map = ASSET_MANAGER.get(mapFilenamePath, TiledMap.class);
		} else {
			Gdx.app.debug(TAG,"Map non charge : " + mapFilenamePath);
		}
		return map;
	}
	
	public static void loadTextureAsset (String textureFilenamePath) {
		if ( textureFilenamePath == null || textureFilenamePath.isEmpty()) {
			return;
		}
		
		//load asset
		if (FILE_PATH_RESOLVER.resolve(textureFilenamePath).exists()) {
			ASSET_MANAGER.setLoader(Texture.class, new TextureLoader(FILE_PATH_RESOLVER));
			ASSET_MANAGER.load(textureFilenamePath, Texture.class);
			//Until we add loading screen, just block until we load the map
			ASSET_MANAGER.finishLoadingAsset(textureFilenamePath);
		} else {
			Gdx.app.debug(TAG,"La texture n existe pas: " + textureFilenamePath);
		}
	}
	
	public static Texture getTextureAsset (String textureFilenamePath) {
		Texture texture = null;
		
		if (ASSET_MANAGER.isLoaded(textureFilenamePath)) {
			texture = ASSET_MANAGER.get(textureFilenamePath, Texture.class);
		} else {
			Gdx.app.debug(TAG, "La texture n est pas charge :" +textureFilenamePath);
		}
		return texture;
	}

}

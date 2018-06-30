package com.beware.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import bewareofthetruth.main.Main;
import bewareofthetruth.view.main.BewareOfTruth;

public class DesktopLauncher {
	public static void main(String [] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1920;
		config.height = 1080;
		config.backgroundFPS = 60;
		/*config.foregroundFPS = 0;
		
		new LwjglApplication(new BewareOfTruth(), config);


		config.foregroundFPS = 60;*/
		config.title = "Zombie/heros et tiledMap";
		new LwjglApplication(/*new Main()*/ new BewareOfTruth(), config);

	}
}

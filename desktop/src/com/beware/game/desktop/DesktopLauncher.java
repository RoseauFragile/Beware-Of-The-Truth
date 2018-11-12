package com.beware.game.desktop;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import bewareofthetruth.main.Main;

public class DesktopLauncher {
	
	public static void main(String [] arg) {
		
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1920;
		config.height = 1080;
		config.backgroundFPS = 60;
		config.title = "Zombie/heros et tiledMap";
		Application app = new LwjglApplication(new Main(), config);
		Gdx.app = app;
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

	}
}

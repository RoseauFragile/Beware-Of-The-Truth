package com.beware.game.desktop;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import Box2d_tuto.Physics1;

public class DesktopLauncher {

	public static void main(String [] arg) {

		final LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1920;
		config.height = 1080;
		config.backgroundFPS = 60;
		config.title = "Zombie/heros et tiledMap";
		final Application app = new LwjglApplication(new Physics1(), config);
		//Application app = new LwjglApplication(new Physics2(), config);

		Gdx.app = app;
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

	}
}

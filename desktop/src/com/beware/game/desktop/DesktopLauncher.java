package com.beware.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import bewareofthetruth.main.Main;
import bewareofthetruth.view.main.BewareOfTruth;

public class DesktopLauncher {
	public static void main(String [] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 720;
		config.height = 480;
		config.backgroundFPS = 60;
		config.foregroundFPS = 60;
		new LwjglApplication(new Main(), config);
	}
}

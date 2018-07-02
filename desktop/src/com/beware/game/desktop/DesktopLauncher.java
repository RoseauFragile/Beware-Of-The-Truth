package com.beware.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import bewareofthetruth.main.Main;
import bewareofthetruth.main.MainTestAudio;

public class DesktopLauncher {
	public static void main(String [] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1920;
		config.height = 1080;
		config.backgroundFPS = 60;
		config.title = "Zombie/heros et tiledMap";
		
		//new LwjglApplication(new MainTestAudio(), config);
		new LwjglApplication(new Main(), config);

	}
}

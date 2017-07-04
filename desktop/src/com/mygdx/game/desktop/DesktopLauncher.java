package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.FishGameDemo;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = FishGameDemo.WIDTH;
		config.height = FishGameDemo.HEIGHT;
		config.title = FishGameDemo.TITLE;
		new LwjglApplication(new FishGameDemo(), config);
	}
}

package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.States.StartScreenState;
import com.mygdx.game.States.StatesManager;

public class FishGameDemo extends ApplicationAdapter {
	public static final int WIDTH = 1920;
	public static final int HEIGHT = 1080;
	public static final String TITLE = "Fish Game";
	private StatesManager sm;
	private SpriteBatch batch;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		sm = new StatesManager();
		sm.push(new StartScreenState(sm));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sm.update(Gdx.graphics.getDeltaTime());
		sm.render(batch);
//		batch.begin();
//		batch.draw(img, 0, 0);
//		batch.end();
	}

	@Override
	public void dispose(){
		super.dispose();
	}

//	@Override
//	public void dispose () {
//		batch.dispose();
//		img.dispose();
//	}
}

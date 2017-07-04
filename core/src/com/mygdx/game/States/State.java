package com.mygdx.game.States;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Caleb on 6/11/2017.
 */

public abstract class State {
    protected OrthographicCamera gameCam;
    protected Vector3 mouse;
    protected StatesManager sm;

    protected State(StatesManager sm){
        this.sm = sm;
        gameCam = new OrthographicCamera();
        mouse = new Vector3();
    }

    protected abstract void handleInput();
    protected abstract void updateAnim(float dt);
    protected abstract void render(SpriteBatch sb);
    protected abstract void dispose();

}

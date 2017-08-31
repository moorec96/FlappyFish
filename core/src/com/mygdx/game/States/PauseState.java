package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.FishGameDemo;

/**
 * Created by Caleb on 6/30/2017.
 */

public class PauseState extends State{
    private Texture startBtn;

    private float sbWidth = FishGameDemo.WIDTH/10;
    private float sbHeight = FishGameDemo.HEIGHT/10;


    protected PauseState(StatesManager sm) {
        super(sm);
        gameCam.setToOrtho(false,FishGameDemo.WIDTH,FishGameDemo.HEIGHT);
        startBtn = new Texture("playbtn.png");
    }

    @Override
    protected void handleInput() {
        Rectangle sbBound = new Rectangle(gameCam.position.x - sbWidth/2, gameCam.position.y - sbHeight/2, sbWidth, sbHeight);
        if(Gdx.input.justTouched() && sbBound.contains(Gdx.input.getX(), Gdx.input.getY())){
            sm.pop(this);
        }
    }

    @Override
    protected void updateAnim(float dt) {
        handleInput();
    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(startBtn, gameCam.position.x - sbWidth/2, gameCam.position.y - sbHeight/2, sbWidth, sbHeight);
        sb.end();
    }

    @Override
    protected void dispose() {
        startBtn.dispose();
    }
}

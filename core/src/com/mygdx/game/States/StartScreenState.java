package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.FishGameDemo;
import com.mygdx.game.Sprites.Fish;

/**
 * Created by Caleb on 6/11/2017.
 */

public class StartScreenState extends State{
    private Texture bg;
    private Texture startBtn;

    public StartScreenState(StatesManager sm){
        super(sm);
        gameCam.setToOrtho(false, FishGameDemo.WIDTH/2,FishGameDemo.HEIGHT/2);
        //bg = new Texture("bg.png");
        startBtn = new Texture("playbtn.png");
        System.out.println("In StartScreenState");
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            sm.set(new PondState(sm, new Fish()));
        }
    }

    @Override
    protected void updateAnim(float dt) {
        handleInput();
    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.setProjectionMatrix(gameCam.combined);
        sb.begin();
       // sb.draw(bg,0,0);
        sb.draw(startBtn, gameCam.position.x - startBtn.getWidth()/2, gameCam.position.y - startBtn.getHeight()/2);
        sb.end();
    }

    @Override
    protected void dispose() {
        startBtn.dispose();
        System.out.println("StartScreenState disposed");
    }
}

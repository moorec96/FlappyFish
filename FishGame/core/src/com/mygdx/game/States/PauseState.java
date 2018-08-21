package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.FishGameDemo;

/**
 * Created by Caleb on 6/30/2017.
 */

public class PauseState extends State{
    private Texture startBtn;
    private Texture restartBtn;
    private Texture bg;

    private float sbWidth = FishGameDemo.WIDTH/8;
    private float sbHeight = FishGameDemo.HEIGHT/8;

    private Music music;


    protected PauseState(StatesManager sm,Texture bg, Music music) {
        super(sm);
        gameCam.setToOrtho(false,FishGameDemo.WIDTH,FishGameDemo.HEIGHT);
        startBtn = new Texture("icon_play.png");
        restartBtn = new Texture("icon_replay.png");
        this.bg = bg;
        this.music = music;
    }

    @Override
    protected void handleInput() {
        Rectangle sbBound = new Rectangle(gameCam.position.x - sbWidth/2, gameCam.position.y - sbHeight/2, sbWidth, sbHeight);
        if(Gdx.input.justTouched() && sbBound.contains(Gdx.input.getX(), Gdx.input.getY())){
            if(StartScreenState.getPlayMusic() && music.isPlaying()) {
                music.play();
            }
            sm.pop(this);
        }
        Rectangle rbBound = new Rectangle(gameCam.position.x - sbWidth/2,gameCam.position.y + sbHeight,sbWidth,sbHeight);
        if(Gdx.input.justTouched() && rbBound.contains(Gdx.input.getX(),Gdx.input.getY())){
            sm.remove(1);
            sm.set(new StartScreenState(sm));
        }
    }

    @Override
    protected void updateAnim(float dt) {
        handleInput();
    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(bg,0,0,1920,1080);
        sb.draw(startBtn, gameCam.position.x - sbWidth/2, gameCam.position.y - sbHeight/2, sbWidth, sbHeight);
        sb.draw(restartBtn,gameCam.position.x - sbWidth/2,gameCam.position.y - sbHeight*2,sbWidth,sbHeight);
        sb.end();
    }

    @Override
    protected void dispose() {
        startBtn.dispose();
        restartBtn.dispose();
    }
}

package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.FishGameDemo;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Sprites.Fish;


/**
 * Created by Caleb on 2/17/2018.
 */

public class SettingState extends State{
    private Texture bg;

    private Texture backBtn;
    private Rectangle backBtnBox;

    private Texture musicBtn;
    private Texture musicStopBtn;
    private Rectangle musicBtnBox;

    private float musicWidth = FishGameDemo.WIDTH/8;
    private float musicHeight = FishGameDemo.HEIGHT/8;

    protected SettingState(StatesManager sm, Texture bg) {
        super(sm);
        this.bg = bg;
        backBtn = new Texture("backBtn.png");
        backBtnBox = new Rectangle(FishGameDemo.WIDTH - 200, FishGameDemo.HEIGHT - 225, 125, 100);
        musicBtn = new Texture("musicBtn.png");
        musicStopBtn = new Texture("icon_music_stop.png");
        musicBtnBox = new Rectangle(FishGameDemo.WIDTH/2 - musicWidth/2, FishGameDemo.HEIGHT/2 - musicHeight/2, musicWidth, musicHeight);
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            if(backBtnBox.contains(Gdx.input.getX(), Gdx.input.getY())){
                sm.pop(this);
            }
            else if(musicBtnBox.contains(Gdx.input.getX(), Gdx.input.getY())){
                if(StartScreenState.getPlayMusic()) {
                    StartScreenState.setPlayMusic(false);
                }
                else{
                    StartScreenState.setPlayMusic(true);
                }
            }
        }
    }

    @Override
    protected void updateAnim(float dt) {
        handleInput();
    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(bg,0,0);
        sb.draw(backBtn,FishGameDemo.WIDTH - 200, FishGameDemo.HEIGHT - 980);
        if(StartScreenState.getPlayMusic()) {
            sb.draw(musicBtn, FishGameDemo.WIDTH / 2 - musicWidth / 2, FishGameDemo.HEIGHT / 2 - musicHeight / 2, musicWidth, musicHeight);
        }
        else{
            sb.draw(musicStopBtn, FishGameDemo.WIDTH / 2 - musicWidth / 2, FishGameDemo.HEIGHT / 2 - musicHeight / 2, musicWidth, musicHeight);
        }
        sb.end();
    }

    @Override
    protected void dispose() {

    }
}

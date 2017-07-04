package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.FishGameDemo;
import com.mygdx.game.Sprites.Fish;



/**
 * Created by Caleb on 6/11/2017.
 * background, start button, title edits - Jeron 7/4/17
 */

public class StartScreenState extends State{
    private Texture bg;
    private Texture startBtn;
    private BitmapFont titleFont;
    private float sbWidth = FishGameDemo.WIDTH/10;
    private float sbHeight = FishGameDemo.HEIGHT/10;

    public StartScreenState(StatesManager sm){
        super(sm);
        gameCam.setToOrtho(false, FishGameDemo.WIDTH,FishGameDemo.HEIGHT);
        bg = new Texture("nemoBG.jpg");
        startBtn = new Texture("playbtn.png");
        titleFont = new BitmapFont();
        System.out.println("In StartScreenState");
    }

    @Override
    protected void handleInput() {
        Rectangle sbBound = new Rectangle(gameCam.position.x - sbWidth/2, gameCam.position.y - sbHeight/2, sbWidth, sbHeight);
        if(Gdx.input.justTouched() && sbBound.contains(Gdx.input.getX(), Gdx.input.getY())){
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
        sb.draw(bg,0,0);
        titleFont.setColor(Color.CYAN);
        titleFont.getData().setScale(3);
        titleFont.draw(sb, "Flying with the fishes,\nSwimming with the Pigeons", gameCam.position.x - 200, gameCam.position.y + 300);
        sb.draw(startBtn, gameCam.position.x - sbWidth/2, gameCam.position.y - sbHeight/2, sbWidth, sbHeight);
        sb.end();
    }

    @Override
    protected void dispose() {
        startBtn.dispose();
        System.out.println("StartScreenState disposed");
    }
}

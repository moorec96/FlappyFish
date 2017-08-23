package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.FishGameDemo;
import com.mygdx.game.Sprites.Fish;

/**
 * Displays game over screen
 * Created by Caleb on 6/30/2017.
 */

public class GameOverState extends State{

    //Game over button texture and sprite
    protected Texture gameOver;
    protected Sprite gameOverSprite;


  //  protected Texture bg;

    //Text on the screen
    protected BitmapFont text;

    //Amount of fish eaten
    protected int finalScore;

    //Width and height of game over button
    private float gameOverImgWidth = FishGameDemo.WIDTH/6;
    private float gameOverImgHeight= FishGameDemo.HEIGHT/10;

    /**
     * Sets gameCam size
     * @param sm
     * @param score
     * @param gameCam
     */
    protected GameOverState(StatesManager sm, int score, OrthographicCamera gameCam) {
        super(sm);
        //gameCam.setToOrtho(false,800,500);
        this.gameCam = gameCam;
        gameOver = new Texture("gameover.png");

        // bg = new Texture("kysen.jpg");
        text = new BitmapFont();
        finalScore = score;
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            sm.set(new PondState(sm,new Fish()));
        }
    }

    @Override
    protected void updateAnim(float dt) {
        handleInput();
    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.begin();
      //  sb.draw(gameOver,gameCam.position.x - gameOverImgWidth, gameCam.position.y);
        sb.draw(gameOver, gameCam.position.x - gameOverImgWidth/2, gameCam.position.y - gameOverImgHeight/2, gameOverImgWidth, gameOverImgHeight);
        text.setColor(Color.CYAN);
        text.getData().setScale(2);
        text.draw(sb,"Final Score: " + finalScore,gameCam.position.x - gameOverImgWidth/2, gameCam.position.y - (gameOver.getHeight() + 50));
        sb.end();
    }

    @Override
    protected void dispose() {
        gameOver.dispose();
    }
}

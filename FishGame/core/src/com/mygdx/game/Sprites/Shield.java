package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.FishGameDemo;

import java.util.Random;

/**
 * Created by Caleb on 8/27/2018.
 */

public class Shield {
    private Texture shieldImg;

    private int x,y;
    private Rectangle collisionBox;

    private final int startX;

    private Random rand;

    private boolean empty;

    private boolean goUp, goDown;

    public Shield(int startX){
        shieldImg = new Texture("blackBackground.png");
        this.startX = startX;
        x = startX;
        rand = new Random();
        y = getRandomNum(75, FishGameDemo.HEIGHT);
        collisionBox = new Rectangle(x,y,75,75);
        empty = false;
        goUp = true;
        goDown = false;
    }

    public void move(){
        x -= 6;
        if(x < -75){
            x = startX;
            y = getRandomNum(75,FishGameDemo.HEIGHT);
            empty = false;
        }
        if(goUp){
            y += 4;
        }
        else{
            y -= 4;
        }
        collisionBox.setPosition(x,y);
        checkYPos();
    }

    public int getRandomNum(int min, int max){
        return rand.nextInt(max-min) + min;
    }

    public Texture getIpecacImg() {
        return shieldImg;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void adjustX(int x){
        x -= x;
    }

    public Rectangle getCollisionBox() {
        return collisionBox;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void checkYPos(){
        if(goUp){
            if(y > 1025) {
                goUp = false;
                goDown = true;
            }
        }
        else if(goDown){
            if(y < 25){
                goDown = false;
                goUp = true;
            }
        }
    }

    public Texture getTexture(){
        return shieldImg;
    }

}

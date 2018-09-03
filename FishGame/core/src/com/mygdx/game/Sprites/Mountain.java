package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.FishGameDemo;

/**
 * Created by Caleb on 9/2/2018.
 */

public class Mountain {
    private Texture mtnTexture;
    private Sprite mtnSprite;
    private int x;
    private final int startX;
    private final int y;

    public Mountain(){
        this.startX = FishGameDemo.WIDTH * 2;
        this.y = FishGameDemo.HEIGHT/2;
        mtnTexture = new Texture("whiteBackground.png");
        mtnSprite = new Sprite(mtnTexture);
        mtnSprite.setSize(100,100);
    }

    public void move(){
        x -= 6;
        if(x < -75){
            x = startX;
        }
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public Sprite getMtnSprite(){
        return mtnSprite;
    }

    public Texture getMtnTexture(){
        return mtnTexture;
    }


}

package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Animation.Animation;

/**
 * Created by Caleb on 6/16/2017.
 */

public class E2 extends EnemyBird {

    private static final int MIN_SIZE = 90;
    private static final int MAX_SIZE = 175;

    public E2(int startPos) {
        super(MIN_SIZE, MAX_SIZE, startPos);
        this.enemyFish = new Texture("Bird2Anim.png");
        this.enemyAnimation = new Animation(new TextureRegion(enemyFish),3,0.8f);
        this.enemyFishSprite = new Sprite(enemyFish);
    }

}

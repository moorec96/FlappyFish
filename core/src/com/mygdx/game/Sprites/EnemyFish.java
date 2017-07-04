package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Animation.Animation;
import com.mygdx.game.States.LevelState;

import java.util.Random;

/**
 * Created by Caleb on 6/16/2017.
 */

public abstract class EnemyFish {

    protected Texture enemyFish;
    protected Animation enemyAnimation;
    protected Sprite enemyFishSprite;

    protected int enemyFishWidth, enemyFishHeight;
    protected final int MAX_SIZE;
    protected final int MIN_SIZE;
    protected int maxHeight;

    protected Random rand;

    protected int xSpawn;
    protected int enemyFishY;
    protected Vector3 position;
    protected int enemySpeed;

    protected Rectangle enemyCollisionBox;

    public EnemyFish(int minSize, int maxSize, int startPos){
        rand = new Random();
        this.MIN_SIZE = minSize;
        this.MAX_SIZE = maxSize;
        enemyFishHeight = getRandomNum(MIN_SIZE,MAX_SIZE);
        enemyFishWidth = enemyFishHeight + 5;
        xSpawn = LevelState.camWidth + enemyFishWidth;
        maxHeight = LevelState.camHeight - MAX_SIZE;
        enemyFishY = getRandomNum(75,maxHeight);
        position = new Vector3(startPos,enemyFishY,0);
        enemyCollisionBox = new Rectangle(position.x + (enemyFishWidth / 20), enemyFishY - (enemyFishHeight / 20), enemyFishWidth - (enemyFishWidth / 20), enemyFishHeight - (enemyFishHeight / 20));
    }

    public void updateAnim(float dt){
        enemyAnimation.update(dt);
        enemyFishSprite.setRegion(enemyAnimation.getFrame());
        position.add(enemySpeed,0,0);
        enemyCollisionBox.setPosition(position.x + (enemyFishWidth / 20),position.y - (enemyFishHeight / 20));
    }

    public void resetFish(){
        maxHeight = LevelState.camHeight - MAX_SIZE;
        xSpawn = LevelState.camWidth + maxHeight;
        resizeFish();
        enemyFishY = getRandomNum(75,maxHeight);
        position.set(xSpawn,enemyFishY,0);
    }

    public void resizeFish(){
        enemyFishHeight = getRandomNum(MIN_SIZE,MAX_SIZE);
        enemyFishWidth = enemyFishHeight + 5;
    }


    public int getRandomNum(int min, int max){
        return rand.nextInt(max-min) + min;
    }

    public Sprite getSprite() {
        return enemyFishSprite;
    }

    public int getEnemyFishWidth() {
        return enemyFishWidth;
    }

    public int getEnemyFishHeight() {
        return enemyFishHeight;
    }

    public int getMaxSize() {
        return MAX_SIZE;
    }

    public int enemySize(){
        return enemyFishHeight * enemyFishWidth;
    }

    public Vector3 getPosition() {
        return position;
    }

    public boolean collides(Rectangle other){
        return other.overlaps(enemyCollisionBox);
    }

    public Rectangle getEnemyCollisionBox() {
        return enemyCollisionBox;
    }

    public void setEnemySpeed(int enemySpeed) {
        this.enemySpeed = enemySpeed;
    }
}

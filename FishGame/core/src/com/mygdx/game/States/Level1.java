package com.mygdx.game.States;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Sprites.EnemyBird;
import com.mygdx.game.Sprites.Fish;

/**
 * Created by Caleb on 6/22/2017.
 */

public class Level1 extends LevelState{
    private final Texture bg = new Texture("game_background_1.png");
    private static final int fishSizeCap = 150;
    private static final int fishGap = 150;
    private static final int growPercentage = 5;

    protected Level1(StatesManager sm, Fish fish, int score) {
        super(sm, Level.Level1, fishGap, fish, score);
       // playMusic();
        setBackgroundImage(bg);
        fishGrowPercentage = growPercentage;
    }

    @Override
    public void checkFishSize() {
        if(fish.getFishWidth() > fishSizeCap){
            EnemyBird highestFish = findHighestFish();
            inputEnabled = false;
            fish.turnOffGravity();
            for(EnemyBird current : enemyBirds){
                current.turnOffEnemyMovement();
            }
            if(!fishOffScreen){
                levelOutro(highestFish);
            }
            else {
                sm.set(new Level2(sm, this.fish, fishEatenCount));

            }
        }
    }
}

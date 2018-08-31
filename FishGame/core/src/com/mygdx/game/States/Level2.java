package com.mygdx.game.States;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Sprites.EnemyBird;
import com.mygdx.game.Sprites.Fish;

/**
 * Created by Caleb on 6/22/2017.
 */

public class Level2 extends LevelState{
    private final Texture bg = new Texture("game_background_4.png");
    public static final int fishSizeCap = 200;
    public static final int fishGap = 150;
    private static final int growPercentage = 10;

    protected Level2(StatesManager sm, Fish fish, int score) {
        super(sm, Level.Level2, fishGap, fish,score);
        setBackgroundImage(bg);
        this.fish = fish;
        fishGrowPercentage = growPercentage;
    }

    @Override
    public void checkFishSize() {
        if(fish.getFishWidth() > fishSizeCap){
            inputEnabled = false;
            fish.turnOffGravity();
            for(EnemyBird current : enemyBirds){
                current.turnOffEnemyMovement();
            }
            if(!fishOffScreen){
                levelOutro(findHighestFish());
            }
            else {
                //fishInPosition = false;
                //fish.setFishY(Level2.lakeCamHeight + fish.getPosition().y);
                sm.set(new Level3(sm,fish,fishEatenCount));
            }
        }
    }
}

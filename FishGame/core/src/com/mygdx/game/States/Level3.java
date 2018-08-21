package com.mygdx.game.States;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Sprites.EnemyBird;
import com.mygdx.game.Sprites.Fish;

/**
 * Created by Caleb on 7/26/2017.
 */

public class Level3 extends LevelState{

    private final Texture bg = new Texture("game_background_3.1.png");
    public static final int fishSizeCap = 250;
    public static final int fishGap = 300;
    private static final int growPercentage = 15;

    protected Level3(StatesManager sm, Fish fish, int score) {
        super(sm, Level.Level3, fishGap, fish,score);
        setBackgroundImage(bg);
        this.fish = fish;
        fish.setFishY(camHeight + fish.getFishHeight());
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
                sm.set(new StartScreenState(sm));
            }
        }
    }
}

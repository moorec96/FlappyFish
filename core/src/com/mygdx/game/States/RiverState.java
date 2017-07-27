package com.mygdx.game.States;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Sprites.EnemyFish;
import com.mygdx.game.Sprites.Fish;

/**
 * Created by Caleb on 7/26/2017.
 */

public class RiverState extends LevelState{

    private final Texture bg = new Texture("river.jpg");
    public static final int riverCamWidth = 3200;
    public static final int riverCamHeight = 2000;

    protected RiverState(StatesManager sm, Fish fish) {
        super(sm, riverCamWidth, riverCamHeight,Level.RIVER, 150, fish);
        setCamSize(riverCamWidth,riverCamHeight);
        setBackgroundImage(bg);
        this.fish = fish;
        fish.setFishY(riverCamHeight + fish.getFishHeight());
        fish.setGravity(-60);
        fish.setJumpHeight(1400);
        gameCam.setToOrtho(false,riverCamWidth,riverCamHeight);
    }

    @Override
    public void checkFishSize() {
        if(fish.getFishWidth() > 250){
            inputEnabled = false;
            fish.turnOffGravity();
            for(EnemyFish current : enemyFishes){
                current.turnOffEnemyMovement();
            }
            if(!fishOffScreen){
                levelOutro(findHighestFish());
            }
            else {
                //fishInPosition = false;
                //fish.setFishY(LakeState.lakeCamHeight + fish.getPosition().y);
                sm.set(new StartScreenState(sm));
            }
        }
    }
}

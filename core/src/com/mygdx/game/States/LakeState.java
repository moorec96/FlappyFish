package com.mygdx.game.States;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Sprites.Fish;

/**
 * Created by Caleb on 6/22/2017.
 */

public class LakeState extends LevelState{
    private final Texture bg = new Texture("lake.png");
    private static final int lakeCamWidth = 1600;
    private static final int lakeCamHeight = 1000;

    protected LakeState(StatesManager sm, Fish fish) {
        super(sm, lakeCamWidth, lakeCamHeight,Level.LAKE, 150, fish);
        setCamSize(lakeCamWidth,lakeCamHeight);
        setBackgroundImage(bg);
        this.fish = fish;
        fish.setGravity(-30);
        fish.setJumpHeight(700);
        gameCam.setToOrtho(false,lakeCamWidth,lakeCamHeight);
    }

    @Override
    public void checkFishSize() {
        if(fish.getFishWidth() > 150){
            sm.set(new StartScreenState(sm));
        }
    }
}

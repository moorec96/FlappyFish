package com.mygdx.game.Animation;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Created by calebmoore on 12/17/17.
 */

public class FishAnimation {
    private Array<TextureRegion> frames;
    private float maxFrameTime;
    private int frameCount;
    private int frame;
    private int count;

    public FishAnimation(TextureRegion region, int frameCount, float cycleTime){
        frames = new Array<TextureRegion>();
        int frameWidth = region.getRegionWidth() / frameCount;
        for(int i = 0; i < frameCount; i++){
            this.frames.add(new TextureRegion(region, i*frameWidth,0,frameWidth,region.getRegionHeight()));
        }
        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
        count = 0;
    }

    public void update(boolean jumping){
        if(jumping){
            frame = 0;
        }
        else{
            frame = 2;
        }
    }

    public TextureRegion getFrame(){
        return frames.get(frame);
    }
}

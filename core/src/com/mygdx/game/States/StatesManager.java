package com.mygdx.game.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by Caleb on 6/11/2017.
 */

public class StatesManager{
    private Stack<State> gameStates;

    public StatesManager(){
        this.gameStates = new Stack<State>();
    }

    public void push(State state){
        gameStates.push(state);
    }

    public void pop(State state){
        gameStates.pop().dispose();
    }

    public void set(State state){
        gameStates.pop().dispose();
        gameStates.push(state);
    }

    public void update(float dt){
        gameStates.peek().updateAnim(dt);
    }

    public void render(SpriteBatch sb){
        gameStates.peek().render(sb);
    }
}

package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.FishGameDemo;
import com.mygdx.game.Sprites.EnemyBird;
import com.mygdx.game.Sprites.Fish;
import com.mygdx.game.Sprites.E1;
import com.mygdx.game.Sprites.E2;
import com.mygdx.game.Sprites.E3;
import com.mygdx.game.Sprites.E4;
import com.mygdx.game.Sprites.E5;
import com.mygdx.game.Sprites.E6;

import java.util.Random;


/**
 * Created by Caleb on 6/11/2017.
 * background, start button, title edits - Jeron 7/4/17
 */

public class StartScreenState extends State{
    //Camera width and height depending on what level the player is on
    public static final int camWidth = 1920;
    public static final int camHeight = 1080;

    //Background texture
    private Texture bg;

    //Start button texture
    private Texture startBtn;

    //Setting button
    private Texture settingsBtn;
    private Rectangle settingsBox;

    //Text on screen
    private BitmapFont highScoreFont;

    //EnemyBirds
    private EnemyBird e1,e2,e3,e4,e5,e6;

    //Music
    private Music music;
    private static boolean playMusic = true;

    String[] bgs = {"game_background_1.png","game_background_2.png","game_background_3.1.png","game_background_4.png"};

    /**
     * Sets camera width and height, and initializes textures
     */
    public StartScreenState(StatesManager sm){
        super(sm);
        gameCam.setToOrtho(false, FishGameDemo.WIDTH,FishGameDemo.HEIGHT);
        bg = new Texture(getBackground());
        startBtn = new Texture("clickToStart.png");
        settingsBtn = new Texture("settingsBtn.png");
        settingsBox = new Rectangle(FishGameDemo.WIDTH - 200, FishGameDemo.HEIGHT - 225, 125, 100);  //Rectangles build from top-left, draw function does bottom-left
        highScoreFont = new BitmapFont();
        spawnLeft();

        if(playMusic) {
            playMusic();
        }
    }

    /**
     * Checks to see if player is clicking on start button
     */
    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            if(settingsBox.contains(Gdx.input.getX(),Gdx.input.getY())){
                sm.push(new SettingState(sm,bg));
            }
            else{
                if(playMusic && music != null) {
                    music.stop();
                }
                sm.set(new Level1(sm, new Fish(),0));
            }
        }

    }

    /**
     * Calls handleInput
     * @param dt
     */
    @Override
    protected void updateAnim(float dt) {
        handleInput();
        e1.updateAnim(dt);
        checkEnemyX(e1,true);

        e2.updateAnim(dt);
        checkEnemyX(e2,true);

        e3.updateAnim(dt);
        checkEnemyX(e3,true);

        e4.updateAnim(dt);
        checkEnemyX(e4,true);

        e5.updateAnim(dt);
        checkEnemyX(e5,true);

        e6.updateAnim(dt);
        checkEnemyX(e6,true);

        if(!playMusic && music != null){
            music.pause();
        }
        if(playMusic && music == null){
            playMusic();
        }
    }

    /**
     * Draws start button, text and background
     * @param sb
     */
    @Override
    protected void render(SpriteBatch sb) {
        sb.setProjectionMatrix(gameCam.combined);
        sb.begin();
        sb.draw(bg,0,0);
        sb.draw(e1.getSprite(), e1.getPosition().x, e1.getPosition().y, e1.getEnemyFishWidth(), e1.getEnemyFishHeight());
        sb.draw(e2.getSprite(), e2.getPosition().x, e2.getPosition().y, e2.getEnemyFishWidth(), e2.getEnemyFishHeight());
        sb.draw(e3.getSprite(), e3.getPosition().x, e3.getPosition().y, e3.getEnemyFishWidth(), e3.getEnemyFishHeight());
        sb.draw(e4.getSprite(), e4.getPosition().x, e4.getPosition().y, e4.getEnemyFishWidth(), e4.getEnemyFishHeight());
        sb.draw(e5.getSprite(), e5.getPosition().x, e5.getPosition().y, e5.getEnemyFishWidth(), e5.getEnemyFishHeight());
        sb.draw(e6.getSprite(), e6.getPosition().x, e6.getPosition().y, e6.getEnemyFishWidth(), e6.getEnemyFishHeight());
        sb.draw(startBtn, gameCam.position.x - startBtn.getWidth()/2, gameCam.position.y - startBtn.getHeight());
        sb.draw(settingsBtn, FishGameDemo.WIDTH - 200, FishGameDemo.HEIGHT - 980);
        highScoreFont.setColor(Color.CYAN);
        highScoreFont.getData().setScale(3);
        highScoreFont.draw(sb,"High Score: " + prefs.getInteger("highscore"),gameCam.position.x - 260, gameCam.position.y -100);

        sb.end();
    }

    private void spawnLeft(){
        e1 = new E1(camWidth);
        e1.setEnemySpeed(-6);
        e1.turnOnEnemyMovement();

        e2 = new E2(camWidth + 150);
        e2.setEnemySpeed(-5);
        e2.turnOnEnemyMovement();

        e3 = new E3(camWidth + 300);
        e3.setEnemySpeed(-2);
        e3.turnOnEnemyMovement();

        e4 = new E4(camWidth + 450);
        e4.setEnemySpeed(-2);
        e4.turnOnEnemyMovement();

        e5 = new E5(camWidth + 600);
        e5.setEnemySpeed(-4);
        e5.turnOnEnemyMovement();

        e6 = new E6(camWidth + 750);
        e6.setEnemySpeed(-3);
        e6.turnOnEnemyMovement();
    }

    private void spawnRight(){

    }

    private void checkEnemyX(EnemyBird bird, boolean goingLeft){
        if(goingLeft){
            if(bird.getPosition().x < -75){
                bird.resetFish();
                bird.resizeFish();
            }
        }
        else{
            if(bird.getPosition().x > 2000){
                bird.resizeFish();
                bird.resetFish();
            }
        }
    }

    private String getBackground(){
        Random rand = new Random();
        return bgs[rand.nextInt(3)];
    }

    public static void setPlayMusic(boolean play){
        playMusic = play;
    }

    public static boolean getPlayMusic(){
        return playMusic;
    }

    /**
     * Disposes textures
     */
    @Override
    protected void dispose() {
        startBtn.dispose();
        bg.dispose();
        highScoreFont.dispose();
        if(playMusic) {
            music.dispose();
        }
        System.out.println("StartScreenState disposed");
    }

    protected  void playMusic(){
        music = Gdx.audio.newMusic(Gdx.files.internal("Spy_Hunter.mp3"));
        music.setLooping(true);
        music.setVolume(0.5f);
        music.play();
    }


}

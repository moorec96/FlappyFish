package com.mygdx.game.States;

        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.audio.Music;
        import com.badlogic.gdx.graphics.Color;
        import com.badlogic.gdx.graphics.Texture;
        import com.badlogic.gdx.graphics.g2d.BitmapFont;
        import com.badlogic.gdx.graphics.g2d.SpriteBatch;
        import com.badlogic.gdx.math.Rectangle;
        import com.badlogic.gdx.utils.Array;
        import com.badlogic.gdx.utils.Timer;
        import com.mygdx.game.FishGameDemo;
        import com.mygdx.game.Sprites.EnemyBird;
        import com.mygdx.game.Sprites.Fish;
        import com.mygdx.game.Sprites.Ipecac;
        import com.mygdx.game.Sprites.E5;
        import com.mygdx.game.Sprites.E6;
        import com.mygdx.game.Sprites.E3;
        import com.mygdx.game.Sprites.E4;
        import com.mygdx.game.Sprites.E1;
        import com.mygdx.game.Sprites.E2;
        import com.mygdx.game.Sprites.Shield;

        import java.util.Random;

/**
 * Class: LevelState
 * Purpose: Abstract state that handles all levels in the game
 * Created by Caleb on 6/22/2017.
 */

public abstract class LevelState extends State{

    //Player fish object
    protected Fish fish;

    //Array of all enemyBird that are spawned
    protected Array<EnemyBird> enemyBirds;

    //This random number determines what type of random fish are spawned in the current level
    protected Random randNum;

    //Enum that helps determines what enemy fish to spawn depending on the current level
    protected Level currentLevel;

    //Camera width and height
    public static final int camWidth = 1920;
    public static final int camHeight = 1080;

    //Horizontal space between each enemy fish
    protected int enemyBirdGap;

    //Set to true if player fish collides with a larger fish
    protected boolean fishDead;

    //These booleans are used in the intro and outro methods that move the fish into position
    protected boolean fishInPosition,fishOffScreen, inIntro;

    //Input is enabled once the levelIntro and levelOutro methods are complete and the fish is in position
    protected boolean inputEnabled;

    //Set to true when the enemies have all moved off of the screen in the levelOutro method
    protected boolean enemyOffScreen;

    //This font is used to display fishEatenCount and fishSize(TEMPERORY)
    protected BitmapFont font;
    protected int fontScale;

    //Background image
    protected Texture bg;

    protected Texture pauseBtn;
    protected Rectangle pauseBtnBox;

    protected Texture startBtn;
    private float sbWidth = FishGameDemo.WIDTH/10;
    private float sbHeight = FishGameDemo.HEIGHT/10;
    protected Rectangle startBtnBox;

    protected Music music;
    protected boolean playMusic;

    //Health bar
    protected Texture healthBar;
    protected boolean liveHealth;

    private String currentLevelName;

    private Ipecac ipecac;

    private Shield shield;

    private boolean levelLive;

    private float rotation;

    protected int fishEatenCount;

    protected int currentBGWidth, currentBGHeight;

    protected int fishGrowPercentage;

    private boolean hitDelay;



    /**
     * Initializes Level variables including textures and camera size
     * @param sm - StateManager
     * @param level - Level enum
     * @param enemyGap
     * @param fish
     */
    protected LevelState(StatesManager sm, Level level, int enemyGap, Fish fish, int currentScore) {
        super(sm);
        gameCam.setToOrtho(false,camWidth,camHeight);
        fishInPosition = false;
        fishOffScreen = false;
        inputEnabled = false;
        enemyOffScreen = false;
        inIntro = true;
        liveHealth = false;
        fishDead = false;
        this.fish = fish;
        randNum = new Random();
        enemyBirds = new Array<EnemyBird>();
        currentLevel = level;
        enemyBirdGap = enemyGap;
        font = new BitmapFont();
        pauseBtn = new Texture("icon_pause.png");
        pauseBtnBox = new Rectangle(0,0,140,140);
//        startBtn = new Texture("playbtn.png");
//        startBtnBox = new Rectangle(gameCam.position.x - sbWidth/2, gameCam.position.y - sbHeight/2, sbWidth, sbHeight);
        addFishes();
        setEnemySpeed();
       // fish.resetFishSize();
        healthBar = new Texture("whiteBackground.png");
        if(!level.equals(Level.Level1)) {
            ipecac = new Ipecac(FishGameDemo.WIDTH * 2);
        }
        shield = new Shield(FishGameDemo.WIDTH * 3);

        levelLive = true;
        rotation  = 0;
        fishEatenCount = currentScore;

        if(StartScreenState.getPlayMusic()) {
            music = Gdx.audio.newMusic(Gdx.files.internal("Defense_Line.mp3"));
            music.setLooping(true);
            music.setVolume(0.5f);
            music.play();
        }
        currentBGWidth = camWidth * 2;
        currentBGHeight = camHeight * 2;
        hitDelay = false;
    }

    /**
     * Spawns enemy fish depending on what Level enum was passed into the constructor
     *  - Fish types are spawned at random
     */
    protected void addFishes(){
        if(currentLevel.equals(Level.Level1)) {
            currentLevelName = "Level 1";
            for (int i = 0; i < 20; i++) {
                int num = randNum.nextInt(10) + 1;
                if (num % 2 == 0) {
                    enemyBirds.add(new E1(camWidth + (i * enemyBirdGap)));

                } else {
                    enemyBirds.add(new E2(camWidth + (i * enemyBirdGap)));
                }
            }
        }
        else if(currentLevel.equals(Level.Level2)){
            currentLevelName = "Level 2";
            for (int i = 0; i < 20; i++) {
                int num = randNum.nextInt(10) + 1;
                if (num % 2 == 0) {
                    enemyBirds.add(new E3(camWidth + (i * enemyBirdGap)));
                } else {
                    enemyBirds.add(new E4(camWidth + (i * enemyBirdGap)));
                }
            }
        }
        else if(currentLevel.equals(Level.Level3)){
            currentLevelName = "Level 3";
            for (int i = 0; i < 20; i++) {
                int num = randNum.nextInt(10) + 1;
                if (num % 2 == 0) {
                    enemyBirds.add(new E5(camWidth + (i * enemyBirdGap)));
                } else {
                    enemyBirds.add(new E6(camWidth + (i * enemyBirdGap)));
                }
            }
        }
    }

    /**
     * Speed varies depending on size of level
     */
    public void setEnemySpeed(){
        if(currentLevel.equals(Level.Level1)){
            for(EnemyBird current: enemyBirds){
                current.setEnemySpeed(-6);
            }
        }
        else if(currentLevel.equals(Level.Level2)){
            for(EnemyBird current: enemyBirds){
                current.setEnemySpeed(-7);
            }
        }
        else if(currentLevel.equals(Level.Level3)){
            for(EnemyBird current: enemyBirds){
                current.setEnemySpeed(-9);
            }
        }
    }

    /**
     * Camera size increases with each letter
     * @param camWidth
     * @param camHeight
     */
//    protected void setCamSize(int camWidth, int camHeight){
//        this.camWidth = camWidth;
//        this.camHeight = camHeight;
//    }

    /**
     * Level background image
     * @param bg
     */
    protected void setBackgroundImage(Texture bg){
        this.bg = bg;
    }

    /**
     * Allows player to jump, pause and unpause game
     */
    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()) {
            if(inputEnabled) {
                if(pauseBtnBox.contains(Gdx.input.getX(),Gdx.input.getY())){
                    if(StartScreenState.getPlayMusic()) {
                        music.pause();
                    }
                    sm.push(new PauseState(sm,bg,music));
                    //music.play();
                }
                else{
                    fish.jump();
                    if(rotation < 20) {
                        rotation += 50;
                    }
                    else if(rotation > 20){
                        rotation = 45;
                    }
                }
            }
            else {
                if (inIntro) {
                    fish.setFishY(gameCam.position.y);
                    currentBGHeight = camHeight;
                    currentBGWidth = camWidth;
                }
            }
        }
    }

    /**
     * Handles player and enemy movement, and checks for collisions. If there is a collision the enemy fish is eather eaten,
     * or the player dies and the GameOverState is pushed
     * @param dt
     */
    @Override
    protected void updateAnim(float dt) {
        if(rotation >= -35){
            rotation -= 1.5;
        }
        handleInput();
        if(enemyBirds.get(0).getPosition().x < gameCam.position.x){
            liveHealth = true;
        }
        if(liveHealth) {
            fish.adjustHealth(-0.0006f);
        }
        if(fish.getHealth() > 1){
            fish.resetHealth();
        }
            fish.updateAnim(dt);
            checkFishSize();
            checkFishHealth();
            if(levelLive && !currentLevel.equals(Level.Level1)) {
                ipecac.move();
                shield.move();
            }

            if (!fishInPosition) {
                levelIntro();
            }
            else {
                for (EnemyBird current : enemyBirds) {
                    if (current.getPosition().x < -75) {
                        current.resetFish();
                        // checkForEnemyCollisions(current);
                    }
                    current.updateAnim(dt);
                    if (current.collides(fish.getCollisionBox()) && fish.isCollisionOn()) {
                        if (fish.canEat(current)) {
                            fish.increaseFishSize(current.getEnemyFishWidth() / fishGrowPercentage, current.getEnemyFishHeight() / fishGrowPercentage, current.getEnemyFishWeight());

                            float adjustment = 0.1f * (fish.getFishWidth()/current.getEnemyFishWidth());
                            fish.adjustHealth(adjustment);
                            current.resetFish();

                            fishEatenCount++;
                        } else {
                            if(fish.isShieldOn() || hitDelay) {
                                System.out.println("yaaaa");
                                if(!hitDelay) {
                                    System.out.println("testing");
                                    hitDelay = true;
                                    startCountDown();
                                    fish.setShieldOn(false);
                                }
                            }
                            else {
                                gameOver();
                            }
                        }
                    }
                }
                if(!currentLevel.equals(Level.Level1)) {
                    if (ipecac.getCollisionBox().overlaps(fish.getCollisionBox()) && !ipecac.isEmpty()) {
                        fish.throwUp();
                        ipecac.setEmpty(true);
                    }
                }
                if (shield.getCollisionBox().overlaps(fish.getCollisionBox()) && !shield.isEmpty()) {
                    fish.setShieldOn(true);
                    shield.setEmpty(true);
                }
            }
        }

    /**
     * Draws all textures and sprites on the screen, as well as the fonts
     * @param sb
     */
    @Override
    protected void render(SpriteBatch sb) {
        sb.setProjectionMatrix(gameCam.combined);
        sb.begin();
        sb.draw(bg,0,0,currentBGWidth,currentBGHeight);
        sb.draw(fish.getCollisionBoxSprt(),fish.getCollisionBox().getX(),fish.getCollisionBox().getY(),fish.getCollisionBox().getWidth(),fish.getCollisionBox().getHeight());
        //sb.draw(fish.getSprite(),fish.getPosition().x, fish.getPosition().y,fish.getSprite().getWidth(),fish.getSprite().getHeight());
        sb.draw(fish.getTexture().getTexture(), fish.getPosition().x, fish.getPosition().y, fish.getFishWidth(), fish.getFishHeight()/2,
                fish.getFishWidth(), fish.getFishHeight(),1,1, rotation, fish.getSprite().getRegionX(), fish.getSprite().getRegionY(),
                fish.getSprite().getRegionWidth(), fish.getSprite().getRegionHeight(), false, false);

        for(EnemyBird current : enemyBirds){
            sb.draw(current.getCollisionBoxSprt(),current.getEnemyCollisionBox().getX(),current.getEnemyCollisionBox().getY(),current.getEnemyCollisionBox().getWidth(),current.getEnemyCollisionBox().getHeight());
            sb.draw(current.getSprite(),current.getPosition().x,current.getPosition().y,current.getEnemyFishWidth(),current.getEnemyFishHeight());
        }

        if(levelLive && !currentLevel.equals(Level.Level1) && !ipecac.isEmpty()) {
            sb.draw(ipecac.getIpecacImg(), ipecac.getX(), ipecac.getY(), 75, 75);
        }
        if(levelLive && !currentLevel.equals(Level.Level1) && !shield.isEmpty()) {
            sb.draw(shield.getShieldImg(), shield.getX(), shield.getY(), 75, 75);
        }
        //Health Bar
        if(fish.getHealth() > 0.5f) {
            sb.setColor(Color.GREEN);
        }
        else if(fish.getHealth() > 0.25f){
            sb.setColor(Color.YELLOW);
        }
        else if(fish.getHealth() > 0.15f){
            sb.setColor(Color.ORANGE);
        }
        else if(fish.getHealth() > 0){
            sb.setColor(Color.RED);
        }
        sb.draw(healthBar,0,0,FishGameDemo.WIDTH * fish.getHealth(), 10);
        sb.setColor(Color.WHITE);

        font.setColor(Color.WHITE);
        font.getData().setScale(2);
        font.draw(sb,currentLevelName,150,camHeight - 10);
        font.draw(sb,"Fish Eaten: " + fishEatenCount,150,camHeight - 50);
        font.draw(sb,"Fish Size: " + fish.getFishWeight() + " lbs.",150, camHeight - 90);
        font.draw(sb,"Shield: " + fish.isShieldOn(),150,camHeight-130);
        sb.draw(pauseBtn,35,camHeight - 110,100,100);
        sb.end();
    }

    /**
     * Abstract class that is defined in each of this classes subclasses
     */
    public abstract void checkFishSize();

    public void checkFishHealth(){
        if(fish.getHealth() <= 0){
            gameOver();
        }
    }

    public void gameOver(){
        fishDead = true;
        int score = fishEatenCount;
        fishEatenCount = 0;
        sm.set(new GameOverState(sm, score));
    }

    /**
     * Moves the player fish to the center of the screen(Vertically)
     */
    public void levelIntro(){
        fish.levelLive = true;
        if(fish.getFishWidth() > fish.getOriginalFishWidth()){
            fish.adjustFishWidth(1f);
        }
        if(fish.getFishHeight() > fish.getOriginalFishHeight()){
            fish.adjustFishHeight(1f);
        }
        if(currentBGWidth > camWidth && currentBGHeight > camHeight){
            currentBGWidth -= 25;
            currentBGHeight -=10;
        }
        if(!fishInPosition){
            fish.addFishX(2);
        }
        if(fish.getPosition().x >= 150 && fish.getFishHeight() <= fish.getOriginalFishHeight()){
            fishInPosition = true;
            fish.turnOnGravity();
            inputEnabled = true;
            inIntro = false;
            fish.resetHealth();
            fish.resetCollisionBox();
            //liveHealth = true;
            fish.setCollision(true);
            for(EnemyBird current : enemyBirds){
               current.turnOnEnemyMovement();
            }

        }
    }

    /**
     * Moves the enemy fish off the screen and the player fish to the top of the screen
     * @param highestFish
     */
    public void levelOutro(EnemyBird highestFish){
        levelLive = false;
        fish.levelLive = false;
        fish.setCollision(false);
        liveHealth = false;
        if(!enemyOffScreen){
            for(EnemyBird current : enemyBirds){
                current.setEnemyY(-(camHeight / 250));
            }
            if(highestFish.getPosition().y + highestFish.getEnemyFishHeight() < -10){
                enemyOffScreen = true;
            }
        }
        else{
            if (!fishOffScreen) {
                fish.addFishY(camHeight / 250);
                fish.addFishY(camHeight / 250);

            }
            if (fish.getPosition().y  > camHeight) {
                fishOffScreen = true;
                fish.resetPosition();
            }
        }
    }

    /**
     * Determines what enemyFish is the highest on the screen so that the levelOutro knows what fish to watch until it is off of the screen
     * @return
     */
    public EnemyBird findHighestFish(){
        EnemyBird highestFish = enemyBirds.get(0);
        for(EnemyBird current : enemyBirds){
            if(current.getPosition().y > highestFish.getPosition().y){
                highestFish = current;
            }
        }
        return highestFish;
    }

    public void startCountDown(){
        Timer.schedule(new Timer.Task(){
            @Override
            public void run(){
                hitDelay = false;
                System.out.println("Hello");
            }
        },2f);
    }

    /**
     * Disposes of textures
     */
    @Override
    protected void dispose() {
        if(fishDead){
            fish.dispose();
        }
        font.dispose();
        bg.dispose();
        for(int i = 0; i < 20; i++){
            enemyBirds.get(i).getSprite().getTexture().dispose();
        }
//        for(EnemyBird current : enemyFishes){
//            current.getSprite().getTexture().dispose();
//        }
        if(StartScreenState.getPlayMusic()) {
            music.dispose();
        }

        if(!currentLevel.equals(Level.Level1)){
            ipecac.getIpecacImg().dispose();
        }

        System.out.println("PondState2 disposed");
    }
}

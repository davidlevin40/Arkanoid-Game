package gamesetting;

import biuoop.KeyboardSensor;
import geometry.Ball;
import geometry.Block;
import geometry.Paddle;
import geometry.Point;
import geometry.Rectangle;
import biuoop.DrawSurface;
import interfaces.Animation;
import interfaces.Collidable;
import interfaces.LevelInformation;
import interfaces.Sprite;
import java.awt.Color;

/**
 * Game class.
 * A Game representing the whole game that we want to play- the engine of the game.
 */
public class GameLevel implements Animation {
    // height of 'screen'
    private static final int HEIGHT = 600;
    // width of 'screen'
    private static final int WIDTH = 800;
    // height of paddle
    private static final int PADDLE_HEIGHT = 20;
    // number of balls game
    private static final int NUM_OF_BALLS_GAME = 3;

    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter score;
    private Counter lives;
    private Paddle paddleGame;
    private AnimationRunner runner;
    private boolean running;
    private LevelInformation levelInformation;
    private KeyboardSensor keyboard;


    /**
     * Construct a game level.
     * @param levelInformation that represents the level information.
     */
    public GameLevel(LevelInformation levelInformation) {
        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection();
        this.remainingBlocks = new Counter();
        this.remainingBalls = new Counter();
        this.score = new Counter();
        this.lives = new Counter();
        lives.increase(7);
        this.runner = new AnimationRunner();
        this.levelInformation = levelInformation;
    }

    /**
     * Construct a game level.
     * @param levelInfo that represents the level info.
     * @param ks that represents the KeyboardSensor.
     * @param ar that represents the AnimationRunner.
     * @param lives that represents the lives.
     * @param score that represents the score.
     */
    public GameLevel(LevelInformation levelInfo, KeyboardSensor ks, AnimationRunner ar, Counter lives, Counter score) {
        this.levelInformation = levelInfo;
        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection();
        this.remainingBlocks = new Counter();
        this.remainingBalls = new Counter();
        this.keyboard = ks;
        this.runner = ar;
        this.lives = lives;
        this.score = score;
    }

    /**
     * The function role is to add collidable objects to the game.
     *
     * @param c a sprite collection of objects
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * The function role is to add some object to the sprite collection.
     *
     * @param s a sprite collection of objects
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * The function role is to remove a collidable object from the game.
     * @param c that represents the collidable that we want to remove.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * The function role is to remove some object from the sprite collection.
     * @param s that represents the sprite that we want to delete.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * The function role is to create and define the 'frames' of the game.
     * <p>
     * The function role is to create the casing of the board: 4 rectangles in each side of the screen.
     * Every rectangle will be with thickness of 20. Moreover, the function will add the blocks to the sprite
     * collection. After it, define the ball to react those blocks.
     * </p>
     * @param ball define the ball to react "good" to the blocks.
     */
    public void createFrames(Ball ball) {
        // upper rectangle
        Rectangle rec = new Rectangle(new Point(0, 20), WIDTH, 20);
        Block block1 = new Block(rec, Color.GRAY);
        // left rectangle
        Rectangle rec2 = new Rectangle(new Point(0, 20), 25, HEIGHT - 20);
        Block block3 = new Block(rec2, Color.GRAY);
        //right rectangle
        Rectangle rec3 = new Rectangle(new Point(WIDTH - 25, 20), 25, HEIGHT - 20);
        Block block4 = new Block(rec3, Color.GRAY);

        // set the first ball to "know" this blocks
        ball.getGameEnvironment().addCollidable(block1);
        ball.getGameEnvironment().addCollidable(block3);
        ball.getGameEnvironment().addCollidable(block4);

        // add this blocks to the game
        block1.addToGame(this);
        block3.addToGame(this);
        block4.addToGame(this);
    }

    /**
     * The function creates a new ball for the game. It will define the velocity, and add it to the game.
     * @return new ball for the game.
     */
    public Ball createBallGame() {
        // create a new ball for the game
        Color color = new Color(0xD1E2E8);
        Ball ball = new Ball(400, 560, 3, color);
        // define the velocity of the ball
        ball.setVelocity(this.levelInformation.initialBallVelocities().get(0));

        ball.setGameEnvironment(this.environment);
        ball.moveOneStep();
        remainingBalls.increase(1);
        return ball;
    }

    /**
     * The function role is create the balls on top of the paddle in the begging of the game.
     */
    public void createBallsOnTopOfPaddle() {
        for (int i = 0; i < NUM_OF_BALLS_GAME; i++) {
            Ball ball = createBallGame();
            ball.addToGame(this);
        }
    }

    /**
     * The function role is to create and add a paddle to the game.
     * @param ball that represents the ball - so the paddle will "know" it.
     * @param keyboardSensor that represents the keyboard sensor that controls the paddle.
     * @return Paddle for the game.
     */
    public Paddle createPaddleGame(Ball ball, biuoop.KeyboardSensor keyboardSensor) {
        // creating new paddle game
        Point paddleStart = new Point(400 - (this.levelInformation.paddleWidth() / 2), 570);
        Rectangle rect = new Rectangle(paddleStart, this.levelInformation.paddleWidth(), PADDLE_HEIGHT);
        Paddle paddleGames = new Paddle(Color.YELLOW, rect, keyboardSensor, this.levelInformation.paddleSpeed());
        ball.getGameEnvironment().addCollidable(paddleGames);
        paddleGames.addToGame(this);
        return paddleGames;
    }

    /**
     * The function role is to initialize a game: create the Blocks, Ball and Paddle and add them to the game.
     * <p>
     * We will create the ball and rand his location and angle. Then we will create the game enviroment:
     * </p>
     */
    public void initialize() {
        this.sprites.addSprite(this.levelInformation.getBackground());
        biuoop.KeyboardSensor keyboardSensor = this.runner.getGui().getKeyboardSensor();
        // creating the balls
        Ball ball1 = createBallGame();
        ball1.addToGame(this);
        // creating the frames of the screen
        createFrames(ball1);

        for (int i = 0; i < this.levelInformation.numberOfBalls() - 1; i++) {
            Ball ball = createBallGame();
            ball.addToGame(this);
        }

        // creating the paddle
        this.paddleGame = createPaddleGame(ball1, keyboardSensor);

        // "dead zone" --> when the ball hit this block, we will remove it from the game
        Rectangle rect = new Rectangle(new Point(20, HEIGHT), WIDTH - (2 * 20), 1);
        Block deadZone = new Block(rect, Color.BLACK);
        deadZone.addToGame(this);
        // removes the ball from the game
        BallRemover removeBalls = new BallRemover(this, this.remainingBalls);
        deadZone.addHitListener(removeBalls);
        // create the score rectangle
        Rectangle scoreHelper = new Rectangle(new Point(0, 0), WIDTH, 20);
        ScoreIndicator printScore = new ScoreIndicator(scoreHelper, this.score);
        this.addSprite(printScore);
        // create the "lives" rectangle
        Rectangle livesHelper = new Rectangle(new Point(0, 0), WIDTH, 20);
        LivesIndicator livesPrintScore = new LivesIndicator(livesHelper, this.lives, this.levelInformation.levelName());
        this.addSprite(livesPrintScore);

        for (Block block : this.levelInformation.blocks()) {
            // adding listener to block removals
            BlockRemover removeBlocks = new BlockRemover(this, this.remainingBlocks);
            block.addHitListener(removeBlocks);
            // adding listener to update the score after a block is removing
            ScoreTrackingListener scoreTracking = new ScoreTrackingListener(this.score);
            block.addHitListener(scoreTracking);
            remainingBlocks.increase(1);
            block.addToGame(this);
        }
    }

    /**
     * The function role is to run the game: considering all the objects in the game (start the animation loop).
     *
     */
    public void playOneTurn() {
        int flag = 0;
        // calling the function from the second run.
        flag++;
        if (flag != 1) {
            this.createBallsOnTopOfPaddle();
        }
        // countdown before turn starts.
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        // use our runner to run the current animation - which is one turn of the game.
        this.runner.run(this);
    }

    /**
     *
     * @return the remaining blocks.
     */
    public Counter getRemainingBlocks() {
        return this.remainingBlocks;
    }

    /**
     *
     * @return the remaining lives.
     */
    public Counter getLives() {
        return this.lives;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // define the blue color of the screen
        Color blue = new Color(0x0194ca);
        d.setColor(blue);
        d.fillRectangle(0, 0, WIDTH, HEIGHT);
        this.sprites.drawAllOn(d);
        // show every detail in our game
        this.sprites.notifyAllTimePassed();
        // the keyboard sensor
        biuoop.KeyboardSensor keyboardSensor = this.runner.getGui().getKeyboardSensor();

        // if the game is "paused"
        if (keyboardSensor.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(keyboardSensor, KeyboardSensor.SPACE_KEY,
                    new PauseScreen(keyboardSensor)));
        }
        // while there is no more blocks in the game --> exit
        if (this.remainingBlocks.getValue() <= 0) {
            this.score.increase(100);
            this.running = false;
        }
        // create the "new game"
        if (this.remainingBalls.getValue() <= 0) {
            // presenting the counting down screen (without the last life)
            if (this.getLives().getValue() > 1) {
                this.runner.run(new CountdownAnimation(2, 3, this.sprites));
            }
            // decrease the live by one
            this.lives.decrease(1);
            // remove the "old" paddle
            this.removeCollidable(this.paddleGame);
            this.removeSprite(this.paddleGame);
            // creating the "new" balls and paddle
            Ball ball1 = createBallGame();
            // creating the frames of the screen
            createFrames(ball1);
            ball1.addToGame(this);
            // creating the other balls - num of balls - 1 (because we create one earlier).
            for (int i = 0; i < this.levelInformation.numberOfBalls() - 1; i++) {
                Ball ball = createBallGame();
                ball.addToGame(this);
            }
            this.paddleGame = createPaddleGame(ball1, keyboardSensor);
        }
        // while there is no lives for the player --> exit
        if (this.lives.getValue() <= 0) {
            HighScores scr = new HighScores(this.score.getValue());
            scr.UpdateScoreFile();
            runner.run(new KeyPressStoppableAnimation(runner.getKeyboardSensor(), KeyboardSensor.SPACE_KEY,
                    new EndScreen(runner.getKeyboardSensor(), score, 0)));
            this.running = false;
            this.runner.getGui().close();
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}

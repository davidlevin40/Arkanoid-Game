package levels;

import geometry.Block;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import interfaces.LevelInformation;
import interfaces.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * FirstLevel class.
 * This class will create the first level of the game.
 */
public class FirstLevel implements LevelInformation {
    // change of movement for the paddle
    private static final int SPEED_PADDLE = 7;
    // number ob balls game
    private static final int NUM_OF_BALLS_GAME = 1;
    // width of rectangle
    private static final int PADDLE_WIDTH = 80;
    // number of blocks needed to remove
    private static final int NUM_OF_BLOCKS = 1;
    // width of 'screen'
    private static final int WIDTH = 800;
    // width of block
    private static final int B_WIDTH = 30;

    @Override
    public int numberOfBalls() {
        return NUM_OF_BALLS_GAME;
    }

    @Override
    public int paddleSpeed() {
        return SPEED_PADDLE;
    }

    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return NUM_OF_BLOCKS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<Velocity>();
        // create the velocity
        Velocity v = Velocity.fromAngleAndSpeed(0, 5);
        // add the block game to the list
        list.add(v);
        return list;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        Sprite s = new FirstLevelMap();
        return s;
    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<Block>();
        // create the game blocks
        Point leftRight = new Point((WIDTH / 2) - (B_WIDTH / 2), B_WIDTH * 4);
        Rectangle rec = new Rectangle(leftRight, B_WIDTH, B_WIDTH);
        Block block = new Block(rec, Color.RED);
        // add the block game to the list
        list.add(block);
        return list;
    }
}

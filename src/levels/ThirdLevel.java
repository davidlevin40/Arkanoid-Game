package levels;
// 316554641

import geometry.Block;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import interfaces.LevelInformation;
import interfaces.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * ThirdLevel class.
 * This class will create the third level of the game.
 */
public class ThirdLevel implements LevelInformation {
    // change of movement for the paddle
    private static final int SPEED_PADDLE = 7;
    // number ob balls game
    private static final int NUM_OF_BALLS_GAME = 2;
    // width of rectangle
    private static final int PADDLE_WIDTH = 80;
    // number of blocks needed to remove
    private static final int NUM_OF_BLOCKS = 40;
    // width of 'screen'
    private static final int WIDTH = 800;
    // height of rectangle
    private static final int REC_SIZE_HEIGHT = 20;
    // width of rectangle
    private static final int REC_SIZE_WIDTH = 50;

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
        Random rand = new Random();
        List<Velocity> list = new ArrayList<>();
        // create the velocity for the balls
        for (int i = 0; i < NUM_OF_BALLS_GAME; i++) {
            int angle = (rand.nextInt(120) + 1) - 60;
            // define the velocity of the ball1
            Velocity v = Velocity.fromAngleAndSpeed(angle, 5);
            // add the block game to the list
            list.add(v);
        }
        return list;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        Sprite s = new ThirdLevelMap();
        return s;
    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<>();
        // create the game blocks
        for (int i = 0; i < 5; i++) {
            // creating the rectangles in every line
            int flag = 0;
            for (int j = i; j < 10; j++) {
                // creating the blocks
                Block block = createBlocksGame(i, flag);
                list.add(block);
                flag++;
            }
        }
        return list;
    }

    /**
     * The function role is to create the blocks for the game: the inner blocks that fill the board.
     * @param location the location of creating the blocks.
     * @param flag help us to consider the lines and colors of the blocks.
     * @return a new block for the game.
     */
    public Block createBlocksGame(int location, int flag) {
        // creating each of the rectangles in the game
        Point upRect = new Point(WIDTH - (REC_SIZE_WIDTH + REC_SIZE_HEIGHT) - (REC_SIZE_WIDTH * flag)
                - (REC_SIZE_HEIGHT / 4), (REC_SIZE_HEIGHT * 6) + (REC_SIZE_HEIGHT * location));
        Rectangle rec = new Rectangle(upRect, REC_SIZE_WIDTH, REC_SIZE_HEIGHT);

        Color color = colorBricks(location);
        Block block = new Block(rec, color);
        return block;
    }

    /**
     * The function will return the correct color for each rectangle - the flag represents the line.
     * <p>
     * @param flag that represent the line of the rectangle- to create on order game.
     * @return the function return value is Velocity - the new velocity after the collision */
    public Color colorBricks(int flag) {
        if (flag == 0) {
            return Color.GRAY;
        }
        if (flag == 1) {
            return Color.RED;
        }
        if (flag == 2) {
            return Color.YELLOW;
        }
        if (flag == 3) {
            return Color.BLUE;
        }
        return Color.WHITE;
    }
}

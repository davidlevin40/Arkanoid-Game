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
import java.util.Random;

/**
 * FourthLevel class.
 * This class will create the forth level of the game.
 */
public class FourthLevel implements LevelInformation {

    // change of movement for the paddle
    private static final int SPEED_PADDLE = 7;
    // number ob balls game
    private static final int NUM_OF_BALLS_GAME = 3;
    // width of rectangle
    private static final int PADDLE_WIDTH = 80;
    // number of blocks needed to remove
    private static final int NUM_OF_BLOCKS = 105;
    // height of rectangle
    private static final int REC_SIZE_HEIGHT = 20;
    // width of rectangle
    private static final int REC_SIZE_WIDTH = 50;
    // width of 'screen'
    private static final int HEIGHT = 600;
    // width of block
    private static final int B_WIDTH = 25;

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
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        Sprite s = new FourthLevelMap();
        return s;
    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<>();
        int flag = 1;
        // create the game blocks
        for (int i = 0; i < 7; i++) {
            // creating the rectangles in every line
            for (int j = 0; j < 15; j++) {
                // creating the blocks
                Block block = createBlocksGame(i, j, flag);
                list.add(block);
            }
            flag++;
        }
        return list;
    }

    /**
     * The function role is to create the blocks for the game: the inner blocks that fill the board.
     * @param raw that represent the raw.
     * @param column that represent the column.
     * @param flag help us to consider the lines and colors of the blocks.
     * @return a new block for the game.
     */
    public Block createBlocksGame(int raw, int column, int flag) {
        // creating each of the rectangles in the game
        Point upRec = new Point(B_WIDTH + (REC_SIZE_WIDTH * column), (HEIGHT / 5) + (REC_SIZE_HEIGHT * raw));
        Rectangle rec = new Rectangle(upRec, REC_SIZE_WIDTH, REC_SIZE_HEIGHT);

        Color color = colorBricks(flag);
        Block block = new Block(rec, color);
        return block;
    }

    /**
     * The function will return the correct color for each rectangle - the flag represents the line.
     * <p>
     * @param flag that represent the line of the rectangle- to create on order game.
     * @return the function return value is Velocity - the new velocity after the collision */
    public Color colorBricks(int flag) {
        if (flag == 1) {
            return Color.GRAY;
        }
        if (flag == 2) {
            return Color.RED;
        }
        if (flag == 3) {
            return Color.YELLOW;
        }
        if (flag == 4) {
            return new Color(0x3AFF00);
        }
        if (flag == 5) {
            return Color.WHITE;
        }
        if (flag == 6) {
            return Color.PINK;
        }
        return new Color(0x00FFF7);
    }
}

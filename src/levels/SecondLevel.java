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
 * SecondLevel class.
 * This class will create the second level of the game.
 */
public class SecondLevel implements LevelInformation {
    // change of movement for the paddle
    private static final int SPEED_PADDLE = 2;
    // number ob balls game
    private static final int NUM_OF_BALLS_GAME = 10;
    // width of rectangle
    private static final int PADDLE_WIDTH = 600;
    // number of blocks needed to remove
    private static final int NUM_OF_BLOCKS = 15;
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
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        Sprite s = new SecondLevelMap();
        return s;
    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<>();
        int flag = 1;
        for (int i = 0; i < 15; i++) {
            Block block = createBlocksGame(i, flag);
            list.add(block);
            flag++;
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
        Point upRect = new Point((REC_SIZE_WIDTH / 2) + (location * REC_SIZE_WIDTH), (REC_SIZE_WIDTH * 5));
        Rectangle rec = new Rectangle(upRect, REC_SIZE_WIDTH, REC_SIZE_HEIGHT);

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
        if (flag == 1 || flag == 2) {
            Color color = new Color(0xFD0015);
            return color;
        }
        if (flag == 3 || flag == 4) {
            Color color = new Color(0xF37609);
            return color;
        }
        if (flag == 5 || flag == 6) {
            Color color = new Color(0xF3EF04);
            return color;
        }
        if (flag == 7 || flag == 8 || flag == 9) {
            Color color = new Color(0x5DEF0F);
            return color;
        }
        if (flag == 10 || flag == 11) {
            Color color = new Color(0x051FA3);
            return color;
        }
        if (flag == 12 || flag == 13) {
            Color color = new Color(0xF64BDF);
            return color;
        }
        return new Color(0x0CE7E7);
    }
}

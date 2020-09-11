// ID: 316554641

import gamesetting.AnimationRunner;
import gamesetting.GameFlow;
import interfaces.LevelInformation;
import levels.FirstLevel;
import levels.FourthLevel;
import levels.SecondLevel;
import levels.ThirdLevel;
import java.util.ArrayList;
import java.util.List;

/**
 * Ass6Game class. run the game.
 */
public class Play {

     /**
     * The main function, starting the game.
     *
     * @param args dont ger arguments from the user.
     */
    public static void main(String[] args) {
        // creating new list of levels
        AnimationRunner runner = new AnimationRunner();
        List<LevelInformation> list = new ArrayList<>();

        LevelInformation levelInformation1 = new FirstLevel();
        LevelInformation levelInformation2 = new SecondLevel();
        LevelInformation levelInformation3 = new ThirdLevel();
        LevelInformation levelInformation4 = new FourthLevel();

        // When run without arguments - run the default levels (1 to 4)
        if (args.length == 0) {
            list.add(levelInformation1);
            list.add(levelInformation2);
            list.add(levelInformation3);
            list.add(levelInformation4);
        }

        // When run with arguments - check the content
        if (args.length > 0) {
            int flag = 0;
            // checking each args - receive only numbers between 1 to 4
            for (int i = 0; i < args.length; i++) {
                // illegal char/digit/sign
                if (!args[i].equals("1") && !args[i].equals("2") && !args[i].equals("3") && !args[i].equals("4")) {
                    continue;
                }
                // first level
                if (args[i].equals("1")) {
                    list.add(levelInformation1);
                    flag++;
                }
                // second level
                if (args[i].equals("2")) {
                    list.add(levelInformation2);
                    flag++;
                }
                // third level
                if (args[i].equals("3")) {
                    list.add(levelInformation3);
                    flag++;
                }
                // fourth level
                if (args[i].equals("4")) {
                    list.add(levelInformation4);
                    flag++;
                }
            }
            // when all the arguments are "non legal" (not from 1 to 4) - run the default levels
            if (flag == 0) {
                list.add(levelInformation1);
                list.add(levelInformation2);
                list.add(levelInformation3);
                list.add(levelInformation4);
            }
        }
        // run the game
        GameFlow gameflow = new GameFlow(runner, runner.getKeyboardSensor());
        gameflow.runLevels(list);
    }
}

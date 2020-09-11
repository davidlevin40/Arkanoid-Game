package gamesetting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * HighScores class. save the highest score of the game.
 */
public class HighScores {

    private int score;

    public HighScores(int score) {
        this.score = score;
    }


    public void UpdateScoreFile() {
            try {
                File myObj = new File("C:\\Users\\david\\IdeaProjects\\ass7\\highscores.txt");
                if (myObj.createNewFile()) {
                    FileWriter myWriter = new FileWriter(myObj);
                    myWriter.write("The highest score so far is: " + this.score);
                    myWriter.close();
                    System.out.println(2);
                }
                else {
                    BufferedReader buffi = new BufferedReader(new InputStreamReader(new FileInputStream("highscores.txt")));
                    // get access to the score text
                    String line = buffi.readLine();
                    String temp = line.substring(31);
                    if (Integer.parseInt(temp) <= this.score) {
                        FileWriter myWriter = new FileWriter(myObj);
                        myWriter.write("The highest score so far is: " + this.score);
                        myWriter.close();
                        System.out.println(1);
                    }
                    buffi.close();
                }
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
    }
}

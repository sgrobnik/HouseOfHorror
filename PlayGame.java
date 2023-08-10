// class Starting Screen 
// sets up the first panel of the game, with game explanation and difficulty level buttons

import javax.swing.*;
import java.awt.image.BufferedImage;

public class PlayGame extends JFrame{

    // set up panel
    static final int WIDTH = 1000;
    static final int HEIGHT = 600;

    // music 
    public static  String MUSIC = "DarkAlley.wav";

    // background image
    public static  String BACKGROUND = "StartScreenBackground.jpg";
    public static BufferedImage bgImage;
    public static JFrame frame = new JFrame("House of Horror");
    public static char difficulty;


    public static void main(String[] args) {
        frame = new FirstScreen();

    }

}
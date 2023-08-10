// class Starting Screen 
// sets up the first panel of the game, with game explanation and difficulty level buttons

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstScreen extends JFrame{

    // set up panel
    static final int WIDTH = 1000;
    static final int HEIGHT = 600;

    // music 
    public static final String MUSIC = "DarkAlley.wav";

    // background image
    public static String BACKGROUND = "StartScreenBackground.jpg";
    public static BufferedImage bgImage;
    public static JFrame frame = new JFrame("House of Horror");

    private static ActionListener listener;
    public static char difficulty;

    public FirstScreen() {
        // load BACKGROUND image
        loadBG();
        // play audio file
        StdAudio.play(MUSIC);
        // display labels with game explanation
        explainGame();

    }


    // load BACKGROUND image into the variable bgImage
    public static void loadBG() {
        try {
            // read in image
            bgImage = ImageIO.read(new File(BACKGROUND));

            // set up jframe
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(new Dimension(WIDTH, HEIGHT));
            frame.setTitle("House of Horror");
            frame.setContentPane(new ImagePanel(bgImage)); // put up background image
            frame.setVisible(true);

        } catch (IOException e) {
            System.out.println("Could not open " + bgImage);
            System.exit(1);
        }
    }

    //draw a series of labels with the game explanation, draw difficulty level buttons
    public static void explainGame(){
        frame.setLayout(new FlowLayout());
        JLabel expl = new JLabel("Welcome to House of Horror. Your goal is simple: to escape the house.");
        JLabel expl2 = new JLabel(" If you at any point make a wrong decision or act too slowly, you will die. Choose wisely.");
        JLabel expl3 = new JLabel("Now choose your difficulty level:");
        expl.setFont(new Font("Serif", Font.BOLD, 20));
        expl.setForeground(Color.RED);
        expl2.setFont(new Font("Serif", Font.BOLD, 20));
        expl2.setForeground(Color.RED);
        expl3.setFont(new Font("Serif", Font.BOLD, 20));
        expl3.setForeground(Color.RED);
        frame.add(expl); 
        frame.add(expl2);
        frame.add(expl3);
        JButton easy = new JButton("easy");
        frame.add(easy);
        JButton medium  = new JButton("medium");
        frame.add(medium);
        JButton hard = new JButton("hard");
        frame.add(hard);
        frame.setVisible(true);

        listener = new ActionListener() { 
            public void actionPerformed(ActionEvent event) {
                if (event.getSource() == easy) difficulty = 'e'; 
                else if (event.getSource() == medium) difficulty= 'm';
                else if (event.getSource() == hard) difficulty= 'h';

                // then, once difficulty level is selected 
                frame.dispose(); 
                frame=new GameSceneNew();
            } // end of actionPerformed
        }; // end of anonymous inner class

        // add action listeners
        easy.addActionListener(listener);
        medium.addActionListener(listener);
        hard.addActionListener(listener);
    
    }

}



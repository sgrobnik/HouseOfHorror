import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

public class YouWon3 extends GameSceneNew{

    private static ActionListener listener;
    private static String BACKGROUND = "free.png";
    private static String MUSIC = FirstScreen.MUSIC;
    private static BufferedImage bgImage;

    public YouWon3(){
        // load BACKGROUND image
       loadBG();
       // play audio file
       StdAudio.play(MUSIC);
       // display labels with game explanation
       scenePlot();
    }

    public static void loadBG() {
        try {
            // read in image
            bgImage = ImageIO.read(new File(BACKGROUND));

            // set up jframe
            frame.dispose();
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
    //will be the options of restart / quit game (ovveride GameScene's scenePlot method)
    public static void scenePlot(){
        frame.setLayout(new FlowLayout());
        JLabel win1 = new JLabel("Congratulations, you escaped the house! But this isn't your only path to survival...");
        win1.setForeground(Color.WHITE);
        win1.setFont(new Font("Serif", Font.BOLD, 20));
        JLabel win2 = new JLabel("Play again to find out what other adventures are in store for you!");
        win2.setFont(new Font("Serif", Font.BOLD, 20));
        win2.setForeground(Color.WHITE);
        frame.add(win1);
        frame.add(win2);
        JButton exit = new JButton("quit game");
        JButton restart = new JButton("play another game");
        frame.add(exit);
        frame.add(restart);
        frame.setVisible(true);

        listener = new ActionListener() { 
            public void actionPerformed(ActionEvent event) {
                if (event.getSource() == exit) {frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING)); System.out.println("here");} // quit game option
                else if (event.getSource() == restart) {
                    frame.dispose();
                    frame = new FirstScreen();
                };
            } // end of actionPerformed
        }; // end of anonymous inner class

        // add action listeners
        exit.addActionListener(listener);
        restart.addActionListener(listener);
    }

}



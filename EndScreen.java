import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

public class EndScreen extends GameSceneNew{

    private static ActionListener listener;
    private static String BACKGROUND = "YouDied.png";
    private static String MUSIC = FirstScreen.MUSIC;
    private static BufferedImage bgImage;

    public EndScreen(){
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
                    if (timer!=null){timer.stop();}
                    frame = new FirstScreen();
                };
            } // end of actionPerformed
        }; // end of anonymous inner class

        // add action listeners
        exit.addActionListener(listener);
        restart.addActionListener(listener);
    }

}



import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.util.Date;

public class GameSceneNew extends JFrame {

    // scene code
    public static final String[] backgrounds = new String[110];
    public static int sceneNumber = 1;
    private static ActionListener listener;
    public static String[][][] scenePrompts = new String[110][10][3];
    // private int count;

    // set up panel
    static final int WIDTH = 1000;
    static final int HEIGHT = 600;

    // music
    public static String MUSIC = FirstScreen.MUSIC;

    // background image
    public static String BACKGROUND = "StartScreenBackground.jpg";
    public static BufferedImage bgImage;
    public static JFrame frame = new JFrame("House of Horror");

    public static Timer timer;
    public static JLabel clock;

    public static int time;
    public static int count;

    // death message
    public static String deathMessage = "Think carefully about what choices you make!";

    // timer
    public static boolean hasWrench;
    public static boolean hasLighter;

    public GameSceneNew() {
        // load scenes
        engageScenes();
        // load BACKGROUND image
        loadBG();
        // play audio file
        StdAudio.play(MUSIC);
        // // display labels with game explanation
        // scenePlot();

        // first set total time in ms based on difficulty selection
        if (sceneNumber == 1) {
            if (FirstScreen.difficulty == 'e')
                time = 150 * 1000;
            else if (FirstScreen.difficulty == 'h')
                time = 50 * 1000;
            else
                time = 100 * 1000; // make medium the default time in case anything goes wrong
            count = time - 1000;
        } // set up count for first scene

        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("mm : ss");
        clock = new JLabel(sdf.format(new Date(time)), JLabel.CENTER);
        clock.setFont(new Font("Serif", Font.BOLD, 40));
        clock.setForeground(Color.RED);
        clock.setAlignmentX(Component.CENTER_ALIGNMENT);
        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (count == 0) {
                    frame.dispose();
                    EndScreen.loadBG();
                    EndScreen.scenePlot();
                    timer.stop();
                } // if time runs out, you die
                clock.setText(sdf.format(new Date(count)));
                count -= 1000;
            } // end of action performed
        }; // end of action listener class
        timer = new javax.swing.Timer(1000, al);
        timer.start();
        frame.add(clock);
        frame.setVisible(true);

        // display labels with game explanation
        scenePlot();
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

    public static void engageScenes() {
        // starts the game without a wrench/lighter
        hasWrench = false;
        hasLighter = false;

        // scene one - we are in front of the house for the first time
        backgrounds[1] = "StartScreenBackground.jpg";
        scenePrompts[1][1][0] = "Phew! I finally made it to the place I'm supposed to meet my blind date.";
        scenePrompts[1][2][0] = "Jeez, this house looks old, and it gives me the creeps!";
        scenePrompts[1][3][0] = "I guess they can't all be green flags... Still...";
        scenePrompts[1][0][1] = "Turn around and walk back home";
        scenePrompts[1][0][2] = "Go inside the house and meet your date!";

        // scene two - you go inside, and both options should lead to the same outcome
        // (progressing)
        backgrounds[2] = "SceneOneBackground.jpg";
        scenePrompts[2][1][0] = "Looks like the door was unlocked, but... This place gives me the heebie jeebies!";
        scenePrompts[2][2][0] = "What do I say?";
        scenePrompts[2][3][0] = "       ";
        scenePrompts[2][0][1] = "\"Is anybody there?!\"";
        scenePrompts[2][0][2] = "\"Your house stinks!\"";

        // scene three - continuation of scene two now it should play like a scream
        // audio
        backgrounds[3] = "SceneOneBackground.jpg";
        scenePrompts[3][1][0] = "Ahh! What was that?! It sounds like it came from upstairs";
        scenePrompts[3][2][0] = "      ";
        scenePrompts[3][3][0] = "      ";
        scenePrompts[3][0][1] = "Go up and investigate";
        scenePrompts[3][0][2] = "Run the other way!";

        // scene four - the door is locked!
        backgrounds[4] = "SceneOneBackground.jpg";
        scenePrompts[4][1][0] = "Oh no! The front door is locked shut!";
        scenePrompts[4][2][0] = "I can't get out!";
        scenePrompts[4][3][0] = "And there's someone coming!";
        scenePrompts[4][0][1] = "Just run! Anywhere!!!";
        scenePrompts[4][0][2] = "Stay still and wait for whatever is upstairs to find you";

        // scene five - choose between bathroom and kitchen
        backgrounds[5] = "SceneHallWay11.jpg";
        scenePrompts[5][1][0] = "Phew... I don't know what's going on here, but this is so not cool!";
        scenePrompts[5][2][0] = "I need to get out of here... but how?!";
        scenePrompts[5][3][0] = "    ";
        scenePrompts[5][0][1] = "Go into the kitchen";
        scenePrompts[5][0][2] = "Go into the bathroom";

        // scene six a - you chose the bathroom
        backgrounds[6] = "Haunted5.jpeg";
        scenePrompts[6][1][0] = "The bathroom! There's got to be something here that can help me get out!";
        scenePrompts[6][2][0] = "There's also a mirror here, I could use it to fix my make-up real quick.";
        scenePrompts[6][3][0] = "After all... I have to make sure I still look presentable to Mr. Right!";
        scenePrompts[6][0][1] = "Take time to fix make-up";
        scenePrompts[6][0][2] = "Look around and try to find something useful";

        // scene six b - you look around to find something useful
        backgrounds[60] = "Kitchin2.jpg";
        scenePrompts[60][1][0] = "The kitchen! Ew! This place reeks!";
        scenePrompts[60][2][0] = "Is this supposed to be the meal my date prepared?";
        scenePrompts[60][3][0] = "     ";
        scenePrompts[60][0][1] = "Try the food! Mmmm!";
        scenePrompts[60][0][2] = "Look around and try to find something useful";

        // scene seven - you look around to find something useful in the bathroom
        backgrounds[7] = "Haunted5.jpeg";
        scenePrompts[7][1][0] = "There seems to be a wrench here. But it's stuck to the faucet!";
        scenePrompts[7][2][0] = "Taking it will cost me some time...";
        scenePrompts[7][3][0] = "     ";
        scenePrompts[7][0][1] = "Take wrench (-30s)";
        scenePrompts[7][0][2] = "Go back out into the hallway!";

        // scene seven b - you look around to find something useful in the kitchen
        backgrounds[61] = "Kitchin2.jpg";
        scenePrompts[61][1][0] = "There seems to be a lighter here. But it's stuck in the oven door!";
        scenePrompts[61][2][0] = "Taking it will cost me some time...";
        scenePrompts[61][3][0] = "     ";
        scenePrompts[61][0][1] = "Take lighter (-30s)";
        scenePrompts[61][0][2] = "Go back into the hallway!";

        // scene eight
        backgrounds[8] = "SceneHallWay11.jpg";
        scenePrompts[8][1][0] = " ";
        scenePrompts[8][2][0] = "I think I hear something in the foyer! I need to get to the main door!";
        scenePrompts[8][3][0] = "But it might be safer to find another way.";
        scenePrompts[8][0][2] = "Run blindly toward the noise!";
        scenePrompts[8][0][1] = "Go further down the hall";

        // scene nine
        backgrounds[9] = "SceneHallWay11.jpg";
        scenePrompts[9][1][0] = "Ah! I fell and made lots of noise!";
        scenePrompts[9][2][0] = "Whoever was in the foyer definitely heard that";
        scenePrompts[9][3][0] = "If only I had a wrench or something, I could probably fight...";
        scenePrompts[9][0][1] = "Face whatever is coming!";
        scenePrompts[9][0][2] = "Run to the last room down the hall!";

        // scene nine b - you had the wrench!
        backgrounds[90] = "SceneHallWay11.jpg";
        scenePrompts[90][1][0] = "(You attack the monster with your wrench!)";
        scenePrompts[90][2][0] = "Ha! Take that loser!";
        scenePrompts[90][3][0] = "   ";
        scenePrompts[90][0][2] = "Run to the foyer while your attacker is wounded";
        scenePrompts[90][0][1] = "Stay here and parade your victory";

        // scene ten - you chose to run in nine and get to the bedroom
        backgrounds[10] = "Bedroom.jpg";
        scenePrompts[10][1][0] = "(You slam the door behind you!)";
        scenePrompts[10][2][0] = "Eeek! A dead end! And that crazy thing knows I'm here for sure";
        scenePrompts[10][3][0] = "I could try to risk going back out, or I could look for clues to help me escape";
        scenePrompts[10][0][1] = "Look around for a way to escape";
        scenePrompts[10][0][2] = "Go outside and try to get back to the foyer";

        // scene eleven
        backgrounds[11] = "Bedroom.jpg";
        scenePrompts[11][1][0] = "I see a giant spiderweb covering what seems to be a crawlway";
        scenePrompts[11][2][0] = "But these could be deadly! If only I had a lighter or something...";
        scenePrompts[11][3][0] = "I could probably set it on fire";
        scenePrompts[11][0][1] = "There has to be something else here...";
        scenePrompts[11][0][2] = "Engage the spiderweb! (do you have a lighter?)";

        // scene twelve
        backgrounds[12] = "Bedroom.jpg";
        scenePrompts[12][1][0] = "Ah-hah! I see a trapdoor below the bed!";
        scenePrompts[12][2][0] = "But that bed looks heavy... Moving it will take effort.";
        scenePrompts[12][3][0] = "  ";
        scenePrompts[12][0][1] = "It's not worth wasting my time, run outside!";
        scenePrompts[12][0][2] = "Move the bed and enter the trapdoor (-20s)";

        // scene thirteen - basement
        backgrounds[13] = "Dark.jpg";
        scenePrompts[13][1][0] = "It's so dark in here... I can't see anything!";
        scenePrompts[13][2][0] = "If only I had some sort of light source...";
        scenePrompts[13][3][0] = "  ";
        scenePrompts[13][0][1] = "Look for a lightswitch";
        scenePrompts[13][0][2] = "Walk around in the dark";

        // scene fourteen - in the dark basement
        backgrounds[14] = "Dark.jpg";
        scenePrompts[14][1][0] = "It's just too dark! But I've got to keep looking";
        scenePrompts[14][2][0] = "  ";
        scenePrompts[14][3][0] = "  ";
        scenePrompts[14][0][1] = "Look for a lightswitch";
        scenePrompts[14][0][2] = "Walk around in the dark";

        // scene 15, basement
        backgrounds[15] = "Basement.jpg";
        scenePrompts[15][1][0] = "Light!!";
        scenePrompts[15][2][0] = "Looks like I'm in a basement, perhaps there is a way out somewhere?";
        scenePrompts[15][3][0] = "I see two doors, and one of them has a weird liquid seeping underneath";
        scenePrompts[15][0][1] = "Go through the weird liquid door";
        scenePrompts[15][0][2] = "Go through the other door (an exit...?)";

        // scene 91 (cont of 90), the last foyer scene
        backgrounds[91] = "SceneOneBackground.jpg";
        scenePrompts[91][1][0] = "Yay!! I made it back to the foyer!";
        scenePrompts[91][2][0] = "The door is still locked, but there's got to be a way out somewhere";
        scenePrompts[91][3][0] = "  ";
        scenePrompts[91][0][1] = "Look around for a key";
        scenePrompts[91][0][2] = "Attack the door with your wrench";

        // scene 92, ending
        backgrounds[92] = "SceneOneBackground.jpg";
        scenePrompts[92][1][0] = "The door is open! Yes! I can escape and finally get out";
        scenePrompts[92][2][0] = "(Quickly!!)";
        scenePrompts[92][3][0] = "  ";
        scenePrompts[92][0][2] = "Actually... maybe I should check up on that monster";
        scenePrompts[92][0][1] = "Escape!!!!!";

        // scene 93, ending
        backgrounds[93] = "Bedroom.jpg";
        scenePrompts[93][1][0] = "The spider web burned down!";
        scenePrompts[93][2][0] = "Looks like the tunnel might be able to lead me out";
        scenePrompts[93][3][0] = "But the fire seems to be spreading";
        scenePrompts[93][0][2] = "Go into the tunnel";
        scenePrompts[93][0][1] = "Stay and burn with the rest of the house";

        // this scene plays when you choose to go back at the start, you're traversing
        // through the woods at random
        backgrounds[20] = "HauntedWoods.jpg";
        scenePrompts[20][1][0] = "Uh oh! I can't seem to find my way back...";
        scenePrompts[20][2][0] = "     ";
        scenePrompts[20][3][0] = "     ";
        scenePrompts[20][0][1] = "Go left";
        scenePrompts[20][0][2] = "Go right";
        backgrounds[21] = "HauntedWoods.jpg";
        scenePrompts[21][1][0] = "Ah! I'm still lost... Where do I go now?";
        scenePrompts[21][2][0] = "     ";
        scenePrompts[21][3][0] = "     ";
        scenePrompts[21][0][1] = "Go left again";
        scenePrompts[21][0][2] = "Go right again";
        backgrounds[22] = "HauntedWoods.jpg";
        scenePrompts[22][1][0] = "I see something in the distance!! Could it be...?";
        scenePrompts[22][2][0] = "     ";
        scenePrompts[22][3][0] = "     ";
        scenePrompts[22][0][1] = "Go towards it";
        scenePrompts[22][0][2] = "Go back";
    }

    // draw a series of labels with the game explanation, draw difficulty level
    // buttons
    public static void scenePlot() {

        // dialogue / narrative lines
        JLabel expl = new JLabel(scenePrompts[sceneNumber][1][0]);
        expl.setFont(new Font("Serif", Font.BOLD, 20));
        expl.setForeground(Color.WHITE);
        if (sceneNumber == 8) {expl.setForeground(Color.YELLOW);}
        expl.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel expl2 = new JLabel(scenePrompts[sceneNumber][2][0]);
        expl2.setFont(new Font("Serif", Font.BOLD, 20));
        expl2.setForeground(Color.WHITE);
        expl2.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel expl3 = new JLabel(scenePrompts[sceneNumber][3][0]);
        expl3.setFont(new Font("Serif", Font.BOLD, 20));
        expl3.setForeground(Color.WHITE);
        expl3.setAlignmentX(Component.CENTER_ALIGNMENT);

        // manual spacing
        JLabel space1 = new JLabel(" ");
        space1.setFont(new Font("Serif", Font.BOLD, 20));
        space1.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel space2 = new JLabel(" ");
        space2.setFont(new Font("Serif", Font.BOLD, 20));
        space2.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel space3 = new JLabel(" ");
        space3.setFont(new Font("Serif", Font.BOLD, 20));
        space3.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel death = new JLabel(deathMessage);
        death.setFont(new Font("Serif", Font.BOLD, 20));
        death.setForeground(Color.RED);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        frame.add(expl);
        frame.add(expl2);
        frame.add(expl3);
        frame.add(space3);

        // prompts 
        JButton option1 = new JButton(scenePrompts[sceneNumber][0][1]);
        option1.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(option1);
        JButton option2 = new JButton(scenePrompts[sceneNumber][0][2]);
        option2.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(option2);

        // misc music stuff
        if (sceneNumber == 3 || sceneNumber == 8) {
            StdAudio.play("DemonicScream.wav");
        }

        frame.setVisible(true);

        listener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (event.getSource() == option1 || event.getSource() == option2) {
                    StdAudio.play("Click.wav");
                }
                // scene one options
                if (sceneNumber == 1) {
                    if (event.getSource() == option2) {
                        sceneNumber++;
                    } else if (event.getSource() == option1) {
                        sceneNumber = 20;
                    }
                    // options don't matter, except for scene 13 where it checks for a lightswitch
                } else if (sceneNumber == 2 || sceneNumber == 13 || sceneNumber == 14 || sceneNumber == 91) {
                    // check if its scene 13 with a lighter
                    if (sceneNumber == 13 && hasLighter && event.getSource() == option2) {
                        sceneNumber = 15;
                    } else if (sceneNumber == 14 && event.getSource() == option1) {
                        double rand1 = Math.random();
                        if (rand1 < 0.07) {
                            sceneNumber++;
                        } 
                    } else if (sceneNumber == 14 && event.getSource() == option2) {
                            sceneNumber = 14;
                    } else if (event.getSource() == option1 || event.getSource() == option2) {
                        sceneNumber++;
                    }
                    // bathroom / other room choice
                } else if (sceneNumber == 5) {
                    if (event.getSource() == option2) {
                        sceneNumber++;
                    } else if (event.getSource() == option1) {
                        sceneNumber = 60;
                    }
                    // has wrench in scene 9 makes it proceed
                } else if (sceneNumber == 9 && hasWrench) {
                    if (event.getSource() == option1) {
                        sceneNumber = 90;
                    } else if (event.getSource() == option2) {
                        sceneNumber++;
                    }
                }

                // leaving bathroom
                else if (sceneNumber == 7 || sceneNumber == 61) {
                    if (event.getSource() == option1) {
                        if (sceneNumber == 7) {
                            hasWrench = true;
                            scenePrompts[8][1][0] = "(You got the wrench!)";
                        } else if (sceneNumber == 61) {
                            hasLighter = true;
                            scenePrompts[8][1][0] = "(You got the lighter!)";
                            scenePrompts[13][0][2] = "Use your lighter!";
                        }
                        count -= 30000;
                        sceneNumber = 8;
                    } else if (event.getSource() == option2) {
                        sceneNumber = 8;
                    }
                } else if (sceneNumber == 11 && hasLighter) {
                    if (event.getSource() == option2) {
                        sceneNumber = 93;
                    } else if (event.getSource() == option1) {
                        sceneNumber++;
                    }
                } else if (sceneNumber == 93) {
                    if (event.getSource() == option2) {
                        frame.dispose();
                        sceneNumber = 1;
                        BACKGROUND = backgrounds[sceneNumber];
                        if (timer!=null){timer.stop();}
                        YouWon2.loadBG();
                        YouWon2.scenePlot();
                        return;
                    } else if (event.getSource() == option1) {
                        frame.dispose();
                        sceneNumber = 1;
                        BACKGROUND = backgrounds[sceneNumber];
                        EndScreen.loadBG();
                        EndScreen.scenePlot();
                        StdAudio.play("GameOver.wav");
                        return;
                    }
                }
                // woods case
                else if (sceneNumber == 20 || sceneNumber == 21) {
                    double rand = Math.random();
                    if (event.getSource() == option1 && rand > 0.50) {
                        sceneNumber++;
                    } else if (event.getSource() == option2 && rand < 0.50) {
                        sceneNumber++;
                    } else if (event.getSource() == option1 || event.getSource() == option2) {
                        frame.dispose();
                        sceneNumber = 1;
                        BACKGROUND = backgrounds[sceneNumber];
                        EndScreen.loadBG();
                        EndScreen.scenePlot();
                        StdAudio.play("GameOver.wav");
                        return;
                    }
                } else if (sceneNumber == 22) {
                    double rand = Math.random();
                    if (event.getSource() == option2) {
                        sceneNumber = 20;
                    } else if (event.getSource() == option1 && rand > 0.5) { // half the time, will win the game
                        frame.dispose();
                        sceneNumber = 1;
                        BACKGROUND = backgrounds[sceneNumber];
                        if (timer!=null){timer.stop();}
                        YouWon1.loadBG();
                        YouWon1.scenePlot();
                        return;
                    } // otherwise, go back to house
                    else if (event.getSource() == option1 && rand < 0.5) {
                        // update scene 1 text
                        scenePrompts[1][1][0] = "Uh oh, I'm back to where I've started.";
                        scenePrompts[1][2][0] = "And all I've done is lost time!!";
                        scenePrompts[1][3][0] = "This is scary... let me try to find my way out as quick as I can";
                        // go back to scene 1
                        sceneNumber = 1;

                    }
                } // every third scene will be the correct one, so the player does not see the
                  // pattern of always choosing right
                else if (sceneNumber > 2 && sceneNumber % 3 == 0) {
                    if (event.getSource() == option1) {
                        frame.dispose();
                        sceneNumber = 1;
                        BACKGROUND = backgrounds[sceneNumber];
                        EndScreen.loadBG();
                        EndScreen.scenePlot();
                        StdAudio.play("GameOver.wav");
                        return;
                    } else if (event.getSource() == option2) {
                        if (sceneNumber == 12) {
                            count -= 20000;
                        }
                        if (sceneNumber == 15) {
                            frame.dispose();
                            sceneNumber = 1;
                            BACKGROUND = backgrounds[sceneNumber];
                            if (timer!=null){timer.stop();}
                            YouWon3.loadBG();
                            YouWon3.scenePlot();
                            return;
                        }
                        sceneNumber++;
                    }
                } else if (sceneNumber > 2 && sceneNumber % 3 != 0) {
                    if (event.getSource() == option2) {
                        frame.dispose();
                        sceneNumber = 1;
                        BACKGROUND = backgrounds[sceneNumber];
                        EndScreen.loadBG();
                        EndScreen.scenePlot();
                        StdAudio.play("GameOver.wav");
                        return;
                    } else if (event.getSource() == option1) {
                        if (sceneNumber == 92) {
                            frame.dispose();
                            sceneNumber = 1;
                            BACKGROUND = backgrounds[sceneNumber];
                            if (timer!=null){timer.stop();}
                            YouWon3.loadBG();
                            YouWon3.scenePlot();
                            return;
                        }
                        sceneNumber++;
                    }
                }
                BACKGROUND = backgrounds[sceneNumber];

                loadBG();
                frame.add(clock);
                frame.setVisible(true);
                scenePlot();
            } // end of actionPerformed
        }; // end of anonymous inner class

        // add action listeners
        option1.addActionListener(listener);
        option2.addActionListener(listener);
    }

}

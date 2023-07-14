package boxpuzzle;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.util.concurrent.TimeUnit;

public class BoxPuzzle extends JPanel { // it is of JPanel type

    static Color myColor1 = new Color(180, 199, 231);

    static String theme;
    private JFrame frame;
    private ScoreBoard scoreboard;
    private BoxPuzzle panel;
    private Rules rules;
    final static boolean shouldFill = true;
    int size;
    String level;
    SimpleAudioPlayer sap;

    BoxPuzzle(JFrame frame, ScoreBoard scoreboard, Rules rules, String theme, int n, String lev,
            SimpleAudioPlayer sap) {
        super();
        this.theme = theme;
        this.frame = frame;
        this.level = lev;
        this.scoreboard = scoreboard;
        this.rules = rules;
        this.sap = sap;
        this.size = n;
        panel = this; // NineBoxPuzzle extends from JPanel;
        Color colorBack;
        Color colorFront;

        Theme t = new Theme(theme);
        colorBack = t.colorBack;
        colorFront = t.colorFront;
        if (theme == "Light") {
            panel.setBackground(colorFront);
        } else {
            panel.setBackground(colorBack);
        }

        setLayout(new GridBagLayout());// Setting layout
        GridBagConstraints gridBagLayout = new GridBagConstraints();// we have to do this to add it to container
        gridBagLayout.fill = GridBagConstraints.BOTH;
        gridBagLayout.anchor = GridBagConstraints.CENTER;
        gridBagLayout.ipady = 20;
        gridBagLayout.ipadx = 40;
        gridBagLayout.gridwidth = 1;
        gridBagLayout.gridheight = 1;
        gridBagLayout.insets = new Insets(10, 10, 10, 10);

        /// FIRST BUTTON/////////////////////////////////
        JButton startButton = new JButton("Start G");
        startButton.setOpaque(false);
        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false);

        JLabel l6 = new JLabel("");
        l6.setIcon(imageIconPath("Start" + theme));
        l6.setPreferredSize(new Dimension(100, 100));
        startButton.add(l6);
        startButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                startButton.setBackground(Color.GREEN);

                panel.setVisible(false);
                frame.setContentPane(new PlayArea(frame, panel, scoreboard, theme, size, level, rules, sap)); // new
                                                                                                              // PlayArea
                                                                                                              // is
                                                                                                              // instanciated
            }

        });
        startButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                startButton.setBackground(Color.GREEN);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                startButton.setBackground(UIManager.getColor("control"));
            }
        });

        startButton.setFont(new Font("Dialog", Font.PLAIN, 40));
        gridBagLayout.gridx = 3;
        gridBagLayout.gridy = 0;
        add(startButton, gridBagLayout);
        //////////////////////////////////////////////////
        // SECOND BUTTON///////////////////////////////////
        JButton scoresButton = new JButton("score");
        // scoresButton.setBorder(border);
        scoresButton.setOpaque(false);
        scoresButton.setContentAreaFilled(false);
        scoresButton.setBorderPainted(false);

        JLabel l5 = new JLabel("");
        l5.setIcon(imageIconPath("Scores" + theme));
        l5.setPreferredSize(new Dimension(100, 100));
        scoresButton.add(l5);

        scoresButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setVisible(false);
                frame.setContentPane(scoreboard);
                scoreboard.showList();
                scoreboard.setVisible(true);
            }

        });
        scoresButton.setFont(new Font("Dialog", Font.PLAIN, 40));
        gridBagLayout.gridx = 3;
        gridBagLayout.gridy = 1;
        add(scoresButton, gridBagLayout);
        //////////////////////////////////////////////////////

        // THIRD BUTTON////////////////////////////////////////
        JButton ruleButton = new JButton("rule");
        ruleButton.setOpaque(false);
        ruleButton.setContentAreaFilled(false);
        ruleButton.setBorderPainted(false);

        JLabel l7 = new JLabel("");
        l7.setIcon(imageIconPath("Rules" + theme));
        l7.setPreferredSize(new Dimension(100, 100));
        ruleButton.add(l7);
        ruleButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("RULES CLICKED");
                frame.repaint();
                panel.setVisible(false);
                frame.setContentPane(rules);
                rules.showRules();
                rules.setVisible(true);
            }

        });
        ruleButton.setFont(new Font("Dialog", Font.PLAIN, 40));
        gridBagLayout.gridx = 3;
        gridBagLayout.gridy = 2;
        add(ruleButton, gridBagLayout);
        ///////////////////////////////////////////////////////

        // Fourth BUTTON////////////////////////////////////////
        JButton exitButton = new JButton("exit");
        exitButton.setOpaque(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setBorderPainted(false);

        JLabel l8 = new JLabel("");
        l8.setIcon(imageIconPath("Exit" + theme));
        exitButton.add(l8);
        exitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // System.exit(0);
                int x = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit ?", "Comform !",
                        JOptionPane.YES_NO_OPTION);

                if (x == JOptionPane.YES_OPTION) {
                    System.exit(0);
                } else {
                    // setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }

            }

        });
        exitButton.setFont(new Font("Dialog", Font.PLAIN, 40));
        gridBagLayout.gridx = 3;
        gridBagLayout.gridy = 3;
        add(exitButton, gridBagLayout);

        /////////////////////////////////////////////////////

        // Home screen name//////////////////////////////////
        JLabel lTop = new JLabel("");
        lTop.setIcon(imageIconPath("NineBoxPuzzle" + theme));
        lTop.setPreferredSize(new Dimension(100, 100));
        gridBagLayout.gridwidth = 1;
        gridBagLayout.gridheight = 1;
        gridBagLayout.ipadx = 80;
        gridBagLayout.gridx = 1;
        gridBagLayout.gridy = 0;
        add(lTop, gridBagLayout);

        // Home screen Gif Animation////////////////////////////////////
        JLabel l1 = new JLabel("");
        gridBagLayout.gridwidth = 1;
        gridBagLayout.gridheight = 3;
        gridBagLayout.ipadx = 180;
        gridBagLayout.gridx = 1;
        gridBagLayout.gridy = 1;
        l1.setPreferredSize(new Dimension(400, 400));
        l1.setIcon(new ImageIcon(new File("src/resources/image/PuzzlePic1" + theme + ".gif").getAbsolutePath()));
        add(l1, gridBagLayout);
        ////////////////////////////////////////////////////////
    }

    // Audio player//////////////////////////////////////
    protected static void playSound(File soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream((soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);// To remove loop , delete this line
            clip.start();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    /////////////////////////////////////////////////////

    // MENU BAR SETTING FUNCTION///////////////////////////////
    protected void setMenu() {

        BoxPuzzle panel = this;
        JMenuBar menubar = new JMenuBar();
        JMenu options = new JMenu("Theme");
        JMenuItem darkTheme = new JMenuItem("Dark Theme");
        darkTheme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theme = "Dark";
                panel.setVisible(false);
                frame.remove(panel);
                BoxPuzzle panel2 = new BoxPuzzle(frame, scoreboard, rules, theme, size, level, sap);
                scoreboard.setMainMenu(panel2, theme, size, level, rules, sap);
                rules.setMainMenuR(panel2, scoreboard, size, level, theme, sap);
                frame.setContentPane(panel2);
                frame.setVisible(true);
                // frame.dispose();

            }
        });
        darkTheme.setFont(new Font("Dialog", Font.PLAIN, 20));
        options.add(darkTheme);

        JMenuItem lightTheme = new JMenuItem("Light Theme");
        lightTheme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theme = "Light";
                panel.setVisible(false);
                frame.remove(panel);
                BoxPuzzle panel2 = new BoxPuzzle(frame, scoreboard, rules, theme, size, level, sap);
                scoreboard.setMainMenu(panel2, theme, size, level, rules, sap);
                rules.setMainMenuR(panel2, scoreboard, size, level, theme, sap);
                frame.setContentPane(panel2);

                frame.setVisible(true);
                // frame.dispose();
                // frame.setContentPane(new NineBoxPuzzle(frame, scoreboard,rules,theme));
            }
        });
        lightTheme.setFont(new Font("Dialog", Font.PLAIN, 20));
        options.add(lightTheme);
        options.setFont(new Font("Dialog", Font.PLAIN, 20));
        /////////////////////////////////
        JMenu Sound = new JMenu("Sound");
        JMenuItem playSound = new JMenuItem("Play");
        playSound.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    sap.resumeAudio();
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });
        playSound.setFont(new Font("Dialog", Font.PLAIN, 20));
        Sound.add(playSound);

        JMenuItem stopSound = new JMenuItem("Pause Sound");
        stopSound.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sap.pause();
            }
        });
        stopSound.setFont(new Font("Dialog", Font.PLAIN, 20));
        Sound.add(stopSound);

        JMenuItem nextSound = new JMenuItem("Next Sound");
        nextSound.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    sap.setI();
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });
        nextSound.setFont(new Font("Dialog", Font.PLAIN, 20));
        Sound.add(nextSound);

        Sound.setFont(new Font("Dialog", Font.PLAIN, 20));
        menubar.add(Sound);
        /////////////////////////////////
        menubar.add(options);
        ///////////////

        ///////////////

        frame.setJMenuBar(menubar);
        frame.validate();
        frame.repaint();
    }

    static ImageIcon imageIconPath(String name) {
        String path = new File("src/resources/image/" + name + ".png").getAbsolutePath();
        ImageIcon i1 = new ImageIcon(path);
        return i1;
    }

    static JLabel labelMaker(String text, int size) {
        JLabel l2 = new JLabel(text, SwingConstants.CENTER);
        l2.setFont(new Font("Dialog", Font.PLAIN, size));
        l2.setAlignmentX(CENTER_ALIGNMENT);
        l2.setAlignmentY(CENTER_ALIGNMENT);
        // l3.setForeground(tCol);
        return l2;
    }

    public static void starter(JFrame frame) throws MalformedURLException {

        JPanel jp = new JPanel();
        Color colorBack;
        Color colorFront;
        MyBorder border = new MyBorder(theme);

        colorBack = new Color(50, 50, 50);
        colorFront = new Color(214, 237, 23);

        jp.setBackground(colorBack);
        jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));

        JLabel l1 = new JLabel("", SwingConstants.CENTER);

        l1.setIcon(new ImageIcon(new File("src/resources/image/iiita1.gif").getAbsolutePath()));

        l1.setAlignmentX(CENTER_ALIGNMENT);
        l1.setAlignmentY(CENTER_ALIGNMENT);
        jp.add(l1);

        JPanel rP = new JPanel(); // Roll NUmber Panel
        rP.setBackground(colorBack);
        JLabel l2 = labelMaker("GROUP 35", 100);
        l2.setForeground(Color.WHITE);
        jp.add(l2);

        JLabel l3 = labelMaker("IIT2020083   ", 40);
        l3.setForeground(Color.WHITE);
        rP.add(l3);

        JLabel l4 = labelMaker("IIT202020168   ", 40);
        l4.setForeground(Color.WHITE);
        rP.add(l4);

        JLabel l5 = labelMaker("IIT2020192   ", 40);
        l5.setForeground(Color.WHITE);
        rP.add(l5);

        JLabel l6 = labelMaker("IIT2020234", 40);
        l6.setForeground(Color.WHITE);
        rP.add(l6);

        jp.add(rP);

        jp.setAlignmentX(CENTER_ALIGNMENT);
        frame.add(jp);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 750);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void wait(int x) {
        try {
            TimeUnit.SECONDS.sleep(x);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

    }

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        JFrame frame = new JFrame();
        starter(frame);
        wait(3);
        frame.repaint();
        ScoreBoard scoreboard = new ScoreBoard(frame);
        Rules rules = new Rules(frame);
        frame.setTitle("N SQUARE");
        SimpleAudioPlayer sap = new SimpleAudioPlayer();
        sap.play();

        BoxPuzzle panel = new BoxPuzzle(frame, scoreboard, rules, "Dark", 3, "E", sap);
        scoreboard.setMainMenu(panel, "Dark", 3, "E", rules, sap);
        rules.setMainMenuR(panel, scoreboard, 3, "E", "Dark", sap);
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(1000, 750);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        panel.setMenu();

    }

}
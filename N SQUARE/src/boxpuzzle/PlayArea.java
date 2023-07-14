package boxpuzzle;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class PlayArea extends JPanel {  	
    private GameCPU mygamecpu;				
    private ScoreBoard scoreboard;			
    private JFrame frame;					
    private BoxPuzzle mainMenu;			
    private JLabel score;					
	Color colorBack;
	Color colorFront;
    private String theme;
    private int size;
    private String level;
    private Rules rules;
    SimpleAudioPlayer sap;
	
   
    PlayArea(JFrame frame, BoxPuzzle mainMenu, ScoreBoard scoreboard, String theme, int s,String lev, Rules rules, SimpleAudioPlayer sap ) 
    {
    	score = new JLabel("Moves Count : 0");
        score.setFont(new Font("Dialog", Font.PLAIN, 20));
        score.setBorder(new EmptyBorder(0,50,0,50));//top,left,bottom,right
        this.size=s;
        this.theme = theme;
        this.level=lev;
        this.rules=rules;
        this.sap = sap;
        Theme t = new Theme(theme);
    	colorBack = t.colorBack;
        colorFront = t.colorFront;

        
        this.mainMenu = mainMenu;
        
        this.scoreboard = scoreboard;
        this.frame = frame;
        mygamecpu = new GameCPU(this, scoreboard,theme,size,level);
        initializeComponents(size);
        setVisible(true);
        JPanel panel = this;
        panel.setBackground(colorFront);
        
        //OPTIONS _ MENU BAR
        JMenuBar menubar = new JMenuBar();
   
        JButton restartGame = new  JButton("Restart");
        restartGame.setFont(new Font("Dialog", Font.PLAIN, 20));
        restartGame.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        panel.setVisible(false);
                        frame.setContentPane(new PlayArea(frame, mainMenu, scoreboard,theme,size,level, rules,sap));		// new PlayArea is instanciated
                    }
                }
            );
        
        JButton mainMenuButtonTop = new JButton("Main Menu");
        mainMenuButtonTop.setFont(new Font("Dialog", Font.PLAIN, 20));
        mainMenuButtonTop.addActionListener(
            new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    panel.setVisible(false);
                    frame.setContentPane(mainMenu);
                    mainMenu.setMenu();
                    mainMenu.setVisible(true);
                }
            }
        );
        JButton scoreButton = new JButton("Show Scores");
        scoreButton.setFont(new Font("Dialog", Font.PLAIN, 20));
        scoreButton.addActionListener(
            new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    panel.setVisible(false);
                    frame.setContentPane(scoreboard);
                    scoreboard.showList();
                    scoreboard.setVisible(true);
                }
            }
        );
        
        
        ////////////////////////////////////
        
        JMenu levOption = new JMenu("  Level  ");
        JMenuItem levE = new JMenuItem("Easy");
        levE.addActionListener(
            new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e)
                {
                	level="E";
                	panel.setVisible(false);
                    frame.remove(panel);
                    BoxPuzzle mainMenu = new BoxPuzzle(frame, scoreboard,rules,theme,size,level,sap);
                    scoreboard.setMainMenu(mainMenu, theme, size, level, rules,sap);
                    rules.setMainMenuR(mainMenu,scoreboard,size, level, theme,sap);
                    frame.setContentPane(new PlayArea(frame, mainMenu, scoreboard,theme,size,level, rules,sap));
                    frame.setVisible(true);
                    //frame.dispose();
                    
                }
            }
        );
        levE.setFont(new Font("Dialog", Font.PLAIN, 20));
        levOption.add(levE);
        
        JMenuItem levM = new JMenuItem("Medium");
        levM.addActionListener(
            new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e)
                {
                	level="M";
                	panel.setVisible(false);
                    frame.remove(panel);
                    BoxPuzzle mainMenu = new BoxPuzzle(frame, scoreboard,rules,theme,size,level,sap);
                    scoreboard.setMainMenu(mainMenu, theme, size, level, rules,sap);
                    rules.setMainMenuR(mainMenu,scoreboard,size, level, theme,sap);
                    frame.setContentPane(new PlayArea(frame, mainMenu, scoreboard,theme,size,level, rules,sap));
                    frame.setVisible(true);
                    //frame.dispose();
                    
                }
            }
        );
        levM.setFont(new Font("Dialog", Font.PLAIN, 20));
        levOption.add(levM);
        
        JMenuItem levH = new JMenuItem("Hard");
        levH.addActionListener(
            new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e)
                {
                	level="H";
                	panel.setVisible(false);
                    frame.remove(panel);
                    BoxPuzzle mainMenu = new BoxPuzzle(frame, scoreboard,rules,theme,size,level,sap);
                    scoreboard.setMainMenu(mainMenu, theme, size, level, rules,sap);
                    rules.setMainMenuR(mainMenu,scoreboard,size, level, theme,sap);
                    frame.setContentPane(new PlayArea(frame, mainMenu, scoreboard,theme,size,level, rules,sap));
                    frame.setVisible(true);
                    //frame.dispose();
                    
                }
            }
        );
        levH.setFont(new Font("Dialog", Font.PLAIN, 20));
        levOption.add(levH);
        levOption.setFont(new Font("Dialog", Font.PLAIN, 20));
        
        /////////////////////////////////////
        
        /////////////////////////////////////////
        JMenu sizeOption = new JMenu("  Size  ");
        JMenuItem Size3 = new JMenuItem("9 Box");
        Size3.addActionListener(
            new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e)
                {
                	size=3;
                    panel.setVisible(false);
                    frame.remove(panel);
                    BoxPuzzle mainMenu = new BoxPuzzle(frame, scoreboard,rules,theme,size,level,sap);
                    scoreboard.setMainMenu(mainMenu, theme, size, level, rules,sap);
                    rules.setMainMenuR(mainMenu,scoreboard,size,level,theme,sap);
                    frame.setContentPane(new PlayArea(frame, mainMenu, scoreboard,theme,size,level, rules,sap));
                    frame.setVisible(true);
                    //frame.dispose();
                    
                }
            }
        );
        Size3.setFont(new Font("Dialog", Font.PLAIN, 20));
        sizeOption.add(Size3);
        
        
        JMenuItem Size4 = new JMenuItem("16 Box");
        Size4.addActionListener(
            new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e)
                {
                	size=4;
                    panel.setVisible(false);
                    frame.remove(panel);
                    BoxPuzzle mainMenu = new BoxPuzzle(frame, scoreboard,rules,theme,size,level,sap);
                    scoreboard.setMainMenu(mainMenu, theme, size, level, rules,sap);
                    rules.setMainMenuR(mainMenu,scoreboard,size, level , theme,sap);
                    frame.setContentPane(new PlayArea(frame, mainMenu, scoreboard,theme,size,level, rules,sap));
                    frame.setVisible(true);
                    //frame.dispose();
                    
                }
            }
        );
        Size4.setFont(new Font("Dialog", Font.PLAIN, 20));
        sizeOption.add(Size4);
        
        
        JMenuItem Size5 = new JMenuItem("25 Box");
        Size5.addActionListener(
            new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e)
                {
                	size=5;
                    panel.setVisible(false);
                    frame.remove(panel);
                    BoxPuzzle mainMenu = new BoxPuzzle(frame, scoreboard,rules,theme,size,level,sap);
                    scoreboard.setMainMenu(mainMenu, theme, size, level, rules,sap);
                    rules.setMainMenuR(mainMenu,scoreboard,size, level,theme,sap);
                    frame.setContentPane(new PlayArea(frame, mainMenu, scoreboard,theme,size,level, rules,sap));
                    frame.setVisible(true);
                    //frame.dispose();
                    
                }
            }
        );
        Size5.setFont(new Font("Dialog", Font.PLAIN, 20));
        sizeOption.add(Size5);
        sizeOption.setFont(new Font("Dialog", Font.PLAIN, 20));
        
        JLabel levelLabel = new JLabel(" || Level: "+ level );
        levelLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        
        JLabel sizeLabel = new JLabel(" || Size: "+ size );
        sizeLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        //////////////////////////////////////////
        menubar.add(levOption);
        menubar.add(sizeOption);
        menubar.add(mainMenuButtonTop);
        menubar.add(restartGame);
        menubar.add(scoreButton);
        menubar.add(score);
        menubar.add(levelLabel);
        menubar.add(sizeLabel);
        frame.setJMenuBar(menubar);
        frame.validate();
        frame.repaint();
        //frame.setUndecorated(true);
        
    }
    //////////////////Start Component//////////////////////
    private void initializeComponents(int n) {
        setLayout(new GridLayout(n, n));
        Box[][] boxes = mygamecpu.getBoxes();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++){
                add(boxes[i][j]);
                boxes[i][j].addActionListener(new EventHandler(mygamecpu, i, j));
            }
        }
        setVisible(true);
    }
    /////////////Winning Dialogue///////////////
    protected void showWinningDialog() {
        JTextField name = new JTextField();
        Object[] input = {
            "Enter your name : ", name
        };
        int option = JOptionPane.showConfirmDialog(this, input, "Yippe!! You Won.Submit You Score",JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
        if(option == JOptionPane.OK_OPTION) {
            mygamecpu.setPlayerData(name.getText().trim());
            JOptionPane.showMessageDialog(this, "Saved!! Try again , You can do better .\n");
            setVisible(false);
            frame.setContentPane(scoreboard);
            scoreboard.setVisible(true);
            scoreboard.showList();
        }
    }
    //////////////////////////////
    protected void setCount(int movesCount) {
        score.setText("Moves Count : " + movesCount);
    }
}

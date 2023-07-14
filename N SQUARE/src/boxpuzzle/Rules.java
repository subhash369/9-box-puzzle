package boxpuzzle;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Collections;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Rules extends JPanel{

	private final JFrame frame;
	private BoxPuzzle mainMenu;
	private ScoreBoard scoreboard;
	private int size;
	private String level;
	private String theme;
	Color colorBack;
    Color colorFront;
	SimpleAudioPlayer sap;
	Rules (JFrame frame)
	{
		this.frame = frame;

	}
	
	public void setMainMenuR(BoxPuzzle panel, ScoreBoard scoreboard, int size, String lev, String theme,SimpleAudioPlayer sap) {
		this.mainMenu = panel;
		this.scoreboard = scoreboard;
		this.size = size;
		this.level = lev;
        this.theme = theme;
        this.sap =sap;
        
	}
	
	    
	public void showRules() {
		
        
        JPanel panel = this;
        JPanel Np = new JPanel();
        
        
        Theme t = new Theme(theme);
    	colorBack = t.colorBack;
        colorFront = t.colorFront;

        
        
    	Np.setBackground(colorBack);
        // panel.removeAll();
        JMenuBar menubar = new JMenuBar();
        JMenu options = new JMenu("Options");
        
        //
        JMenuItem newGameButton = new JMenuItem("Start New Game");
        newGameButton.setFont(new Font("Dialog", Font.PLAIN, 20));
        newGameButton.addActionListener(
            new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    panel.setVisible(false);
                    //panel.removeAll();
                    frame.setContentPane(new PlayArea(frame, mainMenu, scoreboard,theme,size,level,(Rules)panel,sap));
                    frame.setVisible(true);
                }
            }
        );
        options.add(newGameButton);
        
        //
        JMenuItem mainMenuButton = new JMenuItem("Back To Main Menu");
        mainMenuButton.setFont(new Font("Dialog", Font.PLAIN, 20));
        mainMenuButton.addActionListener(
            new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e)
                {
                	
                    panel.setVisible(false);
                    panel.removeAll();
                    frame.repaint();
                    frame.setContentPane(mainMenu);
                    mainMenu.setMenu();
                    mainMenu.setVisible(true);
                }
            }
        );
        
        options.add(mainMenuButton);
        options.setFont(new Font("Dialog", Font.PLAIN, 20));
        menubar.add(options);
        frame.setJMenuBar(menubar);
        frame.validate();
        frame.repaint();
        
        JLabel l2 = labelWithoutImage("RULES",80);
        
        JLabel l3 = labelWithoutImage("Hello, your purpose is to arrange the box in order as shown below.",20);
        
        JLabel l1 = labelImage("4moves");

        JPanel btn = new JPanel(); 
        
        JButton startButton2 = new JButton("Lets Play !! ");
        startButton2.setFont(new Font("Dialog", Font.PLAIN, 40));
        startButton2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	panel.setVisible(false);
            	//panel.removeAll();
                frame.setContentPane(new PlayArea(frame, mainMenu, scoreboard,theme,size,level,(Rules)panel,sap));
                frame.setVisible(true);
            }
            
        });
        JButton backToMenu = new JButton("Back");
        backToMenu.setFont(new Font("Dialog", Font.PLAIN, 40));
        backToMenu.addActionListener(
        		new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	 panel.setVisible(false);
            	 
            	 Np.setVisible(false);
            	 panel.removeAll();
            	 frame.repaint();
            	 frame.setContentPane(mainMenu);
                 mainMenu.setMenu();
                 mainMenu.setVisible(true);
                 
            }
            
        });
        MyBorder border = new MyBorder(theme);
        startButton2.setBackground(colorFront);
        startButton2.setBorder(border);
        backToMenu.setBorder(border);
        backToMenu.setBackground(colorFront);
        btn.add( startButton2);
        btn.add(backToMenu);
        
    	btn.setBackground(colorBack);
        /////////////
        JScrollPane pane = new JScrollPane(Np);
        
        
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        Np.setLayout(new BoxLayout(Np, BoxLayout.Y_AXIS));
        Np.add(l2);
        Np.add(l3);
        Np.add(l1);

        Np.add(btn);
        panel.add(pane);
        setVisible(true);
       
    }
	
	JLabel labelWithoutImage(String text, int size)
	{
		 JLabel l9 = new JLabel(text,SwingConstants.LEFT);
	        l9.setFont(new Font("Dialog", Font.PLAIN, size));
	        l9.setForeground(colorFront);
	        l9.setBorder(new EmptyBorder(20,100,20,100));//top,left,bottom,right
	        l9.setAlignmentX(CENTER_ALIGNMENT);
	        l9.setAlignmentY(CENTER_ALIGNMENT);
	        return l9;
	        
	}
	JLabel labelImage(String name)
	{

        JLabel l10 = new JLabel("",SwingConstants.CENTER);
        String path = new File("src/resources/image/"+name+".png").getAbsolutePath();
        ImageIcon i1 = new ImageIcon(path);
        l10.setIcon(i1);
        l10.setBorder(new EmptyBorder(0,100,0,100));//top,left,bottom,right
        l10.setAlignmentX(CENTER_ALIGNMENT);
        l10.setAlignmentY(CENTER_ALIGNMENT);
        return l10;
	}
	
	
}

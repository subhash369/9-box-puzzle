package boxpuzzle;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

public class ScoreBoard extends JPanel { // Its is of panel type
    private ArrayList<PlayerData> list3;
    private ArrayList<PlayerData> list4;
    private ArrayList<PlayerData> list5;

    private final JFrame frame;
    private BoxPuzzle mainMenu;
    private String theme;
    private Rules rules;
    private int size;
    private String level;
    SimpleAudioPlayer sap;
    Color colorBack;
    static Color colorFront;

    ScoreBoard(JFrame frame) { // everything is added to list
        this.frame = frame;
        list3 = new ArrayList<>();
        // list3.add(new PlayerData("Lahari", 14,"E"));
        // list3.add(new PlayerData("Smaran", 12,"M"));
        // list3.add(new PlayerData("Vaishnav", 10,"M"));
        // list3.add(new PlayerData("Nitheesh", 18,"H"));
        

        
        list4 = new ArrayList<>();

       
        
        list5 = new ArrayList<>();

        

    }

    public void setMainMenu(BoxPuzzle panel, String theme, int size, String lev, Rules rules, SimpleAudioPlayer sap) {
        mainMenu = panel;
        this.level = lev;
        this.rules = rules;
        this.theme = theme;
        this.size = size;
        this.sap = sap;

    }

    public static void setCellsAlignment(JTable table, int alignment) {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(alignment);

        TableModel tableModel = table.getModel();

        for (int columnIndex = 0; columnIndex < tableModel.getColumnCount(); columnIndex++) {
            table.getColumnModel().getColumn(columnIndex).setCellRenderer(rightRenderer);
        }
    }

    public void addPlayerData(PlayerData playerData) {

        if (size == 3)
            list3.add(playerData);
        else if (size == 4)
            list4.add(playerData);
        else if (size == 5)
            list5.add(playerData);
    }

    public void showList() {

        JPanel panel = this;
        panel.removeAll();

        Theme t = new Theme(theme);
        colorBack = t.colorBack;
        colorFront = t.colorFront;

        panel.setBackground(colorBack);
        JMenuBar menubar = new JMenuBar();
        JMenu options = new JMenu("Options");

        JMenuItem newGameButton = new JMenuItem("Start New Game");
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setVisible(false);
                frame.setContentPane(new PlayArea(frame, mainMenu, (ScoreBoard) panel, theme, size, level, rules, sap));
            }
        });
        newGameButton.setFont(new Font("Dialog", Font.PLAIN, 20));
        options.add(newGameButton);

        JMenuItem mainMenuButton = new JMenuItem("Back To Main Menu");
        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setVisible(false);
                frame.setContentPane(mainMenu);
                mainMenu.setMenu();
                mainMenu.setVisible(true);
            }
        });
        mainMenuButton.setFont(new Font("Dialog", Font.PLAIN, 20));
        options.setFont(new Font("Dialog", Font.PLAIN, 20));
        options.add(mainMenuButton);
        menubar.add(options);
        frame.setJMenuBar(menubar);
        frame.validate();
        frame.repaint();

        list3 = sortit(list3);
        list4 = sortit(list4);
        list5 = sortit(list5);

        setLayout(new BorderLayout());
        JTable myTable3 = tableMaker(list3);
        JTable myTable4 = tableMaker(list4);
        JTable myTable5 = tableMaker(list5);

        JLabel l9 = labMaker("Score Board", 40, 20, 100, 20, 100);
        l9.setForeground(colorFront);
        JLabel l10 = labMaker("", 20, 0, 50, 20, 50);
        JLabel l11 = labMaker("", 20, 0, 50, 20, 50);
        JLabel l12 = labMaker("", 20, 50, 50, 50, 50);

        add(l9, BorderLayout.NORTH);
        add(l10, BorderLayout.EAST);
        add(l11, BorderLayout.WEST);

        JPanel downOptions = new JPanel();

        JButton startGame = new JButton("Play Again!!");
        startGame.setFont(new Font("Dialog", Font.PLAIN, 40));
        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setVisible(false);
                frame.setContentPane(new PlayArea(frame, mainMenu, (ScoreBoard) panel, theme, size, level, rules, sap));
            }
        });
        MyBorder border = new MyBorder(theme);
        startGame.setBackground(colorFront);
        startGame.setBorder(border);
        downOptions.add(startGame);

        downOptions.setBackground(colorBack);
        JButton backMainMenu = new JButton("Main Menu");
        backMainMenu.setFont(new Font("Dialog", Font.PLAIN, 40));
        backMainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setVisible(false);
                frame.setContentPane(mainMenu);
                mainMenu.setMenu();
                mainMenu.setVisible(true);
            }
        });
        backMainMenu.setBackground(colorFront);
        backMainMenu.setBorder(border);
        downOptions.add(backMainMenu);

        JPanel scoresboardcenters = new JPanel();
        scoresboardcenters.setLayout(new BoxLayout(scoresboardcenters, BoxLayout.Y_AXIS));

        JLabel l13 = labMaker("9 BOXES", 40, 20, 100, 20, 100);
        l13.setForeground(colorBack);
        scoresboardcenters.add(l13);
        scoresboardcenters.add(new JScrollPane(myTable3));

        JLabel l14 = labMaker("16 BOXES", 40, 20, 100, 20, 100);
        l14.setForeground(colorBack);
        scoresboardcenters.add(l14);
        scoresboardcenters.add(new JScrollPane(myTable4));

        JLabel l15 = labMaker("25 BOXES", 40, 20, 100, 20, 100);
        l15.setForeground(colorBack);
        scoresboardcenters.add(l15);
        scoresboardcenters.add(new JScrollPane(myTable5));

        panel.add(new JScrollPane(scoresboardcenters), BorderLayout.CENTER);
        add(downOptions, BorderLayout.SOUTH);

        setVisible(true);
    }

    static JTable tableMaker(ArrayList<PlayerData> list) {
        String col[] = { "Rank", "Name", "Moves", "Level" };
        DefaultTableModel model3 = new DefaultTableModel(col, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // all cells false
                return false;
            }
        };

        int rank = 1;
        for (PlayerData playerData : list) {
            String row[] = new String[4];
            row[0] = "" + rank++;
            row[1] = playerData.getName();
            row[2] = "" + playerData.getScore();
            row[3] = "" + playerData.getLev();
            model3.addRow(row);
        }

        JTable myTable3 = new JTable(model3);
        myTable3.setFont(new Font("Dialog", Font.PLAIN, 20));
        JTableHeader tableHeader3 = myTable3.getTableHeader();
        Font headerFont3 = new Font("Verdana", Font.PLAIN, 25);
        tableHeader3.setBackground(colorFront);
        tableHeader3.setFont(headerFont3);
        setCellsAlignment(myTable3, SwingConstants.CENTER);
        myTable3.setRowHeight(40);
        myTable3.setLayout(new BorderLayout());
        return myTable3;
    }

    static ArrayList<PlayerData> sortit(ArrayList<PlayerData> list) {
        Collections.sort(list, new Comparator<PlayerData>() {
            public int compare(PlayerData u1, PlayerData u2) {
                int a = 1;
                if (u1.getLev() == "E")
                    a = 1;
                if (u1.getLev() == "M")
                    a = 10;
                else if (u1.getLev() == "H")
                    a = 100;

                int b = 1;
                if (u2.getLev() == "E")
                    b = 1;
                if (u2.getLev() == "M")
                    b = 10;
                else if (u2.getLev() == "H")
                    b = 100;

                double scorea = (u1.getScore() * 1.0) / a;
                double scoreb = (u2.getScore() * 1.0) / b;
                return Double.compare(scorea, scoreb);
            }
        });
        return list;
    }

    static JLabel labMaker(String text, int size, int a, int b, int c, int d) {
        JLabel l12 = new JLabel(text, SwingConstants.CENTER);
        l12.setFont(new Font("Dialog", Font.PLAIN, size));
        l12.setBorder(new EmptyBorder(a, b, c, d));// top,left,bottom,right
        l12.setAlignmentX(CENTER_ALIGNMENT);
        l12.setAlignmentY(CENTER_ALIGNMENT);
        return l12;
    }

}

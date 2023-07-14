package boxpuzzle;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;


public class Box extends JButton {
    private int positionX;
    private int positionY;
    private String theme;
    protected int getPositionX() {
        return positionX;
    }
    
    protected int getPositionY() {
        return positionY;
    }
    
    
    // used to replace the box position
    Box(int positionX, int positionY, int num, String theme) {
        super(String.valueOf(num));			// string to int then pass the value 
        this.theme = theme;
        this.positionX = positionX;
        this.positionY = positionY;
        Color colorBack;
        Color colorFront;
        MyBorder border = new MyBorder(theme);
        
        Theme t = new Theme(theme);
    	colorBack = t.colorBack;
        colorFront = t.colorFront;

        setFont(new Font("Tahoma", Font.BOLD, 100));
        setFocusable(false);
    }
}
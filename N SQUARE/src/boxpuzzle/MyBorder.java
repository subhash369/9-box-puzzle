package boxpuzzle;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.Border;

public class MyBorder implements Border {

    private int thickness_ = 7;
    private Color white = Color.WHITE;
    private Color gray = Color.GRAY;
    private Color black = Color.BLACK;
    Color colorBack;	//black
    Color colorFront;
    String theme ="Darl";
    MyBorder(String theme)
    {
    	this.theme=theme;
    }
    public void paintBorder(Component c, Graphics g, int x, int y, int width,
            int height) {
        Color oldColor = g.getColor();
        int i;

        Theme t = new Theme(theme);
    	colorBack = t.colorBack;
        colorFront = t.colorFront;

        for (i = 0; i < thickness_; i++) {
            g.setColor(gray);
            g.drawRect(x + i, y + i, width - i - i - 1, height - i - i - 1); //White Rectangle
        }
        for (i = 0; i < thickness_/2; i++) {
            g.setColor(colorFront);
            g.drawLine(x + i, y + i, (width - x) - (i * 2), y + i); //Top Outer Edge
            g.drawLine(x + i, y + i, x + i, (height - y) - (i * 2));  //Left Outer Edge
        }
        for (i = thickness_/2; i < thickness_; i++) {
            g.setColor(white);
            g.drawLine(x + i, y + i, (width - x) - (i * 2), y + i); //Top Inner Edge
            g.drawLine(x + i, y + i, x + i, (height - y) - (i * 2)); //Left Inner Edge

        }
        g.setColor(oldColor);
    }

    public int getThickness() {
        return thickness_;
    }

    public void setThickness(int i) {
        thickness_ = i;
    }

    public boolean isBorderOpaque() {
        return true;
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(thickness_, thickness_, thickness_, thickness_);
    }

}
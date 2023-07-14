package boxpuzzle;

import java.awt.Color;
import java.util.*;

public class GameCPU {
    
	Color colorBack;
    Color colorFront;
    String theme;    
    private Box[][] boxes;
    private Box emptyBox;	
    private PlayArea playArea;
    private int init;
    private int movesCount;
    private ScoreBoard scoreboard;
    private String lev;
    private int size;
    GameCPU(PlayArea playArea, ScoreBoard panel2,String theme, int size, String lev) {
        this.playArea = playArea;
        this.scoreboard = panel2;
        this.size=size;
        this.lev=lev;
        init = 0;
        this.theme = theme;
        
        Theme t = new Theme(theme);
    	colorBack = t.colorBack;
        colorFront = t.colorFront;

        movesCount = 0;
        int a=100;int b=100;
        
        if(lev=="E")
        {
        	a=5;b=5;
        }
        else if(lev=="M")
        {
        	a=50;b=50;
        }
        else
        {
        	a=500;b=500;
        }
        
        boxes = getRandomBoxes(a,b);
       
    }
   
    
    protected Box[][] getBoxes(){
        return boxes;
    }
    
    protected int getMovesCount() {
        return movesCount;
    }
    
    boolean canMakeMove(int positionX, int positionY) {
        int differenceX = Math.abs(emptyBox.getPositionX() - positionX);
        int differenceY = Math.abs(emptyBox.getPositionY() - positionY);
        if (differenceX <= 1 && differenceY <= 1 && !(differenceX == 1 && differenceY == 1))
            return true;
        return false;
    }
    
    private boolean checkWinningStatus(int size) {
        boolean hasWon = true;
        
        int num = 1;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                try {
                    if (Integer.parseInt(boxes[i][j].getText().trim()) != num) {
                        hasWon = false;
                    }
                } catch (NumberFormatException e) {
                    hasWon  = false;
                }
                num++;
                if(num==size*size) return hasWon;
            }
        }
        
        return hasWon;
    }
    
    protected void makeMove(int positionX, int positionY) {
        if (canMakeMove(positionX, positionY)) {
        	MyBorder border = new MyBorder(theme);
        	boxes[emptyBox.getPositionX()][emptyBox.getPositionY()].setBackground(colorBack);
        	boxes[emptyBox.getPositionX()][emptyBox.getPositionY()].setBorder(border);
           boxes[positionX][positionY].setBackground(colorFront);
            boxes[positionX][positionY].setBorder(null);
            Box box = boxes[positionX][positionY];
            emptyBox.setText(box.getText());
            emptyBox.setEnabled(true);
            box.setEnabled(false);
            box.setText("");
            emptyBox = box;
            if(init==1) {
                movesCount++;
                playArea.setCount(movesCount);
            }
            if (checkWinningStatus(size) && init!=0) {
                playArea.showWinningDialog();
            }
            
        }
    }    

    private ArrayList<Box> getValidMoves(Box boxes[][]) {
        ArrayList<Box> validMoves = new ArrayList<Box>();
        int emptyBoxX = emptyBox.getPositionX();
        int emptyBoxY = emptyBox.getPositionY();
        
        for (int i=-1; i<=1; i++) {
            for (int j=-1; j<=1; j++) {
                if(Math.abs(j)==Math.abs(i)) continue;
                int x = emptyBoxX + i;
                int y = emptyBoxY + j;
                if(x < size && x >= 0 && y < size && y >= 0) {
                    validMoves.add(boxes[x][y]);
                }
            }
        }

        return validMoves;
    }
     
    private Box[][] getRandomBoxes(int a, int b) {
    	
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i <= (size*size)-1; i++) {
            list.add(i);
        }
        
        Box[][] boxes = new Box[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int boxValue = list.get(size*i+j) + 1;		//Value b/w [1 and 9]
                boxes[i][j] = new Box(i, j, boxValue,theme);
                boxes[i][j]. setForeground(colorFront);
                MyBorder border = new MyBorder(theme);
                boxes[i][j].setBorder(border); 
                boxes[i][j]. setBackground(colorBack);
               
                if (boxValue == size*size) {
                    boxes[i][j].setEnabled(false); //disable 9th box > we can red it 
                    boxes[i][j].setText("");
                    boxes[i][j].setBackground(colorFront);
                    boxes[i][j].setBorder(null);
                    emptyBox = boxes[i][j];
                }
            }
        }
        
        
        
        this.boxes = boxes;
        while(checkWinningStatus(size))
        {
        
        	makeMove(2, 1);
        	Random random = new Random();
        	int minMovesCount = a;       //Randomiserr                         
        	int movesCount =  minMovesCount + random.nextInt(b); 
        
        
        for (int i = 0; i < movesCount; i++) {
            ArrayList<Box> validMoves = getValidMoves(boxes);
            int r = random.nextInt(validMoves.size());
            Box randomBox = validMoves.get(r);
            makeMove(randomBox.getPositionX(), randomBox.getPositionY());
        }
        }
        init = 1;
        boxes[emptyBox.getPositionX()][emptyBox.getPositionY()].setBackground(colorFront);
        boxes[emptyBox.getPositionX()][emptyBox.getPositionY()].setBorder(null);
        return boxes;
    }

    void setPlayerData(String name) {
        PlayerData playerData = new PlayerData(name, movesCount,lev);
        scoreboard.addPlayerData(playerData);
    }
}

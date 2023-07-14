package boxpuzzle;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class EventHandler implements ActionListener{   
    
    private int positionX;
    private int positionY;
    private GameCPU gameCPU;		

    @Override
    public void actionPerformed(ActionEvent e) {
    	if(gameCPU.canMakeMove(positionX, positionY))
    		
            playSound(new File("src/resources/audio/"+"aud"+".wav")); 		//Correct move
    	else
    		playSound(new File("src/resources/audio/"+"err"+".wav"));			//incorrect move 
    	
    	gameCPU.makeMove(positionX, positionY);
        
    }
    protected void playSound(File soundName)
    {
      try 
      {
    	  AudioInputStream audioInputStream = AudioSystem.getAudioInputStream((soundName).getAbsoluteFile( ));
    	  Clip clip = AudioSystem.getClip( );
    	  clip.open(audioInputStream);
    	  clip.start( );
      }
      catch(Exception ex)
      {
        ex.printStackTrace( );
      }
    }
    EventHandler(GameCPU gameCPU, int positionX, int positionY) {
        this.gameCPU = gameCPU;
        this.positionX = positionX;
        this.positionY = positionY;
            
    }
    
}
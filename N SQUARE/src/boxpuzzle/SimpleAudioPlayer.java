package boxpuzzle;

import java.io.File; 
import java.io.IOException; 
import java.util.Scanner; 

import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.Clip; 
import javax.sound.sampled.LineUnavailableException; 
import javax.sound.sampled.UnsupportedAudioFileException; 

public class SimpleAudioPlayer 
{ 

	Long currentFrame; 
	Clip clip; 
	int i=3;
	String status; 
	
	AudioInputStream audioInputStream; 
	
	String filePath = "src/resources/audio/Fur3.wav"; 
	
	public SimpleAudioPlayer()throws UnsupportedAudioFileException,IOException, LineUnavailableException 
	{ 
		// create AudioInputStream object
		String a = "filePath"+i;
		audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile()); 
		
		// create clip reference 
		clip = AudioSystem.getClip(); 
		
		// open audioInputStream to the clip 
		clip.open(audioInputStream); 
		
		clip.loop(Clip.LOOP_CONTINUOUSLY); 
	} 

	public static void main(String[] args) 
	{ 
		try
		{ 
			//filePath = "Your path for the file"; 
			 
			SimpleAudioPlayer audioPlayer = 
							new SimpleAudioPlayer(); 
			
			audioPlayer.play(); 
			audioPlayer.pause(); 
			
			audioPlayer.resumeAudio(); 
			
			Scanner sc = new Scanner(System.in); 
			
			while (true) 
			{ 
				int c = sc.nextInt(); 
				if (c == 6) 
					break;
				
				 
			} 
			sc.close(); 
		} 
		
		catch (Exception ex) 
		{ 
			System.out.println("Error with playing sound."); 
			ex.printStackTrace(); 
		
		} 
	} 
	
	// Method to play the audio 
	public void play() 
	{ 
		//start the clip 
		clip.start(); 
		status = "play"; 
	} 
	
	// Method to pause the audio 
	public void pause() 
	{ 
		if (status.equals("paused")) 
		{ 
			System.out.println("audio is already paused"); 
			return; 
		} 
		clip.stop(); 
		status = "paused"; 
	} 
	public void resumeAudio() throws UnsupportedAudioFileException,IOException, LineUnavailableException 
	{ 
		if (status.equals("play")) 
		{ 
			System.out.println("Audio is already "+ 
			"being played"); 
			return; 
		} 
		clip.close(); 
		resetAudioStream(); 
		this.play(); 
	} 
	public void stop() throws UnsupportedAudioFileException, IOException, LineUnavailableException 
	{ 
		clip.stop(); 
		clip.close(); 
	} 
	
	public void setI() throws UnsupportedAudioFileException, IOException, LineUnavailableException
	{
		int j=this.i;
		j++;
		if(j>=4)
			j=1;
		this.i=j;
		pause();
		resumeAudio();
	}
	
	public void resetAudioStream() throws UnsupportedAudioFileException, IOException, LineUnavailableException 
	{ 
		if(i==1){filePath = "src/resources/audio/Fur1.wav"; }
		else if(i==2){filePath = "src/resources/audio/Fur2.wav"; }
		else if(i==3){filePath = "src/resources/audio/Fur3.wav"; }
		
		audioInputStream = AudioSystem.getAudioInputStream( new File(filePath).getAbsoluteFile()); 
		clip.open(audioInputStream); 
		clip.loop(Clip.LOOP_CONTINUOUSLY); 
	} 

} 

			
	
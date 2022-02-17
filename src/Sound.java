import java.io.*;
import java.util.HashMap;
import javax.sound.sampled.*;
import javax.swing.*;

// Estendo JFrame perché deve essere attiva la classe
public class Sound extends JFrame
{
	private static Clip clip;
	
	private static HashMap <String, AudioInputStream> sounds = new HashMap<>();
	
	
	public Sound()
	{
		this.setVisible(false); // Per non far vedere la finestra JFrame
		
		try //todo caricare tutti i vari suoni
		{
			
			File fileTest = new File("src/../tracks/test.wav");
			File fileHop = new File("src/../tracks/hop.wav");
			File fileSquash = new File("src/../tracks/squash.wav");
			
			AudioInputStream test = AudioSystem.getAudioInputStream(fileTest);
			
			sounds.put("hop",AudioSystem.getAudioInputStream(fileHop));
			
			
			// Get a sound clip resource.
		    clip = AudioSystem.getClip();
			// Open audio clip and load samples from the audio input stream.
			//clip.open(test);
			//clip.start();
		}
		catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
		{
			e.printStackTrace();
		}
		
	}
	
	/*public static void updateSound()
	{
		if(
	}*/
	
	
	public static void soundMorteAuto ()
	{
		clip.start();
	}
	
	public static void soundMorteAcqua ()
	{
		clip.start();
	}
	
	public static void soundPoint ()
	{
		clip.start();
	}
	
	public static void soundBonus ()
	{
		clip.start();
	}
	
	public static void soundHop ()
	{
		
		try
		{
			clip.open(sounds.get("hop"));
		}
		catch (LineUnavailableException | IOException e)
		{
			e.printStackTrace();
		}
		
		clip.start();
		//clip.close();
	}
	
}
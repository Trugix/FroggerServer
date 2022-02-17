import java.io.*;
import java.util.HashMap;
import javax.sound.sampled.*;
import javax.swing.*;

// Estendo JFrame perché deve essere attiva la classe
public class Sound// extends JFrame
{
	private  Clip clip;
	
	private  HashMap <String, AudioInputStream> sounds = new HashMap<>();
	
	private  boolean active=false;
	
	public Sound()
	{
		//this.setVisible(false); // Per non far vedere la finestra JFrame
		
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
	
	/*public void updateSound()
	{
		if(!clip.isActive() && clip.isOpen())
		{
			clip.close();
			active=false;
		}
	}*/
	
	
	public void soundMorteAuto ()
	{
		clip.start();
	}
	
	public void soundMorteAcqua ()
	{
		clip.start();
	}
	
	public void soundPoint ()
	{
		clip.start();
	}
	
	public void soundBonus ()
	{
		clip.start();
	}
	
	public void soundHop ()
	{
		if(!clip.isActive() && !clip.isOpen())
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
			//active=true;
		}
		else
		{
				clip.close();
		}
	}
	
}
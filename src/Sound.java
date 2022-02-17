import java.io.*;
import javax.sound.sampled.*;
import javax.swing.*;

// Estendo JFrame perché deve essere attiva la classe
public class Sound extends JFrame
{
	
	public Sound()
	{
		this.setVisible(false); // Per non far vedere la finestra JFrame
		
		try
		{
			
			File url = new File("src/../tracks/test.wav"); //Carico l'audio
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
			// Get a sound clip resource.
			Clip clip = AudioSystem.getClip();
			// Open audio clip and load samples from the audio input stream.
			clip.open(audioIn);
			clip.start();
		}
		catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
		{
			e.printStackTrace();
		}
	}
	
}
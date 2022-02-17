import java.io.*;
import java.util.HashMap;
import javax.sound.sampled.*;


public class Sound
{
	private Clip clipHop;
	
	private HashMap<String, AudioInputStream> sounds = new HashMap<>();
	
	
	public Sound()
	{
	
		
		try //todo caricare tutti i vari suoni
		{
			
			File fileTest = new File("src/../tracks/test.wav");
			File fileHop = new File("src/../tracks/hop.wav");
			File fileSquash = new File("src/../tracks/squash.wav");
			
			AudioInputStream test = AudioSystem.getAudioInputStream(fileTest);
			
			clipHop = AudioSystem.getClip();
			
			sounds.put("hop", AudioSystem.getAudioInputStream(fileHop));
			clipHop.open(sounds.get("hop"));
			
		}
		catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	
	public void soundMorteAuto ()
	{
		Clip clip = null;
		
		try
		{
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File("src/../tracks/squash.wav")));
		}
		catch (LineUnavailableException | IOException | UnsupportedAudioFileException e)
		{
			e.printStackTrace();
		}
		
		clip.setFramePosition(0);
		clip.start();
	}

	/*public void soundMorteTempo ()
	{
		clip.start();
	}*/
	
	public void soundMorteAcqua ()
	{
		Clip clip = null;
		
		try
		{
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File("src/../tracks/plunk.wav")));
		}
		catch (LineUnavailableException | IOException | UnsupportedAudioFileException e)
		{
			e.printStackTrace();
		}
		
		clip.setFramePosition(0);
		clip.start();
	}
	
	public void soundPoint ()
	{
		Clip clip = null;
		
		try
		{
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File("src/../tracks/point.wav")));
		}
		catch (LineUnavailableException | IOException | UnsupportedAudioFileException e)
		{
			e.printStackTrace();
		}
		
		clip.setFramePosition(0);
		clip.start();
	}
	
	
	public void soundHop()
	{
		Clip clip = null;
		
		try
		{
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File("src/../tracks/hop.wav")));
		}
		catch (LineUnavailableException | IOException | UnsupportedAudioFileException e)
		{
			e.printStackTrace();
		}
		
		clip.setFramePosition(0);
		clip.start();
	}
	
}
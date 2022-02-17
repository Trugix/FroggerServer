import java.io.*;
import javax.sound.sampled.*;


public class Sound
{
	
	public Sound()
	{
	
	}
	
	
	public static void soundMorteAuto ()
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
		
		assert clip != null;
			clip.setFramePosition(0);
		clip.start();
	}

	/*public static void soundMorteTempo ()
	{
		clip.start();
	}*/
	
	public static void soundMorteAcqua ()
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
		
		assert clip != null;
			clip.setFramePosition(0);
		clip.start();
	}
	
	public static void soundPoint ()
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
		
		assert clip != null;
			clip.setFramePosition(0);
		clip.start();
	}
	
	
	public static void soundHop()
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
		
		assert clip != null;
		clip.setFramePosition(0);
		clip.start();
	}
	
}
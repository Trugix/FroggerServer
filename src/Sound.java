import java.io.*;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import javax.sound.sampled.*;
import javax.swing.*;

// Estendo JFrame perché deve essere attiva la classe
public class Sound// extends JFrame
{
	private Clip clipHop;
	
	private HashMap<String, AudioInputStream> sounds = new HashMap<>();
	
	private boolean active = false;
	
	public Sound()
	{
		//this.setVisible(false); // Per non far vedere la finestra JFrame
		
		try //todo caricare tutti i vari suoni
		{
			
			File fileTest = new File("src/../tracks/test.wav");
			File fileHop = new File("src/../tracks/hop.wav");
			File fileSquash = new File("src/../tracks/squash.wav");
			
			AudioInputStream test = AudioSystem.getAudioInputStream(fileTest);
			
			clipHop = AudioSystem.getClip();
			
			sounds.put("hop", AudioSystem.getAudioInputStream(fileHop));
			clipHop.open(sounds.get("hop"));
			
			// Get a sound clip resource.
			
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
	
	
	/*public void soundMorteAuto ()
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
	}*/
	
	public void soundHop()
	{
		Thread t = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				{
					Clip t = null;
					
					
					try
					{
						t = AudioSystem.getClip();
					}
					catch (LineUnavailableException e)
					{
						e.printStackTrace();
					}
					try
					{
						t.open(AudioSystem.getAudioInputStream(new File("src/../tracks/hop.wav")));
					}
					catch (LineUnavailableException e)
					{
						e.printStackTrace();
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
					catch (UnsupportedAudioFileException e)
					{
						e.printStackTrace();
					}
					t.setFramePosition(0);
					t.start();
					
					/*clipHop.setFramePosition(0);
					clipHop.start();*/
				}
			}
		});
		t.run();
	}
	
}
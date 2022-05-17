import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound  //classe che implementa i suoni
{
	
	private static final String SOUND = "src/../tracks/"; //cartella dei suoni
	private static final String MORTE_AUTO =SOUND+"squash.wav";
	private static final String MORTE_ACQUA =SOUND+"plunk.wav";
	private static final String TIC_TOC =SOUND+"time.wav";
	private static final String POINT =SOUND+"point.wav";
	private static final String HOP =SOUND+"hop.wav";
	
	/**
	 * suono riprodotto quando la rana viene schiacciata
	 */
	public static void soundMorteAuto ()    //todo vedere se usare un solo metodo
	{
		Clip clip = null;
		
		try //provo ad aprire la traccia audio
		{
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File(MORTE_AUTO)));
		}
		catch (LineUnavailableException | IOException | UnsupportedAudioFileException e)
		{
			e.printStackTrace();
		}
		
		assert clip != null;
			clip.setFramePosition(0);   //riporto la traccia audio all'inizio
		clip.start();   //riproduco la traccia audio
	}

	/*public static void soundMorteTempo ()//todo decidere se aggiungere sto coso
	{
		clip.start();
	}*/
	
	/**
	 * suono riprodotto quando la rana muore in acqua
	 */
	public static void soundMorteAcqua ()
	{
		Clip clip = null;
		
		try
		{
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File(MORTE_ACQUA)));
		}
		catch (LineUnavailableException | IOException | UnsupportedAudioFileException e)
		{
			e.printStackTrace();
		}
		
		assert clip != null;
			clip.setFramePosition(0);
		clip.start();
	}
	
	/**
	 * suono riprodotto quando il tempo sta per finire
	 */
	public static void soundTicToc ()
	{
		Clip clip = null;

		try
		{
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File(TIC_TOC)));
		}
		catch (LineUnavailableException | IOException | UnsupportedAudioFileException e)
		{
			e.printStackTrace();
		}

		assert clip != null;
		clip.setFramePosition(0);
		clip.loop(2);
	}
	
	/**
	 * suono riprodotto quando si raggiunge la destinazione
	 */
	public static void soundPoint ()
	{
		Clip clip = null;
		
		try
		{
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File(POINT)));
		}
		catch (LineUnavailableException | IOException | UnsupportedAudioFileException e)
		{
			e.printStackTrace();
		}
		
		assert clip != null;
			clip.setFramePosition(0);
		clip.start();
	}
	
	/**
	 * suono riprodotto durante ogni movimento della rana
	 */
	public static void soundHop()
	{
		Clip clip = null;
		
		try
		{
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File(HOP)));
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
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
	 * riproduco i suoni
	 *
	 * @param id suono da riprodurre
	 */
	public static void soundStart (String id)
	{
		Clip clip = null;
		
		try //provo ad aprire la traccia audio
		{
			clip = AudioSystem.getClip();
			switch (id) //scelgo quale traccia audio riprodurre
			{
				case "auto" -> clip.open(AudioSystem.getAudioInputStream(new File(MORTE_AUTO)));
				case "acqua" -> clip.open(AudioSystem.getAudioInputStream(new File(MORTE_ACQUA)));
				case "tempo" -> clip.open(AudioSystem.getAudioInputStream(new File(TIC_TOC)));
				case "point" -> clip.open(AudioSystem.getAudioInputStream(new File(POINT)));
				case "hop" -> clip.open(AudioSystem.getAudioInputStream(new File(HOP)));
			}
		}
		catch (LineUnavailableException | IOException | UnsupportedAudioFileException e)
		{
			e.printStackTrace();
		}
		
		assert clip != null;
		clip.setFramePosition(0);   //riporto la traccia audio all'inizio
		clip.start();   //riproduco la traccia audio
	}
}
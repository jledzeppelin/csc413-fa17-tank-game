
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundPlayer {
  
  public void playSound(String filename) {
    try {
      Clip clip = AudioSystem.getClip();
      URL file = this.getClass().getClassLoader().getResource(filename);
      
      AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
      clip.open(inputStream);
      clip.start();
      
    } catch (UnsupportedAudioFileException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (LineUnavailableException e) {
      e.printStackTrace();
    }
  }
}
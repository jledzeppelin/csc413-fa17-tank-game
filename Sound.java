
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Sound {
  File sound;
  public Sound(File SoundFile ){
    this.sound = SoundFile;
  
  }
  public void PlaySound(){
    try{
      Clip clip = AudioSystem.getClip();
      
    } catch (Exception e){
      
    }
  }
}

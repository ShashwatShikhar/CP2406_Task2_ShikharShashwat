import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundManager {
    Clip clip;

    public SoundManager() {
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("traffic_sound.wav"));
            this.clip = AudioSystem.getClip();
            this.clip.open(inputStream);
            this.clip.loop(-1);
            Thread.sleep(100000000L);
        } catch (Exception var2) {
            System.out.print("Hello world");
        }

    }
}

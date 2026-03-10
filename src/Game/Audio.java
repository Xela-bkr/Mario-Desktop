package Game;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
public class Audio {
	
	//****VARIABLES****//
	private Clip clip;
	
	//****CONSTRUCTEUR****//
	public Audio(String son) {
		try {
			AudioInputStream audio = AudioSystem.getAudioInputStream(getClass().getResource(son));
			this.clip = AudioSystem.getClip();
			this.clip.open(audio);
		}
		catch(Exception e) {
			e.printStackTrace();
			//System.out.println("Exception Audio");
		}
	}
	
	//****GETTERS****//
	public Clip getClip() {return this.clip;}
	
	public void setLoop(boolean loop) {
        if (clip != null) {
            if (loop) {
                this.clip.loop(Clip.LOOP_CONTINUOUSLY); // Boucle infinie
            } else {
                this.clip.loop(0); // Pas de boucle
            }
        }
    }
	
	//****METHODES****//
	public void play() {if(clip != null) {this.clip.start();}}
	public void stop() {this.clip.stop();}
	
	public static void playSound(String son) {
		Audio s = new Audio(son);
		s.play();
	}

}

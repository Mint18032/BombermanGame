package uet.oop.bomberman.Sound;

import javax.sound.sampled.*;
import java.io.File;

public class Sound {
    private String usage;
    private Boolean isPlaying = false;
    private File file;
    private Clip clip;

    public Sound(String usage) {
        this.usage = usage;
        String path = "res/sounds/" + usage + ".wav";
        try {
            file = new File(path);
            clip = (Clip)AudioSystem.getLine(new Line.Info(Clip.class));
            clip.open(AudioSystem.getAudioInputStream(file));
        }
        catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public void play() {
        if (usage.equals("music")) {
            clip.loop(100);
        } else {
            clip.start();
        }
        isPlaying = true;
    }

    public void stop(){
        clip.stop();
        isPlaying = false;
    }

    public boolean getIsPlaying() {
        return this.isPlaying;
    }
}

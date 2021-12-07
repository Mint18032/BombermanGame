package uet.oop.bomberman.Sound;

import javax.sound.sampled.*;
import java.io.File;

public class Sound {
    public static Boolean muted = false;
    private String usage;
    private Boolean isPlaying = false;
    private File file;
    private Clip clip;

    public Sound() {
    }

    public Sound(String usage) {
        setUsage(usage);
    }

    public void setUsage(String usage) {
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
        if (muted) {
            return;
        }
        if (usage.equals("music")) {
            clip.loop(100);
        } else {
            clip.start();
        }
        isPlaying = true;
    }

    public void stop() {
        clip.stop();
        isPlaying = false;
    }

    public static void mute() {
        muted = true;
    }

    public static void unmute() {
        muted = false;
    }

    public boolean getIsPlaying() {
        return this.isPlaying;
    }
}

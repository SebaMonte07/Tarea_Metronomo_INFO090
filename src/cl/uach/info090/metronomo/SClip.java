package cl.uach.info090.metronomo;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SClip {
	/**
	 * Esta clase lee el archivo de audio que utilizare en el metodo tick() de la clase Pulse
	 * Adjunto referencia: https://www.youtube.com/watch?v=ZiKH29VVO5U&t=115s&ab_channel=jk
	 */

    private Clip audioClip;
    private AudioInputStream audioStream;

    public SClip(String path) {
        File audioFile = new File(path);
        try {
            audioStream = AudioSystem.getAudioInputStream(audioFile);
        }
        catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }

        AudioFormat format = audioStream.getFormat();
        var info = new DataLine.Info(Clip.class, format);

        try {
            audioClip = AudioSystem.getClip();
            audioClip.open(audioStream);
        }
        catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        new Thread( () -> {
            audioClip.setFramePosition(0);
            audioClip.start();
        }){}.start();
    }

    public void loop() {
        new Thread( () -> {
            audioClip.setFramePosition(0);
            audioClip.loop(Clip.LOOP_CONTINUOUSLY);
        }){}.start();
    }

    public void stop() {
        audioClip.stop();
    }
}

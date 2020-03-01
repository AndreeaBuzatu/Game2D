package uk.ac.lancaster.scc210.engine.content;

import org.jsfml.audio.Sound;
import org.jsfml.audio.SoundSource;

public class SoundManager extends ContentManager<Sound> {
    /**
     * Instantiates a new Content manager.
     *
     */
    public SoundManager(SoundBufferManager soundBufferManager) {
        super(new Sound());

        soundBufferManager.content.forEach((key, value) -> put(key, new Sound(value)));
    }

    public void playSound(String name) {
        if (content.containsKey(name)) {
            Sound sound = content.get(name);

            // Only play the sound if the sound has been flagged as stop. This is done to prevent the sound from restarting while already playing
            if (sound.getStatus() == SoundSource.Status.STOPPED) {
                sound.play();
            }
        }
    }
}

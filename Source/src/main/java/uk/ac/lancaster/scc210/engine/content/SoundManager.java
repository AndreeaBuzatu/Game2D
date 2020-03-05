package uk.ac.lancaster.scc210.engine.content;

import jdk.net.SocketFlow;
import org.jsfml.audio.Sound;
import org.jsfml.audio.SoundSource;
import org.jsfml.system.Time;

/**
 * The type Sound manager.
 */
public class SoundManager extends ContentManager<Sound> {
    private final Time TIME_RESTART = Time.getSeconds(3);

    private final int MAX_SOUNDS = 255;

    private int currentSounds;

    /**
     * Instantiates a new Content manager.
     *
     * @param soundBufferManager the sound buffer manager
     */
    public SoundManager(SoundBufferManager soundBufferManager) {
        super(new Sound());

        soundBufferManager.content.forEach((key, value) -> put(key, new Sound(value)));

        currentSounds = 0;
    }

    @Override
    public Sound get(String key) {
        Sound sound = super.get(key);

        if (sound.getStatus() != SoundSource.Status.STOPPED) {
            currentSounds++;

            return sound;
        }

        currentSounds--;

        return null;
    }

    /**
     * Play sound.
     *
     * @param name the name
     */
    public void playSound(String name) {
        if (content.containsKey(name) && currentSounds < MAX_SOUNDS) {
            Sound sound = content.get(name);

            // Cut the sound if it is playing for more than TIME_RESTART allows
            if (sound.getStatus() == SoundSource.Status.PLAYING && sound.getPlayingOffset().asSeconds() > TIME_RESTART.asSeconds()) {
                sound.stop();

                sound.play();
            }

            // Only play the sound if the sound has been flagged as stop. This is done to prevent the sound from restarting while already playing
            if (sound.getStatus() == SoundSource.Status.STOPPED) {
                sound.play();
            }
        }
    }
}

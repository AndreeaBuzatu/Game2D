package uk.ac.lancaster.scc210.engine.content;

import org.jsfml.audio.Sound;
import org.jsfml.audio.SoundBuffer;
import org.jsfml.audio.SoundSource;
import uk.ac.lancaster.scc210.engine.resources.deserialise.SerialisedSound;

import java.util.List;

public class SoundBufferManager extends ContentManager<SoundBuffer> {
    /**
     * Instantiates a new Content manager.
     */
    public SoundBufferManager(List<SerialisedSound> serialisedSound) {
        super(new SoundBuffer());

        serialisedSound.parallelStream().forEach(sound -> put(sound.getName(), sound.getSoundBuffer()));
    }

    public static void playSound(Sound sound) {
        // Only play the sound if the sound has been flagged as stop. This is done to prevent the sound from restarting while already playing
        if (sound.getStatus() == SoundSource.Status.STOPPED) {
            sound.play();
        }
    }
}

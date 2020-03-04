package uk.ac.lancaster.scc210.engine.content;

import org.jsfml.audio.SoundBuffer;
import uk.ac.lancaster.scc210.engine.resources.deserialise.SerialisedSound;

import java.util.List;

/**
 * The type Sound buffer manager.
 */
public class SoundBufferManager extends ContentManager<SoundBuffer> {
    /**
     * Instantiates a new Content manager.
     *
     * @param serialisedSound the serialised sound
     */
    public SoundBufferManager(List<SerialisedSound> serialisedSound) {
        super(new SoundBuffer());

        serialisedSound.parallelStream().forEach(sound -> put(sound.getName(), sound.getSoundBuffer()));
    }
}

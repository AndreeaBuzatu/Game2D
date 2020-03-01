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
}

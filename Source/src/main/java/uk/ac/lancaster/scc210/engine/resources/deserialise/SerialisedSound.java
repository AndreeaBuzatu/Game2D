package uk.ac.lancaster.scc210.engine.resources.deserialise;

import org.jsfml.audio.SoundBuffer;
import uk.ac.lancaster.scc210.engine.resources.ResourceLoader;
import uk.ac.lancaster.scc210.engine.resources.ResourceNotFoundException;
import uk.ac.lancaster.scc210.engine.resources.SoundBufferAdapter;

/**
 * The type Serialised sound.
 */
public class SerialisedSound implements Serialised {
    private final SoundBuffer soundBuffer;

    private final String name;

    /**
     * Instantiates a new Serialised sound.
     *
     * @param name     the name
     * @param fileName the file name
     * @throws ResourceNotFoundException the resource not found exception
     */
    SerialisedSound(String name, String fileName) throws ResourceNotFoundException {
        this.name = name;

        SoundBufferAdapter soundAdapter = new SoundBufferAdapter(new SoundBuffer());

        ResourceLoader.loadFromStream(soundAdapter, fileName);

        soundBuffer = soundAdapter.getResource();
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets sound buffer.
     *
     * @return the sound buffer
     */
    public SoundBuffer getSoundBuffer() {
        return soundBuffer;
    }
}

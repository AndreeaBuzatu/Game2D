package uk.ac.lancaster.scc210.engine.resources.deserialise;

import org.jsfml.audio.SoundBuffer;
import uk.ac.lancaster.scc210.engine.resources.ResourceLoader;
import uk.ac.lancaster.scc210.engine.resources.ResourceNotFoundException;
import uk.ac.lancaster.scc210.engine.resources.SoundBufferAdapter;

public class SerialisedSound implements Serialised {
    private final SoundBuffer soundBuffer;

    private final String name;

    SerialisedSound(String name, String fileName) throws ResourceNotFoundException {
        this.name = name;

        SoundBufferAdapter soundAdapter = new SoundBufferAdapter(new SoundBuffer());

        ResourceLoader.loadFromStream(soundAdapter, fileName);

        soundBuffer = soundAdapter.getResource();
    }

    public String getName() {
        return name;
    }

    public SoundBuffer getSoundBuffer() {
        return soundBuffer;
    }
}

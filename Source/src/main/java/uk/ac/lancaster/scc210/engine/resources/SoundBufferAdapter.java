package uk.ac.lancaster.scc210.engine.resources;

import org.jsfml.audio.SoundBuffer;

import java.io.IOException;
import java.nio.file.Path;

public class SoundBufferAdapter extends Resource<SoundBuffer> {
    /**
     * Instantiates a new Resource.
     *
     * @param soundBuffer the buffer to load
     */
    public SoundBufferAdapter(SoundBuffer soundBuffer) {
        super(null);

        resource = soundBuffer;
    }

    @Override
    public void loadFromFile(Path path) throws IOException {
        resource.loadFromFile(path);
    }
}
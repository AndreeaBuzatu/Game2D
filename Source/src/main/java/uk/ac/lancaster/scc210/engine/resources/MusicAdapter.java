package uk.ac.lancaster.scc210.engine.resources;

import org.jsfml.audio.Music;

import java.io.IOException;
import java.io.InputStream;

/**
 * The type Music adapter.
 */
public class MusicAdapter extends Resource<Music> {
    /**
     * Instantiates a new Music adapter.
     *
     * @param music the music
     */
    public MusicAdapter(Music music) {
        super(null);

        resource = music;
    }

    @Override
    public void loadFromFile(InputStream stream) throws IOException {
        resource.openFromStream(stream);
    }
}

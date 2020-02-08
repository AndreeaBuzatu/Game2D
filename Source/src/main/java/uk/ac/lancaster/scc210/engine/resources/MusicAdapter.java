package uk.ac.lancaster.scc210.engine.resources;

import org.jsfml.audio.Music;

import java.io.IOException;
import java.nio.file.Path;

public class MusicAdapter extends Resource<Music> {
    /**
     *
     */
    public MusicAdapter(Music music) {
        super(null);

        resource = music;
    }

    @Override
    public void loadFromFile(Path path) throws IOException {
        resource.openFromFile(path);
    }
}

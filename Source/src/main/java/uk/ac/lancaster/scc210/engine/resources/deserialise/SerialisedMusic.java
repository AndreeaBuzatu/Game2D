package uk.ac.lancaster.scc210.engine.resources.deserialise;

import org.jsfml.audio.Music;
import uk.ac.lancaster.scc210.engine.resources.MusicAdapter;
import uk.ac.lancaster.scc210.engine.resources.ResourceLoader;
import uk.ac.lancaster.scc210.engine.resources.ResourceNotFoundException;

public class SerialisedMusic implements Serialised {
    private final Music music;

    private final String name;

    SerialisedMusic(String name, String fileName) throws ResourceNotFoundException {
        this.name = name;

        MusicAdapter musicAdapter = new MusicAdapter(new Music());

        ResourceLoader.loadFromFile(musicAdapter, fileName);

        music = musicAdapter.getResource();
    }

    public Music getMusic() {
        return music;
    }

    public String getName() {
        return name;
    }
}

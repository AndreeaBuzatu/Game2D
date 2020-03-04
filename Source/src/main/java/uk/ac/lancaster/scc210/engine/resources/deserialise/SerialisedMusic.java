package uk.ac.lancaster.scc210.engine.resources.deserialise;

import org.jsfml.audio.Music;
import uk.ac.lancaster.scc210.engine.resources.MusicAdapter;
import uk.ac.lancaster.scc210.engine.resources.ResourceLoader;
import uk.ac.lancaster.scc210.engine.resources.ResourceNotFoundException;

/**
 * The type Serialised music.
 */
public class SerialisedMusic implements Serialised {
    private final Music music;

    private final String name;

    /**
     * Instantiates a new Serialised music.
     *
     * @param name     the name
     * @param fileName the file name
     * @throws ResourceNotFoundException the resource not found exception
     */
    SerialisedMusic(String name, String fileName) throws ResourceNotFoundException {
        this.name = name;

        MusicAdapter musicAdapter = new MusicAdapter(new Music());

        ResourceLoader.loadFromStream(musicAdapter, fileName);

        music = musicAdapter.getResource();
    }

    /**
     * Gets music.
     *
     * @return the music
     */
    public Music getMusic() {
        return music;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }
}

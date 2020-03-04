package uk.ac.lancaster.scc210.engine.content;

import org.jsfml.audio.Music;
import uk.ac.lancaster.scc210.engine.resources.deserialise.SerialisedMusic;

import java.util.List;

/**
 * The type Music manager.
 */
public class MusicManager extends ContentManager<Music> {
    /**
     * Instantiates a new Content manager.
     *
     * @param serialisedMusic the serialised music
     */
    public MusicManager(List<SerialisedMusic> serialisedMusic) {
        super(new Music());

        serialisedMusic.parallelStream().forEach(piece -> put(piece.getName(), piece.getMusic()));
    }
}

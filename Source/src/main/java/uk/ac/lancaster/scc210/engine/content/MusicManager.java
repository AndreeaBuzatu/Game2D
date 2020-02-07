package uk.ac.lancaster.scc210.engine.content;

import org.jsfml.audio.Music;
import uk.ac.lancaster.scc210.engine.resources.deserialise.SerialisedMusic;

import java.util.List;

public class MusicManager extends ContentManager<Music> {
    /**
     * Instantiates a new Content manager.
     */
    public MusicManager(List<SerialisedMusic> serialisedMusic) {
        super(null);

        serialisedMusic.parallelStream().forEach(piece -> {
            System.out.println("Name: " + piece.getName());

            put(piece.getName(), piece.getMusic());
        });
    }
}

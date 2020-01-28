package uk.ac.lancaster.scc210.engine.resources;

import org.jsfml.graphics.Image;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Adapts the loading of a SFML image into a generic Resource.
 */
public class ImageAdapter extends Resource<Image> {
    /**
     * Instantiates a new Image adapter.
     *
     * @param image the image to load
     */
    public ImageAdapter(Image image) {
        super(image);
    }

    @Override
    public void loadFromFile(Path path) throws IOException {
        resource.loadFromFile(path);
    }
}

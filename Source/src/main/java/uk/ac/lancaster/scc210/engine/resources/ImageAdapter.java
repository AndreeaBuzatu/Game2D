package uk.ac.lancaster.scc210.engine.resources;

import org.jsfml.graphics.Image;

import java.io.IOException;
import java.nio.file.Path;

/**
 * The type Image adapter.
 */
public class ImageAdapter extends Resource<Image> {
    /**
     * Instantiates a new Image adapter.
     *
     * @param image the image
     */
    public ImageAdapter(Image image) {
        super(image);
    }

    @Override
    public void loadFromFile(Path path) throws IOException {
        resource.loadFromFile(path);
    }
}

package uk.ac.lancaster.scc210.resources;

import org.jsfml.graphics.Image;

import java.io.IOException;
import java.nio.file.Path;

public class ImageAdapter extends Resource<Image> {
    public ImageAdapter(Image image) {
        super(image);
    }

    @Override
    public void loadFromFile(Path path) throws IOException {
        resource.loadFromFile(path);
    }
}

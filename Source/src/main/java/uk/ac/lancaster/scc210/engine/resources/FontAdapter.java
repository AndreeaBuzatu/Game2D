package uk.ac.lancaster.scc210.engine.resources;

import org.jsfml.graphics.Font;

import java.io.IOException;
import java.nio.file.Path;

public class FontAdapter extends Resource<Font> {
    /**
     * Instantiates a new Font Adapter.
     *
     * @param font the font
     */
    public FontAdapter(Font font) {
        super(font);
    }

    @Override
    public void loadFromFile(Path path) throws IOException {
        resource.loadFromFile(path);
    }
}

package uk.ac.lancaster.scc210.engine.resources;

import org.jsfml.graphics.Font;

import java.io.IOException;
import java.io.InputStream;

/**
 * The type Font adapter.
 */
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
    public void loadFromFile(InputStream stream) throws IOException {
        resource.loadFromStream(stream);
    }
}

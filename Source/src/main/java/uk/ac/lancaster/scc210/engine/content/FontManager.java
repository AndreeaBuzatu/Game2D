package uk.ac.lancaster.scc210.engine.content;

import org.jsfml.graphics.Font;
import uk.ac.lancaster.scc210.engine.resources.FontAdapter;
import uk.ac.lancaster.scc210.engine.resources.ResourceLoader;
import uk.ac.lancaster.scc210.engine.resources.ResourceNotFoundException;

/**
 * The type Font manager.
 */
public class FontManager extends ContentManager<Font> {
    /**
     * Instantiates a new Content manager.
     *
     * @throws ResourceNotFoundException the resource not found exception
     */
    public FontManager() throws ResourceNotFoundException {
        super(null);

        FontAdapter fontAdapter = new FontAdapter(new Font());

        ResourceLoader.loadFromStream(fontAdapter, "font.ttf");

        put("font", fontAdapter.getResource());
    }
}

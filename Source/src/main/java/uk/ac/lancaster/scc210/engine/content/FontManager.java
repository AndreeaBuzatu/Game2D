package uk.ac.lancaster.scc210.engine.content;

import org.jsfml.graphics.Font;
import uk.ac.lancaster.scc210.engine.resources.FontAdapter;
import uk.ac.lancaster.scc210.engine.resources.ResourceLoader;
import uk.ac.lancaster.scc210.engine.resources.ResourceNotFoundException;

public class FontManager extends ContentManager<Font> {
    /**
     * Instantiates a new Content manager.
     */
    public FontManager() throws ResourceNotFoundException {
        super(null);

        FontAdapter fontAdapter = new FontAdapter(new Font());
        FontAdapter fontAdapter2 = new FontAdapter(new Font());
        ResourceLoader.loadFromStream(fontAdapter, "font.ttf");
        ResourceLoader.loadFromStream(fontAdapter2, "font2.ttf");
        put("font2", fontAdapter2.getResource());
        put("font", fontAdapter.getResource());


    }
}

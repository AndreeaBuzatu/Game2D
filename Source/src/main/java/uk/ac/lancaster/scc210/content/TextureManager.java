package uk.ac.lancaster.scc210.content;

import org.jsfml.graphics.Texture;
import org.jsfml.graphics.TextureCreationException;
import uk.ac.lancaster.scc210.TextureAtlas;

import java.io.IOException;

public class TextureManager extends ContentManager<Texture> {
    private static final TextureManager INSTANCE = new TextureManager();

    public static final int SPRITE_HEIGHT = 64;

    public static final int SPRITE_WIDTH = 64;

    private TextureManager() {
        super(new MissingTexture().getTexture());

        try {
            TextureAtlas textureAtlas = new TextureAtlas();

            put("example", textureAtlas.get(0, 0));

        } catch (IOException | TextureCreationException e) {
            e.printStackTrace();
        }
    }

    public static TextureManager getInstance() {
        return INSTANCE;
    }
}

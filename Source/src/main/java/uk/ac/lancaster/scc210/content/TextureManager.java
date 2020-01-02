package uk.ac.lancaster.scc210.content;

import org.jsfml.graphics.Texture;
import uk.ac.lancaster.scc210.resources.ResourceNotFoundException;

public class TextureManager extends ContentManager<Texture> {
    private static final int SPRITE_WIDTH = 64;

    private static final int SPRITE_HEIGHT = 64;

    public TextureManager() throws ResourceNotFoundException {
        super(new MissingTexture(SPRITE_WIDTH, SPRITE_HEIGHT).getTexture());

        TextureAtlas textureAtlas = new TextureAtlas("spritesheet.png", SPRITE_WIDTH, SPRITE_HEIGHT);

        put("example", textureAtlas.get(0, 0));
    }
}

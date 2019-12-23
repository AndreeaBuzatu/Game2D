package uk.ac.lancaster.scc210;

import org.jsfml.graphics.Image;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Texture;
import org.jsfml.graphics.TextureCreationException;
import uk.ac.lancaster.scc210.content.TextureManager;

import java.io.IOException;
import java.nio.file.Paths;

public class TextureAtlas {
    private Image textureAtlas;

    public TextureAtlas() throws IOException {
        textureAtlas = new Image();

        // TODO: Add proper resource loading
        textureAtlas.loadFromFile(Paths.get("src", "main", "resources", "spritesheet.png"));
    }

    /**
     * Get a Texture from the texture atlas. The row and columns are 0 indexed
     * @param column column to capture from the texture atlas
     * @param row  row to capture from the texture atlas
     * @return a texture with the texture atlas as it's texture and a rectangle containing the requested sprite
     */
    public Texture get(final int column, final int row) throws TextureCreationException {
        Texture texture = new Texture();

        IntRect area = new IntRect(
                column * TextureManager.SPRITE_HEIGHT, row * TextureManager.SPRITE_WIDTH,
                TextureManager.SPRITE_HEIGHT, TextureManager.SPRITE_WIDTH);

        texture.loadFromImage(textureAtlas, area);

        return texture;
    }
}

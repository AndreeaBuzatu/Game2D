package uk.ac.lancaster.scc210;

import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;

import java.io.IOException;
import java.nio.file.Paths;

class SpriteSheet {
    private final int TEXTURE_HEIGHT = 64;

    private final int TEXTURE_WIDTH = 64;

    private Texture spriteSheet;

    SpriteSheet() throws IOException {
        spriteSheet = new Texture();

        // TODO: Add proper resource loading
        spriteSheet.loadFromFile(Paths.get("src", "main", "resources", "spritesheet.png"));
    }

    /**
     * Get a Sprite from the SpriteSheet. The row and columns are 0 indexed
     * @param column column to capture from the spritesheet
     * @param row  row to capture from the spritesheet
     * @return a sprite with the spritesheet with the spritesheet as it's texture and a rectangle containing the requested sprite
     */
    Sprite getSprite(final int column, final int row) {
        Sprite sprite = new Sprite();

        sprite.setTexture(spriteSheet);

        sprite.setTextureRect(new IntRect(column * TEXTURE_HEIGHT, row * TEXTURE_WIDTH, TEXTURE_HEIGHT, TEXTURE_WIDTH));

        return sprite;
    }
}

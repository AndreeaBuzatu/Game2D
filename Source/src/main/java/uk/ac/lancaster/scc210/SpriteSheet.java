package uk.ac.lancaster.scc210;

import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import uk.ac.lancaster.scc210.content.SpriteManager;

import java.io.IOException;
import java.nio.file.Paths;

public class SpriteSheet {
    private Texture spriteSheet;

    public SpriteSheet() throws IOException {
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
    public Sprite get(final int column, final int row) {
        Sprite sprite = new Sprite();

        sprite.setTexture(spriteSheet);

        sprite.setTextureRect(new IntRect(column * SpriteManager.SPRITE_HEIGHT, row * SpriteManager.SPRITE_WIDTH, SpriteManager.SPRITE_HEIGHT, SpriteManager.SPRITE_WIDTH));

        return sprite;
    }
}

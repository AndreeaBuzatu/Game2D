package uk.ac.lancaster.scc210.content;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Image;
import org.jsfml.graphics.Texture;
import org.jsfml.graphics.TextureCreationException;

class MissingTexture {
    private final Color PURPLE = new Color(255, 0, 255);

    private final Texture texture;

    private final int spriteWidth, spriteHeight;

    private final Image image;

    MissingTexture(final int spriteWidth, final int spriteHeight) {
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;

        image = new Image();

        image.create(spriteWidth, spriteHeight);

        createImage();

        texture = new Texture();

        // TODO: Handle this properly
        try {
            texture.loadFromImage(image);
        } catch (TextureCreationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generate an image in the pattern of:
     * P W
     * W P
     * This is used for for when SpriteManager is unable to find the sprite from the key
     */
    private void createImage() {
        final int halfWidth = spriteWidth / 2;

        final int halfHeight = spriteHeight / 2;

        for (int x = 0; x < spriteWidth; x++) {
            for (int y = 0; y < spriteHeight; y++) {
                if ((x < halfWidth && y < halfHeight) || (x > halfWidth && y > halfWidth)) {
                    image.setPixel(x, y, PURPLE);

                } else {
                    image.setPixel(x, y, Color.BLACK);
                }
            }
        }
    }

    Texture getTexture() {
        return texture;
    }
}

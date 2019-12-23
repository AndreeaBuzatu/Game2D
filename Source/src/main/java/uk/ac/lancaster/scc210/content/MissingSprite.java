package uk.ac.lancaster.scc210.content;

import org.jsfml.graphics.*;

class MissingSprite {
    private final Color PURPLE = new Color(255, 0, 255);

    private final Texture texture;

    private Image image;

    MissingSprite() {
        image = new Image();

        image.create(SpriteManager.SPRITE_WIDTH, SpriteManager.SPRITE_HEIGHT);

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
        final int halfWidth = SpriteManager.SPRITE_WIDTH / 2;

        final int halfHeight = SpriteManager.SPRITE_HEIGHT / 2;

        for (int x = 0; x < SpriteManager.SPRITE_WIDTH; x++) {
            for (int y = 0; y < SpriteManager.SPRITE_HEIGHT; y++) {
                if ((x < halfWidth && y < halfHeight) || (x > halfWidth && y > halfWidth)) {
                    image.setPixel(x, y, PURPLE);

                } else {
                    image.setPixel(x, y, Color.WHITE);
                }
            }
        }
    }

    Sprite getSprite() {
        return new Sprite(texture);
    }
}

package uk.ac.lancaster.scc210.content;

import org.jsfml.graphics.Image;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Texture;
import org.jsfml.graphics.TextureCreationException;
import uk.ac.lancaster.scc210.resources.ImageAdapter;
import uk.ac.lancaster.scc210.resources.ResourceLoader;
import uk.ac.lancaster.scc210.resources.ResourceNotFoundException;

public class TextureAtlas {
    private final String fileName;

    private final int spriteWidth, spriteHeight;

    private final Image textureAtlas;

    public TextureAtlas(final String fileName, final int spriteWidth, final int spriteHeight) throws ResourceNotFoundException {
        this.fileName = fileName;
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;

        textureAtlas = new Image();

        ResourceLoader.loadFromFile(new ImageAdapter(textureAtlas), fileName);
    }

    /**
     * Get a Texture from the texture atlas. The row and columns are 0 indexed
     *
     * @param column column to capture from the texture atlas
     * @param row    row to capture from the texture atlas
     * @return a texture with the texture atlas as it's texture and a rectangle containing the requested sprite
     */
    public Texture get(final int column, final int row) throws ResourceNotFoundException {
        Texture texture = new Texture();

        IntRect area = new IntRect(column * spriteHeight, row * spriteWidth, spriteHeight, spriteWidth);

        try {
            texture.loadFromImage(textureAtlas, area);

            return texture;

        } catch (TextureCreationException e) {
            throw new ResourceNotFoundException(fileName);
        }
    }
}

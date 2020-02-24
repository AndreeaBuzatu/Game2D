package uk.ac.lancaster.scc210.engine.content;

import org.jsfml.graphics.Image;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Texture;
import org.jsfml.graphics.TextureCreationException;
import uk.ac.lancaster.scc210.engine.resources.ImageAdapter;
import uk.ac.lancaster.scc210.engine.resources.ResourceLoader;
import uk.ac.lancaster.scc210.engine.resources.ResourceNotFoundException;

/**
 * TextureAtlas represents a single image which is organised into a grid pattern.
 * A TextureAtlas can be subdivided into Textures per the grid.
 * The grid must have a fixed width and height (although they can be different).
 */
public class TextureAtlas {
    private final Image textureAtlas;

    private final int spriteWidth, spriteHeight;

    private final MissingTexture missingTexture;

    private String fileName;

    /**
     * Instantiates a new Texture atlas.
     *
     * @param fileName     the file name from the file system
     * @param spriteWidth  the sprite width
     * @param spriteHeight the sprite height
     * @throws ResourceNotFoundException occurs when the image cannot be found on the file system
     */
    public TextureAtlas(final String fileName, final int spriteWidth, final int spriteHeight) throws ResourceNotFoundException {
        this.fileName = fileName;
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;

        textureAtlas = new Image();

        missingTexture = new MissingTexture(spriteWidth, spriteHeight);

        ResourceLoader.loadFromStream(new ImageAdapter(textureAtlas), fileName);
    }

    /**
     * Instantiates a new Texture Atlas. This should only be used for the TextureAtlasManager, to handle unknown Texture Atlases
     *
     * @throws ResourceNotFoundException if the missing texture can't be created. If we reach this point, we're SOI
     */
    TextureAtlas() throws ResourceNotFoundException {
        this.spriteWidth = TextureManager.SPRITE_WIDTH;
        this.spriteHeight = TextureManager.SPRITE_HEIGHT;

        textureAtlas = new Image();

        missingTexture = new MissingTexture(spriteWidth, spriteHeight);
    }

    /**
     * Get a Texture from the texture atlas. The row and columns are 0 indexed
     *
     * @param row    row to capture from the texture atlas
     * @param column column to capture from the texture atlas
     * @return a texture with the texture atlas as it's texture and a rectangle containing the requested sprite
     */
    public Texture get(final int row, final int column) {
        Texture texture = new Texture();

        IntRect area = new IntRect(column * spriteWidth, row * spriteHeight, spriteWidth, spriteHeight);

        try {
            texture.loadFromImage(textureAtlas, area);

            return texture;

        } catch (TextureCreationException e) {
            // If the texture can't be created, return the missing texture
            return missingTexture.getTexture();
        }
    }

    /**
     * Get the number of columns in the TextureAtlas 0-indexed.
     *
     * @return the columns
     */
    public int getColumns() {
        // Subtract one to account for the 0-indexing
        return (textureAtlas.getSize().x / spriteWidth) - 1;
    }

    /**
     * Gets file name.
     *
     * @return the file name
     */
    String getFileName() {
        return fileName;
    }
}

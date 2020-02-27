package uk.ac.lancaster.scc210.engine.content;

import org.jsfml.graphics.Texture;
import uk.ac.lancaster.scc210.engine.resources.ResourceNotFoundException;
import uk.ac.lancaster.scc210.engine.resources.deserialise.SerialisedTextureAtlas;

import java.util.List;

/**
 * ContentManager for Textures.
 */
public class TextureManager extends ContentManager<Texture> {
    static final int SPRITE_WIDTH = 32;

    static final int SPRITE_HEIGHT = 32;

    /**
     * Instantiates a new TextureManager.
     * From a list of SerialisedTextureAtlases, place the textures into the TextureManager.
     * Note: Textures are namespaced from the filename of their TextureAtlas.
     * E.g. spritesheet.png containing 'example' will be stored as spritesheet.png:example
     *
     * @param serialisedTextureAtlases when the Textures cannot be found
     * @throws ResourceNotFoundException when MissingTexture cannot be created
     */
    public TextureManager(List<SerialisedTextureAtlas> serialisedTextureAtlases) throws ResourceNotFoundException {
        super(new MissingTexture(SPRITE_WIDTH, SPRITE_HEIGHT).getTexture());

        serialisedTextureAtlases.forEach(serialisedTextureAtlas -> serialisedTextureAtlas.getSerialisedTextures()
                .parallelStream()
                .forEach(texture -> {
                    String atlasName = serialisedTextureAtlas.getTextureAtlas().getFileName();

                    String key = String.format("%s:%s", atlasName, texture.getName());

                    put(key, texture.getTexture());
                }));
    }
}

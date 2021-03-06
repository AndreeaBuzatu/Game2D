package uk.ac.lancaster.scc210.engine.resources.deserialise;

import uk.ac.lancaster.scc210.engine.content.TextureAtlas;

import java.util.List;

/**
 * Represents a TextureAtlas when it has been Serialised from XML. This object is not used to access texture atlases.
 */
public class SerialisedTextureAtlas implements Serialised {
    private final TextureAtlas textureAtlas;

    private final List<SerialisedTexture> serialisedTextures;

    /**
     * Instantiates a new Serialised texture atlas.
     *
     * @param textureAtlas       used to store serialised textures for later use.
     * @param serialisedTextures serialised textures to be placed into this Object
     */
    SerialisedTextureAtlas(TextureAtlas textureAtlas, List<SerialisedTexture> serialisedTextures) {
        this.textureAtlas = textureAtlas;
        this.serialisedTextures = serialisedTextures;
    }

    /**
     * Gets texture atlas.
     *
     * @return the texture atlas
     */
    public TextureAtlas getTextureAtlas() {
        return textureAtlas;
    }

    /**
     * Gets serialised textures.
     *
     * @return the serialised textures
     */
    public List<SerialisedTexture> getSerialisedTextures() {
        return serialisedTextures;
    }
}

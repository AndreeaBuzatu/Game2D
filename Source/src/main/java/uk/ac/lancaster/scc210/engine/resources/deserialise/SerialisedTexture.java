package uk.ac.lancaster.scc210.engine.resources.deserialise;

import org.jsfml.graphics.Texture;

/**
 * Represents a Texture when it has been Serialised from XML. This object is not used to draw textures.
 */
public class SerialisedTexture implements Serialised {
    private final String name;

    private final Texture texture;

    /**
     * Instantiates a new Serialised texture.
     *
     * @param name    the name of the new texture
     * @param texture a texture which can be obtained from a SerialisedTextureAtlas
     */
    SerialisedTexture(String name, Texture texture) {
        this.name = name;
        this.texture = texture;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets texture.
     *
     * @return the texture
     */
    public Texture getTexture() {
        return texture;
    }
}

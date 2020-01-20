package uk.ac.lancaster.scc210.engine.resources.deserialise;

import org.jsfml.graphics.Texture;

/**
 * The type Serialised texture.
 */
public class SerialisedTexture implements Serialised {
    private final String name;

    private final Texture texture;

    /**
     * Instantiates a new Serialised texture.
     *
     * @param name    the name
     * @param texture the texture
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

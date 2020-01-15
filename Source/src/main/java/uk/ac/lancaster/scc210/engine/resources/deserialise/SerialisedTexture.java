package uk.ac.lancaster.scc210.engine.resources.deserialise;

import org.jsfml.graphics.Texture;

public class SerialisedTexture implements Serialised {
    private final String name;

    private final Texture texture;

    SerialisedTexture(String name, Texture texture) {
        this.name = name;
        this.texture = texture;
    }

    public String getName() {
        return name;
    }

    public Texture getTexture() {
        return texture;
    }
}

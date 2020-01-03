package uk.ac.lancaster.scc210.resources.deserialise;

import org.jsfml.graphics.Texture;

public class SerialisedTexture {
    private String name;

    private Texture texture;

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

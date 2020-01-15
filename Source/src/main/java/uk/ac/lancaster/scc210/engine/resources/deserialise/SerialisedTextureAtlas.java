package uk.ac.lancaster.scc210.engine.resources.deserialise;

import uk.ac.lancaster.scc210.engine.content.TextureAtlas;

import java.util.List;

public class SerialisedTextureAtlas implements Serialised {
    private final TextureAtlas textureAtlas;

    private final List<SerialisedTexture> serialisedTextures;

    SerialisedTextureAtlas(TextureAtlas textureAtlas, List<SerialisedTexture> serialisedTextures) {
        this.textureAtlas = textureAtlas;
        this.serialisedTextures = serialisedTextures;
    }

    public TextureAtlas getTextureAtlas() {
        return textureAtlas;
    }

    public List<SerialisedTexture> getSerialisedTextures() {
        return serialisedTextures;
    }
}

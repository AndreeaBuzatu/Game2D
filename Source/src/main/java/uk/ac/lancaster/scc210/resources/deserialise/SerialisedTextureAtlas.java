package uk.ac.lancaster.scc210.resources.deserialise;

import uk.ac.lancaster.scc210.content.TextureAtlas;

import java.util.List;

public class SerialisedTextureAtlas {
    private TextureAtlas textureAtlas;

    private List<SerialisedTexture> serialisedTextures;

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

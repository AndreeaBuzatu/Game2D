package uk.ac.lancaster.scc210.engine.content;

import uk.ac.lancaster.scc210.engine.resources.deserialise.SerialisedTextureAtlas;

import java.util.List;

public class TextureAtlasManager extends ContentManager<TextureAtlas> {
    public TextureAtlasManager(List<SerialisedTextureAtlas> serialisedTextureAtlases) {
        super(null);

        serialisedTextureAtlases.parallelStream().forEach(serialisedTextureAtlas -> {
            TextureAtlas textureAtlas = serialisedTextureAtlas.getTextureAtlas();

            put(textureAtlas.getFileName(), textureAtlas);
        });
    }
}

package uk.ac.lancaster.scc210.engine.content;

import uk.ac.lancaster.scc210.engine.resources.deserialise.SerialisedTextureAtlas;

import java.util.List;

/**
 * The type Texture atlas manager.
 */
public class TextureAtlasManager extends ContentManager<TextureAtlas> {
    /**
     * Instantiates a new Texture atlas manager.
     *
     * @param serialisedTextureAtlases the serialised texture atlases
     */
    public TextureAtlasManager(List<SerialisedTextureAtlas> serialisedTextureAtlases) {
        super(null);

        serialisedTextureAtlases.parallelStream().forEach(serialisedTextureAtlas -> {
            TextureAtlas textureAtlas = serialisedTextureAtlas.getTextureAtlas();

            put(textureAtlas.getFileName(), textureAtlas);
        });
    }
}

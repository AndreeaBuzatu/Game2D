package uk.ac.lancaster.scc210.engine.content;

import uk.ac.lancaster.scc210.engine.resources.ResourceNotFoundException;
import uk.ac.lancaster.scc210.engine.resources.deserialise.SerialisedTextureAtlas;

import java.util.List;

/**
 * ContentManager for TextureAtlases
 */
public class TextureAtlasManager extends ContentManager<TextureAtlas> {
    /**
     * Instantiates a new TextureAtlasManager.
     * From a list of SerialisedTextureAtlases, create TextureAtlases from them
     *
     * @param serialisedTextureAtlases the serialised texture atlases
     */
    public TextureAtlasManager(List<SerialisedTextureAtlas> serialisedTextureAtlases) throws ResourceNotFoundException {
        super(new TextureAtlas());

        serialisedTextureAtlases.parallelStream().forEach(serialisedTextureAtlas -> {
            TextureAtlas textureAtlas = serialisedTextureAtlas.getTextureAtlas();

            put(textureAtlas.getFileName(), textureAtlas);
        });
    }
}

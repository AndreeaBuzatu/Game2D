package uk.ac.lancaster.scc210.content;

import org.jsfml.graphics.Texture;
import uk.ac.lancaster.scc210.resources.deserialise.SerialisedTextureAtlas;

import java.util.List;

public class TextureManager extends ContentManager<Texture> {
    private static final int SPRITE_WIDTH = 64;

    private static final int SPRITE_HEIGHT = 64;

    public TextureManager(List<SerialisedTextureAtlas> serialisedTextureAtlases) {
        super(new MissingTexture(SPRITE_WIDTH, SPRITE_HEIGHT).getTexture());

        serialisedTextureAtlases.forEach(serialisedTextureAtlas -> {
            TextureAtlas textureAtlas = serialisedTextureAtlas.getTextureAtlas();

            serialisedTextureAtlas.getSerialisedTextures()
                    .parallelStream()
                    .forEach(texture -> put(texture.getName(), texture.getTexture()));
        });
    }
}

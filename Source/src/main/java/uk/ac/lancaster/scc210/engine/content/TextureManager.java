package uk.ac.lancaster.scc210.engine.content;

import org.jsfml.graphics.Texture;
import uk.ac.lancaster.scc210.engine.resources.deserialise.SerialisedTextureAtlas;

import java.util.List;

public class TextureManager extends ContentManager<Texture> {
    private static final int SPRITE_WIDTH = 32;

    private static final int SPRITE_HEIGHT = 32;

    public TextureManager(List<SerialisedTextureAtlas> serialisedTextureAtlases) {
        super(new MissingTexture(SPRITE_WIDTH, SPRITE_HEIGHT).getTexture());

        serialisedTextureAtlases.forEach(serialisedTextureAtlas -> serialisedTextureAtlas.getSerialisedTextures()
                .parallelStream()
                .forEach(texture -> {
                    String atlasName = serialisedTextureAtlas.getTextureAtlas().getFileName();

                    String key = String.format("%s:%s", atlasName, texture.getName());

                    put(key, texture.getTexture());
                }));
    }
}

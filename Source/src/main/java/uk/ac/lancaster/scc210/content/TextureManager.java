package uk.ac.lancaster.scc210.content;

import org.jsfml.graphics.Texture;
import uk.ac.lancaster.scc210.resources.deserialise.SerialisedTextureAtlas;

import java.util.ArrayList;
import java.util.List;

public class TextureManager extends ContentManager<Texture> {
    private static final int SPRITE_WIDTH = 64;

    private static final int SPRITE_HEIGHT = 64;

    private List<TextureAtlas> textureAtlases;

    public TextureManager(List<SerialisedTextureAtlas> serialisedTextureAtlases) {
        super(new MissingTexture(SPRITE_WIDTH, SPRITE_HEIGHT).getTexture());

        textureAtlases = new ArrayList<>();

        serialisedTextureAtlases.forEach(serialisedTextureAtlas -> {
            TextureAtlas textureAtlas = serialisedTextureAtlas.getTextureAtlas();

            serialisedTextureAtlas.getSerialisedTextures().forEach(texture ->
                    put(texture.getName(), texture.getTexture())
            );

            textureAtlases.add(textureAtlas);
        });
    }
}
